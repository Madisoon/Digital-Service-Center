package com.madisoon.starter.message;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * 简单邮件发送类
 *
 * @author Msater Zg
 * @date 2018/11/6 13:10 AM
 */
public class NumberInfoPost {

    private final static String URI_SEND_SMS = "http://sms.yunpian.com/v2/sms/batch_send.json";

    private final static String API_KEY = "149b85f2319da3c78e25b49d748c6e2d";

    private final static String CONTENT_TEMPLATE = "【舆情平台】尊敬的用户，您设置的关注有新信息到达，请您点击查看:";


    public String sendMsgByYunPian(String content, String mobiles) {
        String text = CONTENT_TEMPLATE + content;
        Map<String, String> params = new HashMap<>(16);
        params.put("apikey", API_KEY);
        params.put("text", text);
        params.put("mobile", mobiles);
        return postMsg(params);
    }

    private String postMsg(Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(URI_SEND_SMS);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, API_KEY));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseText;
    }
}
