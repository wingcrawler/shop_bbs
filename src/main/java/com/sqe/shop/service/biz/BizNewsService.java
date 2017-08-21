package com.sqe.shop.service.biz;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List; 
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.common.BaseCommon;
import com.sqe.shop.model.Image;
import com.sqe.shop.model.News;
import com.sqe.shop.service.CommentService;
import com.sqe.shop.service.ImageService;
import com.sqe.shop.service.NewsService;
import com.sqe.shop.util.DateUtil;
import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.RegularUtil;

@Component
public class BizNewsService extends BaseCommon {
	
	@Autowired
	private NewsService newsService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ImageService imageService;

	/**
	 * 新闻资讯列表
	 * @param searchText
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public Map<String, Object> getNewsData(String searchText, int pageNo, int pageSize) throws UnsupportedEncodingException {
		Map<String, Object> resMap = this.responseOK1("");
		
		News news = new News();
		if(StringUtils.isNotBlank(searchText)){
			searchText = URLDecoder.decode(searchText, UTF8);
			news.setNewsTitle(searchText);
		}
		PageUtil<Map<String, Object>> newsPage = newsService.getMapListByParm(news, pageNo, pageSize);
		if(newsPage.getList()!=null && !newsPage.getList().isEmpty()){
			String newsContent = "";
			for(Map<String, Object> map : newsPage.getList()){
				newsContent = map.get("newsContent").toString();
				newsContent = RegularUtil.Html2Text(newsContent);
				if(newsContent.length()>100){
					newsContent = newsContent.substring(0,100) + "...";
					map.put("newsContent", newsContent);
				} else {
					map.put("newsContent", newsContent);
				}
				
			}
		}
		resMap.put("list", newsPage.getList());
		resMap.put("page", newsPage);
		
		resMap.put("searchText", searchText);
		
		return resMap;
	}

	public Map<String, Object> getDetail(Long newsId, News news) {
		Map<String, Object> resMap = this.responseOK1("");
				
		resMap.put("news", news);
		
		//查询配图
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("newsId", newsId);
		List<Image> images = imageService.getBeanListByParm("ImageMapper", parmMap);
		if(images != null && !images.isEmpty()){
			resMap.put("image", images.get(0));	
		}
		
		//阅读数+1
		news.setCreateTimeStr(DateUtil.dateToString(news.getNewsDate(), DateUtil.DATETIME_FORMATE_2));
		news.setNewsReaded(news.getNewsReaded()+1);
		newsService.update(news);
		
		//评论列表
		parmMap = new HashMap<String, Object>();
		parmMap.put("newsId", newsId);
		parmMap.put("nullCommentId", true);
		parmMap.put("orderby", "c.date desc");
		PageUtil<Map<String, Object>> commentPage = commentService.getNewsCommentListByParm(parmMap, 1, -1);
		resMap.put("commentPage", commentPage);
		resMap.put("commentCount", commentPage.getTotalRecords());
		
		return resMap;
	}
	
	
}
