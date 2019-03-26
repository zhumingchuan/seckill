package com.cn.seckill.controller;

import com.cn.seckill.domain.User;
import com.cn.seckill.redis.RedisService;
import com.cn.seckill.redis.UserKey;
import com.cn.seckill.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhumc on 2019/3/25.
 */
@Controller
@RequestMapping("/test")
public class DemoController {
    @Autowired
    private RedisService redisService;
    @RequestMapping("/list")
    @ResponseBody
    public boolean list() {
        User user = new User();
        user.setId(22);
        user.setName("222");
        boolean b = redisService.set(UserKey.getById, "" + 1, user);
        return b;
    }

}
