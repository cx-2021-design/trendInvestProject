package org.example.config;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 作者：cjy
 * 类名：IpConfiguration
 * 全路径类名：org.example.config.IpConfiguration
 * 父类或接口：@see ApplicationListener
 * 描述：用于获取当前的端口号（因为这个微服务会做成集群，不同的微服务使用的是不同的端口号）
 */
@Component
public class IpConfiguration implements ApplicationListener<WebServerInitializedEvent> {

    private int serverPort;

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();
    }

    public int getPort() {
        return this.serverPort;
    }
}