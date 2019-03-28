package com.cn.seckill.dao;

import com.cn.seckill.domain.MiaoshaGoods;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by admin on 2019/3/27.
 */
public interface MiaoshaGoodsDao {
    @Select("select * from miaosha_goods")
    List<MiaoshaGoods> findAll();
}
