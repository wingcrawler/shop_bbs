package com.sqe.shop.controller.front;

import java.util.HashMap;
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
import com.sqe.shop.model.Message;
import com.sqe.shop.service.MessageService;

@Controller
@RequestMapping("/front/report")
public class ReportController extends BaseBackendController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
	
	@Autowired
	private MessageService messageService;
	
	@ResponseBody
	@RequestMapping(value="/reportMessage", method = RequestMethod.GET)
	public Map<String, Object> reportMessage(Message message) {
		Long userId = this.getCurrentUserId();
		message.setPostId(userId);
		message.setReceiveId(-1L);
		messageService.insert(message);
		return responseOK1("ok");
	}
	
}
