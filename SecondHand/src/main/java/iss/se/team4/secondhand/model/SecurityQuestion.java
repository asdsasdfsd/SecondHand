package iss.se.team4.secondhand.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_security_questions")
public class SecurityQuestion {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String question;

    @Getter
    @Setter
    private String answer;
}
