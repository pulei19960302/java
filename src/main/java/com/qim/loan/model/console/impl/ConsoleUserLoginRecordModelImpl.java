package com.qim.loan.model.console.impl;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.qim.loan.core.dao.BaseDao;
import com.qim.loan.core.model.impl.BaseModelImpl;
import com.qim.loan.dao.console.ConsoleUserLoginRecordDao;
import com.qim.loan.entity.console.ConsoleUserLoginRecord;
import com.qim.loan.model.console.ConsoleUserLoginRecordModel;
/**
 *
 * 类名: ConsoleUserLoginRecordModel
 * 描述: 总台用户登录记录Model层
 * 创建者: 冯子文
 * 创建时间: 2018年05月29日  17:14:59
 * 更新者: 冯子文
 * 更新时间: 2018年05月29日  17:14:59
 */
@Component("consoleUserLoginRecordModel")
public class ConsoleUserLoginRecordModelImpl extends BaseModelImpl<ConsoleUserLoginRecord> implements ConsoleUserLoginRecordModel{
    @Autowired
    @Qualifier("consoleUserLoginRecordDao")
	private ConsoleUserLoginRecordDao consoleUserLoginRecordDao;  

	@Override
	public BaseDao<ConsoleUserLoginRecord> getDao() {
		return consoleUserLoginRecordDao;
	} 

    public ConsoleUserLoginRecordModelImpl(){
    	setPhysicsKeyName("","id");
    }




} 