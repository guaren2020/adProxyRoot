package com.ocean.proxy.api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.JoinDSPEmu;
import com.ocean.persist.api.proxy.shenmi.ShenmiAdDetails;
import com.ocean.persist.api.proxy.shenmi.ShenmiAdEvent;
import com.ocean.persist.api.proxy.shenmi.ShenmiAdPullParams;
import com.ocean.persist.api.proxy.shenmi.ShenmiAdPuller;
import com.ocean.persist.api.proxy.shenmi.ShenmiAdReport;
import com.ocean.persist.api.proxy.shenmi.ShenmiAdResponse;
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
@Component(value="shenmiAdSupplier")
public class ShenmiAdSupplier  extends AbstractAdSupplier{
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		AdUserInfo userInfo = adreq.getUserinfo();
		// 申米的开屏，信息流返回json格式的广告 ，banner和插屏返回h5的
		AdSpaceType spaceType = AdSpaceType.findByValue(positionInfo.getPosType());
		if(spaceType != attri.getSpaceType()){
			throw new AdPullException("类型不匹配，广告位类型：" + spaceType);
		}
		
		ShenmiAdPullParams params = new ShenmiAdPullParams();
		params.setHeight(attri.getSpaceHeight());
		params.setWidth(attri.getSpaceWidth());
		params.setAppname(adreq.getApp());
		params.setVer(SystemContext.getStaticPropertyHandler().get(ProxyConstants.SHENMI_VERSION));
		params.setAppversion(adreq.getVersion());
		params.setModel(userInfo.getPhonemodel());
		params.setIp(userInfo.getClient_ip());
		params.setOsversion(userInfo.getOsversion());
		params.setLanguage("zh-CN");
		params.setAppid(positionInfo.getText1());
		params.setAppkey(positionInfo.getText2());
		if(userInfo.getOs().equalsIgnoreCase("ios")){
			params.setOs(String.valueOf(0));
			// 手机网络运营商 46000, 46002 中国移动 46001 中国联通 46003 中国电信 IOS请提供原始值
			params.setImsi(mobiles.get(userInfo.getMobile()));
		}
		else{
			params.setOs(String.valueOf(1));
			params.setImsi(userInfo.getImsi());
		}
		params.setImei(userInfo.getImei());
		// wifi、edge、gprs、cdma 等
		params.setNetwork("wifi");
		params.setAndroidid(userInfo.getAdid());
		// 广告位
		params.setLid(positionInfo.getPos());
		params.setApppackagename(pgn);
		params.setMac(userInfo.getMac());
		logger.info("onemob ad request,pose:{},space width:{},spae height:{}",positionInfo.getPos(),attri.getSpaceWidth(),attri.getSpaceHeight());
		
		// 屏幕宽高
		params.setScreenheight(userInfo.getDevice_height());
		params.setScreenwidth(userInfo.getDevice_width());
		params.setTime(String.valueOf(System.currentTimeMillis()));
		params.setWifissid("");
		params.setUa(userInfo.getUa());
		ShenmiAdResponse response = (ShenmiAdResponse)ShenmiAdPuller.getInstance(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.SHENMI_URL)).api(params);
		
		boolean isH5 = spaceType == AdSpaceType.BANNER || spaceType == AdSpaceType.INTERSTITIAL;
		
		return parseResult(response, isH5);
	}
	
	private AdRecomReply parseResult(ShenmiAdResponse response, boolean isH5)
			throws AdPullException {
		
		if(response == null){
			return null;
		}
		int code = response.getCode();
		if(code != ErrorCode.SUCCEED){
			throw new AdPullException("返回结果有误code：" + code);
		}
		
		List<ShenmiAdDetails> ads = response.getAds();
		if(ads == null || ads.isEmpty()){
			return null;
		}
		ShenmiAdDetails ad = ads.get(0);
		
		String title = ad.getTitle();
		
		// 返回对象
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		AdContent content = new AdContent();
		AdMutiAction action = new AdMutiAction();
		// 不是html素材
		int actionType = ACTION_WEB;
		if(!isH5){
			content.setContent(ad.getDesc());
			content.setCpIcon(ad.getIcon());
			action.setLinkurl(ad.getLink());
			action.setCpIcon(ad.getIcon());
			// 广告图片
			List<AdImg> imgs = new ArrayList<AdImg>();
			AdImg img = new AdImg();
			img.setSrc(ad.getSrc());
			imgs.add(img);
			content.setImglist(imgs);
			
			// 曝光 点击
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			// 点击监测
			List<String> click = ad.getClickreport();
			if(click == null){
				click = new ArrayList<String>();
			}
			map.put(CLICK, click);
			// 曝光监测
			List<ShenmiAdReport> reports = ad.getDisplayreport();
			List<String> urls = new ArrayList<String>();
			if(reports != null && !reports.isEmpty()){
				
				for (ShenmiAdReport report : reports) {
					urls.add(report.getReporturl());
				}
			}
			// 当广告中的action为2时，即表明是下载类广告
			if(ad.getAction() == 2){
				actionType = ACTION_APP;
				List<ShenmiAdEvent> events = ad.getTrackingevents();
				if(events != null && !events.isEmpty()){
					// 处理下载安装监控
					for(ShenmiAdEvent event : events){
						String e = event.getEventtype();
						if(ShenmiAdEvent.DOWNLOAD.equals(e)){
							map.put(DOWNLOAD, event.getTracking());
						}
						else if(ShenmiAdEvent.INSTALL.equals(e)){
							map.put(INSTALL, event.getTracking());
						}
					}
				}
			}
			map.put(SHOW, urls);
			content.setThirdReportLinks(map);
		}
		// html素材
		else{
			content.setHtmlSnippet(ad.getPage());
			content.setIsHtmlAd(true);
		}
		if(StringUtils.isEmpty(title)){
			title = defTitle;
		}
		// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
		action.setType(actionType);
		content.setMarketTitle(title);
		content.setGuideTitle(title);
		action.setGuideTitle(title);
		content.setMutiAction(Collections.singletonList(action));
		content.setAdId(InvenoIdGenerator.genThirdDspId(JoinDSPEmu.SHENMI.getValue()));
		recomReply.setAd_content(content);
		return recomReply;
	}

}
