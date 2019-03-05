package com.madisoon.cloud.config;

import com.madisoon.cloud.config.aes.AesException;
import com.madisoon.cloud.config.aes.SHA1;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description:
 * 微信链接验证服务
 *
 * @author Msater Zg
 * @date 2018/12/18 10:42 PM
 */
@RestController
@RequestMapping("weChat")
public class WeChatCheckController {

    /**
     * 验证消息来自微信服务器
     *
     * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @param echostr   随机字符串
     */
    @GetMapping("check")
    public void connectWeChatServer(@RequestParam("signature") String signature,
                                    @RequestParam("timestamp") String timestamp,
                                    @RequestParam("nonce") String nonce,
                                    @RequestParam("echostr") String echostr, HttpServletResponse response) throws IOException {
        String token = "qazwsxedc";
        // 确认请求来至微信
        String digest = null;
        try {
            digest = SHA1.getSHA1(token, timestamp, nonce);
        } catch (AesException e) {
            e.printStackTrace();
        }
        if (digest.equals(signature)) {
            response.getWriter().print(echostr);
        }
    }
}
