package com.sqe.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.TopicMapper;
import com.sqe.shop.model.Topic;
import com.sqe.shop.util.PageUtil;

@Component  
public class TopicService extends AdapterService implements BaseService {
	
	@Autowired
    TopicMapper topicMapper;
    
    public int insert(Topic Topic) {
		return topicMapper.insert(Topic);
	}
    
    public int update(Topic Topic) {
		return topicMapper.update(Topic);
	}
	
	public int delete(Long id) {
		return topicMapper.delete(id);
	}

	public Topic getById(Long id) {
		return topicMapper.getById(id);
	}
	
	public int countByParm(Topic Topic) {
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(Topic!=null){
		
		}*/
		return topicMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return topicMapper.countByParm(parm);
	}
	
	public PageUtil<Topic> getBeanListByParm(Topic Topic, int pageNo, Integer pageSize) {
		PageUtil<Topic> pageUtil = new PageUtil<Topic>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(Topic!=null){
			
		}*/
		int count = topicMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Topic> list = topicMapper.getBeanListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Map<String, Object> parm,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		int count = topicMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Map<String, Object>> list = topicMapper.getMapListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}

}
