package com.qim.loan.service.channel.impl;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.qim.loan.core.model.BaseModel;
import com.qim.loan.core.service.impl.BaseServiceImpl;
import com.qim.loan.entity.channel.Channel;
import com.qim.loan.model.channel.ChannelModel;
import com.qim.loan.service.channel.ChannelService; 



/**
 *
 * 类名: ChannelServiceImpl
 * 描述: 渠道ServiceImpl层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年05月29日  17:14:59
 * 更新者: 冯子文
 * 更新时间: 2018年05月29日  17:14:59
 */
@Service("channelService")
public class ChannelServiceImpl extends BaseServiceImpl<Channel> implements ChannelService {

    @Autowired
    @Qualifier("channelModel")
	private ChannelModel channelModel;  

	@Override
	public BaseModel<Channel> getModel() {
		return channelModel;
	} 



}
