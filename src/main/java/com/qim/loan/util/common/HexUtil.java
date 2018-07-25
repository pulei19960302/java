package com.qim.loan.util.common;


/**
 * (OK)
     * 类名:HexUtil
     * 描述:Hex工具	 
     * 创建者:冯子文
     * 创建时间: Dec 8, 2017 10:11:20 AM 
	 * 更新者:冯子文   
	 * 更新时间: Dec 8, 2017 10:11:20 AM
 */
public class HexUtil {
	
	//private static Logger logger = Logger.getLogger(HexUtil.class);

	public static byte[] toByte(byte[] b) {
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}
	
	public static void main(String[] args) {

	}

}
