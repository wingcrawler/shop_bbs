package com.sqe.shop.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.AdminMapper;
import com.sqe.shop.model.Admin;
import com.sqe.shop.util.PageUtil;

@Component  
public class AdminService extends AdapterService implements BaseService {
	
	@Autowired
    AdminMapper adminMapper;
    
    public int insert(Admin admin) {
		return adminMapper.insert(admin);
	}
    
    public int update(Admin admin) {
		return adminMapper.update(admin);
	}
	
	public int delete(Long id) {
		return adminMapper.delete(id);
	}

	public Admin getById(Long id) {
		return adminMapper.getById(id);
	}
	
	public int countByParm(Admin admin) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(admin!=null){
		
		}
		return adminMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return adminMapper.countByParm(parm);
	}
	
	public PageUtil<Admin> getBeanListByParm(Admin admin, int pageNo, Integer pageSize) {
		PageUtil<Admin> pageUtil = new PageUtil<Admin>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		if(admin!=null){
			parm.put("start", pageUtil.getStartRow());
			parm.put("limit", pageUtil.getPageSize());
		}
		int count = adminMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Admin> list = new ArrayList<Admin>();
		if(count!=0){
			list = adminMapper.getBeanListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Map<String, Object> parm,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		int count = adminMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Map<String, Object>> list = adminMapper.getMapListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	public void save(Admin admin) {
		if(admin.getId()!=null){
			adminMapper.update(admin);
		} else {
			adminMapper.insert(admin);
		}
	}

}
