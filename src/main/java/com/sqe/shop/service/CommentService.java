package com.sqe.shop.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
    	String context = comment.getContext();
    	if(context.length()>1000){
    		comment.setContext(context.substring(0, 1000));
    	}
    	comment.setDate(new Date());
    	comment.setStatus(Constants.COMMENT_ON);
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
	
	public PageUtil<Map<String, Object>> getNewsMapListByParm(News news,int pageNo, Integer pageSize) throws UnsupportedEncodingException {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		
		if(news.getId()!=null){
			news = newsService.getById(news.getId());		
		} else if(StringUtils.isNotBlank(news.getNewsTitle())) {
			String newsTitle = URLDecoder.decode(news.getNewsTitle(),"utf-8");
			news = newsService.getByTitle(newsTitle);	
		} else {
			int count = newsService.getByParm("CommentMapper","getMapListByParm_count", null);
			pageUtil.setTotalRecords(count);
			pageUtil.setList(list);
			if(count!=0){
				Map<String, Object> newsMap = new HashMap<String, Object>();
				newsMap.put("start", pageUtil.getStartRow());
				newsMap.put("limit", pageUtil.getPageSize());
				newsMap.put("orderby", "id desc");
				list = commentMapper.getMapListByParm(newsMap);
				pageUtil.setList(list);	
			}
			return pageUtil;
		}
			
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		parm.put("newsId", news.getId());
		parm.put("orderby", "n.id desc");
		int count = newsService.getByParm("CommentMapper","getMapListByParm_count", parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			list = commentMapper.getMapListByParm(parm);
			for (Map<String, Object> map : list) {
				Date date = (Date) map.get("date");
				map.put("dateStr", DateUtil.dateToString(date, DateUtil.DATETIME_FORMATE_2));
				String statusStr = map.get("status").toString();
				map.put("statusStr", this.getCommentStatus(Integer.valueOf(statusStr)));
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

	public PageUtil<Map<String, Object>> getSellerProductCommentListByParm(Map<String, Object> parmMap, int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		parmMap.put("start", pageUtil.getStartRow());
		parmMap.put("limit", pageUtil.getPageSize());
		
		int count = commentMapper.countSellerProductCommentList(parmMap);
		pageUtil.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = commentMapper.getSellerProductCommentListByParm(parmMap);
			//查询是否有回复
			List<Map<String, Object>> replyList = new ArrayList<Map<String,Object>>();
			Map<String, Object> replyMap = new HashMap<String, Object>();
			replyMap.put("orderby", "c.date asc");
			parmMap.put("nullCommentId", false);
			for(Map<String, Object> map : list){
				replyMap.put("commentId", map.get("commentId"));
				replyList = commentMapper.getSellerProductCommentListByParm(replyMap);	
				map.put("replyList", replyList);
			}
		}
		pageUtil.setList(list);
		return pageUtil;
	}

	/**
	 * 新闻评论列表
	 * @param parmMap
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageUtil<Map<String, Object>> getNewsCommentListByParm(Map<String, Object> parmMap, int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		parmMap.put("start", pageUtil.getStartRow());
		parmMap.put("limit", pageUtil.getPageSize());
		
		List<Map<String, Object>> list = commentMapper.getNewsCommentListByParm(parmMap);
		pageUtil.setTotalRecords(list.size());
		pageUtil.setList(list);
		
		//查询是否有回复
		List<Map<String, Object>> replyList = new ArrayList<Map<String,Object>>();
		Map<String, Object> replyMap = new HashMap<String, Object>();
		replyMap.put("orderby", "c.date asc");
		parmMap.put("nullCommentId", false);
		for(Map<String, Object> map : list){
			replyMap.put("commentId", map.get("commentId"));
			replyList = commentMapper.getSubCommentListByParm(replyMap);	
			if(replyList!=null && !replyList.isEmpty() && replyList.get(0)!=null){
				map.put("replyList", replyList);	
			}
		}
		
		return pageUtil;
	}

	/**
	 * 获取产品的评论列表
	 * @param parmMap
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageUtil<Map<String, Object>> getProductCommentListByParm(Map<String, Object> parmMap, int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		parmMap.put("start", pageUtil.getStartRow());
		parmMap.put("limit", pageUtil.getPageSize());
		
		List<Map<String, Object>> list = commentMapper.getProductCommentListByParm(parmMap);
		pageUtil.setTotalRecords(list.size());
		pageUtil.setList(list);
		
		//查询是否有回复
		List<Map<String, Object>> replyList = new ArrayList<Map<String,Object>>();
		Map<String, Object> replyMap = new HashMap<String, Object>();
		replyMap.put("orderby", "c.date asc");
		parmMap.put("nullCommentId", false);
		for(Map<String, Object> map : list){
			replyMap.put("commentId", map.get("commentId"));
			replyList = commentMapper.getSubCommentListByParm(replyMap);	
			if(replyList!=null && !replyList.isEmpty() && replyList.get(0)!=null)
			map.put("replyList", replyList);
		}
		
		return pageUtil;
	}

}
