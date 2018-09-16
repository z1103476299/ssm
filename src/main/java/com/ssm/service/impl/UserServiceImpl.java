package com.ssm.service.impl;

import com.ssm.dao.UserDao;
import com.ssm.entity.User;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 新增用户
     * @param user
     */
    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }


    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @Override
    public boolean deleteUser(int id) {
        return userDao.deleteUser(id);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public User checkUser(String username, String password) {
        User user = userDao.checkUser(username,password);
        if (user!=null){
            return user;
        }
        return null;
    }

    /**
     * 查找所有用户
     * @return
     */
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
