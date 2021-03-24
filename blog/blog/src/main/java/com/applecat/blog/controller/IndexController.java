package com.applecat.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(path = "/")
    public String index(){
        return "index";
    }

    @GetMapping(path = "/blog")
    public String blog(){
        return "blog";
    }
}