package com.sqe.shop.mapper;

import java.util.Map;

public interface InformMapper extends BaseMapper {

	Map<String, Object> getDetailById(Long id);

}
