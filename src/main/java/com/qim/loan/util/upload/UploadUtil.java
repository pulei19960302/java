package com.qim.loan.util.upload;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import com.qim.loan.util.common.FileUtil;
import com.qim.loan.util.common.MessageUtil;
import com.qim.loan.util.common.PrimaryKeyUtil;
import com.qim.loan.util.common.StringUtil;
import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.setting.StatusSetting;
import com.qim.loan.util.setting.UploadExcelSetting;
import com.qim.loan.util.setting.UploadImageSetting;


public class UploadUtil {

	private static Logger logger = LoggerFactory.getLogger(UploadUtil.class);
	/**
	 * 
	     * 方法名:getSuffix
	     * 功能描述:获取后缀	 
	     * 创建者:冯子文
	     * 创建时间: Apr 17, 2018 2:23:20 PM 
		 * 更新者:冯子文  
		 * 更新时间: Apr 17, 2018 2:23:20 PM
	 */
	private static String getSuffix(String name){
		return StringUtil.getSuffix(name);
	}
	/**
	 * 
	     * 方法名:isUploadType
	     * 功能描述:验证上传类型	 
	     * 创建者:冯子文
	     * 创建时间: Apr 17, 2018 2:22:39 PM 
		 * 更新者:冯子文  
		 * 更新时间: Apr 17, 2018 2:22:39 PM
	 */
	private static Boolean isUploadType(UploadType uploadType,String name){
		if(StringUtil.isNull(name))
			return false;
		String suffix=getSuffix(name).toLowerCase();
		Boolean flag=false;
		switch (uploadType) {
			case UPLOAD_PICTURE:
				if(suffix.equalsIgnoreCase("jpeg") || suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("png"))
					flag=true;				
				break;
			case UPLOAD_EXCEL:
				if(suffix.equalsIgnoreCase("xlsx") || suffix.equalsIgnoreCase("xls") || suffix.equalsIgnoreCase("et") || suffix.equalsIgnoreCase("ett"))
					flag=true;				
				break;
			case UPLOAD_WORD:
				if(suffix.equalsIgnoreCase("doc") || suffix.equalsIgnoreCase("docx") || suffix.equalsIgnoreCase("rtf") || suffix.equalsIgnoreCase("wps") || suffix.equalsIgnoreCase("wpt"))
					flag=true;
				break;
			case UPLOAD_RAR:
				if(suffix.equalsIgnoreCase("rar") || suffix.equalsIgnoreCase("zip") || suffix.equalsIgnoreCase("7zip"))
					flag=true;
				break;
			case UPLOAD_TXT:
				if(suffix.equalsIgnoreCase("txt") || suffix.equalsIgnoreCase("rtf"))
					flag=true;
				break;	
			default:
				flag=false;
				break;
		}
		return flag;
	}
	/**
	 * 
	     * 方法名:isOutOfSize
	     * 功能描述:是否越界	 
	     * 创建者:冯子文
	     * 创建时间: Apr 17, 2018 2:22:53 PM 
		 * 更新者:冯子文  
		 * 更新时间: Apr 17, 2018 2:22:53 PM
	 */
	private static Boolean isOutOfSize(UploadType uploadType,Long size){
		Boolean flag=true;
		switch (uploadType) {
			case UPLOAD_PICTURE:
				if(size==null || size<0 || size>(UploadImageSetting.getMaxSize()))
					flag=false;			
				break;
			case UPLOAD_EXCEL:
				if(size==null || size<0 || size>(UploadExcelSetting.getMaxSize()))
					flag=false;			
				break;
			case UPLOAD_WORD:

				break;
			case UPLOAD_RAR:

				break;
			case UPLOAD_TXT:
				if(size==null || size<0 || size>(UploadExcelSetting.getMaxSize()))
					flag=false;			
				break;	
			default:
				flag=false;
				break;
		}
		return flag;
	}
	
	private static String getFullPath(Boolean isRealPath,UploadType uploadType,String fileType){
		String path="";
		switch (uploadType) {
			case UPLOAD_PICTURE:
				path=(isRealPath?UploadImageSetting.getRealPath():UploadImageSetting.getShowPath())+(StringUtil.isNull(fileType)?"common":StringUtil.firstCharLowStrip(fileType))+"/";
				break;
			case UPLOAD_EXCEL:
				path=(isRealPath?UploadExcelSetting.getRealPath():UploadExcelSetting.getShowPath())+"/"+(StringUtil.isNull(fileType)?"common":StringUtil.firstCharLowStrip(fileType))+"/";			
				break;
			case UPLOAD_WORD:
				path=(isRealPath?UploadImageSetting.getRealPath():UploadImageSetting.getShowPath())+"/"+(StringUtil.isNull(fileType)?"common":StringUtil.firstCharLowStrip(fileType))+"/";
				break;
			case UPLOAD_RAR:
				path=(isRealPath?UploadImageSetting.getRealPath():UploadImageSetting.getShowPath())+"/"+(StringUtil.isNull(fileType)?"common":StringUtil.firstCharLowStrip(fileType))+"/";
				break;
			case UPLOAD_TXT:
				path=(isRealPath?UploadExcelSetting.getRealPath():UploadExcelSetting.getShowPath())+"/"+(StringUtil.isNull(fileType)?"common":StringUtil.firstCharLowStrip(fileType))+"/";			
				break;
				
			default:
				break;
		}
		return path;	
	}
	
	private static synchronized BaseResponse uploadFileLs(UploadType uploadType,String pictureType,List<MultipartFile> filedataLs){
		StringBuilder sBuilder=new StringBuilder();
		for(MultipartFile  multipartFile:filedataLs){
			BaseResponse tmpBaseResponse=uploadFile(false,uploadType,pictureType,multipartFile);
			if(tmpBaseResponse.getStatus() ==StatusSetting.getSuccessStatus())
				sBuilder.append(tmpBaseResponse.getUploadPath()+",");			
		}
		if(StringUtil.isNotNull(sBuilder.toString())){
			sBuilder.deleteCharAt(sBuilder.length()-1);
			return BaseResponse.setUploadSuccess(sBuilder.toString());
		}else{
			return BaseResponse.setUploadFailureMsg("文件在上传过程中出现错误.");
		}
	}
	
	public static synchronized BaseResponse uploadImgLs(String pictureType,List<MultipartFile> filedataLs){
		return uploadFileLs(UploadType.UPLOAD_PICTURE,pictureType,filedataLs);
	}
	
	public static synchronized BaseResponse uploadExcelLs(String pictureType,List<MultipartFile> filedataLs){
		return uploadFileLs(UploadType.UPLOAD_EXCEL,pictureType,filedataLs);
	}	
	
	
	private static synchronized BaseResponse uploadFile(Boolean isReal,UploadType uploadType,String fileType,MultipartFile multipartFile){
		if(multipartFile==null){
			return BaseResponse.setUploadFailureMsg("请上传文件.");
		}else{
			String fileName=multipartFile.getOriginalFilename();
			Long fileSize=multipartFile.getSize();
			if(!isUploadType(uploadType,fileName))
				return BaseResponse.setUploadFailureMsg("上传的文件类型不匹配.");
			if(!isOutOfSize(uploadType,fileSize))
				return BaseResponse.setUploadFailureMsg("上传的文件大小越界.");
			String id=PrimaryKeyUtil.getPrimaryId32();
			String realPath=getFullPath(true,uploadType,fileType);
			String virtualPath=getFullPath(false,uploadType,fileType);
			String virtualName=id +"."+ StringUtil.getSuffix(fileName).toLowerCase();
			FileUtil.createDir(realPath);//创建目录
			File targetFile = new File(realPath,virtualName); 
			try {
				multipartFile.transferTo(targetFile);
				if(isReal) {
					return BaseResponse.setUploadSuccess(realPath+virtualName);
				}else {
					return BaseResponse.setUploadSuccess(virtualPath+virtualName);					
				}
			} catch (IllegalStateException | IOException e) {
				MessageUtil.error(logger, "上传文件存储", e);
				return BaseResponse.setUploadFailureMsg("上传的"+fileName+"在上传过程中出现错误"+e);
			}
		}
	}	
	
	
	
	public static synchronized BaseResponse uploadImg(String fileType,MultipartFile multipartFile){
		return uploadFile(false,UploadType.UPLOAD_PICTURE,fileType,multipartFile);
	}
	
	public static synchronized BaseResponse uploadExcel(String fileType,MultipartFile multipartFile){
		return uploadFile(false,UploadType.UPLOAD_EXCEL,fileType,multipartFile);
	}
	
	public static synchronized BaseResponse uploadRealExcel(String fileType,MultipartFile multipartFile){
		return uploadFile(true,UploadType.UPLOAD_EXCEL,fileType,multipartFile);
	}
	
	public static synchronized BaseResponse uploadRealTxt(String fileType,MultipartFile multipartFile){
		return uploadFile(true,UploadType.UPLOAD_TXT,fileType,multipartFile);
	}
	
	
}
