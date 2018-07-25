package com.qim.loan.model.distribute.impl;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.qim.loan.model.distribute.DistributeUserModel;
import com.qim.loan.entity.distribute.DistributeUser;
import com.qim.loan.core.dao.BaseDao;
import com.qim.loan.core.model.impl.BaseModelImpl;
import com.qim.loan.dao.distribute.DistributeUserDao;
/**
 *
 * 类名: DistributeUserModel
 * 描述: 分发用户表Model层
 * 创建者: 冯子文
 * 创建时间: 2018年06月27日  17:59:24
 * 更新者: 冯子文
 * 更新时间: 2018年06月27日  17:59:24
 */
@Component("distributeUserModel")
public class DistributeUserModelImpl extends BaseModelImpl<DistributeUser> implements DistributeUserModel{
    @Autowired
    @Qualifier("distributeUserDao")
	private DistributeUserDao distributeUserDao;  

	@Override
	public BaseDao<DistributeUser> getDao() {
		return distributeUserDao;
	} 

    public DistributeUserModelImpl(){
    	setPhysicsKeyName("","id");
    }




} 