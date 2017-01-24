package com.ocean.persist.api.proxy.shenmi;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.HttpClient;
import com.ocean.core.common.http.HttpInvokeException;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.AdPullParams;
import com.ocean.persist.api.proxy.AdPullResponse;
import com.ocean.persist.api.proxy.AdPuller;
public class ShenmiAdPuller implements AdPuller {

    private static ShenmiAdPuller instance;  
    public static synchronized ShenmiAdPuller getInstance(String apiUrl) {  
	    if (instance == null) {  
	        instance = new ShenmiAdPuller(apiUrl);  
	    }  
	    return instance;  
    } 
	private String apiUrl;
	private ShenmiAdPuller(String url){
		
		this.apiUrl = url;
	}
	
	public AdPullResponse api(AdPullParams params) throws AdPullException{
		
		ShenmiAdPullParams paras = (ShenmiAdPullParams) params;
		
		// md5(appid+key+lid+time)
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(paras.getAppid());
		stringBuilder.append(paras.getAppkey());
		stringBuilder.append(paras.getLid());
		stringBuilder.append(paras.getTime());
		String token = DigestUtils.md5Hex(stringBuilder.toString());
		paras.setToken(token);
		
		paras.setAppkey(null);
		// 请求参数
		String requestBody = JsonUtils.toJson(paras);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>(2);
		nvps.add(new BasicNameValuePair("data", requestBody)); 
//		System.out.println(requestBody);
		ShenmiAdResponse data;
		try {
			String result = HttpClient.getInstance().post(apiUrl, nvps);
//			System.out.println(result);
			data = JsonUtils.toBean(result, ShenmiAdResponse.class);
		} 
		catch (HttpInvokeException e) {
			throw new AdPullException(e.getCode(), e.getMessage());
		}
		if(data == null){
			throw new AdPullException("申米广告拉取失败,结果为空");
		}
		int code = data.getCode();
		// 没有广告
		if(code == ShenmiAdResponse.noad){
			return null;
		}
		// 不成功
		if(code != ShenmiAdResponse.success){
			System.out.println(params);
			throw new AdPullException(code, "申米广告拉取失败," + data.getMsg());
		}
		return data;	
	}
	
/*	public static void main(String[] args) throws AdPullException {
		
		ShenmiAdPullParams params = new ShenmiAdPullParams();
		params.setHeight(320);
		params.setWidth(640);
		params.setImei("86801402470743");
		params.setAppid("49E926A0E8F54665AC48E8A805819D4E");
		params.setAppname("yiqio");
		params.setVer("1.7.4");
		params.setAppversion("1.7.4");
		params.setModel("HUAWEI");
		params.setIp("183.16.192.223");
		params.setOsversion("6.0");
		params.setLanguage("zh-CN");
		// Android: 1, IOS: 2
		params.setImsi("46000");
		params.setNetwork("wifi");
		params.setOs(String.valueOf(1));
		params.setAndroidid("9774D56D682E549C");
		// 广告位
		params.setLid("3B8652DACFE04D039DD74E70FE978172");
		params.setApppackagename("com.zk.test");
		
		params.setMac("04:02:1F:2F:3F:7D");
		
		// 屏幕宽高
		params.setScreenheight(1920);
		params.setScreenwidth(1080);
		params.setTime(String.valueOf(System.currentTimeMillis()));
		params.setWifissid("");
		params.setUa("Mozilla/5.0(Linux;Android4.0.4;GT-I9220 Build/IMM76D)");
		params.setAppid("F984A2200C6342B7B7A1DAE3529BFEA6");
		params.setAppkey("F45D762BC3BA6C038A4CAEEF418282AB");
		//ShenmiAdPuller puller = new ShenmiAdPuller("http://api.taylormedia.com.cn/v10/getad");
		ShenmiAdPuller puller = new ShenmiAdPuller("http://api.snmi.cn/v10/getad");

		System.out.println(puller.api(params));
	}*/
	
	public boolean supports(AdPullParams params) throws AdPullException {
		
		return ShenmiAdPullParams.class.isAssignableFrom(params.getClass());
	}
}
