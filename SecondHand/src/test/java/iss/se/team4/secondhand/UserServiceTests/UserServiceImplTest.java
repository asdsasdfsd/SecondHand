package iss.se.team4.secondhand.UserServiceTests;

import iss.se.team4.secondhand.common.Result;
import iss.se.team4.secondhand.model.User;
import iss.se.team4.secondhand.repository.UserRepository;
import iss.se.team4.secondhand.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void register() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email@email.com");
        user.setPhone("123456");
        user.setAvatar("avatar");
        user.setRole("1");
        // first register
        userService.register(user);
        User testUser = userRepository.findByUsername(user.getUsername());
        assertNotNull(testUser);

        // second register, fail
        Result result = userService.register(user);
        assertFalse(result.isSuccess());

        userService.deleteById(testUser.getId());

    }
}