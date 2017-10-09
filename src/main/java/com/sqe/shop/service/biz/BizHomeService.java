package com.sqe.shop.service.biz;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.common.BaseCommon;
import com.sqe.shop.common.Constants;
import com.sqe.shop.model.Advertisement;
import com.sqe.shop.model.News;
import com.sqe.shop.model.Product;
import com.sqe.shop.service.AdvertisementService;
import com.sqe.shop.service.ImageService;
import com.sqe.shop.service.NewsService;
import com.sqe.shop.service.ProductService;
import com.sqe.shop.service.ProductTypeService;
import com.sqe.shop.service.cached.CachedService;
import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.RegularUtil;

@Component
public class BizHomeService extends BaseCommon {
	
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

	public Map<String, Object> getHomeIndexData(Integer adDeviceType){
		Map<String, Object> resMap = this.responseOK1("");
		
		//轮播图
		Advertisement advertisement = new Advertisement();
		advertisement.setType(Constants.AD_TYPE_LB);
		advertisement.setDevice(adDeviceType);
		PageUtil<Advertisement> adPage = advertisementService.getBeanListByParm(advertisement, 1, -1);
		resMap.put("adList", adPage.getList());
		
		//8个商品
		Product product = new Product();
		product.setProductStatus(1);
		PageUtil<Map<String, Object>> hotProductPage = productService.getHotProductList(product, 1, 8); 
		resMap.put("hotProductList", hotProductPage.getList());
		
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
		resMap.put("newsList", newPage.getList());
		
		return resMap;
	}
	
}
