package com.ocean.persist.api.proxy.ryan;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ocean.core.common.http.HttpClient;
import com.ocean.core.common.http.HttpInvokeException;
import com.ocean.core.common.JsonUtils;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.AdPullParams;
import com.ocean.persist.api.proxy.AdPullResponse;
import com.ocean.persist.api.proxy.AdPuller;
import com.ocean.persist.api.proxy.shenmi.ShenmiAdPuller;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月4日 
      @version 1.0 
 */

public class RyanAdPuller  implements AdPuller,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2301440418226867310L;
	private final Logger logger = LoggerFactory.getLogger(getClass());
    private static RyanAdPuller instance;  
    public static synchronized RyanAdPuller getInstance(String apiUrl) {  
	    if (instance == null) {  
	        instance = new RyanAdPuller(apiUrl);  
	    }  
	    return instance;  
    } 

	private String url;
	
	private RyanAdPuller(String url){
		this.url = url;
	}
	public AdPullResponse api(AdPullParams params) throws AdPullException {
		// TODO Auto-generated method stub
		RyanAdPullParams paras = (RyanAdPullParams)params;
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		headers.put("x-openrtb-version", "2.3.2");//openRtb协议
		String requestBody = JsonUtils.toJson(paras);
		requestBody.replace("_native", "native");
		logger.info("bid id:{},ryan request info:{}",paras.getId(),requestBody);
		RyanAdPullResponse data;
		try {
			String result =  HttpClient.getInstance().post(url, requestBody, headers);
			logger.info("bid id:{},ryan result:",paras.getId(),result);
			data = JsonUtils.toBean(result, RyanAdPullResponse.class);
		} 
		catch (HttpInvokeException e) {
			// Ryan竞价失败，返回204表示没有广告
			if(e.getCode() == 204){
				logger.error("ad pull api return empty!", e);
				//System.out.println(requestBody);
				return null;
			}
			logger.error("ad pull api error!", e);
			throw new AdPullException(e.getCode(), e.getMessage());
			
		}catch(Exception e){
			logger.error("ad pull api error!", e);
			throw new AdPullException(e.getMessage());
		}
		return data;
	}

	public boolean supports(AdPullParams params) throws AdPullException {
		// TODO Auto-generated method stub
		return RyanAdPullParams.class.isAssignableFrom(params.getClass());
	}

}
