package iss.se.team4.secondhand.service.impl;

import cn.hutool.core.map.MapUtil;
import iss.se.team4.secondhand.common.Result;
import iss.se.team4.secondhand.common.dto.RegisterDto;
import iss.se.team4.secondhand.model.SecurityQuestion;
import iss.se.team4.secondhand.model.User;
import iss.se.team4.secondhand.repository.SecurityQuestionRepository;
import iss.se.team4.secondhand.repository.UserRepository;
import iss.se.team4.secondhand.service.MailService;
import iss.se.team4.secondhand.service.UserService;

import iss.se.team4.secondhand.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    //dependency inject
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityQuestionRepository  securityQuestionRepository;

    @Autowired
    private MailService mailService;

    private static final String DEFAULT_ADDRESS = "university town";


    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    JwtUtils jwtUtils;

    //add
    @Override
    public void add(User user) {
        User dbUser = userRepository.findByUsername(user.getUsername());
    }

    //delete
    @Override
    public void deleteById(Integer id){
        userRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        userRepository.deleteBatch(ids);
    }

    //update
    @Override
    public void updateById(User user) {
        userRepository.updateById(user);
    }

    //search
    @Override
    public User selectById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }

    @Override
    public List<User> selectAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> selectPage(Integer pageNum, Integer pageSize){
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return userRepository.getUserPage(pageable);
    }

    //login

    //register

    //update password

    @Override
    public Result register(RegisterDto registerDto) {
        User optionalUser = userRepository.findByUsername(registerDto.getUsername());
        if (optionalUser == null) {
            String rawPassword = registerDto.getPassword();
            User user = new User();

            user.setUsername(registerDto.getUsername());
            user.setPassword(encoder.encode(rawPassword));
            user.setName(registerDto.getName());
            user.setEmail(registerDto.getEmail());
            user.setPhone(registerDto.getPhone());
            user.setAvatar(registerDto.getAvatar());
            user.setRole(registerDto.getRole());
            // default address
            user.setAddress1(DEFAULT_ADDRESS);
            // address2 is empty by default
            user.setAddress2("");

            userRepository.save(user);

            SecurityQuestion securityQuestion = new SecurityQuestion();
            securityQuestion.setUsername(registerDto.getUsername());
            securityQuestion.setQuestion(registerDto.getSecurityQuestion()==null?"":registerDto.getSecurityQuestion());
            securityQuestion.setAnswer(registerDto.getSecurityAnswer()==null?"":registerDto.getSecurityAnswer());
            securityQuestionRepository.save(securityQuestion);

            try {
                mailService.sendWelcomeEmail(registerDto.getEmail(), registerDto.getUsername());
            } catch (Exception e) {
                log.error("send email fail: ", e.getMessage());
            }
            return Result.success();
        } else {
            return Result.failure("Account already exists");
        }
    }

    @Override
    public Result login(String username, String password) {
        User optionalUser = userRepository.findByUsername(username);
        if (optionalUser == null) {
            return Result.failure("User not found");
        }

        if (!encoder.matches(password, optionalUser.getPassword())) {
            return Result.failure("Incorrect password");
        }

        String jwt = jwtUtils.generateToken(optionalUser.getId(), optionalUser.getRole());
        User loginUserInfo = new User();
        loginUserInfo.setId(optionalUser.getId());
        loginUserInfo.setUsername(optionalUser.getUsername());
        loginUserInfo.setEmail(optionalUser.getEmail());
        loginUserInfo.setPhone(optionalUser.getPhone());
        loginUserInfo.setRole(optionalUser.getRole());
        return new Result(true, "Success Login", MapUtil.builder()
                .put("token", jwt)
                .put("user", loginUserInfo)
                .map());
    }

    @Override
    public Result getUserSecurityQuestion(String username) {
        User optionalUser = userRepository.findByUsername(username);
        if (optionalUser == null) {
            return Result.failure("User not found");
        }
        SecurityQuestion securityQuestion = securityQuestionRepository.findByUsername(username);
        if (securityQuestion == null) {
            return Result.failure("User's Security question not found");
        }
        return Result.success(securityQuestion.getQuestion());
    }

    @Override
    public Result checkSecurityQuestion(String username, String question, String answer) {
        User optionalUser = userRepository.findByUsername(username);
        if (optionalUser == null) {
            return Result.failure("User not found");
        }
        SecurityQuestion securityQuestion = securityQuestionRepository.findByUsername(username);
        if (!answer.equals(securityQuestion.getAnswer())) {
            return Result.failure("Wrong answer");
        }
        return Result.success();
    }

    @Override
    public Result changePassword(String username, String newPassword) {
        User optionalUser = userRepository.findByUsername(username);
        if (optionalUser == null) {
            return Result.failure("User not found");
        }
        optionalUser.setPassword(encoder.encode(newPassword));
        userRepository.save(optionalUser);
        return Result.success();
    }

    @Override
    public Result getUserAddress(String username) {
        User optionalUser = userRepository.findByUsername(username);
        if (optionalUser == null) {
            return Result.failure("User not found");
        }
        Map<String, String> userAddress = new HashMap<String, String>();
        userAddress.put("address1", optionalUser.getAddress1());
        userAddress.put("address2", optionalUser.getAddress2());

        return Result.success(userAddress);
    }

    @Override
    public Result setUserAddress(String username, String address1, String address2) {
        User optionalUser = userRepository.findByUsername(username);
        if (optionalUser == null) {
            return Result.failure("User not found");
        }
        if ("".equals(address1) || address1 == null) {
            return Result.failure("address1 cannot be empty");
        }
        optionalUser.setAddress1(address1);
        optionalUser.setAddress2(address2);
        userRepository.save(optionalUser);
        return Result.success();
    }
}
