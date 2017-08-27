package com.sqe.shop.service.cached;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

import com.sqe.shop.model.ProductType;
import com.sqe.shop.util.PropertiesUtil;

public class BaseCached {

	protected Map<ProductType, List<ProductType>> productTypeList_notuse = null;//产品类别： 一级类别为key，二级类别列表为value
	protected Map<ProductType, List<ProductType>> productTypeList = null;
	protected ResourceBundle bundle = null; //国际化内容
	protected String lang = StringUtils.EMPTY; //国际化语言
	protected Map<String, Object> map = new HashMap<String, Object>();//存放简单的key-value数据
	
	/**
	 * 国际化语言
	 * @return
	 */
	public String getLang() {
		if(StringUtils.isBlank(lang)){
			setLang(PropertiesUtil.get("lang", "zh"));
		}
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
}
