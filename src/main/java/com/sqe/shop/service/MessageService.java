package com.sqe.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.MessageMapper;
import com.sqe.shop.model.Message;
import com.sqe.shop.util.PageUtil;

@Component  
public class MessageService extends AdapterService implements BaseService {
	
	@Autowired
    MessageMapper messageMapper;
    
    public int insert(Message Message) {
		return messageMapper.insert(Message);
	}
    
    public int update(Message Message) {
		return messageMapper.update(Message);
	}
	
	public int delete(Long id) {
		return messageMapper.delete(id);
	}

	public Message getById(Long id) {
		return messageMapper.getById(id);
	}
	
	public int countByParm(Message Message) {
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(Message!=null){
		
		}*/
		return messageMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return messageMapper.countByParm(parm);
	}
	
	public PageUtil<Message> getBeanListByParm(Message Message, int pageNo, Integer pageSize) {
		PageUtil<Message> pageUtil = new PageUtil<Message>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(Message!=null){
			
		}*/
		int count = messageMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Message> list = messageMapper.getBeanListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Map<String, Object> parm,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		int count = messageMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Map<String, Object>> list = messageMapper.getMapListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}

}
