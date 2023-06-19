package org.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import cn.hutool.core.util.NetUtil;

/**
 * 作者：cjy
 * 类名：IndexTurbineApplication
 * 全路径类名：org.example.IndexTurbineApplication
 * 父类或接口：
 * 描述：断路器聚合监控子模块
 *      把一个集群里的多个实例汇聚在一个 turbine里，方便监控集群里的多个实例
 */
@SpringBootApplication
@EnableTurbine
public class IndexTurbineApplication {
    public static void main(String[] args) {
        int port = 8080;
        int eurekaServerPort = 8761;

        if(NetUtil.isUsableLocalPort(eurekaServerPort)) {
            System.err.printf("检查到端口%d 未启用，判断 eureka 服务器没有启动，本服务无法使用，故退出%n", eurekaServerPort );
            System.exit(1);
        }
        if(!NetUtil.isUsableLocalPort(port)) {
            System.err.printf("端口%d被占用了，无法启动%n", port );
            System.exit(1);
        }
        new SpringApplicationBuilder(IndexTurbineApplication.class).properties("server.port=" + port).run(args);

    }

}
