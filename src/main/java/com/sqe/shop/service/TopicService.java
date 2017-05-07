package com.sqe.shop.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
		Map<String, Object> parm = queryParm(topic);
		return topicMapper.countByParm(parm);
	}
	
	public PageUtil<Topic> getBeanListByParm(Topic topic, int pageNo, Integer pageSize) {
		PageUtil<Topic> pageUtil = new PageUtil<Topic>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(topic);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		
		int count = topicMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Topic> list = new ArrayList<Topic>();
		if(count!=0){
			list = topicMapper.getBeanListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Topic topic,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(topic);
		int count = topicMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = topicMapper.getMapListByParm(parm);
			for(Map<String, Object> map : list){
				String statusStr = map.get("topicStatus")==null?"0":map.get("topicStatus").toString();
				map.put("statusName", this.getTopicStatus(Integer.valueOf(statusStr)));
			}
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public void save(Topic topic) {
		if(topic.getId()!=null){
			topicMapper.update(topic);
		} else {
			topicMapper.insert(topic);
		}
	}
	
	private Map<String, Object> queryParm(Topic topic) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(topic!=null){
			if(StringUtils.isNotBlank(topic.getTopicTitle())){
				parm.put("topicTitle", topic.getTopicTitle());
			}
			if(topic.getSectionId()!=null&&topic.getSectionId()>=0){
				parm.put("sectionId", topic.getSectionId());	
			}
			parm.put("orderby", "id desc" );
		}
		return parm;
	}

}
