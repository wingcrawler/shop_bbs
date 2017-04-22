package com.sqe.shop.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.CommentMapper;
import com.sqe.shop.model.Comment;
import com.sqe.shop.util.PageUtil;

@Component  
public class CommentService extends AdapterService implements BaseService {
	
	@Autowired
    CommentMapper commentMapper;
    
    public int insert(Comment comment) {
		return commentMapper.insert(comment);
	}
    
    public int update(Comment comment) {
		return commentMapper.update(comment);
	}
	
	public int delete(Long id) {
		return commentMapper.delete(id);
	}

	public Comment getById(Long id) {
		return commentMapper.getById(id);
	}
	
	public int countByParm(Comment comment) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(comment!=null){
		
		}
		return commentMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return commentMapper.countByParm(parm);
	}
	
	public PageUtil<Comment> getBeanListByParm(Comment comment, int pageNo, Integer pageSize) {
		PageUtil<Comment> pageUtil = new PageUtil<Comment>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		if(comment!=null){
			parm.put("start", pageUtil.getStartRow());
			parm.put("limit", pageUtil.getPageSize());
		}
		int count = commentMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Comment> list = new ArrayList<Comment>();
		if(count!=0){
			list = commentMapper.getBeanListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Map<String, Object> parm,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		int count = commentMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Map<String, Object>> list = commentMapper.getMapListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	public void save(Comment comment) {
		if(comment.getId()!=null){
			commentMapper.update(comment);
		} else {
			commentMapper.insert(comment);
		}
	}

}
