package com.qim.loan.model.distribute.impl;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.qim.loan.model.distribute.DistributeCurrencyRecordModel;
import com.qim.loan.entity.distribute.DistributeCurrencyRecord;
import com.qim.loan.core.dao.BaseDao;
import com.qim.loan.core.model.impl.BaseModelImpl;
import com.qim.loan.dao.distribute.DistributeCurrencyRecordDao;
/**
 *
 * 类名: DistributeCurrencyRecordModel
 * 描述: 消费记录表Model层
 * 创建者: 冯子文
 * 创建时间: 2018年06月27日  17:59:24
 * 更新者: 冯子文
 * 更新时间: 2018年06月27日  17:59:24
 */
@Component("distributeCurrencyRecordModel")
public class DistributeCurrencyRecordModelImpl extends BaseModelImpl<DistributeCurrencyRecord> implements DistributeCurrencyRecordModel{
    @Autowired
    @Qualifier("distributeCurrencyRecordDao")
	private DistributeCurrencyRecordDao distributeCurrencyRecordDao;  

	@Override
	public BaseDao<DistributeCurrencyRecord> getDao() {
		return distributeCurrencyRecordDao;
	} 

    public DistributeCurrencyRecordModelImpl(){
    	setPhysicsKeyName("","id");
    }




} 