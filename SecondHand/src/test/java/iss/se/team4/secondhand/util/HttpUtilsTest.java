package iss.se.team4.secondhand.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HttpUtilsTest {

    @Autowired
    private HttpUtils httpUtils;

    @Test
    void sendGet() {
        String url = "https://www.google.com";
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        try {
            String response = httpUtils.sendGet(url, headers);
            System.out.println(response);
        } catch (IOException | InterruptedException e) {
            Assertions.fail();
        }

    }

    @Test
    void sendPost() {
        String url = "https://www.google.com";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        String jsonBody = "1231";

        try {
            String response = httpUtils.sendPost(url, jsonBody, headers);
            System.out.println(response);
        } catch (IOException | InterruptedException e) {
            Assertions.fail();
        }
    }
}