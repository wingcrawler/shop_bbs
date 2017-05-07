package com.sqe.shop.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.UserThumbMapper;
import com.sqe.shop.model.UserThumb;
import com.sqe.shop.util.PageUtil;

@Component  
public class UserThumbService extends AdapterService implements BaseService {
	
	@Autowired
    UserThumbMapper userThumbMapper;
    
    public int insert(UserThumb userThumb) {
		return userThumbMapper.insert(userThumb);
	}
    
    public int update(UserThumb userThumb) {
		return userThumbMapper.update(userThumb);
	}
	
	public int delete(Long id) {
		return userThumbMapper.delete(id);
	}

	public UserThumb getById(Long id) {
		return userThumbMapper.getById(id);
	}
	
	public int countByParm(UserThumb userThumb) {
		Map<String, Object> parm = queryParm(userThumb);
		return userThumbMapper.countByParm(parm);
	}
	
	public PageUtil<UserThumb> getBeanListByParm(UserThumb userThumb, int pageNo, Integer pageSize) {
		PageUtil<UserThumb> pageUtil = new PageUtil<UserThumb>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(userThumb);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		
		int count = userThumbMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<UserThumb> list = new ArrayList<UserThumb>();
		if(count!=0){
			list = userThumbMapper.getBeanListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(UserThumb userThumb,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(userThumb);
		int count = userThumbMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = userThumbMapper.getMapListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public void save(UserThumb userThumb) {
		if(userThumb.getId()!=null){
			userThumbMapper.update(userThumb);
		} else {
			userThumbMapper.insert(userThumb);
		}
	}
	
	private Map<String, Object> queryParm(UserThumb userThumb) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(userThumb!=null){
			
			parm.put("orderby", "id desc" );
		}
		return parm;
	}

}
