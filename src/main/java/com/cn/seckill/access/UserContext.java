package com.cn.seckill.access;

import com.cn.seckill.domain.MiaoshaUser;

/**
 * Created by admin on 2019/3/28.
 */
public class UserContext {
    private static ThreadLocal<MiaoshaUser> userHolder = new ThreadLocal<MiaoshaUser>();
    public static void setUser(MiaoshaUser user){
        userHolder.set(user);
    }
    public static MiaoshaUser getUser(){
        return userHolder.get();
    }
}
