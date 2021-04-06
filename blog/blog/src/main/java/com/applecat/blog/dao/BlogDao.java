package com.applecat.blog.dao;

import java.util.List;

import com.applecat.blog.model.pojo.Blog;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import aj.org.objectweb.asm.Type;

/**
 * Blog
 */
@Mapper
public interface BlogDao {

    /**
     * 分页查询
     * @param start 开始数
     * @param pageSize 每页数量
     * @return 结果集
     */
    @Select({"<script>",
             "select * from t_blog",
             "where 1=1 ",
             "<when test='typeId != null'>",
             " and type_id = #{typeId} ",
             "</when>",
             "limit #{start}, #{pageSize}",
             "</script>"})
    //@Select("select * from t_blog where type_id =  limit #{start},#{pageSize}")
    @Result(property = "updateDate", column = "update_date")
    @Result(property = "type", column = "type_id", javaType = Type.class, one = @One(select = "com.applecat.blog.dao.TypeDao.findById"))
    public List<Blog> limitListBlog(Integer typeId, int start,int pageSize);

    /**
     * 查询总共有多少blog
     * @return 总数
     */
    @Select("select count(*) from t_blog")
    public int getTotalBlog();

    /**
     * 查询特定type_id的Blog的总数
     * @param typeId type_id
     * @return
     */
    @Select({"<script>",
             "select count(*) from t_blog",
             "where 1=1 ",
             "<when test='typeId != null'>",
             " and type_id = #{typeId} ",
             "</when>",
             "</script>"})
    public int getTotalBlogByTypeId(Integer typeId);

    @Select("select * from t_blog where id = #{id}")
    public Blog getBlog(int id);

    @Insert("insert into t_blog values(null,#{appreciation},#{content},#{createDate},#{firstPicture},#{flag},#{published},#{recommend},#{shareStatement},#{title},#{updateDate},#{views},#{type.id})")
    public int saveBlog(Blog blog);

    @Delete("delete from t_blog where id = #{id}")
    public int delBlog(int id);
}