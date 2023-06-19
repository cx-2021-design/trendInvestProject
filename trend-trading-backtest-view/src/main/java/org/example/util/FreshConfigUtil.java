package org.example.util;

import java.util.HashMap;

import cn.hutool.http.HttpUtil;

/**
 * 作者：cjy
 * 类名：FreshConfigUtil
 * 全路径类名：org.example.util.FreshConfigUtil
 * 父类或接口：
 * 描述：为了可以使用 post 访问
 */
public class FreshConfigUtil {

    public static void main(String[] args) {
        HashMap<String,String> headers =new HashMap<>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        System.out.println("因为要去git获取，还要刷新index-config-server, 会比较卡，所以一般会要好几秒才能完成，请耐心等待");

        String result = HttpUtil.createPost("http://localhost:8041/actuator/bus-refresh").addHeaders(headers).execute().body();
        System.out.println("result:"+result);
        System.out.println("refresh 完成");
    }
}