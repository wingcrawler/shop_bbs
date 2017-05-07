package com.sqe.shop.controller.front;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.BaseBackendController;
import com.sqe.shop.model.Image;
import com.sqe.shop.model.Product;
import com.sqe.shop.service.ImageService;
import com.sqe.shop.service.ProductService;
import com.sqe.shop.service.ProductTypeService;
import com.sqe.shop.service.ShopService;
import com.sqe.shop.util.PageUtil;

@Controller
@RequestMapping("/front/sell")
public class SellerController extends BaseBackendController {
	
	private static final Logger logger = LoggerFactory.getLogger(SellerController.class);
	
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private ImageService imageService;
	
	/**
	 * 商家产品列表页
	 * @return
	 */
	@RequestMapping(value="/listPage", method = RequestMethod.GET)
	public ModelAndView listPage(ModelAndView model) {
		model.setViewName("shop/sell/product_list");
		return model;
	}
	
	/**
	 * 获取产品列表
	 * @param product
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getList", method = RequestMethod.GET)
	@RequiresRoles(value="sell")
	public Map<String, Object> getList(Product product,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		PageUtil<Map<String, Object>> page = productService.getMapListByParm(product, pageNo, pageSize);
		resMap.put("list", page.getList());
		resMap.put("page", page);
		return resMap;
	}
	
	/**
	 * 产品编辑页
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(ModelAndView model, Long id) {
		if(id!=null){
			Product entity = productService.getByIdAndUserId(id);
			if(entity!=null){
				List<Image> images = imageService.getByProductId(entity.getId());
				model.addObject("imgList", images);
			} else {
				model.setViewName("shop/404");
				return model;
			}
			model.addObject("entity", entity);
		}
		model.setViewName("shop/sell/product_edit");
		return model;
	}
	
	/**
	 * 保存产品
	 * @param product
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(Product product,
			@RequestParam(name = "indexFile",value="indexFile", required = false) MultipartFile indexFile,
			@RequestParam(name = "listFile",value="listFile", required = false) MultipartFile listFile) {
		if(StringUtils.isBlank(product.getProductName())){
			return responseError(-1, "error_empty_product_name");
		}
		if(StringUtils.isBlank(product.getProductDescripton())){
			return responseError(-1, "error_empty_description");
		}
		if(product.getProductTypeId()==null || product.getProductTypeId()<0){
			return responseError(-1, "error_no_type");
		}
		if(product.getProductPrice()==null || product.getProductPrice()<0){
			return responseError(-1, "error_empty_product_price");
		}
		productService.save(product);
		return responseOK("save_success");
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/doDelete", method = RequestMethod.GET)
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, "error_no_item");
		}
		int i = productService.delete(id);
		if(i==0){
			return responseError(-1, "error_del_failed");
		}
		return responseOK("op_success");
	}

}
