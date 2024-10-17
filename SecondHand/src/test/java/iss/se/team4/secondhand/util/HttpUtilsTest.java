package iss.se.team4.secondhand.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

@SpringBootTest
class HttpUtilsTest {

    @Autowired
    private HttpUtils httpUtils;

    @Test
    void sendGet() throws IOException, InterruptedException {
        HttpUtils mockedHttpUtils = Mockito.mock(HttpUtils.class);
        String url = "https://www.google.com";
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");

        when(mockedHttpUtils.sendGet(url, headers)).thenReturn("Mocked Response");

        String response = mockedHttpUtils.sendGet(url, headers);
        System.out.println(response);
        Assertions.assertEquals("Mocked Response", response);
    }

    @Test
    void sendPost() throws IOException, InterruptedException {
        HttpUtils mockedHttpUtils = Mockito.mock(HttpUtils.class);
        String url = "https://www.google.com";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        String jsonBody = "{\"key\":\"value\"}";

        when(mockedHttpUtils.sendPost(url, jsonBody, headers)).thenReturn("Mocked Response");

        String response = mockedHttpUtils.sendPost(url, jsonBody, headers);
        System.out.println(response);
        Assertions.assertEquals("Mocked Response", response);
    }
}
