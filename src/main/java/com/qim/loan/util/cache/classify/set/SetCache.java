package com.qim.loan.util.cache.classify.set;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qim.loan.util.cache.classify.base.impl.RedisSourceImpl;
import com.qim.loan.util.common.MessageUtil;

import redis.clients.jedis.Jedis;
@Service("setCache")
public class SetCache extends RedisSourceImpl {

	private static Logger logger = LoggerFactory.getLogger(SetCache.class);
	
	public void set(int redisIndex,String key,String... values) {
		Jedis jedis = getJedis(redisIndex);
		boolean isBroken = false;
		try {
			jedis.sadd(key, values);
		} catch (Exception e) {
			isBroken = true;
			MessageUtil.error(logger, "String set", e);
			e.printStackTrace();
		} finally {
			release(jedis, isBroken);
		}
	}

	public void remove(int redisIndex,String key,String... values) {
		Jedis jedis =getJedis(redisIndex);
		boolean isBroken = false;
		try {
			if(jedis.exists(key))
				jedis.srem(key, values);
		}catch (Exception e) {
            isBroken = true;
            MessageUtil.error(logger, "String Remove", e);
        } finally {
            release(jedis, isBroken);
        }	
	}

	public void remove(int redisIndex,String key) {
		Jedis jedis =getJedis(redisIndex);
		boolean isBroken = false;
		try {
			if(jedis.exists(key))
				jedis.del(key);
		}catch (Exception e) {
            isBroken = true;
            MessageUtil.error(logger, "String Remove", e);
        } finally {
            release(jedis, isBroken);
        }	
	}	

	public Set<String> get(int redisIndex,String key) {
		Jedis jedis = getJedis(redisIndex);
		Set<String> result=new HashSet<String>();;
		boolean isBroken = false;
		try {
			if(jedis.exists(key)){
				result=jedis.smembers(key);
			}
		}catch (Exception e) {
            isBroken = true;
            MessageUtil.error(logger, "String get", e);
        } finally {
            release(jedis, isBroken);
        }
		return result;
	}

	public Boolean exist(int redisIndex,String key) {
		Jedis jedis = getJedis(redisIndex);
		Boolean flag=false;
		boolean isBroken = false;
		try {
			if(jedis.exists(key))
				flag=true;
		}catch (Exception e) {
            isBroken = true;
            MessageUtil.error(logger, "String exist", e);
        } finally {
            release(jedis, isBroken);
        }
		return flag;
	}

	public Boolean exist(int redisIndex,String key,String value) {
		Jedis jedis = getJedis(redisIndex);
		Boolean flag=false;
		boolean isBroken = false;
		try {
			if(jedis.sismember(key, value))
				flag=true;
		}catch (Exception e) {
            isBroken = true;
            MessageUtil.error(logger, "String exist", e);
        } finally {
            release(jedis, isBroken);
        }
		return flag;
	}	
	
	
	public Long size(int redisIndex,String key){
		Jedis jedis = getJedis(redisIndex);
		boolean isBroken = false;
		Long size=0L;
		try {
			if(jedis.exists(key))
				size=jedis.scard(key);
		}catch (Exception e) {
            isBroken = true;
            MessageUtil.error(logger, "String exist", e);
        } finally {
            release(jedis, isBroken);
        }
		return size;
	}
	
	
	
	
	
	public static void main(String[] args) {

	}

}
