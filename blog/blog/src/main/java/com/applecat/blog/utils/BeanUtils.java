package com.applecat.blog.utils;

import java.lang.reflect.Field;


public class BeanUtils {

    /**
     * 从target 中寻找空值，用target中同样的属性值赋值
     * @param source
     * @param target
     */
    public static void copyProperties(Object source, Object target ){
        Class<?> sc =  source.getClass();
        Field[] sp =  sc.getDeclaredFields();
        for (Field field : sp) {
            field.setAccessible(true);
            try {
                if (field.get(source) == null) {
                   field.set(source, field.get(target)); 
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }    
}