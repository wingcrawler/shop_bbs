package com.sqe.shop.controller.backend;

import java.util.HashMap;
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

import com.sqe.shop.common.BaseController;
import com.sqe.shop.model.News;
import com.sqe.shop.service.NewsService;
import com.sqe.shop.util.PageUtil;

@Controller
@RequestMapping("/backend/news")
public class NewsController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);
	
	@Autowired
	private NewsService newsService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("backend/news/list");
		return model;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("backend/news/edit");
		if(id!=null){
			News entity = newsService.getById(id);
			model.addObject("entity", entity);
		}
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList", method = RequestMethod.GET)
	public Map<String, Object> getList(News news,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  @RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		PageUtil<News> page = newsService.getBeanListByParm(news, pageNo, pageSize);
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("list", page.getList());
		resMap.put("page", page);
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(News news) {
		if(StringUtils.isBlank(news.getNewsTitle())){
			return responseError(-1, "error_empty_title");
		}
		if(StringUtils.isBlank(news.getNewsContext())){
			return responseError(-1, "error_empty_content");
		}
		if(news.getNewsType()==null || news.getNewsType()<0){
			return responseError(-1, "error_empty_lang");
		}
		newsService.save(news);
		return responseOK("save_success");
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete", method = RequestMethod.GET)
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, "error_no_item");
		}
		int i = newsService.delete(id);
		if(i==0){
			return responseError(-1, "error_del_failed");
		}
		return responseOK("op_success");
	}

}
