package com.sqe.shop.service;

import java.util.HashMap;
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
    
    public int insert(Section Section) {
		return sectionMapper.insert(Section);
	}
    
    public int update(Section Section) {
		return sectionMapper.update(Section);
	}
	
	public int delete(Long id) {
		return sectionMapper.delete(id);
	}

	public Section getById(Long id) {
		return sectionMapper.getById(id);
	}
	
	public int countByParm(Section Section) {
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(Section!=null){
		
		}*/
		return sectionMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return sectionMapper.countByParm(parm);
	}
	
	public PageUtil<Section> getBeanListByParm(Section Section, int pageNo, Integer pageSize) {
		PageUtil<Section> pageUtil = new PageUtil<Section>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(Section!=null){
			
		}*/
		int count = sectionMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Section> list = sectionMapper.getBeanListByParm(parm);
			pageUtil.setList(list);
		}
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

}
