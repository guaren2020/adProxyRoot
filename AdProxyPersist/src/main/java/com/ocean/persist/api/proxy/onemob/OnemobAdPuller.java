package com.ocean.persist.api.proxy.onemob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.Bean2Utils;
import com.ocean.core.common.http.HttpClient;
import com.ocean.core.common.http.HttpInvokeException;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.AdPullParams;
import com.ocean.persist.api.proxy.AdPullResponse;
import com.ocean.persist.api.proxy.AdPuller;
public class OnemobAdPuller implements AdPuller {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private String apiUrl;
    private static OnemobAdPuller instance;  
    public static synchronized OnemobAdPuller getInstance(String apiUrl) {  
	    if (instance == null) {  
	        instance = new OnemobAdPuller(apiUrl);  
	    }  
	    return instance;  
    } 

    private OnemobAdPuller(String url){
	
		this.apiUrl = url;
	}
	
	public AdPullResponse api(AdPullParams params) throws AdPullException {
		
		StringBuilder url = new StringBuilder();
		url.append(apiUrl);
		url.append("?").append(Bean2Utils.toHttpParams(params));
		logger.info("onemob request param:{}",url.toString());
		OnemobAdResponse data;
		try {
			String result = HttpClient.getInstance().get(url.toString());
			if("None".equals(result)){
				return null;
			}
			data = JsonUtils.toBean(result, OnemobAdResponse.class);
		} 
		catch (HttpInvokeException e) {
			throw new AdPullException(e.getCode(),"HttpInvokeException,"+e.getMessage());
		}
		if(data == null){
			throw new AdPullException("onemob广告拉取失败,返回为空！");
		}
		int code = data.getCode();
		if(code != 200){
			throw new AdPullException("onemob广告拉取失败,返回状态码：" + code);
		}
		return data;
	}

	public boolean supports(AdPullParams params) throws AdPullException {
		return OnemobAdPullParams.class
				.isAssignableFrom(params.getClass());
	}
}
