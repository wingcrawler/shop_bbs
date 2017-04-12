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
public class ProductService extends AdapterService implements BaseService {

	@Autowired
    TProductMapper productMapper;
	
	public TProduct getById(Integer productId) {
		return super.getById("TProductMapper", productId);
	}
	
	public List<TProduct> getProductList() {
		Map<String, Object> parmMap = new HashMap<String, Object>();
		List<TProduct> list = super.getBeanListByParm("TProductMapper", parmMap);  
		return list;
	}

	public int insert(TProduct t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(TProduct t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
