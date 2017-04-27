package com.sqe.shop.service;

import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.common.Constants;
import com.sqe.shop.mapper.UserOrderMapper;
import com.sqe.shop.model.UserOrder;
import com.sqe.shop.util.DateUtil;
import com.sqe.shop.util.PageUtil;

@Component  
public class UserOrderService extends AdapterService implements BaseService {
	
	@Autowired
    UserOrderMapper userOrderMapper;
    
    public int insert(UserOrder userOrder) {
		return userOrderMapper.insert(userOrder);
	}
    
    public int update(UserOrder userOrder) {
		return userOrderMapper.update(userOrder);
	}
	
	public int delete(Long id) {
		return userOrderMapper.delete(id);
	}

	public UserOrder getById(Long id) {
		return userOrderMapper.getById(id);
	}
	
	public int countByParm(UserOrder userOrder) {
		Map<String, Object> parm = queryParm(userOrder);
		return userOrderMapper.countByParm(parm);
	}
	
	public PageUtil<UserOrder> getBeanListByParm(UserOrder userOrder, int pageNo, Integer pageSize) {
		PageUtil<UserOrder> pageUtil = new PageUtil<UserOrder>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(userOrder);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		
		int count = userOrderMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<UserOrder> list = new ArrayList<UserOrder>();
		if(count!=0){
			list = userOrderMapper.getBeanListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(UserOrder userOrder,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(userOrder);
		parm.put("orderby", "o.id desc" );
		int count = userOrderMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = userOrderMapper.getMapListByParm(parm);
			for(Map<String, Object> map : list){
				Date date = (Date) map.get("date");
				map.put("createTimeStr", DateUtil.dateToString(date, DateUtil.DATETIME_FORMATE_2));
				String statusStr = map.get("orderStatus").toString();
				map.put("statusName", Constants.getOrderType(Integer.valueOf(statusStr)));
			}
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public void save(UserOrder userOrder) {
		if(userOrder.getId()!=null){
			userOrderMapper.update(userOrder);
		} else {
			userOrderMapper.insert(userOrder);
		}
	}
	
	private Map<String, Object> queryParm(UserOrder userOrder) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(userOrder!=null){
			if(userOrder.getOrderStatus()!=null && userOrder.getOrderStatus()>=0){
				parm.put("orderStatus", userOrder.getOrderStatus());	
			}
			if(userOrder.getUserId()!=null){
				parm.put("userId", userOrder.getUserId());
			}
		}
		parm.put("orderby", "id desc" );
		return parm;
	}

	public Map<String, Object> getMapById(Long id) {
		return userOrderMapper.getMapById(id);
	}

}
