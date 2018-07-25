package com.qim.loan.service.channel;  

import com.qim.loan.core.service.BaseService;
import com.qim.loan.entity.channel.ChannelUser;
import com.qim.loan.entity.channel.ChannelUserLoginRecord;
import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.paramter.RequestPager; 



/**
 *
 * 类名: ChannelUserService
 * 描述: 渠道用户Service层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年05月29日  17:14:59
 * 更新者: 冯子文
 * 更新时间: 2018年05月29日  17:14:59
 */
 
public interface ChannelUserService extends BaseService<ChannelUser> {

	BaseResponse login(ChannelUserLoginRecord channelUserLoginRecord,String channelUserName,String channelUserPwd)  throws Exception;
	
	BaseResponse updatePwd(String channelUserId,String oldChannelUserPwd,String channelUserPwd)  throws Exception;
	
	BaseResponse statics(String channelUserId,Integer startDate,Integer endDate)  throws Exception;
		
	BaseResponse index(String channelUserId)  throws Exception;
	
	BaseResponse loginout(String channelUserId)  throws Exception;
	
	BaseResponse search(RequestPager requestPager,String channelUserId,Integer startDate,Integer endDate)  throws Exception;
	
	BaseResponse batchImport(String channelUserId,String data)  throws Exception;
	
	BaseResponse export(String channelUserId,Integer type,Integer exportDate)  throws Exception;
	
	BaseResponse excelImport(String channelUserId,String path)  throws Exception;
	
	BaseResponse txtImport(String channelUserId,String path)  throws Exception;
	
	BaseResponse todayNumber(String channelUserId)  throws Exception;
	
	BaseResponse getUser(String channelUserId)  throws Exception;

	BaseResponse excelImportPhoneName(String channelUserId, String path)throws Exception;

	BaseResponse excelImportPhoneAndName(String channelUserId, String path)throws Exception;
	
}
