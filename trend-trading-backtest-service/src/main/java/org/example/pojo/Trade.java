package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作者：cjy
 * 类名：Trade
 * 全路径类名：org.example.pojo.Trade
 * 父类或接口：
 * 描述：交易类用于记录交易的购买日期，出售日期，购买盘点，出售盘点，收益。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trade {

    private String buyDate;
    private String sellDate;
    private float buyClosePoint;
    private float sellClosePoint;
    private float rate;
}