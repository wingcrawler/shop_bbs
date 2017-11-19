package com.sqe.shop.controller.api;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sqe.shop.common.Constants;
import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.model.Comment;
import com.sqe.shop.service.CommentService;
import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.Resp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/comment")
@Api("评论接口")
public class ApiCommentController extends BaseFrontController {

	private static final Logger logger = LoggerFactory.getLogger(ApiCommentController.class);

	@Autowired
	private CommentService commentService;

	/**
	 * 评论
	 * 
	 * @param comment
	 * @return
	 */
	@RequestMapping(value = "doComment", method = RequestMethod.POST)
	@ApiOperation(value = " 发表评论", notes = "对新闻 、产品、用户、店铺 发表评论  ")
	public Resp<?> doComment(@RequestBody Comment comment) {
		if (StringUtils.isBlank(comment.getContext())) {
			return Resp.customFail("-1", "error_empty_content");
		}
		comment.setStatus(Constants.COMMENT_ON);
		comment.setUserId(this.getCurrentUserId());
		comment.setDate(new Date());
		commentService.insert(comment);
		return Resp.success("success");
	}

	/**
	 * 根据条件获取评论列表 前台
	 */
	@ApiOperation(value = " 获取评论列表", notes = "获取评论列表")
	@RequestMapping(value = "Comment", method = RequestMethod.GET)
	public Resp<?> getComment (Comment comment,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		comment.setStatus(Constants.COMMENT_ON);
		PageUtil<Comment> result = commentService.getBeanListByParm(comment, pageNo, pageSize);
		if (result.getList().size() == 0) {
			return Resp.notFound("not found comment");
		}
		return Resp.success(result);
	}

}
