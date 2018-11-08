package com.madisoon.cloud.config.websocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
     * 给所有在线用户发送消息
     *
     * @param message 信息内容
     */
    public void broadcast(TextMessage message) {
        Iterator<Entry<Long, WebSocketSession>> it = USER_SOCKET_SESSION_MAP.entrySet().iterator();
        // 多线程群发（给所有在线的用户发送消息）  先判断是否里面有用户（）然后循环发消息
        /*后台调用sendMessage方法的时候，前台会触发onmessage*/
        while (it.hasNext()) {
            final Entry<Long, WebSocketSession> entry = it.next();
            if (entry.getValue().isOpen()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (entry.getValue().isOpen()) {
                                entry.getValue().sendMessage(message);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }
    }

    /**
     * 单个用户发消息
     *
     * @param uid     用户id
     * @param message 信息内容
     */
    public void sendMessageToUser(Long uid, TextMessage message) {
        //根据传过来的账号，在websocketseesion的服务器里面找，接收者注册的账号
        WebSocketSession session = USER_SOCKET_SESSION_MAP.get(uid);
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void removeWebSocketUser(WebSocketSession session) {
        Iterator<Entry<Long, WebSocketSession>> it = USER_SOCKET_SESSION_MAP.entrySet().iterator();
        // 移除Socket会话
        while (it.hasNext()) {
            Entry<Long, WebSocketSession> entry = it.next();
            if (entry.getValue().getId().equals(session.getId())) {
                USER_SOCKET_SESSION_MAP.remove(entry.getKey());
                break;
            }
        }
    }
}