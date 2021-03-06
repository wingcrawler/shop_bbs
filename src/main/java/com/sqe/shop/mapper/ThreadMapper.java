package com.sqe.shop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ThreadMapper extends BaseMapper {

	Map<String, Object> getMapById(@Param("id") Long id);
	
	List<Map<String, Object>> getSectionMapListByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getSectionOneThreadList(@Param("parm")  Map<String, Object> parm);
	
	List<Map<String, Object>> getHotThread(@Param("parm")  Map<String, Object> parm);
	
	
	

}
