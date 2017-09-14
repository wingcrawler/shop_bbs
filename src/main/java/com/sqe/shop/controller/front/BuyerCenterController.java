package com.sqe.shop.controller.front;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.Constants;
import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.model.Comment;
import com.sqe.shop.model.Message;
import com.sqe.shop.model.Shop;
import com.sqe.shop.model.Thread;
import com.sqe.shop.model.User;
import com.sqe.shop.service.CommentService;
import com.sqe.shop.service.MessageService;
import com.sqe.shop.service.ShopService;
import com.sqe.shop.service.ThreadService;
import com.sqe.shop.service.UserService;
import com.sqe.shop.service.biz.BizUserCenterService;
import com.sqe.shop.service.file.ImageFileService;
import com.sqe.shop.util.PageUtil;

@Controller
@RequestMapping("/front/buy")
public class BuyerCenterController extends BaseFrontController {
	
	private static final Logger logger = LoggerFactory.getLogger(BuyerCenterController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private ImageFileService imageFileService;
	@Autowired
	private ThreadService threadService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private BizUserCenterService bizUserCenterService;
	
	/**
	 * 进入用户基本信息页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/basicInfo", method = RequestMethod.GET)
	public ModelAndView basicInfo(ModelAndView model){
		model.setViewName("shop/buy/user_center");
		User entity = userService.getById(this.getCurrentUserId());
		model.addObject("entity", entity);
		model.addObject("img", entity.getUserImage());
		model.addAllObjects(bizUserCenterService.getUserInfo());
		return model;
	}
	/**
	 * 修改用户基本信息
	 * @param user
	 * @param attachFile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/doSaveBasicInfo", method = RequestMethod.POST)
	public Map<String, Object> doSaveBasicInfo(User user,
			@RequestParam(name = "file",value="file", required = false) MultipartFile attachFile){
		return bizUserCenterService.doSaveBasicInfo(user, attachFile);
	}
	
	/**
	 * 进入修改密码页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/changePwd", method = RequestMethod.GET)
	public ModelAndView changePwd(ModelAndView model){
		model.setViewName("shop/buy/change_password");
		return model;
	}
	/**
	 * 修改密码
	 * @param oldPwd
	 * @param newPwd
	 * @param confirmPwd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/doChangePwd", method = RequestMethod.POST)
	public Map<String, Object> doChangePwd(String oldPwd, String newPwd, String confirmPwd){
		return bizUserCenterService.doChangePwd(oldPwd, newPwd, confirmPwd);
	}
	
	/**
	 * 发帖回复页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/postRecord", method = RequestMethod.GET)
	public ModelAndView postReply(ModelAndView model, Thread thread,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize){
		pageSize = 10;
		
		thread.setUserId(this.getCurrentUserId());
		PageUtil<Map<String, Object>> page = threadService.getSellThreadList(thread, pageNo, pageSize);
		
		model.addObject("page", page);
		model.setViewName("shop/buy/posting_record");
		return model;
	}
	
	/**
	 * 商品留言页面 
	 * @param model
	 * @param type 1:留言 2：私信
	 * @return
	 */
	@RequestMapping(value="/messagePage", method = RequestMethod.GET)
	public ModelAndView messagePage(ModelAndView model, String type,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		pageSize = 10;
		PageUtil<Map<String, Object>> page = new PageUtil<Map<String,Object>>();
		if(type.equals("1")){//产品评论
			page = bizUserCenterService.getProductCommentList(this.getCurrentUserId(), pageNo, pageSize);
			model.setViewName("shop/buy/my_product_comment");
		} else if(type.equals("2")){//私信
			page = bizUserCenterService.getMessageList(this.getCurrentUserId(), pageNo, pageSize);
			model.setViewName("shop/buy/my_message");
		} else if(type.equals("3")){//新闻资讯评论
			page = bizUserCenterService.getNewsCommentList(this.getCurrentUserId(), pageNo, pageSize);
			model.setViewName("shop/buy/my_news_comment");
		} else {
			model.setViewName("shop/404");
			return model;
		}
		
		model.addObject("type", type);
		model.addObject("page", page);
		return model;
	}
	
	/**
	 * 回复私信或者产品留言
	 * @param msg
	 * @param productId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/messageReply")
	public Map<String, Object> messageReply(String msgContent, String type,Long productId, Long messageId, Long commentId, Long replyToId) {
		if(StringUtils.isBlank(msgContent)){
			return responseError(-1, "error_empty_content");
		}
		if(type.equals("1")){//产品留言
			Comment comment = new Comment();
			comment.setContext(msgContent);
			comment.setDate(new Date());
			comment.setCommentId(commentId);
			comment.setUserId(this.getCurrentUserId());
			comment.setProductId(productId);
			comment.setStatus(Constants.COMMENT_ON); 
			commentService.insert(comment);
		} else if(type.equals("2")){//私信
			Message message = new Message();
			message.setMessageContext(msgContent);
			message.setCreateTime(new Date());
			message.setProductId(productId);
			message.setPostId(this.getCurrentUserId());
			message.setReceiveId(replyToId);
			message.setMessageStatus(Constants.MSG_ON);
			message.setMessageId(messageId);
			messageService.insert(message);
		} else {
			responseError(-1, "error_illegal");
		}
		return responseOK1("");
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteImg", method = RequestMethod.GET)
	public Map<String, Object> deleteImg(Long id, String type){
		if(id==null || StringUtils.isBlank(type)){
			return responseError(-1, "error_unknow");
		}

		User user = new User();
		user.setId(id);
		if(type.equals("avatar")){
			user.setUserImage("");
		}
		userService.update(user);
		return responseOK1("");
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteUserImg", method = RequestMethod.GET)
	public Map<String, Object> deleteUserImg(Long id, String type){
		if(id==null || StringUtils.isBlank(type)){
			return responseError(-1, "error_unknow");
		}

		User user = new User();
		user.setId(id);
		if(type.equals("avatar")){
			user.setUserImage("");
		}
		userService.update(user);
		
		Subject subject = SecurityUtils.getSubject();
		User userInfo = userService.getById(id);
		subject.getSession().setAttribute("userInfo", userInfo);
		
		return responseOK1("");
	}
	
	/**
	 * 申请开店页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/applayShop", method = RequestMethod.GET)
	public ModelAndView applayShop(ModelAndView model){
		Long userId = this.getCurrentUserId();
		model.addAllObjects(bizUserCenterService.shopInfo(userId));
		model.setViewName("shop/buy/applay_shop");
		return model;
	}
	/**
	 * 用户开店
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/doApplayShop", method = RequestMethod.POST)
	public Map<String, Object> doApplayShop(Shop shop,
			@RequestParam(name = "file", required = false) MultipartFile attachFile){
		return bizUserCenterService.doApplayShop(shop, attachFile);
	}
	
}
