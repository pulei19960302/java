package com.qim.loan.controller.Individual.client;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qim.loan.entity.client.ClientData;
import com.qim.loan.service.client.ClientDataService;
import com.qim.loan.util.author.ResoureManageUtil;
import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.common.IdCardUtil;
import com.qim.loan.util.common.MobilUtil;
import com.qim.loan.util.common.StringUtil;
import com.qim.loan.util.common.ValidateUtil;
import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.paramter.RequestPager;

/**
 *
 * 类名: ClientDataController 描述: 客户数据Controller层(公共) 创建者: 冯子文 创建时间: 2018年05月29日
 * 17:14:59 更新者: 冯子文 更新时间: 2018年05月29日 17:14:59
 */
@Controller
public class ClientDataIndividualController {
	@Autowired
	@Qualifier("clientDataService")
	private ClientDataService clientDataService;

	/**
	 * 
	 * 方法名:retention 功能描述:用户留资页 创建者:冯子文 创建时间: 2018年6月1日 下午3:28:46 更新者:冯子文 更新时间:
	 * 2018年6月1日 下午3:28:46
	 */
	@ResponseBody
	@RequestMapping(value = "/client/retention", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String retention(ClientData clientData) throws Exception {
		if (clientData == null)
			return BaseResponse.setFailure("请填写用户资料.").toString();
		String telphoneNumber = clientData.getTelphoneNumber();
		// 客服部门工号
//		if(!StringUtil.isNotNull(clientData.getEmployeeOneUserId())) 
//			return BaseResponse.setFailure("请求填写客服工号").toString();
	     //审核部门Id
//		if(!StringUtil.isNotNull(clientData.getEmployeeTwoUserId())) 
//			return BaseResponse.setFailure("请求填写审核部门工号").toString();
		
		if (StringUtil.isNull(telphoneNumber))
			return BaseResponse.setFailure("请填写手机号码.").toString();
		if (!MobilUtil.isMobile(telphoneNumber))
			return BaseResponse.setFailure("请填写正确的手机号码.").toString();
		// 服务密码
		String servicePassword = clientData.getServicePassword();
		if (StringUtil.isNotNull(servicePassword) && servicePassword.length() != 6)
			return BaseResponse.setFailure("请填写6位服务密码.").toString();
		// 使用年限
		String phoneUseYear = clientData.getPhoneUseYear();
		if (StringUtil.isNotNull(phoneUseYear) && !ValidateUtil.isNumeric(phoneUseYear))
			return BaseResponse.setFailure("请填写正确的使用年限.").toString();
		// 是否实名
		Integer isReal = clientData.getIsReal();
		if (isReal == null || !(isReal == 1 || isReal == 0))
			return BaseResponse.setFailure("请选择是否实名").toString();
		// 身份证号码
		String identificationCardNumber = clientData.getIdentificationCardNumber();
		if (StringUtil.isNull(identificationCardNumber))
			return BaseResponse.setFailure("请填写身份证编号.").toString();
		if (!(identificationCardNumber.length() == 18 || identificationCardNumber.length() == 15))
			return BaseResponse.setFailure("身份证编号错误.").toString();
		if (!IdCardUtil.isValidatedAllIdcard(identificationCardNumber))
			return BaseResponse.setFailure("身份证编号错误.").toString();
		// 芝麻分
		Integer seseamScore = clientData.getSeseamScore();
		if (seseamScore == null || seseamScore < 0)
			return BaseResponse.setFailure("芝麻分不能为空且值大于0.").toString();
		// 淘气值
		Integer naughtyScore = clientData.getNaughtyScore();
		if (naughtyScore != null && naughtyScore < 0)
			return BaseResponse.setFailure("淘气值不能小于0.").toString();
		// 真实学历
		String realEducation = clientData.getRealEducation();
		if (StringUtil.isNotNull(realEducation) && realEducation.length() < 2)
			return BaseResponse.setFailure("请填写正确的真实学历.").toString();
		// 是否贷款
		Integer isHouseLoan = clientData.getIsHouseLoan();
		if (isHouseLoan == null || !(isHouseLoan == 1 || isHouseLoan == 0))
			return BaseResponse.setFailure("请选择是否有贷款").toString();
		// 是否公积金
		Integer isAccumulationFund = clientData.getIsAccumulationFund();
		if (isAccumulationFund == null || !(isAccumulationFund == 1 || isAccumulationFund == 0))
			return BaseResponse.setFailure("请选择是否有公积金").toString();
		// 是否信用卡
		Integer isCreadit = clientData.getIsCreadit();
		if (isCreadit == null || !(isCreadit == 1 || isCreadit == 0))
			return BaseResponse.setFailure("请选择是否有信用卡").toString();
		// 单张额度
		BigDecimal credit = clientData.getCreadit();
		if (credit != null && !ValidateUtil.isNumeric(credit.toString()))
			return BaseResponse.setFailure("请填写正确的单张额度.").toString();
		// 公司名称
		String companyName = clientData.getCompanyName();
		if (StringUtil.isNotNull(companyName) && companyName.length() < 4)
			return BaseResponse.setFailure("请填写正确的公司名称.").toString();
		// 公司地址
		String companyPlace = clientData.getCompanyPlace();
		if (StringUtil.isNotNull(companyPlace) && companyPlace.length() < 4)
			return BaseResponse.setFailure("请填写正确的公司地址.").toString();
		// 现居住地址
		String livePlace = clientData.getLivePlace();
		if (StringUtil.isNotNull(livePlace) && livePlace.length() < 4)
			return BaseResponse.setFailure("请填写正确的现居住地址.").toString();
		// 直系亲属1
		String contractNameOne = clientData.getContractNameOne();
		if (StringUtil.isNull(contractNameOne) || contractNameOne.length() < 2)
			return BaseResponse.setFailure("请正确填写直系亲属一的姓名.").toString();
		// 直系亲属1的亲属关系
		String contractRelationOne = clientData.getContractRelationOne();
		if (StringUtil.isNull(contractRelationOne) || contractRelationOne.length() < 1)
			return BaseResponse.setFailure("请正确填写直系亲属一的亲属关系.").toString();
		// 直系亲属1的手机号码
		String contractTelphoneOne = clientData.getContractTelphoneOne();
		if (StringUtil.isNull(contractNameOne) || !MobilUtil.isMobile(contractTelphoneOne))
			return BaseResponse.setFailure("请正确填写直系亲属一的手机号码.").toString();
		// 直系亲属2
		String contractNameTwo = clientData.getContractNameTwo();
		if (StringUtil.isNull(contractNameTwo) || contractNameTwo.length() < 2)
			return BaseResponse.setFailure("请正确填写直接亲属二的姓名.").toString();
		// 直系亲属2的亲属关系
		String contractRelationTwo = clientData.getContractRelationTwo();
		if (StringUtil.isNull(contractRelationTwo) || contractRelationTwo.length() < 1)
			return BaseResponse.setFailure("请正确填写直系亲属二的亲属关系.").toString();
		// 直系亲属2的手机号码
		String contractTelphoneTwo = clientData.getContractTelphoneTwo();
		if (StringUtil.isNull(contractNameTwo) || !MobilUtil.isMobile(contractTelphoneTwo))
			return BaseResponse.setFailure("请正确填写直系亲属二的手机号码.").toString();
		if (StringUtil.equals(contractTelphoneOne, contractTelphoneTwo))
			return BaseResponse.setFailure("直系亲属二的手机号码与直系亲属一的手机号码不能一样.").toString();
		// 直系亲属3
		String contractNameThree = clientData.getContractNameThree();
		if (StringUtil.isNotNull(contractNameThree) && contractNameThree.length() < 2)
			return BaseResponse.setFailure("请正确填写亲属三的姓名.").toString();
		// 直系亲属3的亲属关系
		String contractRelationThree = clientData.getContractRelationThree();
		if (StringUtil.isNotNull(contractRelationThree) && contractRelationThree.length() < 1)
			return BaseResponse.setFailure("请正确填写亲属三的亲属关系.").toString();
		// 直系亲属3的手机号码
		String contractTelphoneThree = clientData.getContractTelphoneThree();
		if (StringUtil.isNotNull(contractTelphoneThree) && !MobilUtil.isMobile(contractTelphoneThree))
			return BaseResponse.setFailure("请正确填写亲属三的手机号码.").toString();
		if (StringUtil.equals(contractTelphoneOne, contractTelphoneThree) || StringUtil.equals(contractTelphoneThree, contractTelphoneTwo))
			return BaseResponse.setFailure("亲属三的手机号码不能与直系亲属的手机号码一样.").toString();
		// 直系亲属4
		String contractNameFour = clientData.getContractNameFour();
		if (StringUtil.isNotNull(contractNameFour) && contractNameFour.length() < 2)
			return BaseResponse.setFailure("请正确填写亲属四的姓名.").toString();
		// 直系亲属4的亲属关系
		String contractRelationFour = clientData.getContractRelationFour();
		if (StringUtil.isNotNull(contractRelationFour) && contractRelationFour.length() < 1)
			return BaseResponse.setFailure("请正确填写亲属四的亲属关系.").toString();
		// 直系亲属4的手机号码
		String contractTelphoneFour = clientData.getContractTelphoneFour();
		if (StringUtil.isNotNull(contractTelphoneFour) && !MobilUtil.isMobile(contractTelphoneFour)) {
			if (StringUtil.equals(contractTelphoneOne, contractTelphoneFour) || StringUtil.equals(contractTelphoneFour, contractTelphoneTwo) || StringUtil.equals(contractTelphoneThree, contractTelphoneFour))
				return BaseResponse.setFailure("亲属四的手机号码不能与其它亲属的手机号码一样.").toString();
		}
		// 直系亲属5
		String contractNameFive = clientData.getContractNameFive();
		if (StringUtil.isNotNull(contractNameFive) && contractNameFive.length() < 2)
			return BaseResponse.setFailure("请正确填写亲属五的姓名.").toString();
		// 直系亲属5的亲属关系
		String contractRelationFive = clientData.getContractRelationFive();
		if (StringUtil.isNotNull(contractRelationFive) && contractRelationFive.length() < 1)
			return BaseResponse.setFailure("请正确填写亲属五的亲属关系.").toString();
		// 直系亲属5的手机号码
		String contractTelphoneFive = clientData.getContractTelphoneFive();
		if (StringUtil.isNotNull(contractTelphoneFive) && !MobilUtil.isMobile(contractTelphoneFive)) {
			if (StringUtil.equals(contractTelphoneOne, contractTelphoneFive) || StringUtil.equals(contractTelphoneFive, contractTelphoneTwo) || StringUtil.equals(contractTelphoneThree, contractTelphoneFive) || StringUtil.equals(contractTelphoneFive, contractTelphoneFour))
				return BaseResponse.setFailure("亲属五的手机号码不能与其它亲属的手机号码一样.").toString();
		}
		try {
			return clientDataService.retention(clientData).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}

	/**
	 * 
	 * 方法名:getSelect 功能描述:获取从业人员下拉编号 创建者:冯子文 创建时间: 2018年6月1日 下午3:30:41 更新者:冯子文 更新时间:
	 * 2018年6月1日 下午3:30:41
	 */
	@ResponseBody
	@RequestMapping(value = "/client/getSelect", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String getSelect() throws Exception {
		try {
			return clientDataService.getSelect().toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	

	/**
	 * 
	     * 方法名:getEmployeeTwoIdByNumber
	     * 功能描述:根据部门二的工号获取部门二的ID	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月14日 下午7:12:11 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月14日 下午7:12:11
	 */
	@ResponseBody
	@RequestMapping(value = "/client/getEmployeeTwoIdByNumber",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	private String getEmployeeTwoIdByNumber(@Param("employeeTwoNumber")String employeeTwoNumber) {
		if(StringUtil.isNull(employeeTwoNumber))
			return BaseResponse.setFailure("请传入审核部工号.").toString();
		try {
			return clientDataService.getEmployeeTwoIdByNumber(employeeTwoNumber).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
	/**
	 * 
	     * 方法名:getEmployeeOneNumberByTelphoneNumber
	     * 功能描述:根据员工一的电话号码获取编号	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月14日 下午7:13:31 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月14日 下午7:13:31
	 */
	@ResponseBody
	@RequestMapping(value = "/client/getEmployeeOneNumberByTelphoneNumber",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	private String getEmployeeOneNumberByTelphoneNumber(@Param("telphoneNumber")String telphoneNumber) {
		if(StringUtil.isNull(telphoneNumber) || !MobilUtil.isMobile(telphoneNumber))
			return BaseResponse.setFailure("请传入正确的手机号码").toString();
		try {
			return clientDataService.getEmployeeOneNumberByTelphoneNumber(telphoneNumber).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
	
	
	
	/**
	 * 
	     * 方法名:queryClientDatas
	     * 功能描述:查询留资列表	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月10日 下午10:11:18 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月10日 下午10:11:18
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/retention/queryClientDatas", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String queryClientDatas(@Param("resourceAuthor") String resourceAuthor,RequestPager requestPager, ClientData clientData, @Param("startDate")Integer startDate,@Param("endDate")Integer endDate,@Param("startSeseamScore") Integer startSeseamScore, @Param("endSeseamScore") Integer endSeseamScore) throws Exception  {
		if (!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(endDate==null)
			endDate=DateUtil.formatCurrentDate();
		if(startDate==null)
			startDate=DateUtil.subtractWeek(endDate);	
		try {
			return clientDataService.queryClientDatas(requestPager,clientData,startDate,endDate,startSeseamScore, endSeseamScore).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}

	
	/**
	 * 
	     * 方法名:getById
	     * 功能描述:通过ID获取留资详情 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月11日 上午12:39:35 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月11日 上午12:39:35
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/retention/getById", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String getById(@Param("resourceAuthor") String resourceAuthor, @Param("id") String id) throws Exception {
		if (!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(StringUtil.isNull(id))
			return BaseResponse.setFailure("请传入资料ID.").toString();
		try {
			return clientDataService.getById(id).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}	
	
	
	/**
	 * 
	     * 方法名:staticsCurentNumber
	     * 功能描述:统计留资量(当日，本周，本月)	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月11日 上午12:44:28 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月11日 上午12:44:28
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/retention/staticsRetentionNumber", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String staticsRetentionNumber(@Param("resourceAuthor") String resourceAuthor)  throws Exception{
		if (!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			return clientDataService.staticsRetentionNumber().toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
	/**
	 * 
	     * 方法名:staticsImportNumber
	     * 功能描述:统计导入量 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月11日 上午1:02:51 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月11日 上午1:02:51
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/retention/staticsImportNumber", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String staticsImportNumber(@Param("resourceAuthor") String resourceAuthor)  throws Exception{
		if (!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			return clientDataService.staticsImportNumber().toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	/**
	 * 
	     * 方法名:staticsSurplusNumber
	     * 功能描述:未分发量统计
	     * 创建者:冯子文
	     * 创建时间: 2018年6月11日 上午1:15:10 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月11日 上午1:15:10
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/retention/staticsSurplusNumber", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String staticsSurplusNumber(@Param("resourceAuthor") String resourceAuthor)  throws Exception{
		if (!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			return clientDataService.staticsSurplusNumber().toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
	/**
	 * 
	     * 方法名:staticsIndex
	     * 功能描述:首页统计图(?????????????)	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月11日 上午1:25:34
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月11日 上午1:25:34
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/retention/staticsIndex", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String staticsIndex(@Param("resourceAuthor") String resourceAuthor, @Param("startDate") Integer startDate, @Param("endDate") Integer endDate)  throws Exception{
		if (!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			return clientDataService.staticsIndex(startDate,endDate).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}	
	
	
	/**
	 * 
	     * 方法名:distributeAll
	     * 功能描述:全部分发	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月11日 上午1:27:00 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月11日 上午1:27:00
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/distribute/distributeAll", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String distributeAll(@Param("resourceAuthor") String resourceAuthor) throws Exception{
		if (!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			return clientDataService.distributeAll().toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}

	}
	
	/**
	 * 
	     * 方法名:distributePart
	     * 功能描述:每人分发多少	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月11日 上午2:13:08 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月11日 上午2:13:08
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/distribute/distributePart", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String distributePart(@Param("resourceAuthor") String resourceAuthor,@Param("everyNumber")Integer everyNumber) throws Exception{
		if (!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(everyNumber==null || everyNumber==0)
			return BaseResponse.setFailure("分发数量不能为空或0").toString();
		try {
			return clientDataService.distributePart(everyNumber).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}	
	
	/**
	 * 
	     * 方法名:distributePerson
	     * 功能描述:分发给具体的人	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月11日 上午2:17:40 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月11日 上午2:17:40
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/distribute/distributePerson", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String distributePerson(@Param("resourceAuthor") String resourceAuthor,@Param("number")Integer number,@Param("employeeUserId")String employeeUserId) throws Exception{
		if (!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		if(number==null || number==0)
			return BaseResponse.setFailure("分发数量不能为空或0").toString();
		if(StringUtil.isNull("employeeUserId"))
			return BaseResponse.setFailure("分发的用户不能为空.").toString();
	
		try {
			return clientDataService.distributePerson(number,employeeUserId).toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
	/**
	 * 
	     * 方法名:getDepartmentOneEmployee
	     * 功能描述:获取部门一员工	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月11日 上午2:30:16 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月11日 上午2:30:16
	 */
	@ResponseBody
	@RequestMapping(value = "/consoleuser/employee/getDepartmentOneEmployee", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private String getDepartmentOneEmployee(@Param("resourceAuthor") String resourceAuthor) throws Exception{
		if (!ResoureManageUtil.isPremitManage(resourceAuthor))
			return ResoureManageUtil.printForbidden();	
		try {
			return clientDataService.getDepartmentOneEmployee().toString();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
	
	
}
