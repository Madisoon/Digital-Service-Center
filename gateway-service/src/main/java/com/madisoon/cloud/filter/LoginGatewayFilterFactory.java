package com.madisoon.cloud.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;


/**
 * Description:
 * 登陆过滤器，并处理登陆相关的一系列问题
 *
 * @author Msater Zg
 * @create 2018/12/13 4:28 PM
 */

@Component
public class LoginGatewayFilterFactory extends AbstractGatewayFilterFactory<LoginGatewayFilterFactory.Config> {

    public LoginGatewayFilterFactory() {
        super(LoginGatewayFilterFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("enabled");
    }

    @Override
    public GatewayFilter apply(LoginGatewayFilterFactory.Config config) {
        return (exchange, chain) -> {
            // 这边是pre过滤器
            exchange.getAttributes().put("asdas", System.currentTimeMillis());
            // 这里面是post过滤器
            return chain.filter(exchange).then();
        };
    }

    /**
     * 重写过滤器名称，默认:LoginGatewayFilterFactory，过滤器名称为Login
     *
     * @return 过滤器名称
     */
    @Override
    public String name() {
        return "Login";
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
