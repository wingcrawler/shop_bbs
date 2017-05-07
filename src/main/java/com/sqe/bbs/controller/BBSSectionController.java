/*package com.sqe.bbs.controller;

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

import com.sqe.shop.common.BaseBackendController;
import com.sqe.shop.common.BaseFrontController;
import com.sqe.shop.model.Section;
import com.sqe.bbs.service.BBSSectionService;
import com.sqe.shop.util.PageUtil;

@Controller
@RequestMapping("/bbs/section")
public class BBSSectionController extends BaseFrontController {

	private static final Logger logger = LoggerFactory.getLogger(BBSSectionController.class);

	@Autowired
	private BBSSectionService sectionService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("bbs/section/list");
		return model;
	}

	*//**
	 * 获取一级版块列表0
	 * 
	 * @param section
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *//*
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

	*//**
	 * 获取二级版块列表0
	 * @param section
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *//*
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
	
	*//**
	 * 获取二级版块列表0
	 * @param section
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping(value = "/getSecondSectionby", method = RequestMethod.GET)
	public Map<String, Object> getList(Section section, @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "20") int pageSize,int ParentId) {
		section.setSectionType(1);
		Map<String, Object> resMap = new HashMap<String, Object>();
		PageUtil<Section> page = sectionService.getBeanListByParm(section,ParentId, pageNo, pageSize);
		resMap.put("list", page.getList());
		resMap.put("page", page);
		return resMap;
	}
}
*/