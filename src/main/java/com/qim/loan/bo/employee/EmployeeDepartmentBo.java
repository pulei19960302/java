package com.qim.loan.bo.employee;  

import com.qim.loan.entity.employee.EmployeeDepartment;

  
public class EmployeeDepartmentBo{  

	private EmployeeDepartment employeeDepartment;
	
	public EmployeeDepartment getEmployeeDepartment() {
		return employeeDepartment;
	}

	public void setEmployeeDepartment(EmployeeDepartment employeeDepartment) {
		this.employeeDepartment = employeeDepartment;
	}

	public EmployeeDepartmentBo(){
		employeeDepartment=new EmployeeDepartment();
	}

} 