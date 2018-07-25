package com.qim.loan.util.setting;

import com.qim.loan.util.common.PropertiesUtil;

/**
 * 
     * 类名:StatusSetting
     * 描述:全部状态设置	 
     * 创建者:冯子文
     * 创建时间: 2018年5月31日 上午9:50:11 
	 * 更新者:冯子文   
	 * 更新时间: 2018年5月31日 上午9:50:11
 */
public class StatusSetting {
	// 成功
	private static Integer successStatus;
	// 失败
	private static Integer failureStatus;
	// 鉴权失败
	private static Integer authorityFailure;
	// 异常
	private static Integer exceptionFailure;
	//
	private static Integer uploadFailure;
	
	private static Integer uploadSuccess;

	public static Integer getUploadSuccess() {
		return uploadSuccess;
	}

	public static void setUploadSuccess(Integer uploadSuccess) {
		StatusSetting.uploadSuccess = uploadSuccess;
	}

	static {
		Boolean flag=PropertiesUtil.setProperties("StatusSetting.properties");
		setSuccessStatus(PropertiesUtil.getDefaultInt(flag,"status.success",0));
		setFailureStatus(PropertiesUtil.getDefaultInt(flag,"status.failure",1));
		setAuthorityFailure(PropertiesUtil.getDefaultInt(flag,"status.authority-failure",5));
		setExceptionFailure(PropertiesUtil.getDefaultInt(flag,"status.exception-failure",9));
		setUploadFailure(PropertiesUtil.getDefaultInt(flag,"status.upload-failure",10));
		setUploadSuccess(PropertiesUtil.getDefaultInt(flag,"status.upload-success",0));
	}
	
	public static void setSuccessStatus(Integer successStatus) {
		if(successStatus==null)
			successStatus=0;
		StatusSetting.successStatus = successStatus;
	}

	public static void setFailureStatus(Integer failureStatus) {
		if(failureStatus==null)
			failureStatus=1;
		StatusSetting.failureStatus = failureStatus;
	}

	public static void setAuthorityFailure(Integer authorityFailure) {
		if(authorityFailure==null)
			authorityFailure=5;
		StatusSetting.authorityFailure = authorityFailure;
	}

	public static void setExceptionFailure(Integer exceptionFailure) {
		if(exceptionFailure==null)
			exceptionFailure=9;
		StatusSetting.exceptionFailure = exceptionFailure;
	}

	public static void setUploadFailure(Integer uploadFailure) {
		if(uploadFailure==null)
			uploadFailure=10;
		StatusSetting.uploadFailure = uploadFailure;
	}

	public static Integer getSuccessStatus() {
		return successStatus;
	}

	public static Integer getFailureStatus() {
		return failureStatus;
	}

	public static Integer getAuthorityFailure() {
		return authorityFailure;
	}

	public static Integer getExceptionFailure() {
		return exceptionFailure;
	}

	public static Integer getUploadFailure() {
		return uploadFailure;
	}

	public static void main(String[] args) {

	}

}
