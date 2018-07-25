package com.qim.loan.util.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * (OK)
     * 类名:RegexUtil
     * 描述:正则表达式	 
     * 创建者:冯子文
     * 创建时间: 2018年1月21日 下午9:16:32 
	 * 更新者:冯子文   
	 * 更新时间: 2018年1月21日 下午9:16:32
 */
public class RegexUtil {
	/**
	 * 
	     * 方法名:
	     * 功能描述:	 
	     * 创建者:冯子文
	     * 创建时间: 2018年1月21日 下午9:35:20 
		 * 更新者:冯子文  
		 * 更新时间: 2018年1月21日 下午9:35:20
	 */
	public static String toCamel(final String str){
		String tmpString=str;
		Pattern pattern = Pattern.compile("_[a-z]");
		Matcher mather=pattern.matcher(tmpString);
		String temp="",replaceTmp="";
		while(mather.find()){
			temp=mather.group(0);
			if(StringUtil.isNotNull(temp)){
				replaceTmp=String.valueOf((char)(temp.charAt(1)-32));
				tmpString=tmpString.replace(temp, replaceTmp);
			}
		}
		return tmpString;
	}
	/**
	 * 
	     * 方法名:toStrip
	     * 功能描述:转化为_s格式	 
	     * 创建者:冯子文
	     * 创建时间: 2018年1月21日 下午9:45:58 
		 * 更新者:冯子文  
		 * 更新时间: 2018年1月21日 下午9:45:58
	 */
	public static String toStrip(final String str){
		String tmpString=str;
		Pattern pattern = Pattern.compile("[A-Z]");
		Matcher mather=pattern.matcher(tmpString);
		String temp="",replaceTmp="";
		while(mather.find()){
			temp=mather.group(0);
			if(StringUtil.isNotNull(temp)){
				replaceTmp=String.valueOf("_"+(char)(temp.charAt(0)+32));
				tmpString=tmpString.replace(temp, replaceTmp);
			}
		}
		return tmpString;	
	}
	/**
	 * 
	     * 方法名:find
	     * 功能描述:字符串查询(字符串,正则表达式,组位置)	 
	     * 创建者:冯子文
	     * 创建时间: 2018年1月21日 下午10:03:57 
		 * 更新者:冯子文  
		 * 更新时间: 2018年1月21日 下午10:03:57
	 */
	public static String find(final String str,final String regex,final Integer group){
		Integer groupIndex=group;
		if(group==null || !regex.contains("(") || regex.contains(")"))
			groupIndex=0;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher= pattern.matcher(str);
		StringBuilder sBuilder=new StringBuilder();
		while(matcher.find())
			sBuilder.append(matcher.group(groupIndex)+",");
		if(StringUtil.isNotNull(sBuilder.toString()))
			sBuilder.deleteCharAt(sBuilder.length()-1);
		return sBuilder.toString();
	}
	
	/**
	 * 
	     * 方法名:replaceIgnoreCase
	     * 功能描述:忽略大小写替换	 
	     * 创建者:冯子文
	     * 创建时间: 2018年1月21日 下午10:06:06 
		 * 更新者:冯子文  
		 * 更新时间: 2018年1月21日 下午10:06:06
	 */
	public static String replaceIgnoreCase(String str,String pattern,String replaceStr){
		String patterns = "(?i)"+pattern;
		return str.replaceAll(patterns,replaceStr);
	}
	
	/**
	 * 
	     * 方法名:match
	     * 功能描述:匹配	 
	     * 创建者:冯子文
	     * 创建时间: 2018年1月23日 下午8:55:25 
		 * 更新者:冯子文  
		 * 更新时间: 2018年1月23日 下午8:55:25
	 */
	public static boolean match(String regex, String str) {
		boolean flag = false;
		Pattern p = Pattern.compile(regex);
		if (str != null) {
			Matcher match = p.matcher(str);
			flag = match.matches();
		}
		return flag;
	}
	
	
	
	public static void main(String[] args) {

	}

}
