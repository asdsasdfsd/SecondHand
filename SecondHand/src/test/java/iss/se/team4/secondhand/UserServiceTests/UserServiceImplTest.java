package iss.se.team4.secondhand.UserServiceTests;

import iss.se.team4.secondhand.common.Result;
import iss.se.team4.secondhand.common.dto.RegisterDto;
import iss.se.team4.secondhand.model.SecurityQuestion;
import iss.se.team4.secondhand.model.User;
import iss.se.team4.secondhand.repository.SecurityQuestionRepository;
import iss.se.team4.secondhand.repository.UserRepository;
import iss.se.team4.secondhand.service.UserService;
import iss.se.team4.secondhand.util.JwtUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityQuestionRepository securityQuestionRepository;

    @Autowired
    JwtUtils jwtUtils;

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
        registerDto.setSecurityQuestion("123");
        registerDto.setSecurityAnswer("1234");
        // first register
        userService.register(registerDto);
        User testUser = userRepository.findByUsername(registerDto.getUsername());
        assertNotNull(testUser);
        assertTrue(encoder.matches("password", testUser.getPassword()));
        assertEquals("university town", testUser.getAddress1());
        assertEquals("", testUser.getAddress2());
        SecurityQuestion securityQuestion = securityQuestionRepository.findByUsername(registerDto.getUsername());
        assertNotNull(securityQuestion);
        assertEquals("123", securityQuestion.getQuestion());
        assertEquals("1234", securityQuestion.getAnswer());

        // second register, fail
        Result result = userService.register(registerDto);
        assertFalse(result.isSuccess());

        userService.deleteById(testUser.getId());
        securityQuestionRepository.deleteById(securityQuestion.getId());

    }

    @Test
    void login() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setUsername("Login_username");
        registerDto.setPassword("Login_password");
        registerDto.setEmail("email@email.com");
        registerDto.setPhone("123456");
        registerDto.setAvatar("avatar");
        registerDto.setRole("1");
        registerDto.setSecurityQuestion("123");
        registerDto.setSecurityAnswer("1234");
        userService.register(registerDto);
        User testUser = userRepository.findByUsername(registerDto.getUsername());
        assertNotNull(testUser);

        Result loginResult = userService.login("Login_username", "Login_password");
        assertTrue(loginResult.isSuccess());

        Result failLoginResult = userService.login("Login_username", "wrong_password");
        assertFalse(failLoginResult.isSuccess());

        HashMap data = (HashMap) loginResult.getData();

        String token = data.get("token").toString();
        assertEquals(jwtUtils.getRoleByToken(token), registerDto.getRole());

        userService.deleteById(testUser.getId());
        SecurityQuestion securityQuestion = securityQuestionRepository.findByUsername(registerDto.getUsername());
        securityQuestionRepository.deleteById(securityQuestion.getId());

    }
}