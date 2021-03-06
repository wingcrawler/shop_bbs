package com.sqe.shop.controller.front;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.Constants;
import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.model.ProductType;
import com.sqe.shop.service.AdvertisementService;
import com.sqe.shop.service.ImageService;
import com.sqe.shop.service.NewsService;
import com.sqe.shop.service.ProductService;
import com.sqe.shop.service.ProductTypeService;
import com.sqe.shop.service.biz.BizHomeService;
import com.sqe.shop.service.cached.CachedService;
import com.sqe.shop.util.PageUtil;

@Controller
@RequestMapping("/")
public class HomeController extends BaseFrontController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
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
	private BizHomeService bizHomeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		model.setViewName("index");
		return model;
	}

	/**
	 * 商城首页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="shopIndex", method = RequestMethod.GET)
	public ModelAndView shopIndex(ModelAndView model) {
		model.addAllObjects(bizHomeService.getHomeIndexData(Constants.AD_DEVICE_PC));
		model.setViewName("shop/index");
		return model;
	}
	
	
	/**
	 * 关于我们
	 * @param model
	 * @param productId
	 * @return
	 */
	@RequestMapping(value="about", method = RequestMethod.GET)
	public ModelAndView about(ModelAndView model) {
		model.setViewName("shop/about");
		return model;
	}
	/**
	 * 帮助
	 * @param model
	 * @return
	 */
	@RequestMapping(value="help", method = RequestMethod.GET)
	public ModelAndView help(ModelAndView model) {
		model.setViewName("shop/help");
		return model;
	}
	/**
	 * 404页面
	 */
	@RequestMapping(value="404", method = RequestMethod.GET)
	public ModelAndView error404(ModelAndView model) {
		model.setViewName("shop/404");
		return model;
	}
	/**
	 * 500页面
	 */
	@RequestMapping(value="500", method = RequestMethod.GET)
	public ModelAndView error500(ModelAndView model) {
		model.setViewName("shop/500");
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="getProductTypeTwoLevel", method = RequestMethod.GET)
	public Map<String, Object> getProductTypeTwoLevel(Long id){
		ProductType productType = new ProductType();
		productType.setTypeLevel(2);
		productType.setParentId(id);
		PageUtil<ProductType> page = productTypeService.getBeanListByParm(productType, 1, -1);

		if(cachedService.getLang().equals("zh")){
			for(ProductType pt : page.getList()){
				pt.setTypeName(pt.getTypeNameCh());
			}
		}
		
		Map<String, Object> resMap = this.responseOK1("");
		resMap.put("list", page.getList());
		return resMap;
	}
	
	/**
	 * 刷新缓存
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="cacheInit")
	public Map<String, Object> cacheInit(){
		cachedService.init();
		return this.responseOK1("Refresh finished");
	}
	@ResponseBody
	@RequestMapping(value="refreshProductTypeList")
	public Map<String, Object> refreshProductTypeList(){
		cachedService.refreshProductTypeList();
		return this.responseOK1("Refresh finished");
	}
	@ResponseBody
	@RequestMapping(value="refreshBundle")
	public Map<String, Object> refreshBundle(){
		cachedService.refreshBundle();
		return this.responseOK1("Refresh finished");
	}
	@ResponseBody
	@RequestMapping(value="refreshMap")
	public Map<String, Object> refreshMap(){
		cachedService.refreshBundle();
		return this.responseOK1("Refresh finished");
	}

}
