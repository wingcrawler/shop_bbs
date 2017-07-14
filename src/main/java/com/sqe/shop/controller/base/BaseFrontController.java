package com.sqe.shop.controller.base;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sqe.shop.common.BaseCommon;
import com.sqe.shop.common.Constants;
import com.sqe.shop.model.News;
import com.sqe.shop.model.User;
import com.sqe.shop.service.LoginService;
import com.sqe.shop.service.MessageService;
import com.sqe.shop.service.NewsService;
import com.sqe.shop.service.ProductTypeService;
import com.sqe.shop.service.cached.CachedService;
import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.RegularUtil;

public class BaseFrontController extends BaseCommon {

	Logger logger = LoggerFactory.getLogger(BaseFrontController.class);

	protected HttpServletRequest request;
	protected HttpServletResponse response;

	@Autowired
	private MessageService messageService;
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private CachedService cachedService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private NewsService newsService;

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		this.request = request;
		this.response = response;

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");// 防止数据传递乱码

		// 国际化内容
		request.setAttribute("t", cachedService.getBundle());

		// 获取产品类别列表
		request.setAttribute("productTypeList", cachedService.getProductTypeList());

		// footer 2个新闻资讯
		PageUtil<Map<String, Object>> newPage = newsService.getMapListByParm(new News(), 1, 2);
		if (newPage.getList() != null && !newPage.getList().isEmpty()) {
			String newsTitle = "";
			String newsContent = "";
			for (Map<String, Object> map : newPage.getList()) {
				newsTitle = map.get("newsTitle").toString();
				if (newsTitle.length() > 14) {
					map.put("newsShortTitle", newsTitle.substring(0, 14) + "...");
				} else {
					map.put("newsShortTitle", newsTitle);
				}

				newsContent = map.get("newsContent").toString();
				newsContent = RegularUtil.Html2Text(newsContent);
				if (newsContent.length() > 100) {
					newsContent = newsContent.substring(0, 80) + "...";
					map.put("newsContent", newsContent);
				} else {
					map.put("newsContent", newsContent);
				}
			}
		}
		request.setAttribute("newsFooterList", newPage.getList());

		// 是否登陆
		User user = this.getCurrentUser();
		if (user == null) {
			loginService.autoLogin();
		}
		user = this.getCurrentUser();
		if (user == null) {
			request.setAttribute("isLogin", false);
		} else if (user != null && user.getUserRole().equals(Constants.ROLE_SELL)) {
			request.setAttribute("isSellLogin", true);
			request.setAttribute("isLogin", true);
			request.setAttribute("user", user);
		} else if (user != null && user.getUserRole().equals(Constants.ROLE_BUY)) {
			request.setAttribute("isBuyLogin", true);
			request.setAttribute("isLogin", true);
			request.setAttribute("user", user);
		} else if (user != null && user.getUserRole().equals(Constants.ROLE_ADMIN)) {
			request.setAttribute("isAdminLogin", true);
			request.setAttribute("isLogin", true);
			request.setAttribute("user", user);
		}

		String uri = request.getRequestURI();
		request.setAttribute("uri", uri);
	}

	/**
	 * 如果执行此Controller里面的方法出现异常,则会执行该方法
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ Exception.class })
	public String exceptionHandle(Exception e) {
		System.out.println("Exception" + e.getStackTrace());
		logger.error("", e);
		return "shop/500";
	}

}
