package com.applecat.blog.model.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * 博客标签实体类
 */
@Data
@Entity
@Table(name = "t_tag")
public class Tag {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "name")
    private String name; 

    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs = new ArrayList<>();
}