package com.qim.loan.service.employee.impl;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.qim.loan.core.model.BaseModel;
import com.qim.loan.core.service.impl.BaseServiceImpl;
import com.qim.loan.entity.employee.EmployeeUserLoginRecord;
import com.qim.loan.model.employee.EmployeeUserLoginRecordModel;
import com.qim.loan.service.employee.EmployeeUserLoginRecordService; 



/**
 *
 * 类名: EmployeeUserLoginRecordServiceImpl
 * 描述: 员工用户登录记录ServiceImpl层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年05月29日  17:14:59
 * 更新者: 冯子文
 * 更新时间: 2018年05月29日  17:14:59
 */
@Service("employeeUserLoginRecordService")
public class EmployeeUserLoginRecordServiceImpl extends BaseServiceImpl<EmployeeUserLoginRecord> implements EmployeeUserLoginRecordService {

    @Autowired
    @Qualifier("employeeUserLoginRecordModel")
	private EmployeeUserLoginRecordModel employeeUserLoginRecordModel;  

	@Override
	public BaseModel<EmployeeUserLoginRecord> getModel() {
		return employeeUserLoginRecordModel;
	} 



}
