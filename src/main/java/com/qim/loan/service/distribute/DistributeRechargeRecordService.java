package com.qim.loan.service.distribute;  

import com.qim.loan.core.service.BaseService;
import com.qim.loan.entity.distribute.DistributeRechargeRecord;
import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.paramter.RequestPager; 



/**
 *
 * 类名: DistributeRechargeRecordService
 * 描述: 充值记录表Service层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年06月27日  17:59:24
 * 更新者: 冯子文
 * 更新时间: 2018年06月27日  17:59:24
 */
 
public interface DistributeRechargeRecordService extends BaseService<DistributeRechargeRecord> {
	
	
	BaseResponse rechargeGoldcoin(String id, Integer goldcoinNumber) throws Exception;

	BaseResponse getRechargeRecord(RequestPager requestPager,String consoleUserId,Integer startDate,Integer endDate)throws Exception;

	BaseResponse getRechargeRecordDate(String consoleUserId)throws Exception;

	BaseResponse getRechargeRecordDataGraph(String consoleUserId,Integer startDate,Integer endDate)throws Exception;


}
