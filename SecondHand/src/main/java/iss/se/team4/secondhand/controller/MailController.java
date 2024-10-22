package iss.se.team4.secondhand.controller;

import iss.se.team4.secondhand.common.Result;
import iss.se.team4.secondhand.common.dto.SendWantedEmailDto;
import iss.se.team4.secondhand.model.User;
import iss.se.team4.secondhand.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/sendWantedEmail")
    public Result sendWantedEmail(@RequestBody SendWantedEmailDto sendWantedEmailDto) {
        mailService.sendWantedEmail(sendWantedEmailDto.getBuyerEmail(), sendWantedEmailDto.getBuyerName(),
                sendWantedEmailDto.getSellerEmail(), sendWantedEmailDto.getSellerName(), sendWantedEmailDto.getProductName());
        return Result.success();
    }
}
