package iss.se.team4.secondhand.controller;

import iss.se.team4.secondhand.common.dto.*;
import iss.se.team4.secondhand.model.User;
import iss.se.team4.secondhand.service.UserService;
import iss.se.team4.secondhand.common.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    //add
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        userService.add(user);
        return Result.success();
    }

    //delete
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable int id) {
        userService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        userService.deleteBatch(ids);
        return Result.success();
    }

    //update
    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        userService.updateById(user);
        return Result.success();
    }

    //search
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable int id) {
        User user = userService.selectById(id);
        return Result.success(user);
    }

    @GetMapping("/selectAll")
    public Result selectAll() {
        List<User> list = userService.selectAll();
        return Result.success(list);
    }

    @PostMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "0") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<User> page = userService.selectPage(pageNum ,pageSize);
        return Result.success(page);
    }

    @PostMapping("/register")
    public Result register(@RequestBody RegisterDto registerDto) {
        return userService.register(registerDto);
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto) {
        return  userService.login(loginDto.getUsername(), loginDto.getPassword());
    }

    @GetMapping("/getSecurityQuestion/{username}")
    public Result getSecurityQuestion(@PathVariable String username) {
        return userService.getUserSecurityQuestion(username);
    }

    @PostMapping("/checkSecurityQuestion")
    public Result checkSecurityQuestion(@RequestBody CheckSecurityQuestionDto checkSecurityQuestionDto) {
        return userService.checkSecurityQuestion(checkSecurityQuestionDto.getUsername(),
                checkSecurityQuestionDto.getQuestion(), checkSecurityQuestionDto.getAnswer());
    }

    @PostMapping("/changePassword")
    public Result changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        return userService.changePassword(changePasswordDto.getUsername(), changePasswordDto.getPassword());
    }

    @GetMapping("/getUserAddress/{username}")
    public Result getUserAddress(@PathVariable String username) {
        return userService.getUserAddress(username);
    }

    @PostMapping("/setUserAddress")
    public Result setUserAddress(@RequestBody ChangeAddressDto changeAddressDto) {
        return userService.setUserAddress(changeAddressDto.getUsername(),
                changeAddressDto.getAddress1(),
                changeAddressDto.getAddress2());
    }
}
