package com.qim.loan.controller.Individual.channel;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qim.loan.service.channel.ChannelUserLoginRecordService;
/**
 *
 * 类名: ChannelUserLoginRecordController
 * 描述: 渠道用户登录记录Controller层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年05月29日  17:14:59
 * 更新者: 冯子文
 * 更新时间: 2018年05月29日  17:14:59
 */
@Controller
@RequestMapping(value = "/web/channelUserLoginRecord")
public class ChannelUserLoginRecordIndividualController{
	@Autowired
	@Qualifier("channelUserLoginRecordService")
	private ChannelUserLoginRecordService channelUserLoginRecordService;
	
	

}
