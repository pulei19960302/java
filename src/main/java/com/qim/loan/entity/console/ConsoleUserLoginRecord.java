package com.qim.loan.entity.console;  
  
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.common.PrimaryKeyUtil;

  
public class ConsoleUserLoginRecord implements Serializable,Cloneable{  

	private static final long serialVersionUID = 1L;
	//ID      
    private String id;  
	//总台用户ID      
    private String consoleUserId;  
	//IP地址      
    private String ipAddress;  
	//操作系统      
    private String osVersion;  
	//浏览器版本      
    private String browseVersion;  
	//创建日期      
    private Integer createDate;  
	//创建时间      
    private Integer createTime;  
    public  ConsoleUserLoginRecord(){     
    }
     
    public ConsoleUserLoginRecord setId(String id){
    	this.id=id.trim();
    	return this;
    }
 
    public ConsoleUserLoginRecord autoSetId(){	
    	this.id=PrimaryKeyUtil.getPrimaryId32();
     	return this;
    }   
    
    
      
      
    public String getId(){
        return this.id;  
    }
    
    public static Map<String,List<String>> getIdCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("id",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getIdStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("id",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ConsoleUserLoginRecord setConsoleUserId(String consoleUserId){
    	this.consoleUserId=consoleUserId.trim();
    	return this;
    }
 
    
    
      
      
    public String getConsoleUserId(){
        return this.consoleUserId;  
    }
    
    public static Map<String,List<String>> getConsoleUserIdCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("consoleUserId",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getConsoleUserIdStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("console_user_id",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ConsoleUserLoginRecord setIpAddress(String ipAddress){
    	this.ipAddress=ipAddress.trim();
    	return this;
    }
 
    
    
      
      
    public String getIpAddress(){
        return this.ipAddress;  
    }
    
    public static Map<String,List<String>> getIpAddressCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("ipAddress",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getIpAddressStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("ip_address",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ConsoleUserLoginRecord setOsVersion(String osVersion){
    	this.osVersion=osVersion.trim();
    	return this;
    }
 
    
    
      
      
    public String getOsVersion(){
        return this.osVersion;  
    }
    
    public static Map<String,List<String>> getOsVersionCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("osVersion",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getOsVersionStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("os_version",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ConsoleUserLoginRecord setBrowseVersion(String browseVersion){
    	this.browseVersion=browseVersion.trim();
    	return this;
    }
 
    
    
      
      
    public String getBrowseVersion(){
        return this.browseVersion;  
    }
    
    public static Map<String,List<String>> getBrowseVersionCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("browseVersion",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getBrowseVersionStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("browse_version",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ConsoleUserLoginRecord setCreateDate(Integer createDate){
    	this.createDate=createDate;
    	return this;
    }
 
    
    public ConsoleUserLoginRecord autoSetCreateDate(){	
    	this.createDate=DateUtil.formatCurrentDate();
     	return this;
    }   
    
      
      
    public Integer getCreateDate(){
        return this.createDate;  
    }
    
    public static Map<String,List<Integer>> getCreateDateCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("createDate",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getCreateDateStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("create_date",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ConsoleUserLoginRecord setCreateTime(Integer createTime){
    	this.createTime=createTime;
    	return this;
    }
 
    
    
    public ConsoleUserLoginRecord autoSetCreateTime(){
    	this.createTime=DateUtil.formatCurrentTime();	
    	return this;
    }
      
      
    public Integer getCreateTime(){
        return this.createTime;  
    }
    
    public static Map<String,List<Integer>> getCreateTimeCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("createTime",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getCreateTimeStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("create_time",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    @Override  
    public Object clone() {  
    	ConsoleUserLoginRecord consoleUserLoginRecord = null;
		try {
			consoleUserLoginRecord = (ConsoleUserLoginRecord)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}   
        return consoleUserLoginRecord;  
    }
} 