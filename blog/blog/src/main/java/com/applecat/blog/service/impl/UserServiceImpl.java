package com.applecat.blog.service.impl;

import com.applecat.blog.dao.UserDao;
import com.applecat.blog.model.bo.UserInfo;
import com.applecat.blog.model.pojo.User;
import com.applecat.blog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Override
    public boolean isAdmin(String name, String password) {
        if (name == null || password == null)
            return false;
        int count = dao.conutByNameAndPassword(name, password);

        return count > 0 ? true : false;
    }

    /**
     * 根据用户名和密码检查是否为正确的用户
     * @param name 用户名
     * @param password 密码
     */
    @Override
    public User checkUser(String name, String password) {
        return dao.findUserByNameAndPassword(name, password);
    }

    public UserInfo getUserInfo(){
        User user = dao.getUser();
        return new UserInfo(user.getNickname(), user.getAvater());
    }
}