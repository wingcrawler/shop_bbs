package com.sqe.shop.controller.front;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.aspectj.apache.bcel.generic.RET;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.BaseFrontController;
import com.sqe.shop.model.Comment;
import com.sqe.shop.model.Image;
import com.sqe.shop.model.News;
import com.sqe.shop.model.Product;
import com.sqe.shop.service.AdvertisementService;
import com.sqe.shop.service.CommentService;
import com.sqe.shop.service.ImageService;
import com.sqe.shop.service.NewsService;
import com.sqe.shop.service.ProductService;
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
	 * 新闻资讯详情页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="view", method = RequestMethod.GET)
	public ModelAndView single(ModelAndView model, Long newsId) {
		News news = newsService.getById(newsId);
		if(news==null){
			model.setViewName("shop/404");
			return model;
		}
		model.addObject("news", news);
		
		model.addObject("commentPage", commentService.getNewsMapListByParm(news, 1, 10));
		
		model.setViewName("shop/news/view");
		return model;
	}
	
	@Resource
	@RequestMapping(value="thumb", method = RequestMethod.GET)
	public Map<String, Object> thumb(Long newsId) {
		News news = newsService.getById(newsId);
		if(news==null){
			return responseError(-1, "error_thumb_failed");
		}
		news.setNewsUp(news.getNewsUp()+1);
		newsService.update(news);
		return responseOK("error_thumb_success");
	}
	
}
