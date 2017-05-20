package com.sqe.shop.controller.front;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.model.News;
import com.sqe.shop.service.CommentService;
import com.sqe.shop.service.NewsService;
import com.sqe.shop.util.DateUtil;
import com.sqe.shop.util.PageUtil;

@Controller
@RequestMapping("/news")
public class FrontNewsController extends BaseFrontController {
	
	private static final Logger logger = LoggerFactory.getLogger(FrontNewsController.class);
	
	@Autowired
	private NewsService newsService;
	@Autowired
	private CommentService commentService;
	
	/**
	 * 新闻列表页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView model,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		pageSize=10;
		
		News news = new News();
		PageUtil<Map<String, Object>> newsPage = newsService.getMapListByParm(news, pageNo, pageSize);
		model.addObject("list", newsPage.getList());
		model.addObject("page", newsPage);
		
		model.setViewName("shop/news/list");
		return model;
	}
	
	/**
	 * 新闻资讯详情页
	 * @param model
	 * @param newsId
	 * @return
	 */
	@RequestMapping(value="detail", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView model, Long newsId) {
		//查询news
		News news = newsService.getById(newsId);
		if(news==null){
			model.setViewName("shop/404");
			return model;
		}
		model.addObject("news", news);
		//阅读数+1
		news.setCreateTimeStr(DateUtil.dateToString(news.getNewsDate(), DateUtil.DATETIME_FORMATE_2));
		news.setNewsReaded(news.getNewsReaded()+1);
		newsService.update(news);
		
		//评论列表
		//产品评论列表
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("newsId", newsId);
		parmMap.put("nullCommentId", true);
		parmMap.put("orderby", "c.date desc");
		PageUtil<Map<String, Object>> commentPage = commentService.getSellerProductCommentListByParm(parmMap, 1, -1);
		model.addObject("commentPage", commentPage);
		
		model.setViewName("shop/news/newsdetail");
		return model;
	}
	
	/**
	 * 异步加载新闻评论
	 * @param newsId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getCommentList", method = RequestMethod.GET)
	public PageUtil<Map<String, Object>> getCommentList(Long newsId, int pageNo, Integer pageSize) {
		News news = new News();
		news.setId(newsId);
		return commentService.getNewsMapListByParm(news, pageNo, pageSize);
	}
	
	/**
	 * 点赞
	 * @param newsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="thumb", method = RequestMethod.GET)
	public Map<String, Object> thumb(Long newsId) {
		News news = newsService.getById(newsId);
		if(news==null){
			return responseError(-1, "error_thumb");
		}
		//点赞
		newsService.updateThumb(news.getNewsUp(), newsId);
		return responseOK("success_thumb");
	}
	
}
