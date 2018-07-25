package com.qim.loan.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("commonDao")
public interface CommonDao {
	//谨慎使用(修改表结构语句)
	int baseAlter(@Param("sql")String sql) throws Exception;
	//基础查询
	List<Map<String,Object>> baseList(@Param("sql")String sql) throws Exception;
	//基础插入
	int baseUpdate(@Param("sql")String sql) throws Exception;
	//基础插入
	int baseInsert(@Param("sql")String sql) throws Exception;
	//基础删除
	int baseDelete(@Param("sql")String sql) throws Exception;
	
	int baseCount(@Param("sql")String sql) throws Exception;
	
	String baseQuery(@Param("sql")String sql) throws Exception;
	
	Map<String,Object> baseOne(@Param("sql")String sql) throws Exception;
}
