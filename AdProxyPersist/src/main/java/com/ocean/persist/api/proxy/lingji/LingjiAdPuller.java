package com.ocean.persist.api.proxy.lingji;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.HttpClient;
import com.ocean.core.common.http.HttpInvokeException;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.AdPullParams;
import com.ocean.persist.api.proxy.AdPullResponse;
import com.ocean.persist.api.proxy.AdPuller;
import com.ocean.persist.api.proxy.onemob.OnemobAdPuller;

public class LingjiAdPuller implements AdPuller {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private String url;
    private static LingjiAdPuller instance;  
    public static synchronized LingjiAdPuller getInstance(String apiUrl) {  
	    if (instance == null) {  
	        instance = new LingjiAdPuller(apiUrl);  
	    }  
	    return instance;  
    } 

    private LingjiAdPuller(String url){
		this.url = url;
	}
	
	public AdPullResponse api(AdPullParams params) throws AdPullException{
		String requestBody = JsonUtils.toJson(params);
		logger.info("lingji request param:{}",requestBody);
		String result;
		try {
			result = HttpClient.getInstance().post(url, requestBody,
					Collections.singletonMap("Content-Type", "application/json"));
			return JsonUtils.toBean(result, LingjiAdResponse.class);
		} 
		catch (HttpInvokeException e) {
			// 灵集返回204表示没有广告
			if(e.getCode() == 204){
				logger.info("lingji ad return empty,return code:204");
				return null;
			}
			throw new AdPullException(e.getCode(), e.getMessage());
		}
	}
	public boolean supports(AdPullParams params) throws AdPullException {
		
		return LingjiAdPullParams.class
				.isAssignableFrom(params.getClass());
	}
}
