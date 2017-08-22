package com.sqe.shop.service.biz;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.common.BaseCommon;
import com.sqe.shop.model.User;
import com.sqe.shop.service.AdvertisementService;
import com.sqe.shop.service.CommentService;
import com.sqe.shop.service.ImageService;
import com.sqe.shop.service.MessageService;
import com.sqe.shop.service.NewsService;
import com.sqe.shop.service.ProductService;
import com.sqe.shop.service.ProductTypeService;
import com.sqe.shop.service.ShopService;
import com.sqe.shop.service.ThreadService;
import com.sqe.shop.service.UserService;
import com.sqe.shop.service.cached.CachedService;
import com.sqe.shop.service.file.ImageFileService;

@Component
public class BizUserCenterService extends BaseCommon {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private AdvertisementService advertisementService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private CachedService cachedService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private UserService userService;
	@Autowired
	private ImageFileService imageFileService;
	@Autowired
	private ThreadService threadService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private ShopService shopService;
	
	public Map<String, Object> getUserInfo() {
		Map<String, Object> resMap = this.responseOK1("");
		User entity = userService.getById(this.getCurrentUserId());
		resMap.put("userInfo", entity);
		resMap.put("img", entity.getUserImage());
		return resMap;
	}
	
	

	
}
