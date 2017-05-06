package com.sqe.shop.service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sqe.shop.mapper.BaseMapper;
import com.sqe.shop.util.SpringContextUtil;

public class AdapterService {
	
	private static String BASE_MAPPER_PATH = "com.sqe.shop.mapper.";

	/*public <T> int insert(T t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public <T> int update(T t) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}*/

	/**
	 * sample: super.getById("TProductMapper", productId);
	 * @param mapperName
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getById(String mapperName, Integer id) {
		return (T) invokeMapper(mapperName, "getById", id);
	}

	public int countByParm(String mapperName, Map<String, Object> paramMap) {
		Object o =  invokeMapper(mapperName, "countByParm", paramMap);
		if(o!=null){
			return (Integer)o;
		}
		return 0;
	}

	/**
	 * sample: super.getBeanListByParm("TProductMapper", parmMap)
	 * @param mapperName
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getBeanListByParm(String mapperName, Map<String, Object> paramMap) {
		if(paramMap==null){
			paramMap = new HashMap<String, Object>();
		}
		return  (List<T>) invokeMapper(mapperName, "getBeanListByParm", paramMap);
	}

	/**
	 * sample: super.getMapListByParm("TProductMapper", parmMap)
	 * @param mapperName
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapListByParm(String mapperName, Map<String, Object> paramMap) {
		return (List<Map<String, Object>>) invokeMapper(mapperName, "getMapListByParm", paramMap);
	}

	/**
	 * sample: super.getByParm("TProductMapper", "xmlId", parmMap)
	 * @param mapperName
	 * @param xmlId
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getByParm(String mapperName, String xmlId, Map<String, Object> paramMap) {
		if(paramMap==null){
			paramMap = new HashMap<String, Object>();
		}
		return  (T) invokeMapper(mapperName, xmlId, paramMap);
	}
	
	private Object invokeMapper(String mapperName, String sqlId, Object... args){
		try {
			Class<?> mapperClass = Class.forName(BASE_MAPPER_PATH+mapperName); //反射获取mapper的实例
			if (BaseMapper.class.isAssignableFrom(mapperClass)) {//是否继承BaseMapper
				for (Method mapperMethod : mapperClass.getMethods()) {
					if (mapperMethod.getName().equals(sqlId)) {
						if (args==null) {
							args = (Object[]) new Object();
						}
						Map<String, Object> map = (Map<String, Object>) SpringContextUtil.getBean(mapperClass);
						Object mapperInstance = map.get(mapperName.substring(0, 1).toLowerCase()+mapperName.subSequence(1, mapperName.length())); //获取bean实例
						return mapperMethod.invoke(mapperInstance, args);
					}
				}		
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
}
