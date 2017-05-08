package com.sqe.shop.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface InformMapper extends BaseMapper {

	Map<String, Object> getDetailById(@Param("id")Long id);

}
