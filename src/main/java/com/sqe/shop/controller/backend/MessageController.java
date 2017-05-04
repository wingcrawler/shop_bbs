package com.sqe.shop.controller.backend;

import java.util.HashMap;
import java.util.Map;

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
import com.sqe.shop.model.Message;
import com.sqe.shop.service.MessageService;
import com.sqe.shop.util.PageUtil;

@Controller
@RequestMapping("/backend/message")
public class MessageController extends BaseBackendController {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("backend/message/list");
		return model;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("backend/message/edit");
		if(id!=null){
			Message entity = messageService.getById(id);
			model.addObject("entity", entity);
		}
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList", method = RequestMethod.GET)
	public Map<String, Object> getList(Message message,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  @RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		PageUtil<Message> page = messageService.getBeanListByParm(message, pageNo, pageSize);
		resMap.put("list", page.getList());
		resMap.put("page", page);
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(Message message) {
		messageService.save(message);
		return responseOK("save_success");
	}
	
	@ResponseBody
	@RequestMapping(value="/read", method = RequestMethod.POST)
	public Map<String, Object> read(Message message) {
		message.setMessageStatus(1);
		messageService.update(message);
		return responseOK("save_success");
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete", method = RequestMethod.GET)
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, "error_no_item");
		}
		int i = messageService.delete(id);
		if(i==0){
			return responseError(-1, "error_del_failed");
		}
		return responseOK("op_success");
	}

}
