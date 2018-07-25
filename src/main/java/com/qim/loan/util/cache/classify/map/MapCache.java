package com.qim.loan.util.cache.classify.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qim.loan.util.cache.classify.base.impl.RedisSourceImpl;
import com.qim.loan.util.common.MessageUtil;

import redis.clients.jedis.Jedis;
@Service("mapCache")
public class MapCache extends RedisSourceImpl {

	private static Logger logger = LoggerFactory.getLogger(MapCache.class);
	
	public Long set(int redisIndex,String key,String field,String value) {
        Jedis jedis = getJedis(redisIndex);
        boolean isBroken = false;
        Long index=0L;
        try {
        	index=jedis.hset(key,field,value);
        } catch (Exception e) {
        	isBroken = true;
        	MessageUtil.error(logger, "hashSet", e);
            e.printStackTrace();
        } finally {
        	release(jedis, isBroken);
        }
        return index;
	}
	
	public String set(int redisIndex,String key,HashMap<String, String> map) {
        Jedis jedis = getJedis(redisIndex);
        boolean isBroken = false;
        String index="";
        try {
        	index=jedis.hmset(key,map);
        } catch (Exception e) {
        	isBroken = true;
        	MessageUtil.error(logger, "hashSet", e);
            e.printStackTrace();
        } finally {
        	release(jedis, isBroken);
        }
        return index;
	}	

	public Long increment(int redisIndex,String key,String field) {
        Jedis jedis = getJedis(redisIndex);
        boolean isBroken = false;
        Long index=0L;
        try {
        	index=jedis.hincrBy(key, field,1L);
        } catch (Exception e) {
        	isBroken = true;
        	MessageUtil.error(logger, "hashSet", e);
            e.printStackTrace();
        } finally {
        	release(jedis, isBroken);
        }
        return index;
	}	
	
	
	public List<String> get(int redisIndex,String key,String ...fields) {
        List<String> value = null;
        Jedis jedis = getJedis(redisIndex);
        boolean isBroken = false;
        try {
            value = jedis.hmget(key, fields);
        } catch (Exception e) {
        	isBroken = true;
        	MessageUtil.error(logger, "getHash", e);
            e.printStackTrace();
        } finally {
        	release(jedis, isBroken);
        }
        return value;
	}

	public Map<String,String> get(int redisIndex,String key) {
        Map<String,String> value = new HashMap<String,String>();;
        Jedis jedis = getJedis(redisIndex);
        boolean isBroken = false;
        try {
            value = jedis.hgetAll(key);
        } catch (Exception e) {
        	isBroken = true;
        	MessageUtil.error(logger, "getHash", e);
            e.printStackTrace();
        } finally {
        	release(jedis, isBroken);
        }
        return value;
	}	
	
	
	public Set<String> getKeys(int redisIndex,String key) {
        Set<String> value = new HashSet<String>();;
        Jedis jedis = getJedis(redisIndex);
        boolean isBroken = false;
        try {
            value = jedis.hkeys(key);
        } catch (Exception e) {
        	isBroken = true;
        	MessageUtil.error(logger, "getHash", e);
            e.printStackTrace();
        } finally {
        	release(jedis, isBroken);
        }
        return value;
	}	
	
	public List<String> getValues(int redisIndex,String key) {
        List<String> value = new LinkedList<String>();;
        Jedis jedis = getJedis(redisIndex);
        boolean isBroken = false;
        try {
            value = jedis.hvals(key);
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

	public void remove(int redisIndex,String key,String field) {
		Jedis jedis =getJedis(redisIndex);
		boolean isBroken = false;
		try {
			if(jedis.hexists(key,field))
				jedis.hdel(key,field);
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

	public Boolean exist(int redisIndex,String key,String field) {
		Jedis jedis = getJedis(redisIndex);
		Boolean flag=false;
		boolean isBroken = false;
		try {
			if(jedis.hexists(key, field))
				flag=true;
		}catch (Exception e) {
            isBroken = true;
            MessageUtil.error(logger, "existHash", e);
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
				size=jedis.hlen(key);
		}catch (Exception e) {
            isBroken = true;
            MessageUtil.error(logger, "String exist", e);
        } finally {
            release(jedis, isBroken);
        }
		return size;
	}

}
