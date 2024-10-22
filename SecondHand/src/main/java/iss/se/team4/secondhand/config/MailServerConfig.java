package iss.se.team4.secondhand.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mail")
@Getter
@Setter
@ToString
public class MailServerConfig {
    private String url;
}
