package com.sqe.shop.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.PostMapper;
import com.sqe.shop.model.Post;
import com.sqe.shop.util.PageUtil;

@Component  
public class PostService extends AdapterService implements BaseService {
	
	@Autowired
    PostMapper postMapper;
    
    public int insert(Post post) {
		return postMapper.insert(post);
	}
    
    public int update(Post post) {
		return postMapper.update(post);
	}
	
	public int delete(Long id) {
		return postMapper.delete(id);
	}

	public Post getById(Long id) {
		return postMapper.getById(id);
	}
	
	public int countByParm(Post post) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(post!=null){
		
		}
		return postMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return postMapper.countByParm(parm);
	}
	
	public PageUtil<Post> getBeanListByParm(Post post, int pageNo, Integer pageSize) {
		PageUtil<Post> pageUtil = new PageUtil<Post>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		if(post!=null){
			parm.put("start", pageUtil.getStartRow());
			parm.put("limit", pageUtil.getPageSize());
		}
		int count = postMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Post> list = new ArrayList<Post>();
		if(count!=0){
			list = postMapper.getBeanListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Map<String, Object> parm,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		int count = postMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Map<String, Object>> list = postMapper.getMapListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	public void save(Post post) {
		if(post.getId()!=null){
			postMapper.update(post);
		} else {
			postMapper.insert(post);
		}
	}

}
