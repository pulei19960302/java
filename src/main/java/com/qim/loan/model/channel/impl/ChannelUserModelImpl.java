package com.qim.loan.model.channel.impl;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.qim.loan.bo.channel.ChannelUserBo;
import com.qim.loan.core.dao.BaseDao;
import com.qim.loan.core.model.impl.BaseModelImpl;
import com.qim.loan.dao.channel.ChannelUserDao;
import com.qim.loan.entity.channel.ChannelUser;
import com.qim.loan.model.channel.ChannelUserModel;
/**
 *
 * 类名: ChannelUserModel
 * 描述: 渠道用户Model层
 * 创建者: 冯子文
 * 创建时间: 2018年05月29日  17:14:59
 * 更新者: 冯子文
 * 更新时间: 2018年05月29日  17:14:59
 */
@Component("channelUserModel")
public class ChannelUserModelImpl extends BaseModelImpl<ChannelUser> implements ChannelUserModel{
    @Autowired
    @Qualifier("channelUserDao")
	private ChannelUserDao channelUserDao;  

	@Override
	public BaseDao<ChannelUser> getDao() {
		return channelUserDao;
	} 

    public ChannelUserModelImpl(){
    	setPhysicsKeyName("channelId,channelUserName","id");
    }

	@Override
	public String getChannelId(String channelUserId) throws Exception {
		ChannelUser channelUser=super.getOne(ChannelUserBo.setId(channelUserId));
		if(channelUser==null)
			return "";
		else
			return channelUser.getChannelId();
	}




} 