package com.sqe.shop.controller.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.model.Comment;
import com.sqe.shop.model.Image;
import com.sqe.shop.model.Likes;
import com.sqe.shop.model.News;
import com.sqe.shop.service.CommentService;
import com.sqe.shop.service.ImageService;
import com.sqe.shop.service.LikesService;
import com.sqe.shop.service.NewsService;
import com.sqe.shop.service.biz.BizNewsService;
import com.sqe.shop.util.DateUtil;
import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.PropertiesUtil;
import com.sqe.shop.util.RegularUtil;
import com.sqe.shop.util.Resp;

@Controller
@RequestMapping("api/news")
public class ApiNewsController extends BaseFrontController {

	private static final Logger logger = LoggerFactory.getLogger(ApiNewsController.class);

	@Autowired
	private NewsService newsService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ImageService imageService;

	@Autowired
	private BizNewsService bizNewsService;

	@Autowired
	private LikesService likeService;

	/**
	 * 新闻列表页
	 * 
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@PostMapping("list")
	@ResponseBody
	public Resp<?> list(String searchText, @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) throws UnsupportedEncodingException {
		pageSize = 10;

		Map<String, Object> resMap = bizNewsService.getNewsData(searchText, pageNo, pageSize);
		return Resp.success(resMap);
	}

	/**
	 * 发表评论
	 * 
	 * @param news
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "saveComment", method = RequestMethod.POST)
	public Resp<?> saveComment( Comment comment) {
		if (StringUtils.isBlank(comment.getContext())) {
			return Resp.customFail("-1", "error_empty_content");
		}
		comment.setUserId(this.getCurrentUserId());
		commentService.insert(comment);
		return Resp.success("success");
	}

	/**
	 * 异步加载新闻评论
	 * 
	 * @param newsId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "getCommentList", method = RequestMethod.GET)
	public Resp<?> getCommentList(@RequestParam Long newsId, @RequestParam int pageNo, @RequestParam Integer pageSize)
			throws UnsupportedEncodingException {
		News news = new News();
		news.setId(newsId);
		return Resp.success(commentService.getNewsMapListByParm(news, pageNo, pageSize));
	}

	/**
	 * 点赞
	 * 
	 * @param newsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "doNewsUp")
	public Resp<?> thumb(@RequestParam Long newsId) {
		News news = newsService.getById(newsId);
		if (news == null) {
			return  Resp.customFail("-1", "error_thumb");
		}
		// 点赞
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("newsId", newsId);
		parmMap.put("userId", getCurrentUserId());
		int thumbCount1 = likeService.countByParm(parmMap);
		if (thumbCount1 > 0) {
			String lang = PropertiesUtil.get("lang");
			if (lang.equals("zh")) {
				return Resp.success("已经点赞");
			} else {
				return Resp.success("You're already thumb up");
			}
		} else {
			Likes likes = new Likes();
			likes.setNewsId(newsId);
			likes.setUserId(getCurrentUserId());
			likes.setCreateTime(new Date(System.currentTimeMillis()));
			likeService.insert(likes);
			newsService.updateThumb(news.getNewsUp(), newsId);
			/* return responseOK("success_thumb"); */
			return Resp.success("");
		}

	}

	/**
	 * 
	 * 新闻搜索
	 * 
	 * @param newsTitle
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "search", method = RequestMethod.POST)
	public Resp<?> search(String newsTitle,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		if (StringUtils.isBlank(newsTitle)) {
			return  Resp.customFail("-1", "newsTitle null");
		}
		pageSize = 10;

		// 搜索新闻资讯
		News news = new News();
		news.setNewsTitle(newsTitle);
		PageUtil<Map<String, Object>> newsPage = newsService.getMapListByParm(news, pageNo, pageSize);
		if (newsPage.getList() != null && !newsPage.getList().isEmpty()) {
			String newsContent = "";
			for (Map<String, Object> map : newsPage.getList()) {
				newsContent = map.get("newsContent").toString();
				newsContent = RegularUtil.Html2Text(newsContent);
				if (newsContent.length() > 100) {
					newsContent = newsContent.substring(0, 100) + "...";
					map.put("newsContent", newsContent);
				} else {
					map.put("newsContent", newsContent);
				}
			}
		}
		return Resp.success(newsPage);
	}

	/**
	 * 商城首页热门资讯
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/hot", method = RequestMethod.GET)
	@ResponseBody
	public Resp<?> hotNews() {
		// 热门资讯
		// 3个新闻资讯
		PageUtil<Map<String, Object>> newPage = newsService.getMapListByParm(new News(), 1, 3);
		if (newPage.getList() != null && !newPage.getList().isEmpty()) {
			String newsTitle = "";
			String newsContent = "";
			for (Map<String, Object> map : newPage.getList()) {
				newsTitle = map.get("newsTitle").toString();
				if (newsTitle.length() > 10) {
					map.put("newsShortTitle", newsTitle.substring(0, 9) + "...");
				} else {
					map.put("newsShortTitle", newsTitle);
				}

				newsContent = map.get("newsContent").toString();
				newsContent = RegularUtil.Html2Text(newsContent);
				if (newsContent.length() > 100) {
					newsContent = newsContent.substring(0, 100) + "...";
					map.put("newsContent", newsContent);
				} else {
					map.put("newsContent", newsContent);
				}

			}
		}
		return Resp.success(newPage);
	}

}
