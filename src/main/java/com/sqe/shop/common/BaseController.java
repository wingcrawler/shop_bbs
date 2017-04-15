package com.sqe.shop.common;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController {
	
	protected HttpServletRequest request;  
    protected HttpServletResponse response; 
    
    public static ResourceBundle bundle = null;
    static {
    	
    }
	
	@ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;
        this.response = response;
        
        if(bundle == null){
        	Locale locale = request.getLocale();
        	//Locale locale = Locale.CHINA;
        	//Locale locale = Locale.US;
        	bundle = ResourceBundle.getBundle("i18n", locale);
        }
        request.setAttribute("t", bundle);
    }
	
}
