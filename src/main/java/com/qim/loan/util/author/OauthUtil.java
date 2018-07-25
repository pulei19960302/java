package com.qim.loan.util.author;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qim.loan.util.common.ByteUtil;
import com.qim.loan.util.common.HexUtil;
import com.qim.loan.util.common.MessageUtil;
import com.qim.loan.util.common.StringUtil;

/**
 * 
 * 类名:OauthEncryptUtil 
 * 描述:userID,userToken加解密 
 * 创建者:冯子文 创建时间: 2016年5月13日
 * 上午10:34:01 
 * 更新者:冯子文 
 * 更新时间: 2016年5月13日 上午10:34:01
 */
public class OauthUtil {
	private static Logger logger = LoggerFactory.getLogger(OauthUtil.class);
	
	private static String cryptKey ;
	//desMode
	private final static String desMode="DES/CBC/PKCS5Padding";		

	/**
	 * 加密
	 * 
	 * @param src
	 *            数据源
	 * @param key
	 *            密钥，长度必须是8的倍数
	 * @return 返回加密后的数据
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] src, byte[] key){
		IvParameterSpec spec = new IvParameterSpec(cryptKey.getBytes());
		DESKeySpec dks;
		Cipher cipher = null;
		byte[] resultByte = null;
		try {
			dks = new DESKeySpec(key);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(dks);
			cipher = Cipher.getInstance(desMode);
			cipher.init(Cipher.ENCRYPT_MODE, securekey, spec);
			resultByte=cipher.doFinal(src);
		} catch (IllegalBlockSizeException |InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidAlgorithmParameterException | BadPaddingException e) {
			MessageUtil.error(logger, "encrypt", e);
			e.printStackTrace();
		}
		return resultByte;
	}

	private static byte[] decrypt(byte[] src, byte[] key){
		IvParameterSpec spec = new IvParameterSpec(cryptKey.getBytes());
		DESKeySpec dks;
		Cipher cipher = null;
		byte[] resultByte = null;
		try {
			dks = new DESKeySpec(key);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(dks);
			cipher = Cipher.getInstance(desMode);
			cipher.init(Cipher.DECRYPT_MODE, securekey, spec);
			resultByte=cipher.doFinal(src);
		} catch (IllegalBlockSizeException |BadPaddingException |InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidAlgorithmParameterException e) {
			MessageUtil.error(logger, "decrypt", e);
			e.printStackTrace();
		}
		return 	resultByte;	
	}

	public static String decrypt(String data){
		return decrypt(data, cryptKey);
	}

	public static String decrypt(String data, String key){
		try {
			return new String(decrypt(HexUtil.toByte(data.getBytes()),StringUtil.isNotNull(key)?key.getBytes():cryptKey.getBytes()));
		} catch (Exception e) {
			MessageUtil.error(logger, "解密", e);
			e.printStackTrace();
		}
		return "";
	}

	public static String encrypt(String data) {
		return encrypt(data, cryptKey);
	}

	public static String encrypt(String data, String key) {
		try {
			return ByteUtil.toHex(encrypt(data.getBytes(),StringUtil.isNotNull(key)?key.getBytes():cryptKey.getBytes()));
		} catch (Exception e) {
			MessageUtil.error(logger, "加密", e);
			e.printStackTrace();
		} 
		return null;
	}

	public static void setCryptKey(String cryptKey) {
		OauthUtil.cryptKey = cryptKey;
	}
	public static void main(String[] args) {
		
		
	}

}
