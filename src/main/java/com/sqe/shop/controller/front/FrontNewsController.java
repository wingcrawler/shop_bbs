package com.sqe.shop.controller.front;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
import com.sqe.shop.service.biz.BizNewsService;
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
	
	@Autowired
	private BizNewsService bizNewsService;
	
	/**
	 * 新闻列表页
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="list")
	public ModelAndView list(ModelAndView model,String searchText,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) throws UnsupportedEncodingException {
		pageSize=10;
		
		Map<String, Object> resMap = bizNewsService.getNewsData(searchText,pageNo,pageSize);
		
		model.addAllObjects(resMap);
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
		
		model.addAllObjects(bizNewsService.getDetail(newsId, news));
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
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping(value="getCommentList", method = RequestMethod.GET)
	public PageUtil<Map<String, Object>> getCommentList(Long newsId, int pageNo, Integer pageSize) throws UnsupportedEncodingException {
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
	@RequestMapping(value="doNewsUp", method = RequestMethod.POST)
	public Map<String, Object> thumb(Long newsId) {
		News news = newsService.getById(newsId);
		if(news==null){
			return responseError(-1, "error_thumb");
		}
		//点赞
		newsService.updateThumb(news.getNewsUp(), newsId);
		/*return responseOK("success_thumb");*/
		return responseOK1("");
	}
	
	@RequestMapping(value="search", method = RequestMethod.POST)
	public ModelAndView search(ModelAndView model, String newsTitle,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		if(StringUtils.isBlank(newsTitle)){
			model.setViewName("redirect:/shopIndex");
			return model;	
		}
		pageSize=10;
		try {
			model.addObject("newsTitle", URLEncoder.encode(newsTitle, UTF8));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//搜索新闻资讯
		News news = new News();
		news.setNewsTitle(newsTitle);
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
	
}
