package com.sqe.shop.controller.api;

import java.util.Date;
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

import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.model.Post;
import com.sqe.shop.model.Section;
import com.sqe.shop.model.Thread;

import com.sqe.shop.service.PostService;
import com.sqe.shop.service.SectionService;
import com.sqe.shop.service.ThreadService;

import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.Resp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("api/bbs")
@Api("ApiBBSFontController")
public class ApiBBSFontController extends BaseFrontController {
	@Autowired
	private ThreadService threadService;
	@Autowired
	private SectionService sectionService;

	@Autowired
	private PostService postService;
	private static final Logger logger = LoggerFactory.getLogger(ApiBBSFontController.class);


	/**
	 * 根据版块id 返回列表
	 * 
	 * @param thread
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/threads", method = RequestMethod.GET)
	@ApiOperation(value = "根据版块id 返回列表 A", notes = "根据版块id 返回列表")
	public Resp<?> getList( Thread thread, @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		PageUtil<Map<String, Object>> page = threadService.getThreadsMapListByParm(thread, pageNo, pageSize);
		return Resp.success(page);
	}

	
	/**
	 * 
	 * @param post
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/post/getList", method = RequestMethod.GET)
	@ApiOperation(value = "获取跟帖列表", notes = "获取跟帖列表")
	public Resp<?> getList( Post post,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  @RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		PageUtil<Map<String, Object>> page = postService.getMapListByParm(post, pageNo, pageSize);
		return Resp.success(page);
	}
	
	@ResponseBody
	@RequestMapping(value="post/doSave", method = RequestMethod.POST)
	@ApiOperation(value = "发表跟帖", notes = "内容不能为空")
	public Resp<?> save( Post post) {
		if(StringUtils.isBlank(post.getPostContext())){
			return Resp.customFail("-1", "error_empty_content");
		}
		//
		
		post.setFloor((Long)postService.floorNum(post).get("floor"));
		post.setPostDate(new Date());
		post.setUserId(postService.getCurrentUserId());
		post.setPostStatus(1);
		postService.save(post);
		return  Resp.success("save_success");
	}
	
	@ResponseBody
	@RequestMapping(value="post/doDelete", method = RequestMethod.GET)
	@ApiOperation(value = "删除跟帖", notes = "删除跟帖")
	public Resp<?> doDelete(@RequestParam Long id) {
		if(id==null){
			return Resp.customFail("-1", "error_no_item");
		}
		int i = postService.delete(id);
		if(i==0){
			return Resp.customFail("-1", "error_del_failed");
		}
		return  Resp.success("success");
	}
	
	
	/**
	 * 获取section 列表 分页 一级 二级
	 * 
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/sectionList", method = RequestMethod.POST)
	@ApiOperation(value = "获取section 列表 分页 一级 二级", notes = "获取section 列表 分页 一级 二级  分页")
	public Map<String, Object> getSectionList(Section section,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		Map<String, Object> resMap = this.responseOK1("");
		section.setSectionStatus(1);
		resMap.put("sectionList", sectionService.getBeanListByParm(section, pageNo, pageSize));
		return resMap;
	}
}
