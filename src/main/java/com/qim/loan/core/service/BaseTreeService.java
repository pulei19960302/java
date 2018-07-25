package com.qim.loan.core.service;

import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.paramter.RequestPager;

public interface BaseTreeService<T>  extends BaseService<T>{
	
	BaseResponse update(T record,String pid)  throws Exception;
	
	BaseResponse insert(T record,String pid)  throws Exception;
	
	BaseResponse alter(T record,String pid)  throws Exception;
	
	BaseResponse getSubClassPageById(RequestPager requestPager,String id);
	
	BaseResponse getSubClassListById(String id);
	
	BaseResponse getAllSubClassPageById(RequestPager requestPager,String id);

	BaseResponse getAllSubClassListById(String id);
	
	BaseResponse getAllParClassListById(String id);
	
	BaseResponse getParClassListById(String id);
		
	BaseResponse getTree();

}
