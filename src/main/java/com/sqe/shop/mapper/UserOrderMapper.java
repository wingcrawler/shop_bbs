package com.sqe.shop.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserOrderMapper extends BaseMapper {

	Map<String, Object> getMapById(@Param("id") Long id);

}
