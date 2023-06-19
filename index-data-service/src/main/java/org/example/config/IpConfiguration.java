package org.example.config;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 作者：cjy
 * 类名：IpConfiguration
 * 全路径类名：org.example.config.IpConfiguration
 * 父类或接口：@see ApplicationListener
 * 描述：获得端口号，因为要做集群
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
