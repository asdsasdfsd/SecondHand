package iss.se.team4.secondhand.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class ChangePasswordDto implements Serializable {
    @NotBlank(message = "username can not be blank")
    private String username;

    @NotBlank(message = "password can not be blank")
    private String password;
}
