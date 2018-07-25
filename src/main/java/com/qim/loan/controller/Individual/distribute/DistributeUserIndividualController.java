package com.qim.loan.controller.Individual.distribute;  

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;

import com.qim.loan.entity.distribute.DistributeUser;
import com.qim.loan.entity.distribute.DistributeUserLoginRecord;
import com.qim.loan.service.distribute.DistributeUserService;
import com.qim.loan.util.author.ResoureManageUtil;
import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.common.StringUtil;
import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.paramter.RequestPager;
import com.sun.research.ws.wadl.Request;
/**
 *
 * 类名: DistributeUserController
 * 描述: 分发用户表Controller层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年06月27日  17:59:24
 * 更新者: 冯子文
 * 更新时间: 2018年06月27日  17:59:24
 */
@Controller
@RequestMapping(value = "/distributeUser")
public class DistributeUserIndividualController{
	
	@Autowired
	@Qualifier("distributeUserService")
	private DistributeUserService distributeUserService;
	
	/**
	 * 方法名：login   0
	 * 描述：登录
	 * 创建者：zhouhua
	 * 创建时间：20180629
	 * 更新者：zhouhua
	 * 更新时间：20180629
	 * @param distributeUserLoginRecord
	 * @param distributeUserName
	 * @param distributeUserPassword
	 * @return
	 */
	@RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	private String login(DistributeUserLoginRecord distributeUserLoginRecord,@Param("distributeUserName") String distributeUserName,@Param("distributeUserPassword")String distributeUserPassword) {
		if(StringUtil.isNull(distributeUserName) || StringUtil.isNull(distributeUserPassword))
			return BaseResponse.setFailure("用户名或密码不能为空").toString();
		else
			 try {
				return distributeUserService.login(distributeUserLoginRecord,distributeUserName,distributeUserPassword).toString();
			} catch (Exception e) {
				return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
			}
	}
	
	/**
	 * 方法名：loginout   0
	 * 描述：注销
	 * 创建者：zhouhua
	 * 创建时间：20180629
	 * 更新者：zhouhua
	 * 更新时间：20180629
	 * @param resourceAuthor
	 * @param consoleUserId
	 * @return
	 */
	@RequestMapping(value = "/loginout",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	private String loginout(@Param("resourceAuthor")String resourceAuthor,@Param("channelUserId")String channelUserId) {
		if(!ResoureManageUtil.isPremitChannel(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			return distributeUserService.loginout(channelUserId).toString();
		} catch (Exception e) {
           return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
	/**
	 * 方法名：updateDistributeUserInfo 0
	 * 描述：用户修改密码
	 * 创建者：zhouhua
	 * 创建时间：20180629
	 * 更新者：zhouhua
	 * 更新时间：20180629
	 * @param resourceAuthor
	 * @param consoleUserId
	 * @param distributeUser
	 * @return
	 */
	@RequestMapping(value = "/updateDistributeUserInfo",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	private String updateDistributeUserInfo(@Param("resourceAuthor")String resourceAuthor,@Param("channelUserId")String channelUserId,
			@Param("distributeUserPassword")String distributeUserPassword, @Param("newdistributeUserPassword") String newdistributeUserPassword,@Param("realName")String realName) {
		//鉴权错误
		if(!ResoureManageUtil.isPremitChannel(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(StringUtil.isNull(distributeUserPassword) || StringUtil.isNull(newdistributeUserPassword) || StringUtil.isNull(realName))
			return BaseResponse.setFailure("请填写全部信息").toString();
		try {
			return distributeUserService.updateDistributeUserInfo(channelUserId,distributeUserPassword,newdistributeUserPassword,realName).toString();
		} catch (Exception e) {
			 return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
	/**
	 * 描述：获取用户账号  0
	 * 创建者：zhouhua
	 * 创建时间：20180629
	 * 更新者：zhouhua
	 * 更新时间：20180629
	 * @param resourceAuthor
	 * @param consoleUserId
	 * @return
	 */
	@RequestMapping(value = "/getDistributeUserNumber",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	private String getDistributeUserNumber(@Param("resourceAuthor")String resourceAuthor,@Param("channelUserId")String channelUserId) {
		if(!ResoureManageUtil.isPremitChannel(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			return distributeUserService.getDistributeUserNumber(channelUserId).toString();
		} catch (Exception e) {
			 return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
		
	}
	
	
	/**
	 * 描述： 获取持有的金币数  0
	 * 创建者：zhouhua
	 * 创建时间：20180629
	 * 更新者：zhouhua
	 * 更新时间：20180629
	 * @param resourceAuthor
	 * @param consoleUserId
	 * @return
	 */
	@RequestMapping(value = "/getUserByGoldcoinNumber",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	private String getUserByGoldcoinNumber(@Param("resourceAuthor")String resourceAuthor,@Param("channelUserId")String channelUserId) {
		if(!ResoureManageUtil.isPremitChannel(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			return distributeUserService.getUserByGoldcoinNumber(channelUserId).toString();
		} catch (Exception e) {
          return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	} 
	
	
	/**
	 * 描述：计算消费多少金币  （作废）
	 * 创建者：zhouhua
	 * 创建时间：20180629
	 * 更新者：zhouhua
	 * 更新时间：20180629
	 * @param resourceAuthor
	 * @param consoleUserId
	 * @return
	 */
	@RequestMapping(value = "/consumeGoldcoin",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	private String consumeGoldcoin(@Param("resourceAuthor")String resourceAuthor,@Param("number")Integer number) {
		if(!ResoureManageUtil.isPremitChannel(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			return distributeUserService.consumeGoldcoin(number).toString();
		} catch (Exception e) {
			 return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
		
	}
	
	/**
	 * 描述：购买数据
	 * 创建者：zhouhua
	 * 创建时间：20180629
	 * 更新者：zhouhua
	 * 更新时间：20180629
	 * @param resourceAuthor
	 * @param consoleUserId
	 * @param purchaseNumber
	 * @param currencyNumber
	 * @return
	 */
	@RequestMapping(value = "/purchaseData",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	private String purchaseData(@Param("resourceAuthor")String resourceAuthor,@Param("channelUserId")String channelUserId,
			@Param("purchaseNumber")String purchaseNumber,@Param("currencyNumber")String currencyNumber) {
		if(!ResoureManageUtil.isPremitChannel(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			return distributeUserService.purchaseData(channelUserId,purchaseNumber,currencyNumber).toString();
		} catch (Exception e) {
			 return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
	/**
	 * 功能：购买详情页
	 * 开发者：zhouhua
	 * 时间：20180628
	 * @param requestPager
	 * @param resourceAuthor
	 * @param consoleUserId
	 * @param distributeCurrencyRecordId
	 * @return
	 */
	//功能错误
	@RequestMapping(value = "/getOrderClientData",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	private String getOrderClientData(RequestPager requestPager,@Param("resourceAuthor")String resourceAuthor,@Param("channelUserId")String channelUserId,@Param("distributeCurrencyRecordId")String distributeCurrencyRecordId) {
		if(!ResoureManageUtil.isPremitChannel(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		
		try {
			return distributeUserService.getOrderClientData(requestPager, channelUserId, distributeCurrencyRecordId).toString();
		} catch (Exception e) {
			 return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
	
	/**
	 * 数据导出列表   0
	 * 开发者：zhouhua
	 * 时间：20180627
	 * @param requestPager
	 * @param resourceAuthor
	 * @param consoleUserId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequestMapping(value = "/distributeUserDateImport",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	private String distributeUserDateImport(RequestPager requestPager,@Param("resourceAuthor")String resourceAuthor,@Param("channelUserId")String channelUserId,
			@Param("startDate")Integer startDate,@Param("endDate")Integer endDate) {
		if(!ResoureManageUtil.isPremitChannel(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(startDate == null) {
			startDate = DateUtil.getRealWeek();
		}
		if(endDate == null) {
			endDate = DateUtil.formatCurrentDate();
		}
		try {
			return distributeUserService.distributeUserDateImport(requestPager,channelUserId,startDate,endDate).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
}
