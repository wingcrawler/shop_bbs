package com.sqe.shop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BaseMapper {

	<T> int insert(T t);
	
	<T> int update(T t);
	
	<T> T getById(@Param("id") Long id);
	
	int delete(@Param("id") Long id);

	int countByParm(@Param("parm") Map<String, Object> paramMap);

	<T> List<T> getBeanListByParm(@Param("parm") Map<String, Object> paramMap);
	
	List<Map<String, Object>> getMapListByParm(@Param("parm") Map<String, Object> paramMap);
}
