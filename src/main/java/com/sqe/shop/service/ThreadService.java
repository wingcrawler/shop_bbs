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
    ThreadMapper ThreadMapper;
    
    public int insert(Thread Thread) {
		return ThreadMapper.insert(Thread);
	}
    
    public int update(Thread Thread) {
		return ThreadMapper.update(Thread);
	}
	
	public int delete(Long id) {
		return ThreadMapper.delete(id);
	}

	public Thread getById(Long id) {
		return ThreadMapper.getById(id);
	}
	
	public int countByParm(Thread Thread) {
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(Thread!=null){
		
		}*/
		return ThreadMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return ThreadMapper.countByParm(parm);
	}
	
	public PageUtil<Thread> getBeanListByParm(Thread Thread, int pageNo, Integer pageSize) {
		PageUtil<Thread> pageUtil = new PageUtil<Thread>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(Thread!=null){
			
		}*/
		int count = ThreadMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Thread> list = ThreadMapper.getBeanListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Map<String, Object> parm,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		int count = ThreadMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Map<String, Object>> list = ThreadMapper.getMapListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}

}
