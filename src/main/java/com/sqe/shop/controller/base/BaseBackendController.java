package com.sqe.shop.controller.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sqe.shop.common.BaseCommon;
import com.sqe.shop.model.Message;
import com.sqe.shop.service.MessageService;
import com.sqe.shop.service.cached.CachedService;
import com.sqe.shop.util.PropertiesUtil;

public class BaseBackendController extends BaseCommon {
	
	protected HttpServletRequest request;  
    protected HttpServletResponse response; 
    
    @Autowired
    private MessageService messageService;
    @Autowired
    private CachedService cachedService;
    
	@ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;
        this.response = response;
        request.setAttribute("t", cachedService.getBundle());
        request.setAttribute("lang", PropertiesUtil.get("lang"));
        
        //后台管理页面查询未读私信
        Message message = new Message();
        message.setReceiveId(-1L);
        message.setMessageStatus(0);
        int messageCount = messageService.countByParm(message);
        request.setAttribute("messageCount", messageCount);
    }
	
	
	
}
