package com.sqe.shop.service;

import java.util.HashMap;
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
    
    public int insert(Image Image) {
		return imageMapper.insert(Image);
	}
    
    public int update(Image Image) {
		return imageMapper.update(Image);
	}
	
	public int delete(Long id) {
		return imageMapper.delete(id);
	}

	public Image getById(Long id) {
		return imageMapper.getById(id);
	}
	
	public int countByParm(Image Image) {
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(Image!=null){
		
		}*/
		return imageMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return imageMapper.countByParm(parm);
	}
	
	public PageUtil<Image> getBeanListByParm(Image Image, int pageNo, Integer pageSize) {
		PageUtil<Image> pageUtil = new PageUtil<Image>(pageNo, pageSize);
		Map<String, Object> parm = new HashMap<String, Object>();
		// TODO
		/*if(Image!=null){
			
		}*/
		int count = imageMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if(count!=0){
			List<Image> list = imageMapper.getBeanListByParm(parm);
			pageUtil.setList(list);
		}
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

}
