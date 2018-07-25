package com.qim.loan.model.channel.impl;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.qim.loan.core.dao.BaseDao;
import com.qim.loan.core.model.impl.BaseModelImpl;
import com.qim.loan.dao.channel.ChannelDao;
import com.qim.loan.entity.channel.Channel;
import com.qim.loan.model.channel.ChannelModel;
/**
 *
 * 类名: ChannelModel
 * 描述: 渠道Model层
 * 创建者: 冯子文
 * 创建时间: 2018年05月29日  17:14:59
 * 更新者: 冯子文
 * 更新时间: 2018年05月29日  17:14:59
 */
@Component("channelModel")
public class ChannelModelImpl extends BaseModelImpl<Channel> implements ChannelModel{
    @Autowired
    @Qualifier("channelDao")
	private ChannelDao channelDao;  

	@Override
	public BaseDao<Channel> getDao() {
		return channelDao;
	} 

    public ChannelModelImpl(){
    	setPhysicsKeyName("channelAbbreviate","id");
    }




} 