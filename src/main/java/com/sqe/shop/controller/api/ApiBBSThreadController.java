package com.sqe.shop.controller.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.model.Likes;
import com.sqe.shop.model.News;
import com.sqe.shop.model.Post;
import com.sqe.shop.model.Section;
import com.sqe.shop.model.Thread;
import com.sqe.shop.service.LikesService;
import com.sqe.shop.service.PostService;
import com.sqe.shop.service.SectionService;
import com.sqe.shop.service.ThreadService;
import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.PropertiesUtil;
import com.sqe.shop.util.Resp;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("api/bbs")
public class ApiBBSThreadController extends BaseFrontController {

	private static final Logger logger = LoggerFactory.getLogger(ApiBBSThreadController.class);

	@Autowired
	private ThreadService threadService;

	@Autowired
	private SectionService sectionService;

	@Autowired
	private PostService postService;
	
	@Autowired
	private LikesService likeService;


	/**
	 *
	 * 
	 * @param thread
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/thread/getList", method = RequestMethod.POST)
	@ApiOperation(value = "根据版块 Thread Object 返回列表", notes = "根据 thread 参数获取列表 ")
	public Resp<?> getList(@RequestBody Thread thread, @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		thread.setThreadStatus(THREAD_ON);
		PageUtil<Map<String, Object>> page = threadService.getMapListByParm(thread, pageNo, pageSize);
		return Resp.success(page);
	}

	/**
	 * 根据版块id 返回列表
	 * 
	 * @param thread
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/thread/getSectionList", method = RequestMethod.GET)
	@ApiOperation(value = "根据版块id 返回列表", notes = "根据版块sectionId 返回列表 ")
	public Resp<?> getSectionList(@RequestParam Long sectionId,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		if (sectionId == null) {
			return Resp.customFail("-1", "sectionId null");
		}
		Thread thread = new Thread();
		thread.setSectionId(sectionId);
		Section section = sectionService.getById(thread.getSectionId());
		logger.info("section={}", JSON.toJSON(section));
		if (section.getSectionType() == 0) {
			PageUtil<Map<String, Object>> page = threadService.getSectionOneThreadList(pageNo, pageSize,
					section.getId());
			return Resp.success(page);
		} else {
			PageUtil<Map<String, Object>> page = threadService.getSectionMapListByParm(thread, pageNo, pageSize);
			return Resp.success(page);
		}

	}

	/**
	 * 
	 * @param thread
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/thread/doSave", method = RequestMethod.POST)
	@ApiOperation(value = "发帖", notes = "发帖  编辑  保存帖子 ")
	public Resp<?> save(@RequestBody Thread thread) {
		if (StringUtils.isBlank(thread.getThreadTitle())) {
			return Resp.customFail("-1", "error_empty_title");
		}
		if (StringUtils.isBlank(thread.getThreadContext())) {
			return Resp.customFail("-1", "error_empty_content");
		}
		thread.setUserId(this.getCurrentUserId());
		thread.setThreadTime(new Date());
		thread.setThreadStatus(1);
		thread.setThreadView(5L);
		thread.setThreadUp(1L);
		threadService.save(thread);
		return Resp.success("save_success");
	}

	/**
	 * 根据ID 删除帖子
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/thread/doDelete", method = RequestMethod.GET)
	@ApiOperation(value = "根据ID 删除帖子", notes = "根据ID 删除帖子")
	public Resp<?> doDelete(@RequestParam Long id) {
		if (id == null) {
			return Resp.customFail("-1", "error_no_item");
		}
		int i = threadService.delete(id);
		if (i == 0) {
			return Resp.customFail("-1", "error_del_failed");
		}
		return Resp.success("save_success");

	}

	/**
	 * 查询一级section分类下的thread
	 * 
	 * @param thread
	 * @param sectionId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/thread/getSectionOneThreadList", method = RequestMethod.GET)
	@ApiOperation(value = "查询一级section分类下的threads", notes = "查询一级section分类下的thread  所属一级分类")
	public Resp<?> getSectionOneThreadList(@RequestParam Long sectionId,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		PageUtil<Map<String, Object>> page = threadService.getSectionOneThreadList(pageNo, pageSize, sectionId);
		return Resp.success(page);
	}

	/**
	 * 获取最新的帖子 首页显示
	 * 
	 * @param thread
	 * @param sectionId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/hotThread", method = RequestMethod.GET)
	@ApiOperation(value = "获取最新的帖子  首页显示", notes = "获取最新的帖子  首页显示")
	public Resp<?> getHotThreadList(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
		Thread temp = new Thread();
		temp.setThreadStatus(THREAD_ON);
		PageUtil<Map<String, Object>> page = threadService.getHot(temp, pageNo, pageSize);
		return Resp.success(page);
	}

	/**
	 * 获取管理员推荐的帖子(最新发现)
	 * 
	 * @param thread
	 * @param sectionId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/recommendThread", method = RequestMethod.GET)
	@ApiOperation(value = "获取管理员推荐的帖子(最新发现)", notes = "获取管理员推荐的帖子(最新发现)")
	public Resp<?> getRecommendThreadList(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "3") int pageSize) {
		Thread temp = new Thread();
		temp.setThreadStatus(THREAD_RECOMMEND);
		PageUtil<Map<String, Object>> page = threadService.getHot(temp, pageNo, pageSize);
		return Resp.success(page);
	}

	/**
	 * 发表帖子
	 * 
	 * @param thread
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/thread", method = RequestMethod.POST)
	@ApiOperation(value = "发表帖子", notes = "发表帖子")
	public Resp<?> thread(@RequestBody Thread thread) {
		Long userId = this.getCurrentUserId();
		if (userId == null) {
			return Resp.customFail("-1", "未登录");
		}
		if (StringUtils.isBlank(thread.getThreadContext())) {
			return Resp.customFail("-1", "内容不能为空");
		}
		if (StringUtils.isBlank(thread.getThreadTitle())) {
			return Resp.customFail("-1", "标题不能为空");
		}
		if (thread.getSectionId() == null) {
			return Resp.customFail("-1", "请选择板块");
		}
		thread.setUserId(userId);
		thread.setThreadStatus(THREAD_ON);
		thread.setThreadTime(new Date());
		thread.setThreadView(1L);
		thread.setThreadUp(1L);
		int result = threadService.insert(thread);
		return Resp.success(result);
	}

	/**
	 * 根据id 获取帖子详情
	 * 
	 * @param id
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/bbs/thread/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "根据id 获取帖子详情", notes = "根据id 获取帖子详情 /bbs/thread/{id}")
	public Resp<?> postById(@PathVariable("id") Long id) {
		Map<String, Object> resMap = this.responseOK1("");
		Thread threaddetil = new Thread();
		threaddetil.setThreadStatus(1);
		threaddetil.setId(id);
		threaddetil = threadService.getById(id);
		if (threaddetil == null) {
			return Resp.notFound( "error_empty_thread");
		}
		resMap.put("thread", threaddetil);
		Post post=new Post();
		post.setThreadId(id);
		resMap.put("threadPostNum", postService.countByParm(post));
		return Resp.success(resMap);
	}

	/**
	 * 底部两条带内容的帖子 管理员推荐的两篇帖子
	 * 
	 * @param thread
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/thread/bottomThreads", method = RequestMethod.GET)
	@ApiOperation(value = "底部两条带内容的帖子 管理员推荐的两篇帖子", notes = "底部两条带内容的帖子 管理员推荐的两篇帖子")
	public Resp<?> bottomThread() {
		Thread thread = new Thread();
		thread.setThreadStatus(THREAD_RECOMMEND);
		PageUtil<Thread> result = threadService.getBeanListByParm(thread, 1, 2);
		return Resp.success(result);
	}
	
	/**
	 * 帖子点赞
	 * 
	 * @param bbs
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "doBBSUp", method = RequestMethod.GET)
	@ApiOperation(value = "帖子点赞", notes = "后台控制 防止重复  前台效果点击加1")
	public Resp<?> thumb(@RequestParam Long id) {
		Thread threaddetil = new Thread();
		
		threaddetil = threadService.getById(id);
		if (threaddetil == null) {
			return Resp.customFail("-1", "error bbs id");
		}
		// 点赞
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("threadId", id);
		parmMap.put("userId", getCurrentUserId());
		int thumbCount1 = likeService.countByParm(parmMap);
		if (thumbCount1 > 0) {
			String lang = PropertiesUtil.get("lang");
			if (lang.equals("zh")) {
				return Resp.success("已经点赞");
			} else {
				return Resp.success("You're already thumb up");
			}
		} else {
			Likes likes = new Likes();
			likes.setThreadId(id);
			likes.setUserId(getCurrentUserId());
			likes.setCreateTime(new Date(System.currentTimeMillis()));
			likeService.insert(likes);
			threadService.updateThumb(threaddetil.getThreadUp(), id);
			/* return responseOK("success_thumb"); */
			return Resp.success("success");
		}

	}


}
