package com.qim.loan.service.console;  

import com.qim.loan.core.service.BaseService;
import com.qim.loan.entity.channel.Channel;
import com.qim.loan.entity.channel.ChannelUser;
import com.qim.loan.entity.console.ConsoleUser;
import com.qim.loan.entity.console.ConsoleUserLoginRecord;
import com.qim.loan.entity.employee.EmployeeDepartment;
import com.qim.loan.entity.employee.EmployeeUser;
import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.paramter.RequestPager; 



/**
 *
 * 类名: ConsoleUserService
 * 描述: 总台用户Service层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年05月29日  17:14:59
 * 更新者: 冯子文
 * 更新时间: 2018年05月29日  17:14:59
 */
 
public interface ConsoleUserService extends BaseService<ConsoleUser> {

	BaseResponse login(ConsoleUserLoginRecord consoleUserLoginRecord,String consoleUserName,String consoleUserPassword)  throws Exception;
	
	BaseResponse loginout(String consoleUserId)  throws Exception;
	
	BaseResponse updatePwd(String consoleUserId,String oldConsoleUserPwd,String consoleUserPwd)  throws Exception;
	
	BaseResponse addConsoleUser(ConsoleUser consoleUser)  throws Exception;
	
	BaseResponse updateConsoleUser(ConsoleUser consoleUser)  throws Exception;
	
	BaseResponse updateConsoleUserPwd(String id,String consoleUserPassword)  throws Exception;
	
	BaseResponse getConsoleUserList(RequestPager requestPager,String consoleUserName,Integer isOnline)  throws Exception;
	
	BaseResponse getChannelList() throws Exception;
	
	BaseResponse getChannelPage(RequestPager requestPager,String channelId,Integer isOnline)  throws Exception;
	
	BaseResponse addChannel(String channelName,String channelAbbreviate,Integer isOnline) throws Exception;
	
	BaseResponse updateChannel(Channel channel)  throws Exception;
	
	BaseResponse addChannelUser(ChannelUser channelUser)  throws Exception;
	
	BaseResponse updateChannelUser(ChannelUser channelUser)  throws Exception;
	
	BaseResponse updateChannelUserPwd(String channelUserId,String channelUserPwd,Integer isOnline)  throws Exception;
	
	BaseResponse getChannelUserPage(RequestPager requestPager,String channelId,String channelUserName)  throws Exception;
	
	BaseResponse getUserList(String channelId)  throws Exception;
	
	BaseResponse staticsByUser(Integer startDate,Integer endDate,String channelId,String channelUserId)  throws Exception;
	
	BaseResponse getDepartPage(RequestPager requestPager,Integer isOnline)  throws Exception;
	
	BaseResponse addDepart(EmployeeDepartment employeeDepartment)  throws Exception;
	
	BaseResponse updateDepart(EmployeeDepartment employeeDepartment)  throws Exception;
	
	BaseResponse getEmployeePage(RequestPager requestPager,String employeeDepartmentId)  throws Exception;
	
	BaseResponse addEmployee(EmployeeUser employeeUser)  throws Exception;
	
	BaseResponse updateEmployee(EmployeeUser employeeUser)  throws Exception;
	
	BaseResponse updateEmployeePwd(String id,String employeeUserPassword)  throws Exception;
	
	BaseResponse getEmployeeList(String employeeDepartmentId)  throws Exception;	
	
	BaseResponse statics(Integer startDate,Integer endDate,String employeeDepartmentId,String employeeUserId)  throws Exception;

	BaseResponse totalQuantity()throws Exception;

	BaseResponse DistributeNumber()throws Exception;

	BaseResponse employeeBranchClientData(Integer distributeNumber,Integer peopleNumber)throws Exception;

	BaseResponse employeeAppointClientData(String employeeId,Integer distributeNumber)throws Exception;

	BaseResponse employeeData(RequestPager requestPager,String employeeName)throws Exception;

	BaseResponse distributeNumber()throws Exception;
	
	BaseResponse employeeOneStatics(Integer timeSpan,Integer startDate,Integer endDate,Integer sort)throws Exception;
	
	BaseResponse employeeTwoStatics(Integer timeSpan,Integer startDate,Integer endDate)throws Exception;
	
}
