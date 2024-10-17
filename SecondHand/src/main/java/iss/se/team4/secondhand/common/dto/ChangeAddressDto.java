package iss.se.team4.secondhand.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class ChangeAddressDto implements Serializable {

    @NotBlank(message = "username can not be blank")
    String username;

    @NotBlank(message = "address1 can not be blank")
    String address1;

    String address2;
}
