package com.qim.loan.core.model;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.qim.loan.util.paramter.RequestPager;

public interface BaseModel<T> {
	int alter(T record) throws Exception;	
	/*******查重*******/
	int queryRepeat(T record) throws Exception;
	
	int queryRepeatByMap(Map<String,List<?>> map) throws Exception;
	
	/*********获取分页**********/
	PageInfo<Map<String,Object>> getPage(RequestPager requestPager,T record) throws Exception;

	PageInfo<Map<String,Object>> getPage(RequestPager requestPager,T record,String orderBys) throws Exception;		

	PageInfo<Map<String,Object>> getPage(RequestPager requestPager,T record,String groupBys,String orderBys) throws Exception;	
	
	PageInfo<Map<String,Object>> getPage(RequestPager requestPager,String fields,T record) throws Exception;	

	PageInfo<Map<String,Object>> getPage(RequestPager requestPager,String fields,T record,String orderBys) throws Exception;		

	PageInfo<Map<String,Object>> getPage(RequestPager requestPager,String fields,T record,String groupBys,String orderBys) throws Exception;		

	/********通过参数获取分页********/
	
	PageInfo<Map<String,Object>> getPageByParams(RequestPager requestPager,Map<String,List<?>> params) throws Exception;
	
	PageInfo<Map<String,Object>> getPageByParams(RequestPager requestPager,Map<String,List<?>> params,String orderBys) throws Exception;	
	
	PageInfo<Map<String,Object>> getPageByParams(RequestPager requestPager,Map<String,List<?>> params,String groupBys,String orderBys) throws Exception;			
	
	PageInfo<Map<String,Object>> getPageByParams(RequestPager requestPager,String fields,Map<String,List<?>> params,String orderBys) throws Exception;		
	
	PageInfo<Map<String,Object>> getPageByParams(RequestPager requestPager,String fields,Map<String,List<?>> params,String groupBys,String orderBys) throws Exception;		
	
	/*******获取列表相关*********/	
	List<Map<String,Object>> getListRelative(T record) throws Exception;	
	
	List<Map<String,Object>> getListRelative(T record,String orderBys) throws Exception;	
	
	List<Map<String,Object>> getListRelative(T record,String groupBys,String orderBys) throws Exception;	

	List<Map<String,Object>> getListRelative(String fields,T record,String orderBys) throws Exception;	

	List<Map<String,Object>> getListRelative(String fields,T record,String groupBys,String orderBys) throws Exception;
	
	/************获取列表相关通过参数***************/
	List<Map<String,Object>> getListRelativeByParams(Map<String,List<?>> params) throws Exception;
	
	List<Map<String,Object>> getListRelativeByParams(Map<String,List<?>> params,String orderBys) throws Exception;	
	
	List<Map<String,Object>> getListRelativeByParams(Map<String,List<?>> params,String groupBys,String orderBys) throws Exception;	
	
	List<Map<String,Object>> getListRelativeByParams(String fields,Map<String,List<?>> params,String orderBys) throws Exception;
	
	List<Map<String,Object>> getListRelativeByParams(String fields,Map<String,List<?>> params,String groupBys,String orderBys) throws Exception;	
	
	/************获取列表***************/
	
	List<T> getList(T record) throws Exception;

	List<T> getList(T record,String orderBys) throws Exception;

	List<T> getList(T record,String groupBys,String orderBys) throws Exception;
	
	List<T> getList(String fields) throws Exception;
	
	List<T> getList(String fields,T record) throws Exception;

	List<T> getList(String fields,T record,String orderBys) throws Exception;
	
	List<T> getList(String fields,T record,String groupBys,String orderBys) throws Exception;
	
	/************获取列表通过参数***************/
	
	List<T> getListByParams(Map<String,List<?>> params) throws Exception;
	
	List<T> getListByParams(Map<String,List<?>> params,String orderBys) throws Exception;
	
	List<T> getListByParams(Map<String,List<?>> params,String groupBys,String orderBys) throws Exception;
	
	List<T> getListByParams(String fields,Map<String,List<?>> params,String orderBys) throws Exception;	
	
	List<T> getListByParams(String fields,Map<String,List<?>> params,String groupBys,String orderBys) throws Exception;
	/***********获取总数***********/
	int getCount(T record) throws Exception;
	
	int getCountByParams(Map<String,List<?>> whParams) throws Exception;
	/*******获取单个********/
	T getOneById(String id) throws Exception;
	
	T getOneById(String fields,String id) throws Exception;
	
	T getOne(T record) throws Exception;
	
	T getOne(String fields,T record) throws Exception;	
	
	T getOneByParams(Map<String,List<?>> whParams) throws Exception;
	
	T getOneByParams(String fields,Map<String,List<?>> whParams) throws Exception;
	
	/*****获取单个相关****/
	Map<String,Object> getOneRelativeById(String id) throws Exception;
	
	Map<String,Object> getOneRelativeById(String fields,String id) throws Exception;
	
	Map<String,Object> getOneRelative(T record) throws Exception;
	
	Map<String,Object> getOneRelative(String fields,T record) throws Exception;	
	
	Map<String,Object> getOneRelativeByParam(Map<String,List<?>> whParams) throws Exception;
	
	Map<String,Object> getOneRelativeByParam(String fields,Map<String,List<?>> whParams) throws Exception;
	/*********修改***********/
	int updateById(T record) throws Exception;
	
	int updateRepeatById(T record) throws Exception;
	
	int updateByParams(T record,Map<String, List<?>> params) throws Exception;
	
	int insert(T record) throws Exception;
	
	int insertRepeat(T record) throws Exception;

	int insertBatch(List<T> recordList) throws Exception;

	int insertByParams(Map<String, List<?>> params) throws Exception;
	/*******删除********/
    int deleteAll() throws Exception;

    int delete(T record) throws Exception;

    int deleteByParams(Map<String,List<?>> params);

    int deleteById(String id) throws Exception;

    int deleteByIds(String ids) throws Exception;
	/*******删除 end*******/
}
