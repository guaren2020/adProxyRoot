package com.ocean.proxy.api.helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.dao.proxy.ProxyCacheDao;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.persist.model.proxy.PositionTarget;
import com.ocean.persist.service.proxy.ProxyService;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月13日 
      @version 1.0 
 */
public class AdSupplierCfgLoader {
   private Logger logger=LoggerFactory.getLogger(AdSupplierCfgLoader.class);
   private AdSupplierCfgLoader(){}
   private static class Temp{
	   private static final AdSupplierCfgLoader loader=new AdSupplierCfgLoader();
   }
   public static AdSupplierCfgLoader buildLoader(){
	   return AdSupplierCfgLoader.Temp.loader;
   }
   public DSPPosition getPosition(String joinDSP, String zkSpaceId){
	   //get from cache
	   ProxyCacheDao redisDao=  (ProxyCacheDao)SystemContext.getServiceHandler().getService(ProxyCacheDao.class);
	   DSPPosition pose= redisDao.getPoseCache(joinDSP, zkSpaceId);
	   if(pose!=null){
		   
		   return pose;
	   }
	   logger.info("get position cache missed,joinDSP:{},zk space id:{}",joinDSP,zkSpaceId);
	   //get from db
	   ProxyService service =(ProxyService)SystemContext.getServiceHandler().getService(ProxyService.class);
	   pose= service.getPoseByZPoseId(zkSpaceId,joinDSP);
	   
	   //cache to redis
	   if(pose!=null){
		   try{
			   redisDao.cachePose(joinDSP,zkSpaceId, pose);
		   }catch(Exception e){
			   logger.error("try to catch position info error",e);
		   }
		  
	   }

	   return pose;
   }

   public PositionTarget getPositionTarget(String joinDSP,String dspPoseId){
	   //get from cache
/*	   ProxyCacheDao redisDao=  (ProxyCacheDao)SystemContext.getServiceHandler().getService(ProxyCacheDao.class);
	   PositionTarget target=redisDao.getPoseTargetCache(joinDSP, dspPoseId);
	   if(target!=null){
		   logger.info("get position target cache missed,joinDSP:{},dsp position id:{}",joinDSP,dspPoseId);
		   return target;
	   }*/
	   //get from db
	   ProxyService service =(ProxyService)SystemContext.getServiceHandler().getService(ProxyService.class);
	   PositionTarget target=service.getPositionTarget(dspPoseId);
	   
	   //cache to redis
	  /* 
	   if(pose!=null){
	      try{
	          redisDao.cachePoseTarget(joinDSP,dspPoseId, target);
	      		   }catch(Exception e){
			   logger.error("try to catch position target info error",e);
		   }
	    }
	      */
	   return target;
   }
}
