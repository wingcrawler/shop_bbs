package com.sqe.shop.service;

import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.common.Constants;
import com.sqe.shop.mapper.ShopMapper;
import com.sqe.shop.model.Shop;
import com.sqe.shop.util.DateUtil;
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
			if(StringUtils.isNotBlank(shop.getShopTitle())){
				parm.put("shopTitle", shop.getShopTitle());	
			}
			if(shop.getShopStatus()!=null && shop.getShopStatus()>=0){
				parm.put("shopStatus", shop.getShopStatus());	
			}
		}
		return shopMapper.countByParm(parm);
	}
	
	public PageUtil<Shop> getBeanListByParm(Shop shop, int pageNo, Integer pageSize) {
		PageUtil<Shop> pageUtil = new PageUtil<Shop>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		parm.put("orderby", "shop_rank asc");
		
		if(shop!=null){
			if(StringUtils.isNotBlank(shop.getShopTitle())){
				parm.put("shopTitle", shop.getShopTitle());	
			}
			if(shop.getShopStatus()!=null && shop.getShopStatus()>=0){
				parm.put("shopStatus", shop.getShopStatus());	
			}
		}
		
		int count = shopMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Shop> list = new ArrayList<Shop>();
		if(count!=0){
			list = shopMapper.getBeanListByParm(parm);
			for(Shop s : list){
				s.setCreateTimeStr(DateUtil.dateToString(s.getCreateTime(), DateUtil.DATETIME_FORMATE_2)); 
				s.setStatusName(Constants.getStoreStatus(s.getShopStatus()));
			}
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
		if(shop.getShopRank()==null||shop.getShopRank()<0){
			shop.setShopRank(1);
		}
		if(shop.getId()!=null){
			shopMapper.update(shop);
		} else {
			shop.setCreateTime(new Date());
			shopMapper.insert(shop);
		}
	}

}
