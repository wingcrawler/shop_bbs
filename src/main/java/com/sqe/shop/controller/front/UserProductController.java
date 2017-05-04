package com.sqe.shop.controller.front;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.BaseBackendController;
import com.sqe.shop.model.Image;
import com.sqe.shop.model.Product;
import com.sqe.shop.model.ProductType;
import com.sqe.shop.model.Shop;
import com.sqe.shop.service.ImageService;
import com.sqe.shop.service.ProductService;
import com.sqe.shop.service.ProductTypeService;
import com.sqe.shop.service.ShopService;
import com.sqe.shop.util.PageUtil;

@Controller
@RequestMapping("/front/product")
public class UserProductController extends BaseBackendController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserProductController.class);
	
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private ImageService imageService;
	
	/**
	 * 产品列表
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
	@RequiresRoles(value="sell")
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("backend/product/edit");
		if(id!=null){
			Product entity = productService.getById(id);
			if(entity!=null){
				List<Image> images = imageService.getByProductId(entity.getId());
				model.addObject("imgList", images);
			}
			model.addObject("entity", entity);
		}
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(Product product) {
		productService.save(product);
		return responseOK("save_success");
	}
	
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
