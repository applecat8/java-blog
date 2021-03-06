package com.applecat.blog.controller.admin;

import com.applecat.blog.model.bo.Page;
import com.applecat.blog.model.pojo.Blog;
import com.applecat.blog.service.BlogService;
import com.applecat.blog.service.TypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/blogs")
    public String blogs(@RequestParam(name = "index", defaultValue = "1") int index, Model model) {
        // 获取页面对象
        Page page = blogService.limitListBlog(index, null);

        //所有分类， 用于筛选
        model.addAttribute("types", typeService.listType());

        model.addAttribute("page", page);
        model.addAttribute("index", page.getIndex());
        return "admin/admin";
    }

    /**
     * 根据分类搜索查询
     * @param typeId 分类id
     */
    @PostMapping("/blogs/search")
    public String search(@RequestParam(name = "index", defaultValue = "1") int index, Integer typeId, Model model) {
        // 获取页面对象
        Page page = blogService.limitListBlog(index, typeId);

        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", page);
        model.addAttribute("index", page.getIndex());
        //局部刷新
        return "admin/admin::blogList";
    }
    
    /**
     * 删除博客
     */
    @GetMapping("/blogs/del")
    public String delBlog(int index,int id){
        blogService.delBlog(id);
        return "redirect:/admin/blogs?index=" + index;
    }

    /**
     * 保存新博客
     */
    @PostMapping("/blogs/save")
    public String saveBlog(Blog blog, String tagIds){
        //如果id不为空，说明是更新操作
        if (blog.getId() != null) {
            blogService.updateBlog(blog, tagIds);
        }else {
            blogService.saveBlog(blog, tagIds);
        }
        return "redirect:/admin/blogs";
    }
}