package com.qim.loan.util.common;

import java.util.Date;

import org.slf4j.Logger;

/**
 * (OK)
     * 类名:MessageUtil
     * 描述:日志工具	 
     * 创建者:冯子文
     * 创建时间: 2017年6月28日 下午1:45:16 
	 * 更新者:冯子文   
	 * 更新时间: 2017年6月28日 下午1:45:16
 */
public class MessageUtil {
     
	public static void error(Logger logger,String msg,Exception e){
		logger.error("[ error ]  "+ new Date()+":"+msg+"错误:"+e.getMessage());
	}

	public static void error(Logger logger,String msg){
		logger.error("[ error ]  "+ new Date()+":"+msg);
	}	
	
	public static void warning(Logger logger,String msg){
		logger.error("[warning]  "+ new Date()+":"+msg);
	}
	
	public static void info(Logger logger,String msg){
		logger.error("[  info ]  "+ new Date()+":"+msg);
	}
	
}
