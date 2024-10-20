package iss.se.team4.mailmicroservice.pojo;


import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class EmailContent {
    String emailAddress;
    String userName;
    String productInfo;
    String updatedItem;
    String itemInfo;
}
