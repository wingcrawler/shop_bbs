package com.sqe.shop.service;

import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.common.Constants;
import com.sqe.shop.mapper.ThreadMapper;
import com.sqe.shop.model.Thread;
import com.sqe.shop.util.DateUtil;
import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.RelativeDateFormat;

@Component
public class ThreadService extends AdapterService implements BaseService {

	@Autowired
	ThreadMapper threadMapper;
	@Autowired
	RelativeDateFormat dateFormat;

	public int insert(Thread thread) {
		return threadMapper.insert(thread);
	}

	public int update(Thread thread) {
		return threadMapper.update(thread);
	}

	public int delete(Long id) {
		return threadMapper.delete(id);
	}

	public Thread getById(Long id) {
		return threadMapper.getById(id);
	}

	public int countByParm(Thread thread) {
		Map<String, Object> parm = queryParm(thread);
		return threadMapper.countByParm(parm);
	}

	public PageUtil<Thread> getBeanListByParm(Thread thread, int pageNo, Integer pageSize) {
		PageUtil<Thread> pageUtil = new PageUtil<Thread>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(thread);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());

		int count = threadMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Thread> list = new ArrayList<Thread>();
		if (count != 0) {
			list = threadMapper.getBeanListByParm(parm);
			for (Thread t : list) {
				Date time = t.getThreadTime();
				t.setTime(dateFormat.format(time));

			}
		}

		pageUtil.setList(list);
		return pageUtil;
	}

	public PageUtil<Map<String, Object>> getMapListByParm(Thread thread, int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(thread);
		parm.put("orderby", "t.id desc");
		int count = threadMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (count != 0) {
			list = threadMapper.getMapListByParm(parm);
			for (Map<String, Object> map : list) {
				Date date = (Date) map.get("date");
				map.put("createTimeStr", DateUtil.dateToString(date, DateUtil.DATETIME_FORMATE_2));
				String type = map.get("threadType").toString();
				map.put("typeName", Constants.getThreadType(Integer.valueOf(type)));
				String status = map.get("threadStatus").toString();
				map.put("statusName", Constants.getThreadStatus(Integer.valueOf(status)));
			}
		}
		pageUtil.setList(list);
		return pageUtil;
	}

	public void save(Thread thread) {
		if (thread.getId() != null) {
			threadMapper.update(thread);
		} else {
			threadMapper.insert(thread);
		}
	}

	private Map<String, Object> queryParm(Thread thread) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if (thread != null) {
			if (StringUtils.isNotBlank(thread.getThreadTitle())) {
				parm.put("threadTitle", thread.getThreadTitle());
			}
			if (thread.getThreadType() != null && thread.getThreadType() >= 0) {
				parm.put("threadType", thread.getThreadType());
			}
			if (thread.getTopicId() != null && thread.getTopicId() >= 0) {
				parm.put("topicId", thread.getTopicId());
			}
		}
		return parm;
	}
	
	public Map<String, Object> getMapById(Long id) {
		return threadMapper.getMapById(id);
	}

}
