package com.qim.loan.controller.Individual.channel;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qim.loan.service.channel.ChannelService;
/**
 *
 * 类名: ChannelController
 * 描述: 渠道Controller层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年05月29日  17:14:59
 * 更新者: 冯子文
 * 更新时间: 2018年05月29日  17:14:59
 */
@Controller
@RequestMapping(value = "/web/channel")
public class ChannelIndividualController{
	@Autowired
	@Qualifier("channelService")
	private ChannelService channelService;




}
