package com.sqe.shop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PostMapper extends BaseMapper {

	List<Map<String, Object>> getUserMapListByParm(@Param("parm") Map<String, Object> paramMap);
	
	List<Map<String, Object>> getSubPostListByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getFloorByParm(@Param("parm") Map<String, Object> paramMap);

}
