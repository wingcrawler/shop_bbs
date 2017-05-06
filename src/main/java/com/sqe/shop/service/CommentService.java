package com.sqe.shop.service;

import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.common.Constants;
import com.sqe.shop.mapper.CommentMapper;
import com.sqe.shop.model.Comment;
import com.sqe.shop.model.News;
import com.sqe.shop.util.DateUtil;
import com.sqe.shop.util.PageUtil;

@Component  
public class CommentService extends AdapterService implements BaseService {
	
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private NewsService newsService;
    
    public int insert(Comment comment) {
    	comment.setCommentId(comment.getId());
    	comment.setDate(new Date());
    	comment.setStatus(1);
    	comment.setUserId(-1L);
    	String context = comment.getContext();
    	if(context.length()>1000){
    		comment.setContext(context.substring(0, 1000));
    	}
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
		Map<String, Object> parm = queryParm(comment);
		return commentMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return commentMapper.countByParm(parm);
	}
	
	public PageUtil<Comment> getBeanListByParm(Comment comment, int pageNo, Integer pageSize) {
		PageUtil<Comment> pageUtil = new PageUtil<Comment>(pageNo, pageSize);
		Map<String, Object> parm =  queryParm(comment);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		
		int count = commentMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		
		List<Comment> list = new ArrayList<Comment>();
		if(count!=0){
			list = commentMapper.getBeanListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getNewsMapListByParm(News news,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		if(news==null){
			pageUtil.setList(list);
			pageUtil.setTotalRecords(0);
			return pageUtil;
		}
		
		if(news.getId()!=null){
			news = newsService.getById(news.getId());		
		} else {
			news = newsService.getByTitle(news.getNewsTitle());	
		}
		if(news==null){
			pageUtil.setList(list);
			pageUtil.setTotalRecords(0);
			return pageUtil;
		}
			
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		parm.put("newsId", news.getId());
		parm.put("orderby", "id desc");
		
		int count = commentMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		
		if(count!=0){
			list = commentMapper.getMapListByParm(parm);
			for (Map<String, Object> map : list) {
				Date date = (Date) map.get("date");
				map.put("dateStr", DateUtil.dateToString(date, DateUtil.DATETIME_FORMATE_2));
				String statusStr = map.get("status").toString();
				map.put("statusStr", Constants.getCommentStatus(Integer.valueOf(statusStr)));
				String userIdStr = map.get("userId").toString();
				if(Integer.valueOf(userIdStr)<0){
					map.put("username", "admin");
				}
			}
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
	
	private Map<String, Object> queryParm(Comment comment) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(comment!=null){
			if(comment.getNewsId()!=null && comment.getNewsId()>0){
				parm.put("newsId", comment.getNewsId());
			}
			if(comment.getProductId()!=null && comment.getProductId()>0){
				parm.put("productId", comment.getProductId());
			}
			if(comment.getShopId()!=null && comment.getShopId()>0){
				parm.put("shopId", comment.getShopId());
			}
			if(comment.getUserId()!=null && comment.getNewsId()>0){
				parm.put("userId", comment.getUserId());
			}
		}
		parm.put("orderby", "order by id desc");
		return parm;
	}
	
}
