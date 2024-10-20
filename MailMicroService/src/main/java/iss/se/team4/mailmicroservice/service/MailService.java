package iss.se.team4.mailmicroservice.service;

import iss.se.team4.mailmicroservice.exception.SendMailException;

public interface MailService {
    // sending a welcome email when user register new account
    public void sendWelcomeEmail(String emailAddr, String username) throws SendMailException;

    // send a notice email to seller when a buyer "wanted" his/her product
    public void sendWantedEmail(String emailAddr, String username, String productInfo) throws SendMailException;

    // send a customize email
    public void sendCustomizedEmail(String emailAddr, String username, String updatedItem, String itemInfo) throws SendMailException;

}
