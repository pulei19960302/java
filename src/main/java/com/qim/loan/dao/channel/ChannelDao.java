package com.qim.loan.dao.channel;  

import org.springframework.stereotype.Repository;

import com.qim.loan.core.dao.BaseDao;
import com.qim.loan.entity.channel.Channel; 
 
/**
 *
 * 类名: ChannelDao
 * 描述: 渠道Dao层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年05月29日  17:14:58
 * 更新者: 冯子文
 * 更新时间: 2018年05月29日  17:14:58
 */
 
@Repository("channelDao")
public interface ChannelDao extends BaseDao<Channel>{


} 