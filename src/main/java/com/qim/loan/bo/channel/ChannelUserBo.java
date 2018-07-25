package com.qim.loan.bo.channel;  


import com.qim.loan.entity.channel.ChannelUser;
import com.qim.loan.util.common.EncrypUtil;
import com.qim.loan.util.common.StringUtil;

  
public class ChannelUserBo{  

	private ChannelUser channelUser;
	
	public ChannelUser getChannelUser() {
		return channelUser;
	}

	public void setChannelUser(ChannelUser channelUser) {
		this.channelUser = channelUser;
	}

	public ChannelUserBo(){
		channelUser=new ChannelUser();
	}
	
	public static ChannelUser setLogin(String channelUserName, String channelUserPwd) {		
		ChannelUser channelUser=new ChannelUser();
		channelUser.setChannelUserName(channelUserName);
		channelUser.setChannelUserPwd(EncrypUtil.Encrypt(channelUserPwd));
		channelUser.setIsOnline(0);//0在线
		return channelUser;
	}
	
	public static ChannelUser updatePwd(String channelUserId, String oldChannelUserPwd) {		
		ChannelUser channelUser=new ChannelUser();
		channelUser.setId(channelUserId);
		channelUser.setChannelUserPwd(EncrypUtil.Encrypt(oldChannelUserPwd));
		return channelUser;
	}
	
	public static void updatePwd(ChannelUser channelUser, String channelUserPwd) {
		channelUser.setChannelUserPwd(EncrypUtil.Encrypt(channelUserPwd));
	}
	
	public static ChannelUser setId(String channelUserId) {
		ChannelUser channelUser=new ChannelUser();
		channelUser.setId(channelUserId);	
		return channelUser;
	}
	
	public static ChannelUser setChannelId(String channelId) {
		ChannelUser channelUser=new ChannelUser();
		channelUser.setChannelId(channelId);
		channelUser.setIsOnline(1);
		return channelUser;
	}	
	

	public static ChannelUser setChannelId(String channelId,String channelUserName) {
		ChannelUser channelUser=new ChannelUser();
		channelUser.setChannelId(channelId);
		if(StringUtil.isNotNull(channelUserName))
			channelUser.setChannelUserName(channelUserName);
		return channelUser;		
	}	
	
	

} 