package com.sqe.shop.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.UserMapper;
import com.sqe.shop.model.User;
import com.sqe.shop.util.DateUtil;
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
		Map<String, Object> parm = queryParm(user);
		return userMapper.countByParm(parm);
	}
	public int batchInsert(List<User> userList) {
		int k=0;
		int count=0;
		int size=userList.size();
		while(count+100 < size){
			List<User> sub=userList.subList(count, count+100);
			k = k+userMapper.insertUserBatch(sub);
			count=+100;
		}
		List<User> sublast=userList.subList(count-100, size);
		k=k+userMapper.insertUserBatch(sublast);
		return k;

	}
	
	public PageUtil<User> getBeanListByParm(User user, int pageNo, Integer pageSize) {
		PageUtil<User> pageUtil = new PageUtil<User>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(user);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		
		int count = userMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<User> list = new ArrayList<User>();
		if(count!=0){
			list = userMapper.getBeanListByParm(parm);
			for(User u : list){
				u.setStatusName(this.getUserStatus(u.getUserStatus()));
				u.setCreateTimeStr(DateUtil.dateToString(u.getCreateTime(), DateUtil.DATETIME_FORMATE_2));
			}
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(User user,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(user);
		int count = userMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = userMapper.getMapListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public void save(User user) {
		if(user.getId()!=null){
			userMapper.update(user);
		} else {
			userMapper.insert(user);
		}
	}
	
	private Map<String, Object> queryParm(User user) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(user!=null){
			if(StringUtils.isNotBlank(user.getUsername())){
				try {
					parm.put("username", URLDecoder.decode(user.getUsername(), UTF8));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			
			if(StringUtils.isNotBlank(user.getUserPhone())){
				parm.put("userPhone", user.getUserPhone());	
			}
			
			if(user.getUserRole()!=null){
				parm.put("userRole", user.getUserRole());
			}
			
			if(user.getUserStatus()!=null){
				parm.put("userStatus", user.getUserStatus());
			}
			
		}
		parm.put("orderby", "id desc" );
		return parm;
	}

	public void onOroffUser(Long userId, Integer status) {
		User user = new User();
		user.setId(userId);
		user.setUserStatus(status);
		userMapper.update(user);
	}

	public List<User> getListForExport(User user) {
		int pageNo=1;
		int pageSize=200;
		boolean flag=true;
		
		List<User> list = new ArrayList<User>();
		PageUtil<User> page = new PageUtil<User>(pageNo, pageSize);
		while (flag) {
			page = getBeanListByParm(user, pageNo, pageSize);
			if(page.getList().size()<pageSize){
				flag=false;
			}
			pageNo++;
			list.addAll(page.getList());
		}
		return list;
	}

	public User findByName(String loginName) {
		return  userMapper.findByName(loginName);
	}

	public User findUserByUsernameAndPassword(User user) {
		return  userMapper.findUserByUsernameAndPassword(user);
	}

	public User findOwnerUser(String ownerName) {
		return  userMapper.findOwnerUser(ownerName);
	}

	public List<User> findOnlyByName(String username) {
		return  userMapper.findOnlyByName(username);
	}
}
