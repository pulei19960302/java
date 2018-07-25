package com.qim.loan.util.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qim.loan.util.excel.PhoneName;
/**
 * 
     * 类名:ListUtil
     * 描述:list工具	 
     * 创建者:冯子文
     * 创建时间: Dec 8, 2017 10:11:38 AM 
	 * 更新者:冯子文   
	 * 更新时间: Dec 8, 2017 10:11:38 AM
 */
public class ListUtil {
	
	public static <E> List<E> getSize(List<E> list,Integer size){
		if(isNull(list) || size==null || size==0)
			return null;	
		if(list.size()>size){
			List<E> result=new ArrayList<E>();
			for(int i=0;i<size;i++)
				result.add(list.get(i));
			return result;
		}else
			return list;
	}	
	public static  List<String> toList(String str){
		if(StringUtil.isNull(str))
			return null;
		return Arrays.asList(str.split(","));
	}
	
	
	public static <E> Boolean isNull(List<E> list){
		if(list!=null && list.size()>0 && list.get(0)!=null)
			return false;
		return true;
	}
	
	public static <E> Boolean isNotNull(List<E> list){
		return !isNull(list);
	}
	
	public static <E> List<E> toList(Set<E> set){
		return new ArrayList<E>(set);
	}
	
	
	//差集
	public static <E> List<E> subtract(List<E> a,List<E> b){
		if(isNull(b))
			return a;
		if(isNull(a))
			return null;
		List<E> temp=new ArrayList<E>();
		temp.addAll(a);
		temp.removeAll(b);
		return temp;
	}
	//交集
	public static <E> List<E> interSect(List<E> a,List<E> b){
		List<E> temp=new ArrayList<E>();
		temp.addAll(a);
		temp.retainAll(b);
		return temp;
	}
	
	//并集
	public static <E> List<E> union(List<E> a,List<E> b){
		List<E> temp=new ArrayList<E>();
		temp.addAll(a);
		temp.addAll(b);
		return temp;
	}
	
	
	
	
	
	
	public static  Map<String,List<PhoneName>> phoneNameUnion(List<PhoneName> a,List<PhoneName> b){
		Map<String,List<PhoneName>> returnMap = new HashMap<String,List<PhoneName>>();
		List<PhoneName> namePhoneList = new ArrayList<PhoneName>();
		for (int i = 0; i < a.size(); i++) {
			PhoneName pName = a.get(i);
			for (int j = 0; j < b.size(); j++) {
				PhoneName phoneName = b.get(j);
				// 判断电话号码一样
					if(pName.getPhone().equals(phoneName.getPhone())) {
							if(!pName.getName().equals(phoneName.getName())) {
		                    	namePhoneList.add(pName);
		                    	a.remove(phoneName);
		                    }
					}
			
			}
		}
		for (int i = 0; i < b.size(); i++) {
			PhoneName pName = b.get(i);
			for (int j = 0; j < a.size(); j++) {
				PhoneName phoneName = a.get(j);
			         if(phoneName.getPhone().equals(pName.getPhone())) {
			        	    a.remove(phoneName); 
			         }
			      }
			}
		
		returnMap.put("namePhone", a);
		returnMap.put("phoneName", namePhoneList);
		return returnMap;
	}
	
	
	public static void main(String[] args) {

		List<PhoneName> a = new ArrayList<PhoneName>();
		a.add(new PhoneName("张三","13051830559"));
		a.add(new PhoneName("李四","13051830558"));
		a.add(new PhoneName("王伟","13051830557"));
		
		List<PhoneName> b = new ArrayList<PhoneName>();
		b.add(new PhoneName("张三00","13051830559"));
		b.add(new PhoneName("李四","13051830559"));
        b.add(new PhoneName("1234","13051830557"));		
		Map<String,List<PhoneName>> c =  phoneNameUnion(a,b);
		List<PhoneName> aList = c.get("namePhone");
		List<PhoneName> bList = c.get("phoneName");
	    for (PhoneName phoneName : bList) {
			  System.out.println("phoneName:"+phoneName.getName()+"||"+phoneName.getPhone());
		}
	    for (PhoneName phoneName : aList) {
	    	 System.out.println("namePhone:"+phoneName.getName()+"||"+phoneName.getPhone());
		}
		
		
	}
	
	
	
	public static List<String> toCamelStr(List<String> list) {	
		if(isNull(list))
			return null;
		List<String> data=new ArrayList<String>();
		for(String map:list)
			data.add(StringUtil.toCamel(map));
		return data;			
	}
	
	public static List<String> toCamelSplit(List<String> list) {	
		if(isNull(list))
			return null;
		List<String> data=new ArrayList<String>();
		for(String map:list){
			String prefix=map.substring(0,map.indexOf(".")+1);
			String suffix=map.substring(map.indexOf(".")+1, map.length());
			data.add(prefix+StringUtil.toCamel(suffix));
		}
		return data;			
	}
	
	
	public static List<String> getValue(List<Map<String, Object>> ls){
		List<String> result=new ArrayList<String>();
		if(isNull(ls)) {
			return null;
		}else {
			for(Map<String, Object> map:ls) {
				if(map!=null)
					for(String key:map.keySet()) {
						result.add(String.valueOf(map.get(key)));
					}
			}
			return result;
		}
	}
	
	public static List<PhoneName> getValuePhoneName(List<Map<String, Object>> ls){
		List<PhoneName> result=new ArrayList<PhoneName>();
		PhoneName phoneName = null;
		if(isNull(ls)) {
			return null;
		}else {
			for(Map<String, Object> map:ls)
				for(String key:map.keySet()) { 
					phoneName = new PhoneName();
					if(key.equals("real_name")) {
						  phoneName.setName(map.get(key).toString());
					}
			        if(key.equals("telphoneNumber")){
						  phoneName.setPhone(String.valueOf(map.get(key)));
					}
			        result.add(phoneName);
				}
			return result;
		}
	}
	
	
	
	
	public static List<Map<String, Object>> toCamel(List<Map<String, Object>> list) {	
		if(isNull(list))
			return null;
		List<Map<String, Object>> data=new ArrayList<Map<String, Object>>();
		for(Map<String, Object> map:list)
			data.add(MapUtil.toCamel(map));
		return data;			
	}	
	public static List<Map<String, Object>> toStrip(List<Map<String, Object>> list) {	
		if(isNull(list))
			return null;		
		List<Map<String, Object>> data=new ArrayList<Map<String, Object>>();
		for(Map<String, Object> map:list)
			data.add(MapUtil.toStrip(map));
		return data;			
	}
	
	
	public static List<String> toStripSplit(List<String> list) {	
		if(isNull(list))
			return null;
		List<String> data=new ArrayList<String>();
		for(String map:list){
			String prefix=map.substring(0,map.indexOf(".")+1);
			String suffix=map.substring(map.indexOf(".")+1, map.length());
			data.add(prefix+StringUtil.toStrip(suffix));
		}
		return data;			
	}
	
	
	public static List<String> toStripStr(List<String> list) {	
		if(isNull(list))
			return null;		
		List<String> data=new ArrayList<String>();
		for(String map:list)
			data.add(StringUtil.toStrip(map));
		return data;			
	}	
	
	
	public static List<Integer> toInteger(List<String> list){
		List<Integer> temp=new ArrayList<Integer>();
		for(String key:list)
			temp.add(StringUtil.toInteger(key));
		return temp;
	}
	public static List<Long> toLong(List<String> list){
		List<Long> temp=new ArrayList<Long>();
		for(String key:list)
			temp.add(StringUtil.toLong(key));
		return temp;		
	}
	public static List<Double> toDouble(List<String> list){
		List<Double> temp=new ArrayList<Double>();
		for(String key:list)
			temp.add(StringUtil.toDouble(key));
		return temp;		
	}
	public static List<Float> toFloat(List<String> list){
		List<Float> temp=new ArrayList<Float>();
		for(String key:list)
			temp.add(StringUtil.toFloat(key));
		return temp;		
	}	
	


}
