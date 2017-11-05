package com.lsq.wx.config;

public class StaticVar {
	/**
	 * 公众号AppId
	 */
	public static final String APP_ID = "wxadef2ece2d983993";

	/**
	 * 公众号AppSecret
	 */
	public static final String APP_SECRET = "f345d5ce2c9739e52413787a8c61a612";
	/**
	 * 微信支付商户号
	 */
	public static final String MCH_ID = "1441608002";
	/**
	 * 微信支付通知url
	 */
	public static final String NOTIFY_URL = "http://wxpay.pes-soft.com/wxpay/";
	/**
	 * 微信交易类型:公众号支付
	 */
	public static final String TRADE_TYPE_JSAPI = "JSAPI";
	public static final String WEB = "WEB";
	/**
	 * 返回成功字符串
	 */
	public static final String RETURN_SUCCESS = "SUCCESS";
	/**
	 * 微信统一下单url
	 */
	public static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	/**
	 * 微信申请退款url
	 */
	public static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";

	/**
	 * 支付地址(包涵回调地址)
	 */
	public static final String PAY_URL="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxadef2ece2d983993&redirect_uri=http%3a%2f%2fwxpay.pes-soft.com%2fwxpay%2fpay&response_type=code&scope=snsapi_base#wechat_redirect";
	
	/**
	 * 微信支付API秘钥
	 */
	public static final String KEY = "QBmgXTJP7QDTudt42qN4Jy2zuc2BvfYh";
	/**
	 * 证书位置
	 */
	public static final String CERT_PATH = "H:/Ws/pes-wxpay/src/main/webapp/cert/apiclient_cert.p12";

	/**
	 * 通过code获取授权access_token的URL
	 */
	public static String Authtoken_URL(String code) {
		StringBuffer url = new StringBuffer();
		url.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=");
		url.append(StaticVar.APP_ID);
		url.append("&secret=");
		url.append(StaticVar.APP_SECRET);
		url.append("&code=");
		url.append(code);
		url.append("&grant_type=authorization_code");
		return url.toString();
	}
}
