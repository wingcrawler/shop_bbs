package com.sqe.shop.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.UserMapper;
import com.sqe.shop.model.User;
import com.sqe.shop.util.PageUtil;

@Component  
public class UserService extends AdapterService implements BaseService {
	
	@Autowired
    UserMapper userMapper;
    
    public int insert(User user) {
		return userMapper.insert(user);
	}
    
    public int update(User user) {
		return userMapper.update(user);
	}
	
	public int delete(Long id) {
		return userMapper.delete(id);
	}

	public User getById(Long id) {
		return userMapper.getById(id);
	}
	
	public int countByParm(User user) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(user!=null){
		
		}
		return userMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return userMapper.countByParm(parm);
	}
	
	public PageUtil<User> getBeanListByParm(User user, int pageNo, Integer pageSize) {
		PageUtil<User> pageUtil = new PageUtil<User>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		if(user!=null){
			parm.put("start", pageUtil.getStartRow());
			parm.put("limit", pageUtil.getPageSize());
		}
		int count = userMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<User> list = new ArrayList<User>();
		if(count!=0){
			list = userMapper.getBeanListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Map<String, Object> parm,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		int count = userMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Map<String, Object>> list = userMapper.getMapListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	public void save(User user) {
		if(user.getId()!=null){
			userMapper.update(user);
		} else {
			userMapper.insert(user);
		}
	}

}
