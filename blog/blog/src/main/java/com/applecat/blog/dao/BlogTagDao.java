package com.applecat.blog.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BlogTagDao {
    @Insert("insert into t_blog_tags values(#{blogId}, #{tagId})")
    public int saveMapping(int blogId, int tagId);

    /**
     * 从中间表中删除对应的映射关系
     * @param blogId 博客id
     */
    @Delete("delete from t_blog_tags where blogs_id = #{blogId}")
    public int delMapping(int blogId);

    @Select("select tags_id from t_blog_tags where blogs_id = #{blogId}")
    public int[] listTagIds(int blogId);
}