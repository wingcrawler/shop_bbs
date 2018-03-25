package com.sqe.shop.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.sqe.shop.common.Constants;
import com.sqe.shop.mapper.PostMapper;
import com.sqe.shop.mapper.ThreadMapper;
import com.sqe.shop.mapper.TopicMapper;
import com.sqe.shop.mapper.UserThumbMapper;
import com.sqe.shop.model.News;
import com.sqe.shop.model.Section;
import com.sqe.shop.model.Thread;
import com.sqe.shop.model.UserThumb;
import com.sqe.shop.util.DateUtil;
import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.RelativeDateFormat;

@Component
public class ThreadService extends AdapterService implements BaseService {

	private Logger logger = LoggerFactory.getLogger(ThreadService.class);

	@Autowired
	private ThreadMapper threadMapper;
	@Autowired
	private RelativeDateFormat relativeDateFormat;
	@Autowired
	private TopicMapper topicMapper;
	@Autowired
	private PostMapper postMapper;

	@Autowired
	private UserThumbMapper userThumbMapper;

	public int insert(Thread thread) {
		return threadMapper.insert(thread);
	}

	public int update(Thread thread) {
		return threadMapper.update(thread);
	}

	public int viewUpdate(Thread thread) {
		Thread t = new Thread();
		t.setId(thread.getId());
		t.setThreadView(thread.getThreadView() + 1);
		return threadMapper.update(t);
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
				t.setTime(relativeDateFormat.format(time));

			}
		}

		pageUtil.setList(list);
		return pageUtil;
	}

	public PageUtil<Map<String, Object>> getMapListByParm(Thread thread, int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(thread);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		parm.put("orderby", "t.id desc");
		int count = threadMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (count != 0) {
			list = threadMapper.getMapListByParm(parm);
			parm.clear();
			for (Map<String, Object> map : list) {
				Date date = (Date) map.get("date");
				map.put("createTimeStr", DateUtil.dateToString(date, DateUtil.DATETIME_FORMATE_2));
				String type = map.get("threadType").toString();
				map.put("typeName", this.getThreadType(Integer.valueOf(type)));
				String status = map.get("threadStatus").toString();
				map.put("statusName", this.getThreadStatus(Integer.valueOf(status)));
				parm.put("threadId", map.get("id"));
				parm.put("postStatus", 1);
				map.put("threadPostNum", postMapper.countByParm(parm));
			}
		}
		pageUtil.setList(list);
		return pageUtil;
	}

	/**
	 * 
	 * @param thread
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageUtil<Map<String, Object>> getHot(Thread thread, int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(thread);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		parm.put("view", thread.getThreadView());
		parm.put("orderby", "t.id desc");
		int count = threadMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (count != 0) {
			list = threadMapper.getHotThread(parm);
			parm.clear();
			for (Map<String, Object> map : list) {
				Date date = (Date) map.get("date");
				map.put("createTimeStr", DateUtil.dateToString(date, DateUtil.DATETIME_FORMATE_2));
				String type = map.get("threadType").toString();
				map.put("typeName", this.getThreadType(Integer.valueOf(type)));
				parm.put("threadId", map.get("id"));
				parm.put("postStatus", 1);
				map.put("threadPostNum", postMapper.countByParm(parm));
				map.put("relativeDate", RelativeDateFormat.format((Date) map.get("date")));
			}
		}
		pageUtil.setList(list);
		return pageUtil;
	}

	/**
	 * 
	 * @param thread
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageUtil<Map<String, Object>> getSectionMapListByParm(Thread thread, int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(thread);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		parm.put("orderby", "t.id desc");
		int count = threadMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (count != 0) {
			list = threadMapper.getSectionMapListByParm(parm);
			for (Map<String, Object> map : list) {
				Date date = (Date) map.get("date");
				map.put("timeAgo", relativeDateFormat.format(date));
				map.put("createTimeStr", DateUtil.dateToString(date, DateUtil.DATETIME_FORMATE_2));
				String type = map.get("threadType").toString();
				map.put("typeName", this.getThreadType(Integer.valueOf(type)));
			}
		}
		pageUtil.setList(list);
		return pageUtil;
	}

	/**
	 * 根据section 获取该版块下帖子V1
	 * 
	 * @deprecated
	 * @param section
	 * @param thread
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageUtil<Map<String, Object>> getSectionMapListByParm(Section section, Thread thread, int pageNo,
			Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(thread, section);
		parm.put("orderby", "t.id desc");
		int count = threadMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (count != 0) {
			list = threadMapper.getSectionMapListByParm(parm);
			for (Map<String, Object> map : list) {
				Date date = (Date) map.get("date");
				map.put("timeAgo", relativeDateFormat.format(date));
				map.put("createTimeStr", DateUtil.dateToString(date, DateUtil.DATETIME_FORMATE_2));
				String type = map.get("threadType").toString();
				map.put("typeName", this.getThreadType(Integer.valueOf(type)));
			}
		}
		pageUtil.setList(list);
		return pageUtil;
	}

	/**
	 * 根据section 获取该版块下帖子V2 ajax 调用 10篇帖子
	 * 
	 * @param section
	 * @param thread
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageUtil<Map<String, Object>> getThreadsMapListByParm(Thread thread, int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(thread);
		parm.put("orderby", "t.thread_identify desc , t.id desc ");
		parm.put("start", 0);
		parm.put("limit", 10);
		int count = threadMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (count != 0) {
			list = threadMapper.getSectionMapListByParm(parm);
			for (Map<String, Object> map : list) {
				Date date = (Date) map.get("date");
				map.put("timeAgo", relativeDateFormat.format(date));
				map.put("createTimeStr", DateUtil.dateToString(date, DateUtil.DATETIME_FORMATE_2));
				String type = map.get("threadType").toString();
				map.put("typeName", this.getThreadType(Integer.valueOf(type)));
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
				try {
					parm.put("threadTitle", URLDecoder.decode(thread.getThreadTitle(), UTF8));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (thread.getThreadType() != null && thread.getThreadType() >= 0) {
				parm.put("threadType", thread.getThreadType());
			}
			if (thread.getTopicId() != null && thread.getTopicId() >= 0) {
				parm.put("topicId", thread.getTopicId());
			}
			if (thread.getUserId() != null && thread.getUserId() >= 0) {
				parm.put("userId", thread.getUserId());
			}
			if (thread.getSectionId() != null) {
				parm.put("sectionId", thread.getSectionId());
			}
			if (thread.getThreadStatus() != null) {
				parm.put("threadStatus", thread.getThreadStatus());
			}

		}
		return parm;
	}

	private Map<String, Object> queryParm(Thread thread, Section section) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if (section != null) {
			if (StringUtils.isNotBlank(thread.getThreadTitle())) {
				parm.put("threadTitle", thread.getThreadTitle());
			}
			if (thread.getThreadType() != null && thread.getThreadType() >= 0) {
				parm.put("threadType", thread.getThreadType());
			}
			if (thread.getTopicId() != null && thread.getTopicId() >= 0) {
				parm.put("topicId", thread.getTopicId());
			}
			if (section.getId() != null && section.getId() >= 0) {
				parm.put("sectionId", section.getId());
			}
			if (thread.getUserId() != null && thread.getUserId() >= 0) {
				parm.put("userId", thread.getUserId());
			}
			if (thread.getThreadStatus() != null) {
				parm.put("threadStatus", thread.getThreadStatus());
			}
		}
		return parm;
	}

	public Map<String, Object> getMapById(Long id) {
		return threadMapper.getMapById(id);
	}

	// 商家页面的发帖回复列表
	public PageUtil<Map<String, Object>> getSellThreadList(Thread thread, int pageNo, int pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(thread);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		parm.put("orderby", "t.id desc");

		int count = threadMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int postCount = 0;
		Map<String, Object> postMap = new HashMap<String, Object>();
		if (count != 0) {
			list = threadMapper.getMapListByParm(parm);
			for (Map<String, Object> t : list) {
				// 时间转换
				Date time = (Date) t.get("date");
				t.put("time", DateUtil.dateToString(time, DateUtil.DATETIME_FORMATE_2));
				// 跟帖数量
				postMap.put("threadId", t.get("id"));
				postCount = postMapper.countByParm(postMap);
				t.put("count", postCount);
			}
		}
		pageUtil.setList(list);

		return pageUtil;
	}

	public PageUtil<Map<String, Object>> getSectionOneThreadList(int pageNo, int pageSize, Long sectionId) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();

		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		parm.put("orderby", "t.id desc");
		parm.put("sectionParentId", sectionId);

		int count = threadMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (count > 0) {
			list = threadMapper.getSectionOneThreadList(parm);
			logger.info("getSectionOneThreadList->List={}", JSON.toJSON(list));
			for (Map<String, Object> map : list) {
				// 时间转换
				Date time = (Date) map.get("date");
				map.put("timeAgo", relativeDateFormat.format(time));
				map.put("time", DateUtil.dateToString(time, DateUtil.DATETIME_FORMATE_2));
				String type = map.get("threadType").toString();
				map.put("typeName", this.getThreadType(Integer.valueOf(type)));
			}
		}
		pageUtil.setList(list);

		return pageUtil;
	}

	public void updateThumb(Long bbsUpCount, Long Id) {
		Thread bbs = new Thread();
		bbs.setId(Id);
		bbs.setThreadUp(bbsUpCount + 1);
		this.update(bbs);

		UserThumb thumb = new UserThumb();
		thumb.setBbsId(Id);
		thumb.setUserId(this.getCurrentUserId());
		thumb.setCreateTime(new Date());
		userThumbMapper.insert(thumb);
	}

}
