package com.sqe.shop.controller.front;

import java.util.Date;
import java.util.HashMap;
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
import com.sqe.shop.model.Thread;
import com.sqe.shop.model.User;
import com.sqe.shop.service.CommentService;
import com.sqe.shop.service.MessageService;
import com.sqe.shop.service.ThreadService;
import com.sqe.shop.service.UserService;
import com.sqe.shop.service.file.ImageFileService;
import com.sqe.shop.util.DateUtil;
import com.sqe.shop.util.MD5Util;
import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.PropertiesUtil;

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
			@RequestParam(name = "attachFile",value="attachFile", required = false) MultipartFile attachFile){
		if(StringUtils.isBlank(user.getUsername())){
			return responseError(-1, "error_empty_username");
		}
		if(user.getId()==null){
			return responseError(-1, "error_illegal");
		}
		if(!user.getId().equals(this.getCurrentUserId())){
			return responseError(-1, "error_illegal");
		}
		
		if(attachFile!=null){
		    Map<String, Object> resMap = imageFileService.uploadImage(attachFile);
		    String fileName = resMap.get("errorInfo").toString(); 
		    if(!resMap.get("errorNo").equals(0)){
		    	return resMap;
		    }
		    String uploadPath = PropertiesUtil.get("upload_path_save"); 
		    uploadPath += DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1)+"/";
		    user.setUserImage(uploadPath+fileName);
	    }

		userService.save(user);
		
		Subject subject = SecurityUtils.getSubject();
		User userInfo = (User) subject.getSession().getAttribute("userInfo");
		userInfo.setUserImage(user.getUserImage());
		subject.getSession().setAttribute("userInfo", userInfo);
		
		return responseOK1("");
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
		if(StringUtils.isBlank(oldPwd)){
			return responseError(-1, "error_empty_oldpwd");
		} else if(StringUtils.isBlank(newPwd)){
			return responseError(-1, "error_empty_newpwd");
		} else if(StringUtils.isBlank(confirmPwd)){
			return responseError(-1, "error_empty_confirmpwd");
		} else if(!newPwd.equals(confirmPwd)){
			return responseError(-1, "error_twopwd_not_match");
		}
		
		MD5Util md5 = new MD5Util(MD5Util.SALT, "MD5");
		String oldPwdMd5 = md5.encode(oldPwd);
		User user = userService.getById(this.getCurrentUserId());
		if(!user.getPassword().equals(oldPwdMd5)){
			return responseError(-1, "error_oldpwd");
		}
		
		String newPwdMd5 = md5.encode(newPwd);
		user.setPassword(newPwdMd5);
		userService.update(user);
		return responseOK1("");
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
			page = getProductCommentList(this.getCurrentUserId(), pageNo, pageSize);
			model.setViewName("shop/buy/my_product_comment");
		} else if(type.equals("2")){//私信
			page = getMessageList(this.getCurrentUserId(), pageNo, pageSize);
			model.setViewName("shop/buy/my_message");
		} else if(type.equals("3")){//新闻资讯评论
			page = getNewsCommentList(this.getCurrentUserId(), pageNo, pageSize);
			model.setViewName("shop/buy/my_news_comment");
		} else {
			model.setViewName("shop/404");
			return model;
		}
		
		model.addObject("type", type);
		model.addObject("page", page);
		return model;
	}
	
	public PageUtil<Map<String, Object>> getProductCommentList(Long userId, int pageNo, Integer pageSize) {
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("userId", userId);
		parmMap.put("nullCommentId", true);
		parmMap.put("orderby", "c.date desc");
		PageUtil<Map<String, Object>> msgPage = commentService.getProductCommentListByParm(parmMap, pageNo, pageSize);
		return msgPage;
	}
	
	public PageUtil<Map<String, Object>> getNewsCommentList(Long userId, int pageNo, Integer pageSize) {
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("userId", userId);
		parmMap.put("nullCommentId", true);
		parmMap.put("orderby", "c.date desc");
		PageUtil<Map<String, Object>> msgPage = commentService.getNewsCommentListByParm(parmMap, pageNo, pageSize);
		return msgPage;
	}
	/**
	 * 私信列表
	 * @param message
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageUtil<Map<String, Object>> getMessageList(Long userId, int pageNo, Integer pageSize) {
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("postId", userId);
		parmMap.put("nullMessageId", true);
		parmMap.put("orderby", "m.create_time desc");
		PageUtil<Map<String, Object>> msgPage = messageService.getSellerMessageListByParm(parmMap, pageNo, pageSize);
		return msgPage;
	}
	
	/**
	 * 回复私信或者产品留言
	 * @param msg
	 * @param productId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/messageReply", method = RequestMethod.POST)
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
}
