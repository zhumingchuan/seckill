package com.cn.seckill.config;

import com.alibaba.fastjson.JSON;
import com.cn.seckill.access.AccessLimit;
import com.cn.seckill.access.UserContext;
import com.cn.seckill.domain.MiaoshaUser;
import com.cn.seckill.redis.AccessKey;
import com.cn.seckill.redis.RedisService;
import com.cn.seckill.result.CodeMsg;
import com.cn.seckill.service.MiaoshaUserService;
import com.cn.seckill.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 2019/3/28.
 */
@Service
public class AccessInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private MiaoshaUserService miaoshaUserService;
    @Autowired
    private RedisService redisService;



    public MiaoshaUser getUser(HttpServletRequest request, HttpServletResponse response){
        /*获取页面token*/
        String parameterToken = request.getParameter(MiaoshaUserService.COOKI_NAME_TOKEN);
        String cookieToken = getCookieValue(request, MiaoshaUserService.COOKI_NAME_TOKEN);
        if (StringUtils.isEmpty(parameterToken) && StringUtils.isEmpty(cookieToken)){
            return null;
        }
        String token = StringUtils.isEmpty(parameterToken) ? cookieToken : parameterToken;
        MiaoshaUser user = miaoshaUserService.getByToken(response, token);
        return user;
    }

    public String getCookieValue(HttpServletRequest request,String cookiename){
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length==0){
            return null;
        }
        for (Cookie cookie : cookies){
            if (cookie.getName().equals(cookiename)){
                return cookie.getValue();
            }
        }
        return null;
    }

    public void render(HttpServletResponse response, CodeMsg codeMsg) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        String str = JSON.toJSONString(Result.error(codeMsg));
        outputStream.write(str.getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod){
            MiaoshaUser user = getUser(request, response);
            UserContext.setUser(user);
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            AccessLimit accessLimit = handlerMethod.getMethodAnnotation(AccessLimit.class);
            String key = request.getRequestURI();
            if (accessLimit == null){
                return true;
            }
            int seconds = accessLimit.seconds();
            int maxCount = accessLimit.maxCount();
            boolean needLogin = accessLimit.needLogin();
            if (needLogin){
                if (user == null){
                    render(response,CodeMsg.SERVER_ERROR);
                }
                key += "_"+user.getId();
            }else {

            }
            AccessKey ak = AccessKey.withExpire(seconds);
            Integer count = redisService.get(ak, key, Integer.class);
            if (count == null){
                redisService.set(ak,key,1);
            }else {
                render(response,CodeMsg.ACCESS_LIMIT_REACHED);
                return false;
            }
        }


        return true;
    }
}
