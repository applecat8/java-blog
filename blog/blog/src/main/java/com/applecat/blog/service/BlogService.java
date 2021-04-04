package com.applecat.blog.service;


import com.applecat.blog.model.bo.Page;


public interface BlogService {
    Page limitListBlog(int index, Integer typeId);

    void delBlog(int id);
}