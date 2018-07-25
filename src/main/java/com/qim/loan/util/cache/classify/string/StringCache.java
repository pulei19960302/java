package com.qim.loan.util.cache.classify.string;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qim.loan.util.cache.classify.base.impl.RedisSourceImpl;
import com.qim.loan.util.common.MessageUtil;

import redis.clients.jedis.Jedis;

@Service("stringCache")
public class StringCache extends RedisSourceImpl {

	private static Logger logger = LoggerFactory.getLogger(StringCache.class);
	
	public void set(int redisIndex,String key,String value,int time) {
		Jedis jedis = getJedis(redisIndex);
		boolean isBroken = false;
		try {
            if (jedis.exists(key))
                jedis.del(key);
			if (time>0)
				jedis.setex(key, time,value);
			else
				jedis.set(key, value);
		} catch (Exception e) {
			isBroken = true;
			MessageUtil.error(logger, "String set", e);
			e.printStackTrace();
		} finally {
			release(jedis, isBroken);
		}
	}
	
	public Long increment(int redisIndex,String key) {
		Jedis jedis = getJedis(redisIndex);
		Long count=0L;
		boolean isBroken = false;
		try {
			count=jedis.incr(key);
		} catch (Exception e) {
			isBroken = true;
			MessageUtil.error(logger, "String set", e);
			e.printStackTrace();
		} finally {
			release(jedis, isBroken);
		}
		return count;
	}
	
	public Long decrement(int redisIndex,String key) {
		Jedis jedis = getJedis(redisIndex);
		boolean isBroken = false;
		Long count=0L;
		try {
			count=jedis.decr(key);
		} catch (Exception e) {
			isBroken = true;
			MessageUtil.error(logger, "String set", e);
			e.printStackTrace();
		} finally {
			release(jedis, isBroken);
		}
		return count;
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


	public String get(int redisIndex,String key,int time) {
		Jedis jedis = getJedis(redisIndex);
		String result="";
		boolean isBroken = false;
		try {
			if(jedis.exists(key)){
				result=jedis.get(key);
				if(time>0)
					jedis.expire(key,time);
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
	
	public void removeAll(int redisIndex) {
		Jedis jedis = getJedis(redisIndex);
		jedis.flushDB();		
	}

}
