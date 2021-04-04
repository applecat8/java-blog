package com.applecat.blog.service.impl;

import com.applecat.blog.dao.BlogDao;
import com.applecat.blog.exception.exception.NotFoundException;
import com.applecat.blog.model.bo.Page;
import com.applecat.blog.service.BlogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogserviceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public Page limitListBlog(int index, Integer typeId) {
        int count = blogDao.getTotalBlogByTypeId(typeId);
        int total = (count + 7) / 8;

        // 当数据库中没有数据时会展示一页空白页,而不是报错
        if (total == 0) {
            Page page = new Page();
            page.setBlogs(null);
            page.setCount(0);
            page.setTotal(1);
            return page;
        }

        //当index不在范围内报错
        if (index <= 0 || index > total) {
            throw new NotFoundException("not found bolgs");
        }

        // new Page 用于传递数据
        Page page = new Page();
        page.setTotal(total);
        page.setCount(count);
        page.setBlogs(blogDao.limitListBlog(typeId,(index - 1) * 8, 8));
        return page;
    }

    @Override
    public void delBlog(int id) {
        try {
            blogDao.delBlog(id);
        } catch (Exception e) {
            throw e;
        }
    }
}
