package com.sqe.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.AdvertisementMapper;
import com.sqe.shop.model.Advertisement;
import com.sqe.shop.util.PageUtil;

@Component  
public class AdvertisementService extends AdapterService implements BaseService {
	
	@Autowired
    AdvertisementMapper advertisementMapper;
	
	public int insert(Advertisement Advertisement) {
		return advertisementMapper.insert(Advertisement);
	}
    
    public int update(Advertisement Advertisement) {
		return advertisementMapper.update(Advertisement);
	}
	
	public int delete(Long id) {
		return advertisementMapper.delete(id);
	}

	public Advertisement getById(Long id) {
		return advertisementMapper.getById(id);
	}
	
	public int countByParm(Advertisement Advertisement) {
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(Advertisement!=null){
		
		}*/
		return advertisementMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return advertisementMapper.countByParm(parm);
	}
	
	public PageUtil<Advertisement> getBeanListByParm(Advertisement advertisement, int pageNo, Integer pageSize) {
		PageUtil<Advertisement> pageUtil = new PageUtil<Advertisement>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		if(advertisement!=null){
			parm.put("type", advertisement.getType());
		}
		int count = advertisementMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Advertisement> list = advertisementMapper.getBeanListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Map<String, Object> parm,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		int count = advertisementMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Map<String, Object>> list = advertisementMapper.getMapListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}

	public void save(Advertisement ad) {
		if(ad.getId()==null){
			advertisementMapper.insert(ad);
		} else {
			advertisementMapper.update(ad);
		}
		
	}

}
