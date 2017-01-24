package com.ocean.proxy.api.base;

import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.JoinDSPEmu;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;

public interface BaseAdSupplier {
	
	static final int status = 666;
	
	/** 安卓操作系统*/
	static  final String OS_ANDROID = "android";
	
	/** ios操作系统*/
	static  final String OS_IOS = "iOS";
	
	/** pc*/
	static  final String OS_PC = "pc";
	
	/** 点击监测链接 key*/
	static final String CLICK = "CLICK";
	
	/** 曝光监测链接 key*/
	static final String SHOW = "SHOW";
	
	/** 打开监测链接 key*/
	static final String OPEN = "OPEN";
	
	/** 下载监测链接 key*/
	static final String DOWNLOAD = "DOWNLOAD";
	
	/** 安装监测链接 key*/
	static final String INSTALL = "INSTALL";
	
	/** 激活监测链接 key*/
	static final String ACTIVE = "ACTIVE";
	
	/** 默认广告标题*/
	static final String defTitle = "广告";
	
	/** 默认的mac*/
	static final String defMac = "04:02:1F:2F:3F:7D";
	
	/** 默认的 Androidid*/
	static final String defAndroidId = "9774D56D682E549C";
	
	/** 默认的 Android Advertiser ID*/
	static final String defAaid = "38400000-8cf0-11bd-b23e-10b96e40000d";
	
	/** 默认的 idfa*/
	static final String defIdfa = "EC8AU25A-112B-41A3-A441-09FF1E786101";
	
	/** 默认的应用包名*/
	static final String pgn = "com.alphagolauncher.launcher";
	
	/** 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广 7表示视频*/
	static final int ACTION_WEB = 1;
	
	static final int ACTION_APP = 2;
	
	/** 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广 7表示视频*/
	static final int ACTION_VIDEO = 7;
	
	/** 运营商 移动*/
	static final String MOBILE_CMCC = "CMCC";
	/** 运营商 联通*/
	static final String MOBILE_CUCC = "CUCC";
	/** 运营商 电信*/
	static final String MOBILE_CTCC = "CTCC";
	
	/** 联网类型 2G*/
	static final String NETWORK_2G = "2g";
	/** 联网类型 3G*/
	static final String NETWORK_3G = "3g";
	/** 联网类型 4G*/
	static final String NETWORK_4G = "4g";
	/** 联网类型 wifi*/
	static final String NETWORK_WIFI = "wifi";
	
	//欠缺参数设置默认值
	String PARAM_DEFAULT_ANDROID_VERSION="7.1";
	String PARAM_DEFAULT_BRAND_APPLE="Apple";
	String PARAM_DEFAULT_BRAND_ANDROID="Android";
	String PARAM_DEFAULT_AD_SOURCE="广告";
	int PARAM_DEFAULT_DEVICE_WIDTH=1080;
	int PARAM_DEFAULT_DEVICE_HEIGHT=1920;
	
	
	AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException;
	
	/*JoinDSPEmu getDSP();*/
	
	static class InvokeAttribute{
		private AdRecomReq adreq;
		
		private DSPPosition dspPosition;

		public InvokeAttribute(AdRecomReq adreq, DSPPosition dspPosition){
			this.adreq = adreq;
			this.dspPosition = dspPosition;
		}
		
		public AdRecomReq getAdreq() {
			return adreq;
		}

		public void setAdreq(AdRecomReq adreq) {
			this.adreq = adreq;
		}

		public DSPPosition getDspPosition() {
			return dspPosition;
		}

		public void setDspPosition(DSPPosition dspPosition) {
			this.dspPosition = dspPosition;
		}
	}
}
