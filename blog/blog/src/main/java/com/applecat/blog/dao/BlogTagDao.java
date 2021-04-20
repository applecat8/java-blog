package com.applecat.blog.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogTagDao {
    @Insert("insert into t_blog_tags values(#{blogId}, #{tagId})")
    public int saveMapping(int blogId, int tagId);

    @Delete("delete from t_blog_tags where blogs_id = #{blogId}")
    public int delMapping(int blogId);
}