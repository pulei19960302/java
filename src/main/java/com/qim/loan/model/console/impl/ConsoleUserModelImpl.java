package com.qim.loan.model.console.impl;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.qim.loan.core.dao.BaseDao;
import com.qim.loan.core.model.impl.BaseModelImpl;
import com.qim.loan.dao.console.ConsoleUserDao;
import com.qim.loan.entity.console.ConsoleUser;
import com.qim.loan.model.console.ConsoleUserModel;
/**
 *
 * 类名: ConsoleUserModel
 * 描述: 总台用户Model层
 * 创建者: 冯子文
 * 创建时间: 2018年05月29日  17:14:59
 * 更新者: 冯子文
 * 更新时间: 2018年05月29日  17:14:59
 */
@Component("consoleUserModel")
public class ConsoleUserModelImpl extends BaseModelImpl<ConsoleUser> implements ConsoleUserModel{
    @Autowired
    @Qualifier("consoleUserDao")
	private ConsoleUserDao consoleUserDao;  

	@Override
	public BaseDao<ConsoleUser> getDao() {
		return consoleUserDao;
	} 

    public ConsoleUserModelImpl(){
    	setPhysicsKeyName("consoleUserAccount","id");
    }




} 