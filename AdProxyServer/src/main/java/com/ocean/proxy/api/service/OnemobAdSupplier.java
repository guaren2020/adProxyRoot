package com.ocean.proxy.api.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.JoinDSPEmu;
import com.ocean.persist.api.proxy.onemob.OnemobAdPullParams;
import com.ocean.persist.api.proxy.onemob.OnemobAdPuller;
import com.ocean.persist.api.proxy.onemob.OnemobAdResponse;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.api.base.ProxyConstants;
import com.ocean.proxy.api.helper.InvenoIdGenerator;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdSpaceType;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;

@Component(value="onemobAdSupplier")
public class OnemobAdSupplier extends AbstractAdSupplier{
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		OnemobAdPullParams params = parseParams(adreq, positionInfo);
		// 调用API
		OnemobAdPuller puller = OnemobAdPuller.getInstance(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.ONEMOB_URL));
		OnemobAdResponse response = (OnemobAdResponse)puller.api(params);
		// 解析结果
		return parseResult(response);
	}
	
	private AdRecomReply parseResult(OnemobAdResponse response)
			throws AdPullException {
		
		if(response == null){
			return null;
		}
		
		// 返回对象
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		
		AdContent content = new AdContent();
		AdMutiAction action = new AdMutiAction();
//		1表示落地页为普通页面(href)采用浏览器打开；
//		2表示落地页(href)为下载类app；
//		3表示落地页是下载类，但是需要从href字段解析获得，详情请看附录
		int hrefType = Integer.valueOf(response.getHrefType());
		int at = 2;
		if(hrefType == 1){
			at = 1;
		}
		action.setType(at);
		
		content.setMarketTitle(defTitle);
		content.setGuideTitle(defTitle);
		action.setGuideTitle(defTitle);
		
		// 只返回html的物料
		String html = response.getHtml();
		try {
			html = URLDecoder.decode(html, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			// 不做其它处理
		}
		content.setHtmlSnippet(html);
		content.setIsHtmlAd(true);
		List<AdImg> imgs = new ArrayList<AdImg>();
//		AdImg img = new AdImg();
		// 图片地址
		content.setImglist(imgs);
		action.setLinkurl(response.getHref());
		
		content.setMutiAction(Collections.singletonList(action));
		content.setAdId(InvenoIdGenerator.genThirdDspId(JoinDSPEmu.ONEMOB.getValue()));
		
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		// 点击监测
		List<String> click = response.getC_rpt();
		if(click == null){
			click = new ArrayList<String>();
		}
		map.put(CLICK, click);
		// 曝光监测
		List<String> show = response.getS_rpt();
		if(show == null){
			show = new ArrayList<String>();
		}
		map.put(SHOW, show);
		
//		SHOW, CLICK, OPEN, DOWNLOAD, INSTALL, ACTIVE
		List<String> download = response.getDc_rpt();
		if(download != null && !download.isEmpty()){
			map.put(DOWNLOAD, download);
		}
		List<String> install = response.getI_rpt();
		if(install != null && !install.isEmpty()){
			map.put(INSTALL, install);
		}
		List<String> active = response.getA_rpt();
		if(active != null && !active.isEmpty()){
			map.put(ACTIVE, active);
		}
		content.setThirdReportLinks(map);
		recomReply.setAd_content(content);
		
		return recomReply;
	}
	
	private OnemobAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		
		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		AdUserInfo userInfo = adreq.getUserinfo();
		Integer adType =AdSpaceType.BANNER==attri.getSpaceType()?1:AdSpaceType.OPENING==attri.getSpaceType()?2:-1;
		if(adType == -1){
			throw new AdPullException("目前不支持该类型广告");
		}
		OnemobAdPullParams params = new OnemobAdPullParams();
		params.setVer("1.11");
		params.setCp(positionInfo.getPos());
		String ad_size = attri.getSpaceWidth() + "x" + attri.getSpaceHeight();
		logger.info("onemob ad request,pose id:{},space width:{},spae height:{}",positionInfo.getPos(),attri.getSpaceWidth(),attri.getSpaceHeight());
		params.setAd_size(ad_size);
		params.setAd_type(adType);
		String adid = userInfo.getAdid();
		if(StringUtils.isEmpty(adid)){
			adid = userInfo.getImei();
		}
		params.setAid(adid);
		params.setApp_version("1.1");
		params.setBrnd(userInfo.getBrand_name());
		String imsi = userInfo.getImsi(); 
		if(StringUtils.isEmpty(adid)){
			imsi = userInfo.getImei();
		}
		params.setImsi(imsi);
		params.setMac(userInfo.getMac());
//		params.setDensity(density)
		// 设备类型，手机：1、平板：2
		params.setDevice_type(1);
		String dm = userInfo.getDevice_width() + "x" + userInfo.getDevice_height();
		params.setDm(dm);
		params.setHost_package_name("com.coolshow.app");
		params.setImei(userInfo.getImei());
//		params.setImsi(imsi)
		params.setIp(userInfo.getClient_ip());
		params.setLat(userInfo.getLat());
		params.setLng(userInfo.getLon());
		params.setNo(mobiles.get(userInfo.getMobile()));
		params.setUa(encode(userInfo.getUa()));
		params.setMdl(encode(userInfo.getPhonemodel()));
		params.setRel(userInfo.getOsversion());
		params.setOs(OS_ANDROID.equals(userInfo.getOs())?1:OS_IOS.equals(userInfo.getOs())?2:1);
		return params;
	}

}
