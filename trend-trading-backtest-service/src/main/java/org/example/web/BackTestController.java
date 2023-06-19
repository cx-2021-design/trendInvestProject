package org.example.web;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import org.example.pojo.AnnualProfit;
import org.example.pojo.IndexData;
import org.example.pojo.Profit;
import org.example.service.BackTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.example.pojo.Trade;
import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：cjy
 * 类名：BackTestController
 * 全路径类名：org.example.web.BackTestController
 * 父类或接口：
 * 描述：模拟回测
 */
@RestController
public class BackTestController {
    @Autowired BackTestService backTestService;

    @GetMapping("/simulate/{code}/{ma}/{buyThreshold}/{sellThreshold}/{serviceCharge}/{startDate}/{endDate}")
    @CrossOrigin
    public Map<String,Object> backTest(
            @PathVariable("code") String code
            ,@PathVariable("ma") int ma
            ,@PathVariable("buyThreshold") float buyThreshold
            ,@PathVariable("sellThreshold") float sellThreshold
            ,@PathVariable("serviceCharge") float serviceCharge
            ,@PathVariable("startDate") String strStartDate
            ,@PathVariable("endDate") String strEndDate
    ) throws Exception {
        List<IndexData> allIndexDatas = backTestService.listIndexData(code);

        //计算出开始日期和结束日期
        String indexStartDate = allIndexDatas.get(0).getDate();
        String indexEndDate = allIndexDatas.get(allIndexDatas.size()-1).getDate();

        //根据开始日期和结束日期获取对应日期范围的数据
        allIndexDatas = filterByDateRange(allIndexDatas,strStartDate, strEndDate);

        float sellRate = sellThreshold;
        float buyRate = buyThreshold;
        Map<String,?> simulateResult= backTestService.simulate(ma,sellRate, buyRate,serviceCharge, allIndexDatas);
        List<Profit> profits = (List<Profit>) simulateResult.get("profits");
        List<Trade> trades = (List<Trade>) simulateResult.get("trades");//取出交易集合

        float years = backTestService.getYear(allIndexDatas);//获取年份
        //计算指数的收益和趋势投资的收益，以及对应的年化收益率。
        float indexIncomeTotal = (allIndexDatas.get(allIndexDatas.size()-1).getClosePoint() - allIndexDatas.get(0).getClosePoint()) / allIndexDatas.get(0).getClosePoint();
        float indexIncomeAnnual = (float) Math.pow(1+indexIncomeTotal, 1/years) - 1;
        float trendIncomeTotal = (profits.get(profits.size()-1).getValue() - profits.get(0).getValue()) / profits.get(0).getValue();
        float trendIncomeAnnual = (float) Math.pow(1+trendIncomeTotal, 1/years) - 1;

        //获取交易统计信息
        int winCount = (Integer) simulateResult.get("winCount");
        int lossCount = (Integer) simulateResult.get("lossCount");
        float avgWinRate = (Float) simulateResult.get("avgWinRate");
        float avgLossRate = (Float) simulateResult.get("avgLossRate");

        List<AnnualProfit> annualProfits = (List<AnnualProfit>) simulateResult.get("annualProfits");

        Map<String,Object> result = new HashMap<>();
        result.put("indexDatas", allIndexDatas);
        result.put("indexStartDate", indexStartDate);
        result.put("indexEndDate", indexEndDate);
        result.put("profits", profits);
        result.put("trades", trades);
        result.put("years", years);
        result.put("indexIncomeTotal", indexIncomeTotal);
        result.put("indexIncomeAnnual", indexIncomeAnnual);
        result.put("trendIncomeTotal", trendIncomeTotal);
        result.put("trendIncomeAnnual", trendIncomeAnnual);

        result.put("winCount", winCount);
        result.put("lossCount", lossCount);
        result.put("avgWinRate", avgWinRate);
        result.put("avgLossRate", avgLossRate);

        result.put("annualProfits", annualProfits);

        return result;
    }

    private List<IndexData> filterByDateRange(List<IndexData> allIndexDatas, String strStartDate, String strEndDate) {
        if(StrUtil.isBlankOrUndefined(strStartDate) || StrUtil.isBlankOrUndefined(strEndDate) )
            return allIndexDatas;

        List<IndexData> result = new ArrayList<>();
        Date startDate = DateUtil.parse(strStartDate);
        Date endDate = DateUtil.parse(strEndDate);

        for (IndexData indexData : allIndexDatas) {
            Date date =DateUtil.parse(indexData.getDate());
            if(
                    date.getTime()>=startDate.getTime() &&
                            date.getTime()<=endDate.getTime()
            ) {
                result.add(indexData);
            }
        }
        return result;
    }

}