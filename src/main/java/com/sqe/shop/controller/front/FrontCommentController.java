package com.sqe.shop.controller.front;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sqe.shop.common.Constants;
import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.model.Comment;
import com.sqe.shop.service.CommentService;

@Controller
@RequestMapping("/comment")
public class FrontCommentController extends BaseFrontController {
	
	private static final Logger logger = LoggerFactory.getLogger(FrontCommentController.class);
	
	@Autowired
	private CommentService commentService;
	
	/**
	 * 评论
	 * @param comment
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="doComment", method = RequestMethod.GET)
	public Map<String, Object> doComment(Comment comment) {
		if(StringUtils.isBlank(comment.getContext())){
			return responseError(-1, "error_empty_content");
		}
		comment.setStatus(Constants.COMMENT_ON);
		comment.setUserId(this.getCurrentUserId());
		comment.setDate(new Date());
		commentService.insert(comment);
		return responseOK("success_comment");
	}
}
