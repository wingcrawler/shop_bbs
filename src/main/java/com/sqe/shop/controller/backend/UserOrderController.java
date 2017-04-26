package com.sqe.shop.controller.backend;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.BaseController;
import com.sqe.shop.model.User;
import com.sqe.shop.model.UserOrder;
import com.sqe.shop.service.UserOrderService;
import com.sqe.shop.service.UserService;
import com.sqe.shop.util.PageUtil;

@Controller
@RequestMapping("/backend/order")
public class UserOrderController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserOrderController.class);
	
	@Autowired
	private UserOrderService userOrderService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("backend/order/list");
		return model;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("backend/order/edit");
		if(id!=null){
			UserOrder entity = userOrderService.getById(id);
			model.addObject("entity", entity);
		}
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList", method = RequestMethod.GET)
	public Map<String, Object> getList(UserOrder userOrder,String username,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		if(StringUtils.isNotBlank(username)){
			User user = userService.findByName(username);
			if(user!=null){
				userOrder.setUserId(user.getId());
			} else {
				userOrder.setUserId(-1L);
			}
		}
		Map<String, Object> resMap = new HashMap<String, Object>();
		PageUtil<Map<String, Object>> page = userOrderService.getMapListByParm(userOrder, pageNo, pageSize);
		resMap.put("list", page.getList());
		resMap.put("page", page);
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(UserOrder userOrder) {
		userOrderService.save(userOrder);
		return responseOK("save_success");
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete", method = RequestMethod.GET)
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, "error_no_item");
		}
		int i = userOrderService.delete(id);
		if(i==0){
			return responseError(-1, "error_del_failed");
		}
		return responseOK("op_success");
	}

}
