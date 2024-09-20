package iss.se.team4.secondhand.service.impl;

import iss.se.team4.secondhand.model.User;
import iss.se.team4.secondhand.repository.UserRepository;
import iss.se.team4.secondhand.service.UserService;

import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    //dependency inject
    @Resource
    private UserRepository userRepository;

    //add
    @Override
    public void add(User user) {
        User dbUser = userRepository.selectByUsername(user.getUsername());
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
        return userRepository.selectById(id);
    }

    @Override
    public List<User> selectAll() {
        return userRepository.selectAll();
    }

    @Override
    public Page<User> selectPage(Integer pageNum, Integer pageSize){
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return userRepository.getUserPage(pageable);
    }

    //login

    //register

    //update password
}
