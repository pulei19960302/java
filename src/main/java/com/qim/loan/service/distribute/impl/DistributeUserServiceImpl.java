package com.qim.loan.service.distribute.impl;  

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.qim.loan.service.distribute.DistributeUserService;
import com.qim.loan.util.cache.classify.string.IStringCache;
import com.qim.loan.util.cache.string.StringCacheFactory;
import com.qim.loan.util.common.BeanUtil;
import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.common.ListUtil;
import com.qim.loan.util.common.MapUtil;
import com.qim.loan.util.excel.ExcelMobilUtil;
import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.paramter.RequestPager;
import com.github.pagehelper.PageInfo;
import com.netflix.discovery.converters.Auto;
import com.qim.loan.bo.distribute.DistributeUserBo;
import com.qim.loan.core.model.BaseModel;
import com.qim.loan.core.model.CommonModel;
import com.qim.loan.core.service.impl.BaseServiceImpl;
import com.qim.loan.dao.distribute.DistributeRecordsDao;
import com.qim.loan.dao.distribute.DistributeUserDao;
import com.qim.loan.entity.distribute.DistributeCurrencyRecord;
import com.qim.loan.entity.distribute.DistributeRecords;
import com.qim.loan.entity.distribute.DistributeUser;
import com.qim.loan.entity.distribute.DistributeUserLoginRecord;
import com.qim.loan.model.distribute.DistributeCurrencyRecordModel;
import com.qim.loan.model.distribute.DistributeRecordsModel;
import com.qim.loan.model.distribute.DistributeUserLoginRecordModel;
import com.qim.loan.model.distribute.DistributeUserModel; 



/**
 *
 * 类名: DistributeUserServiceImpl
 * 描述: 分发用户表ServiceImpl层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年06月27日  17:59:24
 * 更新者: 冯子文
 * 更新时间: 2018年06月27日  17:59:24
 */
@Service("distributeUserService")
public class DistributeUserServiceImpl extends BaseServiceImpl<DistributeUser> implements DistributeUserService {

    @Autowired
    @Qualifier("distributeUserModel")
	private DistributeUserModel distributeUserModel; 
    
    @Autowired
    private DistributeRecordsDao distributeRecordsDao;
    
    @Autowired
    @Qualifier("distributeCurrencyRecordModel")
    private DistributeCurrencyRecordModel distributeCurrencyRecordModel; 
    
    @Autowired
    @Qualifier("distributeUserLoginRecordModel")
    private DistributeUserLoginRecordModel distributeUserLoginRecordModel; 
    
    @Autowired
    private CommonModel commonModel;
    
    @Autowired
	@Qualifier("stringCacheFactory")
	private StringCacheFactory stringCacheFactory;


	@Override
	public BaseModel<DistributeUser> getModel() {
		return distributeUserModel;
	}

	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse login(DistributeUserLoginRecord distributeUserLoginRecord, String distributeUserName,
			String distributeUserPassword) throws Exception {
		DistributeUser result = distributeUserModel.getOne(DistributeUserBo.setLogin(distributeUserName, distributeUserPassword));
		if (BeanUtil.isNull(result)) {
			return BaseResponse.setFailure("用户名或密码错误");
		} else {
			
			DistributeUser updateIsOnline = new DistributeUser();
			updateIsOnline.setId(result.getId());
			updateIsOnline.setIsOnline(0);
			distributeUserModel.updateById(updateIsOnline);
			
			IStringCache userCache = stringCacheFactory.getUser();
			distributeUserLoginRecord.setDistributeUserId(result.getId());
			distributeUserLoginRecordModel.insert(distributeUserLoginRecord);
			
			return BaseResponse.setSuccess(MapUtil.setAuthority(userCache, result.getId(), result.getDistributeUserName(), "c"));
		}
	}

	@Override
	public BaseResponse getUserByGoldcoinNumber(String consoleUserId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String sql = " SELECT distribute_user_goldcoin_number from distribute_user WHERE id = '"+consoleUserId+"' ";
		String distributeUserGoldcoinNumber = commonModel.baseQuery(sql);
		if(distributeUserGoldcoinNumber != null) {
			map.put("goldcoin", distributeUserGoldcoinNumber);
			return BaseResponse.setSuccess(map); 
		} else {
			return BaseResponse.setSuccessMsg(String.valueOf(0)); 
		}
		
	}
	
	@Override
	public BaseResponse consumeGoldcoin(Integer number) throws Exception {
        Integer price = 1;
        Integer goldCoinNumber = price * number;
		return BaseResponse.setSuccess(goldCoinNumber);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse purchaseData(String Id, String purchaseNumber, String currencyNumber)
			throws Exception {
		    // 执行查询数据
		    StringBuilder date = new StringBuilder();  
            StringBuilder totalSql = new StringBuilder();
            totalSql.append(" SELECT COUNT(*) from client_data WHERE id not in  ");
		    totalSql.append("  (SELECT client_data_id from distribute_records WHERE distribute_user_id = '"+Id+"')  AND is_fill = 1 ");
		    Integer total = commonModel.baseCount(totalSql.toString());		   
		    double pNumber = Double.valueOf(purchaseNumber);
		    double d =  pNumber / Double.valueOf(total);
		    double count = d*100;
		
		    StringBuilder eachDaySql = new StringBuilder();
		    eachDaySql.append("  SELECT fill_date AS fillDate, (ROUND(COUNT(fill_date)/"
		    		+ "(SELECT COUNT(id) AS total from client_data WHERE id not in "
		    		+ "(SELECT client_data_id from distribute_records WHERE distribute_user_id = '"+Id+"') AND is_fill = 1 "
		    		+ " )*100,1)) "
		    		+ "AS number from client_data "
		    		+ "WHERE id not in "
		    		+ "(SELECT client_data_id from distribute_records WHERE distribute_user_id = '"+Id+"')  AND is_fill = 1 ");
		    eachDaySql.append("  GROUP BY DAY(fill_date)  ");
            List<Map<String,Object>> list= commonModel.baseList(eachDaySql.toString());		    
		    boolean isluck = false;
            for (Map<String, Object> map : list) {
		    	if(isluck) {
		    		break;
		    	}
                 for (String key : map.keySet()) {
                	 if(Integer.valueOf(map.get("fillDate").toString()) != DateUtil.formatCurrentDate()) {
	                	 double number = Double.parseDouble(map.get(key).toString());
	                	 if(count >= number) {
	                		 if(date.length() > 0) {
	                			 date.append(",");
	                		 }
	                			 String createDate = map.get("fillDate").toString();
	                			 date.append(createDate);
	                	 }else {
	                		 if(date.length() > 0) {
	                			 date.append(",");
	                		 }
	                		 String createDate = map.get("fillDate").toString();
                			 date.append(createDate);
                			 isluck = true;
                			 break;
	                	 }
                	 }
				}
		    }
            
		    // 查询用户金币
		    StringBuilder sql = new StringBuilder();
		    sql.append(" SELECT distribute_user_goldcoin_number from distribute_user WHERE id = '"+Id+"' ");
            Integer goldcoinNumber = commonModel.baseCount(sql.toString());		    
            Integer currencySurplusGoldcoin = goldcoinNumber- Integer.valueOf(currencyNumber); 
             
            String distributeUserGoldcoinSql = " update distribute_user set distribute_user_goldcoin_number = "+currencySurplusGoldcoin+" where id = '"+Id+"'";
            commonModel.baseUpdate(distributeUserGoldcoinSql);
            // 记录用户消费记录
            DistributeCurrencyRecord distributeCurrencyRecord = new DistributeCurrencyRecord();
            String currencyRecordId = uuid();
            distributeCurrencyRecord.setId(currencyRecordId);
            distributeCurrencyRecord.setDistributeCurrencyRecordName("test");
            distributeCurrencyRecord.setCurrencyGoldcoinNumber(Integer.valueOf(currencyNumber));
            distributeCurrencyRecord.setCreateTime(DateUtil.formatCurrentTime());
            distributeCurrencyRecord.setCreateDate(DateUtil.formatCurrentDate());
            distributeCurrencyRecord.setDistributeUserId(Id);
            distributeCurrencyRecord.setCurrencySurplusGoldcoin(currencySurplusGoldcoin);
            distributeCurrencyRecord.setCurrencyBeforeGoldcoin(goldcoinNumber);
            distributeCurrencyRecordModel.insert(distributeCurrencyRecord);
            Integer distributeRecordsNumber = 0;
		    if(date.length() > 1) {
		    	String[] strDate = date.toString().split(",");
		    	for (int i = 0; i < strDate.length; i++) {
		    		DistributeRecords distributeRecords = new DistributeRecords();
		    		distributeRecords.setDistributeUserId(Id);
		    		distributeRecords.setCreateDate(Integer.valueOf(strDate[i]));
                    List<DistributeRecords> distributeRecordsList = distributeRecordsDao.getClientDate(distributeRecords);
                    distributeRecordsNumber += DistributeRecordsUtil(distributeRecordsList,currencyRecordId,Id);
		    	}
		    } else {
                DistributeRecords distributeRecords = new DistributeRecords();
	    		distributeRecords.setDistributeUserId(Id);
	    		distributeRecords.setCreateDate(Integer.valueOf(date.toString()));
                List<DistributeRecords> distributeRecordsList = distributeRecordsDao.getClientDate(distributeRecords);
                distributeRecordsNumber = DistributeRecordsUtil(distributeRecordsList,currencyRecordId,Id);
		    }
		return BaseResponse.setSuccess(distributeRecordsNumber);
	}
	    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	    public Integer DistributeRecordsUtil(List<DistributeRecords> distributeRecordsList,String currencyRecordId,String Id) throws Exception {
	    	// 执行批量插入
            StringBuilder insertSql = new StringBuilder();
            insertSql.append(" INSERT INTO distribute_records(id,client_data_id,telphone_number,real_name,create_time,create_date,distribute_currency_record_id,distribute_user_id) ");
            insertSql.append(" VALUES ");
            for (DistributeRecords dr : distributeRecordsList) {
		        if(insertSql.length() >= 158) {
		        	insertSql.append(",");
		        }
            	insertSql.append(" ('"+dr.getId()+"',"+dr.getClientDataId()+","+dr.getTelphoneNumber()+",'"+dr.getRealName()+"',"+DateUtil.formatCurrentTime()+","+DateUtil.formatCurrentDate()+",'"+currencyRecordId+"','"+Id+"')");
            }
            insertSql.append(";");
            System.out.println(insertSql.toString());
            return commonModel.baseInsert(insertSql.toString());
	    }

	@Override
	public BaseResponse getOrderClientData(RequestPager requestPager, String Id,Integer startDate, Integer endDate) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append(" id,create_date,create_time,purchase_number,currency_goldcoin_number,currency_before_goldcoin,currency_surplus_goldcoin ");
		sql.append(" from distribute_currency_record  ");
		sql.append(" WHERE 1=1 ");
		if(startDate != null && endDate != null) {
			sql.append(" and create_date >="+startDate+" and create_date<="+endDate+" ");
		}
        sql.append("  AND distribute_user_id = '"+Id+"' ");		
        PageInfo<Map<String, Object>> pageInfo = commonModel.basePage(requestPager, sql.toString());
		pageInfo.setList(ListUtil.toCamel(pageInfo.getList()));
		return BaseResponse.setSuccess(pageInfo);
	}

	@Override
	public BaseResponse getOrderClientData(RequestPager requestPager, String consoleUserId,
			String distributeCurrencyRecordId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		StringBuilder currencyRecordSql = new StringBuilder();
		currencyRecordSql.append("  SELECT ");
		currencyRecordSql.append(" create_date AS createDate,  create_time AS createTime, purchase_number AS purchaseNumber, ");
		currencyRecordSql.append(" currency_goldcoin_number AS currencyGoldcoinNumber, currency_before_goldcoin AS currencyBeforeGoldcoin, currency_surplus_goldcoin AS currencySurplusGoldcoin ");
		currencyRecordSql.append(" FROM distribute_currency_record  ");
		currencyRecordSql.append(" WHERE id = '"+distributeCurrencyRecordId+"' AND distribute_user_id = '"+consoleUserId+"' ");
		Map<String,Object> currencyRecordMap = commonModel.baseOne(currencyRecordSql.toString());
		map.put("currencyRecordMap", currencyRecordMap);
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append(" real_name AS realName,telphone_number AS telphoneNumber,create_date AS createDate,create_time AS createTime ");
		sql.append(" from distribute_records ");
		sql.append("  WHERE ");
		sql.append(" distribute_currency_record_id = '"+distributeCurrencyRecordId+"' and distribute_user_id = '"+consoleUserId+"' ");
		PageInfo<Map<String, Object>> pageInfo = commonModel.basePage(requestPager, sql.toString());
		pageInfo.setList(ListUtil.toCamel(pageInfo.getList()));
		map.put("pageInfo",pageInfo);
		return BaseResponse.setSuccess(map);
		
	}

	@Override
	public HSSFWorkbook clientExcelImport(String consoleUserId, String distributeCurrencyRecordId) throws Exception {
		List<DistributeRecords> distributeRecordsList= distributeRecordsDao.getDistributeRecordsByCurrencyRecordId(consoleUserId,distributeCurrencyRecordId);
		return ExcelMobilUtil.export(distributeRecordsList);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse updateDistributeUserInfo(String consoleUserId, String distributeUserPassword,
			String newdistributeUserPassword, String realName) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		if(distributeUserModel.getOne(DistributeUserBo.updatePwd(consoleUserId, distributeUserPassword)) == null) {
			  map.put("distributeUserName", "原始密码错误");
			  return BaseResponse.setSuccess(map);
		}
		DistributeUser updateUserInfo = DistributeUserBo.updatePwd(consoleUserId, newdistributeUserPassword);
		if(realName != null) {
			updateUserInfo.setRealName(realName);
		}
		updateUserInfo.setCreateDate(DateUtil.formatCurrentDate());
		updateUserInfo.setCreateTime(DateUtil.formatCurrentTime());
		Integer count = distributeUserModel.updateById(updateUserInfo);
		if(count > 0) {
			map.put("distributeUserName", "修改成功");
			return BaseResponse.setSuccess(map);
		}
		map.put("distributeUserName", "修改失败");
		return BaseResponse.setSuccess(map);
	}

	@Override
	public BaseResponse getDistributeUserNumber(String consoleUserId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String sql = " SELECT distribute_user_name from distribute_user WHERE id = '"+consoleUserId+"' ";
		String distributeUserName = commonModel.baseQuery(sql);
		if(distributeUserName != null) {
			map.put("distributeUserName", distributeUserName);
			return BaseResponse.setSuccess(map);
		}
		    map.put("distributeUserName", "0");
		return BaseResponse.setSuccess(map);
	}

	@Override
	public BaseResponse distributeUserDateImport(RequestPager requestPager, String consoleUserId, Integer startDate,
			Integer endDate) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT  ");
		sql.append("  real_name,telphone_number,create_date,create_time  ");
		sql.append(" from distribute_records  ");
		sql.append("  WHERE 1=1  ");
		sql.append(" AND create_date>="+startDate+" and create_date<="+endDate+" ");
		sql.append(" AND distribute_user_id = '"+consoleUserId+"' ");
		PageInfo<Map<String, Object>> pageInfo = commonModel.basePage(requestPager, sql.toString());
		pageInfo.setList(ListUtil.toCamel(pageInfo.getList()));
		return BaseResponse.setSuccess(pageInfo);
	} 
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse loginout(String consoleUserId) throws Exception {
		IStringCache userCache = stringCacheFactory.getUser();
		MapUtil.setAuthorityOut(userCache, consoleUserId);
		String sql = " UPDATE distribute_user SET is_online = 1 WHERE id = '"+consoleUserId+"' ";
		commonModel.baseUpdate(sql);
		return BaseResponse.setSuccessMsg("注销用户成功.");
	}
	public String uuid() {
	       return UUID.randomUUID().toString().replace("-", "");
	}

	
}
