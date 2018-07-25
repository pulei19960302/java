package com.qim.loan.entity.distribute;  
  
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.common.PrimaryKeyUtil;

  
public class DistributeRecords implements Serializable,Cloneable{  

	private static final long serialVersionUID = 1L;
	//id      
    private String id;  
	//客户数据id      
    private String clientDataId;  
	//电话      
    private String telphoneNumber;  
	//姓名      
    private String realName;  
	//购买时间      
    private Integer createTime;  
	//购买日期      
    private Integer createDate;  
	//点数消费记录id      
    private String distributeRechargeRecordId;  
	//用户id      
    private String distributeUserId;  
    public  DistributeRecords(){     
    }
     
    public DistributeRecords setId(String id){
    	this.id=id.trim();
    	return this;
    }
 
    public DistributeRecords autoSetId(){	
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
          
    public DistributeRecords setClientDataId(String clientDataId){
    	this.clientDataId=clientDataId.trim();
    	return this;
    }
 
    
    
      
      
    public String getClientDataId(){
        return this.clientDataId;  
    }
    
    public static Map<String,List<String>> getClientDataIdCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("clientDataId",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getClientDataIdStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("client_data_id",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public DistributeRecords setTelphoneNumber(String telphoneNumber){
    	this.telphoneNumber=telphoneNumber.trim();
    	return this;
    }
 
    
    
      
      
    public String getTelphoneNumber(){
        return this.telphoneNumber;  
    }
    
    public static Map<String,List<String>> getTelphoneNumberCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("telphoneNumber",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getTelphoneNumberStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("telphone_number",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public DistributeRecords setRealName(String realName){
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
          
    public DistributeRecords setCreateTime(Integer createTime){
    	this.createTime=createTime;
    	return this;
    }
 
    
    
    public DistributeRecords autoSetCreateTime(){
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
          
    public DistributeRecords setCreateDate(Integer createDate){
    	this.createDate=createDate;
    	return this;
    }
 
    
    public DistributeRecords autoSetCreateDate(){	
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
          
    public DistributeRecords setDistributeRechargeRecordId(String distributeRechargeRecordId){
    	this.distributeRechargeRecordId=distributeRechargeRecordId.trim();
    	return this;
    }
 
    
    
      
      
    public String getDistributeRechargeRecordId(){
        return this.distributeRechargeRecordId;  
    }
    
    public static Map<String,List<String>> getDistributeRechargeRecordIdCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("distributeRechargeRecordId",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getDistributeRechargeRecordIdStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("distribute_recharge_record_id",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public DistributeRecords setDistributeUserId(String distributeUserId){
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
          
    @Override  
    public Object clone() {  
    	DistributeRecords distributeRecords = null;
		try {
			distributeRecords = (DistributeRecords)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}   
        return distributeRecords;  
    }
} 