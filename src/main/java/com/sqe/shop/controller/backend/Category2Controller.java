package com.sqe.shop.controller.backend;

import java.util.HashMap;
import java.util.List;
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

import com.sqe.shop.common.BaseBackendController;
import com.sqe.shop.model.ProductType;
import com.sqe.shop.service.ProductTypeService;
import com.sqe.shop.util.PageUtil;

/**
 * 产品类别 二级分类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/backend/cate2")
public class Category2Controller extends BaseBackendController {
	
	private static final Logger logger = LoggerFactory.getLogger(Category2Controller.class);
	
	@Autowired
	private ProductTypeService productTypeService;
	
	/**
	 * 列表页
	 * @return
	 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("typeLevel", 1);
		parm.put("orderby", "type_rank asc");
		List<ProductType> typeList = productTypeService.getBeanListByParm("ProductTypeMapper", parm);
		model.addObject("typeList", typeList);
		
		model.setViewName("backend/product/cate2_list");
		return model;
	}
	
	/**
	 * 编辑页
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("backend/product/cate2_edit");
		if(id!=null){
			ProductType entity = productTypeService.getById(id);
			model.addObject("entity", entity);
		}
		
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("typeLevel", 1);
		parm.put("orderby", "type_rank asc");
		List<ProductType> typeList = productTypeService.getBeanListByParm("ProductTypeMapper", parm);
		model.addObject("typeList", typeList);
		
		return model;
	}
	
	/**
	 * 获取列表接口
	 * @param productType
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getList", method = RequestMethod.GET)
	public Map<String, Object> getList(ProductType productType,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		productType.setTypeLevel(2);
		PageUtil<ProductType> page = productTypeService.getBeanListByParm(productType, pageNo, pageSize);
		resMap.put("list", page.getList());
		resMap.put("page", page);
		return resMap;
	}
	
	/**
	 * 保存
	 * @param productType
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> doSave(ProductType productType) {
		if (StringUtils.isBlank(productType.getTypeName()) ||
				StringUtils.isBlank(productType.getTypeNameCh()) ) {
			return responseError(-1, "error_no_typename");
		}
		productType.setTypeLevel(2);
		productTypeService.save(productType);
		return responseOK("op_success");
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete", method = RequestMethod.GET)
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, "error_no_item");
		}
		int i = productTypeService.delete(id);
		if(i==0){
			return responseError(-1, "error_del_failed");
		}
		return responseOK("op_success");
	}
	
}
