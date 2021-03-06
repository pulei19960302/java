package com.qim.loan.dao.distribute;  

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qim.loan.core.dao.BaseDao;
import com.qim.loan.entity.distribute.DistributeRecords; 
 
/**
 *
 * 类名: DistributeRecordsDao
 * 描述: 分发记录表Dao层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年06月27日  17:59:24
 * 更新者: 冯子文
 * 更新时间: 2018年06月27日  17:59:24
 */
 
@Repository("distributeRecordsDao")
public interface DistributeRecordsDao extends BaseDao<DistributeRecords>{

	List<DistributeRecords> getDistributeRecordsByCurrencyRecordId(@Param("consoleUserId") String consoleUserId,
			@Param("distributeCurrencyRecordId")String distributeCurrencyRecordId);

	List<DistributeRecords> getClientDate(DistributeRecords distributeRecords);

} 