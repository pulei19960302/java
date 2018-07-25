package com.qim.loan.util.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
     * 类名:ValidateUtil
     * 描述:验证工具 
     * 创建者:冯子文
     * 创建时间: 2018年6月9日 下午4:10:28 
	 * 更新者:冯子文   
	 * 更新时间: 2018年6月9日 下午4:10:28
 */
public class ValidateUtil {
	/**
	 * 
	     * 方法名:isNumeric
	     * 功能描述:判断是否为数字	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月9日 下午4:11:27 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月9日 下午4:11:27
	 */
	public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]+[.]?[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) 
            return false;
        return true;
    }
	
	public static void main(String ...args) {
		System.out.println(isNumeric("2.5"));
	} 
	
	
	
}
