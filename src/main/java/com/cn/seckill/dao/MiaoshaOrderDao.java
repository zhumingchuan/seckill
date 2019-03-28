package com.cn.seckill.dao;

import com.cn.seckill.domain.MiaoshaOrder;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by admin on 2019/3/27.
 */
public interface MiaoshaOrderDao {
    @Select("select * from miaosha_order")
    List<MiaoshaOrder> findAll();
}
