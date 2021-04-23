package com.applecat.blog.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.applecat.blog.dao.BlogTagDao;
import com.applecat.blog.dao.TagDao;
import com.applecat.blog.exception.exception.NotFoundException;
import com.applecat.blog.model.bo.TagTop;
import com.applecat.blog.model.pojo.Tag;
import com.applecat.blog.service.TagService;
import com.applecat.blog.utils.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * type service
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    @Autowired
    private BlogTagDao blogTagDao;

    @Override
    public String[] saveTag(String name) {
        //status[0]表示操作是否成功
        //status[1]对应的提示信息
        String[] status = new String[2];
        //判断名称是否为空
        if ("".equals(name) || name == null) {
            status[0] = "false";
            status[1] = "分类名称不能为空";
            return status;
        }

        //判断该分类标签在数据库中是否存在
        if (tagDao.findTagByName(name) != null) {
            status[0] = "false";
            status[1] = "分类名称已存在";
            return status;
        }

        try {
            tagDao.saveTag(name);
            status[0] = "true";
            status[1] = "新增成功";
            return status;
        } catch (Exception e) {
            throw new NotFoundException("save faile" + e.getMessage());
        }
    }

    @Override
    public List<Tag> listTag() {
        return tagDao.listTag();
    }

    /**
     * 删除标签
     */
    @Override
    public void deleteTags(int[] names) {

        //为空，直接返回
        if (names == null || names.length == 0) {
            return; 
        }
        tagDao.deleteTags(StringUtil.arrayConverter(names));
    }

    /**
     * 以标签的使用量来排序返回
     */
    @Override
    public List<TagTop> listTopTag(int len){
        List<Tag> tags = listTag();
        int size = tags.size();
        List<TagTop> result = new ArrayList<>(size);
        for (Tag tag : tags) {
            result.add(new TagTop(tag.getName(), blogTagDao.blogCountByTag(tag.getId())));
        }
        Collections.sort(result);
        return result.subList(0, len > size ? size : len);
    }
}