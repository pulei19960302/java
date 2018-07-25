package com.qim.loan.util.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * (OK) 类名:ReflectUtil 描述:反射工具 创建者:冯子文 创建时间: 2017年6月28日 下午1:46:14 更新者:冯子文 更新时间:
 * 2017年6月28日 下午1:46:14
 */
public class ReflectUtil {

	private static Logger logger = LoggerFactory.getLogger(ReflectUtil.class);
	
	
	public static <T,P> T setT(final Class<T> toCls,P fromObj){
		T t=getInstance(toCls);
		Field[] toFields = toCls.getDeclaredFields();
		List<String> toNameLs=getNameByField(toFields);
		Class<?> fromCls = fromObj.getClass();
		Field[] fromFields = fromCls.getDeclaredFields();
		String tmpName;
		Object tmpObject;
		for(Field tmp:fromFields){
			tmp.setAccessible(true);
			tmpName=tmp.getName();
			if(toNameLs.contains(tmpName) && !StringUtil.equalsIgnoreCase(tmpName, "serialVersionUID")){
				tmpObject=getMethodValue(fromObj,tmpName);
				if(tmpObject!=null)
					setDeclaredField(t, tmpName, tmpObject);	
			}
		}
		return t;
	} 
	
	public static List<String> getNameByField(Field[] fields){
		if(fields==null || fields.length==0)
			return null;
		int length=fields.length;
		String[] name=new String[length];
		for(int i=0;i<length;i++){
			fields[i].setAccessible(true);
			name[i]=fields[i].getName();
		}
		return Arrays.asList(name);
	}
	
	

	/**
	 * 
	 * 方法名:getInstance 功能描述:获取实例 创建者:冯子文 创建时间: 2018年1月21日 下午11:04:06 更新者:冯子文
	 * 更新时间: 2018年1月21日 下午11:04:06
	 */
	public static <T> T getInstance(final Class<T> cls) {
		T obj = null;
		try {
			obj = (T) cls.newInstance();
		} catch (Exception e) {
			MessageUtil.error(logger, "反射newInstance", e);
			obj = null;
		}
		return obj;
	}

	/**
	 * 
	 * 方法名:getInstance 功能描述:获取实例 创建者:冯子文 创建时间: 2018年1月21日 下午11:04:17 更新者:冯子文
	 * 更新时间: 2018年1月21日 下午11:04:17
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getInstance(final String className) {
		T obj = null;
		try {
			obj = (T) Class.forName(className).newInstance();
		} catch (Exception e) {
			MessageUtil.error(logger, "forName", e);
			obj = null;
		}
		return obj;
	}

	/**
	 * 
	 * 方法名:setDeclaredField 功能描述:设置声明域的值 创建者:冯子文 创建时间: 2018年1月21日 下午11:06:57
	 * 更新者:冯子文 更新时间: 2018年1月21日 下午11:06:57
	 */
	public static <T> Boolean setDeclaredField(final Object object, final String fieldNameIgnoreCase, final Object fieldValue) {
		try {
			Class<?> obj = object.getClass();
			Field[] fields = obj.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				if (fieldNameIgnoreCase.toLowerCase().equals(fields[i].getName().toLowerCase())) {
					fields[i].set(object, fieldValue);
					return true;
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			MessageUtil.error(logger, "设置申明域", e);
			e.printStackTrace();
		}
		return false;
	}
	
	public static Field[] getFields(final Object object){
		return  object.getClass().getDeclaredFields();
	}
	
	public static String[] getFieldNames(final Object object){
		Field[] fields = object.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
		String[] fieldNames=new String[fields.length];
		for (int i = 0; i < fields.length; i++){
			fields[i].setAccessible(true);
			if(!StringUtil.equalsIgnoreCase(fields[i].getName(), "serialVersionUID")){
				fieldNames[i]=fields[i].getName();
			}
		}
		return fieldNames;
	}
	
	
	

	/**
	 * 
	 * 方法名:getFieldType 功能描述:根据参数返回相应参数类型 创建者:冯子文 创建时间: 2018年1月21日 下午11:10:21
	 * 更新者:冯子文 更新时间: 2018年1月21日 下午11:10:21
	 */
	public static String getFieldType(final Object object, final String fieldNameIgnoreCase) {
		Field[] fields = object.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
		for (int i = 0; i < fields.length; i++)
			// 遍历所有属性
			if (fieldNameIgnoreCase.toLowerCase().equals(fields[i].getName().toLowerCase()))
				return fields[i].getGenericType().toString(); // 获取属性的类型
		return "";
	}

	/**
	 * 
	 * 方法名:setMethodValue 功能描述:设置setter方法的值 创建者:冯子文 创建时间: 2018年1月21日 下午11:11:14
	 * 更新者:冯子文 更新时间: 2018年1月21日 下午11:11:14
	 */
	public static <T> Boolean setMethodValue(final Object object, final String fieldName, final T value) {
		String tempName = StringUtil.firstCharUprCamel(fieldName);
		try {
			Method setMethod = object.getClass().getMethod("set" + tempName, getRealType(object, tempName));
			setMethod.invoke(object, value);
			return true;
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			MessageUtil.error(logger, "setMethod invoke", e);
			// e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * 方法名:getMethodValue 功能描述:获取getter方法的值 创建者:冯子文 创建时间: 2018年1月21日 下午11:11:52
	 * 更新者:冯子文 更新时间: 2018年1月21日 下午11:11:52
	 */
	public static Object getMethodValue(final Object object, final String fieldName) {
		String tempName = StringUtil.firstCharUprCamel(fieldName);
		try {
			Method getMethod = object.getClass().getMethod("get" + tempName);
			return getMethod.invoke(object);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			MessageUtil.error(logger, "getMethod invoke", e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 方法名:getT 功能描述:获取实体 创建者:冯子文 创建时间: 2018年1月21日 下午11:13:20 更新者:冯子文 更新时间:
	 * 2018年1月21日 下午11:13:20
	 */
	public static <T> T getEntity(final Class<T> BaseEntity) {
		return (T) ReflectUtil.getInstance(BaseEntity);
	}

	/**
	 * 
	 * 方法名:setSingleField 功能描述:设置单个健的键 创建者:冯子文 创建时间: 2018年1月21日 下午11:13:49
	 * 更新者:冯子文 更新时间: 2018年1月21日 下午11:13:49
	 */
	public static <T> Boolean setSingleField(final T t, final String fieldName, final Object fieldValue) {
		return ReflectUtil.setDeclaredField(t, fieldName, fieldValue);
	}

	/**
	 * 
	 * 方法名:setMultiField 功能描述:设置多域的值 创建者:冯子文 创建时间: 2018年1月21日 下午11:18:10 更新者:冯子文
	 * 更新时间: 2018年1月21日 下午11:18:10
	 */
	public static <T, U> Boolean setMultiField(final T t, final Map<String, U> map) {
		Boolean flag = true;
		if (map == null)
			return false;
		for (String key : map.keySet())
			flag = flag && setSingleField(t, key, map.get(key));
		return flag;
	}

	/**
	 * 
	 * 方法名:setMultiField 功能描述:设置多域的值 创建者:冯子文 创建时间: 2018年1月21日 下午11:20:35 更新者:冯子文
	 * 更新时间: 2018年1月21日 下午11:20:35
	 */
	public static <T, U> Boolean setMultiField(final T t, final String[] fieldNameArr, final Object[] fieldValueArr) {
		Boolean flag = true;
		if (fieldNameArr == null || fieldNameArr.length == 0)
			return false;
		for (int i = 0; i < fieldNameArr.length; i++)
			flag = flag && setSingleField(t, fieldNameArr[i], fieldValueArr[i]);

		return flag;
	}

	/**
	 * 
	 * 方法名:getRealType 功能描述:获取真实Java基本类型 创建者:冯子文 创建时间: 2018年1月21日 下午11:27:49
	 * 更新者:冯子文 更新时间: 2018年1月21日 下午11:27:49
	 */
	public static Class<?> getRealType(final Object object, final String fieldName) {
		String type = getFieldType(object, fieldName);
		type = type.toLowerCase();
		if (type.toLowerCase().indexOf("byte") > 0)
			return Byte.class;
		if (type.toLowerCase().indexOf("float") > 0)
			return Float.class;
		if (type.toLowerCase().indexOf("double") > 0)
			return Double.class;
		if (type.toLowerCase().indexOf("short") > 0)
			return Short.class;
		if (type.toLowerCase().indexOf("long") > 0)
			return Long.class;
		if (type.toLowerCase().indexOf("map") > 0)
			return Map.class;
		if (type.toLowerCase().indexOf("list") > 0)
			return List.class;
		if (type.toLowerCase().indexOf("set") > 0)
			return Set.class;
		if (type.toLowerCase().indexOf("stack") > 0)
			return Stack.class;
		if (type.toLowerCase().indexOf("string") > 0)
			return String.class;
		if (type.toLowerCase().indexOf("biginteger") > 0)
			return BigInteger.class;
		if (type.toLowerCase().indexOf("integer") > 0)
			return Integer.class;
		if (type.toLowerCase().indexOf("boolean") > 0)
			return Boolean.class;
		if (type.toLowerCase().indexOf("date") > 0)
			return Date.class;
		if (type.toLowerCase().indexOf("bigdecimal") > 0)
			return BigDecimal.class;
		return Object.class;
	}

	public static void main(String[] args) {

	}

}
