package com.applecat.blog.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfo {
    private String name;
    private String headPortrait;
}