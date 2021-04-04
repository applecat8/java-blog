package com.applecat.blog.controller.admin;

import javax.servlet.http.HttpSession;

import com.applecat.blog.model.pojo.User;
import com.applecat.blog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/admin")
public class LoginController {

    @Autowired
    private UserService service;

    @GetMapping
    public String LoginPage(){
        System.out.println("-----");
        return "admin/login";
    }

    /**
     * 登录检查判断
     */
    @PostMapping("/login")
    public String login(@RequestParam String username,@RequestParam String password,RedirectAttributes attributes,HttpSession session){
        //检查是否是正确的用户
        User user = service.checkUser(username, password); 
        //正确登录，
        if (user != null) {
            user.setPassword(null); 
            //将该用户的信息存入session
            session.setAttribute("user", user);
            //去后台主页
            return "redirect:/admin/blogs";
        }  
        //失败,回到登录页
        attributes.addFlashAttribute("message","用户名或密码错误!");
        return "redirect:/admin";
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}