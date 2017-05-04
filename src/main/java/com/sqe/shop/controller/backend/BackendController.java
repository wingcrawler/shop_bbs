package com.sqe.shop.controller.backend;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.BaseBackendController;

@Controller
@RequestMapping("/backend")
public class BackendController extends BaseBackendController {
	
	private static final Logger logger = LoggerFactory.getLogger(BackendController.class);
	
	/*@RequiresRoles(value="admin")*/
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		model = new ModelAndView("backend/index");
		model.addObject("user", this.getCurrentUser());
		return model;
	}

}
