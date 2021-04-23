package com.applecat.blog.controller;

import com.applecat.blog.model.bo.Page;
import com.applecat.blog.service.BlogService;
import com.applecat.blog.service.TagService;
import com.applecat.blog.service.TypeService;
import com.applecat.blog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private UserService userService;

    @GetMapping(path = "/")
    public String index(@RequestParam(name = "index", defaultValue = "1") int index, Model model){
        Page page = blogService.limitListBlog(index, null);
        model.addAttribute("page", page);
        model.addAttribute("tags", tagService.listTopTag(8));
        model.addAttribute("types", typeService.listTypeTop(8));
        model.addAttribute("user", userService.getUserInfo());
        return "index";
    }

    @GetMapping(path = "/blog")
    public String blog(){
        return "blog";
    }
}