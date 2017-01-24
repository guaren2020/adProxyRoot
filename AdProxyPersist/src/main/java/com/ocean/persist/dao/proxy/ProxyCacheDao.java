package com.ocean.persist.dao.proxy;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.common.PersistConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.persist.model.proxy.PositionTarget;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月16日 
      @version 1.0 
 */
@Repository
public class ProxyCacheDao {
	protected final Logger log = LoggerFactory.getLogger(ProxyCacheDao.class);
	public static final String CACHE_SEPRATE="::";
	@Autowired
	private StringRedisTemplate redisTemplate;
	private String getJoinPoseCacheKey(String joinDsp,String zkSpaceId){
		return PersistConstants.CACHE_POSITION_PREFIX+joinDsp+CACHE_SEPRATE+zkSpaceId;
	}
	private String getPositionTargetCacheKey(String joinDsp,String zkSpaceId){
		return PersistConstants.CACHE_POSITION_TARGET_PREFIX+joinDsp+CACHE_SEPRATE+zkSpaceId;
	}
	
	public void cachePose(HashMap<String, DSPPosition> posM){
		for(Map.Entry<String, DSPPosition> entry:posM.entrySet()){
			DSPPosition pose=entry.getValue();
			Map<String,String> map=new HashMap<String,String>();

			map.put("dsp", String.valueOf(pose.getDsp()));
			if(StringUtils.isEmpty(pose.getPos())){
				map.put("pos", pose.getPos());
			}
			map.put("posType", String.valueOf(pose.getPosType()));
			map.put("settlementType", String.valueOf(pose.getSettlementType()));
			map.put("settlementPrice", String.valueOf(pose.getSettlementPrice()));
			if(!StringUtils.isEmpty( pose.getText1())){
				map.put("text1", pose.getText1());
			}
			if(!StringUtils.isEmpty(pose.getText2())){
				map.put("text2", pose.getText2());
			}
			redisTemplate.opsForHash().putAll(getJoinPoseCacheKey(pose.getDsp(),entry.getKey()), map);
		}
	}
	public void cachePose(String jojinDSP,String zkSpaceId, DSPPosition pose){
			Map<String,String> map=new HashMap<String,String>();

			map.put("dsp", String.valueOf(pose.getDsp()));
			if(!StringUtils.isEmpty(pose.getPos())){
				map.put("pos", pose.getPos());
			}
			map.put("posType", String.valueOf(pose.getPosType()));
			map.put("settlementType", String.valueOf(pose.getSettlementType()));
			map.put("settlementPrice", String.valueOf(pose.getSettlementPrice()));
			if(!StringUtils.isEmpty( pose.getText1())){
				map.put("text1", pose.getText1());
			}
			if(!StringUtils.isEmpty(pose.getText2())){
				map.put("text2", pose.getText2());
			}
			String key=getJoinPoseCacheKey(jojinDSP,zkSpaceId);
			log.debug("cache key:{}",key);
			redisTemplate.opsForHash().putAll(key, map);
			redisTemplate.boundSetOps(key).expire(SystemContext.getDynamicPropertyHandler().getInt(PersistConstants.CACHE_EXPIRE, 600), TimeUnit.SECONDS);
			
	}
	public DSPPosition getPoseCache(String joinDSP,String zkSpaceId){
		String key=getJoinPoseCacheKey(joinDSP,zkSpaceId);
		HashMap<Object,Object> map= (HashMap)redisTemplate.opsForHash().entries(key);
		if(CollectionUtils.isEmpty(map)){
			log.debug("try to get position chache {},but return empty!",key);
			return null;
		}
		DSPPosition pose=new DSPPosition();
		pose.setDsp((String)map.get("dsp"));
		if(!StringUtils.isEmpty(map.get("pos"))){
			pose.setPos((String)map.get("pos"));
		}
		pose.setPosType(Short.parseShort((String)map.get("posType")));
		pose.setSettlementType(Integer.parseInt((String)map.get("settlementType")));
		pose.setSettlementPrice(Integer.parseInt((String)map.get("settlementPrice")));

		if(!StringUtils.isEmpty(map.get("text1"))){
			pose.setText1((String)map.get("text1"));
		}
		if(!StringUtils.isEmpty(map.get("text2"))){
			pose.setText2((String)map.get("text2"));
		}
		return pose;
		

	}
	public void cachePoseTarget(String joinDSP,String zkSpaceId, PositionTarget target){
		Map<String,String> map=new HashMap<String,String>();

		map.put("id", target.getId());
		map.put("dpid", target.getDpid());
		
		if(!StringUtils.isEmpty(target.getIp())){
			map.put("ip",target.getIp());
		}
		if(!StringUtils.isEmpty(target.getImei())){
			map.put("imei", target.getImei());
		}
		
		String key=getPositionTargetCacheKey(joinDSP,zkSpaceId);
		redisTemplate.opsForHash().putAll(key, map);
		redisTemplate.boundSetOps(key).expire(SystemContext.getDynamicPropertyHandler().getInt(PersistConstants.CACHE_EXPIRE, 600), TimeUnit.SECONDS);
		
   }
	public PositionTarget getPoseTargetCache(String joinDSP,String zkSpaceId){
		String key=getJoinPoseCacheKey(zkSpaceId,joinDSP);
		HashMap<Object,Object> map= (HashMap)redisTemplate.opsForHash().entries(key);
		if(CollectionUtils.isEmpty(map)){
			log.debug("try to get posistion target chache {},but return empty!",key);
			return null;
		}
		PositionTarget poseTarget=new PositionTarget();
		poseTarget.setId((String)map.get("id"));
		poseTarget.setDpid((String)map.get("dpid"));
		
		if(!StringUtils.isEmpty(map.get("ip"))){
			poseTarget.setIp((String)map.get("ip"));
		}
		if(!StringUtils.isEmpty(map.get("imei"))){
			poseTarget.setImei((String)map.get("imei"));
		}

		return poseTarget;
		

	}
	public StringRedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
}
