package com.sqe.shop.service;

import java.util.HashMap;
import java.util.ArrayList;
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
    ShopMapper shopMapper;
    
    public int insert(Shop shop) {
		return shopMapper.insert(shop);
	}
    
    public int update(Shop shop) {
		return shopMapper.update(shop);
	}
	
	public int delete(Long id) {
		return shopMapper.delete(id);
	}

	public Shop getById(Long id) {
		return shopMapper.getById(id);
	}
	
	public int countByParm(Shop shop) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(shop!=null){
		
		}
		return shopMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return shopMapper.countByParm(parm);
	}
	
	public PageUtil<Shop> getBeanListByParm(Shop shop, int pageNo, Integer pageSize) {
		PageUtil<Shop> pageUtil = new PageUtil<Shop>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		if(shop!=null){
			parm.put("start", pageUtil.getStartRow());
			parm.put("limit", pageUtil.getPageSize());
		}
		int count = shopMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Shop> list = new ArrayList<Shop>();
		if(count!=0){
			list = shopMapper.getBeanListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Map<String, Object> parm,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		int count = shopMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Map<String, Object>> list = shopMapper.getMapListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	public void save(Shop shop) {
		if(shop.getId()!=null){
			shopMapper.update(shop);
		} else {
			shopMapper.insert(shop);
		}
	}

}
