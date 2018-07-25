package com.qim.loan.dao.employee;  

import org.springframework.stereotype.Repository;

import com.qim.loan.core.dao.BaseDao;
import com.qim.loan.entity.employee.EmployeeDepartment; 
 
/**
 *
 * 类名: EmployeeDepartmentDao
 * 描述: 员工部门Dao层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年06月01日  17:17:42
 * 更新者: 冯子文
 * 更新时间: 2018年06月01日  17:17:42
 */
 
@Repository("employeeDepartmentDao")
public interface EmployeeDepartmentDao extends BaseDao<EmployeeDepartment>{


} 