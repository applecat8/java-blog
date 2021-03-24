package com.applecat.blog.model.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 管理用户实体类
 */
@Data
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 
    private String nickname;//昵称
    private String username;
    private String password;
    private String email;
    private String avater;// 头像

    //不同用户的权限
    private Integer authority; 
    private Date createTime;
    private Date updateTime;
}