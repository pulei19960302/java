package com.qim.loan.controller.Individual.console;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qim.loan.entity.channel.Channel;
import com.qim.loan.entity.channel.ChannelUser;
import com.qim.loan.entity.console.ConsoleUser;
import com.qim.loan.entity.console.ConsoleUserLoginRecord;
import com.qim.loan.entity.employee.EmployeeDepartment;
import com.qim.loan.entity.employee.EmployeeUser;
import com.qim.loan.service.client.ClientDataService;
import com.qim.loan.service.console.ConsoleUserService;
import com.qim.loan.util.author.ResoureManageUtil;
import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.common.StringUtil;
import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.paramter.RequestPager;
/**
 *
 * 类名: ConsoleUserController
 * 描述: 总台用户Controller层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年05月29日  17:14:59
 * 更新者: 冯子文
 * 更新时间: 2018年05月29日  17:14:59
 */
@Controller
public class ConsoleUserIndividualController{
	@Autowired
	@Qualifier("consoleUserService")
	private ConsoleUserService consoleUserService;
	
	@Autowired
	@Qualifier("clientDataService")
	private ClientDataService clientDataService;
	
	/**
	 * 
	     * 方法名:login
	     * 功能描述:总后台用户登录(OK) 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月4日 上午9:14:57 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月4日 上午9:14:57
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/user/login", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String login(ConsoleUserLoginRecord consoleUserLoginRecord,@Param("consoleUserName")String consoleUserName,@Param("consoleUserPassword")String consoleUserPassword)  throws Exception{
		if(StringUtil.isNull(consoleUserName) || StringUtil.isNull(consoleUserPassword))
			return BaseResponse.setFailure("总后台用户名或密码不能为空").toString();
		else
			try {	  
				return consoleUserService.login(consoleUserLoginRecord,consoleUserName,consoleUserPassword).toString();
			} catch (Exception e) {
				return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
			}				
	}
	
	/**
	 * 
	     * 方法名:loginout
	     * 功能描述:注销登录	(OK)
	     * 创建者:冯子文
	     * 创建时间: 2018年6月4日 上午9:22:31 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月4日 上午9:22:31
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/user/loginout", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String loginout(@Param("resourceAuthor")String resourceAuthor,@Param("consoleUserId")String consoleUserId)  throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {		
			return consoleUserService.loginout(consoleUserId).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}				
	}
	
	/**
	 * 
	     * 方法名:updatePwd
	     * 功能描述:修改密码	(OK)
	     * 创建者:冯子文
	     * 创建时间: 2018年5月29日 下午6:37:24 
		 * 更新者:冯子文  
		 * 更新时间: 2018年5月29日 下午6:37:24
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/user/updatePwd", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String updatePwd(@Param("resourceAuthor")String resourceAuthor,@Param("consoleUserId")String consoleUserId,@Param("oldConsoleUserPwd")String oldConsoleUserPwd,@Param("consoleUserPwd")String consoleUserPwd)  throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(StringUtil.isNull(consoleUserId) || StringUtil.isNull(oldConsoleUserPwd) || StringUtil.isNull(consoleUserPwd) || StringUtil.equals(oldConsoleUserPwd, consoleUserPwd))
			return BaseResponse.setFailure("后台旧密码或新密码不能为空,新旧密码不能相等").toString();
		else
			try {		
				return consoleUserService.updatePwd(consoleUserId,oldConsoleUserPwd,consoleUserPwd).toString();
			} catch (Exception e) {
				return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
			}
	}
	
	/**
	 * 
	     * 方法名:addUser
	     * 功能描述:添加用户	(OK)
	     * 创建者:冯子文
	     * 创建时间: 2018年6月4日 上午9:40:46 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月4日 上午9:40:46
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/user/addConsoleUser", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String addConsoleUser(@Param("resourceAuthor")String resourceAuthor,ConsoleUser consoleUser)  throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(consoleUser==null || StringUtil.isNull(consoleUser.getConsoleUserName()) || StringUtil.isNull(consoleUser.getConsoleUserPassword()))
			return BaseResponse.setFailure("请传入总台用户的用户名,密码.").toString();
		else
			try {		
				return consoleUserService.addConsoleUser(consoleUser).toString();
			} catch (Exception e) {
				return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
			}
	}
	
	/**
	 * 
	     * 方法名:updateConsoleUser
	     * 功能描述:修改总台用户资料(不包括密码)(OK)	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月4日 上午9:54:32 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月4日 上午9:54:32
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/user/updateConsoleUser", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String updateConsoleUser(@Param("resourceAuthor")String resourceAuthor,ConsoleUser consoleUser)  throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(consoleUser==null || StringUtil.isNull(consoleUser.getConsoleUserName()) || StringUtil.isNull(consoleUser.getId()))
			return BaseResponse.setFailure("请传入总台用户的用户名.").toString();	
		else
			consoleUser.setConsoleUserPassword("");
		try {		
			return consoleUserService.updateConsoleUser(consoleUser).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}	
	
	/**
	 * 
	     * 方法名:updateConsoleUserPwd
	     * 功能描述:修改总台用户密码(OK)	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月4日 上午9:55:26 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月4日 上午9:55:26
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/user/updateConsoleUserPwd", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String updateConsoleUserPwd(@Param("resourceAuthor")String resourceAuthor,@Param("id")String id,@Param("consoleUserPassword")String consoleUserPassword)  throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(StringUtil.isNull(id) || StringUtil.isNull(consoleUserPassword))
			return BaseResponse.setFailure("请传入总台用户的用户名ID及密码.").toString();	
		else
			try {		
				return consoleUserService.updateConsoleUserPwd(id,consoleUserPassword).toString();
			} catch (Exception e) {
				return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
			}
	}	
	
	/**
	 * 
	     * 方法名:getConsoleUserList
	     * 功能描述:获取总台账户分页(OK)	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月4日 上午10:39:23 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月4日 上午10:39:23
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/user/getConsoleUserPage", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String getConsoleUserList(@Param("resourceAuthor")String resourceAuthor,RequestPager requestPager,@Param("consoleUserName")String consoleUserName,@Param("isOnline")Integer isOnline)  throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(isOnline==null)
			isOnline=0;
		try {		
			return consoleUserService.getConsoleUserList(requestPager,consoleUserName,isOnline).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
	/**
	 * 
	     * 方法名:getChannelList
	     * 功能描述:获取渠道列表(OK)	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月4日 上午11:03:52 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月4日 上午11:03:52
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/channel/getChannelList", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String getChannelList(@Param("resourceAuthor")String resourceAuthor)  throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {		
			return consoleUserService.getChannelList().toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}	
	
	/**
	 * 
	     * 方法名:getChannelPage
	     * 功能描述:渠道分页(OK)
	     * 创建者:冯子文
	     * 创建时间: 2018年6月4日 上午11:04:32 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月4日 上午11:04:32
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/channel/getChannelPage", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String getChannelPage(@Param("resourceAuthor")String resourceAuthor,RequestPager requestPager,@Param("channelId")String channelId,@Param("isOnline")Integer isOnline)  throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();	
		try {		
			return consoleUserService.getChannelPage(requestPager,channelId,isOnline).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}	
	/**
	 * 
	     * 方法名:addChannel
	     * 功能描述:增加渠道(OK) 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月4日 上午11:23:30 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月4日 上午11:23:30
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/channel/addChannel", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String addChannel(@Param("resourceAuthor")String resourceAuthor,@Param("channelName")String channelName,@Param("channelAbbreviate")String channelAbbreviate,@Param("isOnline")Integer isOnline)  throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(isOnline==null)
			isOnline=0;
		if(StringUtil.isNull(channelName) || StringUtil.isNull(channelAbbreviate))
			return BaseResponse.setFailure("请输入渠道名称及简写.").toString();
		try {		
			return consoleUserService.addChannel(channelName,channelAbbreviate,isOnline).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}	
	/**
	 * 
	     * 方法名:updateChannel
	     * 功能描述:修改渠道	(OK)
	     * 创建者:冯子文
	     * 创建时间: 2018年6月4日 上午11:34:18 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月4日 上午11:34:18
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/channel/updateChannel", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String updateChannel(@Param("resourceAuthor")String resourceAuthor,Channel channel)  throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(StringUtil.isNull(channel.getId()))
			return BaseResponse.setFailure("请输入渠道ID.").toString();
		try {		
			return consoleUserService.updateChannel(channel).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}	
	/**
	 * 
	     * 方法名:addChannelUser
	     * 功能描述:增加渠道用户(OK) 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月4日 上午11:23:30
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月4日 上午11:23:30
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/channel/addChannelUser", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String addChannelUser(@Param("resourceAuthor")String resourceAuthor,ChannelUser channelUser)  throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(channelUser==null)
			return BaseResponse.setFailure("请输入渠道用户资料.").toString();
		if(StringUtil.isNull(channelUser.getChannelId()))
			return BaseResponse.setFailure("请输入渠道ID不能为空.").toString();
		//条件校验
		try {		
			return consoleUserService.addChannelUser(channelUser).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	/**
	 * 
	     * 方法名:updateChannelUser
	     * 功能描述:修改渠道用户资料(OK)	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月4日 上午11:53:24 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月4日 上午11:53:24
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/channel/updateChannelUser", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String updateChannelUser(@Param("resourceAuthor")String resourceAuthor,ChannelUser channelUser)  throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(channelUser==null)
			return BaseResponse.setFailure("请输入渠道用户资料.").toString();
		if(StringUtil.isNull(channelUser.getId()))
			return BaseResponse.setFailure("请输入渠道用户ID不能为空.").toString();
		channelUser.setChannelUserPwd("");	
		//条件校验
		try {		
			return consoleUserService.updateChannelUser(channelUser).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	/**
	 * 
	     * 方法名:updateChannelUserPwd
	     * 功能描述:修改渠道用户密码	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月4日 上午11:55:07 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月4日 上午11:55:07
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/channel/updateChannelUserPwd", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String updateChannelUserPwd(@Param("resourceAuthor")String resourceAuthor,@Param("id")String id,@Param("channelUserPwd")String channelUserPwd,@Param("isOnline")Integer isOnline)  throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(isOnline==null)
			isOnline=0;
		if(StringUtil.isNull(id) || StringUtil.isNull(channelUserPwd))
			return BaseResponse.setFailure("请输入渠道用户ID及密码").toString();
		try {		
			return consoleUserService.updateChannelUserPwd(id,channelUserPwd,isOnline).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
	/**
	 * 
	     * 方法名:getChannelUserPage
	     * 功能描述:渠道用户分页(ok)	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月7日 上午7:46:40 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月7日 上午7:46:40
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/channel/getChannelUserPage", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String getChannelUserPage(@Param("resourceAuthor")String resourceAuthor,RequestPager requestPager,@Param("channelId")String channelId,@Param("channelUserName")String channelUserName)  throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(StringUtil.isNull(channelId))
			return BaseResponse.setFailure("请输入渠道ID").toString();
		try {		
			return consoleUserService.getChannelUserPage(requestPager,channelId,channelUserName).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
	/**
	 * 
	     * 方法名:getUserList
	     * 功能描述:获取渠道用户列表(OK)	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月7日 下午5:19:41 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月7日 下午5:19:41
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/channel/getUserList", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String getUserList(@Param("resourceAuthor")String resourceAuthor,@Param("channelId")String channelId)  throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(StringUtil.isNull(channelId))
			return BaseResponse.setFailure("请输入渠道ID").toString();
		try {		
			return consoleUserService.getUserList(channelId).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
	/**
	 * 
	     * 方法名:staticsByUser
	     * 功能描述:渠道用户统计(OK)	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月7日 下午5:19:41 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月7日 下午5:19:41
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/channel/staticsByUser", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String staticsByUser(@Param("resourceAuthor")String resourceAuthor,@Param("startDate")Integer startDate,@Param("endDate")Integer endDate,@Param("channelId")String channelId,@Param("channelUserId")String channelUserId)  throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(StringUtil.isNull(channelId))
			return BaseResponse.setFailure("请输入渠道ID").toString();
		if(endDate==null) 
			endDate=DateUtil.formatCurrentDate();
		if(startDate==null)
			startDate=DateUtil.subtractWeek(endDate);
		try {		
			return consoleUserService.staticsByUser(startDate,endDate,channelId,channelUserId).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}	
	
	/**
	 * 
	     * 方法名:getDepartPage
	     * 功能描述:获取部门分页	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月7日 下午6:17:53 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月7日 下午6:17:53
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/depart/getDepartPage", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String getDepartPage(@Param("resourceAuthor")String resourceAuthor,RequestPager requestPager,@Param("isOnline")Integer isOnline)  throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {		
			return consoleUserService.getDepartPage(requestPager,isOnline).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	/**
	 * 
	     * 方法名:addDepart
	     * 功能描述:增加部门	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月7日 下午6:30:26 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月7日 下午6:30:26
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/depart/addDepart", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String addDepart(@Param("resourceAuthor")String resourceAuthor,EmployeeDepartment employeeDepartment)  throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(employeeDepartment==null)
			return BaseResponse.setFailure("请传入部门资料.").toString();
		if(StringUtil.isNull(employeeDepartment.getEmployeeDepartmentName()))
			return BaseResponse.setFailure("请传入部门名称.").toString();
		if(employeeDepartment.getIsTrack()==null)
			employeeDepartment.setIsTrack(1);//跟踪(即部门二)
		employeeDepartment.setIsOnline(0);//上线
		
		try {		
			return consoleUserService.addDepart(employeeDepartment).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
	/**
	 * 功能描述:修改部门
	 * 开发者：fengziwen
	 * 时间：2018/06/07
	 * @param resourceAuthor
	 * @param employeeDepartment
	 * @return
	 * @throws Exception
	 */
	
	@ResponseBody
	@RequestMapping(value = "/consoleuser/depart/updateDepart", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String updateDepart(@Param("resourceAuthor")String resourceAuthor,EmployeeDepartment employeeDepartment)  throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(employeeDepartment==null)
			return BaseResponse.setFailure("请传入部门资料.").toString();
		if(StringUtil.isNull(employeeDepartment.getId()))
			return BaseResponse.setFailure("请传入部门ID.").toString();
		employeeDepartment.setIsOnline(0);//上线
		try {		
			return consoleUserService.updateDepart(employeeDepartment).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
	/**
	 * 
	     * 方法名:getEmployeePage
	     * 功能描述:获取部门员工列表	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月7日 下午6:59:36 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月7日 下午6:59:36
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/employee/getEmployeePage", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String getEmployeePage(@Param("resourceAuthor")String resourceAuthor,RequestPager requestPager,@Param("employeeDepartmentId")String employeeDepartmentId)  throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(StringUtil.isNull(employeeDepartmentId))
			return BaseResponse.setFailure("请传入部门 ID").toString();
		try {		
			return consoleUserService.getEmployeePage(requestPager,employeeDepartmentId).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	/**
	 * 
	     * 方法名:getEmployeeList
	     * 功能描述:获取员工列表	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月7日 下午8:22:51 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月7日 下午8:22:51
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/employee/getEmployeeList", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String getEmployeeList(@Param("resourceAuthor")String resourceAuthor,@Param("employeeDepartmentId")String employeeDepartmentId)  throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(StringUtil.isNull(employeeDepartmentId))
			return BaseResponse.setFailure("请传入部门 ID").toString();
		try {		
			return consoleUserService.getEmployeeList(employeeDepartmentId).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}	
	/**
	 * 
	     * 方法名:addEmployee
	     * 功能描述:增加员工	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月7日 下午7:05:02 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月7日 下午7:05:02
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/employee/addEmployee", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String addEmployee(@Param("resourceAuthor")String resourceAuthor,EmployeeUser employeeUser)  throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(employeeUser==null)
			return BaseResponse.setFailure("请传入员工资料.").toString();
		if(StringUtil.isNull(employeeUser.getEmployeeDepartmentId()))
			return BaseResponse.setFailure("请传入员工部门ID.").toString();
		try {		
			return consoleUserService.addEmployee(employeeUser).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}	
	
	/**
	 * 
	     * 方法名:updateEmployee
	     * 功能描述:修改用户资料	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月7日 下午7:08:47 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月7日 下午7:08:47
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/employee/updateEmployee", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String updateEmployee(@Param("resourceAuthor")String resourceAuthor,EmployeeUser employeeUser)  throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(employeeUser==null)
			return BaseResponse.setFailure("请传入员工资料.").toString();
		if(StringUtil.isNull(employeeUser.getId()))
			return BaseResponse.setFailure("请传入员工ID.").toString();
		employeeUser.setEmployeeUserPassword("");
		try {		
			return consoleUserService.updateEmployee(employeeUser).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}	
	
	/**
	 * 
	     * 方法名:updateEmployeePwd
	     * 功能描述:修改员工密码	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月7日 下午7:10:41 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月7日 下午7:10:41
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/employee/updateEmployeePwd", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String updateEmployeePwd(@Param("resourceAuthor")String resourceAuthor,@Param("id")String id,@Param("employeeUserPassword")String employeeUserPassword)  throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(StringUtil.isNull(id) || StringUtil.isNull(employeeUserPassword))
			return BaseResponse.setFailure("请传入员工ID或员工密码.").toString();
		try {		
			return consoleUserService.updateEmployeePwd(id,employeeUserPassword).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
	/**
	 * 
	     * 方法名:statics
	     * 功能描述:员工统计	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月11日 上午10:26:52 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月11日 上午10:26:52
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/employee/statics", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String statics(@Param("resourceAuthor")String resourceAuthor,@Param("startDate")Integer startDate,@Param("endDate")Integer endDate,@Param("employeeDepartmentId")String employeeDepartmentId,@Param("employeeUserId")String employeeUserId)  throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(StringUtil.isNull(employeeDepartmentId))
			return BaseResponse.setFailure("请传入员工部门ID.").toString();
		if(endDate==null) 
			endDate=DateUtil.formatCurrentDate();
		if(startDate==null)
			startDate=DateUtil.subtractWeek(endDate);
		try {		
			return consoleUserService.statics(startDate,endDate,employeeDepartmentId,employeeUserId).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
    /**
     * 
         * 方法名:employeeStatics
         * 功能描述:员工统计	 
         * 创建者:冯子文
         * 创建时间: 2018年6月15日 下午7:59:47 
    	 * 更新者:冯子文  
    	 * 更新时间: 2018年6月15日 下午7:59:47
     */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/index/employeeOneStatics", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String employeeOneStatics(@Param("resourceAuthor")String resourceAuthor,@Param("timeSpan")Integer timeSpan,@Param("startDate")Integer startDate,@Param("endDate")Integer endDate,@Param("sort")Integer sort)  throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(timeSpan==null)
			timeSpan=0;//0:日,1:月 2:年
		switch(timeSpan) {
			case 0:
				if(endDate==null) 
					endDate=DateUtil.formatCurrentDate();
				if(startDate==null)
					startDate=DateUtil.subtractWeek(endDate);				
				break;
			case 1:
				if(endDate==null) 
					endDate=DateUtil.formatCurrentDate()/100;
				if(startDate==null)
					startDate=DateUtil.formatCurrentDate()/100;				
				break;
			case 2:
				if(endDate==null) 
					endDate=DateUtil.formatCurrentDate()/10000;
				if(startDate==null)
					startDate=DateUtil.formatCurrentDate()/10000;				
				break;
			default:	
		}
		if(sort==null)
			sort=0;
		if(!(sort==0 || sort==1))
			return BaseResponse.setFailure("请传入正确的参数.").toString();
		try {		
			return consoleUserService.employeeOneStatics(timeSpan,startDate,endDate,sort).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}	


	
	@ResponseBody
	@RequestMapping(value = "/consoleuser/index/employeeTwoStatics", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String employeeTwoStatics(@Param("resourceAuthor")String resourceAuthor,@Param("timeSpan")Integer timeSpan,@Param("startDate")Integer startDate,@Param("endDate")Integer endDate) throws Exception{
		if(!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(timeSpan==null)
			timeSpan=0;//0:日,1:月 2:年
		if(!(timeSpan==0 || timeSpan==1 || timeSpan==2))
			return BaseResponse.setFailure("请传入正确的时间选择.").toString();
		switch(timeSpan) {
			case 0:
				if(endDate==null) 
					endDate=DateUtil.formatCurrentDate();
				if(startDate==null)
					startDate=DateUtil.subtractWeek(endDate);
				if(String.valueOf(endDate).length()!=8 ||String.valueOf(endDate).length()!=8) 
					return BaseResponse.setFailure("请输入正确的日期.").toString();
				break;
			case 1:
				if(endDate==null) 
					endDate=DateUtil.formatCurrentDate()/100;
				if(startDate==null)
					startDate=DateUtil.formatCurrentDate()/100;	
				if(String.valueOf(endDate).length()!=6 ||String.valueOf(endDate).length()!=6) 
					return BaseResponse.setFailure("请输入正确的月份.").toString();
				break;
			case 2:
				if(endDate==null) 
					endDate=DateUtil.formatCurrentDate()/10000;
				if(startDate==null)
					startDate=DateUtil.formatCurrentDate()/10000;
				if(String.valueOf(endDate).length()!=4 ||String.valueOf(endDate).length()!=4) 
					return BaseResponse.setFailure("请输入正确的年份.").toString();
				break;
			default:	
		}
		try {		
			return consoleUserService.employeeTwoStatics(timeSpan,startDate,endDate).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}		
	}

}
