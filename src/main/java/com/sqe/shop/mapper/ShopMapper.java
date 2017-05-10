package com.sqe.shop.mapper;

import org.apache.ibatis.annotations.Param;

import com.sqe.shop.model.Shop;

public interface ShopMapper extends BaseMapper {

	Shop getShopByIdAndUserId(@Param("id") Long id, @Param("userId")Long userId);

}
