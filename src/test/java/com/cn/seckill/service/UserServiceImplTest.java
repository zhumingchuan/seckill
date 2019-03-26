package com.cn.seckill.service;

import com.cn.seckill.SeckillApplication;
import com.cn.seckill.SeckillApplicationTests;
import com.cn.seckill.dao.UserDao;
import com.cn.seckill.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by admin on 2019/3/25.
 */
public class UserServiceImplTest extends SeckillApplicationTests{
    @Autowired
    private UserServiceImpl userService;
    @Test
    public void getAll() throws Exception {
    }

}