package com.sqe.shop.application;

import com.sqe.shop.common.AdminAuthenticationInterceptor;
import com.sqe.shop.common.AuthenticationInterceptor;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

@Configuration
@EnableSpringDataWebSupport
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns(
                "/user/*","/order/*","/cart/*"
        ).excludePathPatterns("/user/login","/user/reg","/user/logout");

        registry.addInterceptor(new AdminAuthenticationInterceptor()).addPathPatterns(
                "/admin/*","/*/admin/*"
        ).excludePathPatterns("/admin/login","/admin/logout");
    }
/*
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // Add formatters and/or converters
        Formatter<Date> formatter = new Formatter<Date>() {
            @Override
            public Date parse(String text, Locale locale) throws ParseException {
                return null;
            }

            @Override
            public String print(Date object, Locale locale) {
                return null;
            }
        };
        registry.addFormatter(formatter);
    }*/

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        resolver.setMaxUploadSize(10240000);
        return resolver;
    }


}
