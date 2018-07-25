package com.qim.loan.service.console.impl;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.qim.loan.core.model.BaseModel;
import com.qim.loan.core.service.impl.BaseServiceImpl;
import com.qim.loan.entity.console.ConsoleUserLoginRecord;
import com.qim.loan.model.console.ConsoleUserLoginRecordModel;
import com.qim.loan.service.console.ConsoleUserLoginRecordService; 



/**
 *
 * 类名: ConsoleUserLoginRecordServiceImpl
 * 描述: 总台用户登录记录ServiceImpl层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年05月29日  17:14:59
 * 更新者: 冯子文
 * 更新时间: 2018年05月29日  17:14:59
 */
@Service("consoleUserLoginRecordService")
public class ConsoleUserLoginRecordServiceImpl extends BaseServiceImpl<ConsoleUserLoginRecord> implements ConsoleUserLoginRecordService {

    @Autowired
    @Qualifier("consoleUserLoginRecordModel")
	private ConsoleUserLoginRecordModel consoleUserLoginRecordModel;  

	@Override
	public BaseModel<ConsoleUserLoginRecord> getModel() {
		return consoleUserLoginRecordModel;
	} 



}
