package com.sqe.shop.controller.bbs;

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

import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.model.Section;
import com.sqe.shop.service.SectionService;
import com.sqe.shop.util.PageUtil;

@Controller
@RequestMapping("/bbs/section")
public class BBSSectionController extends BaseFrontController {

	private static final Logger logger = LoggerFactory.getLogger(BBSSectionController.class);

	@Autowired
	private SectionService sectionService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("bbs/section/list");
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "/getFirstSection", method = RequestMethod.GET)
	public Map<String, Object> getFirstList(Section section,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		section.setSectionType(0);
		Map<String, Object> resMap = new HashMap<String, Object>();
		PageUtil<Section> page = sectionService.getBeanListByParm(section, 0, -1);
		resMap.put("list", page.getList());
		resMap.put("page", page);
		return resMap;
	}


	@ResponseBody
	@RequestMapping(value = "/getSecondSection", method = RequestMethod.GET)
	public Map<String, Object> getList(Section section, @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "20") int pageSize) {
		section.setSectionType(1);
		Map<String, Object> resMap = new HashMap<String, Object>();
		PageUtil<Section> page = sectionService.getBeanListByParm(section, pageNo, pageSize);
		resMap.put("list", page.getList());
		resMap.put("page", page);
		return resMap;
	}
	

	@ResponseBody
	@RequestMapping(value = "/getSecondSectionby", method = RequestMethod.GET)
	public Map<String, Object> getList(Section section, @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "20") int pageSize,Long ParentId) {
		section.setSectionType(1);
		section.setSectionParentId(ParentId);
		Map<String, Object> resMap = new HashMap<String, Object>();
		PageUtil<Section> page = sectionService.getBeanListByParm(section, pageNo, pageSize);
		resMap.put("list", page.getList());
		resMap.put("page", page);
		return resMap;
	}
}
