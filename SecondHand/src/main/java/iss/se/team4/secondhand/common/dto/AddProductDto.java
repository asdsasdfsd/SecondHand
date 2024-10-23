package iss.se.team4.secondhand.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddProductDto {

    String name;
    String description;
    Double price;
    Integer amount;
    @NotBlank(message = "owner can't be blank")
    String owner;
    String releaseDate;
    String imageUrl;
}
