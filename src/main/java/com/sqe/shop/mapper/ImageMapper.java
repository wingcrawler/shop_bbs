package com.sqe.shop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sqe.shop.model.Image;

public interface ImageMapper extends BaseMapper {

	List<Image> getByOtherId(@Param("parm") Map<String, Object> parm);

}
