package com.sqe.shop.service.cached;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.common.Constants;
import com.sqe.shop.model.ProductType;
import com.sqe.shop.service.ProductTypeService;
import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.PropertiesUtil;

/**
 * 缓存
 * @author Administrator
 *
 */
@Component
public class CachedService extends BaseCached {
	
	@Autowired
    private ProductTypeService productTypeService;
	
	/**
	 * 获取产品类别列表
	 * @return
	 */
	public Map<ProductType, List<ProductType>> getProductTypeList(){
		if(productTypeList==null){
			productTypeList = new HashMap<ProductType, List<ProductType>>();
		}
		if(productTypeList.isEmpty()){
			//一级分类
			ProductType productType = new ProductType();
			productType.setTypeLevel(1);
			PageUtil<ProductType> page = productTypeService.getBeanListByParm(productType, 1, -1);
			if(page.getTotalRecords()==0){
				return null;
			}
			//产品列表
	        String lang = PropertiesUtil.get("lang");
	        if(lang.equals("zh")){
	        	for(ProductType type : page.getList()){
	        		type.setTypeName(type.getTypeNameCh());
	        	}
	        }
			
			//二级分类
			productType.setTypeLevel(2);
			PageUtil<ProductType> page2 = null;
			for(ProductType type : page.getList()){
				productType.setParentId(type.getId());
				page2 = productTypeService.getBeanListByParm(productType, 1, -1);
				if(lang.equals("zh")){
		        	for(ProductType type2 : page2.getList()){
		        		type2.setTypeName(type2.getTypeNameCh());
		        	}
		        }
				productTypeList.put(type, page2.getList());
        	}
		}
		return productTypeList;
	}
	
	/**
	 * 国际化
	 * @return
	 */
	public ResourceBundle getBundle() {
    	if(bundle == null){
        	String lang = this.getLang();
        	Locale locale = Locale.CHINA;
        	if(lang.equals("en")){
        		locale = Locale.US;
        	}
        	bundle = ResourceBundle.getBundle("i18n", locale);
        }
    	return bundle;
	}
	public String getText(String key) {
    	if(bundle==null){
    		return Constants.UNKNOW_INFO;
    	}
    	return bundle.getString(key);
	}
	
	/**
	 * map缓存
	 */
	public void initMap(){
		//广告类型
		map.put("ad_type_0", getText("t_ad_lb"));
		map.put("ad_type_1", getText("t_ad_ggw"));
		//产品状态
		map.put("product_status_1", getText("t_product_on"));
		map.put("product_status_2", getText("t_product_off"));
		//news 语言
		map.put("news_lang_0", getText("t_zh"));
		map.put("news_lang_1", getText("t_en"));
		//评论状态
		map.put("comment_status_0", getText("t_off"));
		map.put("comment_status_1", getText("t_on"));
		//举报消息状态
		map.put("inform_status_0", getText("t_undone"));
		map.put("inform_status_1", getText("t_done"));
		//店家状态
		map.put("shop_status_0", getText("t_pending"));
		map.put("shop_status_1", getText("t_on"));
		//留言状态
		map.put("msg_status_0", getText("t_unreaded"));
		map.put("msg_status_1", getText("t_readed"));
		//用户状态
		map.put("user_status_0", getText("t_off"));
		map.put("user_status_1", getText("t_on"));
		//版块状态
		map.put("section_status_0", getText("t_off"));
		map.put("section_status_1", getText("t_on"));
		//话题状态
		map.put("topic_status_0", getText("t_off"));
		map.put("topic_status_1", getText("t_on"));
		//帖子类型
		map.put("thread_type_0", getText("t_en"));
		map.put("thread_type_1", getText("t_zh"));
		//帖子状态
		map.put("thread_status_0", getText("t_off"));
		map.put("thread_status_1", getText("t_on"));
	}
	public Object get(String key) {
		return map.get(key);
	}
	public String getString(String key) {
		return map.get(key) ==null?"":map.get(key).toString();
	}
	public Object get(String key, Object dafaultValue) {
		Object value = map.get(key);
		if(value==null){
			return dafaultValue;
		}
		return value;
	}
	public void set(String key, Object value) {
		map.put(key, value);
	}
	
	
	//初始化缓存
	public void init(){
		refreshBundle();
		refreshProductTypeList();
		refreshMap();
	}
	//刷新部分缓存
	public void refreshBundle(){
		bundle = null;
		getBundle();
	}
	public void refreshProductTypeList(){
		productTypeList = null;
		getProductTypeList();
	}
	public void refreshMap(){
		initMap();
	}

}