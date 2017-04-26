package com.sqe.shop.controller.backend;

import java.util.ArrayList;
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

import com.sqe.shop.common.BaseController;
import com.sqe.shop.model.Section;
import com.sqe.shop.model.Topic;
import com.sqe.shop.service.SectionService;
import com.sqe.shop.service.TopicService;
import com.sqe.shop.util.PageUtil;

@Controller
@RequestMapping("/backend/topic")
public class TopicController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(TopicController.class);
	
	@Autowired
	private TopicService topicService;
	@Autowired
	private SectionService sectionService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("backend/bbs/topic");
		model.addObject("secondSectionList", getSectonSectionList());
		return model;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("backend/bbs/topic_edit");
		if(id!=null){
			Topic entity = topicService.getById(id);
			model.addObject("entity", entity);
		}
		model.addObject("secondSectionList", getSectonSectionList());
		return model;
	}
	
	private List<Section> getSectonSectionList() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sectionType", 1);
		List<Section> list = sectionService.getBeanListByParm("SectionMapper", paramMap);
		if(list==null || list.isEmpty()){
			return new ArrayList<Section>();
		}
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList", method = RequestMethod.GET)
	public Map<String, Object> getList(Topic topic,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  @RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		PageUtil<Map<String, Object>> page = topicService.getMapListByParm(topic, pageNo, pageSize);
		resMap.put("list", page.getList());
		resMap.put("page", page);
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(Topic topic) {
		if(StringUtils.isBlank(topic.getTopicTitle())){
			return responseError(-1, "error_empty_topic");
		}
		if(StringUtils.isBlank(topic.getTopicDescription())){
			return responseError(-1, "error_empty_description");
		}
		topicService.save(topic);
		return responseOK("save_success");
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete", method = RequestMethod.GET)
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, "error_no_item");
		}
		int i = topicService.delete(id);
		if(i==0){
			return responseError(-1, "error_del_failed");
		}
		return responseOK("op_success");
	}

}
