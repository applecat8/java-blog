package com.applecat.blog;

import java.util.Arrays;
import java.util.List;

import com.applecat.blog.dao.BlogDao;
import com.applecat.blog.dao.BlogTagDao;
import com.applecat.blog.model.pojo.Blog;
import com.applecat.blog.service.TagService;
import com.applecat.blog.service.TypeService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

	@Autowired
	private BlogDao blogDao;

	@Autowired
	private TagService tagService;

	@Autowired
	private TypeService typeService;

	@Autowired
	private BlogTagDao blogTagDao;

	/**
	 * 测试分页
	 */
	@Test
	public void  testLimit(){
		List<Blog> blogs = blogDao.limitListBlog(null,0, 2);
		System.out.println("start");
		blogs.forEach(System.out::println);
	}

	/**
	 * 测试保存blog
	 */
	@Test
	public void testAddBlog(){
		Blog blog = new Blog();
		blog.setAppreciation(true);
		blog.setPublished(true);
		blog.setRecommend(true);
		blog.setShareStatement(true);

		blogDao.saveBlog(blog);	
	}

	@Test
	public void getTagid() {
		int[] tags = blogTagDao.listTagIds(2);
		System.out.println(Arrays.toString(tags));
	}
	
	@Test
	public void getBlog() {
		Blog blog = blogDao.getBlog(2);		
		System.out.println(blog);
	}

	@Test
	public void tagTop(){
		tagService.listTopTag(3).stream().forEach(System.out :: println);;
	}

	@Test
	public void typeTop(){
		typeService.listTypeTop(3).stream().forEach(System.out :: println);
	}
}
