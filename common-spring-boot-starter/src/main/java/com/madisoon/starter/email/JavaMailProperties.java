package com.madisoon.starter.email;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description:
 * 发送邮件的配置类
 *
 * @author Msater Zg
 * @date 2018/11/13 10:31 AM
 */
@ConfigurationProperties(prefix = "mail")
public class JavaMailProperties {

    /**
     * 邮箱账号
     */
    private String account;

    /**
     * 邮箱密码
     */
    private String password;

    /**
     * STMPHOST
     */
    private String host;

    /**
     * 发送人备注
     */
    private String from;

    /**
     * 接收人备注
     */
    private String to;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
