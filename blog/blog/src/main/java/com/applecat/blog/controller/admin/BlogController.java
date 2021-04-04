package com.applecat.blog.controller.admin;

import com.applecat.blog.model.bo.Page;
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
        model.addAttribute("index", index);
        return "admin/admin";
    }

    @PostMapping("/blogs/search")
    public String search(@RequestParam(name = "index", defaultValue = "1") int index, Integer typeId, Model model) {
        // 获取页面对象
        Page page = blogService.limitListBlog(index, typeId);

        //所有分类， 用于筛选
        // model.addAttribute("types", typeService.listType());
        // model.addAttribute("page", page);
        // model.addAttribute("index", index);
        System.out.println(index +"> "+ typeId);
        System.out.println(page);
        
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", page);
        model.addAttribute("index", index);
        return "admin/admin::blogList";
    }

    /**
     * 删除博客
     * @param index
     * @param id
     * @return
     */
    @GetMapping("/blogs/del")
    public String delBlog(int index,int id){
        blogService.delBlog(id);
        return "redirect:/admin/blogs?index=" + index;
    }
}