package com.applecat.blog.controller.admin;

import com.applecat.blog.model.pojo.Blog;
import com.applecat.blog.service.BlogService;
import com.applecat.blog.service.TagService;
import com.applecat.blog.service.TypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 新增博客页面
 */
@Controller
@RequestMapping("/admin/blogs")
public class ReleaseController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    /**
     * 发布新博客
     */
    @GetMapping("/release")
    public String release(Model model){
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        model.addAttribute("blog", new Blog());
        return "admin/release";
    } 
    
    /**
     * 编辑博客
     * 将原本的数据回写到页面上
     * @param id 博客id
     */
    @GetMapping("/edit")
    public String editBlog(int id, Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        //回写blog数据
        Blog blog = blogService.getBlog(id);
        model.addAttribute("blog", blog);
        //回写blog标签
        model.addAttribute("tagIds", blogService.getTagIds(blog.getId()));
        return "admin/release";
    }
}