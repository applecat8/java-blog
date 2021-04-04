package com.applecat.blog.service;

import com.applecat.blog.model.pojo.Tag;

import java.util.List;

public interface TagService {

    /**
     * 保存一个新标签
     * @param name 新标签名称
     */
    public String[] saveTag(String name);

    /**
     * 列出所有的标签
     * @return
     */
    public List<Tag> listTag();

    /**
     * 根据名称组来删除对应的分类
     * @param names 名称组
     */
    public void deleteTags(int[] names);
}