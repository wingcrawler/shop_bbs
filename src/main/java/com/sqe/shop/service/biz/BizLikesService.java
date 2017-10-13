package com.sqe.shop.service.biz;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.common.BaseCommon;
import com.sqe.shop.common.Constants;
import com.sqe.shop.model.Likes;
import com.sqe.shop.service.LikesService;
import com.sqe.shop.util.PageUtil;

@Component
public class BizLikesService extends BaseCommon {
	
	@Autowired
	private LikesService likesService;

	public Map<String, Object> thumbUp(Likes likes) {
		Long userId = this.getCurrentUserId();
		if(userId==null){
			return responseError1(ERRORCODE_NOLOGIN, "未登录");
		}
		
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("userId", userId);
		parmMap.put("newsId", likes.getNewsId());
		int count = likesService.countByParm(parmMap);
		if(count>0){
			return responseOK("success_thumb");
		}
		
		likes.setUserId(userId);
		likes.setCreateTime(new Date());

		
		int c = likesService.insert(likes);
		if(c>0){
			return responseOK("success_thumb");
		} else {
			return responseError(Constants.ERRORCODE_FAILED, "error_thumb");
		}
	}

	public Map<String, Object> getLikesList(Map<String, Object> parmMap) {
		Map<String, Object> resMap = responseOK1("");
		
		 PageUtil<Map<String, Object>> pageUtil = likesService.getPageMapByParm(parmMap);
		
		 resMap.put("page", pageUtil);
		return resMap;
	}

	
	
	
}
