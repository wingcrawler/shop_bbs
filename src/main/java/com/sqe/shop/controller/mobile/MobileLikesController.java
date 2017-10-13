package com.sqe.shop.controller.mobile;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.model.Likes;
import com.sqe.shop.service.biz.BizLikesService;

@Controller
@RequestMapping("/h5/likes")
public class MobileLikesController extends BaseFrontController {
	@Autowired
	private BizLikesService bizLikesService;

	/**
	 * 资讯列表
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping("/likesList")
	public Map<String, Object> index(@RequestParam(name="pageNo", defaultValue="1") int pageNo, Integer pageSize)  {
		if(pageSize==null || pageSize<0){
			pageSize=Integer.MAX_VALUE;	
		}
		
		Map<String, Object> parmMap = this.getParmMap();
		parmMap.put("pageNo", pageNo);
		parmMap.put("pageSize", pageSize);
		return bizLikesService.getLikesList(parmMap);
	}
	
	/**
	 * 点赞
	 * @param likes
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="thumbUp")
	public Map<String, Object> thumbUp(Likes likes) {
		return bizLikesService.thumbUp(likes);
		
	}
	
}
