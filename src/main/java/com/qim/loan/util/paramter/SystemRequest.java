package com.qim.loan.util.paramter;

import java.io.Serializable;

import com.qim.loan.util.common.GsonUtil;



public class SystemRequest implements Serializable {

	private static final long serialVersionUID = -8903835907892136095L;

	private String ipAddress;

	private String osVersion;

	private String browserVersion;

	private String domainName;

	private String domain;
	
	private String channelId;
	

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getBrowserVersion() {
		return browserVersion;
	}

	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this);
	}
}
