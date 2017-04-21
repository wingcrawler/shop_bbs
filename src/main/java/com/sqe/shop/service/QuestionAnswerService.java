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
    QuestionAnswerMapper questionAnswerMapper;
    
    public int insert(QuestionAnswer QuestionAnswer) {
		return questionAnswerMapper.insert(QuestionAnswer);
	}
    
    public int update(QuestionAnswer QuestionAnswer) {
		return questionAnswerMapper.update(QuestionAnswer);
	}
	
	public int delete(Long id) {
		return questionAnswerMapper.delete(id);
	}

	public QuestionAnswer getById(Long id) {
		return questionAnswerMapper.getById(id);
	}
	
	public int countByParm(QuestionAnswer QuestionAnswer) {
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(QuestionAnswer!=null){
		
		}*/
		return questionAnswerMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return questionAnswerMapper.countByParm(parm);
	}
	
	public PageUtil<QuestionAnswer> getBeanListByParm(QuestionAnswer QuestionAnswer, int pageNo, Integer pageSize) {
		PageUtil<QuestionAnswer> pageUtil = new PageUtil<QuestionAnswer>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(QuestionAnswer!=null){
			
		}*/
		int count = questionAnswerMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<QuestionAnswer> list = questionAnswerMapper.getBeanListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Map<String, Object> parm,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		int count = questionAnswerMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Map<String, Object>> list = questionAnswerMapper.getMapListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}

}
