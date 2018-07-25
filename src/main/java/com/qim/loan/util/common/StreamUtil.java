package com.qim.loan.util.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * 
     * 类名:StreamUtil
     * 描述:Stream工具	 
     * 创建者:冯子文
     * 创建时间: Dec 8, 2017 10:13:01 AM 
	 * 更新者:冯子文   
	 * 更新时间: Dec 8, 2017 10:13:01 AM
 */
public class StreamUtil {

	public static final byte[] toByte(InputStream inStream){  
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
		byte[] buff = new byte[100];  
		int rc = 0;  
		try {
			while ((rc = inStream.read(buff, 0, 100)) > 0)
				swapStream.write(buff, 0, rc);
		} catch (IOException e) {
			PrintUtil.println("流转换:", e);
		}   
		return swapStream.toByteArray();   
	} 
}
