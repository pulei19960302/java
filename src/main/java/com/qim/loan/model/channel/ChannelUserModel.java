package com.qim.loan.model.channel;  

import com.qim.loan.core.model.BaseModel;
import com.qim.loan.entity.channel.ChannelUser;

public interface ChannelUserModel extends BaseModel<ChannelUser> {

	String getChannelId(String channelUserId) throws Exception;
	
	
}
