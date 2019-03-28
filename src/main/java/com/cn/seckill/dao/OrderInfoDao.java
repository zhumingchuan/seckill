package com.cn.seckill.dao;

import com.cn.seckill.domain.OrderInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by admin on 2019/3/27.
 */
public interface OrderInfoDao {
    @Select("select * from order_info")
    List<OrderInfo> findAll();
}
