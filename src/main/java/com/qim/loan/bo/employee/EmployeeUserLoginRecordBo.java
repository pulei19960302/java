package com.qim.loan.bo.employee;  

import com.qim.loan.entity.employee.EmployeeUserLoginRecord;

  
public class EmployeeUserLoginRecordBo{  

	private EmployeeUserLoginRecord employeeUserLoginRecord;
	
	public EmployeeUserLoginRecord getEmployeeUserLoginRecord() {
		return employeeUserLoginRecord;
	}

	public void setEmployeeUserLoginRecord(EmployeeUserLoginRecord employeeUserLoginRecord) {
		this.employeeUserLoginRecord = employeeUserLoginRecord;
	}

	public EmployeeUserLoginRecordBo(){
		employeeUserLoginRecord=new EmployeeUserLoginRecord();
	}

} 