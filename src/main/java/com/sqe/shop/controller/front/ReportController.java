package com.sqe.shop.controller.front;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.BaseBackendController;
import com.sqe.shop.model.Inform;
import com.sqe.shop.service.InformService;

@Controller
@RequestMapping("/front")
public class ReportController extends BaseBackendController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
	
	@Autowired
	private InformService informService;
	
	/**
	 * 举报页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/reportPage", method = RequestMethod.GET)
	public ModelAndView reportPage(ModelAndView model) {
		model.setViewName("shop/report");
		return model;
	}
	
	/**
	 * 举报
	 * @param inform
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/reportMessage", method = RequestMethod.GET)
	public Map<String, Object> reportMessage(Inform inform) {
		Long userId = this.getCurrentUserId();
		inform.setUserPostId(userId);
		inform.setInfromStatus(0);
		inform.setInfromDate(new Date());
		informService.insert(inform);
		return responseOK("report_success");
	}
	
}
