package com.sqe.shop.controller.mobile;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.service.biz.BizUserCenterService;

@Controller
@RequestMapping("/h5/user")
public class MobileUserController extends BaseFrontController {
	
	@Autowired
	private BizUserCenterService bizUserCenterService;

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
	
}
