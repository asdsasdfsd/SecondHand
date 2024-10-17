package iss.se.team4.secondhand.service.impl;

import iss.se.team4.secondhand.service.MailService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailServiceImplTest {

    @Autowired
    private MailService mailService;

    @Test
    void sendWelcomeEmail() {
        mailService.sendWelcomeEmail("2528827249@qq.com", "Parzival");
    }

    @Test
    void sendWantedEmail() {
    }

    @Test
    void sendCustomizeEmail() {
    }
}