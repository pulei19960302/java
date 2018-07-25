package com.qim.loan.util.author;

import com.qim.loan.util.common.StringUtil;
import com.qim.loan.util.paramter.BaseResponse;

public class ResoureManageUtil {
	/**
	 * 
	     * 方法名:isPremitChannel
	     * 功能描述:渠道资源鉴权	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月4日 上午9:23:22 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月4日 上午9:23:22
	 */
	public static Boolean isPremitChannel(String resourceAuthor) {
		if(StringUtil.equalsIgnoreCases(resourceAuthor,"c"))
			return true;
		return false;
	}
	/**
	 * 
	     * 方法名:isPremitEmployeePC
	     * 功能描述:员工后台二鉴权	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月4日 上午9:23:51 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月4日 上午9:23:51
	 */
	public static Boolean isPremitEmployeePC(String resourceAuthor) {
		if(StringUtil.equalsIgnoreCases(resourceAuthor,"ep"))
			return true;
		return false;
	}	
	/**
	 * 
	     * 方法名:isPremitEmployeeMB
	     * 功能描述:员工后台一鉴权	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月4日 上午9:24:09 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月4日 上午9:24:09
	 */
	public static Boolean isPremitEmployeeMB(String resourceAuthor) {
		if(StringUtil.equalsIgnoreCases(resourceAuthor,"em"))
			return true;
		return false;
	}	
	
	
	/**
	 * 
	     * 方法名:isPremitEmployeeM
	     * 功能描述:总后台鉴权	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月4日 上午9:24:35 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月4日 上午9:24:35
	 */
	public static Boolean isPremitManage(String resourceAuthor) {
		if(StringUtil.equalsIgnoreCases(resourceAuthor,"m"))
			return true;
		return false;
	}
	
	
	
	public static String printForbidden() {
		return BaseResponse.setFailure("你没有权限访问该资源.").toString();
	}
	
	
}
