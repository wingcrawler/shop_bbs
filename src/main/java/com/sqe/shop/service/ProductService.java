package com.sqe.shop.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.common.Constants;
import com.sqe.shop.mapper.ProductMapper;
import com.sqe.shop.model.Product;
import com.sqe.shop.util.PageUtil;

@Component  
public class ProductService extends AdapterService implements BaseService {
	
	@Autowired
    ProductMapper productMapper;
	@Autowired
	Constants constants;
    
    public int insert(Product product) {
		return productMapper.insert(product);
	}
    
    public int update(Product product) {
		return productMapper.update(product);
	}
	
	public int delete(Long id) {
		return productMapper.delete(id);
	}

	public Product getById(Long id) {
		return productMapper.getById(id);
	}
	
	public int countByParm(Product product) {
		Map<String, Object> parm = queryParm(product);
		return productMapper.countByParm(parm);
	}
	
	public PageUtil<Product> getBeanListByParm(Product product, int pageNo, Integer pageSize) {
		PageUtil<Product> pageUtil = new PageUtil<Product>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(product);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		
		int count = productMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Product> list = new ArrayList<Product>();
		if(count!=0){
			list = productMapper.getBeanListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Product product,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(product);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		parm.put("orderby", "p.product_rank asc, p.id desc" );
		
		int count = productMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = productMapper.getMapListByParm(parm);
			for(Map<String, Object> map : list){
				String statusStr = map.get("productStatus")==null?"0":map.get("productStatus").toString();
				map.put("productStatusStr", constants.getProductStatus(Integer.valueOf(statusStr)));	
			}
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public void save(Product product) {
		if(product.getId()!=null){
			productMapper.update(product);
		} else {
			productMapper.insert(product);
		}
	}
	
	private Map<String, Object> queryParm(Product product) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(product!=null){
			if(product.getProductStatus()!=null && product.getProductStatus()>=0){
				parm.put("productStatus", product.getProductStatus());	
			}
			if(product.getShopId()!=null && product.getShopId()>=0){
				parm.put("shopId", product.getShopId());	
			}
			if(StringUtils.isNotBlank(product.getProductName())){
				parm.put("productName", product.getProductName());	
			}
			parm.put("orderby", "product_rank asc" );
		}
		return parm;
	}

	public PageUtil<Map<String, Object>> getHotProductList(Product product, int pageNo, int pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(product);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		parm.put("orderby", "p.product_view desc, p.id desc" );
		
		int count = productMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = productMapper.getHotProductList(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}

}
