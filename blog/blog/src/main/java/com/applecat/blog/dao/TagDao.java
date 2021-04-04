package com.applecat.blog.dao;

import java.util.List;

import com.applecat.blog.model.pojo.Tag;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * 标签dao
 */
@Mapper
public interface TagDao {

    /**
     * 查询所有标签
     * @return
     */
    @Select("select * from t_tag")
    public List<Tag> listTag();

    /**
     * 根据给定的标签名称来保存为一个新的分类
     * @param tname 新标签的名称
     */
    @Insert("insert into t_tag values(null,#{tname})")
    public int saveTag(String tname); 

    /**
     * 根据id字符串来删除对应的标签
     * @param ids id组拼接的字符串 "1,2,3"
     * @return
     */
    @Delete("delete from t_tag where id in ( ${ids} )")
    public int deleteTags(String ids);

    @Select("select * from t_tag where name = #{name}")
    public Tag findTagByName(String name);
}