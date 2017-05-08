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
import com.sqe.shop.service.SectionService;
import com.sqe.shop.service.ThreadService;
import com.sqe.shop.service.TopicService;
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

	private static final Logger logger = LoggerFactory.getLogger(BBSFontController.class);
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public ModelAndView index(Section section,Thread thread,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  @RequestParam(name="pageSize", defaultValue="10") int pageSize) {
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
	
	@RequestMapping(value="/plate", method = RequestMethod.GET)
	public ModelAndView plate() {
		ModelAndView model = new ModelAndView("bbs/plate");
		return model;
	}
	
	@RequestMapping(value="/topic", method = RequestMethod.GET)
	public ModelAndView topic() {
		ModelAndView model = new ModelAndView("bbs/topic");
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList", method = RequestMethod.GET)
	public Map<String, Object> getList() {
		Map<String, Object> resMap = new HashMap<String, Object>();
		return resMap;
	}
	
}
