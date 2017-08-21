package com.sqe.shop.service.biz;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.common.BaseCommon;
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
public class BizNewsService extends BaseCommon {
	
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

	public Map<String, Object> getHomeIndexData(){
		Map<String, Object> resMap = this.responseOK1("");
		
		//轮播图
		Advertisement advertisement = new Advertisement();
		advertisement.setType(0);
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

	public Map<String, Object> getNewsData(String searchText, int pageNo, int pageSize) throws UnsupportedEncodingException {
		Map<String, Object> resMap = this.responseOK1("");
		
		News news = new News();
		if(StringUtils.isNotBlank(searchText)){
			searchText = URLDecoder.decode(searchText, UTF8);
			news.setNewsTitle(searchText);
		}
		PageUtil<Map<String, Object>> newsPage = newsService.getMapListByParm(news, pageNo, pageSize);
		if(newsPage.getList()!=null && !newsPage.getList().isEmpty()){
			String newsContent = "";
			for(Map<String, Object> map : newsPage.getList()){
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
		resMap.put("list", newsPage.getList());
		resMap.put("page", newsPage);
		
		resMap.put("searchText", searchText);
		
		return resMap;
	}
	
}
