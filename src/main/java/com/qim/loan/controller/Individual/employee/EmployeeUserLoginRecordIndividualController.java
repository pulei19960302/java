package com.qim.loan.controller.Individual.employee;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qim.loan.service.employee.EmployeeUserLoginRecordService;
/**
 *
 * 类名: EmployeeUserLoginRecordController
 * 描述: 员工用户登录记录Controller层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年05月29日  17:14:59
 * 更新者: 冯子文
 * 更新时间: 2018年05月29日  17:14:59
 */
@Controller
@RequestMapping(value = "/web/employeeUserLoginRecord")
public class EmployeeUserLoginRecordIndividualController{
	@Autowired
	@Qualifier("employeeUserLoginRecordService")
	private EmployeeUserLoginRecordService employeeUserLoginRecordService;




}
