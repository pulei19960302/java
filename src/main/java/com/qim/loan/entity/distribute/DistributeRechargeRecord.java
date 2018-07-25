package com.qim.loan.entity.distribute;  
  
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.common.PrimaryKeyUtil;

  
public class DistributeRechargeRecord implements Serializable,Cloneable{  

	private static final long serialVersionUID = 1L;
	//id      
    private String id;  
	//金币数量      
    private Integer purchaseGoldcoinNumber;  
	//购买日期      
    private Integer createDate;  
	//购买时间      
    private Integer createTime;  
	//用户id      
    private String distributeUserId;  
	//充值前金币      
    private Integer rechargeGoldcoinBeforeNumber;  
	//充值后金币      
    private Integer rechargeGoldcoinAfterNumber;  
	//充值金币数量      
    private Integer rechargeGoldcoinNumber;  
    public  DistributeRechargeRecord(){     
    }
     
    public DistributeRechargeRecord setId(String id){
    	this.id=id.trim();
    	return this;
    }
 
    public DistributeRechargeRecord autoSetId(){	
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
          
    public DistributeRechargeRecord setPurchaseGoldcoinNumber(Integer purchaseGoldcoinNumber){
    	this.purchaseGoldcoinNumber=purchaseGoldcoinNumber;
    	return this;
    }
 
    
    
      
      
    public Integer getPurchaseGoldcoinNumber(){
        return this.purchaseGoldcoinNumber;  
    }
    
    public static Map<String,List<Integer>> getPurchaseGoldcoinNumberCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("purchaseGoldcoinNumber",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getPurchaseGoldcoinNumberStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("purchase_goldcoin_number",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public DistributeRechargeRecord setCreateDate(Integer createDate){
    	this.createDate=createDate;
    	return this;
    }
 
    
    public DistributeRechargeRecord autoSetCreateDate(){	
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
          
    public DistributeRechargeRecord setCreateTime(Integer createTime){
    	this.createTime=createTime;
    	return this;
    }
 
    
    
    public DistributeRechargeRecord autoSetCreateTime(){
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
          
    public DistributeRechargeRecord setDistributeUserId(String distributeUserId){
    	this.distributeUserId=distributeUserId;
    	return this;
    }
 
    
    
      
      
    public String getDistributeUserId(){
        return this.distributeUserId;  
    }
    
    public static Map<String,List<Integer>> getDistributeUserIdCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("distributeUserId",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getDistributeUserIdStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("distribute_user_id",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public DistributeRechargeRecord setRechargeGoldcoinBeforeNumber(Integer rechargeGoldcoinBeforeNumber){
    	this.rechargeGoldcoinBeforeNumber=rechargeGoldcoinBeforeNumber;
    	return this;
    }
 
    
    
      
      
    public Integer getRechargeGoldcoinBeforeNumber(){
        return this.rechargeGoldcoinBeforeNumber;  
    }
    
    public static Map<String,List<Integer>> getRechargeGoldcoinBeforeNumberCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("rechargeGoldcoinBeforeNumber",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getRechargeGoldcoinBeforeNumberStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("recharge_goldcoin_before_number",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public DistributeRechargeRecord setRechargeGoldcoinAfterNumber(Integer rechargeGoldcoinAfterNumber){
    	this.rechargeGoldcoinAfterNumber=rechargeGoldcoinAfterNumber;
    	return this;
    }
 
    
    
      
      
    public Integer getRechargeGoldcoinAfterNumber(){
        return this.rechargeGoldcoinAfterNumber;  
    }
    
    public static Map<String,List<Integer>> getRechargeGoldcoinAfterNumberCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("rechargeGoldcoinAfterNumber",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getRechargeGoldcoinAfterNumberStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("recharge_goldcoin_after_number",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public DistributeRechargeRecord setRechargeGoldcoinNumber(Integer rechargeGoldcoinNumber){
    	this.rechargeGoldcoinNumber=rechargeGoldcoinNumber;
    	return this;
    }
 
    
    
      
      
    public Integer getRechargeGoldcoinNumber(){
        return this.rechargeGoldcoinNumber;  
    }
    
    public static Map<String,List<Integer>> getRechargeGoldcoinNumberCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("rechargeGoldcoinNumber",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getRechargeGoldcoinNumberStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("recharge_goldcoin_number",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    @Override  
    public Object clone() {  
    	DistributeRechargeRecord distributeRechargeRecord = null;
		try {
			distributeRechargeRecord = (DistributeRechargeRecord)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}   
        return distributeRechargeRecord;  
    }
} 