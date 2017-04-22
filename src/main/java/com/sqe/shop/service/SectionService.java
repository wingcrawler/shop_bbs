package com.sqe.shop.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.SectionMapper;
import com.sqe.shop.model.Section;
import com.sqe.shop.util.PageUtil;

@Component  
public class SectionService extends AdapterService implements BaseService {
	
	@Autowired
    SectionMapper sectionMapper;
    
    public int insert(Section section) {
		return sectionMapper.insert(section);
	}
    
    public int update(Section section) {
		return sectionMapper.update(section);
	}
	
	public int delete(Long id) {
		return sectionMapper.delete(id);
	}

	public Section getById(Long id) {
		return sectionMapper.getById(id);
	}
	
	public int countByParm(Section section) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(section!=null){
		
		}
		return sectionMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return sectionMapper.countByParm(parm);
	}
	
	public PageUtil<Section> getBeanListByParm(Section section, int pageNo, Integer pageSize) {
		PageUtil<Section> pageUtil = new PageUtil<Section>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		if(section!=null){
			parm.put("start", pageUtil.getStartRow());
			parm.put("limit", pageUtil.getPageSize());
		}
		int count = sectionMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Section> list = new ArrayList<Section>();
		if(count!=0){
			list = sectionMapper.getBeanListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Map<String, Object> parm,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		int count = sectionMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Map<String, Object>> list = sectionMapper.getMapListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	public void save(Section section) {
		if(section.getId()!=null){
			sectionMapper.update(section);
		} else {
			sectionMapper.insert(section);
		}
	}

}
