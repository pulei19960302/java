package com.qim.loan.util.cache.classify.base;

import redis.clients.jedis.Jedis;
/**
 * 
     * 类名:RedisDataSource
     * 描述:	 
     * 创建者:冯子文
     * 创建时间: 2016年5月17日 上午11:47:54 
	 * 更新者:冯子文   
	 * 更新时间: 2016年5月17日 上午11:47:54
 */
public interface IRedisSource {
    
	public Jedis getJedis();

	public void disconnect();
    
    public Jedis getJedis(int redisIndex);
       
}
