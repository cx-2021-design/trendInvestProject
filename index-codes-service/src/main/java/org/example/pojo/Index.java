package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 作者：cjy
 * 类名：Index
 * 全路径类名：org.example.pojo.Index
 * 父类或接口：@see Serializable
 * 描述：指数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Index implements Serializable {
    String code;
    String name;
}
