package com.qim.loan.entity.employee;  
  
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.common.PrimaryKeyUtil;

  
public class EmployeeUserLoginRecord implements Serializable,Cloneable{  

	private static final long serialVersionUID = 1L;
	//ID      
    private String id;  
	//员工用户ID      
    private String employeeUserId;  
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
    public  EmployeeUserLoginRecord(){     
    }
     
    public EmployeeUserLoginRecord setId(String id){
    	this.id=id.trim();
    	return this;
    }
 
    public EmployeeUserLoginRecord autoSetId(){	
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
          
    public EmployeeUserLoginRecord setEmployeeUserId(String employeeUserId){
    	this.employeeUserId=employeeUserId.trim();
    	return this;
    }
 
    
    
      
      
    public String getEmployeeUserId(){
        return this.employeeUserId;  
    }
    
    public static Map<String,List<String>> getEmployeeUserIdCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("employeeUserId",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getEmployeeUserIdStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("employee_user_id",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public EmployeeUserLoginRecord setIpAddress(String ipAddress){
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
          
    public EmployeeUserLoginRecord setOsVersion(String osVersion){
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
          
    public EmployeeUserLoginRecord setBrowseVersion(String browseVersion){
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
          
    public EmployeeUserLoginRecord setCreateDate(Integer createDate){
    	this.createDate=createDate;
    	return this;
    }
 
    
    public EmployeeUserLoginRecord autoSetCreateDate(){	
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
          
    public EmployeeUserLoginRecord setCreateTime(Integer createTime){
    	this.createTime=createTime;
    	return this;
    }
 
    
    
    public EmployeeUserLoginRecord autoSetCreateTime(){
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
    	EmployeeUserLoginRecord employeeUserLoginRecord = null;
		try {
			employeeUserLoginRecord = (EmployeeUserLoginRecord)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}   
        return employeeUserLoginRecord;  
    }
} 