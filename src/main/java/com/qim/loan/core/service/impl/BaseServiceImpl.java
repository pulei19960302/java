package com.qim.loan.core.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qim.loan.core.model.BaseModel;
import com.qim.loan.core.service.BaseService;
import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.paramter.RequestPager;



@Service
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	public abstract BaseModel<T> getModel();
	
	@Override
	public BaseResponse alter(T record) throws Exception{
		return BaseResponse.countResponse("基础变更",getModel().alter(record));
	}

	/*********获取分页**********/
	@Override
	public BaseResponse getPage(RequestPager requestPager,T record) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getPage(requestPager,record,null));
	}		
	
	@Override
	public BaseResponse getPageByParams(RequestPager requestPager,Map<String,List<?>> params) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getPageByParams(requestPager,params,null));
	}	
	
	@Override
	public BaseResponse getPage(RequestPager requestPager,T record,String orderBys) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getPage(requestPager,record,orderBys));
	}		
	
	@Override
	public BaseResponse getPageByParams(RequestPager requestPager,Map<String,List<?>> params,String orderBys) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getPageByParams(requestPager,params,orderBys));
	}		
	
	@Override
	public BaseResponse getPage(RequestPager requestPager,T record,String groupBys,String orderBys) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getPage(requestPager,record,groupBys,orderBys));
	}		
	
	@Override
	public BaseResponse getPageByParams(RequestPager requestPager,Map<String,List<?>> params,String groupBys,String orderBys) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getPageByParams(requestPager,params,groupBys,orderBys));
	}

	@Override
	public BaseResponse getPage(RequestPager requestPager,String fields,T record,String orderBys) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getPage(requestPager,fields,record,orderBys));
	}		
	
	@Override
	public BaseResponse getPageByParams(RequestPager requestPager,String fields,Map<String,List<?>> params,String orderBys) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getPageByParams(requestPager,fields,params,orderBys));
	}		
	
	@Override
	public BaseResponse getPage(RequestPager requestPager,String fields,T record,String groupBys,String orderBys) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getPage(requestPager,fields,record,groupBys,orderBys));
	}		
	
	@Override
	public BaseResponse getPageByParams(RequestPager requestPager,String fields,Map<String,List<?>> params,String groupBys,String orderBys) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getPageByParams(requestPager,fields,params,groupBys,orderBys));
	}		
	/*******获取列表*********/
	@Override
	public BaseResponse getListRelative(T record) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getListRelative(record,null));
	}	
	
	@Override
	public BaseResponse getListRelativeByParams(Map<String,List<?>> params) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getListRelativeByParams(params,null));
	}	
	
	
	@Override
	public BaseResponse getListRelative(T record,String orderBys) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getListRelative(record,orderBys));
	}	
	
	@Override
	public BaseResponse getListRelativeByParams(Map<String,List<?>> params,String orderBys) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getListRelativeByParams(params,orderBys));
	}	
		
	@Override
	public BaseResponse getListRelative(T record,String groupBys,String orderBys) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getListRelative(record,groupBys,orderBys));
	}	
	
	@Override
	public BaseResponse getListRelativeByParams(Map<String,List<?>> params,String groupBys,String orderBys) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getListRelativeByParams(params,groupBys,orderBys));
	}	
	
	@Override
	public BaseResponse getListRelative(String fields,T record,String orderBys) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getListRelative(fields,record,orderBys));
	}	
	
	@Override
	public BaseResponse getListRelativeByParams(String fields,Map<String,List<?>> params,String orderBys) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getListRelativeByParams(fields,params,orderBys));	
	}		
	
	@Override
	public BaseResponse getListRelative(String fields,T record,String groupBys,String orderBys) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getListRelative(fields,record,groupBys,orderBys));
	}	
	
	@Override
	public BaseResponse getListRelativeByParams(String fields,Map<String,List<?>> params,String groupBys,String orderBys) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getListRelativeByParams(fields,params,groupBys,orderBys));
	}	
	
	@Override
	public BaseResponse getList(T record,String orderBys) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getList(record,orderBys));
	}
	
	@Override
	public BaseResponse getListByParams(Map<String,List<?>> params,String orderBys) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getListByParams(params,orderBys));
	}
	
	@Override
	public BaseResponse getList(T record,String groupBys,String orderBys) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getList(record,groupBys,orderBys));
	}
	
	@Override
	public BaseResponse getListByParams(Map<String,List<?>> params,String groupBys,String orderBys) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getListByParams(params,groupBys,orderBys));
	}
	
	@Override
	public BaseResponse getList(String fields,T record,String orderBys) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getList(fields,record,orderBys));
	}
	
	@Override
	public BaseResponse getListByParams(String fields,Map<String,List<?>> params,String orderBys) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getListByParams(fields,params,orderBys));
	}
	
	@Override
	public BaseResponse getList(String fields,T record,String groupBys,String orderBys) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getList(fields,record,groupBys,orderBys));
	}	
	@Override
	public BaseResponse getListByParams(String fields,Map<String,List<?>> params,String groupBys,String orderBys) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getListByParams(fields,params,groupBys,orderBys));
	}
	/*******获取单个********/
	@Override
	public BaseResponse getOneById(String id) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getOneById(id));
	}
	@Override
	public BaseResponse getOneById(String fields,String id) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getOneById(fields,id));
	}
	@Override
	public BaseResponse getOne(T record) throws Exception {
		return BaseResponse.ObjectResponse(getModel().getOne(record));
	}

	@Override
	public BaseResponse getOne(String fields, T record) throws Exception {
		return BaseResponse.ObjectResponse(getModel().getOne(fields,record));
	}
	
	@Override
	public BaseResponse getOneByParams(Map<String,List<?>> whParams) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getOneByParams(whParams));
	}	
	@Override
	public BaseResponse getOneByParams(String fields,Map<String,List<?>> whParams) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getOneByParams(fields,whParams));
	}	
	@Override
	public BaseResponse getOneRelativeById(String id) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getOneRelativeById(id));
	}
	@Override
	public BaseResponse getOneRelativeById(String fields,String id) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getOneRelativeById(fields,id));
	}
	
	@Override
	public BaseResponse getOneRelative(T record) throws Exception {
		return BaseResponse.ObjectResponse(getModel().getOneRelative(record));
	}

	@Override
	public BaseResponse getOneRelative(String fields, T record) throws Exception {
		return BaseResponse.ObjectResponse(getModel().getOneRelative(fields,record));
	}
	
	@Override
	public BaseResponse getOneRelativeByParam(Map<String,List<?>> whParams) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getOneRelativeByParam(whParams));
	}
	@Override
	public BaseResponse getOneRelativeByParam(String fields,Map<String,List<?>> whParams) throws Exception{
		return BaseResponse.ObjectResponse(getModel().getOneRelativeByParam(fields,whParams));
	}
	
	/*********修改***********/
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse updateById(T record) throws Exception{
		return BaseResponse.countResponse("根据ID更新",getModel().updateById(record));
	}	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse updateRepeatById(T record) throws Exception{
		return BaseResponse.countResponse("根据ID更新查重",getModel().updateRepeatById(record));
	}	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse updateByParams(T record,Map<String, List<?>> params) throws Exception{
		return BaseResponse.countResponse("根据参数更新",getModel().updateByParams(record,params));
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse insert(T record) throws Exception{
		return BaseResponse.countResponse("直接增加数据",getModel().insert(record));
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse insertRepeat(T record) throws Exception{
		return BaseResponse.countResponse("增加数据查重",getModel().insertRepeat(record));
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse insertBatch(List<T> recordList) throws Exception{
		return BaseResponse.countResponse("批量添加",getModel().insertBatch(recordList));
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse insertByParams(Map<String, List<?>> params) throws Exception{
		return BaseResponse.countResponse("根据多值增加数据",getModel().insertByParams(params));
	}
	
	
	/*******删除********/
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse deleteAll() throws Exception{
		return BaseResponse.countResponse("删除全部数据",getModel().deleteAll());
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse delete(T record) throws Exception{
		return BaseResponse.countResponse("根据条件删除",getModel().delete(record));
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse deleteByParams(Map<String,List<?>> params){
		return BaseResponse.countResponse("根据参数删除",getModel().deleteByParams(params));
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse deleteById(String id) throws Exception{
		return BaseResponse.countResponse("根据ID删除",getModel().deleteById(id));
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse deleteByIds(String ids) throws Exception{
		return BaseResponse.countResponse("根据多个ID删除",getModel().deleteByIds(ids));
	}




}
