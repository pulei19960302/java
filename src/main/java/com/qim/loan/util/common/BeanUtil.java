package com.qim.loan.util.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.cglib.beans.BeanMap;
/**
 * (OK)
     * 类名:BeanUtil
     * 描述:Bean工具类	 
     * 创建者:冯子文
     * 创建时间: Dec 8, 2017 10:09:34 AM 
	 * 更新者:冯子文   
	 * 更新时间: Dec 8, 2017 10:09:34 AM
 */
public class BeanUtil{
	/**
	 * 
	     * 方法名:mappedTo
	     * 功能描述:实体映射	 
	     * 创建者:冯子文
	     * 创建时间: May 2, 2018 2:18:52 PM 
		 * 更新者:冯子文  
		 * 更新时间: May 2, 2018 2:18:52 PM
	 */
	public static <T,U> List<U> mappedTo(List<T> frmBeanLs,String[] frmFields,Class<U> clsU,String[] toFields){
		List<U> lsList=new LinkedList<U>();
		for(T t:frmBeanLs){
			U tmp=mappedTo(t,frmFields,clsU,toFields);
			if(tmp!=null)
				lsList.add(tmp);
		}
		return lsList;
	}
	
	/**
	 * 
	     * 方法名:mappedTo
	     * 功能描述:实体映射	 
	     * 创建者:冯子文
	     * 创建时间: May 2, 2018 2:18:39 PM 
		 * 更新者:冯子文  
		 * 更新时间: May 2, 2018 2:18:39 PM
	 */
	public static <T,U> U mappedTo(T frmBean,String[] frmFields,Class<U> clsU,String[] toFields){
		U u=getT(clsU);
		Boolean flag=mappedTo(frmBean,frmFields,u,toFields);
		if(flag)
			return u;
		return null;
	}
	
	/**
	 * 
	     * 方法名:mappedTo
	     * 功能描述:实体映射	 
	     * 创建者:冯子文
	     * 创建时间: May 2, 2018 2:18:24 PM 
		 * 更新者:冯子文  
		 * 更新时间: May 2, 2018 2:18:24 PM
	 */
	public static <T,U> Boolean mappedTo(T frmBean,String[] frmFields,U tobean,String[] toFields){
		if((frmFields==null || frmFields.length==0) || (toFields==null ||  toFields.length==0)){
			if((frmFields==null || frmFields.length==0) && (toFields==null ||  toFields.length==0)){
				String[] frmFieldNames=ReflectUtil.getFieldNames(frmBean);
				String[] toFieldNames=ReflectUtil.getFieldNames(tobean);
				String[] commonNames=StringUtil.intersect(frmFieldNames, toFieldNames);

				return mappedTo(frmBean,commonNames,tobean,commonNames);
			}else{
				return false;//失败
			}
		}else{//非空情况
			int frmLen=frmFields.length;
			int toLen=toFields.length;
			if(frmLen==toLen){
				for(int i=0;i<frmLen;i++){
					Object tmp=getSingleField(frmBean,frmFields[i]);
					if(tmp!=null)
						setSingleField(tobean,toFields[i],tmp);
				}
				return true;
			}else{
				return false;
			}
		}
	}
	
	/*********基础方法*********/
	public static <T> T getT(Class<T> cls) {
		return ReflectUtil.getInstance(cls);
	}

	public static <T> String getFieldType(T t, String primaryKeyName) {
		return ReflectUtil.getFieldType(t, primaryKeyName);
	}

	// 获取T下parimaryKey的值
	public static <T> Object getSingleField(T t, String primaryKeyName) {
		return ReflectUtil.getMethodValue(t, primaryKeyName);
	}

	public static <T> Boolean setSingleField(T t, String primaryKeyName, Object physicsKeyValue) {
		return ReflectUtil.setMethodValue(t, primaryKeyName, physicsKeyValue);
	}
	
	/**
	 * 
	     * 方法名:isNull
	     * 功能描述:判断对象是否为空	 
	     * 创建者:冯子文
	     * 创建时间: 2018年1月23日 下午9:03:48 
		 * 更新者:冯子文  
		 * 更新时间: 2018年1月23日 下午9:03:48
	 */
	public static Boolean isNull(Object obj){
		if(obj==null)
			return true;
		String str= GsonUtil.toJson(obj);
		if(StringUtil.equal(str, "{}"))
			return true;
		return false;
	}
	/**
	 * 
	     * 方法名:isNotNull
	     * 功能描述:判断对象是否不为空	 
	     * 创建者:冯子文
	     * 创建时间: 2018年1月23日 下午9:04:02 
		 * 更新者:冯子文  
		 * 更新时间: 2018年1月23日 下午9:04:02
	 */
	public static Boolean isNotNull(Object obj){
		return !isNull(obj);
	}
	/**
	 * 
	     * 方法名:toMap
	     * 功能描述:Bean转为Map	 
	     * 创建者:冯子文
	     * 创建时间: 2018年1月23日 下午9:10:07 
		 * 更新者:冯子文  
		 * 更新时间: 2018年1月23日 下午9:10:07
	 */
	public static <T> Map<String, Object> toMap(final T bean) {
		if(bean==null)
			return null;
		Map<String, Object> map = new HashMap<String, Object>();
		BeanMap tmpBean= BeanMap.create(bean);
		for (Object key : tmpBean.keySet())
				map.put(String.valueOf(key), tmpBean.get(key));
		return map;
	}	
	/**
	 * 
	     * 方法名:beanList转为MapList
	     * 功能描述:	 
	     * 创建者:冯子文
	     * 创建时间: 2018年1月23日 下午9:10:22 
		 * 更新者:冯子文  
		 * 更新时间: 2018年1月23日 下午9:10:22
	 */
	public static <T> List<Map<String, Object>> toMapLs(List<T> objList) {
		if(ListUtil.isNull(objList))
			return null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(T tmp:objList)
			if(tmp!=null)
				list.add(toMap(tmp));
		return list;
	}
	
	public static void main(String[] args) {
			
	}

}
