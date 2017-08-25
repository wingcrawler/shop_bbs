package com.sqe.shop.controller.mobile;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.model.Shop;
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
@RequestMapping("/h5/user")
public class MobileUserController extends BaseFrontController {
	
	@Autowired
	private BizUserCenterService bizUserCenterService;
	
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

	/**
	 * 获取用户基本信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/userInfo")
	public Map<String, Object> userInfo() {
		Long userId = this.getCurrentUserId();
		if(userId==null){
			return this.responseError1(ERRORCODE_NOLOGIN, "no login");
		}
		return bizUserCenterService.getUserInfo();
	}
	
	/**
	 * 修改密码
	 * @param oldPwd
	 * @param newPwd
	 * @param confirmPwd
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doChangePwd")
	public Map<String, Object> doChangePwd(String oldPwd, String newPwd, String confirmPwd){
		Long userId = this.getCurrentUserId();
		if(userId==null){
			return this.responseError1(ERRORCODE_NOLOGIN, "no login");
		}
		return bizUserCenterService.doChangePwd(oldPwd, newPwd, confirmPwd);
	}

	/**
	 * 保存用户基本信息
	 * @param user
	 * @param attachFile
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doSaveBasicInfo")
	public Map<String, Object> doSaveBasicInfo(User user,
			@RequestParam(name = "file", required = false) MultipartFile attachFile){
		Long userId = this.getCurrentUserId();
		if(userId==null){
			return this.responseError1(ERRORCODE_NOLOGIN, "no login");
		}
		return bizUserCenterService.doSaveBasicInfo(user, attachFile);
	}
	
	/**
	 * 店铺信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/shopInfo", method = RequestMethod.GET)
	public Map<String, Object> shopInfo(){
		Long userId = this.getCurrentUserId();
		if(userId==null){
			return this.responseError1(ERRORCODE_NOLOGIN, "no login");
		}
		return bizUserCenterService.shopInfo(userId);
	}
	
	/**
	 * 保存用户开店信息
	 * @param shop
	 * @param attachFile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/doApplayShop")
	public Map<String, Object> doApplayShop(Shop shop,
			@RequestParam(name = "file", required = false) MultipartFile attachFile){
		Long userId = this.getCurrentUserId();
		if(userId==null){
			return this.responseError1(ERRORCODE_NOLOGIN, "no login");
		}
		return bizUserCenterService.doApplayShop(shop, attachFile);
	}
	
	/**
	 * 新闻资讯评论列表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getNewsCommentList")
	public Map<String, Object> getNewsCommentList(
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize){
		Long userId = this.getCurrentUserId();
		if(userId==null){
			return this.responseError1(ERRORCODE_NOLOGIN, "no login");
		}
		
		PageUtil<Map<String, Object>> page = bizUserCenterService.getNewsCommentList(userId, pageNo, pageSize);
		Map<String, Object> resMap = this.responseOK1("");
		resMap.put("page", page);
		return resMap;
	}
	
	/**
	 * 留言列表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getMessageCommentList")
	public Map<String, Object> getMessageCommentList(
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize){
		Long userId = this.getCurrentUserId();
		if(userId==null){
			return this.responseError1(ERRORCODE_NOLOGIN, "no login");
		}
		
		PageUtil<Map<String, Object>> page = bizUserCenterService.getMessageList(userId, pageNo, pageSize);
		Map<String, Object> resMap = this.responseOK1("");
		resMap.put("page", page);
		return resMap;
	}
	
	/**
	 * 产品评论列表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getProductCommentList")
	public Map<String, Object> getProductCommentList(
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize){
		Long userId = this.getCurrentUserId();
		if(userId==null){
			return this.responseError1(ERRORCODE_NOLOGIN, "no login");
		}
		
		PageUtil<Map<String, Object>> page = bizUserCenterService.getProductCommentList(userId, pageNo, pageSize);
		Map<String, Object> resMap = this.responseOK1("");
		resMap.put("page", page);
		return resMap;
	}
	
}
