package iss.se.team4.secondhand.service;

public interface MailService {

    // sending a welcome email when user register new account
    public void sendWelcomeEmail(String emailAddr, String username);

    // send a notice email to seller when a buyer "wanted" his/her product
    public void sendWantedEmail(String emailAddr, String username);

    // send a customize email
    public void sendCustomizeEmail(String emailAddr, String username, String subject, String body);
}