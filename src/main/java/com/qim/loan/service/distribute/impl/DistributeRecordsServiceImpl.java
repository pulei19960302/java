package com.qim.loan.service.distribute.impl;  

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.qim.loan.service.distribute.DistributeRecordsService;
import com.qim.loan.core.model.BaseModel;
import com.qim.loan.core.service.impl.BaseServiceImpl;
import com.qim.loan.entity.distribute.DistributeRecords; 
import com.qim.loan.model.distribute.DistributeRecordsModel; 



/**
 *
 * 类名: DistributeRecordsServiceImpl
 * 描述: 分发记录表ServiceImpl层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年06月27日  17:59:24
 * 更新者: 冯子文
 * 更新时间: 2018年06月27日  17:59:24
 */
@Service("distributeRecordsService")
public class DistributeRecordsServiceImpl extends BaseServiceImpl<DistributeRecords> implements DistributeRecordsService {

    @Autowired
    @Qualifier("distributeRecordsModel")
	private DistributeRecordsModel distributeRecordsModel;  

	@Override
	public BaseModel<DistributeRecords> getModel() {
		return distributeRecordsModel;
	} 



}
