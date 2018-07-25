package com.qim.loan.entity.distribute;  
  
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.common.PrimaryKeyUtil;

  
public class DistributeUser implements Serializable,Cloneable{  

	private static final long serialVersionUID = 1L;
	//id      
    private String id;  
	//创建日期      
    private Integer createDate;  
	//创建时间      
    private Integer createTime;  
	//分发用户名      
    private String distributeUserName;  
	//密码      
    private String distributeUserPassword;  
	//真实姓名      
    private String realName;  
	//性别      
    private Integer gender;  
	//电话号码      
    private String distributeUserTelphone;  
	//金币      
    private Integer distributeUserGoldcoinNumber; 
    //上线状态
    private Integer isOnline;
    
    
    public  DistributeUser(){     
    }
     
    public DistributeUser setId(String id){
    	this.id=id.trim();
    	return this;
    }
 
    public DistributeUser autoSetId(){	
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
          
    public DistributeUser setCreateDate(Integer createDate){
    	this.createDate=createDate;
    	return this;
    }
 
    
    public DistributeUser autoSetCreateDate(){	
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
          
    public DistributeUser setCreateTime(Integer createTime){
    	this.createTime=createTime;
    	return this;
    }
 
    
    
    public DistributeUser autoSetCreateTime(){
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
          
    public DistributeUser setDistributeUserName(String distributeUserName){
    	this.distributeUserName=distributeUserName.trim();
    	return this;
    }
 
    
    
      
      
    public String getDistributeUserName(){
        return this.distributeUserName;  
    }
    
    public static Map<String,List<String>> getDistributeUserNameCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("distributeUserName",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getDistributeUserNameStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("distribute_user_name",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public DistributeUser setDistributeUserPassword(String distributeUserPassword){
    	this.distributeUserPassword=distributeUserPassword.trim();
    	return this;
    }
 
    
    
      
      
    public String getDistributeUserPassword(){
        return this.distributeUserPassword;  
    }
    
    public static Map<String,List<String>> getDistributeUserPasswordCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("distributeUserPassword",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getDistributeUserPasswordStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("distribute_user_password",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public DistributeUser setRealName(String realName){
    	this.realName=realName.trim();
    	return this;
    }
 
    
    
      
      
    public String getRealName(){
        return this.realName;  
    }
    
    public static Map<String,List<String>> getRealNameCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("realName",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getRealNameStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("real_name",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public DistributeUser setGender(Integer gender){
    	this.gender=gender;
    	return this;
    }
 
    
    
      
      
    public Integer getGender(){
        return this.gender;  
    }
    
    public static Map<String,List<Integer>> getGenderCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("gender",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getGenderStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("gender",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public DistributeUser setDistributeUserTelphone(String distributeUserTelphone){
    	this.distributeUserTelphone=distributeUserTelphone.trim();
    	return this;
    }
 
    
    
      
      
    public String getDistributeUserTelphone(){
        return this.distributeUserTelphone;  
    }
    
    public static Map<String,List<String>> getDistributeUserTelphoneCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("distributeUserTelphone",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getDistributeUserTelphoneStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("distribute_user_telphone",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public DistributeUser setDistributeUserGoldcoinNumber(Integer distributeUserGoldcoinNumber){
    	this.distributeUserGoldcoinNumber=distributeUserGoldcoinNumber;
    	return this;
    }
    
      
    public Integer getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}

	public Integer getDistributeUserGoldcoinNumber(){
        return this.distributeUserGoldcoinNumber;  
    }
    
    public static Map<String,List<Integer>> getDistributeUserGoldcoinNumberCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("distributeUserGoldcoinNumber",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getDistributeUserGoldcoinNumberStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("distribute_user_goldcoin_number",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    @Override  
    public Object clone() {  
    	DistributeUser distributeUser = null;
		try {
			distributeUser = (DistributeUser)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}   
        return distributeUser;  
    }
} 