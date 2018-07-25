package com.qim.loan.util.cache.classify.base.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qim.loan.util.cache.classify.base.IRedisSource;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;
/**
 * 
     * 类名:RedisDataSourceImpl
     * 描述:	 
     * 创建者:冯子文
     * 创建时间: 2016年5月17日 上午11:48:01 
	 * 更新者:冯子文   
	 * 更新时间: 2016年5月17日 上午11:48:01
 */
@Repository("redisSource")
public class RedisSourceImpl implements IRedisSource {

    private static final Logger log = LoggerFactory.getLogger(RedisSourceImpl.class);

    @Autowired
    private JedisPool jedisPool;
    
    @SuppressWarnings("deprecation")
    private Jedis getRedisClient() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        } catch (JedisException e) {
        	log.error("failed:jedisPool getResource.", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
            }
            throw e;
        }
        return jedis;
    }

    @SuppressWarnings("deprecation")
	private void returnResource(Jedis shardedJedis, boolean broken) {
        if (broken) {
        	jedisPool.returnBrokenResource(shardedJedis);
        } else {
        	jedisPool.returnResource(shardedJedis);
        }
    }
    
    
	public Jedis getJedis() {
		return getRedisClient();
	}

	public void disconnect() {
		Jedis shardedJedis = getJedis();
		if (shardedJedis != null)
			shardedJedis.disconnect();
	}
	
    protected void release(Jedis jedis, boolean isBroken) {
    	returnResource(jedis, isBroken);
    }
    
    public Jedis getJedis(int redisIndex){
		Jedis jedis = this.getJedis();
		jedis.select(redisIndex);
		return jedis;
    }
    
    
    
    
}
