package com.applecat.blog.service.impl;

import java.util.Date;

import com.applecat.blog.dao.BlogDao;
import com.applecat.blog.dao.BlogTagDao;
import com.applecat.blog.model.bo.Page;
import com.applecat.blog.model.pojo.Blog;
import com.applecat.blog.service.BlogService;
import com.applecat.blog.utils.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogserviceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private BlogTagDao blogTagDao;

    @Override
    public Page limitListBlog(int index, Integer typeId) {

        int count = blogDao.getTotalBlogByTypeId(typeId);
        int total = (count + 7) / 8;

        // new Page 用于传递数据
        Page page = new Page();
        // 当数据库中没有数据时会展示一页空白页,而不是报错
        if (total == 0) {
            page.setIndex(1);
            page.setBlogs(null);
            page.setCount(0);
            page.setTotal(1);
            return page;
        }

        page.setTotal(total);
        page.setCount(count);

        // 当index不在范围内，返回第一页，和第一页的数据
        if (index <= 0 || index > total) {
            page.setIndex(1);
            page.setBlogs(blogDao.limitListBlog(typeId, 0, 8));
        } else { // 正常返回
            page.setBlogs(blogDao.limitListBlog(typeId, (index - 1) * 8, 8));
            page.setIndex(index);
        }
        return page;
    }

    @Override
    public void delBlog(int id) {
        try {
            //删除blog前要先将t_blog_tags表中的对应关系删除
            blogTagDao.delMapping(id);
            blogDao.delBlog(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void saveBlog(Blog blog, String tagIds) {
        //设置博客相关时间
        blog.setCreateDate(new Date());
        blog.setUpdateDate(new Date());

        try {
            blogDao.saveBlog(blog);

            //保存blog_tag表之间的映射关系
            //将id字符串分割为数组"1,2,3" -> [1,2,3]
            if (!StringUtil.isEmpty(tagIds)) {
                String[] tagArray = tagIds.split(",");
                for (String tagId : tagArray) {
                    System.out.println(tagId + "---");
                    blogTagDao.saveMapping(blog.getId(), Integer.parseInt(tagId));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
