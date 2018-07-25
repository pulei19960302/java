package com.qim.loan.core.model;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.github.pagehelper.PageInfo;
import com.qim.loan.util.paramter.RequestPager;

import org.springframework.stereotype.Component;

@Component
public interface BaseTreeModel<T> extends BaseModel<T> {
	
	PageInfo<Map<String, Object>> getSubClassPageById(RequestPager requestPager,String id);

	PageInfo<Map<String, Object>> getAllSubClassPageById(RequestPager requestPager,String id);
		
	List<Map<String,Object>> getAllSubClassListById(String id);

	List<Map<String,Object>> getListTree();
	
	List<Map<String,Object>> getSubClassListById(String id);
	
	List<Map<String,Object>> getParClassByHierarchy(List<String> hierarchyList);
	
	List<Map<String,Object>> getAllParClassListById(String id);
	
	List<Map<String,Object>> getParClassListById(String id);
	
	int updateHierarchy(@Param("oldHierarchy")String oldHierarchy,@Param("newHierarchy")String newHierarchy);
	
	int update(T record, String pid) throws Exception;
	
	int alter(T record, String pid) throws Exception;
	
	int insert(T record, String pid) throws Exception;
}
