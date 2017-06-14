package com.sqe.shop.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.PostMapper;
import com.sqe.shop.model.Post;
import com.sqe.shop.model.Thread;
import com.sqe.shop.util.DateUtil;
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
		Map<String, Object> parm = queryParm(post);
		return postMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return postMapper.countByParm(parm);
	}
	
	public PageUtil<Post> getBeanListByParm(Post post, int pageNo, Integer pageSize) {
		PageUtil<Post> pageUtil = new PageUtil<Post>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(post);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());

		int count = postMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		
		List<Post> list = new ArrayList<Post>();
		if(count!=0){
			list = postMapper.getBeanListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Post post,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(post);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		
		int count = postMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = postMapper.getUserMapListByParm(parm);
			for (Map<String, Object> p : list) {
				Date time = (Date) p.get("post_date");
				p.put("time",(DateUtil.dateToString(time, DateUtil.DATETIME_FORMATE_4)));

			}
		}
		pageUtil.setList(list);
		
		//查询是否有回复
				List<Map<String, Object>> replyList = new ArrayList<Map<String,Object>>();
				Map<String, Object> replyMap = new HashMap<String, Object>();
				replyMap.put("orderby", "rp.post_date desc");
				parm.put("nullCommentId", false);
				for(Map<String, Object> map : list){
					replyMap.put("postId", map.get("id"));
					replyList = postMapper.getSubPostListByParm(replyMap);	
					if(replyList!=null && !replyList.isEmpty() && replyList.get(0)!=null){
						map.put("replyList", replyList);	
					}
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
	
	private Map<String, Object> queryParm(Post post) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(post!=null){
			if(post.getUserId()!=null && post.getUserId()>=0){
				parm.put("userId", post.getUserId());	
			}
			if(post.getPostStatus()!=null && post.getPostStatus()>=0){
				parm.put("postStatus", post.getPostStatus());	
			}
			if(post.getThreadId()!=null && post.getThreadId()>=0){
				parm.put("threadId", post.getThreadId());	
			}
		}
		parm.put("orderby", "id desc" );
		return parm;
	}
	
	public Map<String, Object> floorNum(Post post) {
		Map<String, Object> parm = new HashMap<String, Object>();	
		if(post.getThreadId()!=null && post.getThreadId()>=0){
			parm.put("threadId", post.getThreadId());	
		}
		parm.put("orderby", "id desc" );
		return parm;
	}

}
