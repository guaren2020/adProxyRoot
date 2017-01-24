package com.ocean.persist.api.proxy.onemob;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;
public class OnemobAdResponse extends AbstractBaseEntity implements AdPullResponse{

	private static final long serialVersionUID = 1L;

	private Integer code;// Int	返回状态码，处理正常：200 处理异常：404   参数缺失：500
	private String message;// 请求状态说明，
	// 是	String	1表示落地页为普通页面(href)采用浏览器打开；
	// 2表示落地页(href)为下载类app；
	// 3表示落地页是下载类，但是需要从href字段解析获得，详情请看附录
	private String hrefType;
	private String html;// string	渲染好的广告html代码，采用UrlEncode编码，urldecode后可以直接在webview上展示
	private String href;// String	若此字段不为空，则采用此字段作为广告点击后落地页
	private String rtp;// Int	是否，1表示需要上报用户点击坐标，0表示不需要。详情见附录A
	private List<String> s_rpt;// Array	展示上报
	private List<String> c_rpt;// array	点击上报
	private List<String> d_rpt;// array	开始下载上报，hrefType=2或3的时候才需要
	private List<String> dc_rpt;// Array	下载完成上报，hrefType=2或3的时候才需要
	private List<String> i_rpt;// array	安装完成上报，hrefType=2或3的时候才需要
	private List<String> a_rpt;// array	打开激活上报，hrefType=2或3的时候才需要

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getHrefType() {
		return hrefType;
	}

	public void setHrefType(String hrefType) {
		this.hrefType = hrefType;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getRtp() {
		return rtp;
	}

	public void setRtp(String rtp) {
		this.rtp = rtp;
	}

	public List<String> getS_rpt() {
		return s_rpt;
	}

	public void setS_rpt(List<String> s_rpt) {
		this.s_rpt = s_rpt;
	}

	public List<String> getC_rpt() {
		return c_rpt;
	}

	public void setC_rpt(List<String> c_rpt) {
		this.c_rpt = c_rpt;
	}

	public List<String> getD_rpt() {
		return d_rpt;
	}

	public void setD_rpt(List<String> d_rpt) {
		this.d_rpt = d_rpt;
	}

	public List<String> getDc_rpt() {
		return dc_rpt;
	}

	public void setDc_rpt(List<String> dc_rpt) {
		this.dc_rpt = dc_rpt;
	}

	public List<String> getI_rpt() {
		return i_rpt;
	}

	public void setI_rpt(List<String> i_rpt) {
		this.i_rpt = i_rpt;
	}

	public List<String> getA_rpt() {
		return a_rpt;
	}

	public void setA_rpt(List<String> a_rpt) {
		this.a_rpt = a_rpt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
