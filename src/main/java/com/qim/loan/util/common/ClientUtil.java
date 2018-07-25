package com.qim.loan.util.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
/**
 * 
     * 类名:ClientUtil
     * 描述:	客户机工具 
     * 创建者:冯子文
     * 创建时间: 2017年8月5日 上午9:18:02 
	 * 更新者:冯子文   
	 * 更新时间: 2017年8月5日 上午9:18:02
 */
public class ClientUtil {
	
	/**
	 * 
	     * 方法名:setSystemInformation
	     * 功能描述:设置系统信息	 
	     * 创建者:冯子文
	     * 创建时间: 2018年1月24日 下午9:55:27 
		 * 更新者:冯子文  
		 * 更新时间: 2018年1月24日 下午9:55:27
	 */
	public static void setSystemInformation(HttpServletRequest request,Map<String,String[]> map){
    	if(map==null)
    		return;
		String ipAddress=ClientUtil.getIpAddress(request);
    	if(StringUtil.isNotNull(ipAddress))
    		map.put("ipAddress", new String[]{ipAddress});
    	String osVersion=ClientUtil.getOs(request);
    	if(StringUtil.isNotNull(osVersion))
    		map.put("osVersion", new String[]{osVersion});
    	String browserVersion=ClientUtil.getBrowser(request);
    	if(StringUtil.isNotNull(browserVersion))
    		map.put("browseVersion", new String[]{browserVersion});
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T> void  clear(T t){
		if(t instanceof Map){
			Map<String,Object> map=(Map<String,Object>)t;
			if(map.keySet().contains("ipAddress"))
				map.remove("ipAddress");
			if(map.keySet().contains("osVersion"))
				map.remove("osVersion");		
			if(map.keySet().contains("browseVersion"))
				map.remove("browseVersion");
		}else{
			Object ipAddress=ReflectUtil.getMethodValue(t, "ipAddress");
			if(ipAddress!=null)
				ReflectUtil.setMethodValue(t, "ipAddress", "");
			Object osVersion=ReflectUtil.getMethodValue(t, "osVersion");
			if(osVersion!=null)
				ReflectUtil.setMethodValue(t, "osVersion", "");		
			Object browserVersion=ReflectUtil.getMethodValue(t, "browseVersion");
			if(browserVersion!=null)
				ReflectUtil.setMethodValue(t, "browseVersion", "");	
		}
	}
    /**
     * 
         * 方法名:getIpAddress
         * 功能描述:获取IP地址	 
         * 创建者:冯子文
         * 创建时间: Dec 8, 2017 9:57:30 AM 
    	 * 更新者:冯子文  
    	 * 更新时间: Dec 8, 2017 9:57:30 AM
     */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (StringUtil.isNull(ip) || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("Proxy-Client-IP");
		if (StringUtil.isNull(ip) || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("WL-Proxy-Client-IP");
		if (StringUtil.isNull(ip) || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("HTTP_CLIENT_IP");
		if (StringUtil.isNull(ip) || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		if (StringUtil.isNull(ip) || "unknown".equalsIgnoreCase(ip))
			ip = request.getRemoteAddr();
		return ip;
	}
	
	/**
	 * 
	     * 方法名:getOs
	     * 功能描述:获取系统类型	 
	     * 创建者:冯子文
	     * 创建时间: Dec 8, 2017 10:01:13 AM 
		 * 更新者:冯子文  
		 * 更新时间: Dec 8, 2017 10:01:13 AM
	 */
	public static String getOs(HttpServletRequest request){
		String userAgent = request.getHeader("User-Agent").toLowerCase();
		if (userAgent.contains("windows")) {
			return "Windows";
		} else if (userAgent.contains("mac")) {
			return "Mac";
		} else if (userAgent.contains("x11")) {
			return "Unix";
		} else if (userAgent.contains("android")) {
			return "Android";
		} else if (userAgent.contains("iphone")) {
			return "IPhone";
		}
		return "UnKnown, More-Info: " + userAgent;	
	}
	
	/**
	 * 
	     * 方法名:getBrowser
	     * 功能描述:获取浏览器	 
	     * 创建者:冯子文
	     * 创建时间: Dec 8, 2017 10:08:42 AM 
		 * 更新者:冯子文  
		 * 更新时间: Dec 8, 2017 10:08:42 AM
	 */
	public static String getBrowser(HttpServletRequest request){
		String userAgent = request.getHeader("User-Agent").toLowerCase();
		if (userAgent.contains("edge")) {
			return (userAgent.substring(userAgent.indexOf("edge")).split(" ")[0]).replace("/", "-");
		} else if (userAgent.contains("msie")) {
			String substring = userAgent.substring(userAgent.indexOf("msie")).split(";")[0];
			return substring.split(" ")[0].replace("msie", "ie") + "-"+ substring.split(" ")[1];
		} else if (userAgent.contains("safari") && userAgent.contains("version")) {
			return (userAgent.substring(userAgent.indexOf("safari")).split(" ")[0]).split("/")[0]+ "-"+ (userAgent.substring(userAgent.indexOf("version")).split(" ")[0]).split("/")[1];
		} else if (userAgent.contains("opr") || userAgent.contains("opera")) {
			if (userAgent.contains("opera")) {
				return (userAgent.substring(userAgent.indexOf("opera")).split(" ")[0]).split("/")[0]+ "-"+ (userAgent.substring(userAgent.indexOf("version")).split(" ")[0]).split("/")[1];
			}else if (userAgent.contains("opr")) {
				return ((userAgent.substring(userAgent.indexOf("opr")).split(" ")[0]).replace("/", "-")).replace("opr","opera");
			}
		} else if (userAgent.contains("chrome")) {
			return (userAgent.substring(userAgent.indexOf("chrome")).split(" ")[0]).replace("/", "-");
		} else if ((userAgent.indexOf("mozilla/7.0") > -1)|| (userAgent.indexOf("netscape6") != -1)|| (userAgent.indexOf("mozilla/4.7") != -1)|| (userAgent.indexOf("mozilla/4.78") != -1)|| (userAgent.indexOf("mozilla/4.08") != -1)|| (userAgent.indexOf("mozilla/3") != -1)) {
			return "netscape-?";
		} else if (userAgent.contains("firefox")) {
			return (userAgent.substring(userAgent.indexOf("firefox")).split(" ")[0]).replace("/", "-");
		} else if (userAgent.contains("rv")) {
			String IEVersion = (userAgent.substring(userAgent.indexOf("rv")).split(" ")[0]).replace("rv:", "-");
			return "ie" + IEVersion.substring(0, IEVersion.length() - 1);
		}
		return "UnKnown, More-Info: " + userAgent;
	}
	
	public static void main(String[] args) {

	}

}
