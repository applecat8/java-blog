package com.applecat.blog.service;

import com.applecat.blog.model.bo.Page;
import com.applecat.blog.model.pojo.Blog;


public interface BlogService {
    Page limitListBlog(int index, Integer typeId);

    void delBlog(int id);

    void saveBlog(Blog blog, String tagIds);
}