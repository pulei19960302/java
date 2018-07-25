package com.qim.loan.core.model;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.qim.loan.util.paramter.RequestPager;

public interface CommonModel{
	//统计数据(直接传SQL)
	PageInfo<Map<String,Object>> basePage(RequestPager requestPager,String sql) throws Exception;
	//谨慎使用(修改表结构语句)
	int baseAlter(String sql) throws Exception;
	//基础查询
	List<Map<String,Object>> baseList(String sql) throws Exception;
	//基础插入
	int baseUpdate(String sql) throws Exception;
	//基础插入
	int baseInsert(String sql) throws Exception;
	//基础删除
	int baseDelete(String sql) throws Exception;
	
	int baseCount(String sql) throws Exception;
	
	String baseQuery(String sql) throws Exception; 
	
	Map<String,Object> baseOne(String sql) throws Exception;
}
