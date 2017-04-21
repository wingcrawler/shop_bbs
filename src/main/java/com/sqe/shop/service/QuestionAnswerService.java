package com.sqe.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.QuestionAnswerMapper;
import com.sqe.shop.model.QuestionAnswer;
import com.sqe.shop.util.PageUtil;

@Component  
public class QuestionAnswerService extends AdapterService implements BaseService {
	
	@Autowired
    QuestionAnswerMapper QuestionAnswerMapper;
    
    public int insert(QuestionAnswer QuestionAnswer) {
		return QuestionAnswerMapper.insert(QuestionAnswer);
	}
    
    public int update(QuestionAnswer QuestionAnswer) {
		return QuestionAnswerMapper.update(QuestionAnswer);
	}
	
	public int delete(Long id) {
		return QuestionAnswerMapper.delete(id);
	}

	public QuestionAnswer getById(Long id) {
		return QuestionAnswerMapper.getById(id);
	}
	
	public int countByParm(QuestionAnswer QuestionAnswer) {
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(QuestionAnswer!=null){
		
		}*/
		return QuestionAnswerMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return QuestionAnswerMapper.countByParm(parm);
	}
	
	public PageUtil<QuestionAnswer> getBeanListByParm(QuestionAnswer QuestionAnswer, int pageNo, Integer pageSize) {
		PageUtil<QuestionAnswer> pageUtil = new PageUtil<QuestionAnswer>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(QuestionAnswer!=null){
			
		}*/
		int count = QuestionAnswerMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<QuestionAnswer> list = QuestionAnswerMapper.getBeanListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Map<String, Object> parm,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		int count = QuestionAnswerMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Map<String, Object>> list = QuestionAnswerMapper.getMapListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}

}
