package com.sqe.shop.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sqe.shop.model.Message;
import com.sqe.shop.model.User;
import com.sqe.shop.service.MessageService;

public class BaseController extends BaseCommon {
	
	protected HttpServletRequest request;  
    protected HttpServletResponse response; 
    
    @Autowired
    private MessageService messageService;
    
	@ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;
        this.response = response;
        request.setAttribute("t", this.getBundle());
        Message message = new Message();
        message.setReceiveId(-1L);
        message.setMessageStatus(0);
        int messageCount = messageService.countByParm(message);
        request.setAttribute("messageCount", messageCount);
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
