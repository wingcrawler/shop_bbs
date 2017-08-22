package com.sqe.shop.service.biz;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sqe.shop.common.BaseCommon;
import com.sqe.shop.model.Section;
import com.sqe.shop.service.PostService;
import com.sqe.shop.service.SectionService;
import com.sqe.shop.service.ThreadService;
import com.sqe.shop.util.PageUtil;

@Component
public class BizBBSService extends BaseCommon {

	@Autowired
	private PostService postService;

	@Autowired
	private SectionService sectionService;

	@Autowired
	private ThreadService threadService;

	public Map<String, Object> getHomeIndexData() {
		Map<String, Object> resMap = this.responseOK1("");

		// 一级板块
		Section section = new Section();
		section.setSectionType(0);
		PageUtil<Map<String, Object>> sectionList = sectionService.getMapListByParm(section, 1, -1);
		resMap.put("sections", sectionList);

		// 热门帖子
		

		return null;

	}

}
