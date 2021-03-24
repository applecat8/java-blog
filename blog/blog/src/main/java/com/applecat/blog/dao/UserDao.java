package com.applecat.blog.dao;

import com.applecat.blog.model.pojo.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 用户表的dao接口
 */
@Mapper
public interface UserDao {
    @Select("select count(*) from t_user where username = #{name} and password = #{password}")
    public int conutByNameAndPassword(String name,String password);

    @Select("select * from t_user where username = #{name} and password = #{password}")
    public User findUserByNameAndPassword(String name,String password);
}