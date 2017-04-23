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
import com.sqe.shop.model.Comment;
import com.sqe.shop.model.News;
import com.sqe.shop.model.User;
import com.sqe.shop.service.CommentService;
import com.sqe.shop.service.NewsService;
import com.sqe.shop.service.UserService;
import com.sqe.shop.util.PageUtil;

@Controller
@RequestMapping("/backend/comment")
public class CommentController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	private CommentService commentService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView index(Long id) {
		ModelAndView model = new ModelAndView("backend/news/comment_list");
		if(id!=null){
			News news = newsService.getById(id);
			model.addObject("news", news);
		}
		return model;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("backend/news/comment");
		if(id!=null){
			Comment entity = commentService.getById(id);
			if(entity.getUserId()>0){
				News news = newsService.getById(entity.getNewsId());
				User user = userService.getById(entity.getUserId());
				entity.setUserName(user.getUsername());
				entity.setTitle(news.getTitle());
				model.addObject("entity", entity);	
			}
		}
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList", method = RequestMethod.GET)
	public Map<String, Object> getList(News news,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  @RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		PageUtil<Map<String, Object>> page = commentService.getMapListByParm(news, pageNo, pageSize);
		
		resMap.put("list", page.getList());
		resMap.put("page", page);
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(Comment comment) {
		if(StringUtils.isBlank(comment.getContext())){
    		return responseError(-1, bundle.getString("error_empty_content"));
    	}
		commentService.insert(comment);
		return responseOK(bundle.getString("save_success"));
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete", method = RequestMethod.GET)
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, bundle.getString("error_no_item"));
		}
		int i = commentService.delete(id);
		if(i==0){
			return responseError(-1, bundle.getString("error_del_failed"));
		}
		return responseOK(bundle.getString("op_success"));
	}

}
