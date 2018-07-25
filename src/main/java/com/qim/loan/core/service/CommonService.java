package com.qim.loan.core.service;

import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.paramter.RequestPager;

public interface CommonService {
	//统计数据(直接传SQL)
	BaseResponse basePage(RequestPager requestPager,String sql) throws Exception;
	//谨慎使用(修改表结构语句)
	BaseResponse baseAlter(String sql) throws Exception;
	//基础查询
	BaseResponse baseList(String sql) throws Exception;
	//基础插入
	BaseResponse baseUpdate(String sql) throws Exception;
	//基础插入
	BaseResponse baseInsert(String sql) throws Exception;
	//基础删除
	BaseResponse baseDelete(String sql) throws Exception;
	
	BaseResponse baseQuery(String sql) throws Exception; 
	
	BaseResponse baseOne(String sql) throws Exception;
	

}
