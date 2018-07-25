package com.qim.loan.model.distribute.impl;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.qim.loan.model.distribute.DistributeUserLoginRecordModel;
import com.qim.loan.entity.distribute.DistributeUserLoginRecord;
import com.qim.loan.core.dao.BaseDao;
import com.qim.loan.core.model.impl.BaseModelImpl;
import com.qim.loan.dao.distribute.DistributeUserLoginRecordDao;
/**
 *
 * 类名: DistributeUserLoginRecordModel
 * 描述: 分发用户登录记录表Model层
 * 创建者: 冯子文
 * 创建时间: 2018年06月27日  17:59:24
 * 更新者: 冯子文
 * 更新时间: 2018年06月27日  17:59:24
 */
@Component("distributeUserLoginRecordModel")
public class DistributeUserLoginRecordModelImpl extends BaseModelImpl<DistributeUserLoginRecord> implements DistributeUserLoginRecordModel{
    @Autowired
    @Qualifier("distributeUserLoginRecordDao")
	private DistributeUserLoginRecordDao distributeUserLoginRecordDao;  

	@Override
	public BaseDao<DistributeUserLoginRecord> getDao() {
		return distributeUserLoginRecordDao;
	} 

    public DistributeUserLoginRecordModelImpl(){
    	setPhysicsKeyName("","id");
    }




} 