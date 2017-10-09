package com.sqe.shop.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.sqe.shop.mapper.MessageMapper;
import com.sqe.shop.model.Message;
import com.sqe.shop.util.PageUtil;

@Service
public class MessageService extends AdapterService implements BaseService {

	@Autowired
	MessageMapper messageMapper;

	public int insert(Message message) {
		return messageMapper.insert(message);
	}

	public int update(Message message) {
		return messageMapper.update(message);
	}

	public int delete(Long id) {
		return messageMapper.delete(id);
	}

	public Message getById(Long id) {
		return messageMapper.getById(id);
	}

	public int countByParm(Message message) {
		Map<String, Object> parm = queryParm(message);
		parm.put("readFlag", 0);
		return messageMapper.countByParm(parm);
	}

	public PageUtil<Message> getBeanListByParm(Message message, int pageNo, Integer pageSize) {
		PageUtil<Message> pageUtil = new PageUtil<Message>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(message);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());

		int count = messageMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Message> list = new ArrayList<Message>();
		if (count != 0) {
			list = messageMapper.getBeanListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}

	public PageUtil<Map<String, Object>> getAdminMessageListByParm(Message message, int pageNo, Integer pageSize,
			String shopName) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(message);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		parm.put("orderby", "m.id desc");
		if (StringUtils.isNotBlank(shopName)) {
			parm.put("shopName", shopName);
		}

		int count = messageMapper.countAdminMessageListByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		if (count != 0) {
			list = messageMapper.getAdminMessageListByParm(parm);
			for (Map<String, Object> map : list) {
				String statusStr = map.get("status") == null ? "0" : map.get("status").toString();
				map.put("statusName", this.getMsgStatus(Integer.valueOf(statusStr)));
			}
		}
		pageUtil.setList(list);
		return pageUtil;
	}

	public PageUtil<Map<String, Object>> getMapListByParm(Message message, int pageNo, Integer pageSize,
			String orderby) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(message);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		if (StringUtils.isNotBlank(orderby)) {
			parm.put("orderby", orderby);
		}

		int count = messageMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		if (count != 0) {
			list = messageMapper.getMapListByParm(parm);
			for (Map<String, Object> map : list) {
				String statusStr = map.get("status") == null ? "0" : map.get("status").toString();
				map.put("statusName", this.getMsgStatus(Integer.valueOf(statusStr)));
			}
		}
		pageUtil.setList(list);
		return pageUtil;
	}

	public void save(Message message) {
		if (message.getId() != null) {
			messageMapper.update(message);
		} else {
			messageMapper.insert(message);
		}
	}

	private Map<String, Object> queryParm(Message message) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if (message != null) {
			if (message.getReceiveId() != null && message.getReceiveId() > 0) {
				parm.put("receiveId", message.getReceiveId());
			}
			if (message.getMessageStatus() != null && message.getMessageStatus() >= 0) {
				parm.put("messageStatus", message.getMessageStatus());
			}
		}
		parm.put("orderby", "id desc");
		return parm;
	}

	public PageUtil<Map<String, Object>> getSellerMessageListByParm(Map<String, Object> parmMap, int pageNo,
			Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		parmMap.put("start", pageUtil.getStartRow());
		parmMap.put("limit", pageUtil.getPageSize());

		List<Map<String, Object>> list = messageMapper.getSellerMessageListByParm(parmMap);
		pageUtil.setTotalRecords(list.size());

		if (list.size() != 0) {
			list = messageMapper.getSellerMessageListByParm(parmMap);
			// 查询是否有回复
			List<Map<String, Object>> replyList = new ArrayList<Map<String, Object>>();
			Map<String, Object> replyMap = new HashMap<String, Object>();
			replyMap.put("orderby", "m.create_time asc");
			for (Map<String, Object> map : list) {
				parmMap.put("nullMessageId", false);
				replyMap.put("messageId", map.get("messageId"));
				replyList = messageMapper.getSellerMessageListByParm(replyMap);
				map.put("replyList", replyList);
			}
		}

		pageUtil.setList(list);
		return pageUtil;
	}

	/**
	 * 获取用户消息列表 最新的一条消息
	 * 
	 * @param parmMap
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageUtil<Map<String, Object>> getUserMessageListByParm(Long userId, int pageNo, int pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("start", pageNo);
		parmMap.put("limit", pageSize);
		parmMap.put("orderby", "m.create_time desc");
		parmMap.put("postId", userId);
		parmMap.put("postDelFlag", 0);
		List<Map<String, Object>> list = messageMapper.getUserMessageListByParm(parmMap);
		pageUtil.setTotalRecords(list.size());
		pageUtil.setList(list);
		return pageUtil;
	}

	/**
	 * 
	 * 
	 */

	public PageUtil<Map<String, Object>> getUserMessageListByDialogueId(Long DialogueId, int pageNo, int pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("start", pageNo);
		parmMap.put("limit", pageSize);
		parmMap.put("receiveDelFlag", 0);
		parmMap.put("postDelFlag", 0);
		parmMap.put("orderby", "m.create_time desc");
		parmMap.put("messageId", DialogueId);

		List<Map<String, Object>> list = messageMapper.getMessageDialogueIdListByParm(parmMap);
		pageUtil.setTotalRecords(list.size());
		pageUtil.setList(list);
		return pageUtil;
	}

	/**
	 * 删除消息
	 * 
	 * @param message
	 * @param userId
	 * @return
	 */
	public int deleteMessage(Message message, Long userId) {
		if (userId.equals(message.getPostId())) {
			message.setPostDelFlag(true);
		} else if (userId.equals(message.getReceiveId())) {
			message.setReceiveDelFlag(true);
		}
		return messageMapper.update(message);
	}

	/**
	 * 发送消息
	 * 
	 * @param message
	 * @param userId
	 * @return
	 */
	public int sentMessage(Message message) {
		message.setMessageStatus(0);
		message.setCreateTime(new Date(System.currentTimeMillis()));
		message.setReadFlag(false);
		message.setPostDelFlag(false);
		message.setReceiveDelFlag(false);
		message.setMessageId(selectdialogueId(message));

		return messageMapper.insert(message);
	}

	/**
	 * 查询两个用户的对话id
	 */

	public long selectdialogueId(Message message) {
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("receiveId", message.getReceiveId());
		parmMap.put("postId", message.getPostId());
		Long rs = messageMapper.getdialogueIdByParm(parmMap);
		if (rs == null) {
			return 0L;
		}
		return rs;
	}

}
