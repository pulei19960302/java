package com.qim.loan.util.cache.string.leaf;

import org.springframework.stereotype.Service;

import com.qim.loan.util.cache.classify.string.BaseStringCache;
import com.qim.loan.util.setting.CacheSetting;

/**
 * 
 * 类名:AuthCache 
 * 描述:权限验证Cache 
 * 创建者:冯子文 
 * 创建时间: 2016年5月17日 上午11:50:42 
 * 更新者:冯子文 更新时间:
 * 2016年5月17日 上午11:50:42
 */
@Service("authCache")
public class AuthCache  extends BaseStringCache{

	@Override
	protected int getTime() {//秒
		return CacheSetting.getAuthoritySetting().getTime();//(24*3600)
	}
	@Override
	protected int getDB() {//0号
		return CacheSetting.getAuthoritySetting().getLibrary();
	}
	
	
}
