package com.sqe.shop.service;

import java.util.HashMap;
import java.util.ArrayList;
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
    QuestionsMapper questionsMapper;
    
    public int insert(Questions questions) {
		return questionsMapper.insert(questions);
	}
    
    public int update(Questions questions) {
		return questionsMapper.update(questions);
	}
	
	public int delete(Long id) {
		return questionsMapper.delete(id);
	}

	public Questions getById(Long id) {
		return questionsMapper.getById(id);
	}
	
	public int countByParm(Questions questions) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(questions!=null){
		
		}
		return questionsMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return questionsMapper.countByParm(parm);
	}
	
	public PageUtil<Questions> getBeanListByParm(Questions questions, int pageNo, Integer pageSize) {
		PageUtil<Questions> pageUtil = new PageUtil<Questions>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		if(questions!=null){
			parm.put("start", pageUtil.getStartRow());
			parm.put("limit", pageUtil.getPageSize());
		}
		int count = questionsMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Questions> list = new ArrayList<Questions>();
		if(count!=0){
			list = questionsMapper.getBeanListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Map<String, Object> parm,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		int count = questionsMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Map<String, Object>> list = questionsMapper.getMapListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	public void save(Questions questions) {
		if(questions.getId()!=null){
			questionsMapper.update(questions);
		} else {
			questionsMapper.insert(questions);
		}
	}

}
