package iss.se.team4.secondhand.common.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
@Getter
@Setter
public class SendWantedEmailDto {


    private String buyerEmail;
    private String buyerName;

    @NotBlank(message = "seller Email can't be blank")
    private String sellerEmail;
    private String sellerName;
    private String productName;
}
