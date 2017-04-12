/**
 * 
 */
package com.sqe.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import com.sqe.shop.mapper.TProductMapper;
import com.sqe.shop.model.TProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductService {

	@Autowired
    TProductMapper productMapper;
	
	public TProduct getById(Integer productId) {
		return productMapper.getById(productId);
	}
	
	public List<TProduct> getProductList() {
		Map<String, Object> parmMap = new HashMap<String, Object>();
		List<TProduct> list = productMapper.getBeanListByParm(parmMap);  
		return list;
	}

}
