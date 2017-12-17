package com.sqe.shop.service.biz;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sqe.shop.common.BaseCommon;
import com.sqe.shop.common.Constants;
import com.sqe.shop.model.Shop;
import com.sqe.shop.model.User;
import com.sqe.shop.service.AdvertisementService;
import com.sqe.shop.service.CommentService;
import com.sqe.shop.service.ImageService;
import com.sqe.shop.service.MessageService;
import com.sqe.shop.service.NewsService;
import com.sqe.shop.service.ProductService;
import com.sqe.shop.service.ProductTypeService;
import com.sqe.shop.service.ShopService;
import com.sqe.shop.service.ThreadService;
import com.sqe.shop.service.UserService;
import com.sqe.shop.service.cached.CachedService;
import com.sqe.shop.service.file.ImageFileService;
import com.sqe.shop.util.DateUtil;
import com.sqe.shop.util.MD5Util;
import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.PropertiesUtil;

@Component
public class BizUserCenterService extends BaseCommon {

	@Autowired
	@Lazy
	private ProductService productService;
	@Autowired
	@Lazy
	private ProductTypeService productTypeService;
	@Autowired
	@Lazy
	private AdvertisementService advertisementService;
	@Autowired
	@Lazy
	private ImageService imageService;
	@Autowired
	@Lazy
	private CachedService cachedService;
	@Autowired
	@Lazy
	private NewsService newsService;
	@Autowired
	private UserService userService;
	@Autowired
	private ImageFileService imageFileService;
	@Autowired
	@Lazy
	private ThreadService threadService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private ShopService shopService;

	public Map<String, Object> getUserInfo() {
		Map<String, Object> resMap = this.responseOK1("");
		User entity = userService.getById(this.getCurrentUserId());
		resMap.put("userInfo", entity);
		resMap.put("img", entity.getUserImage());
		return resMap;
	}

	public Map<String, Object> doChangePwd(String oldPwd, String newPwd, String confirmPwd) {
		if (StringUtils.isBlank(oldPwd)) {
			return responseError(-1, "error_empty_oldpwd");
		} else if (StringUtils.isBlank(newPwd)) {
			return responseError(-1, "error_empty_newpwd");
		} else if (StringUtils.isBlank(confirmPwd)) {
			return responseError(-1, "error_empty_confirmpwd");
		} else if (!newPwd.equals(confirmPwd)) {
			return responseError(-1, "error_twopwd_not_match");
		}

		MD5Util md5 = new MD5Util(MD5Util.SALT, "MD5");
		String oldPwdMd5 = md5.encode(oldPwd);
		User user = userService.getById(this.getCurrentUserId());
		if (!user.getPassword().equals(oldPwdMd5)) {
			return responseError(-1, "error_oldpwd");
		}

		String newPwdMd5 = md5.encode(newPwd);
		user.setPassword(newPwdMd5);
		userService.update(user);
		return responseOK1("");
	}

	public Map<String, Object> doSaveBasicInfo(User user, MultipartFile attachFile) {
		if (StringUtils.isBlank(user.getUsername())) {
			return responseError(-1, "error_empty_username");
		}
		if (user.getId() == null) {
			return responseError(-1, "error_illegal");
		}
		if (!user.getId().equals(this.getCurrentUserId())) {
			return responseError(-1, "error_illegal");
		}

		if (StringUtils.isNotBlank(user.getUsername())) {
			User u = userService.findOneOnlyByName(user.getUsername());
			if (u == null) {
				return responseError(-1, "error_username_noexist");
			}
		}
		
		/**
		 * 用户签名现在200字
		 */
		if(StringUtils.isNotBlank(user.getUserIntroduce()) && user.getUserIntroduce().length()>200){
			user.setUserIntroduce(user.getUserIntroduce().substring(0, 200));
		}

		if (attachFile != null && attachFile.getSize()>0) {
			Map<String, Object> resMap = imageFileService.uploadImage(attachFile,attachFile.getOriginalFilename());
			String fileName = resMap.get("errorInfo").toString();
			if (!resMap.get("errorNo").equals(0)) {
				return resMap;
			}
			String uploadPath = PropertiesUtil.get("upload_path_save");
			uploadPath += DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1) + "/";
			user.setUserImage(uploadPath + fileName);
		}

		userService.save(user);

		Subject subject = SecurityUtils.getSubject();
		User userInfo = userService.getById(user.getId());
		subject.getSession().setAttribute("userInfo", userInfo);

		return responseOK1("");
	}

	public Map<String, Object> doApplayShop(Shop shop, MultipartFile attachFile) {
		if (StringUtils.isBlank(shop.getShopTitle())) {
			return responseError(-1, "error_empty_shop_name");
		}

		if (attachFile != null) {
			Map<String, Object> resMap = imageFileService.uploadImage(attachFile,attachFile.getOriginalFilename());
			String fileName = resMap.get("errorInfo").toString();
			if (!resMap.get("errorNo").equals(0)) {
				return resMap;
			}
			String uploadPath = PropertiesUtil.get("upload_path_save");
			uploadPath += DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1) + "/";
			shop.setShopLogoImg(uploadPath + fileName);
		}

		shop.setShopStatus(Constants.STORE_PEND);
		shop.setUserId(this.getCurrentUserId());
		shopService.save(shop);

		return responseOK1("");
	}

	public Map<String, Object> shopInfo(Long userId) {
		Map<String, Object> resMap = this.responseOK1("");
		Shop shop = shopService.getByUserId(userId);
		if (shop != null) {
			resMap.put("shop", shop);
			resMap.put("user", this.getCurrentUser());
			resMap.put("img", shop.getShopLogoImg());
		} else {
			resMap.put("shop", new Shop());
			resMap.put("user", this.getCurrentUser());
		}
		return resMap;
	}

	/**
	 * 新闻评论列表
	 * 
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
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
	 * 
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
	 * 产品评论列表
	 * 
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageUtil<Map<String, Object>> getProductCommentList(Long userId, int pageNo, Integer pageSize) {
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("userId", userId);
		parmMap.put("nullCommentId", true);
		parmMap.put("orderby", "c.date desc");
		PageUtil<Map<String, Object>> msgPage = commentService.getProductCommentListByParm(parmMap, pageNo, pageSize);
		return msgPage;
	}

}
