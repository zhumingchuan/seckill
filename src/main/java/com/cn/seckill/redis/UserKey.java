package com.cn.seckill.redis;

/**
 * Created by admin on 2019/3/26.
 */
public class UserKey extends BasePrefix {
    public UserKey(String prefix) {
        super(prefix);
    }
    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
}
