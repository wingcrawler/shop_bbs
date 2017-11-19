package com.sqe.shop.controller.api;

import java.util.Map;

import javax.mail.event.MessageChangedListener;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.model.Message;
import com.sqe.shop.model.User;
import com.sqe.shop.service.MessageService;
import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.Resp;

/**
 * 
 * @author Andy
 *
 */
@RequestMapping("api/message")
@RestController
public class ApiMessageController extends BaseFrontController {

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
	public Resp<PageUtil<Map<String, Object>>> getMessageList(@RequestParam Long userId,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		userId = this.getCurrentUserId();
		return Resp.success(messageService.getUserMessageListByParm(userId, pageNo - 1, pageSize));

	}

	@RequestMapping(value = "/getUserMessageList", method = RequestMethod.GET)
	public Resp<?> getMessage(@RequestParam Long dialogueId, @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		Long userId = this.getCurrentUserId();
		if (userId == null) {
			return Resp.customFail("-1", "need loginin");
		}
		return Resp.success(messageService.getUserMessageListByDialogueId(dialogueId, pageNo - 1, pageSize));

	}

	@RequestMapping(value = "/deleteMessage", method = RequestMethod.GET)
	public Resp<?> deleteMessage(@RequestParam @NotNull Long messageId, @RequestParam @NotNull Long receiveId, @RequestParam @NotNull Long postId) {
		Long userId = this.getCurrentUserId();
		Message message = new Message();
		message.setId(messageId);
		message.setPostId(postId);
		message.setReceiveId(receiveId);
		int re = messageService.deleteMessage(message, userId);
		if (re == 1) {
			return Resp.success("");
		} else {
			return Resp.customFail("-1", "error_delete");
		}

	}

	@RequestMapping(value = "/sentMessage", method = RequestMethod.POST)
	public Resp<?> sentMessage( Message message) {
		Long userId = this.getCurrentUserId();
		// Long userId=2L;
		message.setPostId(userId);
		if (message.getMessageContext() == null) {
			return Resp.customFail("-1", "content_empty");
		}
		if (message.getReceiveId() == null) {
			return Resp.customFail("-1", "receiveID_null");
		}

		int re = messageService.sentMessage(message);
		if (re == 1) {
			return Resp.success("");
		} else {
			return Resp.customFail("-1", "error");
		
		}

	}

}
