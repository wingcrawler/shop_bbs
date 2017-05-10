package com.sqe.shop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CommentMapper extends BaseMapper {

	List<Map<String, Object>> getSellerProductCommentListByParm(@Param("parm") Map<String, Object> parmMap);

	int countSellerProductCommentList(@Param("parm") Map<String, Object> parmMap);

}
