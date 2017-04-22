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
    public static String getProductStatus(int status) {
		if(status==1){
			return "已上架";
		} else if(status==2){
			return "已下架";
		} else {
			return "待审核";
		}
	}
}
