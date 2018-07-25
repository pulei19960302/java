package com.qim.loan.util.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneUtil {

	/**
	 * 
     * 方法名:phoneCheck
     * 功能描述:	 手机号正则验证
     * 创建者:刘鑫
     * 创建时间: 2018年4月9日 下午3:46:25 
	 * 更新者:刘鑫  
	 * 更新时间: 2018年4月9日 下午3:46:25
	 */
	public static Boolean phoneCheck(String phone) {
		if (phone.length() == 11) {
			String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0-9]))\\d{8}$";
			Pattern p = Pattern.compile(regex);  
			Matcher m = p.matcher(phone);  
			return m.find();
		}else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		if (phoneCheck("18224465304")) {
			System.out.println("手机格式正确！");
		}else {
			System.out.println("手机格式错误！");
		}
	}
}
