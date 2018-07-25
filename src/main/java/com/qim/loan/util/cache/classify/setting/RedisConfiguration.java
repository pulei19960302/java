package com.qim.loan.util.cache.classify.setting;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.beans.factory.annotation.Qualifier;  
import org.springframework.beans.factory.annotation.Value;  
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.Configuration;  
  

import redis.clients.jedis.JedisPool;  
import redis.clients.jedis.JedisPoolConfig;  
  
@Configuration  
public class RedisConfiguration {  
      
    @Bean(name= "redis.pool")  
    @Autowired  
    public JedisPool jedisPool(@Qualifier("redis.pool.config") JedisPoolConfig config,@Value("${redis.pool.host}")String host,@Value("${redis.pool.port}")int port, @Value("${redis.pool.timeout}")int timeout, @Value("${redis.pool.password}")final String password) {  
        return new JedisPool(config, host, port,timeout,password);  
    }    
    @Bean(name= "redis.pool.config")  
    public JedisPoolConfig jedisPoolConfig (@Value("${redis.pool.config.minIdle}")int minIdle,@Value("${redis.pool.config.maxTotal}")int maxTotal,@Value("${redis.pool.config.maxIdle}")int maxIdle,@Value("${redis.pool.config.maxWaitMillis}")int maxWaitMillis) {  
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMinIdle(minIdle);
        config.setMaxTotal(maxTotal);  
        config.setMaxIdle(maxIdle);  
        config.setMaxWaitMillis(maxWaitMillis);  
        return config;  
    }  
      
} 
