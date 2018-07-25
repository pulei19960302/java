package com.qim.loan.util.common;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
/**
 * (OK)
     * 类名:ByteUtil
     * 描述:Byte工具类	 
     * 创建者:冯子文
     * 创建时间: Dec 8, 2017 10:09:54 AM 
	 * 更新者:冯子文   
	 * 更新时间: Dec 8, 2017 10:09:54 AM
 */
public class ByteUtil {

	//private static Logger logger = Logger.getLogger(ByteUtil.class);
	/**
	 * 
	     * 方法名:toHex
	     * 功能描述:byte转16进制	 
	     * 创建者:冯子文
	     * 创建时间: 2018年1月23日 下午9:49:40 
		 * 更新者:冯子文  
		 * 更新时间: 2018年1月23日 下午9:49:40
	 */
	public static String toHex(byte[] b) {
		StringBuilder sBuilder=new StringBuilder();
		String tmp = "";
		for (int i = 0; i < b.length; i++) {
			tmp = (Integer.toHexString(b[i] & 0XFF));
			if (tmp.length() == 1)
				sBuilder.append("0" + tmp);
			else
				sBuilder.append(tmp);
		}
		return sBuilder.toString().toUpperCase();
	}
	/**
	 * 
	     * 方法名:toStream
	     * 功能描述:byte转inputStream	 
	     * 创建者:冯子文
	     * 创建时间: 2018年1月23日 下午9:57:29 
		 * 更新者:冯子文  
		 * 更新时间: 2018年1月23日 下午9:57:29
	 */
	public static InputStream toStream(byte[] buf) {
		return new ByteArrayInputStream(buf);  
	} 

}
