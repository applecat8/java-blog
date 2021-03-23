package com.applecat.blog.model.pojo;

import java.util.Date;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

import lombok.Data;

/**
 * 管理用户实体类
 */
@Data
public class User {

    @Column(name = "id",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private Integer id; 
    @Column(name = "nick_name",type = MySqlTypeConstant.VARCHAR,length = 11)
    private String nickname;//昵称
    @Column(name = "username",type = MySqlTypeConstant.VARCHAR,length = 11)
    private String username;
    @Column(name = "password",type = MySqlTypeConstant.VARCHAR,length = 11)
    private String password;
    @Column(name = "email",type = MySqlTypeConstant.VARCHAR,length = 30)
    private String email;
    @Column(name = "avater",type = MySqlTypeConstant.VARCHAR)
    private String avater;// 头像

    //不同用户的权限
    @Column(name = "authority",type = MySqlTypeConstant.INT)
    private Integer authority; 
    @Column(name = "create_time",type = MySqlTypeConstant.DATETIME)
    private Date createTime;
    @Column(name = "update_time",type = MySqlTypeConstant.DATETIME)
    private Date updateTime;
}