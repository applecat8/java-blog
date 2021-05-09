package com.applecat.blog.dao;

import java.util.List;

import com.applecat.blog.model.pojo.Blog;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    @Result(property = "createDate", column = "create_date")
    @Result(property = "firstPicture", column = "first_picture")
    @Result(property = "shareStatement", column = "share_statement")
    @Result(property = "type", column = "type_id", javaType = Type.class, one = @One(select = "com.applecat.blog.dao.TypeDao.findById"))
    public List<Blog> limitListBlog(Integer typeId, int start,int pageSize);


    @Select("select * from t_blog where title like #{query} or content like #{query} limit #{start}, #{pageSize}")
    @Result(property = "updateDate", column = "update_date")
    @Result(property = "createDate", column = "create_date")
    @Result(property = "firstPicture", column = "first_picture")
    @Result(property = "shareStatement", column = "share_statement")
    @Result(property = "type", column = "type_id", javaType = Type.class, one = @One(select = "com.applecat.blog.dao.TypeDao.findById"))
    public List<Blog> LimitSearchBlog(int start, int pageSize, String query);

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

    /**
     * 获取博客完整信息
     * @param id
     * @return
     */
    @Select("select * from t_blog where id = #{id}")
    @Result(property = "id", column = "id")
    @Result(property = "createDate", column = "create_date")
    @Result(property = "firstPicture", column = "first_picture")
    @Result(property = "updateDate", column = "update_date")
    @Result(property = "shareStatement", column = "share_statement")
    @Result(property = "type", column = "type_id", javaType = Type.class, one = @One(select = "com.applecat.blog.dao.TypeDao.findById"))
    @Result(property = "tags", column = "id", many = @Many(select = "com.applecat.blog.dao.TagDao.findTagById"))
    public Blog getBlog(int id);

    /**
     * 保存博客
     * @param blog
     * @return
     */
    @Insert("insert into t_blog values(null,#{appreciation},#{content},#{createDate},#{firstPicture},#{flag},#{published},#{recommend},#{shareStatement},#{title},#{updateDate},#{views},#{type.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int saveBlog(Blog blog);

    /**
     * 删除博客 
     * @param id
     */
    @Delete("delete from t_blog where id = #{id}")
    public int delBlog(int id);

    /**
     * 更新
     */
    @Update("update t_blog set appreciation = #{appreciation}, content = #{content}, first_picture = #{firstPicture}, flag = #{flag}, published = #{published}, recommend = #{recommend}, share_statement = #{shareStatement}, title = #{title}, update_date = #{updateDate}, type_id = #{type.id} where id = #{id}")
    public void updateBlog(Blog blog);

    @Select("select count(*) from t_blog where type_id = #{typeId}")
    public int countByTypeId(int typeId);
}