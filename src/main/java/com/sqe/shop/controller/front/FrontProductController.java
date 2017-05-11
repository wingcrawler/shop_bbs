package com.sqe.shop.controller.front;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.Constants;
import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.model.Image;
import com.sqe.shop.model.Message;
import com.sqe.shop.model.Product;
import com.sqe.shop.service.CommentService;
import com.sqe.shop.service.ImageService;
import com.sqe.shop.service.MessageService;
import com.sqe.shop.service.ProductService;
import com.sqe.shop.util.PageUtil;

@Controller
@RequestMapping("/product")
public class FrontProductController extends BaseFrontController {
	
	private static final Logger logger = LoggerFactory.getLogger(FrontProductController.class);
	
	@Autowired
	private ProductService productService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private MessageService messageService;
	
	/**
	 * 商品详情
	 * @param model
	 * @return
	 */
	@RequestMapping(value="single", method = RequestMethod.GET)
	public ModelAndView single(ModelAndView model, Long productId) {
		//查询单个商品
		Product product = productService.getById(productId);
		if(product==null){
			model.setViewName("shop/404");
			return model;
		}
		model.addObject("product", product);
		//查询商品图片
		Image image = new Image();
		image.setProductId(productId);
		PageUtil<Image> imgPage = imageService.getBeanListByParm(image, 0, -1);
		model.addObject("imgList", imgPage.getList());
		
		model.setViewName("shop/single");
		return model;
	}
	
	/**
	 * 产品私信
	 * @param model
	 * @param message
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="postMessage", method = RequestMethod.POST)
	public Map<String, Object> postMessage(ModelAndView model, Message message) {
		message.setMessageStatus(Constants.MSG_OFF);
		message.setPostId(this.getCurrentUserId());
		message.setCreateTime(new Date());
		messageService.insert(message);
		return responseOK("send_success");
	}
	
	
}