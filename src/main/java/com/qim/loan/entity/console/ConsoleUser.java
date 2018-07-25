package com.qim.loan.entity.console;  
  
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.common.PrimaryKeyUtil;

  
public class ConsoleUser implements Serializable,Cloneable{  

	private static final long serialVersionUID = 1L;
	//ID      
    private String id;  
	//真实名字      
    private String consoleRealName;  
	//用户名      
    private String consoleUserName;  
	//密码      
    private String consoleUserPassword;  
	//上线状态      
    private Integer isOnline;  
	//创建日期      
    private Integer createDate;  
	//创建时间      
    private Integer createTime;  
    public  ConsoleUser(){     
    }
     
    public ConsoleUser setId(String id){
    	this.id=id.trim();
    	return this;
    }
 
    public ConsoleUser autoSetId(){	
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
          
    public ConsoleUser setConsoleRealName(String consoleRealName){
    	this.consoleRealName=consoleRealName.trim();
    	return this;
    }
 
    
    
      
      
    public String getConsoleRealName(){
        return this.consoleRealName;  
    }
    
    public static Map<String,List<String>> getConsoleRealNameCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("consoleRealName",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getConsoleRealNameStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("console_real_name",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ConsoleUser setConsoleUserName(String consoleUserName){
    	this.consoleUserName=consoleUserName.trim();
    	return this;
    }
 
    
    
      
      
    public String getConsoleUserName(){
        return this.consoleUserName;  
    }
    
    public static Map<String,List<String>> getConsoleUserNameCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("consoleUserName",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getConsoleUserNameStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("console_user_name",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ConsoleUser setConsoleUserPassword(String consoleUserPassword){
    	this.consoleUserPassword=consoleUserPassword.trim();
    	return this;
    }
 
    
    
      
      
    public String getConsoleUserPassword(){
        return this.consoleUserPassword;  
    }
    
    public static Map<String,List<String>> getConsoleUserPasswordCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("consoleUserPassword",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getConsoleUserPasswordStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("console_user_password",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ConsoleUser setIsOnline(Integer isOnline){
    	this.isOnline=isOnline;
    	return this;
    }
 
    
    
      
      
    public Integer getIsOnline(){
        return this.isOnline;  
    }
    
    public static Map<String,List<Integer>> getIsOnlineCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("isOnline",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getIsOnlineStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("is_online",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ConsoleUser setCreateDate(Integer createDate){
    	this.createDate=createDate;
    	return this;
    }
 
    
    public ConsoleUser autoSetCreateDate(){	
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
          
    public ConsoleUser setCreateTime(Integer createTime){
    	this.createTime=createTime;
    	return this;
    }
 
    
    
    public ConsoleUser autoSetCreateTime(){
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
    	ConsoleUser consoleUser = null;
		try {
			consoleUser = (ConsoleUser)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}   
        return consoleUser;  
    }
} 