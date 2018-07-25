package com.qim.loan.util.cache.string;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qim.loan.util.cache.classify.string.IStringCache;

@Service("stringCacheFactory")
public class StringCacheFactory {
    
	@Resource(name="authCache")
	private IStringCache authCache;
	
    @Resource(name="smsCache")
	private IStringCache smsCache;	
    
    @Resource(name="userCache")
	private IStringCache userCache;
    
    @Resource(name="randomCache")
	private IStringCache randomCache;         
 
    @Resource(name="dynamicCache")
	private IStringCache dynamicCache;     
    
    public IStringCache getAuthor(){//验证码
    	return authCache;
    }
    //短信存储
    public IStringCache getSMS(){
    	return smsCache;
    }     
    //用户userId,userToken存储
    public IStringCache getUser(){
    	return userCache;
    }  
    public IStringCache getRandom(){//验证码
    	return randomCache;
    } 
    public IStringCache getDynamic(){//验证码
    	return dynamicCache;
    }    
    
}
