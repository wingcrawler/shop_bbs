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
    NewsMapper newsMapper;
    
    public int insert(News News) {
		return newsMapper.insert(News);
	}
    
    public int update(News News) {
		return newsMapper.update(News);
	}
	
	public int delete(Long id) {
		return newsMapper.delete(id);
	}

	public News getById(Long id) {
		return newsMapper.getById(id);
	}
	
	public int countByParm(News News) {
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(News!=null){
		
		}*/
		return newsMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return newsMapper.countByParm(parm);
	}
	
	public PageUtil<News> getBeanListByParm(News News, int pageNo, Integer pageSize) {
		PageUtil<News> pageUtil = new PageUtil<News>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(News!=null){
			
		}*/
		int count = newsMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<News> list = newsMapper.getBeanListByParm(parm);
			pageUtil.setList(list);
		}
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

}
