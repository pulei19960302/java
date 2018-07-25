package com.qim.loan.core.service;

import java.util.List;
import java.util.Map;

import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.paramter.RequestPager;


public interface BaseService<T> {
	BaseResponse alter(T record) throws Exception;	
	/*********获取分页**********/
	BaseResponse getPage(RequestPager requestPager,T record) throws Exception;	
	
	BaseResponse getPageByParams(RequestPager requestPager,Map<String,List<?>> params) throws Exception;
	
	BaseResponse getPage(RequestPager requestPager,T record,String orderBys) throws Exception;		
	
	BaseResponse getPageByParams(RequestPager requestPager,Map<String,List<?>> params,String orderBys) throws Exception;		
	
	BaseResponse getPage(RequestPager requestPager,T record,String groupBys,String orderBys) throws Exception;		
	
	BaseResponse getPageByParams(RequestPager requestPager,Map<String,List<?>> params,String groupBys,String orderBys) throws Exception;		

	BaseResponse getPage(RequestPager requestPager,String fields,T record,String orderBys) throws Exception;		
	
	BaseResponse getPageByParams(RequestPager requestPager,String fields,Map<String,List<?>> params,String orderBys) throws Exception;		
	
	BaseResponse getPage(RequestPager requestPager,String fields,T record,String groupBys,String orderBys) throws Exception;		
	
	BaseResponse getPageByParams(RequestPager requestPager,String fields,Map<String,List<?>> params,String groupBys,String orderBys) throws Exception;		
	/*******获取列表*********/
	BaseResponse getListRelative(T record) throws Exception;	
	
	BaseResponse getListRelativeByParams(Map<String,List<?>> params) throws Exception;	
	
	BaseResponse getListRelative(T record,String orderBys) throws Exception;	
	
	BaseResponse getListRelativeByParams(Map<String,List<?>> params,String orderBys) throws Exception;	
		
	BaseResponse getListRelative(T record,String groupBys,String orderBys) throws Exception;	
	
	BaseResponse getListRelativeByParams(Map<String,List<?>> params,String groupBys,String orderBys) throws Exception;	
	
	BaseResponse getListRelative(String fields,T record,String orderBys) throws Exception;	
	
	BaseResponse getListRelativeByParams(String fields,Map<String,List<?>> params,String orderBys) throws Exception;		
	
	BaseResponse getListRelative(String fields,T record,String groupBys,String orderBys) throws Exception;	
	
	BaseResponse getListRelativeByParams(String fields,Map<String,List<?>> params,String groupBys,String orderBys) throws Exception;	
	
	BaseResponse getList(T record,String orderBys) throws Exception;
	
	BaseResponse getListByParams(Map<String,List<?>> params,String orderBys) throws Exception;
	
	BaseResponse getList(T record,String groupBys,String orderBys) throws Exception;
	
	BaseResponse getListByParams(Map<String,List<?>> params,String groupBys,String orderBys) throws Exception;
	
	BaseResponse getList(String fields,T record,String orderBys) throws Exception;
	
	BaseResponse getListByParams(String fields,Map<String,List<?>> params,String orderBys) throws Exception;	
	
	BaseResponse getList(String fields,T record,String groupBys,String orderBys) throws Exception;
	
	BaseResponse getListByParams(String fields,Map<String,List<?>> params,String groupBys,String orderBys) throws Exception;
	/*******获取单个********/
	BaseResponse getOneById(String id) throws Exception;
	
	BaseResponse getOneById(String fields,String id) throws Exception;
	
	BaseResponse getOne(T record) throws Exception;
	
	BaseResponse getOne(String fields,T record) throws Exception;
		
	BaseResponse getOneByParams(Map<String,List<?>> whParams) throws Exception;
	
	BaseResponse getOneByParams(String fields,Map<String,List<?>> whParams) throws Exception;
	
	BaseResponse getOneRelativeById(String id) throws Exception;
	
	BaseResponse getOneRelativeById(String fields,String id) throws Exception;

	BaseResponse getOneRelative(T record) throws Exception;
	
	BaseResponse getOneRelative(String fields,T record) throws Exception;
	
	BaseResponse getOneRelativeByParam(Map<String,List<?>> whParams) throws Exception;
	
	BaseResponse getOneRelativeByParam(String fields,Map<String,List<?>> whParams) throws Exception;
	/*********修改***********/
	BaseResponse updateById(T record) throws Exception;
	
	BaseResponse updateRepeatById(T record) throws Exception;
	
	BaseResponse updateByParams(T record,Map<String, List<?>> params) throws Exception;
	
	BaseResponse insert(T record) throws Exception;
	
	BaseResponse insertRepeat(T record) throws Exception;

	BaseResponse insertBatch(List<T> recordList) throws Exception;

	BaseResponse insertByParams(Map<String, List<?>> params) throws Exception;
	/*******删除********/
	BaseResponse deleteAll() throws Exception;

    BaseResponse delete(T record) throws Exception;

    BaseResponse deleteByParams(Map<String,List<?>> params);

    BaseResponse deleteById(String id) throws Exception;

    BaseResponse deleteByIds(String ids) throws Exception;

}
