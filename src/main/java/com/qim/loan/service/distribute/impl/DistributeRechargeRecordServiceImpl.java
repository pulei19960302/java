package com.qim.loan.service.distribute.impl;  

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.qim.loan.service.distribute.DistributeRechargeRecordService;
import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.common.ListUtil;
import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.paramter.RequestPager;
import com.github.pagehelper.PageInfo;
import com.qim.loan.core.model.BaseModel;
import com.qim.loan.core.model.CommonModel;
import com.qim.loan.core.service.impl.BaseServiceImpl;
import com.qim.loan.entity.distribute.DistributeRechargeRecord;
import com.qim.loan.entity.distribute.DistributeRecords;
import com.qim.loan.entity.distribute.DistributeUser;
import com.qim.loan.model.distribute.DistributeRechargeRecordModel;
import com.qim.loan.model.distribute.DistributeRecordsModel;
import com.qim.loan.model.distribute.DistributeUserModel; 



/**
 *
 * 类名: DistributeRechargeRecordServiceImpl
 * 描述: 充值记录表ServiceImpl层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年06月27日  17:59:24
 * 更新者: 冯子文
 * 更新时间: 2018年06月27日  17:59:24
 */  
@Service("distributeRechargeRecordService")
public class DistributeRechargeRecordServiceImpl extends BaseServiceImpl<DistributeRechargeRecord> implements DistributeRechargeRecordService {

    @Autowired
    @Qualifier("distributeRechargeRecordModel")
	private DistributeRechargeRecordModel distributeRechargeRecordModel;  
    
    @Autowired
    @Qualifier("distributeUserModel")
    private DistributeUserModel distributeUserModel;
    
    @Autowired
	private CommonModel commonModel;

	@Override
	public BaseModel<DistributeRechargeRecord> getModel() {
		return distributeRechargeRecordModel;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse rechargeGoldcoin(String id, Integer goldcoinNumber) throws Exception {
		// 查看当前金币
		DistributeUser distributeUser  = distributeUserModel.getOneById(id);
		Integer total = distributeUser.getDistributeUserGoldcoinNumber(); //之前的数量
		Integer after = total + goldcoinNumber;  // 之后的数量
		// 新增充值记录
		DistributeRechargeRecord distributeRechargeRecord = new DistributeRechargeRecord();
		distributeRechargeRecord.setRechargeGoldcoinNumber(goldcoinNumber);   // 充值的数
		distributeRechargeRecord.setRechargeGoldcoinBeforeNumber(total);
		distributeRechargeRecord.setRechargeGoldcoinAfterNumber(after);
		distributeRechargeRecord.setPurchaseGoldcoinNumber(after);
		distributeRechargeRecord.setCreateDate(DateUtil.formatCurrentDate());
		distributeRechargeRecord.setCreateTime(DateUtil.formatCurrentTime());
		distributeRechargeRecord.setDistributeUserId(id);
		Integer count = distributeRechargeRecordModel.insert(distributeRechargeRecord);
		// 充值金币
		DistributeUser recharge = new  DistributeUser();
		recharge.setDistributeUserGoldcoinNumber(after);
		recharge.setId(id);
		count += distributeUserModel.updateRepeatById(recharge);
		
		return BaseResponse.setSuccess("success",String.valueOf(count));
	}

	@Override
	public BaseResponse getRechargeRecord(RequestPager requestPager,String consoleUserId,Integer startDate,Integer endDate) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT  ");
		sql.append(" create_date AS createDate,create_time AS createTime,recharge_goldcoin_number AS rechargeGoldcoinNumber,recharge_goldcoin_before_number AS rechargeGoldcoinBeforeNumber,recharge_goldcoin_after_number AS rechargeGoldcoinAfterNumber ");
		sql.append(" from distribute_recharge_record ");
		sql.append("  WHERE 1=1  ");
		sql.append(" AND create_date >="+startDate+" and create_date<="+endDate+" ");
		sql.append("  AND distribute_user_id = '"+consoleUserId+"' ");
	    PageInfo<Map<String, Object>> pageInfo = commonModel.basePage(requestPager, sql.toString());
		pageInfo.setList(ListUtil.toCamel(pageInfo.getList()));
		return BaseResponse.setSuccess(pageInfo);
	}

	@Override
	public BaseResponse getRechargeRecordDate(String consoleUserId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		Integer endDate = DateUtil.formatCurrentDate();
		// 本日
		StringBuilder daySql = new StringBuilder();
		daySql.append("  SELECT  COUNT(create_date) AS dayNumber from distribute_recharge_record ");
		daySql.append("  WHERE create_date = "+endDate+"  AND distribute_user_id = '"+consoleUserId+"' ");
		map.put("dayNumber", commonModel.baseCount(daySql.toString()));
		//TODO：命名
		
		// 本周
		Integer startDate = DateUtil.getRealWeek();
		StringBuilder sqlWeek = new StringBuilder();
		sqlWeek.append(" SELECT COUNT(create_date) AS weekNumber from distribute_recharge_record  ");
		sqlWeek.append("  WHERE create_date >= "+startDate+" and create_date<="+endDate+" AND distribute_user_id = '"+consoleUserId+"' ");
		map.put("weekNumber", commonModel.baseCount(sqlWeek.toString()));
		
		// 本月
		StringBuilder monthSql = new StringBuilder();
		monthSql.append(" SELECT COUNT(create_date) FROM distribute_recharge_record  ");
		monthSql.append(" WHERE CAST(create_date/100 as UNSIGNED INTEGER)=" + endDate/100+" AND distribute_user_id = '"+consoleUserId+"' ");
		map.put("monthNumber", commonModel.baseCount(monthSql.toString()));
		
		// 本年   未完成
        StringBuilder yearSql = new StringBuilder();
        yearSql.append(" select COUNT(create_date) from distribute_recharge_record ");
        yearSql.append(" where CAST(create_date/1000 as UNSIGNED INTEGER)=" + endDate/1000+" AND distribute_user_id = '"+consoleUserId+"'  ");
		map.put("yearNumber", commonModel.baseCount(monthSql.toString()));
		
		return BaseResponse.setSuccess(map);
	}

	@Override
	public BaseResponse getRechargeRecordDataGraph(String consoleUserId,Integer startDate,Integer endDate) throws Exception {
		List<Object> returnList = new ArrayList<Object>();
		Boolean flag = DateUtil.isSameMonth(startDate, endDate);//同月
		StringBuilder sqlOne = new StringBuilder();
		StringBuilder sqlTwo = new StringBuilder();
		if(flag) {  // 同月
			sqlOne.append("  SELECT distinct count(distributeRechargeRecord.id) AS rechargeRecordCount,SUM(distributeRechargeRecord.recharge_goldcoin_number) AS rechargeRecordNumber, distributeRechargeRecord.create_date AS createDate from distribute_user AS distributeUser ");
			sqlOne.append("   LEFT JOIN distribute_recharge_record AS distributeRechargeRecord ");
			sqlOne.append("  ON distributeUser.id = distributeRechargeRecord.distribute_user_id  ");
			sqlOne.append("   WHERE distributeRechargeRecord.create_date>="+startDate+" and distributeRechargeRecord.create_date<="+endDate+" ");
			sqlOne.append(" AND  distributeUser.id = '"+consoleUserId+"' ");
			sqlOne.append(" GROUP BY distributeRechargeRecord.create_date ");
			sqlOne.append("  ORDER BY distributeRechargeRecord.create_date asc");
			List<Map<String,Object>> rechargeRecordList= commonModel.baseList(sqlOne.toString());
			returnList.add(rechargeRecordList);
			
			sqlTwo.append("  SELECT distinct count(distributeCurrencyRecord.id) AS currencyRecordCount, SUM(distributeCurrencyRecord.currency_goldcoin_number) as currencyRecordNumber,distributeCurrencyRecord.create_date AS createDate from distribute_user AS distributeUser ");
			sqlTwo.append("  LEFT JOIN distribute_currency_record AS distributeCurrencyRecord ");
			sqlTwo.append("  ON distributeUser.id = distributeCurrencyRecord.distribute_user_id ");
			sqlTwo.append("  WHERE distributeCurrencyRecord.create_date>="+startDate+" and distributeCurrencyRecord.create_date<="+endDate+" ");
			sqlTwo.append("  AND  distributeUser.id = '"+consoleUserId+"' ");
			sqlTwo.append("  GROUP BY distributeCurrencyRecord.create_date ");
			sqlTwo.append("  ORDER BY distributeCurrencyRecord.create_date asc ");
			List<Map<String,Object>> currencyRecordWeekList = commonModel.baseList(sqlTwo.toString());
			returnList.add(currencyRecordWeekList);
			
		}else {
			sqlOne.append("   SELECT distinct count(distributeRechargeRecord.id) AS rechargeRecordCount,SUM(distributeRechargeRecord.recharge_goldcoin_number) AS rechargeRecordNumber,cast(distributeRechargeRecord.create_date/100 as UNSIGNED INTEGER) as create_date from distribute_user AS distributeUser ");
			sqlOne.append("  LEFT JOIN distribute_recharge_record AS distributeRechargeRecord ");
			sqlOne.append("  ON distributeUser.id = distributeRechargeRecord.distribute_user_id  ");
			sqlOne.append("   WHERE cast(distributeRechargeRecord.create_date /100 as UNSIGNED INTEGER)>="+startDate/100+" ");
			sqlOne.append("   and cast(distributeRechargeRecord.create_date/100  as UNSIGNED INTEGER)<="+endDate/100+" ");
			sqlOne.append("    AND  distributeUser.id = '"+consoleUserId+"' ");
			sqlOne.append(" GROUP BY cast(distributeRechargeRecord.create_date/100 as UNSIGNED INTEGER) ");
			sqlOne.append("  ORDER BY cast(distributeRechargeRecord.create_date/100 as UNSIGNED INTEGER) asc ");
			List<Map<String,Object>> rechargeRecordMonthList= commonModel.baseList(sqlOne.toString());
			returnList.add(rechargeRecordMonthList);
			
			sqlTwo.append(" SELECT distinct count(distributeCurrencyRecord.id) AS currencyRecordCount,SUM(distributeCurrencyRecord.currency_goldcoin_number) as currencyRecordNumber,cast(distributeCurrencyRecord.create_date/100 as UNSIGNED INTEGER) as create_date from distribute_user AS distributeUser ");
			sqlTwo.append(" LEFT JOIN distribute_currency_record AS distributeCurrencyRecord ");
			sqlTwo.append("   ON distributeUser.id = distributeCurrencyRecord.distribute_user_id  ");
			sqlTwo.append(" WHERE cast(distributeCurrencyRecord.create_date/100 as UNSIGNED INTEGER)>="+startDate/100+" ");
			sqlTwo.append(" and cast(distributeCurrencyRecord.create_date/100 as UNSIGNED INTEGER)<="+endDate/100+" ");
			sqlTwo.append(" AND  distributeUser.id = '"+consoleUserId+"' ");
			sqlTwo.append("   GROUP BY cast(distributeCurrencyRecord.create_date/100 as UNSIGNED INTEGER) ");
			sqlTwo.append("  ORDER BY cast(distributeCurrencyRecord.create_date/100 as UNSIGNED INTEGER) asc ");
			List<Map<String,Object>> currencyRecordMonthList = commonModel.baseList(sqlTwo.toString());
		    returnList.add(currencyRecordMonthList);
		}
		 return BaseResponse.setSuccess(returnList);
		
	}


}
