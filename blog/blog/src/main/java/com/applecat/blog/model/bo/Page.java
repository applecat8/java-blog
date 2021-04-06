package com.applecat.blog.model.bo;

import java.util.List;

import com.applecat.blog.model.pojo.Blog;

import lombok.Data;
/**
 * 分页信息类，用于封装查询的结果
 */
@Data
public class Page {
    //当前页面的所有Blog
    private List<Blog> blogs;
    //一共可以分为多少页
    private int total;
    //Blog的数量
    private int count;
    //当前位于页 
    private int index;
}