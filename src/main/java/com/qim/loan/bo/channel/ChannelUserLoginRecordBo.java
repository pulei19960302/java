package com.qim.loan.bo.channel;  

import com.qim.loan.entity.channel.ChannelUserLoginRecord;

  
public class ChannelUserLoginRecordBo{  

	private ChannelUserLoginRecord channelUserLoginRecord;
	
	public ChannelUserLoginRecord getChannelUserLoginRecord() {
		return channelUserLoginRecord;
	}

	public void setChannelUserLoginRecord(ChannelUserLoginRecord channelUserLoginRecord) {
		this.channelUserLoginRecord = channelUserLoginRecord;
	}

	public ChannelUserLoginRecordBo(){
		channelUserLoginRecord=new ChannelUserLoginRecord();
	}

} 