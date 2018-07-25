package com.qim.loan.service.employee.impl;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.qim.loan.core.model.BaseModel;
import com.qim.loan.core.service.impl.BaseServiceImpl;
import com.qim.loan.entity.employee.EmployeeDepartment;
import com.qim.loan.model.employee.EmployeeDepartmentModel;
import com.qim.loan.service.employee.EmployeeDepartmentService;
import com.qim.loan.util.paramter.BaseResponse; 



/**
 *
 * 类名: EmployeeDepartmentServiceImpl
 * 描述: 员工部门ServiceImpl层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年06月01日  17:17:42
 * 更新者: 冯子文
 * 更新时间: 2018年06月01日  17:17:42
 */
@Service("employeeDepartmentService")
public class EmployeeDepartmentServiceImpl extends BaseServiceImpl<EmployeeDepartment> implements EmployeeDepartmentService {

    @Autowired
    @Qualifier("employeeDepartmentModel")
	private EmployeeDepartmentModel employeeDepartmentModel;  

	@Override
	public BaseModel<EmployeeDepartment> getModel() {
		return employeeDepartmentModel;
	}

	



}
