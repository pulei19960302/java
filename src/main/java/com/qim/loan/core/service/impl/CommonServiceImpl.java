package com.qim.loan.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qim.loan.core.model.CommonModel;
import com.qim.loan.core.service.CommonService;
import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.paramter.RequestPager;



@Service("commonService")
public class CommonServiceImpl implements CommonService {
	
	@Autowired
	private CommonModel commonModel;
	//统计数据(直接传SQL)
	@Override
	public BaseResponse basePage(RequestPager requestPager,String sql) throws Exception{
		return BaseResponse.ObjectResponse(commonModel.basePage(requestPager, sql));
	}
	
	//谨慎使用(修改表结构语句)
	@Override
	public BaseResponse baseAlter(String sql) throws Exception{
		return BaseResponse.ObjectResponse(commonModel.baseAlter(sql));
	}
	
	//基础查询
	@Override
	public BaseResponse baseList(String sql) throws Exception{
		return BaseResponse.ObjectResponse(commonModel.baseList(sql));
	}
	//基础插入
	@Override
	public BaseResponse baseUpdate(String sql) throws Exception{
		return BaseResponse.countResponse("基础更新",commonModel.baseAlter(sql));
	}
	//基础插入
	@Override
	public BaseResponse baseInsert(String sql) throws Exception{
		return BaseResponse.countResponse("基础添加",commonModel.baseInsert(sql));
	}
	//基础删除
	@Override
	public BaseResponse baseDelete(String sql) throws Exception{
		return BaseResponse.countResponse("基础删除",commonModel.baseDelete(sql));
	}
	
	@Override
	public BaseResponse baseQuery(String sql) throws Exception{
		return BaseResponse.ObjectResponse(commonModel.baseQuery(sql));
	} 
	
	@Override
	public BaseResponse baseOne(String sql) throws Exception{
		return BaseResponse.ObjectResponse(commonModel.baseOne(sql));
	}
}
