package com.sqe.shop.controller.front;

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

import com.sqe.shop.common.Constants;
import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.model.Image;
import com.sqe.shop.model.Message;
import com.sqe.shop.model.Product;
import com.sqe.shop.service.ImageService;
import com.sqe.shop.service.MessageService;
import com.sqe.shop.service.ProductService;
import com.sqe.shop.service.ProductTypeService;
import com.sqe.shop.service.ShopService;
import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.RegularUtil;

@Controller
@RequestMapping("/front/sell")
public class SellerController extends BaseFrontController {
	
	private static final Logger logger = LoggerFactory.getLogger(SellerController.class);
	
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private MessageService messageService;
	
	//商品管理
	/**
	 * 商家产品列表页
	 * @return
	 */
	@RequestMapping(value="/productListPage", method = RequestMethod.GET)
	public ModelAndView productListPage(ModelAndView model, Product product,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		model.addObject("productPage", getProductList(product, pageNo, pageSize));
		
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
	@RequestMapping(value="getProductList", method = RequestMethod.GET)
	@RequiresRoles(value="sell")
	public PageUtil<Map<String, Object>> getProductList(Product product,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		product.setUserId(this.getCurrentUserId());
		PageUtil<Map<String, Object>> page = productService.getMapListByParm(product, pageNo, pageSize);
		
		return page;
	}
	
	/**
	 * 产品编辑页
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/editProduct", method = RequestMethod.GET)
	public ModelAndView editProduct(ModelAndView model, Long id) {
		Product entity = new Product();
		if(id!=null){
			entity = productService.getByIdAndUserId(id);
			if(entity!=null){
				List<Image> images = imageService.getByProductId(entity.getId());
				model.addObject("imgList", images);
			} else {
				model.setViewName("shop/404");
				return model;
			}
		}
		model.addObject("entity", entity);

		model.setViewName("shop/sell/product_edit");
		return model;
	}
	
	/**
	 * 保存商品
	 * @param product
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/doSaveProduct", method = RequestMethod.POST)
	public Map<String, Object> doSaveProduct(Product product,
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
	 * 删除商品
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteProductById", method = RequestMethod.GET)
	public Map<String, Object> deleteProductById(String idList) {
		if(StringUtils.isBlank(idList)){
			return responseOK1("");	
		}
		String arr[] = idList.split(",");
		for(String str : arr){
			if(StringUtils.isNotBlank(str.trim()) && RegularUtil.isNumeric(str.trim())){
				Long productId = Long.valueOf(str.trim());
				productService.deleteByIdAndUserId(productId);	
			}
		}
		return responseOK1("");
	}
	/**
	 * 下架商品
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/offProductById", method = RequestMethod.POST)
	public Map<String, Object> offProductById(String idList) {
		return productService.updateProductStatus(idList, Constants.PRODUCT_OFF);
	}
	
	/**
	 * 上架商品
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/onProductById", method = RequestMethod.POST)
	public Map<String, Object> onProductById(String idList) {
		return productService.updateProductStatus(idList, Constants.PRODUCT_ON);
	}
	
	//私信
	/**
	 * 商品留言页面 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/messagePage", method = RequestMethod.GET)
	public ModelAndView messagePage(ModelAndView model) {
		model.setViewName("shop/sell/message_list");
		return model;
	}
	/**
	 * 私信列表
	 * @param message
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getMessageList", method = RequestMethod.GET)
	public PageUtil<Map<String, Object>> getMessageList(Message message, int pageNo, Integer pageSize) {
		message.setReceiveId(this.getCurrentUserId());
		PageUtil<Map<String, Object>> msgPage = messageService.getMapListByParm(message, 1, pageSize, "m.create_time desc");
		return msgPage;
	}
	/**
	 * 回复私信
	 * @param msg
	 * @param productId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/messageReply", method = RequestMethod.GET)
	public Map<String, Object> messageReply(String msgContent, Long productId) {
		return responseOK1("");
	}

}
