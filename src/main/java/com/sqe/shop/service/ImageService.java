package com.sqe.shop.service;

import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.db2.jcc.a.i;
import com.sqe.shop.mapper.ImageMapper;
import com.sqe.shop.model.Image;
import com.sqe.shop.model.Product;
import com.sqe.shop.model.Shop;
import com.sqe.shop.util.DateUtil;
import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.PropertiesUtil;

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
		Map<String, Object> parm = queryParm(image);
		return imageMapper.countByParm(parm);
	}
	
	public PageUtil<Image> getBeanListByParm(Image image, int pageNo, Integer pageSize) {
		PageUtil<Image> pageUtil = new PageUtil<Image>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(image);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		
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
		parm.put("orderBy", "id asc");
		return imageMapper.getBeanListByParm(parm);
	}
	public List<Image> getByProductIndexImg(Long id) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("productId", id);
		parm.put("indexShow", 1);
		parm.put("orderBy", "id asc");
		return imageMapper.getBeanListByParm(parm);
	}
	
	private Map<String, Object> queryParm(Image image) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(image!=null){
			if(image.getProductId()!=null && image.getProductId()>=0){
				parm.put("productId", image.getProductId());	
			}
			if(image.getShopId()!=null && image.getShopId()>=0){
				parm.put("shopId", image.getShopId());	
			}
			if(image.getNewsId()!=null && image.getNewsId()>=0){
				parm.put("newsId", image.getNewsId());	
			}
			if(image.getPsotId()!=null && image.getPsotId()>=0){
				parm.put("psotId", image.getPsotId());	
			}
		}
		parm.put("orderby", "id asc" );
		return parm;
	}

	public Image getByShopId(Long shopId) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("shopId", shopId);	
		List<Image> images = this.getBeanListByParm("ImageMapper", parm);
		if (images!=null && !images.isEmpty()) {
			return images.get(0);
		}
		return new Image();
	}

	/*public void saveShopImg(Shop shop, String fileName) {
		Image image = this.getByShopId(shop.getId());
	    image.setShopId(shop.getId());
	    String uploadPath = PropertiesUtil.get("upload_path_save"); 
	    uploadPath += DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1)+"/";
	    image.setImagePath(uploadPath+fileName);
	    this.save(image);
	}*/

	public void saveProductImg(Product product, String fileName, int indexShow) {
		Image image = new Image();
	    image.setProductId(product.getId());
	    image.setIndexShow(indexShow);
	    String uploadPath = PropertiesUtil.get("upload_path_save"); 
	    uploadPath += DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1)+"/";
	    image.setImagePath(uploadPath+fileName);
	    this.save(image);
	}

	public void saveProductIndexImg(Product product, String fileName, int indexShow) {
		String uploadPath = PropertiesUtil.get("upload_path_save"); 
	    uploadPath += DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1)+"/";
	    
	    List<Image> images = this.getByProductIndexImg(product.getId());
		if(images!=null && !images.isEmpty()){
			Image image = images.get(0);
			image.setImagePath(uploadPath+fileName);
			this.update(image);
		} else {
			Image image = new Image();
		    image.setProductId(product.getId());
		    image.setIndexShow(indexShow);
		    image.setImagePath(uploadPath+fileName);
		    this.insert(image);
		}
	    
	}

	public int deleteByIdAndProductId(Long id, Long productId) {
		return imageMapper.deleteByIdAndProductId(id, productId);
	}

}
