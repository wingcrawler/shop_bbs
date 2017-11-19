package com.sqe.shop.controller.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.Constants;
import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.model.Shop;
import com.sqe.shop.model.User;
import com.sqe.shop.service.ImageService;
import com.sqe.shop.service.LoginService;
import com.sqe.shop.service.RegisterService;
import com.sqe.shop.service.ShopService;
import com.sqe.shop.service.UserService;
import com.sqe.shop.service.file.ImageFileService;
import com.sqe.shop.util.DateUtil;
import com.sqe.shop.util.PropertiesUtil;

@Controller
@RequestMapping("api/user")
public class ApiRegisterController extends BaseFrontController {
	@Autowired
	private RegisterService registerservice;
	@Autowired
	private LoginService loginService;
	@Autowired
	private ImageFileService imageFileService;
	@Autowired
	private ShopService shopService;

	@Autowired

	private UserService userService;

	/**
	 * 
	 * @param request
	 * @param user
	 * @param bindingResult
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/doRegister", method = RequestMethod.POST)
	public Map<String, Object> doRegister(HttpServletRequest request, @Validated User user,
			BindingResult bindingResult) {
		// 获取校验错误信息
		if (bindingResult.hasErrors()) {
			final Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put(Constants.ERROR_NO, -1);
			resMap.put(Constants.ERROR_INFO, bindingResult.getFieldErrors().get(0).getDefaultMessage());
			return resMap;
		} else {
			user.setUserRole(ROLE_BUY);
			Map<String, Object> resMap = registerservice.register(user);
			return resMap;
		}
	}

	/**
	 * 
	 * @param request
	 * @param user
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/sellDoRegister", method = RequestMethod.POST)
	public Map<String, Object> sellDoRegister(HttpServletRequest request, @Validated User user,
			BindingResult bindingResult) {

		// 获取校验错误信息
		if (bindingResult.hasErrors()) {
			final Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put(Constants.ERROR_NO, -1);
			resMap.put(Constants.ERROR_INFO, bindingResult.getFieldErrors().get(0).getDefaultMessage());
			return resMap;
		} else {
			user.setUserRole(ROLE_SELL);
			Map<String, Object> resMap = registerservice.register(user);
			return resMap;
		}

	}

	/**
	 * 
	 * @param request
	 * @param user
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public Map<String, Object> doLogin(HttpServletRequest request, User user) {
		Map<String, Object> resMap = loginService.login(user);
		if (resMap.get(Constants.ERROR_NO).equals(0)) {
			User userInfo = this.getCurrentUser();
			// 记录登录时间
			loginService.updateLoginTime(userInfo);
			if (userInfo.getUserRole().equals(Constants.ROLE_ADMIN)) {
				this.logout();
				return responseError(-1, "error_twopwd_not_match");
			}
		}
		return resMap;
	}

	/**
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/dologout", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> dologout(HttpServletRequest request, User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		this.logout();
		map.put("result", "true");
		return map;
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/doApplayShop", method = RequestMethod.POST)
	public Map<String, Object> doApplayShop(Shop shop, @RequestParam(name = "name") String loginName,
			@RequestParam(name = "file", value = "file", required = false) MultipartFile attachFile) {
		if (StringUtils.isBlank(shop.getShopTitle())) {
			return responseError(-1, "error_empty_shop_name");
		}
		if (StringUtils.isBlank(loginName)) {
			return responseError(-1, "error_empty_shop_name2");
		}
		if (attachFile != null) {
			Map<String, Object> resMap = imageFileService.uploadImage(attachFile);
			String fileName = resMap.get("errorInfo").toString();
			if (!resMap.get("errorNo").equals(0)) {
				return resMap;
			}
			String uploadPath = PropertiesUtil.get("upload_path_save");
			uploadPath += DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1) + "/";
			shop.setShopLogoImg(uploadPath + fileName);
		}
		User user = userService.findByName(loginName);
		shop.setShopStatus(Constants.STORE_PEND);
		shop.setUserId(user.getId());
		shopService.save(shop);

		return responseOK1("");
	}

}
