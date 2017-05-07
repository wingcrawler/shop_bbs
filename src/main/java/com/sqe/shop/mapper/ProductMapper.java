package com.sqe.shop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sqe.shop.model.Product;

public interface ProductMapper extends BaseMapper {

	List<Map<String, Object>> getHotProductList(@Param("parm") Map<String, Object> parm);

	Product getByIdAndUserId(Long id, Long userId);

}
