package com.sqe.shop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.common.Constants;
import com.sqe.shop.mapper.AdvertisementMapper;
import com.sqe.shop.model.Advertisement;
import com.sqe.shop.model.Product;
import com.sqe.shop.model.ProductType;
import com.sqe.shop.util.PageUtil;

@Component  
public class AdvertisementService extends AdapterService implements BaseService {
	
	@Autowired
    AdvertisementMapper advertisementMapper;
	
	public int insert(Advertisement advertisement) {
		return advertisementMapper.insert(advertisement);
	}
    
    public int update(Advertisement advertisement) {
		return advertisementMapper.update(advertisement);
	}
	
	public int delete(Long id) {
		return advertisementMapper.delete(id);
	}

	public Advertisement getById(Long id) {
		return advertisementMapper.getById(id);
	}
	
	public int countByParm(Advertisement advertisement) {
		Map<String, Object> parm = queryParm(advertisement);
		return advertisementMapper.countByParm(parm);
	}
	
	public PageUtil<Advertisement> getBeanListByParm(Advertisement advertisement, int pageNo, Integer pageSize) {
		PageUtil<Advertisement> pageUtil = new PageUtil<Advertisement>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(advertisement);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		parm.put("orderby", "sort asc");
		
		int count = advertisementMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Advertisement> list = new ArrayList<Advertisement>();
		if(count!=0){
			list = advertisementMapper.getBeanListByParm(parm);
			for(Advertisement ad : list){
				ad.setTypeName(Constants.getADType(ad.getType()));
			}
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Advertisement ad,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(ad);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		parm.put("orderby", "sort asc");
		
		int count = advertisementMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(count!=0){
			list = advertisementMapper.getMapListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}

	public void save(Advertisement ad) {
		if(ad.getId()==null){
			advertisementMapper.insert(ad);
		} else {
			advertisementMapper.update(ad);
		}
		
	}
	
	private Map<String, Object> queryParm(Advertisement ad) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(ad!=null){
			if(ad.getType()!=null && ad.getType()>=0){
				parm.put("type", ad.getType());
			}
		}
		return parm;
	}

}
