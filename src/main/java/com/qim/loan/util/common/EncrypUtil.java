package com.qim.loan.util.common;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
     * 类名:EncrypUtil
     * 描述:	加密工具 
     * 创建者:冯子文
     * 创建时间: Dec 8, 2017 10:10:43 AM 
	 * 更新者:冯子文   
	 * 更新时间: Dec 8, 2017 10:10:43 AM
 */
public class EncrypUtil {
	
	//private static Logger logger = LoggerFactory.getLogger(EncrypUtil.class);
	
	public static String Encrypt(String str){
		if (StringUtil.isNull(str))
			return "";
		return base64Encode(SHA256(SHA1(MD5(str))));
	}
	
	  /**
     * Base64 encode
     * */
    public static String base64Encode(String data){
        return Base64.encodeBase64String(data.getBytes());
    }
     
    /**
     * Base64 decode
     * */
	public static String base64Decode(String data) throws UnsupportedEncodingException{
        return new String(Base64.decodeBase64(data.getBytes()),"utf-8");
    }
     
    /**
     * md5
     * */
    public static String MD5(String data){
        return DigestUtils.md5Hex(data);
    }
     
    /**
     * sha1
     * */
    public static String SHA1(String data){
        return DigestUtils.sha1Hex(data);
    }
     
    /**
     * sha256
     * */
    public static String SHA256(String data){
        return DigestUtils.sha256Hex(data);
    }
	
	
	
	public static void main(String[] args) {
		System.out.println(EncrypUtil.Encrypt("123456"));
	}
	

}
