package com.ocean.persist.api.proxy.onemob;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

public class OnemobAdPullParams extends AbstractBaseEntity implements AdPullParams {

	private static final long serialVersionUID = 1L;

	private String cp;// 必填	String	由OneMob分配
	private String ver;// 必填	String	本文档为ver=1.11
	private String app_version;// 必填	String	当前app版本
	private String ad_size;// 必填	String	广告位展示尺寸，如：800x600(x为小写字母)
	private Integer ad_type;// 必填	String	广告类型，目前只有banner和开屏 Banner时值填1，开屏时填2
	private String host_package_name;// 必填	String	当前广告展示宿主apk包名
	private String imei;// 必填	String	Android设备唯一标识码
	private String imsi;// 必填	String	用户sim卡imsi号
	private String aid;// 必填	String	Android手机设备系统ID
	private String mac;// 选填	string	设备wifi网卡MAC地址
	private Integer device_type;// 必填	int	设备类型，手机：1、平板：2
	private Integer os;// 必填	Int	操作系统，Android：1、iOS：2
	private String rel;// 必填	String	操作系统版本，如：2.3、5.0等
	private String brnd;// 必填	String	设备厂商名称，中文需要UTF-8编码
	private String mdl;// 必填	String	设备型号，中文需要UTF-8编码
	private String dm;// 必填	String	设备屏幕宽高，如：1080x1920(x为小写字母)
	private String density;// 选填	string	屏幕密度，一个逻辑像素等于几个实 际像素
	private String ip;// 必填	string	用户设备的公网ipv4地址，service to service接入必填
	private String ua;// 必填	String	用户客户端的user-Agent,
	// 必填	String 网络连接类型 无法探测网络类型：0、蜂窝数据2G网络：2g、蜂窝数据3G网络：
	// 3g、蜂窝数据4G网络：4g、蜂窝数据5G网络：5g、Wi-Fi网络：wifi
	private String nt;
	private String no;// 必填	String	手机运营商代号，MCC+MNC	如：46000 中国移动
	private String lat;// 选填	int	纬度，用于精确识别地域。
	private String lng;// 选填	int	经度，用于精确识别地域。

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getApp_version() {
		return app_version;
	}

	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}

	public String getAd_size() {
		return ad_size;
	}

	public void setAd_size(String ad_size) {
		this.ad_size = ad_size;
	}

	public Integer getAd_type() {
		return ad_type;
	}

	public void setAd_type(Integer ad_type) {
		this.ad_type = ad_type;
	}

	public String getHost_package_name() {
		return host_package_name;
	}

	public void setHost_package_name(String host_package_name) {
		this.host_package_name = host_package_name;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Integer getDevice_type() {
		return device_type;
	}

	public void setDevice_type(Integer device_type) {
		this.device_type = device_type;
	}

	public Integer getOs() {
		return os;
	}

	public void setOs(Integer os) {
		this.os = os;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getBrnd() {
		return brnd;
	}

	public void setBrnd(String brnd) {
		this.brnd = brnd;
	}

	public String getMdl() {
		return mdl;
	}

	public void setMdl(String mdl) {
		this.mdl = mdl;
	}

	public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

	public String getDensity() {
		return density;
	}

	public void setDensity(String density) {
		this.density = density;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public String getNt() {
		return nt;
	}

	public void setNt(String nt) {
		this.nt = nt;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
