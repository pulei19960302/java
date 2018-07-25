package com.qim.loan.dao.client;  

import org.springframework.stereotype.Repository;

import com.qim.loan.core.dao.BaseDao;
import com.qim.loan.entity.client.ClientData; 
 
/**
 *
 * 类名: ClientDataDao
 * 描述: 客户数据Dao层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年05月29日  17:14:59
 * 更新者: 冯子文
 * 更新时间: 2018年05月29日  17:14:59
 */
 
@Repository("clientDataDao")
public interface ClientDataDao extends BaseDao<ClientData>{


} 