package com.cn.seckill.service;

import com.cn.seckill.dao.UserDao;
import com.cn.seckill.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhumc on 2019/3/25.
 */
@Service
public class UserServiceImpl {
    @Autowired
    private UserDao userDao;
    public User getById(int id){
        return userDao.getById(id);
    }
    public List<User> getAll(){
        return userDao.getAll();
    }
    public void insert(User user){
        userDao.insert(user);
    }
}
