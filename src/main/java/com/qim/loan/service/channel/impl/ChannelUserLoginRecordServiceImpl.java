package com.qim.loan.service.channel.impl;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.qim.loan.core.model.BaseModel;
import com.qim.loan.core.service.impl.BaseServiceImpl;
import com.qim.loan.entity.channel.ChannelUserLoginRecord;
import com.qim.loan.model.channel.ChannelUserLoginRecordModel;
import com.qim.loan.service.channel.ChannelUserLoginRecordService; 



/**
 *
 * 类名: ChannelUserLoginRecordServiceImpl
 * 描述: 渠道用户登录记录ServiceImpl层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年05月29日  17:14:59
 * 更新者: 冯子文
 * 更新时间: 2018年05月29日  17:14:59
 */
@Service("channelUserLoginRecordService")
public class ChannelUserLoginRecordServiceImpl extends BaseServiceImpl<ChannelUserLoginRecord> implements ChannelUserLoginRecordService {

    @Autowired
    @Qualifier("channelUserLoginRecordModel")
	private ChannelUserLoginRecordModel channelUserLoginRecordModel;  

	@Override
	public BaseModel<ChannelUserLoginRecord> getModel() {
		return channelUserLoginRecordModel;
	} 



}
