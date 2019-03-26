package com.cn.seckill.redis;

/**
 * Created by admin on 2019/3/26.
 */
public interface KeyPrefix {
    public int expireSeconds();

    public String getPrefix();
}
