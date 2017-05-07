package com.sqe.shop.common;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;

import com.sqe.shop.model.User;
import com.sqe.shop.util.PropertiesUtil;

public class BaseCommon{
	
	public static ResourceBundle bundle = null;
    static {
    }
    
    public static ResourceBundle getBundle() {
    	if(bundle == null){
        	String lang = PropertiesUtil.get("lang");
        	Locale locale = Locale.CHINA;
        	if(lang.equals("en")){
        		locale = Locale.US;
        	}
        	bundle = ResourceBundle.getBundle("i18n", locale);
        }
    	return bundle;
	}
    
    public static String getText(String key) {
    	if(bundle==null){
    		return Constants.UNKNOW_INFO;
    	}
    	return getBundle().getString(key);
	}

	public HashMap<String, Object> responseOK(String result){
		result = getText(result);
		return responseOK1(result);
	}
	
	public HashMap<String, Object> responseOK1(String result){
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		resMap.put(Constants.ERROR_NO, Constants.ERRORCODE_SUCCESS);
		resMap.put(Constants.ERROR_INFO, result);
		return resMap;
	}
	
	public HashMap<String, Object> responseError(Integer errorNo, String errorInfo){
		errorInfo = getText(errorInfo);
		return responseError1(errorNo, errorInfo);
	}
	
	public HashMap<String, Object> responseError1(Integer errorNo, String errorInfo){
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
