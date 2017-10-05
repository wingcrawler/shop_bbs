package com.sqe.shop.controller.front;

import java.util.Map;

import javax.mail.event.MessageChangedListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.model.Message;
import com.sqe.shop.model.User;
import com.sqe.shop.service.MessageService;
import com.sqe.shop.util.PageUtil;

/**
 * 
 * @author Andy
 *
 */
@RequestMapping("/front/message")
@RestController
public class FrontMessageController extends BaseFrontController {

	@Autowired
	private MessageService messageService;

	/**
	 * 
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/getUserDialogueList", method = RequestMethod.GET)
	public PageUtil<Map<String, Object>> getMessageList(Long userId,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		userId = this.getCurrentUserId();
		return messageService.getUserMessageListByParm(userId, pageNo, pageSize);

	}
	
	@RequestMapping(value = "/getUserMessageList", method = RequestMethod.GET)
	public PageUtil<Map<String, Object>> getMessage(Long dialogueId,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		Long userId = this.getCurrentUserId();
		if(userId==null){
			
			PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
			return pageUtil;	
		}
		return messageService.getUserMessageListByDialogueId(dialogueId, pageNo, pageSize);

	}

}
