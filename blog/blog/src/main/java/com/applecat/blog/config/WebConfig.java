package com.applecat.blog.config;

import com.applecat.blog.interceptor.LoginInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    
    /**
     * 添加web相关配置
     * @return
     */
    @Bean
    public WebMvcConfigurer webcof(){
        return new WebMvcConfigurer(){
            /**
             * 添加拦截器
             */
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //添加登录拦截器
                registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/**").excludePathPatterns("/admin","/admin/login");
            } 
        };
    }
}