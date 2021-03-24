package com.applecat.blog.service;

import com.applecat.blog.model.pojo.Type;

import java.util.List;

public interface TypeService {

    public void saveType(String name);    

    public List<Type> listType();

    /**
     * 根据名称组来删除对应的分类
     * @param names 名称组
     */
    public void deleteTypes(int[] names);
}