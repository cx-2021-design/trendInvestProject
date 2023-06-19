package org.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import cn.hutool.core.util.NetUtil;

/**
 * 作者：cjy
 * 类名：EurekaServerApplication
 * 全路径类名：org.example.EurekaServerApplication
 * 父类或接口：
 * 描述：注册中心
 */
@SpringBootApplication
@EnableEurekaServer//注册中心服务器
public class EurekaServerApplication {

    public static void main(String[] args) {
        int port = 8761;//注册中心端口

        //判断端口是否启动
        if(!NetUtil.isUsableLocalPort(port)) {
            System.err.printf("端口%d被占用了，无法启动%n", port );
            System.exit(1);
        }
        //启动
        new SpringApplicationBuilder(EurekaServerApplication.class)
                .properties("server.port=" + port)
                .run(args);
    }
}