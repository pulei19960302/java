package com.qim.loan.service.channel.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.qim.loan.bo.channel.ChannelUserBo;
import com.qim.loan.core.model.BaseModel;
import com.qim.loan.core.model.CommonModel;
import com.qim.loan.core.service.impl.BaseServiceImpl;
import com.qim.loan.entity.channel.ChannelUser;
import com.qim.loan.entity.channel.ChannelUserLoginRecord;
import com.qim.loan.model.channel.ChannelModel;
import com.qim.loan.model.channel.ChannelUserLoginRecordModel;
import com.qim.loan.model.channel.ChannelUserModel;
import com.qim.loan.service.channel.ChannelUserService;
import com.qim.loan.util.cache.classify.string.IStringCache;
import com.qim.loan.util.cache.string.StringCacheFactory;
import com.qim.loan.util.common.BeanUtil;
import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.common.ListUtil;
import com.qim.loan.util.common.MapUtil;
import com.qim.loan.util.common.PrimaryKeyUtil;
import com.qim.loan.util.common.StringUtil;
import com.qim.loan.util.excel.ExcelMobilUtil;
import com.qim.loan.util.excel.PhoneName;
import com.qim.loan.util.excel.TxtMobilUtil;
import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.paramter.RequestPager;

/**
 *
 * 类名: ChannelUserServiceImpl 描述: 渠道用户ServiceImpl层(公共) 创建者: 冯子文 创建时间:
 * 2018年05月29日 17:14:59 更新者: 冯子文 更新时间: 2018年05月29日 17:14:59
 */
@Service("channelUserService")
public class ChannelUserServiceImpl extends BaseServiceImpl<ChannelUser> implements ChannelUserService {

	@Autowired
	@Qualifier("channelUserModel")
	private ChannelUserModel channelUserModel;

	@Autowired
	@Qualifier("channelUserLoginRecordModel")
	private ChannelUserLoginRecordModel channelUserLoginRecordModel;

	@Autowired
	@Qualifier("stringCacheFactory")
	private StringCacheFactory stringCacheFactory;

	@Autowired
	private CommonModel commonModel;

	@Autowired
	@Qualifier("channelModel")
	private ChannelModel channelModel;

	@Override
	public BaseModel<ChannelUser> getModel() {
		return channelUserModel;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse login(ChannelUserLoginRecord channelUserLoginRecord, String channelUserName, String channelUserPwd) throws Exception {
		ChannelUser result = channelUserModel.getOne(ChannelUserBo.setLogin(channelUserName, channelUserPwd));
		if (BeanUtil.isNull(result)) {
			return BaseResponse.setFailure("渠道用户名或密码错误.");
		} else {
			IStringCache userCache = stringCacheFactory.getUser();
			// 1:写入记录
			channelUserLoginRecord.setChannelUserId(result.getId());
			channelUserLoginRecordModel.insert(channelUserLoginRecord);
			return BaseResponse.setSuccess(MapUtil.setAuthority(userCache, result.getId(), result.getChannelRealName(), "c"));
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse updatePwd(String channelUserId, String oldChannelUserPwd, String channelUserPwd) throws Exception {
		ChannelUser result = channelUserModel.getOne(ChannelUserBo.updatePwd(channelUserId, oldChannelUserPwd));
		if (BeanUtil.isNull(result)) {
			return BaseResponse.setFailure("密码错误.");
		} else {
			ChannelUserBo.updatePwd(result, channelUserPwd);
			return BaseResponse.countResponse("修改密码", channelUserModel.updateById(result));
		}
	}

	@Override
	public BaseResponse statics(String channelUserId, Integer startDate, Integer endDate) throws Exception {
		String channelId = channelUserModel.getChannelId(channelUserId);
		if (StringUtil.isNull(channelId))
			return BaseResponse.setFailure("请使用合法的用户.");
		StringBuilder builder = new StringBuilder();
		if (DateUtil.isSameMonth(startDate, endDate)) {// 同一个月
			builder.append("select count(clientData.id) as number,clientData.create_date as create_data from client_data clientData ");
			builder.append(" where clientData.create_date>=" + startDate + " and clientData.create_date<=" + endDate + " and clientData.channel_id='" + channelId + "'");
			builder.append(" group by clientData.create_date");
		} else {// 不同一个月
			endDate = endDate / 100;
			startDate = startDate / 100;
			builder.append("select count(clientData.id) as number,	cast(clientData.create_date/100 AS SIGNED INTEGER) as create_data from client_data clientData ");
			builder.append(" where 	cast(clientData.create_date/100 AS SIGNED INTEGER)>=" + startDate + " and 	cast(clientData.create_date/100 AS SIGNED INTEGER)<=" + endDate + " and clientData.channel_id='" + channelId + "'");
			builder.append(" group by 	cast(clientData.create_date/100 AS SIGNED INTEGER)");
		}
		List<Map<String, Object>> resultList = commonModel.baseList(builder.toString());
		return BaseResponse.setSuccess(ListUtil.toCamel(resultList));
	}

	@Override
	public BaseResponse index(String channelUserId) throws Exception {
		String channelId = channelUserModel.getChannelId(channelUserId);
		if (StringUtil.isNull(channelId))
			return BaseResponse.setFailure("请使用合法的用户.");
		Map<String, Object> map = new HashMap<String, Object>();
		synchronized (this) {
			Integer endDate = DateUtil.formatCurrentDate();
			// 1:今日导入量
			StringBuilder todaySql = new StringBuilder();
			todaySql.append(" select count(clientData.id) as number from client_data clientData ");
			todaySql.append(" where clientData.create_date=" + endDate + " and clientData.channel_id='" + channelId + "'");
			int todayCount = commonModel.baseCount(todaySql.toString());
			map.put("todayCount", todayCount);
			// 2:本周导入量
			StringBuilder weekSql = new StringBuilder();
			Integer startDate = DateUtil.getRealWeek();
			weekSql.append(" select count(clientData.id) as number from client_data clientData ");
			weekSql.append(" where clientData.create_date>=" + startDate + " and clientData.create_date<=" + endDate + " and clientData.channel_id='" + channelId + "'");
			int weekCount = commonModel.baseCount(weekSql.toString());
			map.put("weekCount", weekCount);
			// 3:本月导入量
			StringBuilder monthSql = new StringBuilder();
			monthSql.append(" select count(clientData.id) as number from client_data clientData ");
			monthSql.append(" where cast(clientData.create_date/100 as UNSIGNED INTEGER) =" + (endDate / 100) + " and clientData.channel_id='" + channelId + "'");
			int monthCount = commonModel.baseCount(monthSql.toString());
			map.put("monthCount", monthCount);
		}
		return BaseResponse.setSuccess(map);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse loginout(String channelUserId) throws Exception {
		IStringCache userCache = stringCacheFactory.getUser();
		MapUtil.setAuthorityOut(userCache, channelUserId);
		return BaseResponse.setSuccessMsg("注销用户成功.");
	}

	@Override
	public BaseResponse search(RequestPager requestPager, String channelUserId, Integer startDate, Integer endDate) throws Exception {
		String channelId = channelUserModel.getChannelId(channelUserId);
		if (StringUtil.isNull(channelId))
			return BaseResponse.setFailure("请使用合法的用户.");
		StringBuilder sql = new StringBuilder();
		sql.append(" select clientData.telphone_number as telphoneNumber,clientData.real_name as realName,clientData.create_date as createData,clientData.create_time as createTime from client_data clientData ");
		sql.append(" where clientData.create_date>=" + startDate + " and clientData.create_date<=" + endDate + " and clientData.channel_id='" + channelId + "'");
		PageInfo<Map<String, Object>> pageInfo = commonModel.basePage(requestPager, sql.toString());
		return BaseResponse.setSuccess(pageInfo);
	}

	@Override
	public BaseResponse batchImport(String channelUserId, String data) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse export(String channelUserId, Integer type, Integer exportDate) throws Exception {
		String channelId = channelUserModel.getChannelId(channelUserId);
		if (StringUtil.isNull(channelId))
			return BaseResponse.setFailure("请使用合法的用户.");
		String txtPath = "/usr/local/updownload/export/" + channelId + "-" + DateUtil.formatCurrentDate() + "-" + DateUtil.formatCurrentTime() + ((type == 1) ? ".rtf" : ".xls");
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT clientData.real_name,clientData.telphone_number INTO OUTFILE '" + txtPath + "' character set gbk FIELDS TERMINATED BY '\t' FROM client_data clientData ");
		sql.append(" where clientData.create_date=" + exportDate + " and clientData.channel_id='" + channelId + "'");
		commonModel.baseQuery(sql.toString());
		return BaseResponse.setSuccess(MapUtil.set("download", txtPath));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse excelImport(String channelUserId, String path) throws Exception {
		if (StringUtil.isNull(path))
			return BaseResponse.setFailure("上传文件地址获取失败.");
		String channelId = channelUserModel.getChannelId(channelUserId);
		if (StringUtil.isNull(channelId))
			return BaseResponse.setFailure("请使用合法的用户.");
	    List<String> list = ExcelMobilUtil.getMobil(path);
		// 去除重复数据
		List<Map<String, Object>> searchList = commonModel.baseList("select telphone_number as telphoneNumber from client_data");
		List<String> subList = ListUtil.getValue(searchList);
		List<String> resultLs = ListUtil.subtract(list, subList);
		String sql = genBatch(resultLs, channelId, channelUserId);
		int i = 0;
		if (StringUtil.isNotNull(sql))
			i = commonModel.baseInsert(sql);
		return BaseResponse.setSuccess(MapUtil.set("batchNumber", String.valueOf(i)));
	}
	
	/**
	 * 周华
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse excelImportPhoneName(String channelUserId, String path) throws Exception {
		if (StringUtil.isNull(path))
			return BaseResponse.setFailure("上传文件地址获取失败.");
		String channelId = channelUserModel.getChannelId(channelUserId);
		if (StringUtil.isNull(channelId))
			return BaseResponse.setFailure("请使用合法的用户.");
		List<PhoneName> phoneNameList = ExcelMobilUtil.getMobilPhoneName(path);
		// 去除重复数据
		List<Map<String, Object>> searchList = commonModel.baseList("select real_name as realName,telphone_number as telphoneNumber from client_data");
		List<PhoneName> subList = ListUtil.getValuePhoneName(searchList);
		Map<String,List<PhoneName>> resultLs = ListUtil.phoneNameUnion(phoneNameList,subList);
		List<PhoneName> namePhoneList= resultLs.get("namePhone");    
		List<PhoneName> pNList = resultLs.get("phoneName");   // 电话号码一样名字不一样
		String updateSql = updatePhoneByName(pNList,channelId, channelUserId);
		
		String sql = genPhoneName(namePhoneList, channelId, channelUserId);
        int count = 0;
		if(StringUtil.isNotNull(updateSql)) {
			count = commonModel.baseUpdate(updateSql);
		}
		if (StringUtil.isNotNull(sql))
			count += commonModel.baseInsert(sql);
		return BaseResponse.setSuccess(MapUtil.set("batchNumber", String.valueOf(count)));
		  
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse excelImportPhoneAndName(String channelUserId, String path) throws Exception {
		if (StringUtil.isNull(path))
			return BaseResponse.setFailure("上传文件地址获取失败.");
		String channelId = channelUserModel.getChannelId(channelUserId);
		if (StringUtil.isNull(channelId))
			return BaseResponse.setFailure("请使用合法的用户.");
		List<String> phoneList = ExcelMobilUtil.getMobilPhoneAndName(path);
		
		//待处理
		StringBuilder unHandleSql=new StringBuilder();
		unHandleSql.append("SELECT	CONCAT(IFNULL(real_name,''),\"---\",telphone_number) AS namePhone FROM	client_data ");
		unHandleSql.append("where (is_hanle =0 and handle_date is null)  ");	
		//查询未处理的电话号码及名字
		List<Map<String, Object>> unHandleListMap = commonModel.baseList(unHandleSql.toString());
		//处理为字符串
		List<String> unHandleList=ListUtil.getValue(unHandleListMap);
		//差集
		List<String> subList=ListUtil.subtract(phoneList, unHandleList);
		String sql = genBatchNameAndPhone(subList, channelId, channelUserId);
		int i = 0;
		if (StringUtil.isNotNull(sql))
			i = commonModel.baseInsert(sql);
		return BaseResponse.setSuccess(MapUtil.set("batchNumber", String.valueOf(i)));	
	}
	
	
	private String updatePhoneByName(List<PhoneName> list, String channelId, String channelUserId) {
		if (ListUtil.isNull(list))
			return "";
		StringBuilder sql = new StringBuilder();
		StringBuilder sBuilder = new StringBuilder();
		sql.append(" UPDATE client_data ");
		sql.append(" set  real_name = CASE telphone_number ");	
		for (PhoneName phoneName : list) {
			sBuilder.append(" '"+phoneName.getPhone()+"' ");
			sBuilder.append(",");
			
			sql.append(" when '"+phoneName.getPhone()+"'  then '"+phoneName.getName()+"'");
		}
		String strSql = sBuilder.substring(0, sBuilder.length() - 1); 
		sql.append(" end ");
		sql.append(" WHERE  telphone_number In ("+strSql+")");
		
	  return sql.toString();	
	}
	
	

	/**
	 * 
	 * 方法名:genBatch 功能描述:将list转化为批量插入 创建者:冯子文 创建时间: 2018年5月31日 下午4:13:31 更新者:冯子文
	 * 更新时间: 2018年5月31日 下午4:13:31
	 */
	private String genPhoneName(List<PhoneName> list, String channelId, String channelUserId) {
		if (ListUtil.isNull(list))
			return "";
		StringBuilder sql = new StringBuilder();
		sql.append("insert into client_data(id,real_name,telphone_number,channel_id,channel_user_id,create_date,create_time) values");
		int count = list.size();
		for (int i = 0; i < count; i++) {
			PhoneName phoneName = list.get(i);
			sql.append("('" + PrimaryKeyUtil.getPrimaryId32() + "','" + phoneName.getName() +"','" +  phoneName.getPhone() + "','" + channelId + "','" + channelId + "'," + DateUtil.formatCurrentDate() + "," + DateUtil.formatCurrentTime() + ")");
			if (i != (count - 1))
				sql.append(",");
			else
				sql.append(";");
		}
		return sql.toString();
	}
	
	private String genBatch(List<String> list, String channelId, String channelUserId) {
		if (ListUtil.isNull(list))
			return "";
		StringBuilder sql = new StringBuilder();
		sql.append("insert into client_data(id,telphone_number,channel_id,channel_user_id,create_date,create_time) values");
		int count = list.size();
		for (int i = 0; i < count; i++) {
			sql.append("('" + PrimaryKeyUtil.getPrimaryId32() + "','" + list.get(i) +"','" + channelId + "','" + channelId + "'," + DateUtil.formatCurrentDate() + "," + DateUtil.formatCurrentTime() + ")");
			if (i != (count - 1))
				sql.append(",");
			else
				sql.append(";");
		}
		return sql.toString();
	}
	
	private String genBatchNameAndPhone(List<String> list, String channelId, String channelUserId) {
		if (ListUtil.isNull(list))
			return "";
		StringBuilder sql = new StringBuilder();
		sql.append("insert into client_data(id,real_name,telphone_number,channel_id,channel_user_id,create_date,create_time) values");
		int count = list.size();
		for (int i = 0; i < count; i++) {
			String tmp=list.get(i);
			if(StringUtil.isNotNull(tmp)) {
				String[] tmpArray=tmp.split("---");
				sql.append("('" + PrimaryKeyUtil.getPrimaryId32() + "','" +tmpArray[0] +"','" +tmpArray[1] +"','" + channelId + "','" + channelId + "'," + DateUtil.formatCurrentDate() + "," + DateUtil.formatCurrentTime() + ")");
				if (i != (count - 1))
					sql.append(",");
				else
					sql.append(";");				
			}
			

		}
		return sql.toString();
	}
	
	

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse txtImport(String channelUserId, String path) throws Exception {
		if (StringUtil.isNull(path))
			return BaseResponse.setFailure("上传文件地址获取失败.");
		String channelId = channelUserModel.getChannelId(channelUserId);
		if (StringUtil.isNull(channelId))
			return BaseResponse.setFailure("请使用合法的用户.");
		List<String> list = TxtMobilUtil.getMobil(path);
		// 去除重复数据
		List<Map<String, Object>> searchList = commonModel.baseList("select telphone_number as telphoneNumber from client_data");
		List<String> subList = ListUtil.getValue(searchList);
		List<String> resultLs = ListUtil.subtract(list, subList);
		String sql = genBatch(resultLs, channelId, channelUserId);
		int i = 0;
		if (StringUtil.isNotNull(sql))
			i = commonModel.baseInsert(sql);
		return BaseResponse.setSuccess(MapUtil.set("batchNumber", String.valueOf(i)));
	}

	@Override
	public BaseResponse todayNumber(String channelUserId) throws Exception {
		String channelId = channelUserModel.getChannelId(channelUserId);
		if (StringUtil.isNull(channelId))
			return BaseResponse.setFailure("请使用合法的用户.");
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(clientData.id) as number from client_data clientData ");
		sql.append(" where clientData.create_date=" + DateUtil.formatCurrentDate() + " and clientData.channel_id='" + channelId + "'");
		return BaseResponse.setSuccess(commonModel.baseOne(sql.toString()));
	}

	@Override
	public BaseResponse getUser(String channelUserId) throws Exception {
		ChannelUser result = channelUserModel.getOne(ChannelUserBo.setId(channelUserId));
		return BaseResponse.setSuccess(MapUtil.set("channelUserName", result.getChannelUserName()));
	}



}
