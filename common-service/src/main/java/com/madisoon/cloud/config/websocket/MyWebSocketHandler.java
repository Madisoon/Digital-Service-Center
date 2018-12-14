package com.madisoon.cloud.config.websocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.madisoon.cloud.utils.ThreadPoolUtil;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

/**
 * Socket处理器(包括发送信息，接收信息，信息错误等方法。)
 *
 * @author caizi
 */
@Component
public class MyWebSocketHandler implements WebSocketHandler {

    /**
     * 先注册一个websocket服务器，将连接上的所有用户放进去
     */
    private static final Map<Long, WebSocketSession> USER_SOCKET_SESSION_MAP;

    static {
        USER_SOCKET_SESSION_MAP = new HashMap<>();
    }

    /**
     * 前台连接并且注册了账户
     */
    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) {
        Long uid = (Long) session.getAttributes().get("uid");
        USER_SOCKET_SESSION_MAP.putIfAbsent(uid, session);
    }

    /**
     * @param session 通信服务器
     * @param message Websocket文本
     */
    @Override
    public void handleMessage(@NonNull WebSocketSession session, @NonNull WebSocketMessage<?> message) {
        if (message.getPayloadLength() == 0) {
            return;
        }
        NotificationMessage msg = new Gson().fromJson(message.getPayload().toString(), NotificationMessage.class);
        msg.setDate(new Date());
        sendMessageToUser(msg.getTo(), new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
    }

    /**
     * 消息传输错误处理，如果出现错误直接断开连接
     */
    @Override
    public void handleTransportError(@NonNull WebSocketSession session, @NonNull Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        removeWebSocketUser(session);
    }

    /**
     * 关闭连接后
     */
    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus closeStatus) {
        removeWebSocketUser(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给所有在线用户发送消息,后台调用sendMessage方法的时候，前台会触发onmessage
     *
     * @param message 信息内容
     */
    public void broadcast(TextMessage message) {
        Set<Entry<Long, WebSocketSession>> set = USER_SOCKET_SESSION_MAP.entrySet();
        set.forEach(item -> {
            if (item.getValue().isOpen()) {
                ThreadPoolUtil.executeTaskFuture(() -> {
                    try {
                        if (item.getValue().isOpen()) {
                            item.getValue().sendMessage(message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }

    /**
     * 单个用户发消息
     *
     * @param uid     用户id
     * @param message 信息内容
     */
    public void sendMessageToUser(Long uid, TextMessage message) {
        WebSocketSession session = USER_SOCKET_SESSION_MAP.get(uid);
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 移除某个会话
     *
     * @param session 会话
     */
    private void removeWebSocketUser(WebSocketSession session) {
        Set<Entry<Long, WebSocketSession>> set = USER_SOCKET_SESSION_MAP.entrySet();
        set.forEach(item -> {
            if (item.getValue().getId().equals(session.getId())) {
                USER_SOCKET_SESSION_MAP.remove(item.getKey());
            }
        });
    }
}