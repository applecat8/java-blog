package com.applecat.blog.service;

import com.applecat.blog.model.pojo.User;

public interface UserService {
    public boolean isAdmin(String name,String password);

    public User checkUser(String name,String password);
}