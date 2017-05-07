package com.sqe.shop.service.cached;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.model.ProductType;
import com.sqe.shop.service.ProductTypeService;
import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.PropertiesUtil;

/**
 * 缓存
 * @author Administrator
 *
 */
@Component
public class CachedService {
	
	@Autowired
    private ProductTypeService productTypeService;
	
	private Map<ProductType, List<ProductType>> productTypeList = null;//产品类别： 一级类别为key，二级类别列表为value
	
	/**
	 * 获取产品类别列表
	 * @return
	 */
	public Map<ProductType, List<ProductType>> getProductTypeList(){
		if(productTypeList==null){
			productTypeList = new HashMap<ProductType, List<ProductType>>();
		}
		if(productTypeList.isEmpty()){
			//一级分类
			ProductType productType = new ProductType();
			productType.setTypeLevel(1);
			PageUtil<ProductType> page = productTypeService.getBeanListByParm(productType, 1, -1);
			if(page.getTotalRecords()==0){
				return null;
			}
			//产品列表
	        String lang = PropertiesUtil.get("lang");
	        if(lang.equals("zh")){
	        	for(ProductType type : page.getList()){
	        		type.setTypeName(type.getTypeNameCh());
	        	}
	        }
			
			//二级分类
			productType.setTypeLevel(2);
			PageUtil<ProductType> page2 = null;
			for(ProductType type : page.getList()){
				productType.setParentId(type.getId());
				page2 = productTypeService.getBeanListByParm(productType, 1, -1);
				if(lang.equals("zh")){
		        	for(ProductType type2 : page2.getList()){
		        		type2.setTypeName(type2.getTypeNameCh());
		        	}
		        }
				productTypeList.put(type, page2.getList());
        	}
		}
		return productTypeList;
	}
	/**
	 * 刷新productTypeList
	 */
	public void refreshProductTypeList(){
		productTypeList = null;
		getProductTypeList();
	}
	
}
