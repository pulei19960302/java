package com.qim.loan.model.employee.impl;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.qim.loan.core.dao.BaseDao;
import com.qim.loan.core.model.impl.BaseModelImpl;
import com.qim.loan.dao.employee.EmployeeUserDao;
import com.qim.loan.entity.employee.EmployeeUser;
import com.qim.loan.model.employee.EmployeeUserModel;
/**
 *
 * 类名: EmployeeUserModel
 * 描述: 员工用户Model层
 * 创建者: 冯子文
 * 创建时间: 2018年05月29日  17:14:59
 * 更新者: 冯子文
 * 更新时间: 2018年05月29日  17:14:59
 */
@Component("employeeUserModel")
public class EmployeeUserModelImpl extends BaseModelImpl<EmployeeUser> implements EmployeeUserModel{
    @Autowired
    @Qualifier("employeeUserDao")
	private EmployeeUserDao employeeUserDao;  

	@Override
	public BaseDao<EmployeeUser> getDao() {
		return employeeUserDao;
	} 

    public EmployeeUserModelImpl(){
    	setPhysicsKeyName("employeeUserAccount","id");
    }




} 