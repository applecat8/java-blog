package com.applecat.blog.dao;

import java.util.List;

import com.applecat.blog.model.pojo.Type;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * 分类标签dao
 */
@Mapper
public interface TypeDao {

    /**
     * 查询所有分类
     * @return
     */
    @Select("select * from t_type")
    public List<Type> listType();

    /**
     * 根据给定的分类名称来保存为一个新的分类
     * @param tname 新分类的名称
     */
    @Insert("insert into t_type values(null,#{tname})")
    public int saveType(String tname); 

    /**
     * 根据id字符串来删除对应的分类
     * @param ids id组拼接的字符串 "1,2,3"
     * @return
     */
    @Delete("delete from t_type where id in ( ${ids} )")
    public int deleteTypes(String ids);
}