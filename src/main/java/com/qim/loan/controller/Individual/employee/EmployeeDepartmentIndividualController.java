package com.qim.loan.controller.Individual.employee;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qim.loan.service.employee.EmployeeDepartmentService;
/**
 *
 * 类名: EmployeeDepartmentController
 * 描述: 员工部门Controller层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年06月01日  17:17:42
 * 更新者: 冯子文
 * 更新时间: 2018年06月01日  17:17:42
 */
@Controller
@RequestMapping(value = "/web/employeeDepartment")
public class EmployeeDepartmentIndividualController{
	@Autowired
	@Qualifier("employeeDepartmentService")
	private EmployeeDepartmentService employeeDepartmentService;

	
	


}
