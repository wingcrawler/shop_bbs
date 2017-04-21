package com.sqe.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.NewsMapper;
import com.sqe.shop.model.News;
import com.sqe.shop.util.PageUtil;

@Component  
public class NewsService extends AdapterService implements BaseService {
	
	@Autowired
    NewsMapper NewsMapper;
    
    public int insert(News News) {
		return NewsMapper.insert(News);
	}
    
    public int update(News News) {
		return NewsMapper.update(News);
	}
	
	public int delete(Long id) {
		return NewsMapper.delete(id);
	}

	public News getById(Long id) {
		return NewsMapper.getById(id);
	}
	
	public int countByParm(News News) {
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(News!=null){
		
		}*/
		return NewsMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return NewsMapper.countByParm(parm);
	}
	
	public PageUtil<News> getBeanListByParm(News News, int pageNo, Integer pageSize) {
		PageUtil<News> pageUtil = new PageUtil<News>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(News!=null){
			
		}*/
		int count = NewsMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<News> list = NewsMapper.getBeanListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Map<String, Object> parm,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		int count = NewsMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Map<String, Object>> list = NewsMapper.getMapListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}

}
