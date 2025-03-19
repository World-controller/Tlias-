package org.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*") //设置过滤的路径
public class AbcFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("filter init初始化....");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("拦截到请求....放行前");
        //拦截到请求后得放行，如果不放行，过滤器只会实现拦截，而无法对相应的资源进行访问
        chain.doFilter(request,response);
        log.info("拦截到请求....   放行后");
    }

    @Override
    public void destroy() {
       log.info("filter destoryed...");
    }
}
