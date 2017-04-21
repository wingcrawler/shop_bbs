package com.sqe.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.ProductTypeMapper;
import com.sqe.shop.model.ProductType;
import com.sqe.shop.util.PageUtil;

@Component  
public class ProductTypeService extends AdapterService implements BaseService {
	
	@Autowired
    ProductTypeMapper ProductTypeMapper;
    
    public int insert(ProductType ProductType) {
		return ProductTypeMapper.insert(ProductType);
	}
    
    public int update(ProductType ProductType) {
		return ProductTypeMapper.update(ProductType);
	}
	
	public int delete(Long id) {
		return ProductTypeMapper.delete(id);
	}

	public ProductType getById(Long id) {
		return ProductTypeMapper.getById(id);
	}
	
	public int countByParm(ProductType ProductType) {
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(ProductType!=null){
		
		}*/
		return ProductTypeMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return ProductTypeMapper.countByParm(parm);
	}
	
	public PageUtil<ProductType> getBeanListByParm(ProductType ProductType, int pageNo, Integer pageSize) {
		PageUtil<ProductType> pageUtil = new PageUtil<ProductType>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(ProductType!=null){
			
		}*/
		int count = ProductTypeMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<ProductType> list = ProductTypeMapper.getBeanListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Map<String, Object> parm,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		int count = ProductTypeMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Map<String, Object>> list = ProductTypeMapper.getMapListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}

}
