package com.ocean.proxy.api.base;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public abstract class AbstractAdSupplier implements BaseAdSupplier{
	public final Logger logger = LoggerFactory.getLogger(getClass());
	protected static final Map<String, String> mobiles = new HashMap<String, String>(5);
	static{
		mobiles.put("CMCC", "46000");
		mobiles.put("CUCC", "46001");
		mobiles.put("CTCC", "46003");
	}

	protected String encode(String param){
		
		try {
			return URLEncoder.encode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
}
