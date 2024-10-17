package iss.se.team4.secondhand.common.dto;

import lombok.Data;

@Data
public class RegisterDto {

    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String avatar;
    private String role;

    private String securityQuestion;
    private String securityAnswer;
}
