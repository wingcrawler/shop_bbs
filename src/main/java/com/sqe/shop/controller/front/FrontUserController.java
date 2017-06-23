package com.sqe.shop.controller.front;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.controller.base.BaseBackendController;
import com.sqe.shop.model.User;
import com.sqe.shop.service.UserService;
import com.sqe.shop.service.file.FileUploadService;
import com.sqe.shop.service.file.TxtService;
import com.sqe.shop.util.MD5Util;
import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.PropertiesUtil;
import com.sqe.shop.util.SendMail;

@Controller
@RequestMapping("/user")
public class FrontUserController extends BaseBackendController {

	private static final Logger logger = LoggerFactory.getLogger(FrontUserController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private TxtService txtService;
	@Autowired
	private FileUploadService fileUploadService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("backend/user/list");
		return model;
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(Long id) {
		ModelAndView model = new ModelAndView("backend/user/view");
		if (id != null) {
			User entity = userService.getById(id);
			entity.setPassword("");
			model.addObject("entity", entity);
		}
		return model;
	}

	@RequestMapping(value = "/userImport", method = RequestMethod.GET)
	public ModelAndView userImport() {
		ModelAndView model = new ModelAndView("backend/user/import");
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public Map<String, Object> getList(User user, @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		PageUtil<User> page = userService.getBeanListByParm(user, pageNo, pageSize);
		resMap.put("list", page.getList());
		resMap.put("page", page);
		return resMap;
	}

	@ResponseBody
	@RequestMapping(value = "/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(User user) {
		userService.save(user);
		return responseOK("save_success");
	}

	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Map<String, Object> register(User user) {
		userService.save(user);
		return responseOK("save_success");
	}

	@ResponseBody
	@RequestMapping(value = "/doDelete", method = RequestMethod.GET)
	public Map<String, Object> doDelete(Long id) {
		if (id == null) {
			return responseError(-1, "error_no_item");
		}
		int i = userService.delete(id);
		if (i == 0) {
			return responseError(-1, "error_del_failed");
		}
		return responseOK("op_success");
	}

	@ResponseBody
	@RequestMapping(value = "/onOroffUser", method = RequestMethod.POST)
	public Map<String, Object> onOroffUser(Long userId, Integer status) {
		userService.onOroffUser(userId, status);
		return responseOK("save_success");
	}

	@ResponseBody
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public void export(User user) {
		List<User> list = userService.getListForExport(user);
		String filePath = PropertiesUtil.get("path_txt_user_export");
		txtService.exportUser(request, response, list, filePath);
	}

	@ResponseBody
	@RequestMapping(value = "/doImport", method = RequestMethod.POST)
	public Map<String, Object> doImport(
			@RequestParam(name = "txtFile", value = "txtFile", required = false) MultipartFile attachFile,
			MultipartHttpServletRequest multiReq) {
		if (fileUploadService.checkFile(attachFile, "txt")) {
			return responseError(-1, "error_file_formate");
		}
		String filePath = PropertiesUtil.get("path_txt_user_import");
		String fileName = txtService.uploadTxtFile(attachFile, filePath);
		if (StringUtils.isBlank(fileName)) {
			return responseError(-1, "error_upload_failed");
		}
		String result = txtService.userImport(filePath + fileName);
		if (StringUtils.isBlank(result)) {
			return responseError(-1, "error_no_user");
		}
		return responseOK1(result);
	}

	@RequestMapping(value = "/forget", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		
		if(isLogin()){
			String url = getSavedRequestUrl();
			if(StringUtils.isBlank(url)){
				modelAndView.setViewName("shop/forget");
				return modelAndView;
			}
			if(url.indexOf("login")<=0){
				return new ModelAndView("redirect:"+url);
			} else {
				modelAndView.setViewName("shop/forget");
				return modelAndView;
			}
		}
		
		modelAndView.setViewName("shop/forget");
		return modelAndView;
	}

	
	@RequestMapping(value = "/forget_password")
	@ResponseBody
	public Map forgetPass(HttpServletRequest request, String userName) {
		User user = userService.findByName(userName);
		Map map = new HashMap<String, String>();
		String msg = "";
		if (user == null) { // 用户名不存在
			msg = "用户名不存在,你不会忘记用户名了吧?";
			map.put("msg", msg);
			return map;
		}
		try {
			String secretKey = UUID.randomUUID().toString(); // 密钥
			Timestamp outDate = new Timestamp(System.currentTimeMillis() + 30 * 60 * 1000);// 30分钟后过期
			long date = outDate.getTime() / 1000 * 1000; // 忽略毫秒数
			user.setValidataCode(secretKey);
			user.setUpDateTime(outDate);
			userService.update(user); // 保存到数据库
			String key = user.getUserName() + "$" + date + "$" + secretKey;
			MD5Util md5 = new MD5Util(MD5Util.SALT, "MD5");
			String digitalSignature = md5.encode(key); // 数字签名

			String emailTitle = "密码找回";
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
			String resetPassHref = basePath + "user/reset_password?sid=" + digitalSignature + "&userName="
					+ user.getUserName();
			String emailContent = "请勿回复本邮件.点击下面的链接,重设密码<br/><a href=" + resetPassHref + " target='_BLANK'>点击我重新设置密码</a>"
					+ "<br/>tips:本邮件超过30分钟,链接将会失效，需要重新申请'找回密码'" + key + "\t" + digitalSignature;
			System.out.print(resetPassHref);
			SendMail.sendHtmlMail(emailTitle, emailContent, user.getUserMail());
			msg = "操作成功,已经发送找回密码链接到您邮箱。请在30分钟内重置密码";
			logger.info(request + userName, "申请找回密码");
		} catch (Exception e) {
			e.printStackTrace();
			msg = "邮箱不存在？未知错误,联系管理员吧。";
		}
		map.put("msg", msg);
		return map;
	}

}
