package com.qim.loan.bo.console;  

import com.qim.loan.entity.console.ConsoleUser;
import com.qim.loan.util.common.EncrypUtil;

  
public class ConsoleUserBo{  

	private ConsoleUser consoleUser;
	
	public ConsoleUser getConsoleUser() {
		return consoleUser;
	}

	public void setConsoleUser(ConsoleUser consoleUser) {
		this.consoleUser = consoleUser;
	}

	public ConsoleUserBo(){
		consoleUser=new ConsoleUser();
	}
	
	public static ConsoleUser setLogin(String consoleUserName, String consoleUserPwd) {		
		ConsoleUser consoleUser=new ConsoleUser();
		consoleUser.setConsoleUserName(consoleUserName);
		consoleUser.setConsoleUserPassword(EncrypUtil.Encrypt(consoleUserPwd));
		consoleUser.setIsOnline(0);//0：在线
		return consoleUser;
	}
	
	
	public static ConsoleUser updatePwd(String consoleUserId, String oldConsoleUserPwd) {		
		ConsoleUser consoleUser=new ConsoleUser();
		consoleUser.setId(consoleUserId);
		consoleUser.setConsoleUserPassword(EncrypUtil.Encrypt(oldConsoleUserPwd));
		return consoleUser;
	}
	
	public static void updatePwd(ConsoleUser consoleUser, String consoleUserPwd) {
		consoleUser.setConsoleUserPassword(EncrypUtil.Encrypt(consoleUserPwd));
	}
	
	
	
	

} 