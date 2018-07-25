package com.qim.loan.controller.Individual.distribute;  

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;

import com.qim.loan.service.distribute.DistributeRechargeRecordService;
import com.qim.loan.util.author.ResoureManageUtil;
import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.paramter.RequestPager;
/**
 *
 * 类名: DistributeRechargeRecordController
 * 描述: 充值记录表Controller层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年06月27日  17:59:24
 * 更新者: 冯子文
 * 更新时间: 2018年06月27日  17:59:24
 */
@Controller
@RequestMapping(value = "/distributeRechargeRecord")
public class DistributeRechargeRecordIndividualController{
	
	@Autowired
	@Qualifier("distributeRechargeRecordService")
	private DistributeRechargeRecordService distributeRechargeRecordService;

	/**
	 * 方法名：rechargeGoldcoin   2
	 * 描述: 金币充值接口   
	 * 创建者：zhouhua
	 * 创建时间：20180629
	 * 更新者：zhouhua
	 * 更新时间：20180629
	 * @param resourceAuthor
	 * @param consoleUserId
	 * @param goldcoinNumber
	 * @return
	 */
	@RequestMapping(value = "/rechargeGoldcoin",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	private String rechargeGoldcoin(@Param("resourceAuthor")String resourceAuthor,@Param("channelUserId")String channelUserId,@Param("goldcoinNumber")Integer goldcoinNumber) {
		if(!ResoureManageUtil.isPremitChannel(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			return distributeRechargeRecordService.rechargeGoldcoin(channelUserId,goldcoinNumber).toString();
		} catch (Exception e) {
             return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
	/**
	 * 方法名：getRechargeRecord 1
	 * 描述：充值记录分页
	 * 创建者：zhouhua
	 * 创建时间：20180629
	 * 更新者：zhouhua
	 * 更新时间：20180629
	 * @param resourceAuthor
	 * @param consoleUserId
	 * @return
	 */
	@RequestMapping(value = "/getRechargeRecord",method = RequestMethod.POST ,produces = "application/json;charset=utf-8")
	@ResponseBody
	private String getRechargeRecord(RequestPager requestPager,@Param("resourceAuthor")String resourceAuthor,@Param("channelUserId")String channelUserId,
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
			return distributeRechargeRecordService.getRechargeRecord(requestPager,channelUserId,startDate,endDate).toString();
		} catch (Exception e) {
			 return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	} 
	
	/**
	 * 方法名：getRechargeRecordDate
	 * 功能查询日，周，月，年
	 * 创建者：zhouhua
	 * 创建时间：20180629
	 * 更新者：zhouhua
	 * 更新时间：20180629
	 * @param resourceAuthor
	 * @param consoleUserId
	 * @return
	 */
	@RequestMapping(value = "/getRechargeRecordDate",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	private String getRechargeRecordDate(@Param("resourceAuthor")String resourceAuthor,@Param("channelUserId")String channelUserId) {
		if(!ResoureManageUtil.isPremitChannel(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			return distributeRechargeRecordService.getRechargeRecordDate(channelUserId).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
	/**
	 * 描述：统计图   1
     * 创建者：zhouhua
	 * 创建时间：20180629
	 * 更新者：zhouhua
	 * 更新时间：20180629
	 * @param resourceAuthor
	 * @param consoleUserId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequestMapping(value = "/getRechargeRecordDataGraph",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	private String getRechargeRecordDataGraph(@Param("resourceAuthor")String resourceAuthor,@Param("channelUserId")String channelUserId,
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
			return distributeRechargeRecordService.getRechargeRecordDataGraph(channelUserId,startDate,endDate).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	} 
	
	
}
