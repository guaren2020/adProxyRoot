package com.ocean.persist.api.proxy;

public interface AdPuller {

	/**
	 * 通用请求接口
	 * @param req
	 * @return
	 */
	AdPullResponse api(AdPullParams params) throws AdPullException;
	
	boolean supports(AdPullParams params) throws AdPullException;
}
