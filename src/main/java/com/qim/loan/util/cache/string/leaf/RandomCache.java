package com.qim.loan.util.cache.string.leaf;

import org.springframework.stereotype.Service;

import com.qim.loan.util.cache.classify.string.BaseStringCache;
import com.qim.loan.util.setting.CacheSetting;

/**
 * 
 * 类名:RandomCache 
 * 描述:随机码cache 
 * 创建者:冯子文 
 * 创建时间: 2016年5月17日 上午11:50:42 
 * 更新者:冯子文 更新时间:
 * 2016年5月17日 上午11:50:42
 */
@Service("randomCache")
public class RandomCache  extends BaseStringCache{

	@Override
	protected int getTime() {
		return CacheSetting.getRandomSetting().getTime();
	}
	@Override
	protected int getDB() {
		return CacheSetting.getRandomSetting().getLibrary();
	}
	
	
}
