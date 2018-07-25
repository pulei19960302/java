package com.qim.loan.util.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobilUtil {

	public static boolean isMobile(String mobiles){   
		Pattern p = Pattern.compile("^((13[0-9])|(14[5,9])|(15[^4,\\D])|(166)|(17[3,5-8])|(18[0-9])|(19[8,9]))\\d{8}$");  	  
		Matcher m = p.matcher(mobiles);   
		return m.matches();  	  
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(isMobile("13912345678"));
		
		
		
	}

}
