package iss.se.team4.secondhand.service;

public interface MailService {

    // sending a welcome email when user register new account
    void sendWelcomeEmail(String emailAddr, String username);

    // send a notice email to seller when a buyer "wanted" his/her product
    void sendWantedEmail(String buyerEmail, String buyerName, String sellerEmail, String sellerName, String productInfo);

    // send a customize email
    void sendCustomizeEmail(String emailAddr, String username, String subject, String body);
}
