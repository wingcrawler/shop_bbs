package com.sqe.shop.common;

public class Constants extends BaseCommon{
	public static final String UNKNOW_INFO = "Unknow Exception";
	
	public static final String LOGIN_USER = "login_user";
	public static final String LOGIN_ADMIN = "login_admin";
    public static final int IMG_WIDTH = 300;
    public static final int IMG_HEIGHT = 300;

    //订单状态
    public static final Integer ORDER_PAYING = 0;//待付款
    public static final Integer ORDER_PAYED = 1;//已付款
    public static final Integer ORDER_SHIPPED = 2;//已发货
    public static final Integer ORDER_DELETED = 3;//已删除
    public static final Integer ORDER_ENDED = 4;//已签收
    public static final Integer ORDER_BACK = 5;//已退货
    public static String getOrderType(Integer value) {
    	if(value==null){
    		return getText("t_unknow");
    	}
		if(value==0){
			return getText("t_order_paying");
		} else if(value==1){
			return getText("t_order_payed");
		} else if(value==2){
			return getText("t_order_shipped");
		} else if(value==3){
			return getText("t_order_deleted");
		} else if(value==4){
			return getText("t_order_ended");
		} else if(value==5){
			return getText("t_order_back");
		} else {
			return getText("t_unknow");
		}
	}
    
    //用户权限
    public static final int ROLE_BUY = 1; //买家
    public static final int ROLE_SELL = 2; //卖家
    public static final int ROLE_ADMIN = 3; //管理员
    
    //错误号
    public static final String ERROR_NO = "errorNo";
    public static final String ERROR_INFO = "errorInfo";
    public static final int ERRORCODE_SUCCESS = 0; //成功
    public static final int ERRORCODE_FAILED = -1; //失败
    
    //广告类型
    public static final int AD_TYPE_LB = 0; //轮播图
    public static final int AD_TYPE_GGW = 1; //广告位
    public static String getADType(Integer value) {
    	if(value==null){
    		return getText("t_unknow");
    	}
		if(value==0){
			return getText("t_ad_lb");
		} else if(value==1){
			return getText("t_ad_ggw");
		} else {
			return getText("t_unknow");
		}
	}
    
    //产品状态
    public static final int PRODUCT_ON = 1; //上架
    public static final int PRODUCT_OFF = 2; //下架
    public static final int PRODUCT_WAIT = 0; //待审核
    public static String getProductStatus(Integer value) {
    	if(value==null){
    		return getText("t_unknow");
    	}
		if(value==1){
			return getText("t_product_on");
		} else if(value==2){
			return getText("t_product_off");
		} else {
			return getText("t_pending");
		}
	}
    
    //news 语言
    public static final int NEWS_ZH = 1; //中文
    public static final int NEWS_EN = 2; //英文
    public static String getNewsLang(Integer value) {
    	if(value==null){
    		return getText("t_unknow");
    	}
		if(value==0){
			return getText("t_zh");
		} else if(value==1){
			return getText("t_en");
		} else {
			return getText("t_unknow");
		}
	}
    
    //评论状态
    public static final int COMMENT_OFF = 0; //屏蔽
    public static final int COMMENT_ON = 1; //正常
    public static String getCommentStatus(Integer value) {
    	if(value==null){
    		return getText("t_unknow");
    	}
		if(value==0){
			return getText("t_off");
		} else if(value==1){
			return getText("t_on");
		} else {
			return getText("t_unknow");
		}
	}
    
    //举报消息状态
    public static final int INFORM_TODO = 0; //待处理
    public static final int INFORM_DONE = 1; //已处理
    public static String getInformStatus(Integer value) {
    	if(value==null){
    		return getText("t_undone");
    	}
		if(value==0){
			return getText("t_undone");
		} else if(value==1){
			return getText("t_done");
		} else {
			return getText("t_undone");
		}
	}
    
    //店家状态
    public static final int STORE_OFF = 0; //待审核
    public static final int STORE_ON = 1; //正常
    public static String getStoreStatus(Integer value) {
    	if(value==null){
    		return getText("t_pending");
    	}
		if(value==0){
			return getText("t_pending");
		} else if(value==1){
			return getText("t_on");
		} else {
			return getText("t_pending");
		}
	}
    
    //留言状态
    public static final int MSG_OFF = 0; //未读
    public static final int MSG_ON = 1; //已读
    public static String getMsgStatus(Integer value) {
    	if(value==null){
    		return getText("t_unreaded");
    	}
		if(value==0){
			return getText("t_unreaded");
		} else if(value==1){
			return getText("t_readed");
		} else {
			return getText("t_unreaded");
		}
	}
    
    //用户状态
    public static final int USER_OFF = 0; //屏蔽
    public static final int USER_ON = 1; //正常
    public static String getUserStatus(Integer value) {
    	if(value==null){
    		return getText("t_unknow");
    	}
		if(value==0){
			return getText("t_off");
		} else if(value==1){
			return getText("t_on");
		} else {
			return getText("t_unknow");
		}
	}
    
    //版块状态
    public static final int SECTION_OFF = 0; //关闭
    public static final int SECTION_ON = 1; //正常
    public static String getSectionStatus(Integer value) {
    	if(value==null){
    		return getText("t_off");
    	}
		if(value==0){
			return getText("t_off");
		} else if(value==1){
			return getText("t_on");
		} else {
			return getText("t_off");
		}
	}
    
    //话题状态
    public static final int TOPIC_OFF = 0; //关闭
    public static final int TOPIC_ON = 1; //正常
    public static String getTopicStatus(Integer value) {
    	if(value==null){
    		return getText("t_off");
    	}
		if(value==0){
			return getText("t_off");
		} else if(value==1){
			return getText("t_on");
		} else {
			return getText("t_off");
		}
	}
    
    //帖子类型
    public static final int THREAD_ZH = 1; //中文
    public static final int THREAD_EN = 0; //英文
    public static String getThreadType(Integer value) {
    	if(value==null){
    		return getText("t_unknow");
    	}
		if(value==0){
			return getText("t_en");
		} else if(value==1){
			return getText("t_zh");
		} else {
			return getText("t_unknow");
		}
	}
	public static String getThreadStatus(Integer value) {
		if(value==null){
    		return getText("t_unknow");
    	}
		if(value==0){
			return getText("t_off");
		} else if(value==1){
			return getText("t_on");
		} else {
			return getText("t_unknow");
		}
	}
}
