/**
 * 
 */
package com.sqe.shop.common;

/**
 * @author Vito
 * @email zhouwentao16@gmail.com
 * @date 2013-7-18
 * 
 */
public class Constants {
	public static final String LOGIN_USER = "login_user";
	public static final String LOGIN_ADMIN = "login_admin";
	public static final String CART = "cart";
    public static final int IMG_WIDTH = 300;
    public static final int IMG_HEIGHT = 300;

    public static class OrderStatus {
        public static final Integer WAIT_PAY = 0;//代付款
        public static final Integer PAYED = 1;//已付款
        public static final Integer SHIPPED = 2;//已发货
        public static final Integer DELETED = 3;//已删除
        public static final Integer ENDED = 4;//已完成
    }
    
    //用户权限
    public static final int ROLE_BUY = 1; //买家
    public static final int ROLE_SELL = 2; //卖家
    
    //错误号
    public static final String ERROR_NO = "errorNo";
    public static final String ERROR_INFO = "errorInfo";
    public static final int ERRORCODE_SUCCESS = 0; //成功
    public static final int ERRORCODE_FAILED = -1; //失败
    
    //广告类型
    public static final int AD_TYPE_HDT = 0; //活动图
    public static final int AD_TYPE_GGW = 1; //广告位
    
    //产品状态
    public static final int PRODUCT_ON = 1; //上架
    public static final int PRODUCT_OFF = 2; //下架
    public static final int PRODUCT_WAIT = 0; //待审核
    public static String getProductStatus(Integer value) {
    	if(value==null){
    		return "未知";
    	}
		if(value==1){
			return "已上架";
		} else if(value==2){
			return "已下架";
		} else {
			return "待审核";
		}
	}
    
    //news 语言
    public static final int NEWS_ZH = 1; //中文
    public static final int NEWS_EN = 2; //英文
    public static String getNewsLang(Integer value) {
    	if(value==null){
    		return "未知";
    	}
		if(value==0){
			return "中文";
		} else if(value==1){
			return "英文";
		} else {
			return "未知";
		}
	}
    
    //评论状态
    public static final int COMMENT_OFF = 0; //屏蔽
    public static final int COMMENT_ON = 1; //显示
    public static String getCommentStatus(Integer value) {
    	if(value==null){
    		return "未知";
    	}
		if(value==0){
			return "已屏蔽";
		} else if(value==1){
			return "正常";
		} else {
			return "未知";
		}
	}
    
    //举报消息状态
    public static final int INFORM_TODO = 0; //待处理
    public static final int INFORM_DONE = 1; //已处理
    public static String getInformStatus(Integer value) {
    	if(value==null){
    		return "待处理";
    	}
		if(value==0){
			return "待处理";
		} else if(value==1){
			return "已处理";
		} else {
			return "待处理";
		}
	}
}
