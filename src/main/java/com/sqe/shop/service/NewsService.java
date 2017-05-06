package com.sqe.shop.service;

import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.common.Constants;
import com.sqe.shop.mapper.NewsMapper;
import com.sqe.shop.mapper.UserThumbMapper;
import com.sqe.shop.model.News;
import com.sqe.shop.model.UserThumb;
import com.sqe.shop.util.DateUtil;
import com.sqe.shop.util.PageUtil;

@Component  
public class NewsService extends AdapterService implements BaseService {
	
	@Autowired
	private NewsMapper newsMapper;
	@Autowired
	private UserThumbMapper userThumbMapper;
    
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
		Map<String, Object> parm = queryParm(news);
		return newsMapper.countByParm(parm);
	}
	
	public PageUtil<News> getBeanListByParm(News news, int pageNo, Integer pageSize) {
		PageUtil<News> pageUtil = new PageUtil<News>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(news);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());

		int count = newsMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		
		List<News> list = new ArrayList<News>();
		if(count!=0){
			list = newsMapper.getBeanListByParm(parm);
			for(News n : list){
				n.setTypeName(Constants.getNewsLang(n.getNewsType()));
				String dateStr = DateUtil.dateToString(n.getNewsDate(), DateUtil.DATETIME_FORMATE_2);
				n.setCreateTimeStr(dateStr);
			}
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(News news,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(news);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		
		int count = newsMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = newsMapper.getMapListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public void save(News news) {
		if(news.getId()!=null){
			newsMapper.update(news);
		} else {
			news.setNewsDate(new Date());
			news.setNewsReaded(0);
			news.setNewsUp(0);
			newsMapper.insert(news);
		}
	}

	public News getByTitle(String newsTitle) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("newsTitle", newsTitle);
		List<News> list = newsMapper.getBeanListByParm(parm);
		if(list==null || list.isEmpty()){
			return null;
		}
		return list.get(0);
	}
	
	private Map<String, Object> queryParm(News news) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(news!=null){
			if(StringUtils.isNotBlank(news.getNewsTitle())){
				parm.put("newsTitle", news.getNewsTitle());	
			}
		}
		parm.put("orderby", "id desc" );
		return parm;
	}

	public void updateThumb(Integer newsUpCount, Long newsId) {
		News news = new News();
		news.setNewsUp(news.getNewsUp()+1);
		this.update(news);
		
		UserThumb thumb = new UserThumb();
		thumb.setNewsId(newsId);
		thumb.setUserId(this.getCurrentUserId());
		userThumbMapper.insert(thumb);
	}

}
