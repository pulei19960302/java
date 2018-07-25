package com.qim.loan.service.employee;  

import com.qim.loan.core.service.BaseService;
import com.qim.loan.entity.employee.EmployeeUser;
import com.qim.loan.entity.employee.EmployeeUserLoginRecord;
import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.paramter.RequestPager; 



/**
 *
 * 类名: EmployeeUserService
 * 描述: 员工用户Service层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年05月29日  17:14:59
 * 更新者: 冯子文
 * 更新时间: 2018年05月29日  17:14:59
 */
 
public interface EmployeeUserService extends BaseService<EmployeeUser> {

	BaseResponse login(EmployeeUserLoginRecord employeeUserLoginRecord,String employeeUserName,String employeeUserPwd)  throws Exception;
	
	BaseResponse loginm(EmployeeUserLoginRecord employeeUserLoginRecord,String employeeUserName,String employeeUserPwd)  throws Exception;
	
	
	BaseResponse loginout(String employeeUserId)  throws Exception;
	
	BaseResponse loginoutm(String employeeUserId)  throws Exception;
	
	BaseResponse updatePwd(String employeeUserId,String oldEmployeeUserPwd,String employeeUserPwd)  throws Exception;
	
	BaseResponse updatePwdm(String employeeUserId,String oldEmployeeUserPwd,String employeeUserPwd)  throws Exception;
	
	
	BaseResponse getUser(String employeeUserId)  throws Exception;
	
	BaseResponse getClientList(RequestPager requestPager,String employeeUserId,String telphoneNumber,Integer startDate,Integer endDate,Integer isHanle,String realName)  throws Exception;
	
	BaseResponse getClient(String clientDataId)  throws Exception;
	
	BaseResponse getClientMore(RequestPager requestPager,String employeeUserId,Integer isHanle)  throws Exception;
	
	BaseResponse handle(String clientDataId,String employeeUserId,String handleDescript,String employeeTwoUserNumber)  throws Exception;
	
	BaseResponse statics(String employeeUserId)  throws Exception;
	
	BaseResponse handleStatics(String employeeUserId)  throws Exception;
	
	BaseResponse exportExcel(String employeeUserId,Integer exportDate) throws Exception;
	
	BaseResponse searchStatics(String employeeUserId,Integer startDate,Integer endDate)  throws Exception;
	
	BaseResponse staticsm(String employeeUserId)  throws Exception;
	
	BaseResponse todayStaticsm(String employeeUserId)  throws Exception;
	
	BaseResponse intention(String employeeUserId,String clientDataId)  throws Exception;
	
	BaseResponse noIntention(String employeeUserId,String clientDataId)  throws Exception;
	
	BaseResponse dial(String employeeUserId,String clientDataId)  throws Exception;
	
	BaseResponse getClientMorem(RequestPager requestPager,String employeeUserId,Integer isDial)  throws Exception;	
	
	BaseResponse searchStaticsm(String employeeUserId,Integer startDate,Integer endDate,Integer status)  throws Exception;

	BaseResponse loanStaticsm(String employeeUserId) throws Exception;
	
	BaseResponse getPercentByMonth(String employeeUserId) throws Exception;
	BaseResponse getPercentByYear(String employeeUserId) throws Exception;

	BaseResponse conversionLoan(String employeeUserId)throws Exception;

	BaseResponse updatePhone(String employeeUserId, String phone)throws Exception;

	BaseResponse monthByDay(String employeeUserId)throws Exception;

	BaseResponse errNumber(String employeeUserId, String clientDataId) throws Exception;

	BaseResponse errNumLoan(String employeeUserId) throws Exception;

	BaseResponse callCompletLoan(String employeeUserId) throws Exception;

}
