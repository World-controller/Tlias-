package org.example.config;

import org.example.Interceptor.DemoInterceptor;
import org.example.Interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private DemoInterceptor demoInterceptor;
    @Autowired
    private TokenInterceptor tokenInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

//        //注册拦截器（自定义拦截器对象）,并设置拦截器拦截的请求路径
//        registry.addInterceptor(tokenInterceptor)
//                .addPathPatterns("/**");//需要拦截哪些资源   /*是一级路径   /**是多级路径
////                .excludePathPatterns("/login")//不需要拦截哪些资源
    }
}
