package com.qim.loan.service.distribute;  

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.qim.loan.core.service.BaseService;
import com.qim.loan.entity.distribute.DistributeUser;
import com.qim.loan.entity.distribute.DistributeUserLoginRecord;
import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.paramter.RequestPager; 



/**
 *
 * 类名: DistributeUserService
 * 描述: 分发用户表Service层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年06月27日  17:59:24
 * 更新者: 冯子文
 * 更新时间: 2018年06月27日  17:59:24
 */
 
public interface DistributeUserService extends BaseService<DistributeUser> {

	BaseResponse login(DistributeUserLoginRecord distributeUserLoginRecord, String distributeUserName,
			String distributeUserPassword) throws Exception;

	BaseResponse getUserByGoldcoinNumber(String consoleUserId)throws Exception;

	BaseResponse consumeGoldcoin(Integer number)throws Exception;

	BaseResponse purchaseData(String Id, String purchaseNumber, String currencyNumber)throws Exception;

	BaseResponse getOrderClientData(RequestPager requestPager, String consoleUserId, Integer startDate, Integer endDate)throws Exception;

	BaseResponse getOrderClientData(RequestPager requestPager, String consoleUserId, String distributeCurrencyRecordId)throws Exception;

	HSSFWorkbook clientExcelImport(String consoleUserId, String distributeCurrencyRecordId)throws Exception;

	BaseResponse updateDistributeUserInfo(String consoleUserId, String distributeUserPassword,
			String newdistributeUserPassword, String realName)throws Exception;

	BaseResponse getDistributeUserNumber(String consoleUserId)throws Exception;

	BaseResponse distributeUserDateImport(RequestPager requestPager, String consoleUserId, Integer startDate,
			Integer endDate)throws Exception;

	BaseResponse loginout(String consoleUserId)throws Exception;


}
