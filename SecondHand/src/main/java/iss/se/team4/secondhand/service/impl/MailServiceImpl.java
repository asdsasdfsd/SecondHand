package iss.se.team4.secondhand.service.impl;

import iss.se.team4.secondhand.service.MailService;
import iss.se.team4.secondhand.util.HttpUtils;
import iss.se.team4.secondhand.util.MailUtils;
import iss.se.team4.secondhand.exception.SendMailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import jetbrick.template.JetEngine;
import jetbrick.template.JetTemplate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static iss.se.team4.secondhand.util.MailUtils.SMTP_QQ;

@Service
public class MailServiceImpl implements MailService {

    static {
        initialize();
    }

    private static void initialize(){
        MailUtils.config(SMTP_QQ(false), "1602531630@qq.com", "psfqluaincyxbahd");
    }

    @Override
    public void sendWelcomeEmail(String emailAddr, String username) throws SendMailException {
        MailUtils.subject("Welcome to SecondHand")
                .from("SecondHand Team")
                .to(emailAddr)
                .text("Hi " + username + "! You have successfully created your account in SecondHand. \n" +
                        "Discover a world of opportunities where you can buy and sell pre-loved items. Whether you’re looking to find great deals or declutter your home, you’re in the right place! Join our community of savvy shoppers and sellers, and turn your unused items into cash. Happy trading!")
                .send();
        Assert.assertTrue(true);
    }

    @Override
    public void sendWantedEmail(String emailAddr, String username) {

    }

    @Override
    public void sendCustomizeEmail(String emailAddr, String username, String subject, String body) {

    }
}