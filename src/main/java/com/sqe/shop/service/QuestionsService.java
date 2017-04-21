package com.sqe.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.QuestionsMapper;
import com.sqe.shop.model.Questions;
import com.sqe.shop.util.PageUtil;

@Component  
public class QuestionsService extends AdapterService implements BaseService {
	
	@Autowired
    QuestionsMapper QuestionsMapper;
    
    public int insert(Questions Questions) {
		return QuestionsMapper.insert(Questions);
	}
    
    public int update(Questions Questions) {
		return QuestionsMapper.update(Questions);
	}
	
	public int delete(Long id) {
		return QuestionsMapper.delete(id);
	}

	public Questions getById(Long id) {
		return QuestionsMapper.getById(id);
	}
	
	public int countByParm(Questions Questions) {
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(Questions!=null){
		
		}*/
		return QuestionsMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return QuestionsMapper.countByParm(parm);
	}
	
	public PageUtil<Questions> getBeanListByParm(Questions Questions, int pageNo, Integer pageSize) {
		PageUtil<Questions> pageUtil = new PageUtil<Questions>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(Questions!=null){
			
		}*/
		int count = QuestionsMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Questions> list = QuestionsMapper.getBeanListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Map<String, Object> parm,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		int count = QuestionsMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Map<String, Object>> list = QuestionsMapper.getMapListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}

}
