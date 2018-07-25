package com.qim.loan.dao.distribute;  

import org.springframework.stereotype.Repository;

import com.qim.loan.core.dao.BaseDao;
import com.qim.loan.entity.distribute.DistributeRechargeRecord; 
 
/**
 *
 * 类名: DistributeRechargeRecordDao
 * 描述: 充值记录表Dao层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年06月27日  17:59:24
 * 更新者: 冯子文
 * 更新时间: 2018年06月27日  17:59:24
 */
 
@Repository("distributeRechargeRecordDao")
public interface DistributeRechargeRecordDao extends BaseDao<DistributeRechargeRecord>{


} 