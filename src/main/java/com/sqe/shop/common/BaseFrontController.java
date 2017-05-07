package com.sqe.shop.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

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
    
	@ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;
        this.response = response;
        //国际化内容
        request.setAttribute("t", this.getBundle());
        //获取产品类别列表
        request.setAttribute("productTypeList", cachedService.getProductTypeList());
    }
	
}
