package com.qim.loan.util.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
     * 类名:MD5Encryption
     * 描述:	 MD5加密工具
     * 创建者:刘鑫
     * 创建时间: 2018年4月3日 下午3:48:19 
	 * 更新者:刘鑫   
	 * 更新时间: 2018年4月3日 下午3:48:19
 */
public class MD5Encryption {
	
	private MD5Encryption() {}

    public static String getEncryption(String originString)
            throws UnsupportedEncodingException {
        String result = "";
        if (originString != null) {
            try {
                // 指定加密的方式为MD5
                MessageDigest md = MessageDigest.getInstance("MD5");
                // 进行加密运算
                byte bytes[] = md.digest(originString.getBytes("ISO8859-1"));
                for (int i = 0; i < bytes.length; i++) {
                    // 将整数转换成十六进制形式的字符串 这里与0xff进行与运算的原因是保证转换结果为32位
                    String str = Integer.toHexString(bytes[i] & 0xFF);
                    if (str.length() == 1) {
                        str += "F";
                    }
                    result += str;
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
    	try {
			System.out.println(MD5Encryption.getEncryption("acb123"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
    
}
