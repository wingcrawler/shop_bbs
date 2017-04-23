package com.sqe.shop.service;

import java.util.HashMap;
import java.util.ArrayList;
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
    
    public int insert(Thread thread) {
		return threadMapper.insert(thread);
	}
    
    public int update(Thread thread) {
		return threadMapper.update(thread);
	}
	
	public int delete(Long id) {
		return threadMapper.delete(id);
	}

	public Thread getById(Long id) {
		return threadMapper.getById(id);
	}
	
	public int countByParm(Thread thread) {
		Map<String, Object> parm = queryParm(thread);
		return threadMapper.countByParm(parm);
	}
	
	public PageUtil<Thread> getBeanListByParm(Thread thread, int pageNo, Integer pageSize) {
		PageUtil<Thread> pageUtil = new PageUtil<Thread>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(thread);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		
		int count = threadMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Thread> list = new ArrayList<Thread>();
		if(count!=0){
			list = threadMapper.getBeanListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Thread thread,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(thread);
		int count = threadMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = threadMapper.getMapListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public void save(Thread thread) {
		if(thread.getId()!=null){
			threadMapper.update(thread);
		} else {
			threadMapper.insert(thread);
		}
	}
	
	private Map<String, Object> queryParm(Thread thread) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(thread!=null){
			
			parm.put("orderby", "id desc" );
		}
		return parm;
	}

}
