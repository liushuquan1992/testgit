package com.lsq.wx.service;


import com.lsq.wx.vo.AuthToken;
public interface IndexService {
	
	/**
	 * 统一下单接口
	 * @param authToken 授权token
	 * @param remoteAddr 请求主机ip
	 * @return prepayId 预支付id
	 */
	String pay(AuthToken authToken, String remoteAddr);
	
	/**
	 * 申请退款接口
	 */
	String refund();

}
