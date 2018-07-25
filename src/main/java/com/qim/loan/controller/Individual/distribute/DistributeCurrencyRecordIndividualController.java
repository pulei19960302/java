package com.qim.loan.controller.Individual.distribute;  

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;

import com.qim.loan.service.distribute.DistributeCurrencyRecordService;
import com.qim.loan.util.author.ResoureManageUtil;
import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.paramter.RequestPager;
/**
 *
 * 类名: DistributeCurrencyRecordController
 * 描述: 消费记录表Controller层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年06月27日  17:59:24
 * 更新者: 冯子文
 * 更新时间: 2018年06月27日  17:59:24
 */
@Controller
@RequestMapping(value = "/web/distributeCurrencyRecord")
public class DistributeCurrencyRecordIndividualController{
	@Autowired
	@Qualifier("distributeCurrencyRecordService")
	private DistributeCurrencyRecordService distributeCurrencyRecordService;

	
	/**
	 * 描述：消费分页
	 * 创建者：zhouhua
	 * 创建时间：20180629
	 * 更新者：zhouhua
	 * 更新时间：20180629
	 * @param requestPager
	 * @param resourceAuthor
	 * @param consoleUserId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequestMapping(value = "/getCurrencyRecordPage",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	private String getwholeOrderClientData(RequestPager requestPager,@Param("resourceAuthor")String resourceAuthor,@Param("channelUserId")String channelUserId,@Param("startDate")Integer startDate,@Param("endDate")Integer endDate) {
		if(!ResoureManageUtil.isPremitChannel(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			return distributeCurrencyRecordService.getOrderClientData(requestPager,channelUserId,startDate,endDate).toString();
		} catch (Exception e) {
			 return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
/**
 * 
     * 方法名:消费记录详情
     * 功能描述:拿到消费记录对应的消费数据
     * 创建者:LGZ
     * 创建时间: 2018年7月18日 下午2:11:22 
	 * 更新者:LGZ  
	 * 更新时间: 2018年7月18日 下午2:11:22
 */
	@RequestMapping(value = "/getCurrencyRecordDetail",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	private String getOrderClientData(RequestPager requestPager,@Param("resourceAuthor")String resourceAuthor,@Param("channelUserId")String channelUserId,@Param("distributeCurrencyRecordId")String distributeCurrencyRecordId) {
		if(!ResoureManageUtil.isPremitChannel(resourceAuthor))
			return ResoureManageUtil.printForbidden();
		try {
			return distributeCurrencyRecordService.getCurrencyRecordDetail(requestPager, channelUserId, distributeCurrencyRecordId).toString();
		} catch (Exception e) {
			 return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
	}
	
	/**
	 * 功能：消费数据导出
	 * 开发者：zhouhua
	 * 时间：20180628
	 * @param requestPager
	 * @param resourceAuthor
	 * @param consoleUserId
	 * @param distributeCurrencyRecordId
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value = "/clientExcelImport",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	private String clientExcelImport(RequestPager requestPager,@Param("resourceAuthor")String resourceAuthor,@Param("channelUserId")String channelUserId,
			@Param("distributeCurrencyRecordId")String distributeCurrencyRecordId,HttpServletResponse response) {
		if(!ResoureManageUtil.isPremitChannel(resourceAuthor))
			return ResoureManageUtil.printForbidden();
        try {
        	HSSFWorkbook wb = distributeCurrencyRecordService.clientExcelImport(channelUserId,distributeCurrencyRecordId);
        	response.setContentType("application/vnd.ms-excel");
    		response.addHeader("Content-disposition", "attachent;filename="+new String("数据列表".getBytes("gbk"), "iso8859-1")+".xls");
    		OutputStream outputStream = response.getOutputStream();
    		wb.write(outputStream);
    		outputStream.flush();
    		outputStream.close();
		} catch (Exception e) {
			return BaseResponse.setExceptionMsg(e, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName()).toString();
		}
        return null;
	}

}
