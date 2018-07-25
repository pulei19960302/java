package com.qim.loan.entity.distribute;  
  
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.common.PrimaryKeyUtil;

  
public class DistributeUserLoginRecord implements Serializable,Cloneable{  

	private static final long serialVersionUID = 1L;
	//id      
    private String id;  
	//登录日期      
    private Integer createDate;  
	//登录时间      
    private Integer createTime;  
	//用户id      
    private String distributeUserId;  
	//ip地址      
    private String ipAddress;  
	//浏览器版本      
    private String browseVersion; 
	//操作系统      
    private String osVersion;  
    public  DistributeUserLoginRecord(){     
    }
     
    public DistributeUserLoginRecord setId(String id){
    	this.id=id.trim();
    	return this;
    }
 
    public DistributeUserLoginRecord autoSetId(){	
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
          
    public DistributeUserLoginRecord setCreateDate(Integer createDate){
    	this.createDate=createDate;
    	return this;
    }
 
    
    public DistributeUserLoginRecord autoSetCreateDate(){	
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
          
    public DistributeUserLoginRecord setCreateTime(Integer createTime){
    	this.createTime=createTime;
    	return this;
    }
 
    
    
    public DistributeUserLoginRecord autoSetCreateTime(){
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
          
    public DistributeUserLoginRecord setDistributeUserId(String distributeUserId){
    	this.distributeUserId=distributeUserId.trim();
    	return this;
    }
 
    
    
      
      
    public String getDistributeUserId(){
        return this.distributeUserId;  
    }
    
    public static Map<String,List<String>> getDistributeUserIdCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("distributeUserId",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getDistributeUserIdStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("distribute_user_id",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public DistributeUserLoginRecord setIpAddress(String ipAddress){
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
          
    public DistributeUserLoginRecord setBrowseVersion(String browseVersion){
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
	    	map.put("browser_version",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public DistributeUserLoginRecord setOsVersion(String osVersion){
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
          
    @Override  
    public Object clone() {  
    	DistributeUserLoginRecord distributeUserLoginRecord = null;
		try {
			distributeUserLoginRecord = (DistributeUserLoginRecord)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}   
        return distributeUserLoginRecord;  
    }
} 