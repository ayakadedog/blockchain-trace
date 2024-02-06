package com.project.bookstore.WebConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    //资源映射
    private String filePath = "C:\\Users\\11540\\Desktop\\毕业设计材料\\源码\\后台\\bookstore\\src\\main\\resources\\static/upload/";
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/upload/**").addResourceLocations("file:"+filePath);
        System.out.println("静态资源获取");
    }
}