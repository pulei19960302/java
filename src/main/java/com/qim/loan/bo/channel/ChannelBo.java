package com.qim.loan.bo.channel;  

import com.qim.loan.entity.channel.Channel;

  
public class ChannelBo{  

	private Channel channel;
	//提交测试
	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public ChannelBo(){
		channel=new Channel();
	}
	
	public static Channel setChannelName(String channelName, String channelAbbreviate, Integer isOnline) {
		Channel channel=new Channel();
		channel.setChannelName(channelName);
		channel.setChannelAbbreviate(channelAbbreviate);
		channel.setIsOnline(isOnline);
		return channel;
	}
	
	
	
	

} 