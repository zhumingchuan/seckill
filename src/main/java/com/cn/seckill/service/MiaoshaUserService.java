package com.cn.seckill.service;

import com.cn.seckill.dao.MiaoshaUserDao;
import com.cn.seckill.domain.MiaoshaUser;
import com.cn.seckill.exception.GlobalException;
import com.cn.seckill.redis.MiaoshaUserKey;
import com.cn.seckill.redis.RedisService;
import com.cn.seckill.result.CodeMsg;
import com.cn.seckill.util.MD5Util;
import com.cn.seckill.util.UUIDUtil;
import com.cn.seckill.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhumc on 2019/3/27.
 */
@Service
public class MiaoshaUserService {
    public static final String COOKI_NAME_TOKEN = "token";
    @Autowired
    private RedisService redisService;
    @Autowired
    private MiaoshaUserDao miaoshaUserDao;

    public MiaoshaUser getById(Long id){
        MiaoshaUser miaoshaUser = redisService.get(MiaoshaUserKey.getById, "" + id, MiaoshaUser.class);
        if (miaoshaUser != null){
            return miaoshaUser;
        }
        MiaoshaUser user = miaoshaUserDao.findById(id);
        redisService.set(MiaoshaUserKey.getById,""+id,user);
        return user;

    }

    public String login(HttpServletResponse response, LoginVo loginVo){
        if (loginVo == null){
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        /*判断手机号是否存在*/
        MiaoshaUser user = getById(Long.parseLong(loginVo.getMobile()));
        if (user == null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        String databasePassword = user.getPassword();
        String dbSalt = user.getSalt();
        String formPassword = MD5Util.formPassToDBPass(loginVo.getPassword(), dbSalt);
        if (!databasePassword.equals(formPassword)){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        /*类似与sessionid，用来标识用户，写到cookie当中，传递给客户端，
        然后客户端在随后的访问中，都在cookie中上传这个token,然后服务端
        拿到这个token， 通过token取到用户对应的session信息
        */
        String token = UUIDUtil.uuid();
        addCookie(response,token,user);
        return  token;
    }
    /*延长cookie的有效期*/
    public void addCookie(HttpServletResponse response,String token,MiaoshaUser user){
        redisService.set(MiaoshaUserKey.token,token,user);
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN,token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public MiaoshaUser getByToken(HttpServletResponse response,String token){
        if (StringUtils.isEmpty(token)){
            return null;
        }
        MiaoshaUser user = redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);
        if (user !=null){
            redisService.set(MiaoshaUserKey.token,token,user);
        }
        return user;
    }
}
