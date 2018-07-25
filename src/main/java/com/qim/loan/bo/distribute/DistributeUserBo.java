package com.qim.loan.bo.distribute;  

import com.qim.loan.entity.distribute.DistributeUser;
import com.qim.loan.entity.employee.EmployeeUser;
import com.qim.loan.util.common.EncrypUtil;

  
public class DistributeUserBo{  

	private DistributeUser distributeUser;
	
	public DistributeUser getDistributeUser() {
		return distributeUser;
	}

	public void setDistributeUser(DistributeUser distributeUser) {
		this.distributeUser = distributeUser;
	}

	public DistributeUserBo(){
		distributeUser=new DistributeUser();
	}
	
	// 密码加密
	public static DistributeUser setLogin(String distributeUserName,String distributeUserPassword) {
		DistributeUser distributeUser = new DistributeUser();
		distributeUser.setDistributeUserName(distributeUserName);
		distributeUser.setDistributeUserPassword(EncrypUtil.Encrypt(distributeUserPassword));
		distributeUser.setIsOnline(0);
		return distributeUser;
	}
	
	public static DistributeUser updatePwd(String channelUserId, String distributeUserPassword) {		
		DistributeUser distributeUser = new DistributeUser();
		distributeUser.setId(channelUserId);
		distributeUser.setDistributeUserPassword(EncrypUtil.Encrypt(distributeUserPassword));
		return distributeUser;
	}
	
	public static void main(String[] args) {
		System.out.println(setLogin("zhangsan","123456").getDistributeUserPassword());
	}
	

} 