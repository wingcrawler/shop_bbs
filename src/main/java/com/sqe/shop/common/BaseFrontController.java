package com.sqe.shop.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sqe.shop.model.ProductType;
import com.sqe.shop.service.MessageService;
import com.sqe.shop.service.ProductTypeService;
import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.PropertiesUtil;

public class BaseFrontController extends BaseCommon {
	
	protected HttpServletRequest request;  
    protected HttpServletResponse response; 
    private List<ProductType> list = null;
    
    @Autowired
    private MessageService messageService;
    @Autowired
    private ProductTypeService productTypeService;
    
	@ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;
        this.response = response;
        //国际化内容
        request.setAttribute("t", this.getBundle());
        //获取产品类别列表
        request.setAttribute("productTypeList", this.getProductTypeList());
    }
	
	/**
	 * 获取产品类别列表
	 * @return
	 */
	private List<ProductType> getProductTypeList(){
		if(list==null || list.isEmpty()){
			ProductType productType = new ProductType();
			PageUtil<ProductType> page = productTypeService.getBeanListByParm(productType, 1, -1);
			list = page.getList();
			if(!list.isEmpty()){
				//产品列表
		        String lang = PropertiesUtil.get("lang");
		        if(lang.equals("zh")){
		        	for(ProductType type : list){
		        		type.setTypeName(type.getTypeNameCh());
		        	}
		        }
			}
		}
		return list;
	}
	
}
