package com.sqe.shop.common;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import com.sqe.shop.util.PropertiesUtil;

public class BaseCommon {
	
	public static ResourceBundle bundle = null;
    static {
    }
    
    public static ResourceBundle getBundle() {
    	if(bundle == null){
        	String lang = PropertiesUtil.get("lang");
        	Locale locale = Locale.CHINA;
        	if(lang.equals("en")){
        		locale = Locale.US;
        	}
        	bundle = ResourceBundle.getBundle("i18n", locale);
        }
    	return bundle;
	}
    
    public static String getText(String key) {
    	if(bundle==null){
    		return Constants.UNKNOW_INFO;
    	}
    	return getBundle().getString(key);
	}

	public HashMap<String, Object> responseOK(String result){
		result = getText(result);
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		resMap.put(Constants.ERROR_NO, Constants.ERRORCODE_SUCCESS);
		resMap.put(Constants.ERROR_INFO, result);
		return resMap;
	}
	
	public HashMap<String, Object> responseError(Integer errorNo, String errorInfo){
		errorInfo = getText(errorInfo);
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		resMap.put(Constants.ERROR_NO, errorNo);
		resMap.put(Constants.ERROR_INFO, errorInfo);
		return resMap;
	}
	
}
