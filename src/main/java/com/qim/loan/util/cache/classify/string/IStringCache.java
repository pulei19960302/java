package com.qim.loan.util.cache.classify.string;

import com.qim.loan.util.cache.classify.base.IRedisSource;


public interface IStringCache {
	
	IRedisSource set(String key, String value);

	String get(String key);
	
	IRedisSource remove(String key);
	
	Boolean exists(String key);
	
	Long increment(String key);
	
	Long decrement(String key);
	
	void removeAll();
	
}
