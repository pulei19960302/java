package com.qim.loan.entity.distribute;  
  
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.common.PrimaryKeyUtil;

  
public class DistributeCurrencyRecord implements Serializable,Cloneable{  

	private static final long serialVersionUID = 1L;
	//id      
    private String id;  
	//消费记录名称      
    private String distributeCurrencyRecordName;  
	//消费金币      
    private Integer currencyGoldcoinNumber;  
	//消费时间      
    private Integer createTime;  
	//消费日期      
    private Integer createDate;  
	//用户id      
    private String distributeUserId;  
	//剩余的金币      
    private Integer currencySurplusGoldcoin;  
	//消费前金币      
    private Integer currencyBeforeGoldcoin;  
	//购买数量      
    private Integer purchaseNumber;  
    public  DistributeCurrencyRecord(){     
    }
     
    public DistributeCurrencyRecord setId(String id){
    	this.id=id.trim();
    	return this;
    }
 
    public DistributeCurrencyRecord autoSetId(){	
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
          
    public DistributeCurrencyRecord setDistributeCurrencyRecordName(String distributeCurrencyRecordName){
    	this.distributeCurrencyRecordName=distributeCurrencyRecordName.trim();
    	return this;
    }
 
    
    
      
      
    public String getDistributeCurrencyRecordName(){
        return this.distributeCurrencyRecordName;  
    }
    
    public static Map<String,List<String>> getDistributeCurrencyRecordNameCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("distributeCurrencyRecordName",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getDistributeCurrencyRecordNameStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("distribute_currency_record_name",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public DistributeCurrencyRecord setCurrencyGoldcoinNumber(Integer currencyGoldcoinNumber){
    	this.currencyGoldcoinNumber=currencyGoldcoinNumber;
    	return this;
    }
 
    
    
      
      
    public Integer getCurrencyGoldcoinNumber(){
        return this.currencyGoldcoinNumber;  
    }
    
    public static Map<String,List<Integer>> getCurrencyGoldcoinNumberCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("currencyGoldcoinNumber",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getCurrencyGoldcoinNumberStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("currency_goldcoin_number",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public DistributeCurrencyRecord setCreateTime(Integer createTime){
    	this.createTime=createTime;
    	return this;
    }
 
    
    
    public DistributeCurrencyRecord autoSetCreateTime(){
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
          
    public DistributeCurrencyRecord setCreateDate(Integer createDate){
    	this.createDate=createDate;
    	return this;
    }
 
    
    public DistributeCurrencyRecord autoSetCreateDate(){	
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
          
    public DistributeCurrencyRecord setDistributeUserId(String distributeUserId){
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
          
    public DistributeCurrencyRecord setCurrencySurplusGoldcoin(Integer currencySurplusGoldcoin){
    	this.currencySurplusGoldcoin=currencySurplusGoldcoin;
    	return this;
    }
 
    
    
      
      
    public Integer getCurrencySurplusGoldcoin(){
        return this.currencySurplusGoldcoin;  
    }
    
    public static Map<String,List<Integer>> getCurrencySurplusGoldcoinCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("currencySurplusGoldcoin",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getCurrencySurplusGoldcoinStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("currency_surplus_goldcoin",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public DistributeCurrencyRecord setCurrencyBeforeGoldcoin(Integer currencyBeforeGoldcoin){
    	this.currencyBeforeGoldcoin=currencyBeforeGoldcoin;
    	return this;
    }
 
    
    
      
      
    public Integer getCurrencyBeforeGoldcoin(){
        return this.currencyBeforeGoldcoin;  
    }
    
    public static Map<String,List<Integer>> getCurrencyBeforeGoldcoinCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("currencyBeforeGoldcoin",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getCurrencyBeforeGoldcoinStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("currency_before_goldcoin",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public DistributeCurrencyRecord setPurchaseNumber(Integer purchaseNumber){
    	this.purchaseNumber=purchaseNumber;
    	return this;
    }
 
    
    
      
      
    public Integer getPurchaseNumber(){
        return this.purchaseNumber;  
    }
    
    public static Map<String,List<Integer>> getPurchaseNumberCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("purchaseNumber",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getPurchaseNumberStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("purchase_number",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    @Override  
    public Object clone() {  
    	DistributeCurrencyRecord distributeCurrencyRecord = null;
		try {
			distributeCurrencyRecord = (DistributeCurrencyRecord)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}   
        return distributeCurrencyRecord;  
    }
} 