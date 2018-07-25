package com.qim.loan.controller.Individual.distribute;  

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.qim.loan.service.distribute.DistributeUserLoginRecordService;
/**
 *
 * 类名: DistributeUserLoginRecordController
 * 描述: 分发用户登录记录表Controller层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年06月27日  17:59:24
 * 更新者: 冯子文
 * 更新时间: 2018年06月27日  17:59:24
 */
@Controller
@RequestMapping(value = "/web/distributeUserLoginRecord")
public class DistributeUserLoginRecordIndividualController{
	@Autowired
	@Qualifier("distributeUserLoginRecordService")
	private DistributeUserLoginRecordService distributeUserLoginRecordService;




}
