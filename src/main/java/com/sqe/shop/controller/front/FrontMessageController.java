package com.sqe.shop.controller.front;

import java.util.Map;

import javax.mail.event.MessageChangedListener;
import javax.validation.constraints.NotNull;

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
		return messageService.getUserMessageListByParm(userId, pageNo - 1, pageSize);

	}

	@RequestMapping(value = "/getUserMessageList", method = RequestMethod.GET)
	public PageUtil<Map<String, Object>> getMessage(Long dialogueId,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		Long userId = this.getCurrentUserId();
		if (userId == null) {

			PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
			return pageUtil;
		}
		return messageService.getUserMessageListByDialogueId(dialogueId, pageNo - 1, pageSize);

	}

	@RequestMapping(value = "/deleteMessage", method = RequestMethod.GET)
	public Map<String, Object> deleteMessage(@NotNull Long messageId, @NotNull Long receiveId, @NotNull Long postId) {
		Long userId = this.getCurrentUserId();
		Message message = new Message();
		message.setId(messageId);
		message.setPostId(postId);
		message.setReceiveId(receiveId);
		int re = messageService.deleteMessage(message, userId);
		if (re == 1) {
			return responseOK1("");
		} else {
			return responseError(-1, "error_delete");
		}

	}

	@RequestMapping(value = "/sentMessage", method = RequestMethod.POST)
	public Map<String, Object> sentMessage(Message message) {
		Long userId = this.getCurrentUserId();
		// Long userId=2L;
		message.setPostId(userId);
		if (message.getMessageContext() == null) {
			return responseError(-1, "content_empty");
		}
		if (message.getReceiveId() == null) {
			return responseError(-1, "receiveID_null");
		}

		int re = messageService.sentMessage(message);
		if (re == 1) {
			return responseOK1("");
		} else {
			return responseError(-1, "error_sent");
		}

	}

}
