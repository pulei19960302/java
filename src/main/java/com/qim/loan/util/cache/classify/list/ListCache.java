package com.qim.loan.util.cache.classify.list;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qim.loan.util.cache.classify.base.impl.RedisSourceImpl;
import com.qim.loan.util.common.MessageUtil;

import redis.clients.jedis.Jedis;

@Service("listCache")
public class ListCache extends RedisSourceImpl {

	private static Logger logger = LoggerFactory.getLogger(ListCache.class);
	
	public Long set(int redisIndex,String key,String ... values) {
        Jedis jedis = getJedis(redisIndex);
        boolean isBroken = false;
        Long index=0L;
        try {
        	index=jedis.lpush(key, values);
        } catch (Exception e) {
        	isBroken = true;
        	MessageUtil.error(logger, "hashSet", e);
            e.printStackTrace();
        } finally {
        	release(jedis, isBroken);
        }
        return index;
	}

	public List<String> get(int redisIndex,String key) {
        List<String> value = null;
        Jedis jedis = getJedis(redisIndex);
        boolean isBroken = false;
        try {
            value = jedis.lrange(key, 0, jedis.llen(key)-1);
        } catch (Exception e) {
        	isBroken = true;
        	MessageUtil.error(logger, "getHash", e);
            e.printStackTrace();
        } finally {
        	release(jedis, isBroken);
        }
        return value;
	}
	//length：-1 代表全部
	public List<String> get(int redisIndex,String key,Long start,Long length) {
        List<String> value = null;
        Jedis jedis = getJedis(redisIndex);
        boolean isBroken = false;
        try {
            value = jedis.lrange(key, start,length);
        } catch (Exception e) {
        	isBroken = true;
        	MessageUtil.error(logger, "getHash", e);
            e.printStackTrace();
        } finally {
        	release(jedis, isBroken);
        }
        return value;
	}	
	
	
	
	public void remove(int redisIndex,String key) {
		Jedis jedis =getJedis(redisIndex);
		boolean isBroken = false;
		try {
			if(jedis.exists(key))
				jedis.del(key);
		}catch (Exception e) {
            isBroken = true;
            MessageUtil.error(logger, "List Remove", e);
        } finally {
            release(jedis, isBroken);
        }	
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
            MessageUtil.error(logger, "existHash", e);
        } finally {
            release(jedis, isBroken);
        }
		return flag;
	}



}
