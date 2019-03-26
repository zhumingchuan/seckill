package com.cn.seckill.dao;

import com.cn.seckill.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zhumc on 2019/3/25.
 */

public interface UserDao {
    @Select("select * from user where id = #{id}")
    User getById(@Param("id")int id	);
    @Select("select * from user")
    List<User> getAll();
    @Insert("insert into user(name)values(#{name})")
    public int insert(User user);


}
