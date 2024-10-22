package iss.se.team4.secondhand.service.impl;

import cn.hutool.json.JSONUtil;
import iss.se.team4.secondhand.config.MailServerConfig;
import iss.se.team4.secondhand.service.MailService;
import iss.se.team4.secondhand.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private HttpUtils httpUtils;

    // TODO 改成远程服务器地址或配置项
    @Autowired
    private MailServerConfig mailServerConfig;

    private static final String WELCOME_EMAIL_PATH = "/api/mail/welcome";

    private static final String WANTED_EMAIL_PATH = "/api/mail/wanted";

    private static final String CUSTOMIZED_EMAIL_PATH = "/api/mail/customized";

    @Override
    public void sendWelcomeEmail(String emailAddr, String username) {
        String url = mailServerConfig.getUrl() + WELCOME_EMAIL_PATH;
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        Map<String, String> welcomeEmailRequest = new HashMap<>();
        welcomeEmailRequest.put("emailAddress", emailAddr);
        welcomeEmailRequest.put("userName", username);

        try {
            httpUtils.sendPost(url, JSONUtil.toJsonStr(welcomeEmailRequest), headers);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendWantedEmail(String buyerEmail, String buyerName, String sellerEmail, String sellerName, String productInfo) {
        String url = mailServerConfig.getUrl() + WANTED_EMAIL_PATH;
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        Map<String, String> wantedEmailRequest = new HashMap<>();
        wantedEmailRequest.put("buyerEmail", buyerEmail);
        wantedEmailRequest.put("buyerName", buyerName);
        wantedEmailRequest.put("sellerEmail", sellerEmail);
        wantedEmailRequest.put("sellerName", sellerName);
        wantedEmailRequest.put("productInfo", productInfo);

        try {
            httpUtils.sendPost(url, JSONUtil.toJsonStr(wantedEmailRequest), headers);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void sendCustomizeEmail(String emailAddr, String username, String subject, String body) {

    }
}
