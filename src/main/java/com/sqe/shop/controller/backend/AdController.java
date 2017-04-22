package com.sqe.shop.controller.backend;

import java.util.Date;
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

import com.sqe.shop.common.BaseController;
import com.sqe.shop.file.service.FileUploadService;
import com.sqe.shop.model.Advertisement;
import com.sqe.shop.service.AdvertisementService;
import com.sqe.shop.util.DateUtil;
import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.PropertiesUtil;

@Controller
@RequestMapping("/backend/ad")
public class AdController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdController.class);
	
	@Autowired
	private AdvertisementService adService;
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("backend/ad/list");
		return model;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("backend/ad/edit");
		if(id!=null){
			Advertisement entity = adService.getById(id);
			model.addObject("entity", entity);
		}
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList", method = RequestMethod.GET)
	public Map<String, Object> getList(Advertisement ad, 
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  @RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		PageUtil<Advertisement> page = adService.getBeanListByParm(ad, pageNo, pageSize);
		resMap.put("list", page.getList());
		resMap.put("page", page);
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doEdit", method = RequestMethod.POST)
	public Map<String, Object> doEdit(Advertisement ad, @RequestParam(name = "attachFile",value="attachFile", required = false) MultipartFile attachFile,MultipartHttpServletRequest multiReq) {
		
		/*String uploadFilePath = multiReq.getFile("attachFile").getOriginalFilename();*/
		
		if(StringUtils.isBlank(ad.getImageUrl())){
			return responseError(-1, bundle.getString("error_empty_img_url"));
		}
		if(ad.getType()<0){
			return responseError(-1, bundle.getString("error_no_type"));
		}
		if(ad.getSort()==null){
			ad.setSort(1);
		}
		
		if(attachFile!=null){
	    	String uploadPath = PropertiesUtil.get("path_img_ad"); 
		    String fileName = fileUploadService.uploadImage(attachFile, uploadPath);
		    if(StringUtils.isBlank(uploadPath)){
		    	return responseError(-1, bundle.getString("error_upload_failed"));
		    }
		    ad.setImagePath(PropertiesUtil.get("path_img_ad_save")+fileName);
	    }
		
		adService.save(ad);
		return responseOK(bundle.getString("save_success"));
	}

	@ResponseBody
	@RequestMapping(value="/doDelete", method = RequestMethod.GET)
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, bundle.getString("error_no_item"));
		}
		int i = adService.delete(id);
		if(i==0){
			return responseError(-1, bundle.getString("error_del_failed"));
		}
		return responseOK(bundle.getString("op_success"));
	}
}
