package com.sqe.shop.service;

import java.util.HashMap;
import java.util.ArrayList;
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
    NewsMapper newsMapper;
    
    public int insert(News news) {
		return newsMapper.insert(news);
	}
    
    public int update(News news) {
		return newsMapper.update(news);
	}
	
	public int delete(Long id) {
		return newsMapper.delete(id);
	}

	public News getById(Long id) {
		return newsMapper.getById(id);
	}
	
	public int countByParm(News news) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(news!=null){
		
		}
		return newsMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return newsMapper.countByParm(parm);
	}
	
	public PageUtil<News> getBeanListByParm(News news, int pageNo, Integer pageSize) {
		PageUtil<News> pageUtil = new PageUtil<News>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		if(news!=null){
			parm.put("start", pageUtil.getStartRow());
			parm.put("limit", pageUtil.getPageSize());
		}
		int count = newsMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<News> list = new ArrayList<News>();
		if(count!=0){
			list = newsMapper.getBeanListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Map<String, Object> parm,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		int count = newsMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Map<String, Object>> list = newsMapper.getMapListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	public void save(News news) {
		if(news.getId()!=null){
			newsMapper.update(news);
		} else {
			newsMapper.insert(news);
		}
	}

}
