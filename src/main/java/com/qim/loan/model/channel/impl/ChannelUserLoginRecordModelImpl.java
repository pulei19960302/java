package com.qim.loan.model.channel.impl;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.qim.loan.core.dao.BaseDao;
import com.qim.loan.core.model.impl.BaseModelImpl;
import com.qim.loan.dao.channel.ChannelUserLoginRecordDao;
import com.qim.loan.entity.channel.ChannelUserLoginRecord;
import com.qim.loan.model.channel.ChannelUserLoginRecordModel;
/**
 *
 * 类名: ChannelUserLoginRecordModel
 * 描述: 渠道用户登录记录Model层
 * 创建者: 冯子文
 * 创建时间: 2018年05月29日  17:14:59
 * 更新者: 冯子文
 * 更新时间: 2018年05月29日  17:14:59
 */
@Component("channelUserLoginRecordModel")
public class ChannelUserLoginRecordModelImpl extends BaseModelImpl<ChannelUserLoginRecord> implements ChannelUserLoginRecordModel{
    @Autowired
    @Qualifier("channelUserLoginRecordDao")
	private ChannelUserLoginRecordDao channelUserLoginRecordDao;  

	@Override
	public BaseDao<ChannelUserLoginRecord> getDao() {
		return channelUserLoginRecordDao;
	} 

    public ChannelUserLoginRecordModelImpl(){
    	setPhysicsKeyName("","id");
    }




} 