package com.qim.loan.service.distribute;  

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.qim.loan.core.service.BaseService;
import com.qim.loan.entity.distribute.DistributeCurrencyRecord;
import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.paramter.RequestPager; 



/**
 *
 * 类名: DistributeCurrencyRecordService
 * 描述: 消费记录表Service层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年06月27日  17:59:24
 * 更新者: 冯子文
 * 更新时间: 2018年06月27日  17:59:24
 */
 
public interface DistributeCurrencyRecordService extends BaseService<DistributeCurrencyRecord> {

	BaseResponse getOrderClientData(RequestPager requestPager, String channelUserId, Integer startDate, Integer endDate) throws Exception;

	BaseResponse getCurrencyRecordDetail(RequestPager requestPager, String channelUserId, String distributeCurrencyRecordId)  throws Exception;

	HSSFWorkbook clientExcelImport(String channelUserId, String distributeCurrencyRecordId) throws Exception;

}
