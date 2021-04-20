package com.applecat.blog.utils;

public class StringUtil {

    public static boolean isEmpty(String s){
        if ("".equals(s) || s == null) {
            return true; 
        }
        return false;
    }
}