package iss.se.team4.secondhand.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class CheckSecurityQuestionDto implements Serializable {

    @NotBlank(message = "username can not be blank")
    private String username;

    @NotBlank(message = "question can not be blank")
    private String question;

    @NotBlank(message = "answer can not be blank")
    private String answer;
}
