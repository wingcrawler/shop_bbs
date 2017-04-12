package com.sqe.shop.service;

import java.util.List;
import java.util.Map;

public interface BaseService {
	
	/*<T> int insert(T t);
	
	<T> int update(T t);
	
	int delete (Integer id);*/
	
	<T> T getById(String mapperName, Integer id);
	
	int countByParm(String mapperName, Map<String, Object> paramMap);

	<T> List<T> getBeanListByParm(String mapperName, Map<String, Object> paramMap);
	
	List<Map<String, Object>> getMapListByParm(String mapperName,  Map<String, Object> paramMap);
	
}
