package com.sqe.shop.controller.mobile;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.Constants;
import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.model.Comment;
import com.sqe.shop.model.News;
import com.sqe.shop.service.CommentService;
import com.sqe.shop.service.ImageService;
import com.sqe.shop.service.LikesService;
import com.sqe.shop.service.NewsService;
import com.sqe.shop.service.biz.BizNewsService;

@Controller
@RequestMapping("/h5/news")
public class MobileNewsController extends BaseFrontController {
	@Autowired
	private NewsService newsService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private LikesService likesService;
	
	@Autowired
	private BizNewsService bizNewsService;

	/**
	 * 新闻资讯列表
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> index(String searchText,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) throws UnsupportedEncodingException {
		pageSize=10;
		return bizNewsService.getNewsData(searchText,pageNo,pageSize);
	}
	
	/**
	 * 新闻资讯详情页
	 * @param model
	 * @param newsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="detail", method = RequestMethod.GET)
	public Map<String, Object> list(ModelAndView model, Long newsId) {
		if(newsId==null){
			return this.responseError1(Constants.ERRORCODE_FAILED, "记录不存在");
		}

		//查询news
		News news = newsService.getById(newsId);
		if(news==null){
			return this.responseError1(Constants.ERRORCODE_FAILED, "记录不存在");
		}
				
		Map<String, Object> resMap = bizNewsService.getDetail(newsId, news);
		
		//点赞数量
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("newsId", newsId);
		int thumbCount = likesService.countByParm(parmMap);
		resMap.put("thumbCount", thumbCount);
		//是否已点赞
		resMap.put("isliked", false);
		Long userId = this.getCurrentUserId();
		if(userId!=null){
			parmMap.put("userId", newsId);	
			int thumbCount1 = likesService.countByParm(parmMap);
			if(thumbCount1>0){
				resMap.put("isliked", true);		
			}
		}
		
		return resMap;
	}
	
	/** 
	 * 保存评论
	 * @param comment
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="saveComment", method = RequestMethod.POST)
	public Map<String, Object> saveComment(Comment comment) {
		Long userId = this.getCurrentUserId();
		if(userId==null){
			return responseError1(ERRORCODE_NOLOGIN, "未登录");
		}
		if(StringUtils.isBlank(comment.getContext())){
			return responseError(-1, "error_empty_content");
		}
		
		comment.setUserId(userId);
		commentService.insert(comment);
		return responseOK1("");
	}
	
}
