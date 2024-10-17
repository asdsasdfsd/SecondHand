package iss.se.team4.secondhand.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@Slf4j
public class FileUploadUtils {
    public String sendMultipartFile(String url, MultipartFile file) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // 创建文件资源
        ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };

        // 创建请求体
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", resource);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // 发送请求
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

        // 处理响应
        if (response.getStatusCode() == HttpStatus.OK) {
            log.info("file upload success: " + response.getBody());
            return response.getBody();
        } else {
            log.error("file upload fail: " + response.getStatusCode());
            return null;
        }
    }
}
