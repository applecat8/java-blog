package com.applecat.blog.service.impl;

import java.util.List;

import com.applecat.blog.dao.TagDao;
import com.applecat.blog.exception.exception.NotFoundException;
import com.applecat.blog.model.pojo.Tag;
import com.applecat.blog.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * type service
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

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
            status[1] = "分类名称以存在";
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

    @Override
    public void deleteTags(int[] names) {

        //为空，直接返回
        if (names == null || names.length == 0) {
            return; 
        }

        StringBuilder sb = new StringBuilder();
        //将id数组拼接为id字符串链
        //[1,2,3] -> "1,2,3"
        for (int i = 0; i < names.length - 1; i++) {
            sb.append(names[i] + ",");
        }
        sb.append(names[names.length - 1]);
        tagDao.deleteTags(sb.toString());
    }
}