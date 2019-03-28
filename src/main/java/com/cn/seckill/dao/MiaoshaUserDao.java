package com.cn.seckill.dao;

import com.cn.seckill.domain.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by admin on 2019/3/27.
 */
@Mapper
public interface MiaoshaUserDao {
    @Select("select * from miaosha_user")
    List<MiaoshaUser> getAll();
    @Select("select * from miaosha_user where nickname = #{id}")
    MiaoshaUser findById(@Param("id")Long id);
}
