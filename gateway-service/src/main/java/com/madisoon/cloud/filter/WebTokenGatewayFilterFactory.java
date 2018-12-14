package com.madisoon.cloud.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Description:
 * 请求token过滤器
 *
 * @author Msater Zg
 * @create 2018/12/13 4:29 PM
 */
@Component
public class WebTokenGatewayFilterFactory extends AbstractGatewayFilterFactory<WebTokenGatewayFilterFactory.Config> {

    public WebTokenGatewayFilterFactory() {
        super(WebTokenGatewayFilterFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("enabled");
    }

    @Override
    public GatewayFilter apply(WebTokenGatewayFilterFactory.Config config) {

        System.out.println("执行web");
        return (exchange, chain) -> chain.filter(exchange);
    }

    public static class Config {
        private boolean enabled;

        public Config() {
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}
