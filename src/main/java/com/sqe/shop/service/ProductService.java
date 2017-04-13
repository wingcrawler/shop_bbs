/**
 * 
 */
package com.sqe.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.ProductMapper;
import com.sqe.shop.model.Product;

@Component
public class ProductService extends AdapterService implements BaseService {

	@Autowired
    ProductMapper productMapper;
	
	public Product getById(Integer productId) {
		return super.getById("ProductMapper", productId);
	}
	
	public List<Product> getProductList() {
		Map<String, Object> parmMap = new HashMap<String, Object>();
		List<Product> list = super.getBeanListByParm("ProductMapper", parmMap);  
		return list;
	}

	public int insert(Product t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(Product t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
