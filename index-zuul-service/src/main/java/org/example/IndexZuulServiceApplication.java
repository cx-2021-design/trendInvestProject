package org.example;

import brave.sampler.Sampler;
import cn.hutool.core.util.NetUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * 作者：cjy
 * 类名：IndexZuulServiceApplication
 * 全路径类名：org.example.IndexZuulServiceApplication
 * 父类或接口：
 * 描述：网关
 */
@SpringBootApplication
@EnableZuulProxy//网关
@EnableEurekaClient
@EnableDiscoveryClient
public class IndexZuulServiceApplication {
    //  http://127.0.0.1:8031/api-codes/codes
    public static void main(String[] args) {
        int port = 8031;
        if(!NetUtil.isUsableLocalPort(port)) {
            System.err.printf("端口%d被占用了，无法启动%n", port );
            System.exit(1);
        }
        new SpringApplicationBuilder(IndexZuulServiceApplication.class).properties("server.port=" + port).run(args);

    }
    //zipkin一直取样
    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

}