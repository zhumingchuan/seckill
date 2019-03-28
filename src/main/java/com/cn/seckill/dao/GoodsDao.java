package com.cn.seckill.dao;

import com.cn.seckill.domain.Goods;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by admin on 2019/3/27.
 */
public interface GoodsDao {
    @Select("select * from goods")
    List<Goods> findAll();
}
