package com.qim.loan.util.cache.string.leaf;

import org.springframework.stereotype.Service;

import com.qim.loan.util.cache.classify.string.BaseStringCache;
import com.qim.loan.util.setting.CacheSetting;
/**
 * 
     * 类名:UserCache
     * 描述:客户cache	 
     * 创建者:冯子文
     * 创建时间: 2016年5月17日 上午11:49:58 
	 * 更新者:冯子文   
	 * 更新时间: 2016年5月17日 上午11:49:58
 */
@Service("userCache")
public class UserCache  extends BaseStringCache{
	
	@Override
	protected int getTime() {
		CacheSetting.setUserSetting(null,null);
		return CacheSetting.getUserSetting().getTime();
	}

	@Override
	protected int getDB() {
		CacheSetting.setUserSetting(null,null);
		return CacheSetting.getUserSetting().getLibrary();
	}
	
}
