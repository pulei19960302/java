package com.qim.loan.util.setting;

import com.qim.loan.util.common.PropertiesUtil;
import com.qim.loan.util.common.StringUtil;

public class UploadExcelSetting {
	// 最大尺寸
	private static Integer maxSize;
	//真实路径
	private static String realPath;
	//显示路径
	private static String showPath;
	static {
		Boolean flag=PropertiesUtil.setProperties("/config/UploadSetting.properties");
		setMaxSize(PropertiesUtil.getDefaultInt(flag, "upload.maxSize", 200));
		setRealPath(PropertiesUtil.getDefaultStr(flag, "upload.realPath", "D:/"));
		setShowPath(PropertiesUtil.getDefaultStr(flag, "upload.showPath", "/upload/"));			
	}
	public static Integer getMaxSize() {
		return maxSize;
	}

	public static void setMaxSize(Integer maxSize) {
		if(maxSize==null)
			maxSize=200;
		maxSize=maxSize*1024;	
		UploadExcelSetting.maxSize = maxSize;
	}

	public static String getRealPath() {
		return realPath;
	}

	public static void setRealPath(String realPath) {
		if(StringUtil.isNull(realPath)) 
			realPath ="D:/";		
		UploadExcelSetting.realPath = realPath;
	}

	public static String getShowPath() {
		return showPath;
	}

	public static void setShowPath(String showPath) {
		if(StringUtil.isNull(showPath))
			showPath="/usr/local/updownload/sichuangupload/";
		UploadExcelSetting.showPath = showPath;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
