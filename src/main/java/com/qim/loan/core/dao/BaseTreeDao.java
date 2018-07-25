package com.qim.loan.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BaseTreeDao<T> extends BaseDao<T> {
		
	List<Map<String,Object>> getAllSubClassListById(@Param("id")String id);

	List<Map<String,Object>> getListTree();
	
	List<Map<String,Object>> getSubClassListById(@Param("id")String id);
	
	List<Map<String,Object>> getParClassByHierarchy(List<String> hierarchyList);
	
	List<Map<String,Object>> getAllParClassListById(@Param("id")String id);
	
	List<Map<String,Object>> getParClassListById(@Param("id")String id);
	
	int updateHierarchy(@Param("oldHierarchy")String oldHierarchy,@Param("newHierarchy")String newHierarchy);
}
