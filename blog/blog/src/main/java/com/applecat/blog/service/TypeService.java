package com.applecat.blog.service;

import com.applecat.blog.model.pojo.Type;

import java.util.List;

public interface TypeService {

    /**
     * 保存一个新分类
     * @param name 新分类名称
     */
    public String[] saveType(String name);    

    /**
     * 列出所有的分类
     * @return
     */
    public List<Type> listType();

    /**
     * 根据名称组来删除对应的分类
     * @param names 名称组
     */
    public void deleteTypes(int[] names);
}