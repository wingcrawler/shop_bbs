package com.sqe.shop.controller.mobile;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.model.Post;
import com.sqe.shop.model.Section;
import com.sqe.shop.model.Thread;
import com.sqe.shop.service.PostService;
import com.sqe.shop.service.SectionService;
import com.sqe.shop.service.ThreadService;
import com.sqe.shop.service.biz.BizBBSService;
import com.sqe.shop.util.PageUtil;

@Controller
@RequestMapping("/h5")
public class MobileBBSController extends BaseFrontController {

	@Autowired
	private BizBBSService bizBBSService;

	@Autowired
	private PostService postService;

	@Autowired
	private SectionService sectionService;

	@Autowired
	private ThreadService threadService;

	/**
	 * 主页
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/bbs/index")
	public Map<String, Object> index() {
		return bizBBSService.getHomeIndexData();
	}

	/**
	 * 发表帖子
	 * 
	 * @param thread
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bbs/thread", method = RequestMethod.POST)
	public Map<String, Object> thread(Thread thread) {
		/*Long userId = this.getCurrentUserId();*/
		Long userId=2L;
		if (userId == null) {
			return responseError1(ERRORCODE_NOLOGIN, "未登录");
		}
		if (StringUtils.isBlank(thread.getThreadContext())) {
			return responseError1(-1, "内容不能为空");
		}
		if (StringUtils.isBlank(thread.getThreadTitle())) {
			return responseError1(-1, "标题不能为空");
		}
		if (thread.getSectionId() == null) {
			return responseError1(-1, "请选择板块");
		}
		Map<String, Object> resMap = this.responseOK1("");
		thread.setUserId(userId);
		thread.setThreadStatus(1);
		thread.setThreadTime(new Date());
		thread.setThreadView(1L);
		thread.setThreadUp(1L);
		int result = threadService.insert(thread);
		resMap.put("result", result);
		return resMap;
	}

	/**
	 * 发表帖子评论(跟帖)
	 * 
	 * @param model
	 *            前端校验空
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bbs/post", method = RequestMethod.POST)
	public Map<String, Object> post(Post post) {
		Long userId = this.getCurrentUserId();
		if (userId == null) {
			return responseError1(ERRORCODE_NOLOGIN, "未登录");
		}
		if (StringUtils.isBlank(post.getPostContext())) {
			return responseError1(-1, "评论内容为空");
		}
		if (post.getThreadId() == null) {
			return responseError1(-1, "error_empty_threadID");
		}
		Map<String, Object> resMap = this.responseOK1("");
		post.setUserId(userId);
		post.setPostDate(new Date());
		post.setPostStatus(1);
		int result = postService.insert(post);
		resMap.put("result", result);
		return resMap;
	}

	/**
	 * 根据id 获取帖子详情
	 * 
	 * @param id
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/bbs/thread/{id}", method = RequestMethod.GET)
	public Map<String, Object> postById(@PathVariable("id") Long id) {
		Map<String, Object> resMap = this.responseOK1("");
		Thread threaddetil = new Thread();
		threaddetil.setThreadStatus(1);
		threaddetil.setId(id);
		threaddetil = threadService.getById(id);
		if (threaddetil == null) {
			return responseError1(404, "error_empty_thread");
		}
		resMap.put("thred", threaddetil);
		return resMap;
	}

	/**
	 * 获取section 列表 分页 一级 二级
	 * 
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/bbs/sectionList", method = RequestMethod.POST)
	public Map<String, Object> getSectionList(Section section,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		Map<String, Object> resMap = this.responseOK1("");
		section.setSectionStatus(1);
		resMap.put("sectionList", sectionService.getBeanListByParm(section, pageNo, pageSize));
		return resMap;
	}

	/**
	 * 根据sectionId 获取板块ThreadList
	 * 
	 * @param section
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bbs/threadList", method = RequestMethod.POST)
	public PageUtil<Map<String, Object>> getThreadsBySection(@RequestParam(name = "sectionId", defaultValue = "1") Long sectionId,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		Map<String, Object> resMap = this.responseOK1("");
		Thread threadList = new Thread();
		threadList.setSectionId(sectionId);
		threadList.setThreadStatus(1);
		return threadService.getMapListByParm(threadList, pageNo, pageSize);
		
	}

	/**
	 * 根据threadID 获取Post 列表
	 * 
	 * @param threadId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bbs/postList", method = RequestMethod.POST)
	public Map<String, Object> getPostByThread(@RequestParam(name = "threadId") Long threadId,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		Map<String, Object> resMap = this.responseOK1("");
		Post postList = new Post();
		postList.setThreadId(threadId);
		postList.setPostStatus(1);
		resMap.put("sectionList", postService.getMapListByParm(postList, pageNo, pageSize));
		return resMap;
	}

	/**
	 * 根据ID 获取Section 信息
	 * 
	 * @param threadId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bbs/section/{id}", method = RequestMethod.GET)
	public Map<String, Object> getSectionById(@PathVariable("id") Long id) {
		Map<String, Object> resMap = this.responseOK1("");
		Section section = new Section();
		section.setId(id);
		section.setSectionStatus(1);
		resMap.put("section", sectionService.getById(id));
		return resMap;
	}

}
