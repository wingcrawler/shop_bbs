package com.sqe.shop.common;

import java.util.HashMap;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;

import com.sqe.shop.model.User;
import com.sqe.shop.service.cached.CachedService;

public class BaseCommon extends Constants{
	
	@Autowired
	private CachedService cachedService;
	
	public HashMap<String, Object> responseOK(String result){
		result = cachedService.getText(result);
		return responseOK1(result);
	}
	
	public HashMap<String, Object> responseOK1(String result){
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		resMap.put(Constants.ERROR_NO, Constants.ERRORCODE_SUCCESS);
		resMap.put(Constants.ERROR_INFO, result);
		return resMap;
	}
	
	public HashMap<String, Object> responseError(Integer errorNo, String errorInfo){
		errorInfo = cachedService.getText(errorInfo);
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
