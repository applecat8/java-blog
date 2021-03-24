package com.applecat.blog.service.impl;

import java.util.List;

import com.applecat.blog.dao.TypeDao;
import com.applecat.blog.exception.exception.NotFoundException;
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

    @Override
    public void saveType(String name) {
        try {
            typeDao.saveType(name);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException("save faile");
        }
    }

    @Override
    public List<Type> listType() {
        return typeDao.listType();
    }

    @Override
    public void deleteTypes(int[] names) {
        StringBuilder sb = new StringBuilder();
        //将id数组拼接为id字符串链
        //[1,2,3] -> "1,2,3"
        for (int i = 0; i < names.length - 1; i++) {
            sb.append(names[i] + ",");
        }
        sb.append(names[names.length - 1]);
        typeDao.deleteTypes(sb.toString());
    }
}