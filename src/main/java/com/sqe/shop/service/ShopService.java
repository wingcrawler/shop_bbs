package com.sqe.shop.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.ShopMapper;
import com.sqe.shop.mapper.UserMapper;
import com.sqe.shop.model.Shop;
import com.sqe.shop.model.User;
import com.sqe.shop.util.DateUtil;
import com.sqe.shop.util.PageUtil;

@Component  
public class ShopService extends AdapterService implements BaseService {
	
	@Autowired
    private ShopMapper shopMapper;
	@Autowired
    private UserMapper userMapper;
    
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
		Map<String, Object> parm = queryParm(shop);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		parm.put("orderby", "id desc");
		
		int count = shopMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Shop> list = new ArrayList<Shop>();
		if(count!=0){
			list = shopMapper.getBeanListByParm(parm);
			User user = null;
			for(Shop s : list){
				s.setCreateTimeStr(DateUtil.dateToString(s.getCreateTime(), DateUtil.DATETIME_FORMATE_2));
				user = userMapper.getById(s.getUserId());
				if(user!=null){
					s.setUserName(user.getUsername());	
				} else {
					s.setUserName("");
				}
				s.setStatusName(this.getStoreStatus(s.getShopStatus()));
			}
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Shop shop,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(shop);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		parm.put("orderby", "s.id desc");
		
		int count = shopMapper.getMapListByParm_count(parm);
		pageUtil.setTotalRecords(count);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = shopMapper.getMapListByParm(parm);
			for(Map<String, Object> s : list){
				Integer shopStatus = Integer.valueOf(s.get("shopStatus").toString());
				s.put("statusName", this.getStoreStatus(shopStatus));
			}
		}
		pageUtil.setList(list);		
		return pageUtil;
	}
	
	public void save(Shop shop) {
		if(shop.getShopRank()==null||shop.getShopRank()<0){
			shop.setShopRank(1);
		}
		if(shop.getId()!=null){
			shopMapper.update(shop);
		} else {
			if(shop.getShopLevel()==null){
				shop.setShopLevel(0);	
			}
			shop.setCreateTime(new Date());
			shopMapper.insert(shop);
		}
	}

	public List<Map<String, Object>> getListForExport(Shop shop) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		int pageNo=1;
		int pageSize=200;
		PageUtil<Map<String, Object>> page = new PageUtil<Map<String,Object>>(pageNo, pageSize);
		boolean flag=true;
		
		Map<String, Object> parm = queryParm(shop);
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		parm.put("orderby", "s.shop_rank asc, s.id desc");
		while (flag) {
			page = getMapListByParm(parm);
			if(page.getList().size()<pageSize){
				flag=false;
			}
			pageNo++;
			list.addAll(page.getList());
		}
		return list;
	}

	private PageUtil<Map<String, Object>> getMapListByParm( Map<String, Object> parm) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>();
		int count = shopMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = shopMapper.getMapListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}

	private Map<String, Object> queryParm(Shop shop) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(shop!=null){
			if(StringUtils.isNotBlank(shop.getShopTitle())){
				try {
					parm.put("shopTitle", URLDecoder.decode(shop.getShopTitle(), UTF8));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(shop.getShopStatus()!=null&&shop.getShopStatus()>=0){
				parm.put("shopStatus", shop.getShopStatus());
			}
			if(shop.getUserId()!=null){
				parm.put("userId", shop.getUserId());
			}
		}
		parm.put("orderby", "id desc");
		return parm;
	}
	
	// 获取当前用户shop
	public Shop getCurrentUserShop(Long userId) {
		Shop shop = new Shop();
		shop.setUserId(userId);
		PageUtil<Shop> shopPage = this.getBeanListByParm(shop, 0, -1);
		if(shopPage.getTotalRecords()==0){
			return null;
		}
		return shopPage.getList().get(0);
	}

	public boolean exitShop(Long id) {
		Shop shop = shopMapper.getShopByIdAndUserId(id, this.getCurrentUserId());
		if(shop!=null){
			return true;
		}
		return false;
	}
	
	public boolean userExitShop(Long userId) {
		int shop = shopMapper.getShopByUserId(userId);
		if(shop==0){
			return true;
		}
		return false;
	}

	public Shop getByUserId(Long currentUserId) { 
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("userId", currentUserId);
		List<Shop> list = shopMapper.getBeanListByParm(parm);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public Shop getByName(String shopName) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("shopTitle", shopName);
		List<Shop> list = shopMapper.getBeanListByParm(parm);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
