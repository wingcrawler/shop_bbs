/*   
 * Copyright (c) 2014-2034 360taihe Ltd. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * 360taihe. You shall not disclose such Confidential Information.   
 */

/**
 * 
 */
package com.sqe.shop.controller.bbs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.sqe.shop.controller.base.BaseBackendController;
import com.sqe.shop.model.Post;
import com.sqe.shop.model.Section;
import com.sqe.shop.service.PostService;
import com.sqe.shop.service.ThreadService;
import com.sqe.shop.service.TopicService;
import com.sqe.shop.util.PageUtil;

/**
 * @author Andy
 *
 */
@Controller
@RequestMapping("/bbs/post")
public class BBSPostController extends BaseBackendController{

	@Autowired
	private ThreadService threadService;
	@Autowired
	private PostService postService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("bbs/thread");
		model.addObject("secondSectionList", getSectonSectionList());
		return model;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("backend/bbs/topic_edit");
		if(id!=null){
			Post entity = postService.getById(id);
			model.addObject("entity", entity);
		}
		model.addObject("secondSectionList", getSectonSectionList());
		return model;
	}
	
	private List<Post> getSectonSectionList() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sectionType", Constants.SECTION_ON);
		List<Post> list = postService.getBeanListByParm("SectionMapper", paramMap);
		if(list==null || list.isEmpty()){
			return new ArrayList<Post>();
		}
		return list;
	}
	/**
	 * 联表获取用户信息
	 * @param post
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getList", method = RequestMethod.GET)
	public Map<String, Object> getList(Post post,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  @RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		PageUtil<Map<String, Object>> page = postService.getMapListByParm(post, pageNo, pageSize);
		resMap.put("list", page.getList());
		resMap.put("page", page);
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(Post post) {
		if(StringUtils.isBlank(post.getPostContext())){
			return responseError(-1, "error_empty_content");
		}
		postService.save(post);
		return responseOK("save_success");
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete", method = RequestMethod.GET)
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, "error_no_item");
		}
		int i = postService.delete(id);
		if(i==0){
			return responseError(-1, "error_del_failed");
		}
		return responseOK("op_success");
	}
}
