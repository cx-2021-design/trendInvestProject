package org.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 作者：cjy
 * 类名：ThirdPartIndexDataApplication
 * 全路径类名：org.example.ThirdPartIndexDataApplication
 * 父类或接口：
 * 描述：第三方数据服务，原始数据
 */
@SpringBootApplication
@EnableEurekaClient//注册为微服务
public class ThirdPartIndexDataApplication {
    public static void main(String[] args) {
        int port = 8090;//第三方数据服务默认端口
        int eurekaServerPort = 8761;

        if(NetUtil.isUsableLocalPort(eurekaServerPort)) {
            System.err.printf("检查到端口%d 未启用，判断 eureka 服务器没有启动，本服务无法使用，故退出%n", eurekaServerPort );
            System.exit(1);
        }

        //不使用默认端口也可以自己传如端口
        if(null!=args && 0!=args.length) {//如果带了参数,如：port=8099
            for (String arg : args) {
                if(arg.startsWith("port=")) {
                    String strPort= StrUtil.subAfter(arg, "port=", true);
                    if(NumberUtil.isNumber(strPort)) {
                        port = Convert.toInt(strPort);//就会使用 8099 作为端口号
                    }
                }
            }
        }
        //判断第三方数据服务是否被占用
        if(!NetUtil.isUsableLocalPort(port)) {
            System.err.printf("端口%d被占用了，无法启动%n", port );
            System.exit(1);
        }
        //启动第三方数据服务
        new SpringApplicationBuilder(ThirdPartIndexDataApplication.class)
                .properties("server.port=" + port)
                .run(args);

    }

}
