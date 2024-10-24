package iss.se.team4.secondhand.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "resource")
@Getter
@Setter
@ToString
public class ResourceServerConfig {
    private String url;
}
