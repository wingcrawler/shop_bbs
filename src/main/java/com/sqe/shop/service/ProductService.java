package com.sqe.shop.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.mapper.ProductMapper;
import com.sqe.shop.model.Product;
import com.sqe.shop.service.cached.CachedService;
import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.RegularUtil;

@Component
public class ProductService extends AdapterService implements BaseService {

	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private CachedService cachedService;

	public int insert(Product product) {
		return productMapper.insert(product);
	}

	public int update(Product product) {
		return productMapper.update(product);
	}

	public int delete(Long id) {
		return productMapper.delete(id);
	}

	public Product getById(Long id) {
		return productMapper.getById(id);
	}

	public int countByParm(Product product) {
		Map<String, Object> parm = queryParm(product);
		return productMapper.countByParm(parm);
	}

	public PageUtil<Product> getBeanListByParm(Product product, int pageNo, Integer pageSize) {
		PageUtil<Product> pageUtil = new PageUtil<Product>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(product);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());

		int count = productMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		List<Product> list = new ArrayList<Product>();
		if (count != 0) {
			list = productMapper.getBeanListByParm(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}

	public PageUtil<Map<String, Object>> getMapListByParm(Product product, int pageNo, Integer pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(product);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		parm.put("orderby", "p.product_rank asc, p.id desc");

		int count = productMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (count != 0) {
			list = productMapper.getMapListByParm(parm);
			String lang = cachedService.getLang();
			for (Map<String, Object> map : list) {
				String statusStr = map.get("productStatus") == null ? "0" : map.get("productStatus").toString();
				map.put("productStatusStr", this.getProductStatus(Integer.valueOf(statusStr)));

				String productTag = map.get("productTag") == null ? "" : map.get("productTag").toString();
				if (StringUtils.isNotBlank(productTag)) {
					String[] tags = productTag.split(",");
					String tagStr = "";
					if (tags != null && tags.length > 0) {
						for (String str : tags) {
							if (StringUtils.isBlank(str)) {
								continue;
							}
							tagStr += cachedService.getText(str) + " ";
						}
					}
					map.put("productTag", tagStr);
				}

				String productNameCh = map.get("productNameCh") == null ? "" : map.get("productNameCh").toString();
				String productName = map.get("productName") == null ? "" : map.get("productName").toString();
				String productTypeNameCh = map.get("productTypeNameCh") == null ? ""
						: map.get("productTypeNameCh").toString();
				String productTypeName = map.get("productTypeName") == null ? ""
						: map.get("productTypeName").toString();
				if (lang.equals("zh")) {
					map.put("productName", productNameCh);
					map.put("productTypeName", productTypeNameCh);
				}
			}
		}
		pageUtil.setList(list);
		return pageUtil;
	}

	public int save(Product product) {
		if (product.getProductRank() == null) {
			product.setProductRank(0);
		}
		if (product.getId() != null) {
			return this.update(product);
		} else {
			product.setCreateTime(new Date());
			product.setProductStatus(0);
			product.setProductView(0);
			product.setProductUrlClick(0);
			return this.insert(product);
		}
	}

	private Map<String, Object> queryParm(Product product) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if (product != null) {
			if (product.getProductStatus() != null && product.getProductStatus() >= 0) {
				parm.put("productStatus", product.getProductStatus());
			}
			if (product.getProductTypeId() != null && product.getProductTypeId() >= 0) {
				parm.put("productTypeId", product.getProductTypeId());
			}
			if (product.getProductSubtypeId() != null && product.getProductSubtypeId() >= 0) {
				parm.put("productSubtypeId", product.getProductSubtypeId());
			}
			if (product.getShopId() != null && product.getShopId() >= 0) {
				parm.put("shopId", product.getShopId());
			}
			if (StringUtils.isNotBlank(product.getProductName())) {
				try {
					parm.put("productName", URLDecoder.decode(product.getProductName(), "utf8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (StringUtils.isNotBlank(product.getCreateTimeStr())) {
				parm.put("createTimeStr", product.getCreateTimeStr());
			}
			if (product.getUserId() != null && product.getUserId() >= 0) {
				parm.put("userId", product.getUserId());
			}
		}
		parm.put("orderby", "product_rank asc");
		return parm;
	}

	public PageUtil<Map<String, Object>> getHotProductList(Product product, int pageNo, int pageSize) {
		PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(product);
		parm.put("start", pageUtil.getStartRow());
		parm.put("limit", pageUtil.getPageSize());
		parm.put("orderby", "p.product_view desc, p.id desc");

		int count = productMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (count != 0) {
			list = productMapper.getHotProductList(parm);
		}
		pageUtil.setList(list);
		return pageUtil;
	}

	/**
	 * 查询当前用户的商品
	 * 
	 * @param id
	 * @return
	 */
	public Product getByIdAndUserId(Long id) {
		return productMapper.getByIdAndUserId(id, this.getCurrentUserId());
	}

	/**
	 * 删除这个用户的产品
	 * 
	 * @param productId
	 */
	public int deleteByIdAndUserId(Long productId) {
		return productMapper.deleteByIdAndUserId(productId, this.getCurrentUserId());
	}

	/**
	 * 
	 * @param idList
	 * @param productStatus
	 * @return
	 */
	public Map<String, Object> updateProductStatus(List<String> idList, int productStatus) {
		if (idList.size() == 0) {
			return responseOK1("");
		}
		Product product = null;
		for (String str : idList) {
			if (StringUtils.isNotBlank(str.trim()) && RegularUtil.isNumeric(str.trim())) {
				Long productId = Long.valueOf(str.trim());
				product = productMapper.getByIdAndUserId(productId, this.getCurrentUserId());
				if (product != null) {
					product.setId(Long.valueOf(str));
					product.setProductStatus(productStatus);
					this.update(product);
				}
			}
		}
		return responseOK1("");
	}
	/**
	 * 
	 * @param idList
	 * @param productStatus
	 * @return
	 */
	public Map<String, Object> updateProductStatusold(String idList, int productStatus) {
		if (StringUtils.isEmpty(idList)) {
			return responseOK1("");
		}
		Product product = null;
		String rs[]=idList.split(",");
		for (String str : rs) {
			if (StringUtils.isNotBlank(str.trim()) && RegularUtil.isNumeric(str.trim())) {
				Long productId = Long.valueOf(str.trim());
				product = productMapper.getByIdAndUserId(productId, this.getCurrentUserId());
				if (product != null) {
					product.setId(Long.valueOf(str));
					product.setProductStatus(productStatus);
					this.update(product);
				}
			}
		}
		return responseOK1("");
	}

}
