package com.sqe.shop.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.ImageMapper;
import com.sqe.shop.model.Image;
import com.sqe.shop.util.PageUtil;

@Component  
public class ImageService extends AdapterService implements BaseService {
	
	@Autowired
    ImageMapper imageMapper;
    
    public int insert(Image image) {
		return imageMapper.insert(image);
	}
    
    public int update(Image image) {
		return imageMapper.update(image);
	}
	
	public int delete(Long id) {
		return imageMapper.delete(id);
	}

	public Image getById(Long id) {
		return imageMapper.getById(id);
	}
	
	public int countByParm(Image image) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(image!=null){
		
		}
		return imageMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return imageMapper.countByParm(parm);
	}
	
	public PageUtil<Image> getBeanListByParm(Image image, int pageNo, Integer pageSize) {
		PageUtil<Image> pageUtil = new PageUtil<Image>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		if(image!=null){
			parm.put("start", pageUtil.getStartRow());
			parm.put("limit", pageUtil.getPageSize());
		}
		int count = imageMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Image> list = new ArrayList<Image>();
		if(count!=0){
			list = imageMapper.getBeanListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}
	
	public PageUtil<Map<String, Object>> getMapListByParm(Map<String, Object> parm,int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		int count = imageMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Map<String, Object>> list = imageMapper.getMapListByParm(parm);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	public void save(Image image) {
		if(image.getId()!=null){
			imageMapper.update(image);
		} else {
			imageMapper.insert(image);
		}
	}

	public List<Image> getByProductId(Long id) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("productId", id);
		return imageMapper.getBeanListByParm(parm);
	}

}
