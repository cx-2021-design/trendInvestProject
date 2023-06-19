package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作者：cjy
 * 类名：IndexData
 * 全路径类名：org.example.pojo.IndexData
 * 父类或接口：
 * 描述：指数数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IndexData {
    String date;
    float closePoint;
}