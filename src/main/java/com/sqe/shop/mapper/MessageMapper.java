package com.sqe.shop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface MessageMapper extends BaseMapper {

	List<Map<String, Object>> getAdminMessageListByParm(@Param("parm") Map<String, Object> parm);

	List<Map<String, Object>> getSellerMessageListByParm(@Param("parm") Map<String, Object> parmMap);

}
