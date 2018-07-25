package com.qim.loan.service.client;  

import com.qim.loan.core.service.BaseService;
import com.qim.loan.entity.client.ClientData;
import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.paramter.RequestPager; 



/**
 *
 * 类名: ClientDataService
 * 描述: 客户数据Service层(公共)
 * 创建者: 冯子文
 * 创建时间: 2018年05月29日  17:14:59
 * 更新者: 冯子文
 * 更新时间: 2018年05月29日  17:14:59
 */
 
public interface ClientDataService extends BaseService<ClientData> {

	BaseResponse getSelect()  throws Exception;
	
	BaseResponse getByEmployeeId(String employeeId)  throws Exception;
	
	BaseResponse retention(ClientData clientData)  throws Exception;
	
	BaseResponse clientDataUserById(String clientDataId) throws Exception;

	BaseResponse clientDayNumber() throws Exception;

	BaseResponse clientMonthNumber()throws Exception;

	BaseResponse clientWeekNumber()throws Exception;

	BaseResponse clientSurplusNumber()throws Exception;
	
	BaseResponse systemStatisticalChart(Integer startData,Integer endData)throws Exception;
	
	BaseResponse queryClientData(ClientData clientData,RequestPager requestPager,Integer seseamScoreTwo)throws Exception;

	BaseResponse queryClientDatas(RequestPager requestPager, ClientData clientData,Integer startDate,Integer endDate,Integer startSeseamScore,Integer endSeseamScore)throws Exception;
	
	BaseResponse getById(String id) throws Exception;
	
	BaseResponse staticsRetentionNumber() throws Exception;
	
	BaseResponse staticsImportNumber() throws Exception;
	
	BaseResponse staticsSurplusNumber() throws Exception;
	
	BaseResponse staticsIndex(Integer startDate,Integer endDate)  throws Exception;
	
	BaseResponse distributeAll() throws Exception;
	
	BaseResponse distributePart(Integer everyNumber) throws Exception;
	
	BaseResponse distributePerson(Integer number,String employeeUserId) throws Exception;
	
	BaseResponse getDepartmentOneEmployee() throws Exception;
	
	BaseResponse ImportNumber()throws Exception;


    BaseResponse distributionAll() throws Exception;

    BaseResponse getEmployeeTwoIdByNumber(String number) throws Exception;

	BaseResponse getEmployeeOneNumberByTelphoneNumber(String phone) throws Exception;


}
