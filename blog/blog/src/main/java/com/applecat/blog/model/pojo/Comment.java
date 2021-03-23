package com.applecat.blog.model.pojo;

import java.util.Date;

import lombok.Data;

/**
 * 留言交流实体类
 */
@Data
public class Comment{
    private Integer id;
    private String nikeName;
    private String content;
    private String avatar;
    private Date createTime;
}