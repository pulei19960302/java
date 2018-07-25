package com.qim.loan.model.client.impl;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.qim.loan.core.dao.BaseDao;
import com.qim.loan.core.model.impl.BaseModelImpl;
import com.qim.loan.dao.client.ClientDataDao;
import com.qim.loan.entity.client.ClientData;
import com.qim.loan.model.client.ClientDataModel;
/**
 *
 * 类名: ClientDataModel
 * 描述: 客户数据Model层
 * 创建者: 冯子文
 * 创建时间: 2018年05月29日  17:14:59
 * 更新者: 冯子文
 * 更新时间: 2018年05月29日  17:14:59
 */
@Component("clientDataModel")
public class ClientDataModelImpl extends BaseModelImpl<ClientData> implements ClientDataModel{
    @Autowired
    @Qualifier("clientDataDao")
	private ClientDataDao clientDataDao;  

	@Override
	public BaseDao<ClientData> getDao() {
		return clientDataDao;
	} 

    public ClientDataModelImpl(){
    	setPhysicsKeyName("telphoneNumber,channelId","id");
    }




} 