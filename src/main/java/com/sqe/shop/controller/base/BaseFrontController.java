package com.sqe.shop.controller.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mysql.jdbc.log.Log;
import com.sqe.shop.common.BaseCommon;
import com.sqe.shop.common.Constants;
import com.sqe.shop.model.User;
import com.sqe.shop.service.LoginService;
import com.sqe.shop.service.MessageService;
import com.sqe.shop.service.ProductTypeService;
import com.sqe.shop.service.cached.CachedService;

public class BaseFrontController extends BaseCommon {
	
	protected HttpServletRequest request;  
    protected HttpServletResponse response; 
    
    @Autowired
    private MessageService messageService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private CachedService cachedService;
    @Autowired
    private LoginService loginService;
    
	@ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;
        this.response = response;
        //国际化内容
        request.setAttribute("t", cachedService.getBundle());
        //获取产品类别列表
        request.setAttribute("productTypeList", cachedService.getProductTypeList());
        //是否登陆
        User user = this.getCurrentUser();
        if(user==null){
        	loginService.autoLogin();
        }
        user = this.getCurrentUser();
        if(user==null){
        	request.setAttribute("isLogin", false);
        } else if(user!=null && user.getUserRole().equals(Constants.ROLE_SELL)){
        	request.setAttribute("isSellLogin", true);
        	request.setAttribute("isLogin", true);
        } else if(user!=null && user.getUserRole().equals(Constants.ROLE_BUY)){
        	request.setAttribute("isBuyLogin", true);
        	request.setAttribute("isLogin", true);
        	request.setAttribute("user", user);
        }
    }
	
}
