package com.sqe.shop.service;

import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.InformMapper;
import com.sqe.shop.model.Inform;
import com.sqe.shop.util.DateUtil;
import com.sqe.shop.util.PageUtil;

@Component  
public class InformService extends AdapterService implements BaseService {
	
	@Autowired
    InformMapper informMapper;
    
    public int insert(Inform inform) {
		return informMapper.insert(inform);
	}
    
    public int update(Inform inform) {
		return informMapper.update(inform);
	}
	
	public int delete(Long id) {
		return informMapper.delete(id);
	}

	public Inform getById(Long id) {
		return informMapper.getById(id);
	}
	
	public int countByParm(Inform inform) {
		Map<String, Object> parm = queryParm(inform);
		return informMapper.countByParm(parm);
	}
	
	public PageUtil<Inform> getBeanListByParm(Inform inform, int pageNo, Integer pageSize) {
		PageUtil<Inform> pageUtil = new PageUtil<Inform>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(inform);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());

		int count = informMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
	
		List<Inform> list = new ArrayList<Inform>();
		if(count!=0){
			list = informMapper.getBeanListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Inform inform,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(inform);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		
		if(inform!=null){
			if(StringUtils.isNotBlank(inform.getInformTitle())){
				parm.put("informTitle", inform.getInformTitle());	
			}
			if(inform.getInfromStatus()!=null && inform.getInfromStatus()>=0){
				parm.put("infromStatus", inform.getInfromStatus());	
			}
		}
		
		int count = informMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = informMapper.getMapListByParm(parm);
			for(Map<String, Object> map : list){
				String statusStr = map.get("status")==null?"0":map.get("status").toString();
				map.put("statusName", this.getInformStatus(Integer.valueOf(statusStr)));
				Date date = (Date) map.get("date");
				map.put("createTimeStr", DateUtil.dateToString(date, DateUtil.DATETIME_FORMATE_2));
			}
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public void save(Inform inform) {
		if(inform.getId()!=null){
			informMapper.update(inform);
		} else {
			informMapper.insert(inform);
		}
	}

	public Map<String, Object> getDetailById(Long id) {
		Map<String, Object> detail = informMapper.getDetailById(id);
		return detail;
	}

	private Map<String, Object> queryParm(Inform inform) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(inform!=null){
			if(inform.getUserPostId()!=null && inform.getUserPostId()>0){
				parm.put("postId", inform.getUserPostId());	
			}
			if(inform.getInfromStatus()!=null && inform.getInfromStatus()>=0){
				parm.put("informStatus", inform.getInfromStatus());
			}
			if(inform.getUserReportedId()!=null && inform.getUserReportedId()>0){
				parm.put("userReportedId", inform.getUserReportedId());
			}
			if(StringUtils.isNotBlank(inform.getInformTitle())){
				parm.put("informTitle", inform.getInformTitle());
			}
		}
		parm.put("orderby", "id asc" );
		return parm;
	}

}
