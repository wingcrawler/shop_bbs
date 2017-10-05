package com.sqe.shop.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.sqe.shop.mapper.MessageMapper;
import com.sqe.shop.model.Message;
import com.sqe.shop.util.PageUtil;

@Component
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
		parmMap.put("orderby", "m.create_time desc");
		parmMap.put("messageId", DialogueId);
		
		List<Map<String, Object>> list = messageMapper.getMessageDialogueIdListByParm(parmMap);
		pageUtil.setTotalRecords(list.size());
		pageUtil.setList(list);
		return pageUtil;
	}
}
