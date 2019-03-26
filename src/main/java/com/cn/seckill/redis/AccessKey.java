package com.cn.seckill.redis;

/**
 * Created by admin on 2019/3/26.
 */
public class AccessKey extends BasePrefix {
    private AccessKey( int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static AccessKey withExpire(int expireSeconds) {
        return new AccessKey(expireSeconds, "access");
    }
}
