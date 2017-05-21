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

import com.sqe.shop.controller.base.BaseBackendController;
import com.sqe.shop.model.Image;
import com.sqe.shop.model.Message;
import com.sqe.shop.model.Shop;
import com.sqe.shop.service.ImageService;
import com.sqe.shop.service.MessageService;
import com.sqe.shop.service.ShopService;
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
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("backend/store/list");
		return model;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("backend/store/edit");
		if(id!=null){
			Shop entity = shopService.getById(id);
			model.addObject("entity", entity);
			
			Image image = new Image();
			image.setShopId(id);
			PageUtil<Image> imgPage = imageService.getBeanListByParm(image, 1, 10);
			model.addObject("imageList", imgPage.getList());
		}
		return model;
	}
	
	@RequestMapping(value="/product", method = RequestMethod.GET)
	public ModelAndView product(Long shopId) {
		ModelAndView model = new ModelAndView("backend/store/store_detail");
		model.addObject("pageflag", 1);
		model.addObject("shopList", getShopList().getList());
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
		PageUtil<Shop> page = shopService.getBeanListByParm(shop, pageNo, pageSize);
		resMap.put("list", page.getList());
		resMap.put("page", page);
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/getMsgList", method = RequestMethod.GET)
	public Map<String, Object> getMsgList(Message message,String shopName,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		if(StringUtils.isNotBlank(shopName)){
			try {
				shopName = URLDecoder.decode(shopName, "utf8");
			} catch (UnsupportedEncodingException e) {
				shopName = "";
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
	public Map<String, Object> save(Shop shop, @RequestParam(name = "attachFile",value="attachFile", required = false) MultipartFile attachFile) {
		if(StringUtils.isBlank(shop.getShopTitle())){
			return responseError(-1, "error_empty_title");
		}
		
		if(attachFile!=null){
			Map<String, Object> resMap = imageFileService.uploadImage(attachFile);
			String fileName = resMap.get("errorInfo").toString(); 
		    if(!resMap.get("errorNo").equals(0)){
		    	return resMap;
		    }
		    String uploadPath = PropertiesUtil.get("upload_path_save"); 
		    uploadPath += DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1)+"/";
		    shop.setShopImg(uploadPath+fileName);
	    }
		
		shopService.save(shop);
		
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
		return responseOK("op_success");
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

}
