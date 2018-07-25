package com.qim.loan.entity.channel;  
  
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.common.PrimaryKeyUtil;

  
public class Channel implements Serializable,Cloneable{  

	private static final long serialVersionUID = 1L;
	//ID      
    private String id;  
	//渠道名      
    private String channelName;  
	//渠道缩写名      
    private String channelAbbreviate;  
	//上下线状态      
    private Integer isOnline;  
	//描述      
    private String description;  
	//创建日期      
    private Integer createDate;  
	//创建时间      
    private Integer createTime;  
    public  Channel(){     
    }
     
    public Channel setId(String id){
    	this.id=id.trim();
    	return this;
    }
 
    public Channel autoSetId(){	
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
          
    public Channel setChannelName(String channelName){
    	this.channelName=channelName.trim();
    	return this;
    }
 
    
    
      
      
    public String getChannelName(){
        return this.channelName;  
    }
    
    public static Map<String,List<String>> getChannelNameCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("channelName",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getChannelNameStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("channel_name",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public Channel setChannelAbbreviate(String channelAbbreviate){
    	this.channelAbbreviate=channelAbbreviate.trim();
    	return this;
    }
 
    
    
      
      
    public String getChannelAbbreviate(){
        return this.channelAbbreviate;  
    }
    
    public static Map<String,List<String>> getChannelAbbreviateCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("channelAbbreviate",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getChannelAbbreviateStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("channel_abbreviate",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public Channel setIsOnline(Integer isOnline){
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
          
    public Channel setDescription(String description){
    	this.description=description.trim();
    	return this;
    }
 
    
    
      
      
    public String getDescription(){
        return this.description;  
    }
    
    public static Map<String,List<String>> getDescriptionCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("description",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getDescriptionStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("description",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public Channel setCreateDate(Integer createDate){
    	this.createDate=createDate;
    	return this;
    }
 
    
    public Channel autoSetCreateDate(){	
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
          
    public Channel setCreateTime(Integer createTime){
    	this.createTime=createTime;
    	return this;
    }
 
    
    
    public Channel autoSetCreateTime(){
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
    	Channel channel = null;
		try {
			channel = (Channel)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}   
        return channel;  
    }
} 