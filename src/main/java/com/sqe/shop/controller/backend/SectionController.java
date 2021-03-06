package com.sqe.shop.controller.backend;

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

import com.sqe.shop.common.Constants;
import com.sqe.shop.controller.base.BaseBackendController;
import com.sqe.shop.model.Section;
import com.sqe.shop.service.SectionService;
import com.sqe.shop.util.PageUtil;

@Controller
@RequestMapping("/backend/section")
public class SectionController extends BaseBackendController {
	
	private static final Logger logger = LoggerFactory.getLogger(SectionController.class);
	
	@Autowired
	private SectionService sectionService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("backend/bbs/plate");
		return model;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("backend/bbs/plate_edit");
		if(id!=null){
			Section entity = sectionService.getById(id);
			model.addObject("entity", entity);
		}
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getFirstList", method = RequestMethod.GET)
	public Map<String, Object> getFirstList(Section section,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  @RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		section.setSectionType(0);
		Map<String, Object> resMap = new HashMap<String, Object>();
		PageUtil<Section> page = sectionService.getBeanListByParm(section, 0, -1);
		resMap.put("list", page.getList());
		resMap.put("page", page);
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/getSecondList", method = RequestMethod.GET)
	public Map<String, Object> getList(Section section,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  @RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		section.setSectionType(Constants.SECTION_ON);
		Map<String, Object> resMap = new HashMap<String, Object>();
		PageUtil<Section> page = sectionService.getBeanListByParm(section, pageNo, pageSize);
		resMap.put("list", page.getList());
		resMap.put("page", page);
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(Section section) {
		if(StringUtils.isBlank(section.getSectionTitle())){
			return responseError(-1, "error_empty_title");
		}
		sectionService.save(section);
		return responseOK("save_success");
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete", method = RequestMethod.GET)
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, "error_no_item");
		}
		int i = sectionService.delete(id);
		if(i==0){
			return responseError(-1, "error_del_failed");
		}
		return responseOK("op_success");
	}

}
