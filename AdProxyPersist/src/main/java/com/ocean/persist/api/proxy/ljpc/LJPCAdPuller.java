package com.ocean.persist.api.proxy.ljpc;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.HttpClient;
import com.ocean.core.common.http.HttpInvokeException;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.AdPullParams;
import com.ocean.persist.api.proxy.AdPullResponse;
import com.ocean.persist.api.proxy.AdPuller;

public class LJPCAdPuller implements AdPuller {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private String url;
    private static LJPCAdPuller instance;  
    public static synchronized LJPCAdPuller getInstance(String apiUrl) {  
	    if (instance == null) {  
	        instance = new LJPCAdPuller(apiUrl);  
	    }  
	    return instance;  
    } 

    private LJPCAdPuller(String url){
		this.url = url;
	}
	public AdPullResponse api(AdPullParams pars) throws AdPullException{
		LJPCAdPullParams params = (LJPCAdPullParams) pars;
		StringBuilder sb = new StringBuilder();
		sb.append(url);
		sb.append("?").append("uid=").append(encode(params.getUid()));
		sb.append("&").append("ua=").append(encode(params.getUa()));
		sb.append("&").append("ip=").append(params.getIp());
		sb.append("&").append("r=").append(params.getR());
		sb.append("&").append("l=").append(params.getL());
		Integer v = params.getV();
		if(v != null){
			sb.append("&").append("v=").append(v);
		}
		String u = params.getU();
		if(!StringUtils.isEmpty(u)){
			sb.append("&").append("u=").append(encode(u));
		}
		String f = params.getF();
		if(!StringUtils.isEmpty(f)){
			sb.append("&").append("f=").append(encode(f));
		}
		Integer max = params.getMax();
		if(max != null){
			sb.append("&").append("max=").append(max);
		}
		Integer min = params.getMin();
		if(min != null){
			sb.append("&").append("min=").append(min);
		}
		logger.info("onemob request param:{}",sb.toString());
		String result;
		try {
			result = HttpClient.getInstance().get(sb.toString());
			return JsonUtils.toBean(result, LJPCAdResponse.class);
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
		
	private String encode(String param){
		if(StringUtils.isEmpty(param)){
			return null;
		}
		try {
			return URLEncoder.encode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	public boolean supports(AdPullParams pars) throws AdPullException {
		
		return LJPCAdPullParams.class
				.isAssignableFrom(pars.getClass());
	}
}
