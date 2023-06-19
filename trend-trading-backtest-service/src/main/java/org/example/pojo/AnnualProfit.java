package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作者：cjy
 * 类名：AnnualProfit
 * 全路径类名：org.example.pojo.AnnualProfit
 * 父类或接口：
 * 描述：年度利润
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnnualProfit {
    private int year;//年份
    private float indexIncome;//指数收益
    private float trendIncome;//趋势收益
}
