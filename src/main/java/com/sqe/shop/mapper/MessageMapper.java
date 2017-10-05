package com.sqe.shop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface MessageMapper extends BaseMapper {

	List<Map<String, Object>> getAdminMessageListByParm(@Param("parm") Map<String, Object> parm);

	List<Map<String, Object>> getSellerMessageListByParm(@Param("parm") Map<String, Object> parmMap);

	int countAdminMessageListByParm(@Param("parm") Map<String, Object> parm);

	// 获取用户的message列表 最新的一条消息
	List<Map<String, Object>> getUserMessageListByParm(@Param("parm") Map<String, Object> parmMap);

	// 获取用户的dialogueId message列表 根据对话message_id
	List<Map<String, Object>> getMessageDialogueIdListByParm(@Param("parm") Map<String, Object> parmMap);

}
