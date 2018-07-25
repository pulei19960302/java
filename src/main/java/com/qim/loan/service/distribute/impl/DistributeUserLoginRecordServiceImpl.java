package com.qim.loan.service.distribute.impl;  

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.qim.loan.service.distribute.DistributeUserLoginRecordService;
import com.qim.loan.core.model.BaseModel;
import com.qim.loan.core.service.impl.BaseServiceImpl;
import com.qim.loan.entity.distribute.DistributeUserLoginRecord; 
import com.qim.loan.model.distribute.DistributeUserLoginRecordModel; 



/**
 *
 * 类名: DistributeUserLoginRecordServiceImpl
 * 描述: 分发用户登录记录表ServiceImpl层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年06月27日  17:59:24
 * 更新者: 冯子文
 * 更新时间: 2018年06月27日  17:59:24
 */
@Service("distributeUserLoginRecordService")
public class DistributeUserLoginRecordServiceImpl extends BaseServiceImpl<DistributeUserLoginRecord> implements DistributeUserLoginRecordService {

    @Autowired
    @Qualifier("distributeUserLoginRecordModel")
	private DistributeUserLoginRecordModel distributeUserLoginRecordModel;  

	@Override
	public BaseModel<DistributeUserLoginRecord> getModel() {
		return distributeUserLoginRecordModel;
	} 



}
