package com.sqe.shop.controller.backend;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.BaseController;
import com.sqe.shop.model.Advertisement;
import com.sqe.shop.model.ProductType;
import com.sqe.shop.service.ProductTypeService;
import com.sqe.shop.util.PageUtil;

@Controller
@RequestMapping("/backend/cate")
public class CategoryController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private ProductTypeService productTypeService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("backend/product/cate_list");
		return model;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("backend/product/cate_edit");
		if(id!=null){
			ProductType entity = productTypeService.getById(id);
			model.addObject("entity", entity);
		}
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList", method = RequestMethod.GET)
	public Map<String, Object> getList(ProductType productType,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  @RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(StringUtils.isBlank(productType.getTypeName())){
			productType.setTypeName(null);
		}
		PageUtil<ProductType> page = productTypeService.getBeanListByParm(productType, pageNo, pageSize);
		resMap.put("list", page.getList());
		resMap.put("page", page);
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doEdit", method = RequestMethod.POST)
	public Map<String, Object> doEdit(ProductType productType) {
		if (StringUtils.isBlank(productType.getTypeName()) ||
				StringUtils.isBlank(productType.getTypeNameCh()) ) {
			return responseError(-1, bundle.getString("error_no_typename"));
		}
		productTypeService.save(productType);
		return responseOK(bundle.getString("op_success"));
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete", method = RequestMethod.GET)
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, bundle.getString("error_no_item"));
		}
		int i = productTypeService.delete(id);
		if(i==0){
			return responseError(-1, bundle.getString("error_del_failed"));
		}
		return responseOK(bundle.getString("op_success"));
	}
	
}
