package com.applecat.blog.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 前端首页展示使用
 */
@Data
@AllArgsConstructor
public class TagTop implements Comparable<TagTop>{

    private String name;    

    // 该标签的数量
    private int number;

    @Override
    public int compareTo(TagTop arg0) {
        return arg0.getNumber() - this.number;
    }
}
