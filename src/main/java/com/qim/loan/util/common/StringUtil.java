package com.qim.loan.util.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * (OK)
 * 类名:StringUtil 描述:字符串工具 创建者:冯子文 创建时间: Dec 8, 2017 10:13:15 AM 更新者:冯子文 更新时间:
 * Dec 8, 2017 10:13:15 AM
 */
public class StringUtil {

	
	public static String format(String format, Object... args){
		return String.format(format,args);   
	}
	
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
    
    public static boolean equals(CharSequence cs1, CharSequence cs2) {
        return cs1 == null ? cs2 == null : cs1.equals(cs2);
    }
    
    public static boolean equalsIgnoreCases(CharSequence str1, CharSequence str2) {
        if (str1 == null || str2 == null) {
            return str1 == str2;
        } else {
            return regionMatches(str1, true, 0, str2, 0, Math.max(str1.length(), str2.length()));
        }
    }
    public static boolean regionMatches(CharSequence cs, boolean ignoreCase, int thisStart,
            CharSequence substring, int start, int length)    {
        if (cs instanceof String && substring instanceof String) {
            return ((String) cs).regionMatches(ignoreCase, thisStart, (String) substring, start, length);
        } else {
            return cs.toString().regionMatches(ignoreCase, thisStart, substring.toString(), start, length);
        }
    }	
	public static Boolean isNull(final String str){
		String tmp=str;
		if(!isEmpty(str))
			tmp=tmp.trim().replace("null", "").replace("undefined", "");
		return isEmpty(tmp);
	}
	
	public static String replaceInvalid(final String str){
		if(isNotNull(str))
			return str.trim().replace("null", "").replace("undefined", "");
		return "";
	}
	
	public static List<String> getOrderBy(String orderBy){
		if(StringUtil.isNull(orderBy) || StringUtil.isNull(replaceInvalid(orderBy).trim())){
			return null;
		}else{
			orderBy=replaceInvalid(orderBy).trim();
			return  Arrays.asList(toStrip(StringUtil.replaceSort(orderBy.trim())).split(","));
		}
	}
	
	public static String replace(String str,String pattern,String replaceStr){
		String patterns = "(?i)"+pattern;
		return str.replaceAll(patterns,replaceStr);
	}
	
	public static String replaceSort(String str){
		if(isNull(str))
			return "";
		String tmp=replace(str,"desc","desc");	
		return replace(tmp,"asc","asc");
	}
	
	public static Boolean equal(final String str,final String ostr){
		return equals(str, ostr);
	}
	
	public static Boolean equalsIgnoreCase(final String str,final String ostr){
		return equalsIgnoreCases(str, ostr);
	}
	
	public static String firstLowCharCamel(String str) {
		return firstLowChar(toCamel(str));
	}
	
	public static String firstUprCharCamel(String str) {
		return firstUprChar(toCamel(str));
	}

	public static String firstLowCharStrip(String str) {
		return firstLowChar(toStrip(str));
	}
	
	public static String firstUprCharStrip(String str) {
		return firstUprChar(toStrip(str));
	}	
	
	//转化为小写
	public static String firstLowChar(String str) {
		char[] arr=str.toCharArray();
		arr[0]+=32;
		return String.valueOf(arr);
	}	
	//转换为大写
	public static String firstUprChar(String str) {
		char[] arr=str.toCharArray();
		arr[0]-=32;
		return String.valueOf(arr);
	}	
	/**
	 * 
	     * 方法名:removeInvalid
	     * 功能描述:清除无效字符	 
	     * 创建者:冯子文
	     * 创建时间: 2018年1月24日 下午9:53:46 
		 * 更新者:冯子文  
		 * 更新时间: 2018年1月24日 下午9:53:46
	 */
	public static String removeInvalid(final String str){
		if(isNull(str))
			return "";
		return str.replace("null", "").replace("undefined","");		
	}

	/**
	 * 
	 * 方法名:isNotNull 功能描述:非空 创建者:冯子文 创建时间: 2018年1月21日 下午9:06:00 更新者:冯子文 更新时间:
	 * 2018年1月21日 下午9:06:00
	 */
	public static Boolean isNotNull(final String str) {
		return !isNull(str);
	}

	/**
	 * 
	 * 方法名:replaceIgnoreCase 功能描述:忽略大小写替换 创建者:冯子文 创建时间: 2018年1月21日 下午10:09:41
	 * 更新者:冯子文 更新时间: 2018年1月21日 下午10:09:41
	 */
	public static String replaceIgnoreCase(final String str, final String regexStr, final String replaceStr) {
		return RegexUtil.replaceIgnoreCase(str, regexStr, replaceStr);
	}
	
	/**
	 * 
	 * 方法名:replaceOrderBy 功能描述:替换orderBy的desc及asc 创建者:冯子文 创建时间: 2018年1月21日
	 * 下午10:10:27 更新者:冯子文 更新时间: 2018年1月21日 下午10:10:27
	 */
	public static String replaceOrderBy(final String str) {
		if (isNull(str))
			return "";
		String tmp = replaceIgnoreCase(str, "desc", "desc");
		return replaceIgnoreCase(tmp, "asc", "asc");
	}

	/**
	 * 
	 * 方法名:firstCharLow 功能描述:首字母小写 创建者:冯子文 创建时间: 2018年1月21日 下午10:24:28 更新者:冯子文
	 * 更新时间: 2018年1月21日 下午10:24:28
	 */
	public static String firstCharLow(String str) {
		char[] cs = str.trim().toCharArray();
		if(cs[0]>=65 && cs[0]<=90)
			cs[0] += 32;
		return String.valueOf(cs);
	}

	/**
	 * 
	 * 方法名:toCamel 功能描述:转换位驼峰命名 创建者:冯子文 创建时间: 2018年1月21日 下午10:31:25 更新者:冯子文
	 * 更新时间: 2018年1月21日 下午10:31:25
	 */
	public static String toCamel(String str) {
		return RegexUtil.toCamel(str);
	}

	/**
	 * 
	 * 方法名:toStrip 功能描述:将驼峰转换为_s 创建者:冯子文 创建时间: 2018年1月21日 下午10:32:53 更新者:冯子文
	 * 更新时间: 2018年1月21日 下午10:32:53
	 */
	public static String toStrip(String str) {
		return RegexUtil.toStrip(str);
	}

	/**
	 * 
	 * 方法名:firstCharUpr 功能描述:首字母大写 创建者:冯子文 创建时间: 2018年1月21日 下午10:23:56 更新者:冯子文
	 * 更新时间: 2018年1月21日 下午10:23:56
	 */
	public static String firstCharUpr(String str) {
		char[] cs = str.toCharArray();
		if(cs[0]>=97 && cs[0]<=122)
			cs[0] -= 32;
		return String.valueOf(cs);
	}

	/**
	 * 
	 * 方法名:firstCharLowCamel 功能描述:首字母小写并驼峰 创建者:冯子文 创建时间: 2018年1月21日 下午10:27:25
	 * 更新者:冯子文 更新时间: 2018年1月21日 下午10:27:25
	 */
	public static String firstCharLowCamel(String str) {
		return firstCharLow(toCamel(str));
	}

	/**
	 * 
	 * 方法名:firstCharUprCamel 功能描述:首字母大写并驼峰 创建者:冯子文 创建时间: 2018年1月21日 下午10:27:29
	 * 更新者:冯子文 更新时间: 2018年1月21日 下午10:27:29
	 */
	public static String firstCharUprCamel(String str) {
		return firstCharUpr(toCamel(str));
	}

	/**
	 * 
	 * 方法名:firstCharLowStrip 功能描述:首字母小写—— 创建者:冯子文 创建时间: 2018年1月21日 下午10:42:00
	 * 更新者:冯子文 更新时间: 2018年1月21日 下午10:42:00
	 */
	public static String firstCharLowStrip(String str) {
		return firstCharLow(toCamel(str));
	}

	/**
	 * 
	 * 方法名:toList 功能描述:字符串转List 创建者:冯子文 创建时间: 2018年1月21日 下午10:36:27 更新者:冯子文
	 * 更新时间: 2018年1月21日 下午10:36:27
	 */
	public static List<String> toList(String str) {
		if (isEmpty(str))
			return new ArrayList<String>();
		return Arrays.asList(str.split(","));
	}
	
	public static List<String> toList(List<String> list,String containChar) {
		List<String> result=new ArrayList<String>();
		for(String tmp:list)
			if(tmp.contains(containChar))
				result.add(tmp);
		return (result!=null && result.size()>0)?result:null;
	}
	
	
	

	/**
	 * 
	 * 方法名:toInteger 功能描述:转integer 创建者:冯子文 创建时间: 2018年1月21日 下午10:36:40 更新者:冯子文
	 * 更新时间: 2018年1月21日 下午10:36:40
	 */
	public static Integer toInteger(String str) {
		return Integer.valueOf(str);
	}

	/**
	 * 
	 * 方法名:toLong 功能描述:转Long 创建者:冯子文 创建时间: 2018年1月21日 下午10:36:51 更新者:冯子文 更新时间:
	 * 2018年1月21日 下午10:36:51
	 */
	public static Long toLong(String str) {
		return Long.valueOf(str);
	}

	/**
	 * 
	 * 方法名:toDouble 功能描述:转为double 创建者:冯子文 创建时间: 2018年1月21日 下午10:37:22 更新者:冯子文
	 * 更新时间: 2018年1月21日 下午10:37:22
	 */
	public static Double toDouble(String str) {
		return Double.valueOf(str);
	}

	/**
	 * 
	 * 方法名:toFloat 功能描述:转为float 创建者:冯子文 创建时间: 2018年1月21日 下午10:37:34 更新者:冯子文
	 * 更新时间: 2018年1月21日 下午10:37:34
	 */
	public static Float toFloat(String str) {
		return Float.valueOf(str);
	}

	/**
	 * 
	 * 方法名:getSuffix 功能描述:获取文件后缀 创建者:冯子文 创建时间: 2018年1月21日 下午10:46:41 更新者:冯子文
	 * 更新时间: 2018年1月21日 下午10:46:41
	 */
	public static String getSuffix(String name) {
		return name.split("\\.")[1];
	}

	/**
	 * 
	 * 方法名:containIgnoreCase 功能描述:包含字符串，忽略大小写(,分割) 创建者:冯子文 创建时间: 2018年1月21日
	 * 下午10:59:22 更新者:冯子文 更新时间: 2018年1月21日 下午10:59:22
	 */
	public static Boolean containIgnoreCase(String str, String containStr) {
		if (isNull(str) || isNull(containStr))
			return false;
		String[] containArr = containStr.toLowerCase().split(",");
		for (int i = 0; i < containArr.length; i++)
			if (str.toLowerCase().contains(containArr[i]))
				return true;
		return false;
	}

	/**
	 * 
	 * 方法名:contain 功能描述:包含字符串(,分割) 创建者:冯子文 创建时间: 2018年1月21日 下午10:59:04 更新者:冯子文
	 * 更新时间: 2018年1月21日 下午10:59:04
	 */
	public static Boolean contain(String str, String containStr) {
		if (isNull(str) || isNull(containStr))
			return false;
		String[] containArr = containStr.split(",");
		for (int i = 0; i < containArr.length; i++)
			if (str.contains(containArr[i]))
				return true;
		return false;
	}

	/**
	 * 
	 * 方法名:toBytes 功能描述:16进制转byte 创建者:冯子文 创建时间: 2018年1月23日 下午9:52:26 更新者:冯子文
	 * 更新时间: 2018年1月23日 下午9:52:26
	 */
	public static byte[] toBytes(String hexStr) {
		if (isNull(hexStr))
			return null;
		hexStr = hexStr.toUpperCase();
		int length = hexStr.length() / 2;
		char[] hexChars = hexStr.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (toByte(hexChars[pos]) << 4 | toByte(hexChars[pos + 1]));
		}
		return d;
	}
	
	/**
	 * 
	     * 方法名:toByte
	     * 功能描述:char转byte	 
	     * 创建者:冯子文
	     * 创建时间: 2018年1月23日 下午9:53:56 
		 * 更新者:冯子文  
		 * 更新时间: 2018年1月23日 下午9:53:56
	 */
	public static byte toByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
	
	 //求两个字符串数组的并集，利用set的元素唯一性     
    public static String[] union(String[] arr1, String[] arr2) {
    	Set<String> result = new HashSet<String>();     
        for (String tmp : arr1)     
        	result.add(tmp);      
        for (String tmp : arr2)
        	result.add(tmp);         
        String[] resultStr = new String[result.size()];
        return result.toArray(resultStr);
    }     
    
    //求两个数组的交集     
    public static String[] intersect(String[] arr1, String[] arr2) {     
        Map<String, Boolean> map = new HashMap<String, Boolean>();     
        LinkedList<String> list = new LinkedList<String>();     
        for (String str : arr1)    
            if (!map.containsKey(str))    
                map.put(str, Boolean.FALSE);      
        for (String str : arr2)
            if (map.containsKey(str))      
                map.put(str, Boolean.TRUE);             
        for (Entry<String, Boolean> e : map.entrySet())     
            if (e.getValue().equals(Boolean.TRUE))     
                list.add(e.getKey());            
        String[] result = new String[list.size()];     
        return list.toArray(result);     
    }     
    
    //求两个数组的差集     
    public static String[] minus(String[] arr1, String[] arr2) {     
        LinkedList<String> list = new LinkedList<String>();     
        LinkedList<String> history = new LinkedList<String>();     
        String[] longerArr = arr1;     
        String[] shorterArr = arr2;     
        //找出较长的数组来减较短的数组     
        if (arr1.length > arr2.length) {     
            longerArr = arr2;     
            shorterArr = arr1;     
        }     
        for (String str : longerArr)      
            if (!list.contains(str))   
                list.add(str);          
        for (String tmp : shorterArr)
            if (list.contains(tmp)) {     
                history.add(tmp);     
                list.remove(tmp);     
            } else {     
                if (!history.contains(tmp))   
                    list.add(tmp);     
            }       
        String[] result = new String[list.size()];     
        return list.toArray(result);     
    }  
	
	
	
	
	
	
	
	
	
	

	public static void main(String[] args) {
		// System.out.println(toStrip(toCamel("admin_test")));
		String ss = "admin.text";
		// Pattern p = Pattern.compile("\\..+");
		// Matcher m = p.matcher(ss);
		//
		// while (m.find()) {
		// System.out.println(m.group(0));
		// }
		System.out.println(getSuffix(ss));
		

	}

}
