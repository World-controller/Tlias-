package org.example.filter;


import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.utils.CurrentHolder;
import org.example.utils.JwtUtils;

import java.io.IOException;

@Slf4j
@WebFilter("/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse hsrs = (HttpServletResponse) response;
        HttpServletRequest hsrq = (HttpServletRequest) request;
//        1.获取请求路径
        String s  = hsrq.getRequestURI();
//        2.判断是否是登录请求，是就放行(即访问想要的url，也就是资源）
        if(s.contains("/login")){
            log.info("登录操作，放行！");
            chain.doFilter(request,response);
            return;
        }
//        3.如果不是登录请求，获取请求头中token
        String token = hsrq.getHeader("token");
//        4.判断token是否存在，存在则进行解析，不存在则响应401
        if(token == null || token ==""){
            log.info("令牌为空，响应401");
            hsrs.setStatus(401);
        }
        try {
            Claims claims = JwtUtils.parseJWT(token);
            Integer currentLoginEmpId = Integer.parseInt(claims.get("id").toString());
            CurrentHolder.setCurrentId(currentLoginEmpId);
        } catch (Exception e) {
            log.info("令牌非法，响应401");
            hsrs.setStatus(401);
            return;
        }
//        5.解析成功则放行，失败则相应401
        chain.doFilter(request,response);
        CurrentHolder.remove();//登录完成并且aop程序结束后返回过滤器时再将id删掉，保证安全性  ThreadLocal
    }
}
