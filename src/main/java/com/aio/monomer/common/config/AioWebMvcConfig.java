package com.aio.monomer.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

/**
 * @description: TODO
 * @author: Mr.chen
 * @date: 2020/3/27 17:20
 * @version: 1.0
 */
@Configuration
public class AioWebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static-aioweb/**").addResourceLocations("classpath:/static-aioweb/");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        thymeleafViewResolver.setViewClass(AioThymeleafViewClass.class);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }
}
