package com.sqe.shop.common;

import org.springframework.beans.factory.annotation.Autowired;

import com.sqe.shop.service.cached.CachedService;

public class Constants{
	
	@Autowired
	private CachedService cachedService;
	
	public static final String UNKNOW_INFO = "Unknow Exception";
	
	public static final String UTF8 = "utf-8";
	
    //订单状态
    public static final Integer ORDER_PAYING = 0;//待付款
    public static final Integer ORDER_PAYED = 1;//已付款
    public static final Integer ORDER_SHIPPED = 2;//已发货
    public static final Integer ORDER_DELETED = 3;//已删除
    public static final Integer ORDER_ENDED = 4;//已签收
    public static final Integer ORDER_BACK = 5;//已退货
    public String getOrderType(Integer value) {
    	if(value==null){
    		return cachedService.getText("t_unknow");
    	}
		if(value==0){
			return cachedService.getText("t_order_paying");
		} else if(value==1){
			return cachedService.getText("t_order_payed");
		} else if(value==2){
			return cachedService.getText("t_order_shipped");
		} else if(value==3){
			return cachedService.getText("t_order_deleted");
		} else if(value==4){
			return cachedService.getText("t_order_ended");
		} else if(value==5){
			return cachedService.getText("t_order_back");
		} else {
			return cachedService.getText("t_unknow");
		}
	}
    
    //用户权限
    public static final Long ROLE_BUY = 1L; //买家
    public static final Long ROLE_SELL = 2L; //卖家
    public static final Long ROLE_ADMIN = 3L; //管理员
    public String getUserRoleStr(Integer value) {
    	return cachedService.getString("user_role_"+value);
	}
    
    //错误号
    public static final String ERROR_NO = "errorNo";
    public static final String ERROR_INFO = "errorInfo";
    public static final int ERRORCODE_SUCCESS = 0; //成功
    public static final int ERRORCODE_FAILED = -1; //失败
    public static final int ERRORCODE_NOLOGIN = 2; //未登录
    
    //广告类型
    public static final int AD_TYPE_LB = 0; //轮播图
    public static final int AD_TYPE_GGW = 1; //广告位
    public String getADType(Integer value) {
    	return cachedService.getString("ad_type_"+value);
	}
    
    //广告类型
    public static final int AD_DEVICE_PC = 0; // 电脑版
    public static final int AD_DEVICE_APP = 1; // 手机版
    public String getADDevice(Integer value) {
    	return cachedService.getString("ad_device_"+value);
	}
    
    //产品状态
    public static final int PRODUCT_ON = 1; //上架
    public static final int PRODUCT_OFF = 2; //下架
    public static final int PRODUCT_WAIT = 0; //待审核
    public String getProductStatus(Integer value) {
    	return cachedService.getString("product_status_"+value);
	}
    
    //产品分类等级
    public static final int PRODUCT_LEVEL_ONE = 1; //一级分类
    public static final int PRODUCT_LEVEL_TWO = 2; //二级分类
    public String getProductLevel(Integer value) {
    	return cachedService.getString("product_level_"+value);
	}
    
    //news 语言
    public static final int NEWS_ZH = 1; //中文
    public static final int NEWS_EN = 2; //英文
    public String getNewsLang(Integer value) {
    	return cachedService.getString("news_lang_"+value);
	}
    
    //评论状态
    public static final int COMMENT_OFF = 0; //屏蔽
    public static final int COMMENT_ON = 1; //正常
    public String getCommentStatus(Integer value) {
    	return cachedService.getString("comment_status_"+value);
	}
    
    //举报消息状态
    public static final int INFORM_TODO = 0; //待处理
    public static final int INFORM_DONE = 1; //已处理
    public String getInformStatus(Integer value) {
    	return cachedService.getString("inform_status_"+value);
	}
    
    //店家状态
    public static final Integer STORE_OFF = 0; //关闭
    public static final Integer STORE_ON = 1; //正常
    public static final Integer STORE_PEND = 2; //待审核
    public static final Integer STORE_FAILED = 3; //不通过
    public String  getStoreStatus(Integer value) {
    	return cachedService.getString("shop_status_"+value);
	}
    
    //留言状态
    public static final int MSG_OFF = 0; //未读
    public static final int MSG_ON = 1; //已读
    public String getMsgStatus(Integer value) {
    	return cachedService.getString("msg_status_"+value);
	}
    
    //用户状态
    public static final int USER_OFF = 0; //屏蔽
    public static final int USER_ON = 1; //正常
    public String getUserStatus(Integer value) {
    	return cachedService.getString("user_status_"+value);
	}
    
    //版块状态
    public static final int SECTION_OFF = 0; //关闭
    public static final int SECTION_ON = 1; //正常
    public String getSectionStatus(Integer value) {
    	return cachedService.getString("section_status_"+value);
	}
    
    //话题状态
    public static final int TOPIC_OFF = 0; //关闭
    public static final int TOPIC_ON = 1; //正常
    public String getTopicStatus(Integer value) {
    	return cachedService.getString("topic_status_"+value);
	}
    
    //帖子类型
    public static final int THREAD_ZH = 1; //中文
    public static final int THREAD_EN = 0; //英文
    public String getThreadType(Integer value) {
    	return cachedService.getString("thread_type_"+value);
	}
   
    //帖子状态
    public static final int THREAD_OFF = 0; //关闭
    public static final int THREAD_ON = 1; //正常
    public static final int THREAD_RECOMMEND = 2; //管理员推荐
	public String getThreadStatus(Integer value) {
		return cachedService.getString("thread_status_"+value);
	}
}
