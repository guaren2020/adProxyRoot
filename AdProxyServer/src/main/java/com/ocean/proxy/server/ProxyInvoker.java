package com.ocean.proxy.server;

import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ocean.bid.service.AdBidService;
import com.ocean.core.common.system.BusinessException;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.JoinDSPEmu;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.persist.model.proxy.PositionTarget;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.api.base.BaseAdSupplier;
import com.ocean.proxy.api.helper.AdSupplierCfgLoader;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;

public class ProxyInvoker extends AbstractProxyInvoker{
	public void ping() throws TException {
		
	}

	public AdRecomReply poll(String uid, AdRecomReq adreq) throws TException {
		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		String joinDSP=adreq.getUserAdSpaceAttri().getJoinDspName();
		logger.info("ad request,joinDSP:{},ad space id:{},ad req info:{}",joinDSP,attri.getAdSpaceId(),adreq.toString());
		long ts = System.currentTimeMillis();
		try {
			adreq.setOgin_name(uid);
			AdRecomReply recomReply = invoke(adreq);
			// 没有广告
			if(recomReply == null){
				return noad();
			}
		
			logger.info("ad reply,joinDSP:{},ad space id:{},ad reply info:{}",joinDSP,attri.getAdSpaceId(),recomReply.toString());
			return recomReply;
		}
		catch (AdPullException e) {
			logger.error("ad rquest error(AdPullException),joinDSP:{},ad space id:{},error Code:{},error Msg:{}" ,joinDSP,attri.getAdSpaceId(),e.getCode(), e.getMsg());
			AdRecomReply recomReply = new AdRecomReply();
			// 暂定 0表示没有广告
			recomReply.setStatus(ErrorCode.INTER_ERROR);
			return recomReply;
		}catch (BusinessException e) {
			logger.error("ad rquest error(BusinessException),joinDSP:{},ad space id:{},error Code:{},error Msg:{}" ,joinDSP,attri.getAdSpaceId(),e.getErrorCode(), e.getMsg());
			AdRecomReply recomReply = new AdRecomReply();
			// 暂定 0表示没有广告
			recomReply.setStatus(ErrorCode.INTER_ERROR);
			return recomReply;
		}finally{
			long ls = System.currentTimeMillis();
			logger.info("joinDSP:{},ad space id :{},request time cost:{} ms",joinDSP,attri.getAdSpaceId(),ls - ts);
		}
	}
	private AdRecomReply invoke(AdRecomReq adreq)throws AdPullException{

		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		AdSupplierCfgLoader cfgLoader=AdSupplierCfgLoader.buildLoader();
		DSPPosition dspPosition = cfgLoader.getPosition(String.valueOf(JoinDSPEmu.getJoinDspByName(attri.getJoinDspName()).getValue()),String.valueOf(attri.getAdSpaceId()));
		if(dspPosition == null){
			logger.error("no space mapping info,space id:{}", attri.getAdSpaceId());
			throw new AdPullException("no space mapping info,space id:" + attri.getAdSpaceId());
		}
/*		PositionTarget target = cfgLoader.getPositionTarget(attri.getJoinDspName(),dspPosition.getId());
		if(target != null){
			AdUserInfo userInfo = adreq.getUserinfo();
			String imei = target.getImei();
			// imei不合法
			if(!StringUtils.isEmpty(imei) && !imei.equals(userInfo.getImei())){
				String ip = target.getIp();
				// ip不合法
				if(!StringUtils.isEmpty(ip) && !ip.equals(userInfo.getClient_ip())){
					// 不是定向目标 返回
					return null;
				}
			}
		}*/
		// 设置默认参数
		AdUserInfo userInfo = adreq.getUserinfo();
		// 操作系统
		String os = userInfo.getOs();
		if(StringUtils.isEmpty(os)){
			os = AbstractAdSupplier.OS_ANDROID;
			userInfo.setOs(os);
		}
		// 操作系统版本
		if(StringUtils.isEmpty(userInfo.getOsversion())){
			userInfo.setOsversion(AbstractAdSupplier.PARAM_DEFAULT_ANDROID_VERSION);
		}
		// 设备宽高
		if(userInfo.getDevice_width() == 0){
			userInfo.setDevice_width(AbstractAdSupplier.PARAM_DEFAULT_DEVICE_WIDTH);
		}
		if(userInfo.getDevice_height() == 0){
			userInfo.setDevice_height(AbstractAdSupplier.PARAM_DEFAULT_DEVICE_HEIGHT);
		}
		// aaid
		if(StringUtils.isEmpty(userInfo.getAaid())){
			userInfo.setAaid(StringUtils.EMPTY);
		}
		// adid
		if(StringUtils.isEmpty(userInfo.getAdid())){
			userInfo.setAdid(userInfo.getImei());
		}
		// 厂商
		if(StringUtils.isEmpty(userInfo.getBrand_name())){
			if(AbstractAdSupplier.OS_IOS.equals(os)){
				userInfo.setBrand_name(AbstractAdSupplier.PARAM_DEFAULT_BRAND_APPLE);
			}
			else{
				userInfo.setBrand_name(AbstractAdSupplier.PARAM_DEFAULT_BRAND_ANDROID);
			}
		}
		// ip
		userInfo.setClient_ip(userInfo.getClient_ip().trim());
		// imsi
		if(StringUtils.isEmpty(userInfo.getMac())){
			userInfo.setMac(userInfo.getImei());
		}
		AbstractAdSupplier.InvokeAttribute attribute = new AbstractAdSupplier.InvokeAttribute(adreq, dspPosition);
		AdBidService adBidService=(AdBidService)SystemContext.getServiceHandler().getService(AdBidService.class);
		BaseAdSupplier  proxy=adBidService.getAdSupplier(adreq);
		// 处理结果
		AdRecomReply recomReply = proxy.invoke(attribute);
		if(recomReply == null){
			return null;
		}
		
		AdContent content = recomReply.getAd_content();
		content.setSettlementPrice(dspPosition.getSettlementPrice());
		content.setIdFromAdSrc(dspPosition.getPos());
		content.setPosition(defpos);
		content.setIsNeedSrcImg(true);
		content.setAdSource(AbstractAdSupplier.PARAM_DEFAULT_AD_SOURCE);
		content.setAdSrc(JoinDSPEmu.getJoinDspByName(attri.getJoinDspName()).getId2adr());
		return recomReply;
	}
	private AdRecomReply noad(){
		AdRecomReply recomReply = new AdRecomReply();
		// 暂定 0表示没有广告 
		recomReply.setStatus(ErrorCode.AD_EMPTY);
		return recomReply;
	}
	public int notice_click(String uid, long adid) throws TException {
		return 0;
	}

	public void prefetch(String uid, AdRecomReq adreq) throws TException {
		
	}

	public int notice(String uid, long adid) throws TException {
		// TODO Auto-generated method stub
		return 0;
	}
}
