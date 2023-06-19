package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作者：cjy
 * 类名：Profit
 * 全路径类名：org.example.pojo.Profit
 * 父类或接口：
 * 描述：Profit 利润类，有日期和数值两个属性
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Profit {

    String date;
    float value;
}
