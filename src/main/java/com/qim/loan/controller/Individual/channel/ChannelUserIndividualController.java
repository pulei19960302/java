package com.qim.loan.controller.Individual.channel;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qim.loan.entity.channel.ChannelUserLoginRecord;
import com.qim.loan.service.channel.ChannelUserService;
import com.qim.loan.util.author.ResoureManageUtil;
import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.common.StringUtil;
import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.paramter.RequestPager;
import com.qim.loan.util.setting.StatusSetting;
import com.qim.loan.util.upload.UploadUtil;
/**
 *
 * 类名: ChannelUserController
 * 描述: 渠道用户Controller层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年05月29日  17:14:59
 * 更新者: 冯子文
 * 更新时间: 2018年05月29日  17:14:59
 */
@Controller
public class ChannelUserIndividualController{
	@Autowired
	@Qualifier("channelUserService")
	private ChannelUserService channelUserService;
	/*********************************以下为渠道后台接口**********************************/
	/**
	 * 方法名: login
	 * 功能描述: 根据用户名及密码登录
	 * 创建者: 冯子文
	 * 创建时间: 2018年05月29日  17:14:59
	 * 更新者: 冯子文
	 * 更新时间: 2018年05月29日  17:14:59
	 */
	@ResponseBody
	@RequestMapping(value = "/channel/user/login", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String login(ChannelUserLoginRecord channelUserLoginRecord,@Param("channelUserName")String channelUserName,@Param("channelUserPwd")String channelUserPwd)  throws Exception{
		if(StringUtil.isNull(channelUserName) || StringUtil.isNull(channelUserPwd))
			return BaseResponse.setFailure("渠道用户名或密码不能为空").toString();
		else
			try {		
				return channelUserService.login(channelUserLoginRecord,channelUserName,channelUserPwd).toString();
			} catch (Exception e) {
				return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
			}				
	}	
	/**
	 * 
	     * 方法名:loginout
	     * 功能描述:注销	 
	     * 创建者:冯子文
	     * 创建时间: 2018年5月30日 上午10:17:28 
		 * 更新者:冯子文  
		 * 更新时间: 2018年5月30日 上午10:17:28
	 */
	@ResponseBody
	@RequestMapping(value = "/channel/user/loginout", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String loginout(@Param("resourceAuthor")String resourceAuthor,@Param("channelUserId")String channelUserId)  throws Exception{
		if(!ResoureManageUtil.isPremitChannel(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {		
			return channelUserService.loginout(channelUserId).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}				
	}		
	/**
	 * 
	     * 方法名:updatePwd
	     * 功能描述:修改密码	 
	     * 创建者:冯子文
	     * 创建时间: 2018年5月29日 下午6:37:24 
		 * 更新者:冯子文  
		 * 更新时间: 2018年5月29日 下午6:37:24
	 */
	@ResponseBody
	@RequestMapping(value = "/channel/user/updatePwd", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String updatePwd(@Param("resourceAuthor")String resourceAuthor,@Param("channelUserId")String channelUserId,@Param("oldChannelUserPwd")String oldChannelUserPwd,@Param("channelUserPwd")String channelUserPwd)  throws Exception{
		if(!ResoureManageUtil.isPremitChannel(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(StringUtil.isNull(channelUserId) || StringUtil.isNull(oldChannelUserPwd) || StringUtil.isNull(channelUserPwd) || StringUtil.equals(oldChannelUserPwd, channelUserPwd))
			return BaseResponse.setFailure("渠道旧密码或新密码不能为空,新旧密码不能相等").toString();
		else
			try {		
				return channelUserService.updatePwd(channelUserId,oldChannelUserPwd,channelUserPwd).toString();
			} catch (Exception e) {
				return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}		
	/**
	 * 
	     * 方法名:statics
	     * 功能描述:统计 
	     * 创建者:冯子文
	     * 创建时间: 2018年5月29日 下午6:37:24 
		 * 更新者:冯子文  
		 * 更新时间: 2018年5月29日 下午6:37:24
	 */
	@ResponseBody
	@RequestMapping(value = "/channel/user/statics", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String statics(@Param("resourceAuthor")String resourceAuthor,@Param("channelUserId")String channelUserId,@Param("startDate")Integer startDate,@Param("endDate")Integer endDate)  throws Exception{
		if(!ResoureManageUtil.isPremitChannel(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		//如果为空 默认一周
		if(endDate==null)
			endDate=DateUtil.formatCurrentDate();
		if(startDate==null)
			startDate=DateUtil.subtractWeek(endDate);		
		try {		
			return channelUserService.statics(channelUserId,startDate,endDate).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}				
	}	
	/**
	 * 
	     * 方法名:index
	     * 功能描述:首页头顶	 
	     * 创建者:冯子文
	     * 创建时间: 2018年5月30日 上午9:22:16 
		 * 更新者:冯子文  
		 * 更新时间: 2018年5月30日 上午9:22:16
	 */
	@ResponseBody
	@RequestMapping(value = "/channel/user/index", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String index(@Param("resourceAuthor")String resourceAuthor,@Param("channelUserId")String channelUserId)  throws Exception{
		if(!ResoureManageUtil.isPremitChannel(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {		
			return channelUserService.index(channelUserId).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}				
	}		
	/**
	 * 
	     * 方法名:search
	     * 功能描述:根据时间段条件搜索	 
	     * 创建者:冯子文
	     * 创建时间: 2018年5月30日 上午10:08:34 
		 * 更新者:冯子文  
		 * 更新时间: 2018年5月30日 上午10:08:34
	 */
	@ResponseBody
	@RequestMapping(value = "/channel/user/search", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String search(@Param("resourceAuthor")String resourceAuthor,RequestPager requestPager,@Param("channelUserId")String channelUserId,@Param("startDate")Integer startDate,@Param("endDate")Integer endDate)  throws Exception{
		if(!ResoureManageUtil.isPremitChannel(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(endDate==null)
			endDate=DateUtil.formatCurrentDate();
		if(startDate==null)
			startDate=DateUtil.formatCurrentDate();
		try {		
			return channelUserService.search(requestPager,channelUserId,startDate,endDate).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}				
	}		
	/**
	 * 
	     * 方法名:excelImport
	     * 功能描述:excel导入	 
	     * 创建者:冯子文
	     * 创建时间: 2018年5月31日 下午4:18:35 
		 * 更新者:冯子文  
		 * 更新时间: 2018年5月31日 下午4:18:35
	 */
	@ResponseBody
	@RequestMapping(value = "/channel/user/excelImport", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String excelImport(@Param("resourceAuthor")String resourceAuthor,@Param("channelUserId")String channelUserId,@Param("mobilExcelUrl")MultipartFile mobilExcelUrl) throws Exception{		
		if(!ResoureManageUtil.isPremitChannel(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(mobilExcelUrl==null)
			return BaseResponse.setFailure("请选择上传文件(excel).").toString();
		BaseResponse resultResponse=UploadUtil.uploadRealExcel(channelUserId, mobilExcelUrl);
		if(resultResponse.getStatus()==StatusSetting.getSuccessStatus()){
			//解析
			String path=resultResponse.getUploadPath();
			return channelUserService.excelImportPhoneAndName(channelUserId, path).toString();
			//return channelUserService.excelImportPhoneName(channelUserId, path).toString();
			// return channelUserService.excelImport(channelUserId,path).toString();
		}else{
			return BaseResponse.setFailure("文件上传失败.").toString();
		}
	}	
	
	/**
	 * 
	     * 方法名:txtImport
	     * 功能描述:txt文本导入	 
	     * 创建者:冯子文
	     * 创建时间: 2018年5月31日 下午4:19:01 
		 * 更新者:冯子文  
		 * 更新时间: 2018年5月31日 下午4:19:01
	 */
	@ResponseBody
	@RequestMapping(value = "/channel/user/txtImport", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String txtImport(@Param("resourceAuthor")String resourceAuthor,@Param("channelUserId")String channelUserId,@Param("mobilTxtUrl")MultipartFile mobilTxtUrl) throws Exception{		
		if(!ResoureManageUtil.isPremitChannel(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(mobilTxtUrl==null)
			return BaseResponse.setFailure("请选择上传文件(文本).").toString();
		BaseResponse resultResponse=UploadUtil.uploadRealTxt(channelUserId, mobilTxtUrl);
		if(resultResponse.getStatus()==StatusSetting.getSuccessStatus()){
			//解析
			String path=resultResponse.getUploadPath();
			return channelUserService.txtImport(channelUserId,path).toString();
		}else{
			return BaseResponse.setFailure("文件上传失败.").toString();
		}
	}		
	/**
	 * 
	     * 方法名:batchImport
	     * 功能描述:批量导入	 
	     * 创建者:冯子文
	     * 创建时间: 2018年5月30日 上午10:21:33 
		 * 更新者:冯子文  
		 * 更新时间: 2018年5月30日 上午10:21:33
	 */
	@ResponseBody
	@RequestMapping(value = "/channel/user/batchImport", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String batchImport(@Param("resourceAuthor")String resourceAuthor,@Param("channelUserId")String channelUserId,@Param("mobilData")String mobilData)  throws Exception{
		if(!ResoureManageUtil.isPremitChannel(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {		
			return channelUserService.batchImport(channelUserId,mobilData).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}				
	}
	/**
	 * 
	     * 方法名:exportExcel
	     * 功能描述:导出为excel	 
	     * 创建者:冯子文
	     * 创建时间: 2018年5月30日 上午10:40:36 
		 * 更新者:冯子文  
		 * 更新时间: 2018年5月30日 上午10:40:36
	 */
	@ResponseBody
	@RequestMapping(value = "/channel/user/exportExcel", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String exportExcel(@Param("resourceAuthor")String resourceAuthor,@Param("channelUserId")String channelUserId,@Param("exportDate")Integer exportDate)  throws Exception{
		if(!ResoureManageUtil.isPremitChannel(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(exportDate==null)
			exportDate=DateUtil.formatCurrentDate();
		try {		
			return channelUserService.export(channelUserId,1,exportDate).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}	
	/**
	 * 
	     * 方法名:exportTxt
	     * 功能描述:导出为txt	 
	     * 创建者:冯子文
	     * 创建时间: 2018年5月30日 上午10:41:05 
		 * 更新者:冯子文  
		 * 更新时间: 2018年5月30日 上午10:41:05
	 */
	@ResponseBody
	@RequestMapping(value = "/channel/user/exportTxt", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String exportTxt(@Param("resourceAuthor")String resourceAuthor,@Param("channelUserId")String channelUserId,@Param("exportDate")Integer exportDate)  throws Exception{
		if(!ResoureManageUtil.isPremitChannel(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(exportDate==null)
			exportDate=DateUtil.formatCurrentDate();
		try {		
			return channelUserService.export(channelUserId,2,exportDate).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}	
	
	/**
	 * 
	     * 方法名:todayNumber
	     * 功能描述:今日导入量 
	     * 创建者:冯子文
	     * 创建时间: 2018年5月31日 下午4:32:08 
		 * 更新者:冯子文  
		 * 更新时间: 2018年5月31日 下午4:32:08
	 */
	@ResponseBody
	@RequestMapping(value = "/channel/user/todayNumber", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String todayNumber(@Param("resourceAuthor")String resourceAuthor,@Param("channelUserId")String channelUserId)  throws Exception{
		if(!ResoureManageUtil.isPremitChannel(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {		
			return channelUserService.todayNumber(channelUserId).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}	
	
	/**
	 * 
	     * 方法名:getUser
	     * 功能描述:获取用户名	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月1日 上午11:43:24 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月1日 上午11:43:24
	 */
	@ResponseBody
	@RequestMapping(value = "/channel/user/getUser", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String getUser(@Param("resourceAuthor")String resourceAuthor,@Param("channelUserId")String channelUserId)  throws Exception{
		if(!ResoureManageUtil.isPremitChannel(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {		
			return channelUserService.getUser(channelUserId).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}		
}
