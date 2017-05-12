package com.sqe.shop.controller.bbs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.type.PrimitiveType;

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
import com.sqe.shop.model.Section;
import com.sqe.shop.model.Thread;
import com.sqe.shop.model.Topic;
import com.sqe.shop.model.User;
import com.sqe.shop.service.SectionService;
import com.sqe.shop.service.ThreadService;
import com.sqe.shop.service.TopicService;
import com.sqe.shop.service.UserService;
import com.sqe.shop.util.PageUtil;

@Controller
@RequestMapping("/bbs")
public class BBSFontController extends BaseFrontController {
	@Autowired
	private ThreadService threadService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private SectionService sectionService;
	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(BBSFontController.class);

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(Section section, Thread thread,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		ModelAndView model = new ModelAndView("bbs/index");
		section.setSectionType(0);
		PageUtil<Section> page = sectionService.getBeanListByParm(section, 0, -1);
		model.addObject("section", page);
		section.setSectionType(1);
		PageUtil<Section> pageSecond = sectionService.getBeanListByParm(section, 0, -1);
		model.addObject("sectionSecond", pageSecond);
		section.setSectionType(1);
		PageUtil<Map<String, Object>> pageThread = threadService.getSectionMapListByParm(thread, 0, -1);
		model.addObject("thread", pageThread);
		return model;
	}

	// thread 不存在显示为空
	@RequestMapping(value = "/thread", method = RequestMethod.GET)
	public ModelAndView thread(@RequestParam(name = "threadId") Long threadId) {
		ModelAndView model = new ModelAndView("bbs/thread");
		Thread thread = threadService.getById(threadId);
		User user = userService.getById(thread.getUserId());
		model.addObject("thread", thread);
		model.addObject("user", user);
		return model;
	}

	// 根据一级版块跳转二级版块index
	@RequestMapping(value = "/sectionindex", method = RequestMethod.GET)
	public ModelAndView section(Thread thread, @RequestParam(name = "sectionId") Long sectionId) {
		ModelAndView model = new ModelAndView("bbs/section");
		Section section = new Section();
		section.setSectionParentId(sectionId);
		PageUtil<Section> page = sectionService.getBeanListByParm(section, 0, -1);
		model.addObject("section", page);
		section.setId(sectionId);
		section.setSectionParentId(null);
		PageUtil<Map<String, Object>> pageThread = threadService.getSectionMapListByParm(section, thread, 0, -1);
		model.addObject("thread", pageThread);
		return model;
	}

	@RequestMapping(value = "/topic", method = RequestMethod.GET)
	public ModelAndView topic() {
		ModelAndView model = new ModelAndView("bbs/topic");
		return model;
	}

}
