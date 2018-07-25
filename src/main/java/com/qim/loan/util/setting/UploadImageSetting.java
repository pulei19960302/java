package com.qim.loan.util.setting;

import com.qim.loan.util.common.PropertiesUtil;
import com.qim.loan.util.common.StringUtil;

public class UploadImageSetting {
	// 最大尺寸
	private static Integer maxSize;
	//真实路径
	private static String realPath;
	//显示路径
	private static String showPath;
	
	
	static{
		Boolean flag=PropertiesUtil.setProperties("uploadImgSetting.properties");
		if(flag){
			setMaxSize(PropertiesUtil.getPropInt("upload.image.maxSize"));
			setRealPath(PropertiesUtil.getPropStr("upload.image.realPath"));
		}else{
			setMaxSize(null);
			setRealPath("/usr/local/service/upload/");
		}
		setShowPath(null);
	}
	

	public static Integer getMaxSize() {
		return maxSize;
	}

	public static void setMaxSize(Integer maxSize) {
		if(maxSize==null)
			maxSize=2000;
		maxSize=maxSize*1024;	
		UploadImageSetting.maxSize = maxSize;
	}

	public static String getRealPath() {
		return realPath;
	}

	public static void setRealPath(String realPath) {
		UploadImageSetting.realPath = realPath;
	}

	public static String getShowPath() {
		return showPath;
	}

	public static void setShowPath(String showPath) {
		if(StringUtil.isNull(showPath))
			showPath="/upload/";
		UploadImageSetting.showPath = showPath;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
