package com.qim.loan.dao.distribute;  

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qim.loan.core.dao.BaseDao;
import com.qim.loan.entity.distribute.DistributeRecords;
import com.qim.loan.entity.distribute.DistributeUser; 
 
/**
 *
 * 类名: DistributeUserDao
 * 描述: 分发用户表Dao层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年06月27日  17:59:24
 * 更新者: 冯子文
 * 更新时间: 2018年06月27日  17:59:24
 */
 
@Repository("distributeUserDao")
public interface DistributeUserDao extends BaseDao<DistributeUser>{


} 