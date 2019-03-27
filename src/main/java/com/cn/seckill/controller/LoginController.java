package com.cn.seckill.controller;

import com.cn.seckill.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by admin on 2019/3/26.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }
    @RequestMapping("/do_login")
    @ResponseBody
    public Result<String> doLogin(HttpServletResponse response) {

        return Result.success("ok");
    }
}
