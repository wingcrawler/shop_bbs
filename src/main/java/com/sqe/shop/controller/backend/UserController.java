package com.sqe.shop.controller.backend;

import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.controller.base.BaseBackendController;
import com.sqe.shop.model.User;
import com.sqe.shop.service.UserService;
import com.sqe.shop.service.file.FileUploadService;
import com.sqe.shop.service.file.TxtService;
import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.PropertiesUtil;

@Controller
@RequestMapping("/backend/user")
public class UserController extends BaseBackendController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private TxtService txtService;
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("backend/user/list");
		return model;
	}
	
	@RequestMapping(value="/detail", method = RequestMethod.GET)
	public ModelAndView detail(Long id) {
		ModelAndView model = new ModelAndView("backend/user/view");
		if(id!=null){
			User entity = userService.getById(id);
			entity.setPassword("");
			model.addObject("entity", entity);
		}
		return model;
	}
	
	@RequestMapping(value="/userImport", method = RequestMethod.GET)
	public ModelAndView userImport() {
		ModelAndView model = new ModelAndView("backend/user/import");
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList", method = RequestMethod.GET)
	public Map<String, Object> getList(User user,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  @RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		PageUtil<User> page = userService.getBeanListByParm(user, pageNo, pageSize);
		resMap.put("list", page.getList());
		resMap.put("page", page);
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(User user) {
		userService.save(user);
		return responseOK("save_success");
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete", method = RequestMethod.GET)
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, "error_no_item");
		}
		int i = userService.delete(id);
		if(i==0){
			return responseError(-1, "error_del_failed");
		}
		return responseOK("op_success");
	}
	
	@ResponseBody
	@RequestMapping(value="/onOroffUser", method = RequestMethod.POST)
	public Map<String, Object> onOroffUser(Long userId, Integer status) {
		userService.onOroffUser(userId, status);
		return responseOK("save_success");
	}
	
	@ResponseBody
	@RequestMapping(value="/export", method = RequestMethod.GET)
	public void export(User user) {
		List<User> list = userService.getListForExport(user);
		String filePath = PropertiesUtil.get("path_txt_user_export");
		txtService.exportUser(request,response,list,filePath);
	}
	
	@ResponseBody
	@RequestMapping(value="/doImport", method = RequestMethod.POST)
	public Map<String, Object> doImport(@RequestParam(name = "txtFile",value="txtFile", required = false) MultipartFile attachFile,
			MultipartHttpServletRequest multiReq) {
		if(fileUploadService.checkFile(attachFile, "txt")){
			return responseError(-1, "error_file_formate");
		}
		String filePath = PropertiesUtil.get("path_txt_user_import");
		String fileName = txtService.uploadTxtFile(attachFile, filePath);
		if (StringUtils.isBlank(fileName)) {
			return responseError(-1, "error_upload_failed");
		}
		String result = txtService.userImport(filePath+fileName);
		if(StringUtils.isBlank(result)){
			return responseError(-1, "error_no_user");
		}
		return responseOK1(result);
	}

}
