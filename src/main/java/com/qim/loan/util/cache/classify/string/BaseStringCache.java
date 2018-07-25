package com.qim.loan.util.cache.classify.string;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.qim.loan.util.cache.classify.base.IRedisSource;


public abstract class BaseStringCache implements IStringCache  {

	@Autowired	
	@Qualifier("stringCache")
	private StringCache stringCache;

	protected abstract int getTime();
	
	protected abstract int getDB();
	
	public IRedisSource set(String key, String value) {
		stringCache.set(getDB(),key, value, getTime());
		return stringCache;
	}

	public String get(String key) {
		return stringCache.get(getDB(),key,getTime());
	}
	
	public IRedisSource remove(String key) {
		stringCache.remove(getDB(),key);
		return stringCache;
	}
	
	public Boolean exists(String key) {
		return stringCache.exist(getDB(),key);
	}

	public Long increment(String key) {
		return stringCache.increment(getDB(),key);
	}	

	public Long decrement(String key) {
		return stringCache.decrement(getDB(),key);
	}
	
	public void removeAll() {
		stringCache.removeAll(getDB());
	}
	
}
