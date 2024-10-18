package iss.se.team4.secondhand.UserServiceTests;
import iss.se.team4.secondhand.common.dto.RegisterDto;
import iss.se.team4.secondhand.model.SecurityQuestion;
import iss.se.team4.secondhand.model.User;
import iss.se.team4.secondhand.repository.SecurityQuestionRepository;
import iss.se.team4.secondhand.repository.UserRepository;
import iss.se.team4.secondhand.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:schema.sql")
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityQuestionRepository securityQuestionRepository;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void register() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setUsername("username");
        registerDto.setPassword("password");
        registerDto.setEmail("email@email.com");
        registerDto.setPhone("123456");
        registerDto.setAvatar("avatar");
        registerDto.setRole("1");
        registerDto.setSecurityQuestion("What is your favorite color?");
        registerDto.setSecurityAnswer("Blue");

        var registerResult = userService.register(registerDto);
        assertTrue(registerResult.isSuccess());

        User testUser = userRepository.findByUsername(registerDto.getUsername());
        assertNotNull(testUser);
        assertTrue(encoder.matches("password", testUser.getPassword()));

        SecurityQuestion securityQuestion = securityQuestionRepository.findByUsername(registerDto.getUsername());
        assertNotNull(securityQuestion);
        assertEquals("What is your favorite color?", securityQuestion.getQuestion());
        assertEquals("Blue", securityQuestion.getAnswer());

        var secondRegisterResult = userService.register(registerDto);
        assertFalse(secondRegisterResult.isSuccess());

        userService.deleteById(testUser.getId());

        securityQuestionRepository.deleteById(securityQuestion.getId());
    }


}
