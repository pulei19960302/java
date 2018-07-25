package com.qim.loan.service.distribute.impl;  

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.qim.loan.core.model.BaseModel;
import com.qim.loan.core.model.CommonModel;
import com.qim.loan.core.service.impl.BaseServiceImpl;
import com.qim.loan.dao.distribute.DistributeRecordsDao;
import com.qim.loan.entity.distribute.DistributeCurrencyRecord;
import com.qim.loan.entity.distribute.DistributeRecords;
import com.qim.loan.model.distribute.DistributeCurrencyRecordModel;
import com.qim.loan.service.distribute.DistributeCurrencyRecordService;
import com.qim.loan.util.common.ListUtil;
import com.qim.loan.util.common.StringUtil;
import com.qim.loan.util.excel.ExcelMobilUtil;
import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.paramter.RequestPager; 



/**
 *
 * 类名: DistributeCurrencyRecordServiceImpl
 * 描述: 消费记录表ServiceImpl层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年06月27日  17:59:24
 * 更新者: 冯子文
 * 更新时间: 2018年06月27日  17:59:24
 */
@Service("distributeCurrencyRecordService")
public class DistributeCurrencyRecordServiceImpl extends BaseServiceImpl<DistributeCurrencyRecord> implements DistributeCurrencyRecordService {

    @Autowired
    @Qualifier("distributeCurrencyRecordModel")
	private DistributeCurrencyRecordModel distributeCurrencyRecordModel;  

    @Autowired
	private DistributeRecordsDao distributeRecordsDao;  
    
    @Autowired
    private CommonModel commonModel;
    
	@Override
	public BaseModel<DistributeCurrencyRecord> getModel() {
		return distributeCurrencyRecordModel;
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
	public BaseResponse getCurrencyRecordDetail(RequestPager requestPager, String channelUserId,
			String distributeCurrencyRecordId) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append(" real_name as name,id as number,create_time as createTime,create_date as createDate");
		sql.append(" from distribute_records  ");
		sql.append(" WHERE distribute_currency_record_id = "+distributeCurrencyRecordId);
		sql.append(" and distribute_user_id = '" +channelUserId+"'");
        PageInfo<Map<String, Object>> pageInfo = commonModel.basePage(requestPager, sql.toString());
		return BaseResponse.setSuccess(pageInfo);
	}

	@Override
	public HSSFWorkbook clientExcelImport(String channelUserId, String distributeCurrencyRecordId) throws Exception {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT ");
			sql.append(" real_name as name,id as number,create_time as createTime,create_date as createDate");
			sql.append(" from distribute_records  ");
			sql.append(" WHERE 1=1");
			if(StringUtil.isNotNull(distributeCurrencyRecordId)){
				sql.append(" and distribute_currency_record_id = "+distributeCurrencyRecordId);
			}
			sql.append(" and distribute_user_id = '" +channelUserId+"'");
			List<Map<String, Object>> baseList = commonModel.baseList(sql.toString());
			return ExcelMobilUtil.exportMap(baseList);
		}


}
