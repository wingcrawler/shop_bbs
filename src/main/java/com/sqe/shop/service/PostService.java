package com.sqe.shop.service;

import java.util.HashMap;
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
    PostMapper PostMapper;
    
    public int insert(Post Post) {
		return PostMapper.insert(Post);
	}
    
    public int update(Post Post) {
		return PostMapper.update(Post);
	}
	
	public int delete(Long id) {
		return PostMapper.delete(id);
	}

	public Post getById(Long id) {
		return PostMapper.getById(id);
	}
	
	public int countByParm(Post Post) {
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(Post!=null){
		
		}*/
		return PostMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return PostMapper.countByParm(parm);
	}
	
	public PageUtil<Post> getBeanListByParm(Post Post, int pageNo, Integer pageSize) {
		PageUtil<Post> pageUtil = new PageUtil<Post>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(Post!=null){
			
		}*/
		int count = PostMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Post> list = PostMapper.getBeanListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Map<String, Object> parm,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		int count = PostMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Map<String, Object>> list = PostMapper.getMapListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}

}
