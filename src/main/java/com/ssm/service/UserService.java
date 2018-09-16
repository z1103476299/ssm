package com.ssm.service;

import com.ssm.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    void saveUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(int id);

    User findById(int id);

    User checkUser(String username,String password);

    List<User> findAll();

}
