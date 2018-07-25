package com.qim.loan.util.cache.string.leaf;

import org.springframework.stereotype.Service;

import com.qim.loan.util.cache.classify.string.BaseStringCache;
import com.qim.loan.util.setting.CacheSetting;

@Service("smsCache")
public class SMSCache  extends BaseStringCache{
	
	@Override
	protected int getTime() {
		return CacheSetting.getSmsSetting().getTime();
	}

	@Override
	protected int getDB() {
		return CacheSetting.getSmsSetting().getLibrary();
	}
	
	
}
