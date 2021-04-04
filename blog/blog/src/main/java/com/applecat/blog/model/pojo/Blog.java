package com.applecat.blog.model.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

/**
 * blog实体类
 */
@Data
@Entity
@Table(name = "t_blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;    
    //标题
    private String title;
    // 内容
    private String content;
    //首页图片链接
    private String firstPicture;
    private String flag;
    private Integer views;
    //是否打开赞赏功能
    private boolean appreciation;
    //版权声明
    private boolean shareStatement;
    private boolean published;
    //是否推荐
    private boolean recommend;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creatDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    // 博客的分类
    @ManyToOne
    private Type type;

    //博客的标签
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Tag> tags = new ArrayList<>();
}