package com.sqe.shop.mapper;

import java.util.Map;

public interface UserOrderMapper extends BaseMapper {

	Map<String, Object> getMapById(Long id);

}
