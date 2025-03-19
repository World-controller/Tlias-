package org.example.Interceptor;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        1.获取请求路径
        String s  = request.getRequestURI();
//        2.判断是否是登录请求，是就放行(即访问想要的url，也就是资源）
        if(s.contains("/login")){
            log.info("登录操作，放行！");
            return true;
        }
//        3.如果不是登录请求，获取请求头中token
        String token = request.getHeader("token");
//        4.判断token是否存在，存在则进行解析，不存在则响应401
        if(token == null || token.isEmpty()){
            log.info("令牌为空，响应401");
            response.setStatus(401);
            return false;
        }
        try {
            Claims claims = JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("令牌非法，响应401");
            response.setStatus(401);
            return false;
        }
//        5.解析成功则放行，失败则相应401
        return true;
    }
}
