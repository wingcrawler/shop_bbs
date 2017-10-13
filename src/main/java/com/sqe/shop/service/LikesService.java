package com.sqe.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.LikesMapper;
import com.sqe.shop.model.Likes;
import com.sqe.shop.util.PageUtil;


@Component  
public class LikesService extends AdapterService {
	
	@Autowired
    LikesMapper likesMapper;
    
    public int insert(Likes likes) {
		return likesMapper.insert(likes);
	}
    
    public int update(Likes likes) {
		return likesMapper.update(likes);
	}
	
	public int delete(Long id) {
		return likesMapper.delete(id);
	}
	
	public void save(Likes likes) {
		if(likes.getId()!=null){
			likesMapper.update(likes);
		} else {
			likesMapper.insert(likes);
		}
	}

	public Likes getById(Long id) {
		return likesMapper.getById(id);
	}
	
	public int countByParm(Map<String, Object> parmMap) {
		return likesMapper.countByParm(parmMap);
	}
	
	public List<Map<String, Object>> getMapListByParm(Map<String, Object> parm) {
		return  likesMapper.getMapListByParm(parm);
	}
	
	public List<Likes> getBeanListByParm(Map<String, Object> parm) {
		return likesMapper.getBeanListByParm(parm);
	}
	
	public PageUtil<Likes> getPageBeanByParm(Map<String, Object> parm) {
		PageUtil<Likes> page = new PageUtil<Likes>((Integer)parm.get("pageNo"), (Integer)parm.get("pageSize"));
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		int count = this.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Likes> list = new ArrayList<Likes>();
		if(count!=0){
			list = this.getBeanListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public PageUtil<Map<String, Object>> getPageMapByParm(Map<String, Object> parm) {
		PageUtil<Map<String, Object>> page = new PageUtil<Map<String, Object>>((Integer)parm.get("pageNo"), (Integer)parm.get("pageSize"));
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		int count = this.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = this.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
}
