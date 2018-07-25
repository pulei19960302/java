package com.qim.loan.model.employee.impl;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.qim.loan.core.dao.BaseDao;
import com.qim.loan.core.model.impl.BaseModelImpl;
import com.qim.loan.dao.employee.EmployeeUserLoginRecordDao;
import com.qim.loan.entity.employee.EmployeeUserLoginRecord;
import com.qim.loan.model.employee.EmployeeUserLoginRecordModel;
/**
 *
 * 类名: EmployeeUserLoginRecordModel
 * 描述: 员工用户登录记录Model层
 * 创建者: 冯子文
 * 创建时间: 2018年05月29日  17:14:59
 * 更新者: 冯子文
 * 更新时间: 2018年05月29日  17:14:59
 */
@Component("employeeUserLoginRecordModel")
public class EmployeeUserLoginRecordModelImpl extends BaseModelImpl<EmployeeUserLoginRecord> implements EmployeeUserLoginRecordModel{
    @Autowired
    @Qualifier("employeeUserLoginRecordDao")
	private EmployeeUserLoginRecordDao employeeUserLoginRecordDao;  

	@Override
	public BaseDao<EmployeeUserLoginRecord> getDao() {
		return employeeUserLoginRecordDao;
	} 

    public EmployeeUserLoginRecordModelImpl(){
    	setPhysicsKeyName("","id");
    }




} 