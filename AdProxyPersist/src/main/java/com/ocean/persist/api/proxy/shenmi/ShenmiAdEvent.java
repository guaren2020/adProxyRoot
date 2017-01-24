package com.ocean.persist.api.proxy.shenmi;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;


public class ShenmiAdEvent extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;

	public static final String DOWNLOAD = "download";
	
	public static final String INSTALL = "install";
	
	private String eventtype;
	
	private List<String> tracking;
	
	public String getEventtype() {
		return eventtype;
	}

	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}

	public List<String> getTracking() {
		return tracking;
	}

	public void setTracking(List<String> tracking) {
		this.tracking = tracking;
	}

	public static String getDownload() {
		return DOWNLOAD;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
