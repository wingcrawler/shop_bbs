/*package com.sqe.shop.base.interfaces;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BaseInterface {

	<T> int insert(T t);
	
	<T> int update(T t);
	
	<T> T getById(@Param("id") Integer id);
	
	int delete(@Param("id") Integer id);

	int countByParm(@Param("parm") Map<String, Object> paramMap);

	<T> List<T> getBeanListByParm(@Param("parm") Map<String, Object> paramMap);
	
	List<Map<String, Object>> getMapListByParm(@Param("parm") Map<String, Object> paramMap);
}
*/