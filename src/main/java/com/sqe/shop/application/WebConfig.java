package com.sqe.shop.application;

import javax.servlet.MultipartConfigElement;

import com.sqe.shop.interceptor.AdminAuthenticationInterceptor;
import com.sqe.shop.interceptor.AuthenticationInterceptor;
import com.sqe.shop.interceptor.NotFoundInterceptor;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.web.filter.OrderedCharacterEncodingFilter;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableSpringDataWebSupport
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new NotFoundInterceptor())
        	.addPathPatterns("/**");

        /*registry.addInterceptor(new AdminAuthenticationInterceptor())
        	.addPathPatterns("/backend1/**","/backend1/**")
        	.excludePathPatterns("/backend/login","/backend/logout");*/
        
        /*super.addInterceptors(registry);*/
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

   /* @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        resolver.setMaxUploadSize(10240000);
        return resolver;
    }*/
    
    @Bean
	@ConditionalOnMissingBean(CharacterEncodingFilter.class)
	public CharacterEncodingFilter characterEncodingFilter() {
		CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
		filter.setEncoding("utf-8");
		filter.setForceRequestEncoding(true);
		filter.setForceResponseEncoding(true);
		return filter;
	}
    
}
