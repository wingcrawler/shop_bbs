package com.sqe.shop.service;

import java.util.HashMap;
import java.util.ArrayList;
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
    
    public int insert(QuestionAnswer questionAnswer) {
		return questionAnswerMapper.insert(questionAnswer);
	}
    
    public int update(QuestionAnswer questionAnswer) {
		return questionAnswerMapper.update(questionAnswer);
	}
	
	public int delete(Long id) {
		return questionAnswerMapper.delete(id);
	}

	public QuestionAnswer getById(Long id) {
		return questionAnswerMapper.getById(id);
	}
	
	public int countByParm(QuestionAnswer questionAnswer) {
		Map<String, Object> parm = queryParm(questionAnswer);
		return questionAnswerMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return questionAnswerMapper.countByParm(parm);
	}
	
	public PageUtil<QuestionAnswer> getBeanListByParm(QuestionAnswer questionAnswer, int pageNo, Integer pageSize) {
		PageUtil<QuestionAnswer> pageUtil = new PageUtil<QuestionAnswer>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(questionAnswer);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());

		int count = questionAnswerMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		
		List<QuestionAnswer> list = new ArrayList<QuestionAnswer>();
		if(count!=0){
			list = questionAnswerMapper.getBeanListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(QuestionAnswer questionAnswer,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(questionAnswer);
		
		int count = questionAnswerMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = questionAnswerMapper.getMapListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public void save(QuestionAnswer questionAnswer) {
		if(questionAnswer.getId()!=null){
			questionAnswerMapper.update(questionAnswer);
		} else {
			questionAnswerMapper.insert(questionAnswer);
		}
	}
	
	private Map<String, Object> queryParm(QuestionAnswer questionAnswer) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(questionAnswer!=null){
			
		}
		parm.put("orderby", "id desc" );
		return parm;
	}

}
