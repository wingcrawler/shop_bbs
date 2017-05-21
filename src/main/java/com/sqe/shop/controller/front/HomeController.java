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

import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.model.Advertisement;
import com.sqe.shop.model.News;
import com.sqe.shop.model.Product;
import com.sqe.shop.model.ProductType;
import com.sqe.shop.service.AdvertisementService;
import com.sqe.shop.service.ImageService;
import com.sqe.shop.service.NewsService;
import com.sqe.shop.service.ProductService;
import com.sqe.shop.service.ProductTypeService;
import com.sqe.shop.service.cached.CachedService;
import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.RegularUtil;

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
		//轮播图
		Advertisement advertisement = new Advertisement();
		advertisement.setType(0);
		PageUtil<Advertisement> adPage = advertisementService.getBeanListByParm(advertisement, 1, -1);
		model.addObject("adList", adPage.getList());
		
		//8个商品
		Product product = new Product();
		product.setProductStatus(1);
		PageUtil<Map<String, Object>> hotProductPage = productService.getHotProductList(product, 1, 8); 
		model.addObject("hotProductList", hotProductPage.getList());
		
		//3个新闻资讯
		PageUtil<Map<String, Object>> newPage = newsService.getMapListByParm(new News(), 1, 3);
		if(newPage.getList()!=null && !newPage.getList().isEmpty()){
			String newsTitle = "";
			String newsContent = "";
			for(Map<String, Object> map : newPage.getList()){
				newsTitle = map.get("newsTitle").toString();
				if(newsTitle.length()>10){
					map.put("newsShortTitle", newsTitle.substring(0, 9)+"...");
				} else {
					map.put("newsShortTitle", newsTitle);
				}
				
				newsContent = map.get("newsContent").toString();
				newsContent = RegularUtil.Html2Text(newsContent);
				if(newsContent.length()>100){
					newsContent = newsContent.substring(0,100) + "...";
					map.put("newsContent", newsContent);
				} else {
					map.put("newsContent", newsContent);
				}
				
			}
		}
		model.addObject("newsList", newPage.getList());
		
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

}
