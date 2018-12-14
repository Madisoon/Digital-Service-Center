package com.madisoon.starter.email;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;

/**
 * Description:
 * 复杂邮件发送类
 *
 * @author Msater Zg
 * @date 2018/11/6 11:10 AM
 */

public class JavaMailWithAttachment {

    private String mailAccount;

    private String mailPassword;

    private String mailSmtpHost;

    private String mailFrom;

    private String mailTo;

    private final static String MAIL_CHARSET = "UTF-8";

    private final static String MAIL_CHARSET_TYPE = "text/html;charset=UTF-8";

    private static MimeMessage message;

    private static Session session;

    private static Transport transport;

    public JavaMailWithAttachment(JavaMailProperties javaMailProperties) {
        this.mailAccount = javaMailProperties.getAccount();
        this.mailPassword = javaMailProperties.getPassword();
        this.mailSmtpHost = javaMailProperties.getHost();
        this.mailFrom = javaMailProperties.getFrom();
        this.mailTo = javaMailProperties.getTo();
        initMail();
    }
    
    /**
     * 初始化session和transport对象
     */
    private void initMail() {
        // 使用的协议（JavaMail规范要求）
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");

        // 发件人邮箱 SMTP 服务器地址
        props.setProperty("mail.smtp.host", mailSmtpHost);

        // 需要请求认证
        props.setProperty("mail.smtp.auth", "true");

        session = Session.getInstance(props);
        try {
            transport = session.getTransport("smtp");
            transport.connect(mailAccount, mailPassword);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        session.setDebug(true);
    }

    /**
     * 收件人邮箱（替换为自己知道的有效邮箱）
     */
    public void postEmail(String receiveMailAccount, String title, String content) throws Exception {
        message = createMimeMessage(receiveMailAccount, title, content);
        initTransport();
    }

    /**
     * 收件人邮箱（替换为自己知道的有效邮箱）
     */
    public void postEmail(String receiveMailAccount, String title, String content, String url) throws Exception {
        message = createMimeMessage(receiveMailAccount, title, content, url);
        initTransport();
    }

    /**
     * 初始化message对象
     *
     * @param receiveMail 接受者邮箱
     * @param subject     邮箱主题
     */
    private void initMessage(String receiveMail, String subject) throws Exception {
        message = new MimeMessage(session);

        // From: 发件人
        message.setFrom(new InternetAddress(mailAccount, mailFrom, MAIL_CHARSET));

        // To: 收件人（可以增加多个收件人、抄送、密送）
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiveMail, mailTo, MAIL_CHARSET));

        // Subject: 邮件主题
        message.setSubject(subject, MAIL_CHARSET);
    }

    private void initTransport() throws Exception {
        // 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());

        // 关闭连接
        transport.close();
    }

    /**
     * 创建一封简单邮箱（文本）
     */
    private MimeMessage createMimeMessage(String receiveMail, String subject, String content) throws Exception {
        initMessage(receiveMail, subject);
        message.setContent(content, MAIL_CHARSET_TYPE);
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }

    /**
     * 创建一封复杂邮件（文本+图片+附件）
     */
    private MimeMessage createMimeMessage(String receiveMail, String subject, String content, String url) throws Exception {
        initMessage(receiveMail, subject);
        // 创建文本“节点”
        MimeBodyPart text = new MimeBodyPart();
        // 这里添加图片的方式是将整个图片包含到邮件内容中, 实际上也可以以 http 链接的形式添加网络图片
        text.setContent(content, MAIL_CHARSET_TYPE);

        // 文本+图片）设置 文本 和 图片 “节点”的关系（将 文本 和 图片 “节点”合成一个混合“节点”）
        MimeMultipart mimeTextImage = new MimeMultipart();
        mimeTextImage.addBodyPart(text);
        mimeTextImage.setSubType("related");

        // 将文本+图片 的混合“节点”封装成一个普通“节点”
        // 最终添加到邮件的 Content 是由多个 BodyPart 组成的 Multipart, 所以我们需要的是 BodyPart,
        // 上面的 mm_text_image 并非 BodyPart, 所有要把 mm_text_image 封装成一个 BodyPart
        MimeBodyPart textImage = new MimeBodyPart();
        textImage.setContent(mimeTextImage);

        // 创建附件“节点”
        MimeBodyPart attachment = new MimeBodyPart();
        DataHandler dh2 = new DataHandler(new FileDataSource(url));
        attachment.setDataHandler(dh2);
        attachment.setFileName(MimeUtility.encodeText(dh2.getName()));

        // 设置（文本+图片）和 附件 的关系（合成一个大的混合“节点” / Multipart ）
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(textImage);
        mm.addBodyPart(attachment);
        mm.setSubType("mixed");
        message.setContent(mm);
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }
}