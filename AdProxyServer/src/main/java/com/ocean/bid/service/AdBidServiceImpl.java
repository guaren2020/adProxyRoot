package com.ocean.bid.service;

import org.springframework.stereotype.Component;

import com.ocean.core.common.system.BusinessException;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.api.proxy.JoinDSPEmu;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.api.service.LingjiAdSupplier;
import com.ocean.proxy.api.service.OnemobAdSupplier;
import com.ocean.proxy.api.service.RyanAdSupplier;
import com.ocean.proxy.api.service.ShenmiAdSupplier;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月13日 
      @version 1.0 
 */
@Component(value="adBidService")
public class AdBidServiceImpl implements AdBidService{

	public JoinDSPEmu bid(AdRecomReq adreq) {
		// TODO Auto-generated method stub
		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		String joinDspName=attri.getJoinDspName();
		JoinDSPEmu joinDs=JoinDSPEmu.getJoinDspByName(joinDspName);
		
		/*
		 * bid business code
		
		*/
        return joinDs;

	}
	public AbstractAdSupplier getAdSupplier(AdRecomReq adreq) {
		JoinDSPEmu joinDs=bid(adreq);
		switch (joinDs) {
			case RYAN:
			    return (RyanAdSupplier)SystemContext.getServiceHandler().getService(RyanAdSupplier.class);
			case SHENMI:
				return (ShenmiAdSupplier)SystemContext.getServiceHandler().getService(ShenmiAdSupplier.class);
			case ONEMOB:
				return (OnemobAdSupplier)SystemContext.getServiceHandler().getService(OnemobAdSupplier.class);
			case LINGJI:
				return (LingjiAdSupplier)SystemContext.getServiceHandler().getService(LingjiAdSupplier.class);
			default:
			    throw new BusinessException(ErrorCode.INTER_ERROR,"do not find mapping server:"+joinDs.getAbbrev());
		}
	}

}
