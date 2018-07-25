package com.qim.loan.util.setting;

import com.qim.loan.util.common.PropertiesUtil;


/**
 * 
     * 类名:CacheSetting
     * 描述:redis设置 
     * 创建者:冯子文
     * 创建时间: 2018年5月31日 上午10:09:54 
	 * 更新者:冯子文   
	 * 更新时间: 2018年5月31日 上午10:09:54
 */
public class CacheSetting {
	// 权限cache设置 0
	private static CacheBaseSetting authoritySetting;
	// 短信权限 1
	private static CacheBaseSetting smsSetting;
	// 随机码 2
	private static CacheBaseSetting randomSetting;
	// 用户权限 3
	private static CacheBaseSetting userSetting;
	// 动态码 4
	private static CacheBaseSetting dynamicSetting;
	
	static{
		Boolean flag=PropertiesUtil.setProperties("/config/CacheSetting.properties");
		setAuthoritySetting(PropertiesUtil.getDefaultInt(flag, "cache.authority.library", 5),PropertiesUtil.getDefaultInt(flag, "cache.authority.time",1440));
		setUserSetting(PropertiesUtil.getDefaultInt(flag, "cache.user.library",6),PropertiesUtil.getDefaultInt(flag, "cache.user.time",30));
		setSmsSetting(PropertiesUtil.getDefaultInt(flag, "cache.sms.library",7),PropertiesUtil.getDefaultInt(flag, "cache.sms.time",5));
		setRandomSetting(PropertiesUtil.getDefaultInt(flag, "cache.random.library",8),PropertiesUtil.getDefaultInt(flag, "cache.random.time",5));
		setDynamicSetting(PropertiesUtil.getDefaultInt(flag, "cache.dynamic.library",9),PropertiesUtil.getDefaultInt(flag, "cache.dynamic.time",30));
	}

	public static CacheBaseSetting getAuthoritySetting() {
		return authoritySetting;
	}
	public static void setAuthoritySetting(Integer library, Integer time) {
		if(library==null || library<0 || library>15)
			library=0;
		if(time==null)
			time=24*60;
		CacheSetting.authoritySetting = new CacheBaseSetting(library, time);
	}

	public static CacheBaseSetting getUserSetting() {
		return userSetting;
	}

	public static void setUserSetting(Integer library, Integer time) {
		if(library==null || library<0 || library>15)
			library=6;
		if(time==null || time<-1 || time==0)
			time=15;		
		CacheSetting.userSetting = new CacheBaseSetting(library, time);
	}

	public static CacheBaseSetting getSmsSetting() {
		return smsSetting;
	}

	public static void setSmsSetting(Integer library, Integer time) {
		if(library==null || library<0 || library>15)
			library=7;
		if(time==null || time<-1 || time==0)
			time=5;		
		CacheSetting.smsSetting = new CacheBaseSetting(library, time);
	}

	public static CacheBaseSetting getRandomSetting() {
		return randomSetting;
	}

	public static void setRandomSetting(Integer library, Integer time) {
		if(library==null || library<0 || library>15)
			library=8;
		if(time==null || time<-1 || time==0)
			time=5;			
		CacheSetting.randomSetting = new CacheBaseSetting(library, time);
	}

	public static CacheBaseSetting getDynamicSetting() {
		return dynamicSetting;
	}

	public static void setDynamicSetting(Integer library, Integer time) {
		if(library==null || library<0 || library>15)
			library=9;
		if(time==null || time<-1 || time==0)
			time=30;			
		CacheSetting.dynamicSetting = new CacheBaseSetting(library, time);
	}

	public static void main(String[] args) {

	}

}
