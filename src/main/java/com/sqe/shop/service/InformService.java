package com.sqe.shop.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.InformMapper;
import com.sqe.shop.model.Inform;
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
		Map<String, Object> parm = new HashMap<String, Object>();
		if(inform!=null){
		
		}
		return informMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return informMapper.countByParm(parm);
	}
	
	public PageUtil<Inform> getBeanListByParm(Inform inform, int pageNo, Integer pageSize) {
		PageUtil<Inform> pageUtil = new PageUtil<Inform>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		if(inform!=null){
			parm.put("start", pageUtil.getStartRow());
			parm.put("limit", pageUtil.getPageSize());
		}
		int count = informMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Inform> list = new ArrayList<Inform>();
		if(count!=0){
			list = informMapper.getBeanListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Map<String, Object> parm,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		int count = informMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Map<String, Object>> list = informMapper.getMapListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	public void save(Inform inform) {
		if(inform.getId()!=null){
			informMapper.update(inform);
		} else {
			informMapper.insert(inform);
		}
	}

}
