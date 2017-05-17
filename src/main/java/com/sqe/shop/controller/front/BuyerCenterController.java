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

import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.model.Shop;
import com.sqe.shop.model.User;
import com.sqe.shop.service.UserService;
import com.sqe.shop.service.file.ImageFileService;
import com.sqe.shop.util.DateUtil;
import com.sqe.shop.util.PropertiesUtil;

@Controller
@RequestMapping("/front/buy")
public class BuyerCenterController extends BaseFrontController {
	
	private static final Logger logger = LoggerFactory.getLogger(BuyerCenterController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private ImageFileService imageFileService;
	
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
		user.setPassword(null);
		subject.getSession().setAttribute("userInfo", user);
		
		return responseOK1("");
	}
	
}
