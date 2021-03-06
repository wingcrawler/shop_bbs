package com.sqe.shop.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class NotFoundInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(NotFoundInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    	if(modelAndView!=null){
    		int status = httpServletResponse.getStatus();
    		if(status==404){  
            	httpServletResponse.sendRedirect("/404");
            }else if(status==500||status==400){
            	httpServletResponse.sendRedirect("/500");
            }
    	}
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
