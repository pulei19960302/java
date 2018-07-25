package com.qim.loan.bo.employee;  

import com.qim.loan.entity.employee.EmployeeUser;
import com.qim.loan.util.common.EncrypUtil;

  
public class EmployeeUserBo{  

	private EmployeeUser employeeUser;
	
	public EmployeeUser getEmployeeUser() {
		return employeeUser;
	}

	public void setEmployeeUser(EmployeeUser employeeUser) {
		this.employeeUser = employeeUser;
	}

	public EmployeeUserBo(){
		employeeUser=new EmployeeUser();
	}

	public static EmployeeUser setLogin(String employeeUserName, String employeeUserPwd) {		
		EmployeeUser employeeUser=new EmployeeUser();
		employeeUser.setEmployeeUserName(employeeUserName);
		employeeUser.setEmployeeUserPassword(EncrypUtil.Encrypt(employeeUserPwd));
		employeeUser.setIsOnline(0);//0：在线	
		return employeeUser;
	}
	
	public static EmployeeUser updatePwd(String channelUserId, String oldEmployeeUserrPwd) {		
		EmployeeUser employeeUser=new EmployeeUser();
		employeeUser.setId(channelUserId);
		employeeUser.setEmployeeUserPassword(EncrypUtil.Encrypt(oldEmployeeUserrPwd));
		return employeeUser;
	}
	
	public static void updatePwd(EmployeeUser employeeUser, String employeeUserPwd) {
		employeeUser.setEmployeeUserPassword(EncrypUtil.Encrypt(employeeUserPwd));
	}
	
	public static EmployeeUser setId(String employeeUserId) {
		EmployeeUser employeeUser=new EmployeeUser();
		employeeUser.setId(employeeUserId);	
		return employeeUser;
	}
	
	
	
	public static EmployeeUser setRetention() {
		EmployeeUser employeeUser=new EmployeeUser();
		return employeeUser;
	}
	
	
} 