package iss.se.team4.secondhand;

import org.apache.catalina.core.ApplicationContext;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@MapperScan("iss.se.team4.secondhand.mapper") // MyBatis Mapper 包扫描
@EnableJpaRepositories(basePackages = "iss.se.team4.secondhand.repository") // JPA Repository 包扫描
public class SecondHandApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondHandApplication.class, args);
    }

}
