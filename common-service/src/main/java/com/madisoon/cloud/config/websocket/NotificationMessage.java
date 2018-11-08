package com.madisoon.cloud.config.websocket;

import java.util.Date;

/**
 * 消息类
 *
 * @author zg
 * @date 2015年6月11日 下午1:15:09
 */
public class NotificationMessage {
    /**
     * 发送者账号
     */
    private Long from;
    /**
     * 发送者名称
     */
    private String fromName;
    /**
     * 接收者账号
     */
    private Long to;
    /**
     * 发送的内容
     */
    private String text;
    /**
     * 发送的日期
     */
    private Date date;

    public NotificationMessage(Long from, String fromName, Long to, String text, Date date) {
        this.from = from;
        this.fromName = fromName;
        this.to = to;
        this.text = text;
        this.date = date;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
