package com.applecat.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @GetMapping(path = "/{id}")
    public String index(@PathVariable int id){
        // String blog = "";
        // if (blog == null || "".equals(blog)) {
        //     throw new NotFoundException("博客未找到");    
        // }
        System.out.println("----index"+ id +"-----");
        return "index";
    }

    @GetMapping(path = "/blog")
    public String blog(){
        return "blog";
    }
}