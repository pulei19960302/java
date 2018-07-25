package com.qim.loan.service.client.impl;

import static org.mockito.Mockito.ignoreStubs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.qim.loan.bo.client.ClientDataBo;
import com.qim.loan.core.model.BaseModel;
import com.qim.loan.core.model.CommonModel;
import com.qim.loan.core.service.impl.BaseServiceImpl;
import com.qim.loan.entity.client.ClientData;
import com.qim.loan.model.client.ClientDataModel;
import com.qim.loan.model.employee.EmployeeUserModel;
import com.qim.loan.service.client.ClientDataService;
import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.common.GsonUtil;
import com.qim.loan.util.common.ListUtil;
import com.qim.loan.util.common.MapUtil;
import com.qim.loan.util.common.MobilUtil;
import com.qim.loan.util.common.StringUtil;
import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.paramter.RequestPager;

import freemarker.core.ReturnInstruction.Return;

/**
 *
 * 类名: ClientDataServiceImpl 描述: 客户数据ServiceImpl层(公共) 创建者: 冯子文 创建时间: 2018年05月29日
 * 17:14:59 更新者: 冯子文 更新时间: 2018年05月29日 17:14:59
 */
@Service("clientDataService")
public class ClientDataServiceImpl extends BaseServiceImpl<ClientData> implements ClientDataService {

	@Autowired
	@Qualifier("clientDataModel")
	private ClientDataModel clientDataModel;

	@Autowired
	@Qualifier("employeeUserModel")
	private EmployeeUserModel employeeUserModel;

	@Autowired
	private CommonModel commonModel;

	@Override
	public BaseModel<ClientData> getModel() {
		return clientDataModel;
	}

	@Override
	public BaseResponse getSelect() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select  employeeUser.id as employee_two_user_id,employeeUser.employee_telphone,employeeUser.employee_number, ");
		sql.append("CONCAT(left(employeeUser.employee_real_name,1), ");
		sql.append("(CASE WHEN employeeUser.gender = '0' THEN '先生'  ");
		sql.append("      WHEN employeeUser.gender = '1' THEN '女士' ");
		sql.append("      ELSE '其他' END)) as nick_name  ");
		sql.append("from employee_department  employeeDepartment ");
		sql.append("left join employee_user employeeUser on employeeUser.employee_department_id=employeeDepartment.id and employeeUser.is_online=0 ");
		sql.append("where employeeDepartment.is_online=0 and employeeDepartment.is_track=1 ");
		List<Map<String, Object>> result = commonModel.baseList(sql.toString());
		return BaseResponse.setSuccess(ListUtil.toCamel(result));
	}

	@Override
	public BaseResponse getByEmployeeId(String employeeId) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select employeeUser.id as employeeId,employeeUser.employee_number,employeeUser.employee_telphone from employee_user employeeUser ");
		sql.append(" where employeeUser.is_online=0 ");
		Map<String, Object> map = commonModel.baseOne(sql.toString());
		return BaseResponse.setSuccess(MapUtil.toCamel(map));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse retention(ClientData clientData) throws Exception {
		String telphoneNumber = clientData.getTelphoneNumber();
		//已提交未处理
		ClientData unHandleResult = clientDataModel.getOne(ClientDataBo.setUnhandleTelphoneNumber(telphoneNumber));
		if(unHandleResult==null) {//处理
			ClientData result = clientDataModel.getOne(ClientDataBo.setTelphoneNumber(telphoneNumber));
			clientData.setIsFill(1);// 已填写
			clientData.setFillDate(DateUtil.formatCurrentDate());
			clientData.setFillTime(DateUtil.formatCurrentTime());
			if (result == null) {
				int count = clientDataModel.insert(clientData);
				return BaseResponse.countResponse("填写资料", count,"业务人员正在加急处理中,请耐心等待.");
			} else {
				clientData.setId(result.getId());
				int count = clientDataModel.updateById(clientData);
				return BaseResponse.countResponse("填写资料", count,"业务人员正在加急处理中,请耐心等待.");
			}			
		}else {//提示信息
			return BaseResponse.setSuccessMsg("您已提交过资料,业务人员正在加班加点处理中,请耐心等待.");
		}
	}

	@Override
	public BaseResponse distributionAll() throws Exception {
		BaseResponse baseResponse = new BaseResponse();
		// 获取未分配客户Id总数
		StringBuilder cli_count_Sql = new StringBuilder();
		cli_count_Sql.append("select id   from client_data ");
		cli_count_Sql.append(" where employee_one_user_id is null or employee_one_user_id='' ");
		List<Map<String, Object>> cli_List = commonModel.baseList(cli_count_Sql.toString());
		System.out.println("客户数量" + cli_List.size());
		// 获取部门一员工ID集合
		StringBuilder emp01_List_Sql = new StringBuilder();
		emp01_List_Sql.append("select id as employeeOneUserId  from employee_user   ");
		emp01_List_Sql.append("where employee_department_id='44444444444' ");
		List<Map<String, Object>> emp01_List = commonModel.baseList(emp01_List_Sql.toString());
		System.out.println("员工一数量" + emp01_List.size());
		// 全部均分
		StringBuilder distributionAll01_Sql = new StringBuilder();
		int num = cli_List.size() / emp01_List.size();// 得到每个员工要分配的客户量
		int Remainder = cli_List.size() % emp01_List.size();// 获取平均分配后剩余的客户量
		int flag01 = 0;
		for (int a = 0; a < emp01_List.size(); a++) {
			System.out.println("员工01");
			String employeeOneUserId = (String) emp01_List.get(a).get("employeeOneUserId");
			distributionAll01_Sql.append("update client_data set employee_one_user_id=" + employeeOneUserId + " ");
			distributionAll01_Sql.append(" where id in\r\n" + "( SELECT * from (select id  from client_data where employee_one_user_id is null or employee_one_user_id='' LIMIT 0," + num + ")a)");
			flag01 = commonModel.baseUpdate(distributionAll01_Sql.toString());
			distributionAll01_Sql.delete(0, distributionAll01_Sql.length());// 循环清空StringBuilder的存的sql语句
			flag01 += flag01;
		}
		// 如果平均分配后有剩余的客户，再次循环分配
		if (Remainder != 0) {
			for (int a = 0; a < emp01_List.size(); a++) {
				System.out.println("员工01");
				String employeeOneUserId = (String) emp01_List.get(a).get("employeeOneUserId");
				distributionAll01_Sql.append("update client_data set employee_one_user_id=" + employeeOneUserId + " ");
				distributionAll01_Sql.append(" where id in\r\n" + "( SELECT * from (select id  from client_data where employee_one_user_id is null or employee_one_user_id='' LIMIT 0,1)a)");
				flag01 += commonModel.baseUpdate(distributionAll01_Sql.toString());
				distributionAll01_Sql.delete(0, distributionAll01_Sql.length());

			}
		}
		if (flag01 > 0) {
			baseResponse.setMsg("分配成功");
			System.out.print(baseResponse.toString());
			return baseResponse;
		} else {
			baseResponse.setMsg("分配失败");
			System.out.print(baseResponse.toString());
			return baseResponse;
		}
	}

	@Override
	public BaseResponse clientDataUserById(String clientDataId) throws Exception {

		ClientData clientData = clientDataModel.getOneById(clientDataId);
		return BaseResponse.setSuccess(clientData);
	}

	@Override
	public BaseResponse getById(String id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT  ");
		sql.append("id,telphone_number,service_password,is_real,phone_use_year,identification_card_number,seseam_score,naughty_score,real_education,is_house_loan,is_accumulation_fund,is_creadit,creadit," + "company_name,company_place,live_place,contract_name_one,contract_telphone_one,contract_relation_one,contract_name_two,contract_telphone_two,contract_relation_two,contract_name_three,contract_telphone_three,contract_relation_three," + "contract_name_four,contract_telphone_four,contract_relation_four,contract_name_five,contract_telphone_five,contract_relation_five");
		sql.append(" FROM client_data  ");
		sql.append("WHERE 1=1 and id='" + id + "'");
		Map<String, Object> map = commonModel.baseOne(sql.toString());
		return BaseResponse.setSuccess(MapUtil.toStrip(map));
	}

	@Override
	public BaseResponse clientDayNumber() throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(id) from client_data WHERE identification_card_number is NOT NULL  AND  seseam_score is NOT NULL AND");
		sql.append(" real_education is NOT NULL  AND is_accumulation_fund is NOT NULL  AND  create_date  = " + DateUtil.formatCurrentDate() + "");
		int dayNumber = commonModel.baseCount(sql.toString());
		map.put("dayNumber", dayNumber);
		return BaseResponse.setSuccess(map);
	}

	@Override
	public BaseResponse clientMonthNumber() throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		StringBuilder sql = new StringBuilder();
		String strMonth = String.valueOf(DateUtil.formatCurrentDate() / 100);
		strMonth += "%";
		sql.append("SELECT COUNT(id) from client_data WHERE identification_card_number is NOT NULL AND seseam_score is NOT NULL AND");
		sql.append(" real_education is NOT NULL  AND is_accumulation_fund is NOT NULL AND create_date like '" + strMonth + "' ");
		int monthNumber = commonModel.baseCount(sql.toString());

		map.put("monthNumber", monthNumber);
		return BaseResponse.setSuccess(map);
	}

	@Override
	public BaseResponse clientWeekNumber() throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(id) from client_data WHERE identification_card_number is NOT NULL AND seseam_score is NOT NULL AND ");
		sql.append(" real_education is NOT NULL  AND is_accumulation_fund is NOT NULL AND create_date >= " + DateUtil.getRealWeek());
		int weekNumber = commonModel.baseCount(sql.toString());
		map.put("weekNumber", weekNumber);
		return BaseResponse.setSuccess(map);
	}

	@Override
	public BaseResponse clientSurplusNumber() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuilder dayNumbersql = new StringBuilder();
		// 查询今日剩余量
		dayNumbersql.append(" SELECT COUNT(create_date) from client_data WHERE create_date=" + DateUtil.formatCurrentDate());
		int dayNumber = commonModel.baseCount(dayNumbersql.toString());
		map.put("dayNumber", dayNumber);
		// 查询本周 剩余量
		StringBuilder weekSql = new StringBuilder();
		weekSql.append(" SELECT count(create_date) from client_data WHERE create_date >= " + DateUtil.getRealWeek());
		int weekNumber = commonModel.baseCount(weekSql.toString());
		map.put("weekNumber", weekNumber);
		// 查询本月剩余量
		StringBuilder monthSql = new StringBuilder();
		monthSql.append(" SELECT COUNT(*) from client_data WHERE is_hanle = 0 AND create_date='" + DateUtil.formatCurrentDate() / 100 + "%' ");
		int monthNumber = commonModel.baseCount(monthSql.toString());
		map.put("monthNumber", monthNumber);
		return BaseResponse.setSuccess(map);
	}

	@Override
	public BaseResponse queryClientData(ClientData clientData, RequestPager requestPager, Integer seseamScoreTwo) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT  ");
		sql.append("id,telphone_number,service_password,is_real,phone_use_year,identification_card_number,seseam_score,is_house_loan,is_accumulation_fund,is_creadit");
		sql.append(" FROM client_data  ");
		sql.append("WHERE 1=1 ");

		if (clientData.getCreateDate() != null) {
			sql.append(" AND create_date >= " + clientData.getCreateDate());
		}
		if (clientData.getCreateTime() != null) {
			sql.append(" AND create_date <= " + clientData.getCreateTime());
		}
		if (clientData.getIsReal() != null) {// null 0 1
			sql.append(" AND is_real = " + clientData.getIsReal());
		}
		if (clientData.getIsCreadit() != null) {
			sql.append(" AND is_creadit = " + clientData.getIsCreadit());
		}
		if (clientData.getIsHouseLoan() != null) {
			sql.append(" AND is_house_loan = " + clientData.getIsHouseLoan());
		}
		if (clientData.getIsAccumulationFund() != null) {
			sql.append(" AND is_accumulation_fund = " + clientData.getIsAccumulationFund());
		}
		if (clientData.getSeseamScore() != null) {
			sql.append(" AND seseam_score >= " + clientData.getSeseamScore());
		}
		if (seseamScoreTwo != null) {
			sql.append(" AND seseam_score <= " + seseamScoreTwo);
		}
		if (clientData.getTelphoneNumber() != null) {
			sql.append(" AND telphone_number = " + clientData.getTelphoneNumber());
		}
		PageInfo<Map<String, Object>> pageInfo = commonModel.basePage(requestPager, sql.toString());
		pageInfo.setList(ListUtil.toCamel(pageInfo.getList()));
		return BaseResponse.setSuccess(pageInfo);
	}

	@Override
	public BaseResponse queryClientDatas(RequestPager requestPager, ClientData clientData, Integer startDate, Integer endDate, Integer startSeseamScore, Integer endSeseamScore) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT  ");
		sql.append("id,telphone_number,service_password,is_real,phone_use_year,identification_card_number,seseam_score,naughty_score,real_education,is_house_loan,is_accumulation_fund,is_creadit,creadit," + "company_name,company_place,live_place,contract_name_one,contract_telphone_one,contract_relation_one,contract_name_two,contract_telphone_two,contract_relation_two,contract_name_three,contract_telphone_three,contract_relation_three," + "contract_name_four,contract_telphone_four,contract_relation_four,contract_name_five,contract_telphone_five,contract_relation_five");
		sql.append(" FROM client_data  ");
		sql.append("WHERE 1=1 and is_fill=1 ");
		sql.append(" AND fill_date >= " + startDate);
		sql.append(" AND fill_date <= " + endDate);
		if (clientData.getIsReal() != null && (clientData.getIsReal() == 0 || clientData.getIsReal() == 1))
			sql.append(" AND is_real = " + clientData.getIsReal());
		if (clientData.getIsCreadit() != null && (clientData.getIsCreadit() == 0 || clientData.getIsCreadit() == 1))
			sql.append(" AND is_creadit = " + clientData.getIsCreadit());
		if (clientData.getIsHouseLoan() != null && (clientData.getIsHouseLoan() == 0 || clientData.getIsHouseLoan() == 1))
			sql.append(" AND is_house_loan = " + clientData.getIsHouseLoan());
		if (clientData.getIsAccumulationFund() != null && (clientData.getIsAccumulationFund() == 0 || clientData.getIsAccumulationFund() == 1))
			sql.append(" AND is_accumulation_fund = " + clientData.getIsAccumulationFund());
		if (startSeseamScore != null)
			sql.append(" AND seseam_score >= " + startSeseamScore);
		if (endSeseamScore != null)
			sql.append(" AND seseam_score <= " + endSeseamScore);
		if (StringUtil.isNotNull(clientData.getTelphoneNumber()) && MobilUtil.isMobile(clientData.getTelphoneNumber()))
			sql.append(" AND telphone_number like '%" + clientData.getTelphoneNumber() + "%' ");
		PageInfo<Map<String, Object>> pageInfo = commonModel.basePage(requestPager, sql.toString());
		pageInfo.setList(ListUtil.toCamel(pageInfo.getList()));
		return BaseResponse.setSuccess(pageInfo);
	}

	@Override
	public BaseResponse systemStatisticalChart(Integer startData, Integer endData) throws Exception {
		ArrayList<Object> map = new ArrayList<Object>();

		StringBuilder importSql = new StringBuilder();
		StringBuilder createDataRqSql = new StringBuilder();
		StringBuilder retentionSql = new StringBuilder();

		retentionSql.append("  SELECT distinct ub.create_date,count(ub.id) as number FROM client_data ub ");
		importSql.append(" SELECT distinct ub.create_date,count(ub.id) as number FROM client_data ub ");
		createDataRqSql.append(" SELECT distinct ub.create_date,count(ub.id) as number FROM client_data ub  ");

		if (startData == null && endData == null) {
			importSql.append(" WHERE  ub.create_date>= " + DateUtil.getRealWeek());
			createDataRqSql.append(" WHERE  ub.create_date>=" + DateUtil.getRealWeek());
			retentionSql.append(" WHERE  ub.create_date>=" + DateUtil.getRealWeek());
		} else if (startData != null && endData != null) {
			importSql.append(" WHERE  ub.create_date>= " + startData + " AND ub.create_date<= " + endData);
			createDataRqSql.append(" WHERE  ub.create_date>=" + startData + " AND ub.create_date<= " + endData);
			retentionSql.append(" WHERE  ub.create_date>= " + startData + " AND ub.create_date<= " + endData);
		} else if (startData != null) {
			importSql.append(" WHERE  ub.create_date>= " + startData);
			createDataRqSql.append(" WHERE  ub.create_date>= " + startData);
			retentionSql.append(" WHERE  ub.create_date>= " + startData);
		} else if (endData != null) {
			importSql.append(" AND ub.create_date<= " + endData);
			createDataRqSql.append(" AND ub.create_date<= " + endData);
			retentionSql.append(" AND ub.create_date<= " + endData);
		}
		importSql.append(" GROUP BY ub.create_date ");
		createDataRqSql.append(" GROUP BY ub.create_date ");
		retentionSql.append(" GROUP BY ub.create_date ");

		List<Map<String, Object>> importData = commonModel.baseList(importSql.toString());
		List<Map<String, Object>> createData = commonModel.baseList(createDataRqSql.toString());
		List<Map<String, Object>> retentionData = commonModel.baseList(retentionSql.toString());
		map.add(importData);
		map.add(retentionData);
		map.add(createData);
		return BaseResponse.setSuccess(map);
	}

	@Override
	public BaseResponse ImportNumber() throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		synchronized (this) {
			// 日
			StringBuilder DaySql = new StringBuilder();
			DaySql.append(" SELECT COUNT(create_date) from client_data WHERE create_date = " + DateUtil.formatCurrentDate());
			int dayNumber = commonModel.baseCount(DaySql.toString());
			map.put("dayNumber", dayNumber);
			// 周
			StringBuilder weekSql = new StringBuilder();
			weekSql.append("SELECT COUNT(create_date) from client_data WHERE create_date = " + DateUtil.getRealWeek());
			int weekNumber = commonModel.baseCount(weekSql.toString());
			map.put("weekNumber", weekNumber);
			// 月
			StringBuilder monthSql = new StringBuilder();
			monthSql.append("SELECT COUNT(create_date) from client_data WHERE create_date >= " + DateUtil.formatCurrentDate() / 100);
			int monthNumber = commonModel.baseCount(monthSql.toString());
			map.put("monthNumber", monthNumber);
		}
		return BaseResponse.setSuccess(map);
	}

	@Override
	public BaseResponse staticsRetentionNumber() throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		synchronized (this) {
			// 本日
			Integer today = DateUtil.formatCurrentDate();
			Integer startDate = DateUtil.subtractWeek(today);
			Integer monthDay = Integer.valueOf(today / 100);
			StringBuilder sql = new StringBuilder();
			sql.append(" select count(id) as number from client_data where is_fill=1  ");
			int dayNumber = commonModel.baseCount(sql.toString() + " and fill_date=" + today);
			map.put("todayNumber", dayNumber);
			// 本周
			int weekNumber = commonModel.baseCount(sql.toString() + " and fill_date>=" + startDate + " and fill_date<=" + today);
			map.put("weekNumber", weekNumber);
			// 本月
			int monthNumber = commonModel.baseCount(sql.toString() + " and cast(fill_date/100 as UNSIGNED INTEGER)=" + monthDay);
			map.put("monthNumber", monthNumber);
		}
		return BaseResponse.setSuccess(map);
	}

	@Override
	public BaseResponse staticsImportNumber() throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		synchronized (this) {
			// 本日
			Integer today = DateUtil.formatCurrentDate();
			Integer startDate = DateUtil.subtractWeek(today);
			Integer monthDay = Integer.valueOf(today / 100);
			StringBuilder sql = new StringBuilder();
			sql.append(" select count(id) as number from client_data where telphone_number is NOT NULL ");
			int dayNumber = commonModel.baseCount(sql.toString() + " and create_date=" + today);
			map.put("todayNumber", dayNumber);
			// 本周
			int weekNumber = commonModel.baseCount(sql.toString() + " and create_date>=" + startDate + " and create_date<=" + today);
			map.put("weekNumber", weekNumber);
			// 本月
			int monthNumber = commonModel.baseCount(sql.toString() + " and cast(create_date/100 as UNSIGNED INTEGER)=" + monthDay);
			map.put("monthNumber", monthNumber);
		}
		return BaseResponse.setSuccess(map);
	}

	/**
	 * 
	 * 类名:staticsSurplusNumber 功能描述:统计未分发量余量 创建者:冯子文 创建时间: 2018年6月11日 上午1:24:14
	 * 更新者:冯子文 更新时间: 2018年6月11日 上午1:24:14
	 */
	@Override
	public BaseResponse staticsSurplusNumber() throws Exception {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();

		synchronized (this) {
			// 本周未分发量
			Integer today = DateUtil.formatCurrentDate();
			Integer startDate = DateUtil.subtractWeek(today);
			Integer monthDay = Integer.valueOf(today / 100);
			StringBuilder sql = new StringBuilder();
			sql.append("select count(clientData.id) from client_data clientData ");
			sql.append("where clientData.employee_one_user_id is null ");
			int weekNumber = commonModel.baseCount(sql.toString() + " and clientData.create_date>=" + startDate + " and clientData.create_date<=" + today);
			map.put("weekNumber", weekNumber);
			// 本月未分发量
			int monthNumber = commonModel.baseCount(sql.toString() + " and cast(clientData.create_date/100 as UNSIGNED INTEGER) =" + monthDay);
			map.put("monthNumber", monthNumber);
			// 未分发量
			int unDistributeNumber = commonModel.baseCount(sql.toString());
			map.put("unDistributeNumber", unDistributeNumber);
		}
		System.out.println(GsonUtil.toJson(map));
		return BaseResponse.setSuccess(map);
	}

	@Override
	public BaseResponse staticsIndex(Integer startDate, Integer endDate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse distributeAll() throws Exception {
		// 获取未分配数量
		StringBuilder unDistributeNumberSql = new StringBuilder();
		unDistributeNumberSql.append("select count(id) as number from client_data where employee_one_user_id is null ");
		int unDistributeNumber = commonModel.baseCount(unDistributeNumberSql.toString());
		if (unDistributeNumber == 0)
			return BaseResponse.setFailure("分发数量不能为0.");
		// 员工部门1数量(在线)
		StringBuilder departMentOneNumberSql = new StringBuilder();
		departMentOneNumberSql.append("select employeeUser.id as id from employee_user employeeUser ");
		departMentOneNumberSql.append("left join employee_department employeeDepartment on employeeDepartment.id=employeeUser.employee_department_id  ");
		departMentOneNumberSql.append("where employeeUser.is_online=0 and employeeDepartment.is_online=0 and employeeDepartment.is_track=0 ");
		List<Map<String, Object>> departMentOneNumberLs = commonModel.baseList(departMentOneNumberSql.toString());
		if (ListUtil.isNull(departMentOneNumberLs))
			return BaseResponse.setFailure("请先增加部门一的员工.");
		double departMentOneNumber = (double) departMentOneNumberLs.size();
		int number = (int) Math.ceil(unDistributeNumber / departMentOneNumber);
		// 均分
		int count = distributeMulti(number, departMentOneNumberLs);
		return BaseResponse.setSuccessMsg("分发成功");
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	private int distributeMulti(int number, List<Map<String, Object>> departMentOneNumberLs) throws Exception {
		int count = 0;
		int index = 0;
		for (Map<String, Object> tmp : departMentOneNumberLs) {
			count += distributeSingle(number, index, String.valueOf(tmp.get("id")));
			index++;
		}
		return count;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	private int distributeSingle(int number, Integer index, String id) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("update client_data set distribute_date=" + DateUtil.formatCurrentDate() + ",distribute_time=" + DateUtil.formatCurrentTime() + ",employee_one_user_id='" + id + "' ");
		sql.append("where id in ");
		sql.append("(select * from ");
		sql.append("(select id as number from client_data where employee_one_user_id is null limit 0," + (number) + ") ");
		sql.append("as clientData) ");
		return commonModel.baseUpdate(sql.toString());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse distributePart(Integer everyNumber) throws Exception {
		// 员工部门1数量(在线)
		StringBuilder departMentOneNumberSql = new StringBuilder();
		departMentOneNumberSql.append("select employeeUser.id as id from employee_user employeeUser ");
		departMentOneNumberSql.append("left join employee_department employeeDepartment on employeeDepartment.id=employeeUser.employee_department_id  ");
		departMentOneNumberSql.append("where employeeUser.is_online=0 and employeeDepartment.is_online=0 and employeeDepartment.is_track=0 ");
		List<Map<String, Object>> departMentOneNumberLs = commonModel.baseList(departMentOneNumberSql.toString());
		if (ListUtil.isNull(departMentOneNumberLs))
			return BaseResponse.setFailure("请先增加部门一的员工.");
		// 均分
		int count = distributeMulti(everyNumber, departMentOneNumberLs);
		return BaseResponse.countResponse("数据分发", count);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse distributePerson(Integer number, String employeeUserId) throws Exception {
		int count = distributeSingle(number, 0, employeeUserId);
		return BaseResponse.countResponse("数据分发", count);
	}

	@Override
	public BaseResponse getDepartmentOneEmployee() throws Exception {
		StringBuilder departMentOneNumberSql = new StringBuilder();
		departMentOneNumberSql.append("select employeeUser.id,employeeUser.employee_number,employeeUser.employee_telphone,employeeUser.employee_real_name,employeeUser.employee_user_name from employee_user employeeUser ");
		departMentOneNumberSql.append("left join employee_department employeeDepartment on employeeDepartment.id=employeeUser.employee_department_id and employeeDepartment.is_online=0 ");
		departMentOneNumberSql.append("where employeeUser.is_online=0 and employeeDepartment.is_track=0 ");
		List<Map<String, Object>> list = commonModel.baseList(departMentOneNumberSql.toString());
		return BaseResponse.setSuccess(ListUtil.toCamel(list));
	}

	@Override
	public BaseResponse getEmployeeTwoIdByNumber(String number) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT employessUser.employee_number AS employee_two_number,employessUser.id as employee_two_user_id from employee_user employessUser  ");
		sql.append("LEFT JOIN employee_department employeeDepartment ON employessUser.employee_department_id = employeeDepartment.id ");
		sql.append("WHERE employeeDepartment.is_track = 0 AND employessUser.employee_number = '" + number + "'  ");
		Map<String, Object> map = commonModel.baseOne(sql.toString());
		if (MapUtil.isNull(map))
			return BaseResponse.setFailure("审核部工号错误，请重新输入！");
		return BaseResponse.setSuccess(MapUtil.toCamel(map));
	}

	@Override
	public BaseResponse getEmployeeOneNumberByTelphoneNumber(String phone) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT employeeUser.employee_number AS employeeOneNumber from employee_user employeeUser ");
		sql.append("LEFT JOIN client_data clientData ON clientData.employee_one_user_id=employeeUser.id ");
		sql.append("WHERE clientData.telphone_number = '" + phone + "'  ");
		Map<String, Object> map = commonModel.baseOne(sql.toString());
		if (MapUtil.isNull(map))
			return BaseResponse.setFailure("未存在此用户手机号！！！");
		return BaseResponse.setSuccess(map);
	}
}
