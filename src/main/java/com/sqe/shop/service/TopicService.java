package com.sqe.shop.service;

import java.util.HashMap;
import java.util.ArrayList;
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
    
    public int insert(Topic topic) {
		return topicMapper.insert(topic);
	}
    
    public int update(Topic topic) {
		return topicMapper.update(topic);
	}
	
	public int delete(Long id) {
		return topicMapper.delete(id);
	}

	public Topic getById(Long id) {
		return topicMapper.getById(id);
	}
	
	public int countByParm(Topic topic) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(topic!=null){
		
		}
		return topicMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return topicMapper.countByParm(parm);
	}
	
	public PageUtil<Topic> getBeanListByParm(Topic topic, int pageNo, Integer pageSize) {
		PageUtil<Topic> pageUtil = new PageUtil<Topic>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		if(topic!=null){
			parm.put("start", pageUtil.getStartRow());
			parm.put("limit", pageUtil.getPageSize());
		}
		int count = topicMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Topic> list = new ArrayList<Topic>();
		if(count!=0){
			list = topicMapper.getBeanListByParm(parm);
		}
		pageUtil.setList(list);
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
	
	public void save(Topic topic) {
		if(topic.getId()!=null){
			topicMapper.update(topic);
		} else {
			topicMapper.insert(topic);
		}
	}

}
