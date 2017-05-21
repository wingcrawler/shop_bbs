package com.sqe.shop.controller.front;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import com.sqe.shop.model.Comment;
import com.sqe.shop.model.Image;
import com.sqe.shop.model.News;
import com.sqe.shop.service.CommentService;
import com.sqe.shop.service.ImageService;
import com.sqe.shop.service.NewsService;
import com.sqe.shop.util.DateUtil;
import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.RegularUtil;

@Controller
@RequestMapping("/news")
public class FrontNewsController extends BaseFrontController {
	
	private static final Logger logger = LoggerFactory.getLogger(FrontNewsController.class);
	
	@Autowired
	private NewsService newsService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ImageService imageService;
	
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
		if(newsId==null){
			model.setViewName("shop/404");
			return model;
		}
		
		//查询news
		News news = newsService.getById(newsId);
		if(news==null){
			model.setViewName("shop/404");
			return model;
		}
		model.addObject("news", news);
		
		//查询配图
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("newsId", newsId);
		List<Image> images = imageService.getBeanListByParm("ImageMapper", parmMap);
		if(images != null && !images.isEmpty()){
			model.addObject("image", images.get(0));	
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
		model.addObject("commentPage", commentPage);
		
		model.setViewName("shop/news/newsdetail");
		return model;
	}
	
	/**
	 * 发表评论
	 * @param news
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="saveComment", method = RequestMethod.POST)
	public Map<String, Object> saveComment(Comment comment) {
		if(StringUtils.isBlank(comment.getContext())){
			return responseError(-1, "error_empty_content");
		}
		comment.setUserId(this.getCurrentUserId());
		commentService.insert(comment);
		return responseOK1("");
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
