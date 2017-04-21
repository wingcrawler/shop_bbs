package com.sqe.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.ShopMapper;
import com.sqe.shop.model.Shop;
import com.sqe.shop.util.PageUtil;

@Component  
public class ShopService extends AdapterService implements BaseService {
	
	@Autowired
    ShopMapper ShopMapper;
    
    public int insert(Shop Shop) {
		return ShopMapper.insert(Shop);
	}
    
    public int update(Shop Shop) {
		return ShopMapper.update(Shop);
	}
	
	public int delete(Long id) {
		return ShopMapper.delete(id);
	}

	public Shop getById(Long id) {
		return ShopMapper.getById(id);
	}
	
	public int countByParm(Shop Shop) {
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(Shop!=null){
		
		}*/
		return ShopMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return ShopMapper.countByParm(parm);
	}
	
	public PageUtil<Shop> getBeanListByParm(Shop Shop, int pageNo, Integer pageSize) {
		PageUtil<Shop> pageUtil = new PageUtil<Shop>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(Shop!=null){
			
		}*/
		int count = ShopMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Shop> list = ShopMapper.getBeanListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Map<String, Object> parm,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		int count = ShopMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Map<String, Object>> list = ShopMapper.getMapListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}

}
