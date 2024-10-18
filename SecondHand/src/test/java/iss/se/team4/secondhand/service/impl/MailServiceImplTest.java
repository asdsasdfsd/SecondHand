package iss.se.team4.secondhand.service.impl;

import iss.se.team4.secondhand.exception.SendMailException;
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
    void sendWelcomeEmail() throws SendMailException {
        mailService.sendWelcomeEmail("jianghc_nus@163.com", "Felix");
    }

    @Test
    void sendWantedEmail() throws SendMailException {
        mailService.sendWantedEmail("jianghc_nus@163.com", "Felix", "iPhone 14");
    }

    @Test
    void sendCustomizeEmail() throws SendMailException {
        mailService.sendCustomizeEmail("jianghc_nus@163.com", "Felix", "phone number", "86806932");
    }
}