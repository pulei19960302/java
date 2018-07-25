package com.qim.loan.model.employee.impl;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.qim.loan.core.dao.BaseDao;
import com.qim.loan.core.model.impl.BaseModelImpl;
import com.qim.loan.dao.employee.EmployeeDepartmentDao;
import com.qim.loan.entity.employee.EmployeeDepartment;
import com.qim.loan.model.employee.EmployeeDepartmentModel;
/**
 *
 * 类名: EmployeeDepartmentModel
 * 描述: 员工部门Model层
 * 创建者: 冯子文
 * 创建时间: 2018年06月01日  17:17:42
 * 更新者: 冯子文
 * 更新时间: 2018年06月01日  17:17:42
 */
@Component("employeeDepartmentModel")
public class EmployeeDepartmentModelImpl extends BaseModelImpl<EmployeeDepartment> implements EmployeeDepartmentModel{
    @Autowired
    @Qualifier("employeeDepartmentDao")
	private EmployeeDepartmentDao employeeDepartmentDao;  

	@Override
	public BaseDao<EmployeeDepartment> getDao() {
		return employeeDepartmentDao;
	} 

    public EmployeeDepartmentModelImpl(){
    	setPhysicsKeyName("employeeDepartmentName","id");
    }




} 