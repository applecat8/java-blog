package com.applecat.blog;

import java.util.List;

import com.applecat.blog.dao.BlogDao;
import com.applecat.blog.model.pojo.Blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

	@Autowired
	private BlogDao blogDao;

	/**
	 * 测试分页
	 */
	@Test
	public void  testLimit(){
		List<Blog> blogs = blogDao.limitListBlog(null,0, 2);
		System.out.println("start");
		blogs.forEach(System.out::println);
	}

	@Test
	public void testAddBlog(){
		Blog blog = new Blog();
		blog.setAppreciation(true);
		blog.setPublished(true);
		blog.setRecommend(true);
		blog.setShareStatement(true);

		blogDao.saveBlog(blog);	
	}
	
	/**
	 * 测试分页
	 */
	@Test
	public void  testLimit1(){
	}
}
