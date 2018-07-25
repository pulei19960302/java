package com.qim.loan.core.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;



public interface BaseDao<T>{	
	/*********查询列表相关***********/
	List<Map<String,Object>> getListRelative(@Param("fieldLs")List<String> fields,@Param("fieldLrs")List<String> fieldrs,@Param("record")T record,@Param("groupByLs")List<String> groupByLs,@Param("orderByLs")List<String> orderBys) throws Exception;	
	
	List<Map<String,Object>> getListRelativeByParams(@Param("fieldLs")List<String> fields,@Param("fieldLrs")List<String> fieldrs,@Param("whParams")Map<String,List<?>> params,@Param("groupByLs")List<String> groupByLs,@Param("orderByLs")List<String> orderBys) throws Exception;	
	
	/**********查询列表本身*************/
	//通过条件查询所需字段名	
	List<T> getList(@Param("fields")String fields,@Param("record")T record,@Param("groupBys")String groupBys,@Param("orderBys")String orderBys) throws Exception;
	//通过本身获取自己
	List<T> getListByParams(@Param("fields")String fields,@Param("whParams")Map<String,List<?>> params,@Param("groupBys")String groupBys,@Param("orderBys")String orderBys) throws Exception;
	
	/********获取单挑记录相关*****/
	//根据ID获取相应关联字段(测试完成)
	Map<String,Object> getOneRelativeById(@Param("fieldLs")List<String> fields,@Param("fieldLrs")List<String> fieldrs,@Param("id")String id) throws Exception;
	//需要
	Map<String,Object> getOneRelative(@Param("fieldLs")List<String> fields,@Param("fieldLrs")List<String> fieldrs,@Param("record")T record) throws Exception;	
	//根据条件获取相应关联字段
	Map<String,Object> getOneRelativeByParams(@Param("fieldLs")List<String> fields,@Param("fieldLrs")List<String> fieldrs,@Param("whParams")Map<String,List<?>> whParams) throws Exception;

	/*******获取单条记录**********/
	//根据ID获取相应字段(测试完成)
	T getOneById(@Param("fields")String fields,@Param("id")String id) throws Exception;
	
	T getOne(@Param("fields")String fields,@Param("record")T record) throws Exception;
	//根据条件获取相应字段
	T getOneByParams(@Param("fields")String fields,@Param("whParams")Map<String,List<?>> whParams) throws Exception;
	
	/******查询总记录数**********/
	//查询数量专用(ID不等于)
	int getCount(T record) throws Exception;	
	//根据条件查询记录数量(ID不等于)
	int getCountByParams(@Param("whParams")Map<String,List<?>> whParams) throws Exception;	
	
	/*********添加*********/
	//添加
	int insert(T record) throws Exception;
	//添加(批量)
	int insertBatch(List<T> recordList) throws Exception;
	//根据多条件插入
	int insertByParams(@Param("fields")String fields,@Param("vlParamLs") List<Map<String,?>> vlParamLs) throws Exception;

	/******修改******/
	//修改(通过主键)
    int updateById(@Param("record")T record) throws Exception;
    //根据多条件多值进行更新
    int updateByParams(@Param("record")T record,@Param("whParamLs") List<Map<String,?>> whParamLs);
    
    /*******删除*******/
    //删除全部(修改完成)
    int deleteAll() throws Exception;
    //通过条件删除(修改完成)
    int delete(@Param("record")T record) throws Exception;
    //通过多条件删除(修改完成)
    int deleteByParams(@Param("whParams")Map<String,List<?>> whParams);
    //通过主键删除(修改完成)
    <U> int deleteById(@Param("id")U id) throws Exception;
    //批量删除(修改完成)
    <U> int deleteByIds(@Param("idLs")List<String> idLs) throws Exception;
}
