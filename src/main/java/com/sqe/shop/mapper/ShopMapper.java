package com.sqe.shop.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sqe.shop.model.Shop;

public interface ShopMapper extends BaseMapper {

	Shop getShopByIdAndUserId(@Param("id") Long id, @Param("userId")Long userId);

	int getMapListByParm_count(@Param("parm") Map<String, Object> paramMap);

}
