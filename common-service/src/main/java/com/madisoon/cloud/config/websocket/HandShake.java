package com.madisoon.cloud.config.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * Socket建立连接（握手）和断开
 *
 * @author zg
 * @date 2018年9月11日 下午2:23:09
 */
public class HandShake implements HandshakeInterceptor {
    /**
     * 握手协议
     *
     * @param request    请求
     * @param response   响应
     * @param wsHandler  握手协议
     * @param attributes map对象
     * @return 握手状态
     */
    @Override
    public boolean beforeHandshake(@NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response, @NonNull WebSocketHandler wsHandler,
                                   @NonNull Map<String, Object> attributes) {
        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
        long uid = Long.parseLong(servletRequest.getServletRequest().getParameter("uid"));
        if (uid != 0) {
            attributes.put("uid", uid);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public void afterHandshake(@NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response, @NonNull WebSocketHandler wsHandler,
                               Exception exception) {
    }
}