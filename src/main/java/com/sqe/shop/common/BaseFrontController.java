package com.sqe.shop.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sqe.shop.service.MessageService;

public class BaseFrontController extends BaseCommon {
	
	protected HttpServletRequest request;  
    protected HttpServletResponse response; 
    
    @Autowired
    private MessageService messageService;
    
	@ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;
        this.response = response;
        request.setAttribute("t", this.getBundle());
    }
	
}
