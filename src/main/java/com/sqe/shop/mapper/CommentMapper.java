package com.sqe.shop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CommentMapper extends BaseMapper {

	List<Map<String, Object>> getSellerProductCommentListByParm(@Param("parm") Map<String, Object> parmMap);

	int countSellerProductCommentList(@Param("parm") Map<String, Object> parmMap);

	List<Map<String, Object>> getNewsCommentListByParm(@Param("parm") Map<String, Object> parmMap);

	List<Map<String, Object>> getSubCommentListByParm(@Param("parm") Map<String, Object> replyMap);

	List<Map<String, Object>> getProductCommentListByParm(@Param("parm") Map<String, Object> parmMap);

	int getMapListByParm_count(@Param("parm") Map<String, Object> paramMap);
	
}
