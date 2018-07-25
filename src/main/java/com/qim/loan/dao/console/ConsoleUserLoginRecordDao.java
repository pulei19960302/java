package com.qim.loan.dao.console;  

import org.springframework.stereotype.Repository;

import com.qim.loan.core.dao.BaseDao;
import com.qim.loan.entity.console.ConsoleUserLoginRecord; 
 
/**
 *
 * 类名: ConsoleUserLoginRecordDao
 * 描述: 总台用户登录记录Dao层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年05月29日  17:14:59
 * 更新者: 冯子文
 * 更新时间: 2018年05月29日  17:14:59
 */
 
@Repository("consoleUserLoginRecordDao")
public interface ConsoleUserLoginRecordDao extends BaseDao<ConsoleUserLoginRecord>{


} 