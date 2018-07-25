package com.qim.loan.model.distribute.impl;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.qim.loan.model.distribute.DistributeRecordsModel;
import com.qim.loan.entity.distribute.DistributeRecords;
import com.qim.loan.core.dao.BaseDao;
import com.qim.loan.core.model.impl.BaseModelImpl;
import com.qim.loan.dao.distribute.DistributeRecordsDao;
/**
 *
 * 类名: DistributeRecordsModel
 * 描述: 分发记录表Model层
 * 创建者: 冯子文
 * 创建时间: 2018年06月27日  17:59:24
 * 更新者: 冯子文
 * 更新时间: 2018年06月27日  17:59:24
 */
@Component("distributeRecordsModel")
public class DistributeRecordsModelImpl extends BaseModelImpl<DistributeRecords> implements DistributeRecordsModel{
    @Autowired
    @Qualifier("distributeRecordsDao")
	private DistributeRecordsDao distributeRecordsDao;  

	@Override
	public BaseDao<DistributeRecords> getDao() {
		return distributeRecordsDao;
	} 

    public DistributeRecordsModelImpl(){
    	setPhysicsKeyName("","id");
    }




} 