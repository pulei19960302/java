package com.qim.loan.util.common;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * (OK)
     * 类名:SetUtil
     * 描述:Set工具	 
     * 创建者:冯子文
     * 创建时间: Dec 8, 2017 10:12:48 AM 
	 * 更新者:冯子文   
	 * 更新时间: Dec 8, 2017 10:12:48 AM
 */
public class SetUtil {
	
	public static <E> Set<E> toList(List<E> list){
		return new HashSet<E>(list);
	}
	
	public static String toString(Set<String> set){
		if(set==null || set.size()==0)
			return "";
		StringBuilder sbBuilder=new StringBuilder();
		for(String key:set)
			sbBuilder.append(key+",");
		if(sbBuilder!=null && sbBuilder.length()>0)
			sbBuilder.deleteCharAt(sbBuilder.length()-1);
		return sbBuilder.toString();
	}
	
	
	//差集
	public static <E> Set<E> subtract(Set<E> a,Set<E> b){		
		Set<E> temp=new HashSet<E>();
		temp.addAll(a);
		temp.removeAll(b);
		return temp;
	}
	//交集
	public static <E> Set<E> intersect(Set<E> a,Set<E> b){
		Set<E> temp=new HashSet<E>();
		temp.addAll(a);
		temp.retainAll(b);
		return temp;
	}	
	//并集
	public static <E> Set<E> union(Set<E> a,Set<E> b){
		Set<E> temp=new HashSet<E>();
		temp.addAll(a);
		temp.addAll(b);
		return temp;
	}
	
	public static Set<String> toCamel(Set<String> map) {
		if (null != map && map.size()>0){
			Set<String> data = new HashSet<String>();
			for(String key:map)
				data.add(StringUtil.toCamel(key));
			return data;			
		}else{
			return null;
		}
	}
	
	public static Set<String> toStrip(Set<String> map) {
		if (null != map && map.size()>0){
			Set<String> data = new HashSet<String>();
			for(String key:map)
				data.add(StringUtil.toStrip(key));
			return data;			
		}else{
			return null;
		}
	}	
	
	public static void main(String[] args) {

	}

}
