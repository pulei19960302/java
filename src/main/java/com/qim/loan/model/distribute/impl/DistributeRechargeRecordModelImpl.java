package com.qim.loan.model.distribute.impl;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.qim.loan.model.distribute.DistributeRechargeRecordModel;
import com.qim.loan.entity.distribute.DistributeRechargeRecord;
import com.qim.loan.core.dao.BaseDao;
import com.qim.loan.core.model.impl.BaseModelImpl;
import com.qim.loan.dao.distribute.DistributeRechargeRecordDao;
/**
 *
 * 类名: DistributeRechargeRecordModel
 * 描述: 充值记录表Model层
 * 创建者: 冯子文
 * 创建时间: 2018年06月27日  17:59:24
 * 更新者: 冯子文
 * 更新时间: 2018年06月27日  17:59:24
 */
@Component("distributeRechargeRecordModel")
public class DistributeRechargeRecordModelImpl extends BaseModelImpl<DistributeRechargeRecord> implements DistributeRechargeRecordModel{
    @Autowired
    @Qualifier("distributeRechargeRecordDao")
	private DistributeRechargeRecordDao distributeRechargeRecordDao;  

	@Override
	public BaseDao<DistributeRechargeRecord> getDao() {
		return distributeRechargeRecordDao;
	} 

    public DistributeRechargeRecordModelImpl(){
    	setPhysicsKeyName("","id");
    }




} 