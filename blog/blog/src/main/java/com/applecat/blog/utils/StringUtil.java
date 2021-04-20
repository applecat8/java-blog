package com.applecat.blog.utils;

public class StringUtil {

    public static boolean isEmpty(String s){
        if ("".equals(s) || s == null) {
            return true; 
        }
        return false;
    }

    /**
     * 将int数组以 ， 为间隔拼接为字符串
     * @param nums
     * @return
     */
    public static String arrayConverter(int[] nums){
        StringBuilder sb = new StringBuilder();
        //将id数组拼接为id字符串链
        //[1,2,3] -> "1,2,3"
        for (int i = 0; i < nums.length - 1; i++) {
            sb.append(nums[i] + ",");
        }
        sb.append(nums[nums.length - 1]);

        return sb.toString();
    }
}