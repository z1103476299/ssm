package com.ssm.dao;

import com.ssm.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    void saveUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(int id);

    User findById(int id);

    User checkUser(@Param("username") String username,@Param("password") String password);

    List<User> findAll();


}
