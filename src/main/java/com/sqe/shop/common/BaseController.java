package com.sqe.shop.common;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sqe.shop.model.User;

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
	
	public HashMap<String, Object> responseOK(String result){
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		resMap.put(Constants.ERROR_NO, Constants.ERRORCODE_SUCCESS);
		resMap.put(Constants.ERROR_INFO, result);
		return resMap;
	}
	public HashMap<String, Object> responseError(Integer errorNo, String errorInfo){
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		resMap.put(Constants.ERROR_NO, errorNo);
		resMap.put(Constants.ERROR_INFO, errorInfo);
		return resMap;
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
