package com.sqe.shop.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.ProductTypeMapper;
import com.sqe.shop.model.ProductType;
import com.sqe.shop.service.cached.CachedService;
import com.sqe.shop.util.PageUtil;

@Component  
public class ProductTypeService extends AdapterService implements BaseService {
	
	@Autowired
    ProductTypeMapper productTypeMapper;
	@Autowired
	private CachedService cachedService;
    
    public int insert(ProductType productType) {
		int count = productTypeMapper.insert(productType);
		cachedService.refreshProductTypeList();
		return count;
	}
    
    public int update(ProductType productType) {
    	cachedService.refreshProductTypeList();
		int count = productTypeMapper.update(productType);
		return count;
	}
	
	public int delete(Long id) {
		return productTypeMapper.delete(id);
	}

	public ProductType getById(Long id) {
		return productTypeMapper.getById(id);
	}
	
	public int countByParm(ProductType productType) {
		Map<String, Object> parm = queryParm(productType);
		return productTypeMapper.countByParm(parm);
	}
	
	public PageUtil<ProductType> getBeanListByParm(ProductType productType, int pageNo, Integer pageSize) {
		PageUtil<ProductType> pageUtil = new PageUtil<ProductType>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(productType);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());	
		parm.put("orderby", "type_rank asc");

		int count = productTypeMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<ProductType> list = new ArrayList<ProductType>();
		if(count!=0){
			list = productTypeMapper.getBeanListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Map<String, Object> parm,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		int count = productTypeMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Map<String, Object>> list = productTypeMapper.getMapListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	public void save(ProductType productType) {
		if(productType.getId()!=null){
			this.update(productType);
		} else {
			this.insert(productType);
		}
	}
	
	private Map<String, Object> queryParm(ProductType productType) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(productType!=null){
			if(StringUtils.isNotBlank(productType.getTypeName())){
				try {
					parm.put("typeName", URLDecoder.decode(productType.getTypeName(),"utf-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			if(productType.getTypeLevel()!=null && productType.getTypeLevel()>0){
				parm.put("typeLevel", productType.getTypeLevel());
			}
			if(productType.getParentId()!=null && productType.getParentId()>=0){
				parm.put("parentId", productType.getParentId());
			}
		}
		parm.put("orderby", "id asc" );
		return parm;
	}

	
}
