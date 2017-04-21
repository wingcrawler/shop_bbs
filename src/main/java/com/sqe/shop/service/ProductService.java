package com.sqe.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.ProductMapper;
import com.sqe.shop.model.Product;
import com.sqe.shop.util.PageUtil;

@Component  
public class ProductService extends AdapterService implements BaseService {
	
	@Autowired
    ProductMapper ProductMapper;
    
    public int insert(Product Product) {
		return ProductMapper.insert(Product);
	}
    
    public int update(Product Product) {
		return ProductMapper.update(Product);
	}
	
	public int delete(Long id) {
		return ProductMapper.delete(id);
	}

	public Product getById(Long id) {
		return ProductMapper.getById(id);
	}
	
	public int countByParm(Product Product) {
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(Product!=null){
		
		}*/
		return ProductMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return ProductMapper.countByParm(parm);
	}
	
	public PageUtil<Product> getBeanListByParm(Product Product, int pageNo, Integer pageSize) {
		PageUtil<Product> pageUtil = new PageUtil<Product>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(Product!=null){
			
		}*/
		int count = ProductMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Product> list = ProductMapper.getBeanListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Map<String, Object> parm,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		int count = ProductMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Map<String, Object>> list = ProductMapper.getMapListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}

}
