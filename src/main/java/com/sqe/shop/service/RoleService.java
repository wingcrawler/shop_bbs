package com.sqe.shop.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.RoleMapper;
import com.sqe.shop.model.Role;
import com.sqe.shop.util.PageUtil;

@Component  
public class RoleService extends AdapterService implements BaseService {
	
	@Autowired
    RoleMapper roleMapper;
    
    public int insert(Role role) {
		return roleMapper.insert(role);
	}
    
    public int update(Role role) {
		return roleMapper.update(role);
	}
	
	public int delete(Long id) {
		return roleMapper.delete(id);
	}

	public Role getById(Long id) {
		return roleMapper.getById(id);
	}
	
	public int countByParm(Role role) {
		Map<String, Object> parm = queryParm(role);
		return roleMapper.countByParm(parm);
	}
	
	public PageUtil<Role> getBeanListByParm(Role role, int pageNo, Integer pageSize) {
		PageUtil<Role> pageUtil = new PageUtil<Role>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(role);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		
		int count = roleMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Role> list = new ArrayList<Role>();
		if(count!=0){
			list = roleMapper.getBeanListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Role role,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(role);
		int count = roleMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = roleMapper.getMapListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public void save(Role role) {
		if(role.getId()!=null){
			roleMapper.update(role);
		} else {
			roleMapper.insert(role);
		}
	}
	
	private Map<String, Object> queryParm(Role role) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(role!=null){
			
			parm.put("orderby", "id desc" );
		}
		return parm;
	}

}
