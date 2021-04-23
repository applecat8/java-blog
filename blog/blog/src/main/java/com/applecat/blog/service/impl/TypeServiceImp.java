package com.applecat.blog.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.applecat.blog.dao.BlogDao;
import com.applecat.blog.dao.TypeDao;
import com.applecat.blog.exception.exception.NotFoundException;
import com.applecat.blog.model.bo.TypeTop;
import com.applecat.blog.model.pojo.Type;
import com.applecat.blog.service.TypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * type service
 */
@Service
public class TypeServiceImp implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Autowired
    private BlogDao blogDao;

    @Override
    public String[] saveType(String name) {
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
        if (typeDao.findByName(name) != null) {
            status[0] = "false";
            status[1] = "分类名称以存在";
            return status;
        }

        try {
            typeDao.saveType(name);
            status[0] = "true";
            status[1] = "新增成功";
            return status;
        } catch (Exception e) {
            throw new NotFoundException("save faile" + e.getMessage());
        }
    }

    @Override
    public List<Type> listType() {
        return typeDao.listType();
    }

    @Override
    public void deleteTypes(int[] names) {

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
        typeDao.deleteTypes(sb.toString());
    }

    @Override
    public List<TypeTop> listTypeTop(int len) {
        List<Type> types = typeDao.listType();
        int size = types.size();
        List<TypeTop> results = new ArrayList<>(size);
        for (Type type: types) {
            results.add(new TypeTop(type.getName(), blogDao.countByTypeId(type.getId())));
        }
        Collections.sort(results);
        return results.subList(0, len > size ? size : len);
    }
}