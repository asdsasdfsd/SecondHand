package iss.se.team4.secondhand.service.impl;

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
    private static final String EMAIL_SERVER_URL = "http://localhost:1111";

    private static final String WELCOME_EMAIL_PATH = "/api/upload/welcome";

    private static final String WANTED_EMAIL_PATH = "/wanted";

    private static final String CUSTOMIZED_EMAIL_PATH = "/customized";

    @Override
    public void sendWelcomeEmail(String emailAddr, String username) {
        String url = EMAIL_SERVER_URL + WELCOME_EMAIL_PATH;
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        EmailRequest emailRequest = new EmailRequest(emailAddr, "Welcome!", "Welcome:" + username);

        String requestBody = toJson(emailRequest);


        try {
            httpUtils.sendPost(url, requestBody, headers);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendWantedEmail(String emailAddr, String username) {

    }

    @Override
    public void sendCustomizeEmail(String emailAddr, String username, String subject, String body) {

    }

    private String toJson(EmailRequest emailRequest) {
        // 这里可以使用Gson、Jackson等JSON库来进行序列化
        return "{ \"to\": \"" + emailRequest.getTo() + "\", " +
                "\"subject\": \"" + emailRequest.getSubject() + "\", " +
                "\"body\": \"" + emailRequest.getBody() + "\" }";
    }

    public static class EmailRequest {
        private String to;
        private String subject;
        private String body;

        public EmailRequest(String to, String subject, String body) {
            this.to = to;
            this.subject = subject;
            this.body = body;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }
}
