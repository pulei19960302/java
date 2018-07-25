package com.qim.loan.controller.Individual.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qim.loan.entity.employee.EmployeeUserLoginRecord;
import com.qim.loan.service.employee.EmployeeUserService;
import com.qim.loan.util.author.ResoureManageUtil;
import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.common.StringUtil;
import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.paramter.RequestPager;

/**
 *
 * 类名: EmployeeUserController 描述: 员工用户Controller层(公共) 创建者: 冯子文 创建时间: 2018年05月29日
 * 17:14:59 更新者: 冯子文 更新时间: 2018年05月29日 17:14:59
 */
@Controller
public class EmployeeUserIndividualController {
	@Autowired
	@Qualifier("employeeUserService")
	private EmployeeUserService employeeUserService;

	/**
	 * 方法名: login 功能描述: 根据用户名及密码登录 创建者: 冯子文 创建时间: 2018年05月29日 17:14:59 更新者: 冯子文
	 * 更新时间: 2018年05月29日 17:14:59
	 */
	@ResponseBody
	@RequestMapping(value = "/employee/user/login", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String login(EmployeeUserLoginRecord employeeUserLoginRecord, @Param("employeeUserName") String employeeUserName, @Param("employeeUserPwd") String employeeUserPwd) throws Exception {
		if (StringUtil.isNull(employeeUserName) || StringUtil.isNull(employeeUserPwd))
			return BaseResponse.setFailure("用户名或密码不能为空").toString();
		else
			try {
				return employeeUserService.login(employeeUserLoginRecord, employeeUserName, employeeUserPwd).toString();
			} catch (Exception e) {
				return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
			}
	}

	/**
	 * 
	 * 方法名:loginm 功能描述:部门一用户登录 创建者:冯子文 创建时间: 2018年6月2日 下午6:40:07 更新者:冯子文 更新时间:
	 * 2018年6月2日 下午6:40:07
	 */
	@ResponseBody
	@RequestMapping(value = "/employeem/user/loginm", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String loginm(EmployeeUserLoginRecord employeeUserLoginRecord, @Param("employeeUserName") String employeeUserName, @Param("employeeUserPwd") String employeeUserPwd) throws Exception {
		if (StringUtil.isNull(employeeUserName) || StringUtil.isNull(employeeUserPwd))
			return BaseResponse.setFailure("用户名或密码不能为空").toString();
		else
			try {
				return employeeUserService.loginm(employeeUserLoginRecord, employeeUserName, employeeUserPwd).toString();
			} catch (Exception e) {
				return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
			}
	}

	/**
	 * 
	 * 方法名:loginout 功能描述:注销 创建者:冯子文 创建时间: 2018年5月30日 上午10:17:28 更新者:冯子文 更新时间:
	 * 2018年5月30日 上午10:17:28
	 */
	@ResponseBody
	@RequestMapping(value = "/employee/user/loginout", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String loginout(@Param("resourceAuthor") String resourceAuthor, @Param("employeeUserId") String employeeUserId) throws Exception {
		if (!ResoureManageUtil.isPremitEmployeePC(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			return employeeUserService.loginout(employeeUserId).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}

	/**
	 * 
	 * 方法名:loginoutm 功能描述:员工一管理 创建者:冯子文 创建时间: 2018年6月2日 下午7:06:48 更新者:冯子文 更新时间:
	 * 2018年6月2日 下午7:06:48
	 */
	@ResponseBody
	@RequestMapping(value = "/employeem/user/loginoutm", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String loginoutm(@Param("resourceAuthor") String resourceAuthor, @Param("employeeUserId") String employeeUserId) throws Exception {
		if (!ResoureManageUtil.isPremitEmployeeMB(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			return employeeUserService.loginoutm(employeeUserId).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}

	/**
	 * 
	 * 方法名:updatePwd 功能描述:修改密码 创建者:冯子文 创建时间: 2018年5月29日 下午6:37:24 更新者:冯子文 更新时间:
	 * 2018年5月29日 下午6:37:24
	 */
	@ResponseBody
	@RequestMapping(value = "/employee/user/updatePwd", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String updatePwd(@Param("resourceAuthor") String resourceAuthor, @Param("employeeUserId") String employeeUserId, @Param("oldEmployeeUserPwd") String oldEmployeeUserPwd, @Param("employeeUserPwd") String employeeUserPwd) throws Exception {
		if (!ResoureManageUtil.isPremitEmployeePC(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if (StringUtil.isNull(employeeUserId) || StringUtil.isNull(oldEmployeeUserPwd) || StringUtil.isNull(employeeUserPwd) || StringUtil.equals(oldEmployeeUserPwd, employeeUserPwd))
			return BaseResponse.setFailure("用户旧密码或新密码不能为空,新旧密码不能相等").toString();
		else
			try {
				return employeeUserService.updatePwd(employeeUserId, oldEmployeeUserPwd, employeeUserPwd).toString();
			} catch (Exception e) {
				return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
			}
	}

	/**
	 * 
	 * 方法名:updatePwdm 功能描述:员工部门1修改密码 创建者:冯子文 创建时间: 2018年6月2日 下午7:07:53 更新者:冯子文 更新时间:
	 * 2018年6月2日 下午7:07:53
	 */
	@ResponseBody
	@RequestMapping(value = "/employeem/user/updatePwdm", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String updatePwdm(@Param("resourceAuthor") String resourceAuthor, @Param("employeeUserId") String employeeUserId, @Param("oldEmployeeUserPwd") String oldEmployeeUserPwd, @Param("employeeUserPwd") String employeeUserPwd) throws Exception {
		if (!ResoureManageUtil.isPremitEmployeeMB(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if (StringUtil.isNull(employeeUserId) || StringUtil.isNull(oldEmployeeUserPwd) || StringUtil.isNull(employeeUserPwd) || StringUtil.equals(oldEmployeeUserPwd, employeeUserPwd))
			return BaseResponse.setFailure("用户旧密码或新密码不能为空,新旧密码不能相等").toString();
		else
			try {
				return employeeUserService.updatePwdm(employeeUserId, oldEmployeeUserPwd, employeeUserPwd).toString();
			} catch (Exception e) {
				return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
			}
	}

	/**
	 * 
	 * 方法名:getUser 功能描述:获取用户详情 创建者:冯子文 创建时间: 2018年6月1日 下午6:13:54 更新者:冯子文 更新时间:
	 * 2018年6月1日 下午6:13:54
	 */
	@ResponseBody
	@RequestMapping(value = "/employee/user/getUser", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String getUser(@Param("resourceAuthor") String resourceAuthor, @Param("employeeUserId") String employeeUserId) throws Exception {
		if (!ResoureManageUtil.isPremitEmployeePC(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			return employeeUserService.getUser(employeeUserId).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	/**
	 * 
	 * 方法名:getClient 功能描述:获取客户信息列表(按时间顺序排列) 创建者:冯子文 创建时间: 2018年6月2日 下午2:08:48
	 * 更新者:冯子文 更新时间: 2018年6月2日 下午2:08:48
	 */
	@ResponseBody
	@RequestMapping(value = "/employee/user/getClientList", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String getClientList(@Param("resourceAuthor") String resourceAuthor, RequestPager requestPager, @Param("employeeUserId") String employeeUserId, @Param("telphoneNumber") String telphoneNumber, @Param("startDate") Integer startDate, @Param("endDate") Integer endDate, @Param("isHanle") Integer isHanle,@Param("realName")String realName) throws Exception {
		if (!ResoureManageUtil.isPremitEmployeePC(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if (isHanle == null)
			isHanle = 0;
		if (endDate == null) 
			endDate = DateUtil.formatCurrentDate();
		if (startDate == null)
			startDate = DateUtil.subtractWeek(endDate);
		try {
			return employeeUserService.getClientList(requestPager, employeeUserId, telphoneNumber, startDate, endDate, isHanle,realName).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}

	/**
	 * 
	 * 方法名:getClient 功能描述:获取客户资料详情 创建者:冯子文 创建时间: 2018年6月2日 下午2:45:07 更新者:冯子文 更新时间:
	 * 2018年6月2日 下午2:45:07
	 */
	@ResponseBody
	@RequestMapping(value = "/employee/user/getClient", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String getClient(@Param("resourceAuthor") String resourceAuthor, @Param("clientDataId") String clientDataId) throws Exception {
		if (!ResoureManageUtil.isPremitEmployeePC(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if (StringUtil.isNull(clientDataId))
			return BaseResponse.setFailure("请传入客户资料ID").toString();
		try {
			return employeeUserService.getClient(clientDataId).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}

	/**
	 * 
	 * 方法名:getClientMore 功能描述:获取详情上一页，下一页 创建者:冯子文 创建时间: 2018年6月2日 下午2:59:04 更新者:冯子文
	 * 更新时间: 2018年6月2日 下午2:59:04
	 */
	@ResponseBody
	@RequestMapping(value = "/employee/user/getClientMore", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String getClientMore(@Param("resourceAuthor") String resourceAuthor, @Param("currentPage") Integer currentPage, @Param("employeeUserId") String employeeUserId, @Param("isHanle") Integer isHanle) throws Exception {
		if (!ResoureManageUtil.isPremitEmployeePC(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if (isHanle == null)
			isHanle = 0;
		if (currentPage == null)
			currentPage = 1;
		RequestPager requestPager = new RequestPager(currentPage, 1);
		try {
			return employeeUserService.getClientMore(requestPager, employeeUserId, isHanle).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}

	/**
	 * 
	 * 方法名:handle 功能描述:处理本条 创建者:冯子文 创建时间: 2018年6月2日 下午3:01:29 更新者:冯子文 更新时间:
	 * 2018年6月2日 下午3:01:29
	 */
	@ResponseBody
	@RequestMapping(value = "/employee/user/handle", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String handle(@Param("resourceAuthor") String resourceAuthor, @Param("clientDataId") String clientDataId, @Param("employeeUserId") String employeeUserId, @Param("handleDescript") String handleDescript, @Param("employeeTwoUserNumber") String employeeTwoUserNumber) throws Exception {
		if (!ResoureManageUtil.isPremitEmployeePC(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if (StringUtil.isNull(clientDataId))
			return BaseResponse.setFailure("请传入客户资料ID").toString();
		if (StringUtil.isNull(employeeTwoUserNumber))
			return BaseResponse.setFailure("请审核组编号").toString();
		try {
			return employeeUserService.handle(clientDataId, employeeUserId, handleDescript,employeeTwoUserNumber).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}

	/**
	 * 
	 * 方法名:statics 功能描述:首页统计 创建者:冯子文 创建时间: 2018年6月2日 下午4:01:26 更新者:冯子文 更新时间:
	 * 2018年6月2日 下午4:01:26
	 */
	@ResponseBody
	@RequestMapping(value = "/employee/user/statics", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String statics(@Param("resourceAuthor") String resourceAuthor, @Param("employeeUserId") String employeeUserId) throws Exception {
		if (!ResoureManageUtil.isPremitEmployeePC(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			return employeeUserService.statics(employeeUserId).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}

	/**
	 * 
	 * 方法名:handleStatics 功能描述:今日处理量及未处理量 创建者:冯子文 创建时间: 2018年6月2日 下午4:05:03 更新者:冯子文
	 * 更新时间: 2018年6月2日 下午4:05:03
	 */
	@ResponseBody
	@RequestMapping(value = "/employee/user/handleStatics", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String handleStatics(@Param("resourceAuthor") String resourceAuthor, @Param("employeeUserId") String employeeUserId) throws Exception {
		if (!ResoureManageUtil.isPremitEmployeePC(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			return employeeUserService.handleStatics(employeeUserId).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}

	/**
	 * 
	 * 方法名:exportExcel 功能描述:选定日期导出 创建者:冯子文 创建时间: 2018年6月2日 下午4:37:53 更新者:冯子文 更新时间:
	 * 2018年6月2日 下午4:37:53
	 */
	@ResponseBody
	@RequestMapping(value = "/employee/user/exportExcel", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String exportExcel(@Param("resourceAuthor") String resourceAuthor, @Param("employeeUserId") String employeeUserId, @Param("exportDate") Integer exportDate) throws Exception {
		if (!ResoureManageUtil.isPremitEmployeePC(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if (exportDate == null)
			exportDate = DateUtil.formatCurrentDate();
		try {
			return employeeUserService.exportExcel(employeeUserId, exportDate).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}

	/**
	 * 
	 * 方法名:search 功能描述:搜索统计 创建者:冯子文 创建时间: 2018年5月30日 上午11:45:14 更新者:冯子文 更新时间:
	 * 2018年5月30日 上午11:45:14
	 */
	@ResponseBody
	@RequestMapping(value = "/employee/user/searchStatics", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String searchStatics(@Param("resourceAuthor") String resourceAuthor, @Param("employeeUserId") String employeeUserId, @Param("startDate") Integer startDate, @Param("endDate") Integer endDate) throws Exception {
		if (!ResoureManageUtil.isPremitEmployeePC(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if (endDate == null) 
			endDate = DateUtil.formatCurrentDate();
		if (startDate == null)
			startDate = DateUtil.subtractWeek(endDate);
		try {
			return employeeUserService.searchStatics(employeeUserId, startDate, endDate).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}

	/**
	 * 
	 * 方法名:staticsm 功能描述:员工一后台首页统计 创建者:冯子文 创建时间: 2018年6月2日 下午7:37:32 更新者:冯子文 更新时间:
	 * 2018年6月2日 下午7:37:32
	 */
	@ResponseBody
	@RequestMapping(value = "/employeem/user/staticsm", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String staticsm(@Param("resourceAuthor") String resourceAuthor, @Param("employeeUserId") String employeeUserId) throws Exception {
		if (!ResoureManageUtil.isPremitEmployeeMB(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			return employeeUserService.staticsm(employeeUserId).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}

	/**
	 * 
	 * 方法名:todayStaticsm 功能描述:今日统计 创建者:冯子文 创建时间: 2018年6月2日 下午7:43:31 更新者:冯子文 更新时间:
	 * 2018年6月2日 下午7:43:31
	 */
	@ResponseBody
	@RequestMapping(value = "/employeem/user/todayStaticsm", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String todayStaticsm(@Param("resourceAuthor") String resourceAuthor, @Param("employeeUserId") String employeeUserId) throws Exception {
		if (!ResoureManageUtil.isPremitEmployeeMB(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			return employeeUserService.todayStaticsm(employeeUserId).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/employeem/user/intention", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String intention(@Param("resourceAuthor") String resourceAuthor, @Param("clientDataId") String clientDataId, @Param("employeeUserId") String employeeUserId) throws Exception {
		if (!ResoureManageUtil.isPremitEmployeeMB(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			return employeeUserService.intention(employeeUserId, clientDataId).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/employeem/user/noIntention", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String noIntention(@Param("resourceAuthor") String resourceAuthor, @Param("clientDataId") String clientDataId, @Param("employeeUserId") String employeeUserId) throws Exception {
		if (!ResoureManageUtil.isPremitEmployeeMB(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			return employeeUserService.noIntention(employeeUserId, clientDataId).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/employeem/user/dial", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String dial(@Param("resourceAuthor") String resourceAuthor, @Param("clientDataId") String clientDataId, @Param("employeeUserId") String employeeUserId) throws Exception {
		if (!ResoureManageUtil.isPremitEmployeeMB(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			return employeeUserService.dial(employeeUserId, clientDataId).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
	//异常号
	@ResponseBody
	@RequestMapping(value = "/employeem/user/errNumber", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String errNumber(@Param("resourceAuthor") String resourceAuthor, @Param("clientDataId") String clientDataId, @Param("employeeUserId") String employeeUserId) throws Exception {
		if (!ResoureManageUtil.isPremitEmployeeMB(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			return employeeUserService.errNumber(employeeUserId, clientDataId).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/employeem/user/getClientMorem", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String getClientMorem(@Param("resourceAuthor") String resourceAuthor, @Param("currentPage") Integer currentPage, @Param("employeeUserId") String employeeUserId, @Param("isDial") Integer isDial) throws Exception {
		if (!ResoureManageUtil.isPremitEmployeeMB(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if (currentPage == null)
			currentPage = 1;
		RequestPager requestPager = new RequestPager(currentPage, 1);
		try {
			return employeeUserService.getClientMorem(requestPager, employeeUserId, isDial).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/employeem/user/searchStaticsm", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String searchStaticsm(@Param("resourceAuthor") String resourceAuthor, @Param("employeeUserId") String employeeUserId, @Param("stratDate") Integer startDate, @Param("endDate") Integer endDate, @Param("status") Integer status) throws Exception {
		if (!ResoureManageUtil.isPremitEmployeeMB(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if (endDate == null) 
			endDate = DateUtil.formatCurrentDate();
		if (startDate == null)
			startDate = DateUtil.subtractWeek(endDate);
		if (status == null)
			status = 0;
		try {
			return employeeUserService.searchStaticsm(employeeUserId, startDate, endDate, status).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
	/**
	 * 
	 * 方法名:getUser 功能描述:获取用户详情 创建者:冯子文 创建时间: 2018年6月1日 下午6:13:54 更新者:冯子文 更新时间:
	 * 2018年6月1日 下午6:13:54
	 */
	@ResponseBody
	@RequestMapping(value = "/employeem/user/getUserm", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String getUserm(@Param("resourceAuthor") String resourceAuthor, @Param("employeeUserId") String employeeUserId) throws Exception {
		if (!ResoureManageUtil.isPremitEmployeeMB(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			return employeeUserService.getUser(employeeUserId).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
	/**
	 * 功能描述：获取今日，本周，本月 统计留资量  接口还未使用
	 * @param resourceAuthor
	 * @param employeeUserId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/employeem/user/loanStaticsm",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	private String getStatisticsData(@Param("resourceAuthor") String resourceAuthor, @Param("employeeUserId") String employeeUserId) {
		if (!ResoureManageUtil.isPremitEmployeeMB(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			 return employeeUserService.loanStaticsm(employeeUserId).toString();
		} catch (Exception e) {
             return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/employeem/user/getPercentByYear",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	private String getPercentByYear(@Param("resourceAuthor") String resourceAuthor,@Param("employeeUserId") String employeeUserId) {
		if (!ResoureManageUtil.isPremitEmployeeMB(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {	    
			return employeeUserService.getPercentByYear(employeeUserId).toString();
		} catch (Exception e) {
             return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/employeem/user/getPercentByMonth",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	private String getPercentConversion(@Param("resourceAuthor") String resourceAuthor,@Param("employeeUserId") String employeeUserId) {
		if (!ResoureManageUtil.isPremitEmployeeMB(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		
		try { 
			return employeeUserService.getPercentByMonth(employeeUserId).toString();
		} catch (Exception e) {
             return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
		
	}

	/**
	 * 
	     * 方法名:conversionLoan
	     * 功能描述:转化率	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月15日 下午6:04:23 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月15日 下午6:04:23
	 */
	@ResponseBody
	@RequestMapping(value = "/employeem/user/conversionLoan",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	private String getConversionLoan(@Param("resourceAuthor") String resourceAuthor, @Param("employeeUserId") String employeeUserId) {
		if (!ResoureManageUtil.isPremitEmployeeMB(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			 return employeeUserService.conversionLoan(employeeUserId).toString();
	    }catch (Exception e) {
	    	e.printStackTrace();
            return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
	/**
	 * 
	     * 方法名:接通率
	     * 功能描述:	 
	     * 创建者:吕高照
	     * 创建时间: 2018年7月17日 下午2:00:42 
		 * 更新者:吕高照  
		 * 更新时间: 2018年7月17日 下午2:00:42
	 */
	@ResponseBody
	@RequestMapping(value = "/employeem/user/callCompletLoan",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	private String getCallCompletLoan(@Param("resourceAuthor") String resourceAuthor, @Param("employeeUserId") String employeeUserId) {
		if (!ResoureManageUtil.isPremitEmployeeMB(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			 return employeeUserService.callCompletLoan(employeeUserId).toString();
	    }catch (Exception e) {
	    	e.printStackTrace();
            return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
	/**
	 * 
	     * 方法名:异常号码率
	     * 功能描述:	 
	     * 创建者:吕高照
	     * 创建时间: 2018年7月17日 下午2:00:42 
		 * 更新者:吕高照  
		 * 更新时间: 2018年7月17日 下午2:00:42
	 */
	@ResponseBody
	@RequestMapping(value = "/employeem/user/errNumLoan",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	private String getErrNumLoan(@Param("resourceAuthor") String resourceAuthor, @Param("employeeUserId") String employeeUserId) {
		if (!ResoureManageUtil.isPremitEmployeeMB(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			 return employeeUserService.errNumLoan(employeeUserId).toString();
	    }catch (Exception e) {
	    	e.printStackTrace();
            return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	

	/**
	 * 
	     * 方法名:getMonth
	     * 功能描述:本月中每天	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月15日 下午7:24:13 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月15日 下午7:24:13
	 */
	@ResponseBody
	@RequestMapping(value = "/employeem/user/getMonth",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	public String getmonthByDay(@Param("resourceAuthor") String resourceAuthor, @Param("employeeUserId") String employeeUserId) {
		if (!ResoureManageUtil.isPremitEmployeeMB(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			 return employeeUserService.monthByDay(employeeUserId).toString();
	    }catch (Exception e) {
           return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}		
	}
	
	
}
