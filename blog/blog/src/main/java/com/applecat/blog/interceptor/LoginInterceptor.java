package com.applecat.blog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 登录拦截器
 * 判断是否登录
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 没有获取到user,未登录
        if (request.getSession().getAttribute("user") == null) {
            //重定向到登录页
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
    
}