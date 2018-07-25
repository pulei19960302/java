package com.qim.loan.service.employee.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.qim.loan.bo.employee.EmployeeUserBo;
import com.qim.loan.core.model.BaseModel;
import com.qim.loan.core.model.CommonModel;
import com.qim.loan.core.service.impl.BaseServiceImpl;
import com.qim.loan.entity.employee.EmployeeUser;
import com.qim.loan.entity.employee.EmployeeUserLoginRecord;
import com.qim.loan.model.employee.EmployeeUserLoginRecordModel;
import com.qim.loan.model.employee.EmployeeUserModel;
import com.qim.loan.service.employee.EmployeeUserService;
import com.qim.loan.util.cache.classify.string.IStringCache;
import com.qim.loan.util.cache.string.StringCacheFactory;
import com.qim.loan.util.common.BeanUtil;
import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.common.ListUtil;
import com.qim.loan.util.common.MapUtil;
import com.qim.loan.util.common.StringUtil;
import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.paramter.RequestPager;

/**
 *
 * 类名: EmployeeUserServiceImpl 描述: 员工用户ServiceImpl层(公共) 创建者: 冯子文 创建时间:
 * 2018年05月29日 17:14:59 更新者: 冯子文 更新时间: 2018年05月29日 17:14:59
 */
@Service("employeeUserService")
public class EmployeeUserServiceImpl extends BaseServiceImpl<EmployeeUser> implements EmployeeUserService {

	@Autowired
	@Qualifier("employeeUserModel")
	private EmployeeUserModel employeeUserModel;

	@Autowired
	@Qualifier("stringCacheFactory")
	private StringCacheFactory stringCacheFactory;

	@Autowired
	private CommonModel commonModel;

	@Autowired
	@Qualifier("employeeUserLoginRecordModel")
	private EmployeeUserLoginRecordModel employeeUserLoginRecordModel;

	@Override
	public BaseModel<EmployeeUser> getModel() {
		return employeeUserModel;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse login(EmployeeUserLoginRecord employeeUserLoginRecord, String employeeUserName, String employeeUserPwd) throws Exception {
		EmployeeUser result = employeeUserModel.getOne(EmployeeUserBo.setLogin(employeeUserName, employeeUserPwd));
		if (BeanUtil.isNull(result)) {
			return BaseResponse.setFailure("用户名或密码错误.");
		} else {
			IStringCache userCache = stringCacheFactory.getUser();
			// 1:写入记录
			employeeUserLoginRecord.setEmployeeUserId(result.getId());
			employeeUserLoginRecordModel.insert(employeeUserLoginRecord);
			return BaseResponse.setSuccess(MapUtil.setAuthority(userCache, result.getId(), result.getEmployeeUserName(), "ep"));
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse loginm(EmployeeUserLoginRecord employeeUserLoginRecord, String employeeUserName, String employeeUserPwd) throws Exception {
		EmployeeUser result = employeeUserModel.getOne(EmployeeUserBo.setLogin(employeeUserName, employeeUserPwd));
		if (BeanUtil.isNull(result)) {
			return BaseResponse.setFailure("用户名或密码错误.");
		} else {
			IStringCache userCache = stringCacheFactory.getUser();
			// 1:写入记录
			employeeUserLoginRecord.setEmployeeUserId(result.getId());
			employeeUserLoginRecordModel.insert(employeeUserLoginRecord);
			return BaseResponse.setSuccess(MapUtil.setAuthority(userCache, result.getId(), result.getEmployeeUserName(), "em"));
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse loginout(String employeeUserId) throws Exception {
		IStringCache userCache = stringCacheFactory.getUser();
		MapUtil.setAuthorityOut(userCache, employeeUserId);
		return BaseResponse.setSuccessMsg("注销用户成功.");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse loginoutm(String employeeUserId) throws Exception {
		IStringCache userCache = stringCacheFactory.getUser();
		MapUtil.setAuthorityOut(userCache, employeeUserId);
		return BaseResponse.setSuccessMsg("注销用户成功.");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse updatePwd(String employeeUserId, String oldEmployeeUserPwd, String employeeUserPwd) throws Exception {
		EmployeeUser result = employeeUserModel.getOne(EmployeeUserBo.updatePwd(employeeUserId, oldEmployeeUserPwd));
		if (BeanUtil.isNull(result)) {
			return BaseResponse.setFailure("密码错误.");
		} else {
			EmployeeUserBo.updatePwd(result, employeeUserPwd);
			return BaseResponse.countResponse("修改密码", employeeUserModel.updateById(result));
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse updatePwdm(String employeeUserId, String oldEmployeeUserPwd, String employeeUserPwd) throws Exception {
		EmployeeUser result = employeeUserModel.getOne(EmployeeUserBo.updatePwd(employeeUserId, oldEmployeeUserPwd));
		if (BeanUtil.isNull(result)) {
			return BaseResponse.setFailure("密码错误.");
		} else {
			EmployeeUserBo.updatePwd(result, employeeUserPwd);
			return BaseResponse.countResponse("修改密码", employeeUserModel.updateById(result));
		}
	}

	@Override
	public BaseResponse getUser(String employeeUserId) throws Exception {
		EmployeeUser employeeUser = employeeUserModel.getOneById("employee_user_name,employee_real_name", employeeUserId);
		return BaseResponse.setSuccess(employeeUser);
	}

	@Override
	public BaseResponse getClientList(RequestPager requestPager, String employeeUserId, String telphoneNumber, Integer startDate, Integer endDate, Integer isHanle,String realName) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select clientData.id as client_data_id,clientData.telphone_number,clientData.real_name,clientData.is_real,clientData.service_password, ");
		sql.append("clientData.phone_use_year,clientData.identification_card_number,clientData.seseam_score,clientData.naughty_score, ");
		sql.append("clientData.real_education,clientData.is_house_loan,clientData.is_accumulation_fund,clientData.is_creadit, ");
		sql.append("clientData.creadit,clientData.company_name,clientData.company_place,clientData.live_place,clientData.is_hanle, ");
		sql.append("employeeUser.employee_number ");
		sql.append("from client_data clientData ");
		sql.append("left join employee_user employeeUser on employeeUser.id=clientData.employee_two_user_id ");
		sql.append("where clientData.is_fill=1 ");
		//sql.append("and clientData.employee_two_user_id='" + employeeUserId + "' ");
		sql.append("and clientData.is_hanle=" + isHanle + " ");
		if(StringUtil.isNotNull(realName))
			sql.append("and clientData.real_name like '%" + realName + "%' ");
		if (startDate != null)
			sql.append("and clientData.fill_date>=" + startDate + " ");
		if (endDate != null)
			sql.append("and clientData.fill_date<=" + endDate + " ");
		if (StringUtil.isNotNull(telphoneNumber))
			sql.append("and clientData.telphone_number='" + telphoneNumber + "' ");

		sql.append("ORDER BY clientData.fill_date asc,clientData.fill_time asc ");
		PageInfo<Map<String, Object>> pageInfo = commonModel.basePage(requestPager, sql.toString());
		pageInfo.setList(ListUtil.toCamel(pageInfo.getList()));
		return BaseResponse.setSuccess(pageInfo);
	}

	@Override
	public BaseResponse getClient(String clientDataId) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select clientData.id as client_data_id,clientData.telphone_number,clientData.real_name,clientData.is_real,clientData.service_password, ");
		sql.append("clientData.phone_use_year,clientData.identification_card_number,clientData.seseam_score,clientData.naughty_score, ");
		sql.append("clientData.real_education,clientData.is_house_loan,clientData.is_accumulation_fund,clientData.is_creadit, ");
		sql.append("clientData.creadit,clientData.company_name,clientData.company_place,clientData.live_place, ");
		sql.append("clientData.contract_name_one,clientData.contract_relation_one,clientData.contract_telphone_one, ");
		sql.append("clientData.contract_name_two,clientData.contract_relation_two,clientData.contract_telphone_two, ");
		sql.append("clientData.contract_name_three,clientData.contract_relation_three,clientData.contract_telphone_three, ");
		sql.append("clientData.contract_name_four,clientData.contract_relation_four,clientData.contract_telphone_four, ");
		sql.append("clientData.contract_name_five,clientData.contract_relation_five,clientData.contract_telphone_five ");
		sql.append("from client_data clientData ");
		sql.append("where clientData.id='" + clientDataId + "' ");
		Map<String, Object> map = commonModel.baseOne(sql.toString());
		return BaseResponse.setSuccess(MapUtil.toCamel(map));
	}

	@Override
	public BaseResponse getClientMore(RequestPager requestPager, String employeeUserId, Integer isHanle) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select clientData.id as client_data_id,clientData.telphone_number,clientData.real_name,clientData.is_real,clientData.service_password, ");
		sql.append("clientData.phone_use_year,clientData.identification_card_number,clientData.seseam_score,clientData.naughty_score, ");
		sql.append("clientData.real_education,clientData.is_house_loan,clientData.is_accumulation_fund,clientData.is_creadit, ");
		sql.append("clientData.creadit,clientData.company_name,clientData.company_place,clientData.live_place, ");
		sql.append("clientData.contract_name_one,clientData.contract_relation_one,clientData.contract_telphone_one, ");
		sql.append("clientData.contract_name_two,clientData.contract_relation_two,clientData.contract_telphone_two, ");
		sql.append("clientData.contract_name_three,clientData.contract_relation_three,clientData.contract_telphone_three, ");
		sql.append("clientData.contract_name_four,clientData.contract_relation_four,clientData.contract_telphone_four, ");
		sql.append("clientData.contract_name_five,clientData.contract_relation_five,clientData.contract_telphone_five,clientData.handle_descript ");
		sql.append("from client_data clientData ");
		sql.append("where clientData.is_fill=1  ");
		//查看处理的只能查看自己处理的
		if(isHanle==1) 
			sql.append("and clientData.employee_two_user_id='" + employeeUserId + "' ");
		sql.append("and clientData.is_hanle=" + isHanle + " ");
		sql.append("ORDER BY clientData.fill_date asc,clientData.fill_time asc ");
		PageInfo<Map<String, Object>> pageInfo = commonModel.basePage(requestPager, sql.toString());
		pageInfo.setList(ListUtil.toCamel(pageInfo.getList()));
		return BaseResponse.setSuccess(pageInfo);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse handle(String clientDataId, String employeeUserId, String handleDescript,String employeeTwoUserNumber) throws Exception {
		//查询 具体操作人和操作编号是否一致
		StringBuilder searchSql=new StringBuilder();
		searchSql.append("select count(id) from employee_user where id='"+employeeUserId+"' and employee_number='"+employeeTwoUserNumber+"'");
		int searchCount=commonModel.baseCount(searchSql.toString());
		if(searchCount>0) {
			StringBuilder sql = new StringBuilder();
			sql.append("update client_data set is_hanle=1,handle_descript='" + handleDescript + "',handle_date=" + DateUtil.formatCurrentDate() + ",handle_time=" + DateUtil.formatCurrentTime() + ",employee_two_user_id='" + employeeUserId + "' ");
			sql.append("where id='" + clientDataId + "'");
			int count = commonModel.baseUpdate(sql.toString());
			return BaseResponse.countResponse("处理资料", count);			
		}else {
			return BaseResponse.setFailure("审核部员工仅能处理自己的业务.");
		}
	}

	@Override
	public BaseResponse statics(String employeeUserId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		synchronized (this) {
			Integer today = DateUtil.formatCurrentDate();
			// 1：今日
			StringBuilder todaySql = new StringBuilder();
			todaySql.append("select count(clientData.id) as number from client_data clientData ");
			todaySql.append("where clientData.is_fill = 1 and clientData.is_hanle=1 and clientData.employee_two_user_id='" + employeeUserId + "' and clientData.handle_date=" + today);
			int todayCount = commonModel.baseCount(todaySql.toString());
			map.put("todayCount", todayCount);
			// 2:本周
			StringBuilder weekSql = new StringBuilder();
			Integer startDate = DateUtil.getRealWeek();
			weekSql.append("select count(clientData.id) as number from client_data clientData ");
			weekSql.append("where clientData.is_fill = 1 and clientData.is_hanle=1 and clientData.employee_two_user_id='" + employeeUserId + "' and clientData.handle_date<=" + today + " and clientData.handle_date>=" + startDate);
			int weekCount = commonModel.baseCount(weekSql.toString());
			map.put("weekCount", weekCount);
			// 3:本月
			StringBuilder monthSql = new StringBuilder();
			monthSql.append("select count(clientData.id) as number from client_data clientData ");
			monthSql.append("where clientData.is_fill = 1 and clientData.is_hanle=1 and clientData.employee_two_user_id='" + employeeUserId + "' and CAST(clientData.handle_date/100 as UNSIGNED INTEGER)=" + today / 100);
			int monthCount = commonModel.baseCount(monthSql.toString());
			map.put("monthCount", monthCount);
			// 4:未处理量
			StringBuilder unHandleSql = new StringBuilder();
			unHandleSql.append("select count(clientData.id) as number from client_data clientData ");
			unHandleSql.append("where clientData.is_fill = 1 and clientData.is_hanle=0 and clientData.employee_two_user_id='" + employeeUserId + "'");
			int unHandleCount = commonModel.baseCount(unHandleSql.toString());
			map.put("unHandleCount", unHandleCount);
		}
		return BaseResponse.setSuccess(map);
	}

	@Override
	public BaseResponse handleStatics(String employeeUserId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		synchronized (this) {
			Integer today = DateUtil.formatCurrentDate();
			StringBuilder handleSql = new StringBuilder();
			handleSql.append("select count(clientData.id) as number from client_data clientData ");
			handleSql.append("where clientData.is_fill = 1 and clientData.is_hanle=1 and clientData.employee_two_user_id='" + employeeUserId + "' and clientData.handle_date=" + today);
			int handleCount = commonModel.baseCount(handleSql.toString());
			map.put("handleCount", handleCount);

			StringBuilder unHandleSql = new StringBuilder();
			unHandleSql.append("select count(clientData.id) as number from client_data clientData ");
			unHandleSql.append("where clientData.is_fill = 1 and clientData.is_hanle=0 ");
			int unHandleCount = commonModel.baseCount(unHandleSql.toString());
			map.put("unHandleCount", unHandleCount);
		}
		return BaseResponse.setSuccess(map);
	}

	@Override
	public BaseResponse exportExcel(String employeeUserId, Integer exportDate) throws Exception {
		String txtPath = "/usr/local/updownload/export/" + employeeUserId + "-" + DateUtil.formatCurrentDate() + "-" + DateUtil.formatCurrentTime() + ".xls";
		StringBuilder sql = new StringBuilder();
		sql.append("select clientData.id as client_data_id,clientData.telphone_number,clientData.real_name,clientData.is_real,clientData.service_password, ");
		sql.append("clientData.phone_use_year,clientData.identification_card_number,clientData.seseam_score,clientData.naughty_score, ");
		sql.append("clientData.real_education,clientData.is_house_loan,clientData.is_accumulation_fund,clientData.is_creadit, ");
		sql.append("clientData.creadit,clientData.company_name,clientData.company_place,clientData.live_place, ");
		sql.append("clientData.contract_name_one,clientData.contract_relation_one,clientData.contract_telphone_one, ");
		sql.append("clientData.contract_name_two,clientData.contract_relation_two,clientData.contract_telphone_two, ");
		sql.append("clientData.contract_name_three,clientData.contract_relation_three,clientData.contract_telphone_three, ");
		sql.append("clientData.contract_name_four,clientData.contract_relation_four,clientData.contract_telphone_four, ");
		sql.append("clientData.contract_name_five,clientData.contract_relation_five,clientData.contract_telphone_five,clientData.handle_descript ");
		sql.append("INTO OUTFILE '" + txtPath + "' character set gbk FIELDS TERMINATED BY '\t' from client_data clientData ");
		sql.append("where clientData.is_fill=1 and clientData.employee_two_user_id='" + employeeUserId + "' ");
		sql.append("and clientData.is_hanle=1 and clientData.handle_date=" + exportDate + " ");
		commonModel.baseQuery(sql.toString());
		return BaseResponse.setSuccess(MapUtil.set("download", txtPath));
	}

	@Override
	public BaseResponse searchStatics(String employeeUserId, Integer startDate, Integer endDate) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(clientData.id) as number,clientData.handle_date from client_data clientData ");
		sql.append("where clientData.is_hanle=1 and clientData.employee_two_user_id='" + employeeUserId + "' and clientData.handle_date>=" + startDate + " and clientData.handle_date<=" + endDate + " ");
		sql.append("group by clientData.handle_date");
		List<Map<String, Object>> result = commonModel.baseList(sql.toString());
		return BaseResponse.setSuccess(ListUtil.toCamel(result));
	}

	@Override
	public BaseResponse staticsm(String employeeUserId) throws Exception {
		// 今日统计
		Map<String, Object> resultMap = new HashMap<String, Object>();
		synchronized (this) {
			Integer today = DateUtil.formatCurrentDate();
			/*************** 今日统计 ****************/
			// 1:今日拨打电话
			resultMap.put("today", todayStatics(employeeUserId));
			
			StringBuilder todayhandleSql = new StringBuilder();
			todayhandleSql.append("select count(clientData.id) as number  from client_data clientData ");
			todayhandleSql.append("where clientData.employee_one_user_id='" + employeeUserId + "' and (clientData.is_dial=0 or (clientData.is_dial=1 and clientData.is_purpose is null))");
			
			
			/****************** 本周统计 *******************/
			Integer startDate = DateUtil.subtractWeek(today);
			Map<String, Object> weekMap = new HashMap<String, Object>();
			// 1:今日拨打电话
			StringBuilder weekDailSql = new StringBuilder();
			weekDailSql.append("select count(clientData.id) as number  from client_data clientData ");
			weekDailSql.append("where clientData.is_dial=1 and clientData.is_purpose is null and  clientData.employee_one_user_id='" + employeeUserId + "' and clientData.dial_date<=" + today + " and clientData.dial_date>=" + startDate);
			weekMap.put("weekDialCount", commonModel.baseCount(weekDailSql.toString()));
			// 2:今日有意向
			StringBuilder weekRetionSql = new StringBuilder();
			weekRetionSql.append("select count(clientData.id) as number  from client_data clientData ");
			weekRetionSql.append("where clientData.is_dial=1 and clientData.employee_one_user_id='" + employeeUserId + "' and clientData.is_purpose=1 and clientData.dial_date<=" + today + " and clientData.dial_date>=" + startDate);
			weekMap.put("weekIntentCount", commonModel.baseCount(weekRetionSql.toString()));
			// 3:今日无意向
			StringBuilder weekNRetionSql = new StringBuilder();
			weekNRetionSql.append("select count(clientData.id) as number  from client_data clientData ");
			weekNRetionSql.append("where clientData.is_dial=1 and clientData.employee_one_user_id='" + employeeUserId + "' and clientData.is_purpose=0 and clientData.dial_date<=" + today + " and clientData.dial_date>=" + startDate);
			weekMap.put("weekNoIntentCount", commonModel.baseCount(weekNRetionSql.toString()));
			// 4:待处理
			weekMap.put("weekNoHandleCount", commonModel.baseCount(todayhandleSql.toString()));
			//weekMap.put("weekisFill", commonModel.baseCount(weekNRetionSql.toString()));

			// 5:本周统计
			StringBuilder weekFinishSql = new StringBuilder();
			weekFinishSql.append("SELECT COUNT(id) AS weekNumber from client_data WHERE employee_one_user_id = '"+employeeUserId+"' ");
			weekFinishSql.append("AND is_fill = 1 AND fill_date >= "+startDate+" AND fill_date <= "+today+" ");
			weekMap.put("weekFinishNumber", commonModel.baseCount(weekFinishSql.toString()));		
			
			resultMap.put("week", weekMap);
			/********************* 本月统计 *******************/
			Map<String, Object> monthMap = new HashMap<String, Object>();
			// 1:今日拨打电话
			StringBuilder monthDailSql = new StringBuilder();
			monthDailSql.append("select count(clientData.id) as number  from client_data clientData ");
			monthDailSql.append("where clientData.is_dial=1 and clientData.is_purpose is null and clientData.employee_one_user_id='" + employeeUserId + "' and CAST(clientData.dial_date/100 as UNSIGNED INTEGER)=" + today / 100);
			monthMap.put("monthDialCount", commonModel.baseCount(monthDailSql.toString()));
			// 2:今日有意向
			StringBuilder monthRetionSql = new StringBuilder();
			monthRetionSql.append("select count(clientData.id) as number  from client_data clientData ");
			monthRetionSql.append("where clientData.is_dial=1 and clientData.employee_one_user_id='" + employeeUserId + "' and clientData.is_purpose=1 and CAST(clientData.dial_date/100 as UNSIGNED INTEGER)=" + today / 100);
			monthMap.put("monthyIntentCount", commonModel.baseCount(monthRetionSql.toString()));
			// 3:今日无意向
			StringBuilder monthNRetionSql = new StringBuilder();
			monthNRetionSql.append("select count(clientData.id) as number  from client_data clientData ");
			monthNRetionSql.append("where clientData.is_dial=1 and clientData.employee_one_user_id='" + employeeUserId + "' and clientData.is_purpose=0 and CAST(clientData.dial_date/100 as UNSIGNED INTEGER)=" + today / 100);
			monthMap.put("monthNoIntentCount", commonModel.baseCount(monthNRetionSql.toString()));
			// 4:待处理
			monthMap.put("monthNoHandleCount", commonModel.baseCount(todayhandleSql.toString()));
			
			// 5:本月统计
			StringBuilder monthFinishSql = new StringBuilder();
			monthFinishSql.append("SELECT COUNT(id) AS monthNumber from client_data WHERE employee_one_user_id = '"+employeeUserId+"' "); 
			monthFinishSql.append("AND is_fill = 1 AND cast(fill_date/100 as UNSIGNED INTEGER) = "+today/100+" ");
			monthMap.put("monthFinishNumber", commonModel.baseCount(monthFinishSql.toString()));	
			resultMap.put("month", monthMap);
		}
		return BaseResponse.setSuccess(resultMap);
	}

	private Map<String, Object> todayStatics(String employeeUserId) throws Exception {
		Integer today = DateUtil.formatCurrentDate();
		Map<String, Object> todayMap = new HashMap<String, Object>();
		// 1:今日未拨通
		StringBuilder todayDailSql = new StringBuilder();
		todayDailSql.append("select count(clientData.id) as number  from client_data clientData ");
		todayDailSql.append("where clientData.is_dial=1 and clientData.is_purpose is null and  clientData.employee_one_user_id='" + employeeUserId + "' and clientData.dial_date=" + today);
		todayMap.put("todayDialCount", commonModel.baseCount(todayDailSql.toString()));
		// 2:今日有意向
		StringBuilder todayRetionSql = new StringBuilder();
		todayRetionSql.append("select count(clientData.id) as number  from client_data clientData ");
		todayRetionSql.append("where clientData.is_dial=1 and clientData.employee_one_user_id='" + employeeUserId + "' and clientData.is_purpose=1 and clientData.dial_date=" + today);
		todayMap.put("todayIntentCount", commonModel.baseCount(todayRetionSql.toString()));
		// 3:今日无意向
		StringBuilder todayNRetionSql = new StringBuilder();
		todayNRetionSql.append("select count(clientData.id) as number  from client_data clientData ");
		todayNRetionSql.append("where clientData.is_dial=1 and clientData.employee_one_user_id='" + employeeUserId + "' and clientData.is_purpose=0 and clientData.dial_date=" + today);
		todayMap.put("todayNoIntentCount", commonModel.baseCount(todayNRetionSql.toString()));
		// 4:待处理
		StringBuilder todayhandleSql = new StringBuilder();
		todayhandleSql.append("select count(clientData.id) as number  from client_data clientData ");
		todayhandleSql.append("where clientData.employee_one_user_id='" + employeeUserId + "' and (clientData.is_dial=0 or (clientData.is_dial=1 and (clientData.is_purpose is null)))");
		todayMap.put("todayNoHandleCount", commonModel.baseCount(todayhandleSql.toString()));
		// 5：已结完成
		StringBuilder todayFinishNumber = new StringBuilder();
		todayFinishNumber.append("SELECT COUNT(id) AS daynumber from client_data WHERE employee_one_user_id = '"+employeeUserId+"' "); 
		todayFinishNumber.append("AND is_fill = 1 AND fill_date = "+today+" ");
		todayMap.put("todayFinishNumber", commonModel.baseCount(todayFinishNumber.toString()));
		
		return todayMap;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse todayStaticsm(String employeeUserId) throws Exception {
		return BaseResponse.setSuccess(todayStatics(employeeUserId));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse intention(String employeeUserId, String clientDataId) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("update client_data set is_dial=1,is_purpose=1,employee_one_user_id='" + employeeUserId + "',dial_date=" + DateUtil.formatCurrentDate() + ",dial_time=" + DateUtil.formatCurrentTime() + " where  id='" + clientDataId + "'");
		return BaseResponse.countResponse("有意向成功.", commonModel.baseUpdate(sql.toString()));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse noIntention(String employeeUserId, String clientDataId) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("update client_data set is_dial=1,is_purpose=0,employee_one_user_id='" + employeeUserId + "',dial_date=" + DateUtil.formatCurrentDate() + ",dial_time=" + DateUtil.formatCurrentTime() + " where id='" + clientDataId + "'");
		return BaseResponse.countResponse("无意向成功.", commonModel.baseUpdate(sql.toString()));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse dial(String employeeUserId, String clientDataId) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("update client_data set is_dial=1,employee_one_user_id='" + employeeUserId + "',dial_date=" + DateUtil.formatCurrentDate() + ",dial_time=" + DateUtil.formatCurrentTime() + " where id='" + clientDataId + "'");
		return BaseResponse.countResponse("拨打成功.", commonModel.baseUpdate(sql.toString()));
	}

	@Override
	public BaseResponse getClientMorem(RequestPager requestPager, String employeeUserId, Integer isDial) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select clientData.id AS client_data_id,clientData.telphone_number,(CASE WHEN clientData.is_dial = 0 THEN '未拨打电话.' \r\n" + "   WHEN clientData.is_dial = 1 THEN '已拨打过电话' \r\n" + "ELSE '其他' END) as tips from client_data clientData ");
		sql.append("where clientData.employee_one_user_id='" + employeeUserId + "'");
		if (isDial == 1)
			sql.append(" and clientData.is_dial=1 and (clientData.is_purpose is null) ");
		else
			sql.append(" and clientData.is_dial=0 ");
		PageInfo<Map<String, Object>> pageInfo = commonModel.basePage(requestPager, sql.toString());
		pageInfo.setList(ListUtil.toCamel(pageInfo.getList()));
		return BaseResponse.setSuccess(pageInfo);
	}

	@Override
	public BaseResponse searchStaticsm(String employeeUserId, Integer startDate, Integer endDate, Integer status) throws Exception {
		StringBuilder builder = new StringBuilder();
		if (DateUtil.isSameMonth(startDate, endDate)) {// 同一个月		
			switch (status) {
			case 0://为拨通
				builder.append("select count(clientData.id) as number,clientData.dial_date as create_data from client_data clientData ");
				builder.append("where clientData.dial_date>=" + startDate + " and clientData.dial_date<=" + endDate + " and clientData.employee_one_user_id='" + employeeUserId + "' ");								
				builder.append("and  clientData.is_dial=1 and clientData.is_purpose is null ");
				builder.append(" group by clientData.dial_date");
				break;
			case 1://有效
				builder.append("select count(clientData.id) as number,clientData.dial_date as create_data from client_data clientData ");
				builder.append("where clientData.dial_date>=" + startDate + " and clientData.dial_date<=" + endDate + " and clientData.employee_one_user_id='" + employeeUserId + "' ");				
				builder.append("and clientData.is_dial=1 and clientData.is_purpose=1 ");
				builder.append(" group by clientData.dial_date");
				break;
			case 2://无效
				builder.append("select count(clientData.id) as number,clientData.dial_date as create_data from client_data clientData ");
				builder.append("where clientData.dial_date>=" + startDate + " and clientData.dial_date<=" + endDate + " and clientData.employee_one_user_id='" + employeeUserId + "' ");								
				builder.append("and clientData.is_dial=1 and clientData.is_purpose=0 ");
				builder.append(" group by clientData.dial_date");
				break;
			case 3://未完成
				builder.append("select count(clientData.id) as number,clientData.create_date as create_data from client_data clientData ");
				builder.append("where clientData.create_date>=" + startDate + " and clientData.create_date<=" + endDate + " and clientData.employee_one_user_id='" + employeeUserId + "' ");												
				builder.append("and (clientData.is_dial=0 or (clientData.is_dial=1 and clientData.is_purpose is null)) ");
				builder.append(" group by clientData.create_date");
				break;
			case 4://已成交
				builder.append("select count(clientData.id) as number,clientData.fill_date as create_data from client_data clientData ");
				builder.append("where clientData.fill_date>=" + startDate + " and clientData.fill_date<=" + endDate + " and clientData.employee_one_user_id='" + employeeUserId + "' ");												
				builder.append("and clientData.is_fill=1 ");
				builder.append(" group by clientData.fill_date");
				break;
			default:				
			}
		} else {// 不同一个月
			endDate = endDate / 100;
			startDate = startDate / 100;
			switch (status) {
			case 0:// 
				builder.append("select count(clientData.id) as number,cast(clientData.dial_date/100 as UNSIGNED INTEGER) as create_data from client_data clientData ");
				builder.append("where cast(clientData.dial_date/100 as UNSIGNED INTEGER)>=" + startDate + " and cast(clientData.dial_date/100 as UNSIGNED INTEGER)<=" + endDate + " and clientData.employee_one_user_id='" + employeeUserId + "' ");								
				builder.append("and  clientData.is_dial=1 and clientData.is_purpose is null ");
				builder.append("group by cast(clientData.dial_date/100 as UNSIGNED INTEGER)");
				break;
			case 1://有效
				builder.append("select count(clientData.id) as number,cast(clientData.dial_date/100 as UNSIGNED INTEGER) as create_data from client_data clientData ");
				builder.append("where cast(clientData.dial_date/100 as UNSIGNED INTEGER)>=" + startDate + " and cast(clientData.dial_date/100 as UNSIGNED INTEGER)<=" + endDate + " and clientData.employee_one_user_id='" + employeeUserId + "' ");				
				builder.append("and clientData.is_dial=1 and clientData.is_purpose=1 ");
				builder.append(" group by cast(clientData.dial_date/100 as UNSIGNED INTEGER)");
				break;				
			case 2://无效
				builder.append("select count(clientData.id) as number,cast(clientData.dial_date/100 as UNSIGNED INTEGER) as create_data from client_data clientData ");
				builder.append("where cast(clientData.dial_date/100 as UNSIGNED INTEGER)>=" + startDate + " and cast(clientData.dial_date/100 as UNSIGNED INTEGER)<=" + endDate + " and clientData.employee_one_user_id='" + employeeUserId + "' ");								
				builder.append("and clientData.is_dial=1 and clientData.is_purpose=0 ");
				builder.append("group by cast(clientData.dial_date/100 as UNSIGNED INTEGER)");
				break;
			case 3://未完成
				builder.append("select count(clientData.id) as number,cast(clientData.create_date/100 as UNSIGNED INTEGER) as create_data from client_data clientData ");
				builder.append("where cast(clientData.create_date/100 as UNSIGNED INTEGER)>=" + startDate + " and cast(clientData.create_date/100 as UNSIGNED INTEGER)<=" + endDate + " and clientData.employee_one_user_id='" + employeeUserId + "' ");												
				builder.append("and (clientData.is_dial=0 or (clientData.is_dial=1 and clientData.is_purpose is null)) ");
				builder.append("group by cast(clientData.create_date/100 as UNSIGNED INTEGER)");
				break;
			case 4://已成交
				builder.append("select count(clientData.id) as number,cast(clientData.fill_date/100 as UNSIGNED INTEGER) as create_data from client_data clientData ");
				builder.append("where cast(clientData.fill_date/100 as UNSIGNED INTEGER)>=" + startDate + " and cast(clientData.fill_date/100 as UNSIGNED INTEGER)<=" + endDate + " and clientData.employee_one_user_id='" + employeeUserId + "' ");												
				builder.append("and clientData.is_fill=1 ");
				builder.append("group by cast(clientData.fill_date/100 as UNSIGNED INTEGER)");
				break;
			default:
			}	
		}
		List<Map<String, Object>> resultList = commonModel.baseList(builder.toString());
		return BaseResponse.setSuccess(ListUtil.toCamel(resultList));

	}
	
	@Override
	public BaseResponse loanStaticsm(String employeeUserId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		Integer today = DateUtil.formatCurrentDate();
		Integer startDate = DateUtil.subtractWeek(today);
		Integer weekDate=today/100;
		// 日
		StringBuilder daySql = new StringBuilder();
		daySql.append("SELECT COUNT(*) AS daynumber from client_data WHERE employee_one_user_id = '"+employeeUserId+"' "); 
		daySql.append("AND is_fill = 1 AND fill_date = "+DateUtil.formatCurrentDate()+" ");
		map.put("dayNumber", commonModel.baseOne(daySql.toString()));
		// 周
		StringBuilder weekSql = new StringBuilder();
		weekSql.append("SELECT COUNT(*) AS weekNumber from client_data WHERE employee_one_user_id = '"+employeeUserId+"' ");
		weekSql.append("AND is_fill = 1 AND fill_date >= "+startDate+" AND fill_date <= "+today+" ");
		map.put("weekNumber", commonModel.baseOne(weekSql.toString()));		
		// 月
		StringBuilder monthSql = new StringBuilder();
		monthSql.append("SELECT COUNT(*) AS monthNumber from client_data WHERE employee_one_user_id = '"+employeeUserId+"' "); 
	    monthSql.append("AND is_fill = 1 AND cast(fill_date/100 as UNSIGNED INTEGER) = "+weekDate+" ");
		map.put("monthNumber", commonModel.baseOne(monthSql.toString()));	
		return  BaseResponse.setSuccess(map);
	}

	@Override
	public BaseResponse conversionLoan(String employeeUserId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		Integer today = DateUtil.formatCurrentDate();
		Integer startDate = DateUtil.subtractWeek(today);
		Integer monthDate=today/100;
		Integer yearDate=monthDate/100;
		
		// 今日
		StringBuilder daySql = new StringBuilder();
		daySql.append("select CONCAT(IFNULL(handle,0)/total*100,'%') as dayPercent from  ");
		daySql.append("(select COUNT(clientData.id) as total,clientData.dial_date as handle_date from client_data clientData where clientData.employee_one_user_id='"+employeeUserId+"' ");
		daySql.append("and dial_date="+today+" and is_purpose is not null )a LEFT JOIN ");
		daySql.append("(select COUNT(clientData.id) as handle,clientData.fill_date as handle_date from client_data clientData ");
		daySql.append("where clientData.is_fill = 1 and clientData.employee_one_user_id='"+employeeUserId+"'  ");
		daySql.append("and fill_date="+today+" and is_fill=1 )b ON a.handle_date=b.handle_date  ");
		
		Map<String, Object> dayMap=commonModel.baseOne(daySql.toString());
		
		map.putAll(dayMap);
		
		//本周		
		StringBuilder weekSql = new StringBuilder();
		weekSql.append("select CONCAT(IFNULL(handle,0)/total*100,'%') as weekPercent from  ");
		weekSql.append("(select COUNT(clientData.id) as total,CAST(clientData.dial_date/100 as UNSIGNED INTEGER) as handle_date from client_data clientData where clientData.employee_one_user_id='"+employeeUserId+"' ");
		weekSql.append("and dial_date<="+today+" and dial_date>="+startDate+" and is_purpose is not null )a LEFT JOIN ");
		weekSql.append("(select COUNT(clientData.id) as handle,CAST(clientData.fill_date/100 as UNSIGNED INTEGER) as handle_date from client_data clientData ");
		weekSql.append("where clientData.is_fill = 1 and clientData.employee_one_user_id='"+employeeUserId+"'  ");
		weekSql.append("and fill_date<="+today+" and fill_date>="+startDate+" and is_fill=1 )b ON a.handle_date=b.handle_date  ");
		map.putAll(commonModel.baseOne(weekSql.toString()));
		
		//本月
		StringBuilder monthSql = new StringBuilder();
		monthSql.append("select CONCAT(IFNULL(handle,0)/total*100,'%') as monthPercent from  ");
		monthSql.append("(select COUNT(clientData.id) as total,CAST(clientData.dial_date/100 as UNSIGNED INTEGER) as handle_date from client_data clientData where clientData.employee_one_user_id='"+employeeUserId+"' ");
		monthSql.append("and  CAST(dial_date/100 as UNSIGNED INTEGER)="+monthDate+" and is_purpose is not null )a LEFT JOIN ");
		monthSql.append("(select COUNT(clientData.id) as handle,CAST(clientData.fill_date/100 as UNSIGNED INTEGER) as handle_date from client_data clientData ");
		monthSql.append("where clientData.is_fill = 1 and clientData.employee_one_user_id='"+employeeUserId+"'  ");
		monthSql.append("and CAST(fill_date/100 as UNSIGNED INTEGER)="+monthDate+" and is_fill=1 )b ON a.handle_date=b.handle_date  ");
		map.putAll(commonModel.baseOne(monthSql.toString()));		

		// 本年
		StringBuilder yearSql = new StringBuilder();
		yearSql.append("select CONCAT(IFNULL(handle,0)/total*100,'%') as yearPercent from  ");
		yearSql.append("(select COUNT(clientData.id) as total,CAST(clientData.dial_date/10000 as UNSIGNED INTEGER) as handle_date from client_data clientData where clientData.employee_one_user_id='"+employeeUserId+"' ");
		yearSql.append("and  CAST(dial_date/10000 as UNSIGNED INTEGER)="+yearDate+" and is_purpose is not null )a LEFT JOIN ");
		yearSql.append("(select COUNT(clientData.id) as handle,CAST(clientData.fill_date/10000 as UNSIGNED INTEGER) as handle_date from client_data clientData ");
		yearSql.append("where clientData.is_fill = 1 and clientData.employee_one_user_id='"+employeeUserId+"'  ");
		yearSql.append("and CAST(fill_date/10000 as UNSIGNED INTEGER)="+yearDate+" and is_fill=1 )b ON a.handle_date=b.handle_date  ");
		map.putAll(commonModel.baseOne(yearSql.toString()));	
		return BaseResponse.setSuccess(map);
	}

	/**
	 * * 功能描述：修改客服的地电话状态
	 * 开发者：zhouhua
	 * 时间：201806915
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse updatePhone(String employeeUserId, String phone) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("  UPDATE client_data SET is_dial = 2 WHERE employee_one_user_id = '"+employeeUserId+"' AND telphone_number ="+phone);
		int update = commonModel.baseUpdate(sql.toString());
		if(update > 0) {
			return BaseResponse.setSuccessMsg("修改成功");
		}
		return BaseResponse.setSuccessMsg("修改失败");
	}

	/**
	 * 
	 */
	@Override
	public BaseResponse monthByDay(String employeeUserId) throws Exception {
		Integer today = DateUtil.formatCurrentDate();
		Integer monthDate=today/100;
        StringBuilder sql = new StringBuilder();
		sql.append("select CONCAT(IFNULL(handle,0)/total*100,'%') as yearPercent,IFNULL(handle,0),total,a.create_date from ");
		sql.append("(select COUNT(clientData.id) as total,clientData.dial_date as create_date from client_data clientData  where clientData.employee_one_user_id='"+employeeUserId+"' ");
		sql.append("and CAST(clientData.dial_date/100 as UNSIGNED INTEGER)="+monthDate+" and clientData.is_dial=1 and clientData.is_purpose is not null  GROUP BY clientData.dial_date) a LEFT JOIN ");
		sql.append("(select COUNT(clientData.id) as handle,clientData.fill_date as create_date  from client_data clientData ");
		sql.append("where clientData.is_fill = 1 and clientData.employee_one_user_id='"+employeeUserId+"'	 ");
		sql.append("and CAST(clientData.fill_date/100 as UNSIGNED INTEGER)="+monthDate+" GROUP BY clientData.fill_date) b  ON a.create_date=b.create_date ");
		List<Map<String,Object>> map = commonModel.baseList(sql.toString());
		return BaseResponse.setSuccess(map);
	}

/*	@Override
	public BaseResponse getPercentConversion(String employeeOneUserId,Integer handleDate) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		synchronized (this) {
			//本年分发总数
			StringBuilder yearSql = new StringBuilder();
			yearSql.append("select count(clientData.id) as number from client_data clientData ");
			yearSql.append("where  clientData.employee_one_user_id='" + employeeOneUserId + "' "
					     + " and CAST(clientData.handle_date/1000 as UNSIGNED INTEGER)<=" + handleDate / 1000
					     + " and CAST(clientData.handle_date/1000 as UNSIGNED INTEGER)>=" + (handleDate / 1000-12));
			int yearCount = commonModel.baseCount(yearSql.toString());						
		     //本年处理总数
		    StringBuilder yearSql02 = new StringBuilder();
		    yearSql02.append("select count(clientData.id) as number from client_data clientData ");
		    yearSql02.append("where clientData.is_fill = 1 and clientData.is_hanle=1 and clientData.employee_one_user_id='" + employeeOneUserId + "' "
				          + " and CAST(clientData.handle_date/1000 as UNSIGNED INTEGER)<=" + handleDate / 1000
				          + " and CAST(clientData.handle_date/1000 as UNSIGNED INTEGER)>=" + (handleDate / 1000-12));
			int yearCount02 = commonModel.baseCount(yearSql02.toString());
			 //本年转化率
		     Double yearPercent=(double) (yearCount02/yearCount);
			 map.put("yearPercent", yearPercent);
			 
			//月分发总数
			StringBuilder monthSql = new StringBuilder();
			monthSql.append("select count(clientData.id) as number from client_data clientData ");
			monthSql.append("where  clientData.employee_one_user_id='" + employeeOneUserId + "' "
					     + " and CAST(clientData.handle_date/100 as UNSIGNED INTEGER)=" + handleDate / 100);	    
			int monthCount = commonModel.baseCount(monthSql.toString());
		     //月处理总数
		    StringBuilder monthSql02 = new StringBuilder();
			monthSql02.append("select count(clientData.id) as number from client_data clientData ");
			monthSql02.append("where clientData.is_fill = 1 and clientData.is_hanle=1 and clientData.employee_one_user_id='" + employeeOneUserId + "' "
				           + " and CAST(clientData.handle_date/100 as UNSIGNED INTEGER)=" + handleDate / 100);
			int monthCount02 = commonModel.baseCount(monthSql02.toString());
			 //月转化率
		     Double monthPercent=(double) (monthCount02/monthCount);
			 map.put("monthPercent", monthPercent);
		
		     //月每天转化率
			 StringBuilder todaySql = new StringBuilder();
			 int num=handleDate/100000;
	         for (int i = 0; i <num; i++) {
	 		    
	        	 todaySql.append("select count(clientData.id) as number from client_data clientData ");
	        	 todaySql.append("where  clientData.employee_one_user_id='" + employeeOneUserId + "' "
						     + " and CAST(clientData.handle_date as UNSIGNED INTEGER)=" + (handleDate-i));	    
				int Count = commonModel.baseCount(monthSql.toString());
				map.put((handleDate-i)+"todayPercent", Count);
			}
			 
			 
			 
		}
			
		return  BaseResponse.setSuccess(map);
	}*/


/**
 * 月度转化率
 */
	@Override
	public BaseResponse getPercentByMonth(String employeeUserId) throws Exception {		
		Integer today = DateUtil.formatCurrentDate();
		Integer monthDate=today/10000;
        StringBuilder sql = new StringBuilder();
		sql.append("select CONCAT(IFNULL(handle,0)/total*100,'%') as yearPercent,IFNULL(handle,0),total,a.create_date from ");
		sql.append("(select COUNT(clientData.id) as total,CAST(clientData.dial_date/100 as UNSIGNED INTEGER) as create_date from client_data clientData  where clientData.employee_one_user_id='"+employeeUserId+"' ");
		sql.append("and CAST(clientData.dial_date/10000 as UNSIGNED INTEGER)="+monthDate+" and clientData.is_dial=1 and clientData.is_purpose is not null  GROUP BY CAST(clientData.dial_date/100 as UNSIGNED INTEGER)) a LEFT JOIN ");
		sql.append("(select COUNT(clientData.id) as handle, CAST(clientData.fill_date/100 as UNSIGNED INTEGER) as create_date  from client_data clientData ");
		sql.append("where clientData.is_fill = 1 and clientData.employee_one_user_id='"+employeeUserId+"'	 ");
		sql.append("and CAST(clientData.fill_date/10000 as UNSIGNED INTEGER)="+monthDate+" GROUP BY CAST(clientData.fill_date/100 as UNSIGNED INTEGER)) b  ON a.create_date=b.create_date ");
		List<Map<String,Object>> map = commonModel.baseList(sql.toString());
		return BaseResponse.setSuccess(map);
	}
/**
 * 年度转化率
 */
	@Override
	public BaseResponse getPercentByYear(String employeeUserId) throws Exception {
		Integer today = DateUtil.formatCurrentDate();
		Integer monthDate=today/10000;
        StringBuilder sql = new StringBuilder();
		sql.append("select CONCAT(IFNULL(handle,0)/total*100,'%') as yearPercent,IFNULL(handle,0),total,a.create_date from ");
		sql.append("(select COUNT(clientData.id) as total,CAST(clientData.dial_date/10000 as UNSIGNED INTEGER) as create_date from client_data clientData  where clientData.employee_one_user_id='"+employeeUserId+"' ");
		sql.append("and CAST(clientData.dial_date/10000 as UNSIGNED INTEGER)="+monthDate+" and clientData.is_dial=1 and clientData.is_purpose is not null  GROUP BY CAST(clientData.dial_date/10000 as UNSIGNED INTEGER)) a LEFT JOIN ");
		sql.append("(select COUNT(clientData.id) as handle, CAST(clientData.fill_date/10000 as UNSIGNED INTEGER) as create_date  from client_data clientData ");
		sql.append("where clientData.is_fill = 1 and clientData.employee_one_user_id='"+employeeUserId+"'	 ");
		sql.append("and CAST(clientData.fill_date/10000 as UNSIGNED INTEGER)="+monthDate+" GROUP BY CAST(clientData.fill_date/10000 as UNSIGNED INTEGER)) b  ON a.create_date=b.create_date ");
		List<Map<String,Object>> map = commonModel.baseList(sql.toString());
		return BaseResponse.setSuccess(map);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse errNumber(String employeeUserId, String clientDataId) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("update client_data set is_dial=2,employee_one_user_id='" + employeeUserId + "',dial_date=" + DateUtil.formatCurrentDate() + ",dial_time=" + DateUtil.formatCurrentTime() + " where id='" + clientDataId + "'");
		return BaseResponse.countResponse("异常号码.", commonModel.baseUpdate(sql.toString()));
	}
	
	//异常电话率
	@Override
	public BaseResponse errNumLoan(String employeeUserId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		Integer today = DateUtil.formatCurrentDate();
		Integer startDate = DateUtil.subtractWeek(today);
		Integer monthDate=today/100;
		Integer yearDate=monthDate/100;
		
		// 今日
		StringBuilder daySql = new StringBuilder();
		daySql.append("select CONCAT(IFNULL(handle,0)/total*100,'%') as dayPercent from  ");
		daySql.append("(select COUNT(clientData.id) as total,clientData.dial_date as handle_date from client_data clientData where clientData.employee_one_user_id='"+employeeUserId+"' ");
		daySql.append("and dial_date="+today+" and is_purpose is not null )a LEFT JOIN ");
		daySql.append("(select COUNT(clientData.id) as handle,clientData.dial_date as handle_date from client_data clientData ");
		daySql.append("where clientData.is_dial = 2 and clientData.employee_one_user_id='"+employeeUserId+"'  ");
		daySql.append("and dial_date="+today+" )b ON a.handle_date=b.handle_date  ");
		
		Map<String, Object> dayMap=commonModel.baseOne(daySql.toString());
		
		map.putAll(dayMap);
		
		//本周		
		StringBuilder weekSql = new StringBuilder();
		weekSql.append("select CONCAT(IFNULL(handle,0)/total*100,'%') as weekPercent from  ");
		weekSql.append("(select COUNT(clientData.id) as total,CAST(clientData.dial_date/100 as UNSIGNED INTEGER) as handle_date from client_data clientData where clientData.employee_one_user_id='"+employeeUserId+"' ");
		weekSql.append("and dial_date<="+today+" and dial_date>="+startDate+" and is_purpose is not null )a LEFT JOIN ");
		weekSql.append("(select COUNT(clientData.id) as handle,CAST(clientData.dial_date/100 as UNSIGNED INTEGER) as handle_date from client_data clientData ");
		weekSql.append("where clientData.is_dial = 2 and clientData.employee_one_user_id='"+employeeUserId+"'  ");
		weekSql.append("and dial_date<="+today+" and dial_date>="+startDate+")b ON a.handle_date=b.handle_date  ");
		map.putAll(commonModel.baseOne(weekSql.toString()));
		
		//本月
		StringBuilder monthSql = new StringBuilder();
		monthSql.append("select CONCAT(IFNULL(handle,0)/total*100,'%') as monthPercent from  ");
		monthSql.append("(select COUNT(clientData.id) as total,CAST(clientData.dial_date/100 as UNSIGNED INTEGER) as handle_date from client_data clientData where clientData.employee_one_user_id='"+employeeUserId+"' ");
		monthSql.append("and  CAST(dial_date/100 as UNSIGNED INTEGER)="+monthDate+" and is_purpose is not null )a LEFT JOIN ");
		monthSql.append("(select COUNT(clientData.id) as handle,CAST(clientData.dial_date/100 as UNSIGNED INTEGER) as handle_date from client_data clientData ");
		monthSql.append("where clientData.is_dial = 2 and clientData.employee_one_user_id='"+employeeUserId+"'  ");
		monthSql.append("and CAST(dial_date/100 as UNSIGNED INTEGER)="+monthDate+" )b ON a.handle_date=b.handle_date  ");
		map.putAll(commonModel.baseOne(monthSql.toString()));		

		// 本年
		StringBuilder yearSql = new StringBuilder();
		yearSql.append("select CONCAT(IFNULL(handle,0)/total*100,'%') as yearPercent from  ");
		yearSql.append("(select COUNT(clientData.id) as total,CAST(clientData.dial_date/10000 as UNSIGNED INTEGER) as handle_date from client_data clientData where clientData.employee_one_user_id='"+employeeUserId+"' ");
		yearSql.append("and  CAST(dial_date/10000 as UNSIGNED INTEGER)="+yearDate+" and is_purpose is not null )a LEFT JOIN ");
		yearSql.append("(select COUNT(clientData.id) as handle,CAST(clientData.dial_date/10000 as UNSIGNED INTEGER) as handle_date from client_data clientData ");
		yearSql.append("where clientData.is_dial = 2 and clientData.employee_one_user_id='"+employeeUserId+"'  ");
		yearSql.append("and CAST(dial_date/10000 as UNSIGNED INTEGER)="+yearDate+")b ON a.handle_date=b.handle_date  ");
		map.putAll(commonModel.baseOne(yearSql.toString()));	
		return BaseResponse.setSuccess(map);
	}

	@Override
	public BaseResponse callCompletLoan(String employeeUserId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		Integer today = DateUtil.formatCurrentDate();
		Integer startDate = DateUtil.subtractWeek(today);
		Integer monthDate=today/100;
		Integer yearDate=monthDate/100;
		
		// 今日
		StringBuilder daySql = new StringBuilder();
		daySql.append("select CONCAT(IFNULL(handle,0)/total*100,'%') as dayPercent from  ");
		daySql.append("(select COUNT(clientData.id) as total,clientData.dial_date as handle_date from client_data clientData where clientData.employee_one_user_id='"+employeeUserId+"' ");
		daySql.append("and dial_date="+today+" and is_purpose is not null )a LEFT JOIN ");
		daySql.append("(select COUNT(clientData.id) as handle,clientData.dial_date as handle_date from client_data clientData ");
		daySql.append("where clientData.is_dial = 1 and clientData.employee_one_user_id='"+employeeUserId+"'  ");
		daySql.append("and dial_date="+today+" AND is_purpose IS NOT NULL )b ON a.handle_date=b.handle_date  ");
		
		Map<String, Object> dayMap=commonModel.baseOne(daySql.toString());
		
		map.putAll(dayMap);
		
		//本周		
		StringBuilder weekSql = new StringBuilder();
		weekSql.append("select CONCAT(IFNULL(handle,0)/total*100,'%') as weekPercent from  ");
		weekSql.append("(select COUNT(clientData.id) as total,CAST(clientData.dial_date/100 as UNSIGNED INTEGER) as handle_date from client_data clientData where clientData.employee_one_user_id='"+employeeUserId+"' ");
		weekSql.append("and dial_date<="+today+" and dial_date>="+startDate+" and is_purpose is not null )a LEFT JOIN ");
		weekSql.append("(select COUNT(clientData.id) as handle,CAST(clientData.dial_date/100 as UNSIGNED INTEGER) as handle_date from client_data clientData ");
		weekSql.append("where clientData.is_dial = 1 and clientData.employee_one_user_id='"+employeeUserId+"'  ");
		weekSql.append("and dial_date<="+today+" and dial_date>="+startDate+" AND is_purpose IS NOT NULL )b ON a.handle_date=b.handle_date  ");
		map.putAll(commonModel.baseOne(weekSql.toString()));
		
		//本月
		StringBuilder monthSql = new StringBuilder();
		monthSql.append("select CONCAT(IFNULL(handle,0)/total*100,'%') as monthPercent from  ");
		monthSql.append("(select COUNT(clientData.id) as total,CAST(clientData.dial_date/100 as UNSIGNED INTEGER) as handle_date from client_data clientData where clientData.employee_one_user_id='"+employeeUserId+"' ");
		monthSql.append("and  CAST(dial_date/100 as UNSIGNED INTEGER)="+monthDate+" and is_purpose is not null )a LEFT JOIN ");
		monthSql.append("(select COUNT(clientData.id) as handle,CAST(clientData.dial_date/100 as UNSIGNED INTEGER) as handle_date from client_data clientData ");
		monthSql.append("where clientData.is_dial = 1 and clientData.employee_one_user_id='"+employeeUserId+"'  ");
		monthSql.append("and CAST(dial_date/100 as UNSIGNED INTEGER)="+monthDate+" AND is_purpose IS NOT NULL )b ON a.handle_date=b.handle_date  ");
		map.putAll(commonModel.baseOne(monthSql.toString()));		

		// 本年
		StringBuilder yearSql = new StringBuilder();
		yearSql.append("select CONCAT(IFNULL(handle,0)/total*100,'%') as yearPercent from  ");
		yearSql.append("(select COUNT(clientData.id) as total,CAST(clientData.dial_date/10000 as UNSIGNED INTEGER) as handle_date from client_data clientData where clientData.employee_one_user_id='"+employeeUserId+"' ");
		yearSql.append("and  CAST(dial_date/10000 as UNSIGNED INTEGER)="+yearDate+" and is_purpose is not null )a LEFT JOIN ");
		yearSql.append("(select COUNT(clientData.id) as handle,CAST(clientData.dial_date/10000 as UNSIGNED INTEGER) as handle_date from client_data clientData ");
		yearSql.append("where clientData.is_dial = 1 and clientData.employee_one_user_id='"+employeeUserId+"'  ");
		yearSql.append("and CAST(dial_date/10000 as UNSIGNED INTEGER)="+yearDate+" AND is_purpose IS NOT NULL )b ON a.handle_date=b.handle_date  ");
		map.putAll(commonModel.baseOne(yearSql.toString()));	
		return BaseResponse.setSuccess(map);
	}

}
