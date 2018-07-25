package com.qim.loan.util.common;

import java.util.ArrayList;
import java.util.List;


/**
 * (OK)
     * 类名:IntegerUtil
     * 描述:包装整形工具	 
     * 创建者:冯子文
     * 创建时间: Dec 8, 2017 9:55:45 AM 
	 * 更新者:冯子文   
	 * 更新时间: Dec 8, 2017 9:55:45 AM
 */
public class IntegerUtil {

	/**
	 * 
	     * 方法名:toArray
	     * 功能描述:字符串转换为数组	 
	     * 创建者:冯子文
	     * 创建时间: 2018年1月23日 下午9:22:30 
		 * 更新者:冯子文  
		 * 更新时间: 2018年1月23日 下午9:22:30
	 */
	public static Integer[] toArray(String[] array){
		Integer[] result=new Integer[array.length];
		for(int i=0;i<array.length;i++)
			result[i]=Integer.valueOf(array[i]);
		return result;
	}
	/**
	 * 
	     * 方法名:toList
	     * 功能描述:转化为List
	     * 创建者:冯子文
	     * 创建时间: 2018年1月23日 下午9:25:23 
		 * 更新者:冯子文  
		 * 更新时间: 2018年1月23日 下午9:25:23
	 */
	public static List<Integer> toList(List<String> arrayLs){
		if(ListUtil.isNull(arrayLs))
			return null;
		List<Integer> result=new ArrayList<Integer>();
		for(String tmp:arrayLs)
			if(StringUtil.isNotNull(tmp))
				result.add(Integer.valueOf(tmp));
		return result;
	}
	
	
	
	
	
}
