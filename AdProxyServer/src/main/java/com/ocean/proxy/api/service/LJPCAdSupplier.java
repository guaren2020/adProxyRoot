package com.ocean.proxy.api.service;

import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.lingji.LingjiAdResponse;
import com.ocean.persist.api.proxy.ljpc.LJPCAdPullParams;
import com.ocean.persist.api.proxy.onemob.OnemobAdPuller;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.ProxyConstants;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.AdVid;



/**
 * 从灵集获取PC广告数据<p>
 * 灵集的请求参数<p>
 * <a href="http://docs.thextrader.cn/media/s2s_api.html">
 * http://docs.thextrader.cn/media/mobile_native_protocol.html
 * </a>
 * @author xy
 *
 */
public class LJPCAdSupplier extends LingjiAdSupplier{
	
	@Override
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		LJPCAdPullParams params = parseParams(adreq, positionInfo.getPos());
		logger.info("lingji pc ad request,pose id:{}",positionInfo.getPos());
		
		// 调用API获取
		LingjiAdResponse response = (LingjiAdResponse) OnemobAdPuller.getInstance(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.LINGJI_PC_URL)).api(params);
		AdRecomReply recomReply = parseResult(response);
		// 没有广告
		if(recomReply != null){
			// 广告物料信息
			AdVid vid = new AdVid();
			vid.setWidth(response.getAdw());
			vid.setHeight(response.getAdh());
			vid.setSrc(response.getSrc());
			AdContent content = recomReply.getAd_content();
			content.setSettlementPrice(
					positionInfo.getSettlementPrice());
			content.setAdVid(vid);
			content.setMarketTitle(defTitle);
			content.setGuideTitle(defTitle);
		}
		return recomReply;
	}
	
	private LJPCAdPullParams parseParams(AdRecomReq adreq, String posId){
		
		AdUserInfo userInfo = adreq.getUserinfo();
		
		LJPCAdPullParams params = new LJPCAdPullParams();
		params.setUa(userInfo.getUa());
		params.setIp(userInfo.getClient_ip());
		params.setR("1");
		params.setL(posId);
		params.setUid(adreq.getOgin_name());
		return params;
	}
}
