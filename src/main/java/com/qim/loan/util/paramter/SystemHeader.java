package com.qim.loan.util.paramter;

import com.qim.loan.util.common.GsonUtil;

public class SystemHeader {

	// 引用（域名）
	private String referer;
	//Service:soufun-passport-web
	private String service;
	
	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Boolean equal(){
		if(this.referer.equalsIgnoreCase("www.youxinwallet.com") && this.service.equalsIgnoreCase("youxinwallet-passport-web"))
			return true; 
		else
			return false;
	}
	
	
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this);
	}

}
