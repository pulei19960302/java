package com.qim.loan.dao.employee;  

import org.springframework.stereotype.Repository;

import com.qim.loan.core.dao.BaseDao;
import com.qim.loan.entity.employee.EmployeeUser; 
 
/**
 *
 * 类名: EmployeeUserDao
 * 描述: 员工用户Dao层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年05月29日  17:14:59
 * 更新者: 冯子文
 * 更新时间: 2018年05月29日  17:14:59
 */
 
@Repository("employeeUserDao")
public interface EmployeeUserDao extends BaseDao<EmployeeUser>{


} 