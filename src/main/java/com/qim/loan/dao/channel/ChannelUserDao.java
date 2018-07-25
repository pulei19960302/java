package com.qim.loan.dao.channel;  

import org.springframework.stereotype.Repository;

import com.qim.loan.core.dao.BaseDao;
import com.qim.loan.entity.channel.ChannelUser; 
 
/**
 *
 * 类名: ChannelUserDao
 * 描述: 渠道用户Dao层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年05月29日  17:14:59
 * 更新者: 冯子文
 * 更新时间: 2018年05月29日  17:14:59
 */
 
@Repository("channelUserDao")
public interface ChannelUserDao extends BaseDao<ChannelUser>{


} 