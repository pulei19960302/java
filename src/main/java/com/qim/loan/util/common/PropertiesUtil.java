package com.qim.loan.util.common;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 
     * 类名:PropertiesUtil
     * 描述:读取配置文件	(兼容linux) 
     * 创建者:冯子文
     * 创建时间: 2018年5月31日 上午9:39:28 
	 * 更新者:冯子文   
	 * 更新时间: 2018年5月31日 上午9:39:28
 */
public class PropertiesUtil {
	
	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	
	private static Properties properties;
	
	public static Boolean setProperties(String propertiesName) {
		try {
			Properties tmProperties=PropertiesLoaderUtils.loadAllProperties(propertiesName);
			if(tmProperties==null || tmProperties.isEmpty() || tmProperties.size()==0)
				return false;
			else {
				properties=tmProperties;
				return true;
			}	
		} catch (IOException e) {
			MessageUtil.error(logger,"", e);
			return false;
		}	
	}	
	/**
	 * 
	     * 方法名:getProp
	     * 功能描述:读取配置文件	 
	     * 创建者:冯子文
	     * 创建时间: Jan 10, 2018 9:30:53 AM 
		 * 更新者:冯子文  
		 * 更新时间: Jan 10, 2018 9:30:53 AM
	 */
	public static String getProp(String ... args){
		switch(args.length){
			case 1:
				return properties.getProperty(args[0]).trim();
			case 2:
				return properties.getProperty(args[0], args[1]).trim();
		}
		return "";
	}
	/**
	 * 
	     * 方法名:getPropInt
	     * 功能描述:获取配置文件整形	 
	     * 创建者:冯子文
	     * 创建时间: Jan 10, 2018 9:30:31 AM 
		 * 更新者:冯子文  
		 * 更新时间: Jan 10, 2018 9:30:31 AM
	 */
	public static Integer getPropInt(String ... args){
		String result=getProp(args);
		if(StringUtil.isNull(result))
			return null;
		else
			return Integer.parseInt(result);
	}
	
	public static Integer getDefaultInt(String key,Integer defaultvalue) {
		Integer tmp=getPropInt(key);
		return tmp==null?defaultvalue:tmp;
	}
	
	public static Integer getDefaultInt(Boolean flag,String key,Integer defaultvalue) {
		return flag?getDefaultInt(key,defaultvalue):defaultvalue;
	}
	
	
	
	
	/**
	 * 
	     * 方法名:getPropStr
	     * 功能描述:获取配置文件字符串	 
	     * 创建者:冯子文
	     * 创建时间: Jan 10, 2018 9:30:15 AM 
		 * 更新者:冯子文  
		 * 更新时间: Jan 10, 2018 9:30:15 AM
	 */
	public static String getPropStr(String ... args){
		return getProp(args);
	}
	
	public static String getDefaultStr(String key,String defaultvalue) {
		String tmp=getPropStr(key);
		return StringUtil.isNull(tmp)?defaultvalue:tmp;
	}
	
	public static String getDefaultStr(Boolean flag,String key,String defaultvalue) {
		return flag?getDefaultStr(key,defaultvalue):defaultvalue;
	}	
	
	public static void main(String ...args) {
		//Boolean falg=PropertiesUtil.setProperties("setting1.properties");
		System.out.println(Integer.parseInt(null));
		
		
	}
	
	
	
}
