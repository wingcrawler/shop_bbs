package com.sqe.shop.controller.front;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.model.Image;
import com.sqe.shop.model.Shop;
import com.sqe.shop.model.User;
import com.sqe.shop.service.ImageService;
import com.sqe.shop.service.cached.CachedService;
import com.sqe.shop.service.file.ImageFileService;
import com.sqe.shop.util.DateUtil;
import com.sqe.shop.util.PropertiesUtil;

@RequestMapping("front/file")
@RestController
public class FileController extends BaseFrontController {

	@Autowired
	private ImageService imageService;
	@Autowired
	private CachedService cachedService;
	@Autowired
	private ImageFileService imageFileService;

	/**
	 * 上传图片文件
	 * 
	 * @param model
	 * @param shop
	 * @param attachFile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/image", method = RequestMethod.POST)
	public Map<String, Object> doSaveMerchant(
			@RequestParam(name = "file", value = "file", required = false) MultipartFile attachFile,@RequestParam(name = "fileName", required = true) String fileOName) {

		User user = this.getCurrentUser();

		if (null == user) {
			return responseError(-1, "need loginIn");
		}

		if (1L == user.getUserRole()) {
			return responseError(-1, "user need Auth");
		}
		if (attachFile != null) {
			Map<String, Object> resMap = imageFileService.uploadImage(attachFile,fileOName);
			String fileName = resMap.get("errorInfo").toString();
			if (!resMap.get("errorNo").equals(0)) {
				return resMap;
			}
			String uploadPath = PropertiesUtil.get("upload_path_save");
			uploadPath += DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1) + "/";
			return responseOK1((uploadPath + fileName));
		}
		return responseError(-1, "image upload Error");

	}
}
