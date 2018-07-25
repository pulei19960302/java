package com.qim.loan.service.console.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.qim.loan.bo.channel.ChannelBo;
import com.qim.loan.bo.channel.ChannelUserBo;
import com.qim.loan.bo.console.ConsoleUserBo;
import com.qim.loan.bo.employee.EmployeeUserBo;
import com.qim.loan.core.model.BaseModel;
import com.qim.loan.core.model.CommonModel;
import com.qim.loan.core.service.impl.BaseServiceImpl;
import com.qim.loan.entity.channel.Channel;
import com.qim.loan.entity.channel.ChannelUser;
import com.qim.loan.entity.client.ClientData;
import com.qim.loan.entity.console.ConsoleUser;
import com.qim.loan.entity.console.ConsoleUserLoginRecord;
import com.qim.loan.entity.employee.EmployeeDepartment;
import com.qim.loan.entity.employee.EmployeeUser;
import com.qim.loan.model.channel.ChannelModel;
import com.qim.loan.model.channel.ChannelUserModel;
import com.qim.loan.model.console.ConsoleUserLoginRecordModel;
import com.qim.loan.model.console.ConsoleUserModel;
import com.qim.loan.model.employee.EmployeeDepartmentModel;
import com.qim.loan.model.employee.EmployeeUserModel;
import com.qim.loan.service.client.ClientDataService;
import com.qim.loan.service.console.ConsoleUserService;
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
 * 类名: ConsoleUserServiceImpl 描述: 总台用户ServiceImpl层(公共) 创建者: 冯子文 创建时间:
 * 2018年05月29日 17:14:59 更新者: 冯子文 更新时间: 2018年05月29日 17:14:59
 */
@Service("consoleUserService")
public class ConsoleUserServiceImpl extends BaseServiceImpl<ConsoleUser> implements ConsoleUserService {

	@Autowired
	@Qualifier("consoleUserModel")
	private ConsoleUserModel consoleUserModel;

	@Autowired
	@Qualifier("stringCacheFactory")
	private StringCacheFactory stringCacheFactory;

	@Autowired
	@Qualifier("consoleUserLoginRecordModel")
	private ConsoleUserLoginRecordModel consoleUserLoginRecordModel;

	@Autowired
	private CommonModel commonModel;

	@Autowired
	@Qualifier("channelModel")
	private ChannelModel channelModel;

	@Autowired
	@Qualifier("channelUserModel")
	private ChannelUserModel channelUserModel;

	@Autowired
	@Qualifier("employeeDepartmentModel")
	private EmployeeDepartmentModel employeeDepartmentModel;

	@Autowired
	@Qualifier("employeeUserModel")
	private EmployeeUserModel employeeUserModel;
	
	@Autowired
	@Qualifier("clientDataService")
	private ClientDataService clientDataService;

	@Override
	public BaseModel<ConsoleUser> getModel() {
		return consoleUserModel;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse login(ConsoleUserLoginRecord consoleUserLoginRecord, String consoleUserName, String consoleUserPassword) throws Exception {
		ConsoleUser result = consoleUserModel.getOne(ConsoleUserBo.setLogin(consoleUserName, consoleUserPassword));
		if (BeanUtil.isNull(result)) {
			return BaseResponse.setFailure("总后台用户名或密码错误.");
		} else {
			IStringCache userCache = stringCacheFactory.getUser();
			// 1:写入记录
			consoleUserLoginRecord.setConsoleUserId(result.getId());
			consoleUserLoginRecordModel.insert(consoleUserLoginRecord);
			return BaseResponse.setSuccess(MapUtil.setAuthority(userCache, result.getId(), result.getConsoleUserName(), "m"));
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse loginout(String consoleUserId) throws Exception {
		IStringCache userCache = stringCacheFactory.getUser();
		MapUtil.setAuthorityOut(userCache, consoleUserId);
		return BaseResponse.setSuccessMsg("注销用户成功.");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse updatePwd(String consoleUserId, String oldConsoleUserPwd, String consoleUserPwd) throws Exception {
		ConsoleUser result = consoleUserModel.getOne(ConsoleUserBo.updatePwd(consoleUserId, oldConsoleUserPwd));
		if (BeanUtil.isNull(result)) {
			return BaseResponse.setFailure("密码错误.");
		} else {
			ConsoleUserBo.updatePwd(result, consoleUserPwd);
			return BaseResponse.countResponse("修改密码", consoleUserModel.updateById(result));
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse addConsoleUser(ConsoleUser consoleUser) throws Exception {
		ConsoleUserBo.updatePwd(consoleUser, consoleUser.getConsoleUserPassword());
		int count = consoleUserModel.insert(consoleUser);
		return BaseResponse.countResponse("添加总台用户", count);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse updateConsoleUser(ConsoleUser consoleUser) throws Exception {
		int count = consoleUserModel.updateById(consoleUser);
		return BaseResponse.countResponse("修改总台用户资料", count);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse updateConsoleUserPwd(String id, String consoleUserPassword) throws Exception {
		int count = consoleUserModel.updateById(ConsoleUserBo.updatePwd(id, consoleUserPassword));
		return BaseResponse.countResponse("修改总台用户密码", count);
	}

	@Override
	public BaseResponse getConsoleUserList(RequestPager requestPager, String consoleUserName, Integer isOnline) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select consoleUser.id,consoleUser.console_user_name,consoleUser.console_real_name,consoleUser.is_online,consoleUser.create_date,consoleUser.create_time from console_user consoleUser ");
		sql.append("where consoleUser.is_online=" + isOnline + " ");
		if (!StringUtil.isNull(consoleUserName))
			sql.append(" and consoleUser.console_user_name = '" + consoleUserName + "'");
		PageInfo<Map<String, Object>> pageInfo = commonModel.basePage(requestPager, sql.toString());
		pageInfo.setList(ListUtil.toCamel(pageInfo.getList()));
		return BaseResponse.setSuccess(pageInfo);
	}

	@Override
	public BaseResponse getChannelList() throws Exception {
		Channel channel = new Channel();
		channel.setIsOnline(0);// 在线
		List<Channel> list = channelModel.getList("id,channel_name,channel_abbreviate", channel);
		return BaseResponse.setSuccess(list);
	}

	@Override
	public BaseResponse getChannelPage(RequestPager requestPager, String channelId, Integer isOnline) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct channel.id,channel.channel_name,channel.channel_abbreviate,channel.is_online,count(clientData.id) as todayNumber ");
		sql.append("from channel channel ");
		sql.append("left join client_data clientData on clientData.channel_id=channel.id ");
		sql.append("where 1=1 ");
		if(isOnline!=null)
			sql.append("and channel.is_online=" + isOnline + " ");
		if (!StringUtil.isNull(channelId))
			sql.append(" and channel.id='" + channelId + "' ");
		sql.append("group by channel.id ");
		PageInfo<Map<String, Object>> pageInfo = commonModel.basePage(requestPager, sql.toString());
		pageInfo.setList(ListUtil.toCamel(pageInfo.getList()));
		return BaseResponse.setSuccess(pageInfo);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse addChannel(String channelName, String channelAbbreviate, Integer isOnline) throws Exception {
		int count = channelModel.insert(ChannelBo.setChannelName(channelName, channelAbbreviate, isOnline));
		return BaseResponse.countResponse("添加渠道", count);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse updateChannel(Channel channel) throws Exception {
		int count = channelModel.updateById(channel);
		return BaseResponse.countResponse("修改渠道", count);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse addChannelUser(ChannelUser channelUser) throws Exception {
		ChannelUserBo.updatePwd(channelUser, channelUser.getChannelUserPwd());
		int count = channelUserModel.insert(channelUser);
		return BaseResponse.countResponse("添加渠道用户", count);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse updateChannelUser(ChannelUser channelUser) throws Exception {
		int count = channelUserModel.updateById(channelUser);
		return BaseResponse.countResponse("修改渠道用户", count);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse updateChannelUserPwd(String channelUserId, String channelUserPwd, Integer isOnline) throws Exception {
		ChannelUser channelUser = ChannelUserBo.updatePwd(channelUserId, channelUserPwd);
		channelUser.setIsOnline(isOnline);
		int count = channelUserModel.updateById(channelUser);
		return BaseResponse.countResponse("修改渠道用户密码", count);
	}

	@Override
	public BaseResponse getChannelUserPage(RequestPager requestPager, String channelId, String channelUserName) throws Exception {
		Integer today=DateUtil.formatCurrentDate();
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct channelUser.id,channelUser.channel_user_name,channelUser.channel_real_name,channelUser.channel_id,channel.channel_name, ");
		sql.append("channelUser.contract_person,channelUser.contract_telphone,channelUser.create_date,channelUser.create_time,count(clientData.id) as number ");
		sql.append("from  channel_user channelUser ");
		sql.append("left join channel channel on channel.id=channelUser.channel_id ");
		sql.append("left join client_data clientData on clientData.channel_user_id=channelUser.id and clientData.create_date="+today+" ");	
		sql.append("where  channelUser.channel_id='"+channelId+"' "); 
		if(StringUtil.isNotNull(channelUserName))
			sql.append("and channelUser.channel_user_name like '%"+channelUserName+"%' ");
		sql.append("group by channelUser.id ");
		PageInfo<Map<String, Object>> pageInfo =commonModel.basePage(requestPager, sql.toString());
		pageInfo.setList(ListUtil.toCamel(pageInfo.getList()));
		return BaseResponse.setSuccess(pageInfo);
	}

	@Override
	public BaseResponse getUserList(String channelId) throws Exception {
		ChannelUser channelUser = new ChannelUser();
		channelUser.setChannelId(channelId);
		List<ChannelUser> list = channelUserModel.getList("id,channel_user_name,channel_real_name", channelUser);
		return BaseResponse.setSuccess(list);
	}

	@Override
	public BaseResponse staticsByUser(Integer startDate, Integer endDate, String channelId, String channelUserId) throws Exception {
		StringBuilder sql = new StringBuilder();
		Boolean flag = DateUtil.isSameMonth(startDate, endDate);
		if (!flag) {// 不同月
			sql.append("select count(clientData.id) as number,cast(clientData.create_date/100 as UNSIGNED INTEGER) as create_date  FROM client_data clientData ");
			sql.append("where  clientData.channel_id='" + channelId + "' ");
			sql.append("and cast(clientData.create_date/100 as UNSIGNED INTEGER)>= " + startDate / 100 + " and cast(clientData.create_date/100 as UNSIGNED INTEGER)<=" + endDate / 100 + " ");
			if (StringUtil.isNotNull(channelUserId))
				sql.append("and clientData.channel_user_id='" + channelUserId + "' ");
			sql.append("group by cast(clientData.create_date/100 as UNSIGNED INTEGER)  ");
			sql.append("order by cast(clientData.create_date/100 as UNSIGNED INTEGER) asc  ");
		} else {// 同月
			sql.append("select count(clientData.id) as number,clientData.create_date  FROM client_data clientData ");
			sql.append("where  clientData.channel_id='" + channelId + "' ");
			sql.append("and clientData.create_date>= " + startDate + " and  clientData.create_date<=" + endDate + " ");
			if (StringUtil.isNotNull(channelUserId))
				sql.append("and clientData.channel_user_id='" + channelUserId + "' ");
			sql.append("group by clientData.create_date ");
			sql.append("order by clientData.create_date asc");
		}
		return BaseResponse.setSuccess(ListUtil.toCamel(commonModel.baseList(sql.toString())));
	}

	@Override
	public BaseResponse getDepartPage(RequestPager requestPager, Integer isOnline) throws Exception {
		EmployeeDepartment record = new EmployeeDepartment();
		if (isOnline != null)
			record.setIsOnline(isOnline);
		PageInfo<Map<String, Object>> pageInfo = employeeDepartmentModel.getPage(requestPager, record);
		return BaseResponse.setSuccess(pageInfo);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse addDepart(EmployeeDepartment employeeDepartment) throws Exception {
		EmployeeDepartment search = new EmployeeDepartment();
		search.setIsTrack(employeeDepartment.getIsTrack());
		int count = employeeDepartmentModel.getCount(search);
		if (count > 0) {
			return BaseResponse.setFailure("物理主键重复(上下线状态,是否跟踪).");
		} else {
			int addCount = employeeDepartmentModel.insert(employeeDepartment);
			return BaseResponse.countResponse("增加部门", addCount);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse updateDepart(EmployeeDepartment employeeDepartment) throws Exception {
		int addCount = employeeDepartmentModel.updateById(employeeDepartment);
		return BaseResponse.countResponse("修改部门", addCount);
	}

	@Override
	public BaseResponse getEmployeePage(RequestPager requestPager, String employeeDepartmentId) throws Exception {
		EmployeeUser employeeUser = new EmployeeUser();
		employeeUser.setEmployeeDepartmentId(employeeDepartmentId);
		PageInfo<Map<String, Object>> pageInfo = employeeUserModel.getPage(requestPager, employeeUser);
		return BaseResponse.setSuccess(pageInfo);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse addEmployee(EmployeeUser employeeUser) throws Exception {
		EmployeeUserBo.updatePwd(employeeUser, employeeUser.getEmployeeUserPassword());
		int count = employeeUserModel.insert(employeeUser);
		return BaseResponse.countResponse("新增员工", count);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse updateEmployee(EmployeeUser employeeUser) throws Exception {
		int count = employeeUserModel.updateById(employeeUser);
		return BaseResponse.countResponse("修改员工", count);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse updateEmployeePwd(String id, String employeeUserPassword) throws Exception {
		int count = employeeUserModel.updateById(EmployeeUserBo.updatePwd(id, employeeUserPassword));
		return BaseResponse.countResponse("修改密码", count);
	}

	@Override
	public BaseResponse getEmployeeList(String employeeDepartmentId) throws Exception {
		EmployeeUser search=new EmployeeUser();
		search.setEmployeeDepartmentId(employeeDepartmentId);
		return BaseResponse.setSuccess(employeeUserModel.getList(search));		
	}

	@Override
	public BaseResponse statics(Integer startDate, Integer endDate, String employeeDepartmentId, String employeeUserId) throws Exception {
		StringBuilder sql = new StringBuilder();
		Boolean flag = DateUtil.isSameMonth(startDate, endDate);//同月
		Map<String, Object> map=commonModel.baseOne("select is_track from employee_department where id='"+employeeDepartmentId+"'");
		map=MapUtil.toCamel(map);//是否部门二	
		Integer istrack=Integer.valueOf(String.valueOf(map.get("isTrack")));
		Boolean isSecond=(istrack==1)?true:false;
		if(!flag) {//不同月
			if(isSecond) {//第二部门
				sql.append("select distinct count(clientData.id) as number,cast(clientData.handle_date/100 as UNSIGNED INTEGER)  as create_date from client_data clientData ");
				sql.append("left join employee_user employeeUser on  employeeUser.id=clientData.employee_two_user_id  ");
				sql.append("left join employee_department employeeDepartment on employeeDepartment.id=employeeUser.employee_department_id and employeeDepartment.id='"+employeeDepartmentId+"' ");
				sql.append("where cast(clientData.handle_date/100 as UNSIGNED INTEGER)>="+startDate/100+" and cast(clientData.handle_date/100  as UNSIGNED INTEGER)<="+endDate/100+" ");
				if(StringUtil.isNotNull(employeeUserId))
					sql.append("and employeeUser.id='"+employeeUserId+"' ");
				sql.append("GROUP BY cast(clientData.handle_date/100 as UNSIGNED INTEGER) ");
				sql.append("order by cast(clientData.handle_date/100 as UNSIGNED INTEGER) asc ");
			}else {//第一部门
				sql.append("select distinct count(clientData.id) as number,cast(clientData.dial_date/100 as UNSIGNED INTEGER)  as create_date from client_data clientData ");
				sql.append("left join employee_user employeeUser on  employeeUser.id=clientData.employee_one_user_id  ");
				sql.append("left join employee_department employeeDepartment on employeeDepartment.id=employeeUser.employee_department_id and employeeDepartment.id='"+employeeDepartmentId+"' ");
				sql.append("where cast(clientData.dial_date/100 as UNSIGNED INTEGER)>="+startDate/100+" and cast(clientData.dial_date/100 as UNSIGNED INTEGER)<="+endDate/100+" ");
				if(StringUtil.isNotNull(employeeUserId))
					sql.append("and employeeUser.id='"+employeeUserId+"' ");
				sql.append("GROUP BY cast(clientData.dial_date/100 as UNSIGNED INTEGER) ");
				sql.append("order by cast(clientData.dial_date/100 as UNSIGNED INTEGER) asc ");
			}
		}else {//同月
			if(isSecond) {//第二部门
				sql.append("select distinct count(clientData.id) as number,clientData.handle_date  as create_date from client_data clientData ");
				sql.append("left join employee_user employeeUser on  employeeUser.id=clientData.employee_two_user_id  ");
				sql.append("left join employee_department employeeDepartment on employeeDepartment.id=employeeUser.employee_department_id and employeeDepartment.id='"+employeeDepartmentId+"' ");
				sql.append("where clientData.handle_date>="+startDate+" and clientData.handle_date<="+endDate+" ");
				if(StringUtil.isNotNull(employeeUserId))
					sql.append("and employeeUser.id='"+employeeUserId+"' ");								
				sql.append("GROUP BY clientData.handle_date ");
				sql.append("order by clientData.handle_date asc ");
			}else {//第一部门
				sql.append("select distinct count(clientData.id) as number,clientData.dial_date  as create_date from client_data clientData ");
				sql.append("left join employee_user employeeUser on  employeeUser.id=clientData.employee_one_user_id  ");
				sql.append("left join employee_department employeeDepartment on employeeDepartment.id=employeeUser.employee_department_id and employeeDepartment.id='"+employeeDepartmentId+"' ");
				sql.append("where clientData.dial_date>="+startDate+" and clientData.dial_date<="+endDate+" ");
				if(StringUtil.isNotNull(employeeUserId))
					sql.append("and employeeUser.id='"+employeeUserId+"' ");
				sql.append("GROUP BY clientData.dial_date ");
				sql.append("order by clientData.dial_date asc ");
			}
		}
		return BaseResponse.setSuccess(ListUtil.toCamel(commonModel.baseList(sql.toString())));
	}

	@Override
	public BaseResponse totalQuantity() throws Exception {
		Map<String,Integer> map = new HashMap<String,Integer>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(id) from client_data");
		int totalNumber = commonModel.baseCount(sql.toString());
		map.put("totalNumber", totalNumber);
		return BaseResponse.setSuccess(map);
	}

	@Override
	public BaseResponse DistributeNumber() throws Exception {
		Map<String,Integer> map = new HashMap<String,Integer>();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(id) from client_data where employee_one_user_id = '' || employee_one_user_id is NULL");
		int distributeNumber = commonModel.baseCount(sql.toString());
		map.put("distributeNumber", distributeNumber);
		return BaseResponse.setSuccess(map);
	}

	@Override
	public BaseResponse employeeBranchClientData(Integer distributeNumber,Integer peopleNumber)
			 throws Exception {
		StringBuilder sql = new StringBuilder();
		 if(distributeNumber == null) {
			 sql.append(" select id from client_data where employee_one_user_id = '' || employee_one_user_id is NULL");
		 } else {
			 sql.append("  select id from client_data where employee_one_user_id = '' || employee_one_user_id is NULL  LIMIT "+distributeNumber);
		 }
		List<Map<String,Object>> list= commonModel.baseList(sql.toString());
		
		StringBuilder employeeSql = new StringBuilder();
		if(peopleNumber != null) {
		  employeeSql.append("SELECT id FROM employee_user where is_online = 0 ORDER BY create_date DESC LIMIT "+peopleNumber);
		} else {
		  employeeSql.append("SELECT id FROM employee_user where is_online = 0 ORDER BY create_date DESC  ");
		}
		List<Map<String,Object>> employessList = commonModel.baseList(employeeSql.toString());
		if(list.size() > 0) {
			updateEmployeeClientDataUtil(list,employessList);
        	return BaseResponse.setSuccessMsg("分发成功");
        }else {
           return BaseResponse.setSuccessMsg("没有分发量");
        }			
	}

	
	/**
	 * 
	 * 平均分配
	 * @throws Exception 
	 * */ 
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse updateEmployeeClientDataUtil(List<Map<String,Object>> clientDatas,List<Map<String,Object>> employees) throws Exception {
		List<ClientData> dbClientData = new ArrayList<ClientData>();
		List<ClientData> clietList = new ArrayList<ClientData>();
		for (Map<String, Object> mapTwo : clientDatas) {
			ClientData clientdata = new ClientData(); 
			 for (Entry<String,Object> entry : mapTwo.entrySet()) {
				  if(entry.getKey().equals("id")) {
					  clientdata.setId((String)entry.getValue());
					  clietList.add(clientdata);  
				  }
			 }
	  }
		List<String> employeesList = new ArrayList<String>();
		for(Map<String, Object> employee : employees) {
			 for (Entry<String,Object> entry : employee.entrySet()) {
				 if(entry.getKey().equals("id")) {
					 employeesList.add((String)entry.getValue());
				 }
			}
		}
		Integer count = null;
		for (int i = 0; i < clietList.size(); i++) {
			 ClientData clientdata = new ClientData();
			 clientdata.setId(clietList.get(i).getId());
			 clientdata.setEmployeeOneUserId(employeesList.get(i % employeesList.size()));
			 dbClientData.add(clientdata);
		}
		StringBuilder sql = new StringBuilder();
		StringBuilder sql2 = new StringBuilder();
		sql.append(" update client_data ");
		sql.append(" SET distribute_date = 20180609, distribute_time = 20180609,employee_one_user_id = CASE id ");
		for (ClientData clientData : dbClientData) {
			sql.append(" when '"+clientData.getId()+"' then '"+clientData.getEmployeeOneUserId()+"'");		
			sql2.append("'"+clientData.getId()+"',");
		}
		String strSql = sql2.toString().substring(0,sql2.length()-1);
		sql.append(" end  where id IN ("+strSql+")");
		count = commonModel.baseUpdate(sql.toString());			
		return BaseResponse.countResponse("分发失败", count);
	}

	@Override
	public BaseResponse employeeAppointClientData(String employeeId,Integer distributeNumber) throws Exception {
		StringBuilder sql = new StringBuilder();
		Integer count = null;
		if(distributeNumber != null) {
		   sql.append(" select id from client_data where employee_one_user_id = '' || employee_one_user_id is NULL  LIMIT "+ distributeNumber);
		}else {
		   sql.append("  select id from client_data where employee_one_user_id = '' || employee_one_user_id is NULL ");
		}
		List<Map<String,Object>> map = commonModel.baseList(sql.toString());
		System.out.println(map.size());
		if(map.size() == 0) {
			return BaseResponse.setSuccessMsg("无分发数据");
		}
		StringBuilder employeeSql = new StringBuilder();
		List<ClientData> clientDataList = MapToList(map);
		for (ClientData clientData : clientDataList) {
		     if(clientData != null) {
		     employeeSql.append(" update client_data SET employee_one_user_id = '"+employeeId+"' WHERE id = '"+clientData.getId()+"' and distribute_date = "+DateUtil.formatCurrentDate()+" and distribute_time = "+DateUtil.formatCurrentDate());
		     count = commonModel.baseUpdate(employeeSql.toString());
		     if(count > 0) {
		    	 return BaseResponse.countResponse("分发", count);
		     }
		  }
		}
		return BaseResponse.countResponse("分发失败", count);
	}
	
	public List<ClientData> MapToList(List<Map<String,Object>> clientDatas) {
		List<ClientData> clietList = new ArrayList<ClientData>();
		for (Map<String, Object> mapTwo : clientDatas) {
			ClientData clientdata = new ClientData(); 
			 for (Entry<String,Object> entry : mapTwo.entrySet()) {
				  if(entry.getKey().equals("id")) {
					  clientdata.setId((String)entry.getValue());
					  clietList.add(clientdata);  
				  }
			 }
	  }
		return clietList;
	}

	@Override
	public BaseResponse employeeData(RequestPager requestPager,String employeeName) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * from employee_user WHERE is_online = 0  ");
		System.out.println(employeeName);
		if(employeeName != null) {
		  sql.append(" AND employee_user_name = '"+employeeName+"'");
		}
		PageInfo<Map<String, Object>> pageInfo = commonModel.basePage(requestPager, sql.toString());
		return BaseResponse.setSuccess(pageInfo);
	}

	@Override
	public BaseResponse distributeNumber() throws Exception {
		Map<String,Integer> map = new HashMap<String,Integer>();
        StringBuilder sql = new StringBuilder();
        sql.append(" select employee_one_user_id from client_data where employee_one_user_id = '' || employee_one_user_id is NULL ");
        int distributeCount = commonModel.baseCount(sql.toString());
        map.put("distributeCount", distributeCount);
		return BaseResponse.setSuccess(map);
	}


	@Override
	public BaseResponse employeeOneStatics(Integer timeSpan, Integer startDate, Integer endDate, Integer sort) throws Exception {
		StringBuilder sql=new StringBuilder();
		switch(timeSpan) {
			case 0://日
				sql.append("select IFNULL(total,0) as total,IFNULL(handle,0) as handle,CONCAT(IFNULL(handle,0)/total*100,'%') as percent,totalTable.employeeId,totalTable.employee_number,totalTable.employee_real_name ");
				sql.append("from( ");
				sql.append("(select count(clientData.id) as total,clientData.employee_one_user_id as employeeId,employeeUser.employee_number,employeeUser.employee_real_name from client_data clientData ");
				sql.append("left join employee_user employeeUser on employeeUser.id=clientData.employee_one_user_id ");
				sql.append("where clientData.employee_one_user_id is not null and clientData.is_dial=1 and clientData.is_purpose is not null ");
				sql.append("and clientData.dial_date>="+startDate+" and clientData.dial_date<="+endDate+" ");
				sql.append("group by clientData.employee_one_user_id order by count(clientData.id) desc ");
				sql.append(")totalTable left join ( ");
				sql.append("select count(clientData.id) as handle,clientData.employee_one_user_id as employeeId,employeeUser.employee_number,employeeUser.employee_real_name from client_data clientData ");
				sql.append("left join employee_user employeeUser on employeeUser.id=clientData.employee_one_user_id ");
				sql.append("where clientData.employee_one_user_id is not null and clientData.is_fill=1 ");
				sql.append("and clientData.fill_date>="+startDate+" and clientData.fill_date<="+endDate+" ");				
				sql.append("group by clientData.employee_one_user_id order by count(clientData.id) desc ");
				sql.append(")handleTable on handleTable.employeeId=totalTable.employeeId) ");
				if(sort==0)
					sql.append("group by handle desc ");
				if(sort==1)
					sql.append("group by IFNULL(handle,0)/total*100 desc ");
				break;
			case 1://月
				sql.append("select IFNULL(total,0) as total,IFNULL(handle,0) as handle,CONCAT(IFNULL(handle,0)/total*100,'%') as percent,totalTable.employeeId,totalTable.employee_number,totalTable.employee_real_name ");
				sql.append("from( ");
				sql.append("(select count(clientData.id) as total,clientData.employee_one_user_id as employeeId,employeeUser.employee_number,employeeUser.employee_real_name from client_data clientData ");
				sql.append("left join employee_user employeeUser on employeeUser.id=clientData.employee_one_user_id ");
				sql.append("where clientData.employee_one_user_id is not null and clientData.is_dial=1 and clientData.is_purpose is not null ");
				sql.append("and cast(clientData.dial_date/100 as UNSIGNED INTEGER)>="+startDate+" and cast(clientData.dial_date/100 as UNSIGNED INTEGER)<="+endDate+" ");
				sql.append("group by clientData.employee_one_user_id order by count(clientData.id) desc ");
				sql.append(")totalTable left join ( ");
				sql.append("select count(clientData.id) as handle,clientData.employee_one_user_id as employeeId,employeeUser.employee_number,employeeUser.employee_real_name from client_data clientData ");
				sql.append("left join employee_user employeeUser on employeeUser.id=clientData.employee_one_user_id ");
				sql.append("where clientData.employee_one_user_id is not null and clientData.is_fill=1 ");
				sql.append("and cast(clientData.fill_date/100 as UNSIGNED INTEGER)>="+startDate+" and cast(clientData.fill_date/100 as UNSIGNED INTEGER)<="+endDate+" ");				
				sql.append("group by clientData.employee_one_user_id order by count(clientData.id) desc ");
				sql.append(")handleTable on handleTable.employeeId=totalTable.employeeId) ");
				if(sort==0)
					sql.append("group by handle desc ");
				if(sort==1)
					sql.append("group by IFNULL(handle,0)/total*100 desc ");
				break;
			case 2://年
				sql.append("select IFNULL(total,0) as total,IFNULL(handle,0) as handle,CONCAT(IFNULL(handle,0)/total*100,'%') as percent,totalTable.employeeId,totalTable.employee_number,totalTable.employee_real_name ");
				sql.append("from( ");
				sql.append("(select count(clientData.id) as total,clientData.employee_one_user_id as employeeId,employeeUser.employee_number,employeeUser.employee_real_name from client_data clientData ");
				sql.append("left join employee_user employeeUser on employeeUser.id=clientData.employee_one_user_id ");
				sql.append("where clientData.employee_one_user_id is not null and clientData.is_dial=1 and clientData.is_purpose is not null ");
				sql.append("and cast(clientData.dial_date/10000 as UNSIGNED INTEGER)>="+startDate+" and cast(clientData.dial_date/10000 as UNSIGNED INTEGER)<="+endDate+" ");
				sql.append("group by clientData.employee_one_user_id order by count(clientData.id) desc ");
				sql.append(")totalTable left join ( ");
				sql.append("select count(clientData.id) as handle,clientData.employee_one_user_id as employeeId,employeeUser.employee_number,employeeUser.employee_real_name from client_data clientData ");
				sql.append("left join employee_user employeeUser on employeeUser.id=clientData.employee_one_user_id ");
				sql.append("where clientData.employee_one_user_id is not null and clientData.is_fill=1 ");
				sql.append("and cast(clientData.fill_date/10000 as UNSIGNED INTEGER)>="+startDate+" and cast(clientData.fill_date/10000 as UNSIGNED INTEGER)<="+endDate+" ");				
				sql.append("group by clientData.employee_one_user_id order by count(clientData.id) desc ");
				sql.append(")handleTable on handleTable.employeeId=totalTable.employeeId) ");
				if(sort==0)
					sql.append("group by handle desc ");
				if(sort==1)
					sql.append("group by IFNULL(handle,0)/total*100 desc ");
				break;
		}		
		return BaseResponse.setSuccess(ListUtil.toCamel(commonModel.baseList(sql.toString())));
	}

	@Override
	public BaseResponse employeeTwoStatics(Integer timeSpan, Integer startDate, Integer endDate) throws Exception {
		StringBuilder sql=new StringBuilder();
		switch(timeSpan) {
			case 0://日
				sql.append("select IFNULL(count(clientData.id),0) as number,clientData.employee_two_user_id as employeeId,employeeUser.employee_number,employeeUser.employee_real_name from client_data clientData ");
				sql.append("left join employee_user employeeUser on employeeUser.id=clientData.employee_two_user_id ");
				sql.append("where clientData.employee_two_user_id is not null and clientData.is_hanle=1 ");
				sql.append("and clientData.handle_date>="+startDate+" and clientData.handle_date<="+endDate+" ");
				sql.append("group by clientData.employee_two_user_id ");
				sql.append("order by count(clientData.id) desc ");
				break;
			case 1://月
				sql.append("select IFNULL(count(clientData.id),0) as number,clientData.employee_two_user_id as employeeId,employeeUser.employee_number,employeeUser.employee_real_name from client_data clientData ");
				sql.append("left join employee_user employeeUser on employeeUser.id=clientData.employee_two_user_id ");
				sql.append("where clientData.employee_two_user_id is not null and clientData.is_hanle=1 ");
				sql.append("and cast(clientData.handle_date/100 as UNSIGNED INTEGER)>="+startDate+" and cast(clientData.handle_date/100 as UNSIGNED INTEGER)<="+endDate+" ");
				sql.append("group by clientData.employee_two_user_id ");
				sql.append("order by count(clientData.id) desc ");
				break;
			case 2://年
				sql.append("select IFNULL(count(clientData.id),0) as number,clientData.employee_two_user_id as employeeId,employeeUser.employee_number,employeeUser.employee_real_name from client_data clientData ");
				sql.append("left join employee_user employeeUser on employeeUser.id=clientData.employee_two_user_id ");
				sql.append("where clientData.employee_two_user_id is not null and clientData.is_hanle=1 ");
				sql.append("and cast(clientData.handle_date/10000 as UNSIGNED INTEGER)>="+startDate+" and cast(clientData.handle_date/10000 as UNSIGNED INTEGER)<="+endDate+" ");
				sql.append("group by clientData.employee_two_user_id ");
				sql.append("order by count(clientData.id) desc ");
				break;
		}
		return BaseResponse.setSuccess(ListUtil.toCamel(commonModel.baseList(sql.toString())));
	}			
}
