package com.qim.loan.entity.channel;  
  
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.common.PrimaryKeyUtil;

  
public class ChannelUser implements Serializable,Cloneable{  

	private static final long serialVersionUID = 1L;
	//ID      
    private String id;  
	//渠道ID      
    private String channelId;  
	//商务用户名      
    private String channelUserName;  
	//商务姓名      
    private String channelRealName;  
	//渠道用户密码      
    private String channelUserPwd;  
	//联系人      
    private String contractPerson;  
	//联系电话      
    private String contractTelphone;  
	//上线状态      
    private Integer isOnline;  
	//总台用户ID      
    private String consoleUserId;  
	//创建日期      
    private Integer createDate;  
	//创建时间      
    private Integer createTime;  
    public  ChannelUser(){     
    }
     
    public ChannelUser setId(String id){
    	this.id=id.trim();
    	return this;
    }
 
    public ChannelUser autoSetId(){	
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
          
    public ChannelUser setChannelId(String channelId){
    	this.channelId=channelId.trim();
    	return this;
    }
 
    
    
      
      
    public String getChannelId(){
        return this.channelId;  
    }
    
    public static Map<String,List<String>> getChannelIdCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("channelId",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getChannelIdStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("channel_id",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ChannelUser setChannelUserName(String channelUserName){
    	this.channelUserName=channelUserName.trim();
    	return this;
    }
 
    
    
      
      
    public String getChannelUserName(){
        return this.channelUserName;  
    }
    
    public static Map<String,List<String>> getChannelUserNameCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("channelUserName",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getChannelUserNameStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("channel_user_name",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ChannelUser setChannelRealName(String channelRealName){
    	this.channelRealName=channelRealName.trim();
    	return this;
    }
 
    
    
      
      
    public String getChannelRealName(){
        return this.channelRealName;  
    }
    
    public static Map<String,List<String>> getChannelRealNameCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("channelRealName",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getChannelRealNameStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("channel_real_name",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ChannelUser setChannelUserPwd(String channelUserPwd){
    	this.channelUserPwd=channelUserPwd.trim();
    	return this;
    }
 
    
    
      
      
    public String getChannelUserPwd(){
        return this.channelUserPwd;  
    }
    
    public static Map<String,List<String>> getChannelUserPwdCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("channelUserPwd",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getChannelUserPwdStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("channel_user_pwd",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ChannelUser setContractPerson(String contractPerson){
    	this.contractPerson=contractPerson.trim();
    	return this;
    }
 
    
    
      
      
    public String getContractPerson(){
        return this.contractPerson;  
    }
    
    public static Map<String,List<String>> getContractPersonCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contractPerson",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getContractPersonStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contract_person",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ChannelUser setContractTelphone(String contractTelphone){
    	this.contractTelphone=contractTelphone.trim();
    	return this;
    }
 
    
    
      
      
    public String getContractTelphone(){
        return this.contractTelphone;  
    }
    
    public static Map<String,List<String>> getContractTelphoneCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contractTelphone",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getContractTelphoneStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contract_telphone",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ChannelUser setIsOnline(Integer isOnline){
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
          
    public ChannelUser setConsoleUserId(String consoleUserId){
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
          
    public ChannelUser setCreateDate(Integer createDate){
    	this.createDate=createDate;
    	return this;
    }
 
    
    public ChannelUser autoSetCreateDate(){	
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
          
    public ChannelUser setCreateTime(Integer createTime){
    	this.createTime=createTime;
    	return this;
    }
 
    
    
    public ChannelUser autoSetCreateTime(){
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
    	ChannelUser channelUser = null;
		try {
			channelUser = (ChannelUser)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}   
        return channelUser;  
    }
} 