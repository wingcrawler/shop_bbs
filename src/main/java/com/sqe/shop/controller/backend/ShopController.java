package com.sqe.shop.controller.backend;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.Constants;
import com.sqe.shop.controller.base.BaseBackendController;
import com.sqe.shop.model.Image;
import com.sqe.shop.model.Message;
import com.sqe.shop.model.Shop;
import com.sqe.shop.model.User;
import com.sqe.shop.service.ImageService;
import com.sqe.shop.service.MessageService;
import com.sqe.shop.service.ShopService;
import com.sqe.shop.service.UserService;
import com.sqe.shop.service.file.ExcelExportService;
import com.sqe.shop.service.file.ImageFileService;
import com.sqe.shop.util.DateUtil;
import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.PropertiesUtil;


@Controller
@RequestMapping("/backend/shop")
public class ShopController extends BaseBackendController {
	
	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
	
	@Autowired
	private ShopService shopService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private ExcelExportService excelExportService;
	@Autowired
	private ImageFileService imageFileService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("backend/store/list");
		return model;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("backend/store/edit");
		if(id!=null){
			//查询店铺
			Shop entity = shopService.getById(id);
			model.addObject("entity", entity);
			//店铺图片
			Image image = new Image();
			image.setShopId(id);
			PageUtil<Image> imgPage = imageService.getBeanListByParm(image, 1, 10);
			model.addObject("imageList", imgPage.getList());
			//店家信息
			User user = userService.getById(entity.getUserId());
			model.addObject("ownerName", user.getUsername());
		} else {
			model.addObject("ownerName", "");
		}
		return model;
	}
	
	@RequestMapping(value="/product", method = RequestMethod.GET)
	public ModelAndView product(Long shopId) {
		ModelAndView model = new ModelAndView("backend/store/store_detail");
		model.addObject("pageflag", 1);
		model.addObject("shop", shopService.getById(shopId));
		if(shopId==null){
			return model;
		}
		model.addObject("shopId", shopId);
		return model;
	}
	
	@RequestMapping(value="/msg", method = RequestMethod.GET)
	public ModelAndView msg(Long shopId) {
		ModelAndView model = new ModelAndView("backend/store/store_detail");
		model.addObject("pageflag", 2);
		model.addObject("shop", shopService.getById(shopId));
		if(shopId==null){
			return model;
		}
		model.addObject("shopId", shopId);
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList", method = RequestMethod.GET)
	public Map<String, Object> getList(Shop shop,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  @RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		PageUtil<Map<String, Object>> page = shopService.getMapListByParm(shop, pageNo, pageSize);
		resMap.put("list", page.getList());
		resMap.put("page", page);
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/getMsgList", method = RequestMethod.GET)
	public Map<String, Object> getMsgList(Message message,String shopName,Long shopId,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		if(StringUtils.isNotBlank(shopName)){
			try {
				shopName = URLDecoder.decode(shopName, "utf8");
			} catch (UnsupportedEncodingException e) {
				shopName = "";
			}
		} else {
			if(shopId!=null){
				Shop shop = shopService.getById(shopId);
				shopName = shop.getShopTitle();
			}
		}
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		message.setReceiveId(-1L);
		PageUtil<Map<String, Object>> page = messageService.getAdminMessageListByParm(message, pageNo, pageSize, shopName);
		resMap.put("list", page.getList());
		resMap.put("page", page);
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(Shop shop, String ownerName, 
			@RequestParam(name = "attachFile",value="attachFile", required = false) MultipartFile attachFile) {
		if(StringUtils.isBlank(shop.getShopTitle())){
			return responseError(-1, "error_empty_title");
		}
		
		if(shop.getShopStatus().equals(Constants.STORE_FAILED) && StringUtils.isBlank(shop.getFailedReason())){
			return responseError(-1, "error_empty_not_pass_resaon");
		}
		
		User user = null;
		if(StringUtils.isBlank(ownerName)){
			return responseError(-1, "error_empty_onwername");
		} else if(StringUtils.isNotBlank(ownerName)){
			if(shop.getId()==null){
				user = userService.findOwnerUser(ownerName);
			} else {
				user = userService.findByName(ownerName);
			}
			if(user==null){
				return responseError(-1, "error_onwername_not_exist");	
			} 
			shop.setUserId(user.getId());
		}
		
		if(attachFile!=null){
			Map<String, Object> resMap = imageFileService.uploadImage(attachFile,attachFile.getOriginalFilename());
			String fileName = resMap.get("errorInfo").toString(); 
		    if(!resMap.get("errorNo").equals(0)){
		    	return resMap;
		    }
		    String uploadPath = PropertiesUtil.get("upload_path_save"); 
		    uploadPath += DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1)+"/";
		    shop.setShopImg(uploadPath+fileName);
	    }
		
		shopService.save(shop);
		
		if(user!=null && !user.getUserRole().equals(Constants.ROLE_SELL)){
			user.setUserRole(Constants.ROLE_SELL);
			userService.update(user);
		}
		
		
	    return responseOK("save_success");
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete", method = RequestMethod.GET)
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, "error_no_item");
		}
		int i = shopService.delete(id);
		if(i==0){
			return responseError(-1, "error_del_failed");
		}
		return responseOK("op_success");
	}
	
	@ResponseBody
	@RequestMapping(value="/doMsgDelete", method = RequestMethod.GET)
	public Map<String, Object> doMsgDelete(Long id) {
		if(id==null){
			return responseError(-1, "error_no_item");
		}
		int i = messageService.delete(id);
		if(i==0){
			return responseError(-1, "error_del_failed");
		}
		return responseOK("");
	}
	
	@ResponseBody
	@RequestMapping(value="/export", method = RequestMethod.GET)
	public void export(Shop shop) {
		List<Map<String, Object>> list = shopService.getListForExport(shop);
		excelExportService.exportExcel(request, response, list);
	}
	
	private PageUtil<Shop> getShopList() {
		Shop shop = new Shop();
		shop.setShopStatus(1);
		PageUtil<Shop> shops = shopService.getBeanListByParm(shop, 0, -1);
		return shops;
	}
	
	/**
	 * 审核中的
	 * @return
	 */
	@RequestMapping(value="/pendingList", method = RequestMethod.GET)
	public ModelAndView notPassList() {
		ModelAndView model = new ModelAndView("backend/store/pend_list");
		return model;
	}
	@ResponseBody
	@RequestMapping(value="/getPendingList", method = RequestMethod.GET)
	public Map<String, Object> getNotpassList(Shop shop,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  @RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		shop.setShopStatus(Constants.STORE_PEND);
		PageUtil<Map<String, Object>> page = shopService.getMapListByParm(shop, pageNo, pageSize);
		resMap.put("list", page.getList());
		resMap.put("page", page);
		return resMap;
	}

}
