package com.sqe.shop.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sqe.shop.model.Message;
import com.sqe.shop.service.MessageService;

public class BaseBackendController extends BaseCommon {
	
	protected HttpServletRequest request;  
    protected HttpServletResponse response; 
    
    @Autowired
    private MessageService messageService;
    
	@ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;
        this.response = response;
        request.setAttribute("t", this.getBundle());
        
        //后台管理页面查询未读私信
        Message message = new Message();
        message.setReceiveId(-1L);
        message.setMessageStatus(0);
        int messageCount = messageService.countByParm(message);
        request.setAttribute("messageCount", messageCount);
    }
	
	
	
}
