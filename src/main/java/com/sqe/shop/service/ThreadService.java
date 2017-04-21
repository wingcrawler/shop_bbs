package com.sqe.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.ThreadMapper;
import com.sqe.shop.model.Thread;
import com.sqe.shop.util.PageUtil;

@Component  
public class ThreadService extends AdapterService implements BaseService {
	
	@Autowired
    ThreadMapper threadMapper;
    
    public int insert(Thread Thread) {
		return threadMapper.insert(Thread);
	}
    
    public int update(Thread Thread) {
		return threadMapper.update(Thread);
	}
	
	public int delete(Long id) {
		return threadMapper.delete(id);
	}

	public Thread getById(Long id) {
		return threadMapper.getById(id);
	}
	
	public int countByParm(Thread Thread) {
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(Thread!=null){
		
		}*/
		return threadMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return threadMapper.countByParm(parm);
	}
	
	public PageUtil<Thread> getBeanListByParm(Thread Thread, int pageNo, Integer pageSize) {
		PageUtil<Thread> pageUtil = new PageUtil<Thread>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(Thread!=null){
			
		}*/
		int count = threadMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Thread> list = threadMapper.getBeanListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Map<String, Object> parm,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		int count = threadMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Map<String, Object>> list = threadMapper.getMapListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}

}
