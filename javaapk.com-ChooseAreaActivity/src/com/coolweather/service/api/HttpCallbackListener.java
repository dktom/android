package com.coolweather.service.api;

/**
 * 网络请求结果接口监听
 * 
 * @author zhou.ni
 *
 */
public interface HttpCallbackListener {
	
	/**
	 *请求完成
	 * @param response
	 */
	void onFinish(String response);
	
	/**
	 * 请求失败
	 * @param e
	 */
	void onError(Exception e);
	
}
