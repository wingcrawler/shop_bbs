package com.sqe.shop.common;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sqe.shop.model.User;

public class BaseController extends BaseCommon {
	
	protected HttpServletRequest request;  
    protected HttpServletResponse response; 
    
	@ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;
        this.response = response;
        request.setAttribute("t", this.getBundle());
    }
	
	public static String getSavedRequestUrl() {  
	    Subject subject = SecurityUtils.getSubject();  
	    Session session = subject.getSession(false);  
	    if (session != null) {  
	    	SavedRequest savedRequest = (SavedRequest) session.getAttribute("shiroSavedRequest");
	        if(savedRequest!=null){
	        	return savedRequest.getRequestUrl();
	        }
	    } 
	    return "";
	}  

	public User getCurrentUser() {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession(false);  
	    if (session != null) {
	    	User user =  (User) session.getAttribute("userInfo");
			if (user != null) {
				return user;
			}
		}
	    return null;
	}
	
	public Long getCurrentUserId() {
		User user = getCurrentUser();
		if(user != null){
			return user.getId();
		}
		return null;
	}
	
	public boolean isLogin() {
		if(getCurrentUser()!=null){
			return true;
		}
		return false;
	}
	
	public void logout() {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
	}
	
}
