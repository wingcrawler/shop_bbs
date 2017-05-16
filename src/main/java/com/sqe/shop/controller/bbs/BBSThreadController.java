package com.sqe.shop.controller.bbs;

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

import com.sqe.shop.controller.base.BaseBackendController;
import com.sqe.shop.model.Thread;
import com.sqe.shop.model.Topic;
import com.sqe.shop.service.ThreadService;
import com.sqe.shop.service.TopicService;
import com.sqe.shop.util.PageUtil;

@Controller
@RequestMapping("/bbs")
public class BBSThreadController extends BaseBackendController {

	private static final Logger logger = LoggerFactory.getLogger(BBSThreadController.class);

	@Autowired
	private ThreadService threadService;
	@Autowired
	private TopicService topicService;

	@RequestMapping(value = "/thread/list", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("backend/bbs/list");
		List<Topic> topicList = topicService.getBeanListByParm("TopicMapper", null);
		model.addObject("topicList", topicList);
		return model;
	}

	@RequestMapping(value = "/thread/edit", method = RequestMethod.GET)
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("backend/bbs/list_edit");
		if (id != null) {
			Map<String, Object> entity = threadService.getMapById(id);
			model.addObject("entity", entity);
		}
		return model;
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
	@RequestMapping(value = "/thread/getList", method = RequestMethod.GET)
	public Map<String, Object> getList(Thread thread, @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		PageUtil<Map<String, Object>> page = threadService.getMapListByParm(thread, pageNo, pageSize);
		resMap.put("list", page.getList());
		resMap.put("page", page);
		return resMap;
	}

	@ResponseBody
	@RequestMapping(value = "/thread/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(Thread thread) {
		if (StringUtils.isBlank(thread.getThreadTitle())) {
			return responseError(-1, "error_empty_title");
		}
		if (StringUtils.isBlank(thread.getThreadContext())) {
			return responseError(-1, "error_empty_content");
		}
		threadService.save(thread);
		return responseOK("save_success");
	}

	@ResponseBody
	@RequestMapping(value = "/thread/doDelete", method = RequestMethod.GET)
	public Map<String, Object> doDelete(Long id) {
		if (id == null) {
			return responseError(-1, "error_no_item");
		}
		int i = threadService.delete(id);
		if (i == 0) {
			return responseError(-1, "error_del_failed");
		}
		return responseOK("op_success");
	}

}
