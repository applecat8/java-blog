package com.applecat.blog.aspect.log;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 日志
 */
@Aspect
@Component
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.applecat.blog.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes(); 

        // 获取要封装的请求数据
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        RequestLog log = new RequestLog(url, ip, classMethod, args);
        logger.info("RequestLog: {}",log);
    }

    @After("log()")
    public void doAfter() {
        // TODO: after <+applecat+>
        logger.info("---After-----");
    }

    @AfterReturning("log()")
    public void doAfterReturn() {
        // TODO: after return <+applecat+>
        logger.info("----return-----");
    }

    @AfterThrowing("log()")
    public void doAfterThrow() {
        // TODO: after throw <+applecat+>
        logger.info("----throw-----");
    }

    /**
     * 请求日志封装
     */
    private class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "[args=" + Arrays.toString(args) + ", classMethod=" + classMethod + ", ip=" + ip + ", url=" + url
                    + "]";
        }
    }
}