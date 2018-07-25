package com.qim.loan.util.common;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;
/**
 * (OK)
     * 类名:CacheStoreUtil
     * 描述:内存存储工具	 
     * 创建者:冯子文
     * 创建时间: 2018年1月23日 下午10:01:07 
	 * 更新者:冯子文   
	 * 更新时间: 2018年1月23日 下午10:01:07
 */
public class CacheStoreUtil {
	 // 这个是线程安全的
    private static ConcurrentHashMap<String,Object> storeMap = new ConcurrentHashMap<String,Object>(); 
    // 插入一条数据
    public static void set(String key, Object value){ 
        synchronized (Object.class){ 
        	storeMap.put(key, value); 
        } 
    }
    // 得到用户数量
    public static int getSize(){ 
        return storeMap.size(); 
    } 
    // 查找用户是否存在
    public static boolean exist(String key) { 
        if (storeMap.containsKey(key)) { 
            return true; 
        } else { 
            return false; 
        } 
    } 
    // 得到用户登录信息
    public static Object get(String key){ 
        if (storeMap.containsKey(key)) 
            return storeMap.get(key);  
        return null; 
    }
    public static String getString(String key){ 
        if (storeMap.containsKey(key)) 
            return GsonUtil.toJson(storeMap.get(key));  
        return null; 
    }
    
    // 删除
    public static void delete(String key) { 
        if (storeMap.containsKey(key))
            synchronized (Object.class){ 
            	storeMap.remove(key); 
            }  
    } 
    // 打印所有信息
    public static void print() { 
        Enumeration<String> keys = storeMap.keys(); 
        while (keys.hasMoreElements()) { 
            String tempKey = keys.nextElement(); 
            System.out.println(tempKey + " = " + GsonUtil.toJson(storeMap.get(tempKey))); 
        } 
    } 
	public static void main(String[] args) {

	}

}
