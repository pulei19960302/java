package com.qim.loan.entity.employee;  
  
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.common.PrimaryKeyUtil;

  
public class EmployeeDepartment implements Serializable,Cloneable{  

	private static final long serialVersionUID = 1L;
	//ID      
    private String id;  
	//部门名称      
    private String employeeDepartmentName;  
	//是否跟踪服务      
    private Integer isTrack;  
	//上线状态      
    private Integer isOnline;  
	//创建日期      
    private Integer createDate;  
	//创建时间      
    private Integer createTime;  
    public  EmployeeDepartment(){     
    }
     
    public EmployeeDepartment setId(String id){
    	this.id=id.trim();
    	return this;
    }
 
    public EmployeeDepartment autoSetId(){	
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
          
    public EmployeeDepartment setEmployeeDepartmentName(String employeeDepartmentName){
    	this.employeeDepartmentName=employeeDepartmentName.trim();
    	return this;
    }
 
    
    
      
      
    public String getEmployeeDepartmentName(){
        return this.employeeDepartmentName;  
    }
    
    public static Map<String,List<String>> getEmployeeDepartmentNameCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("employeeDepartmentName",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getEmployeeDepartmentNameStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("employee_department_name",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public EmployeeDepartment setIsTrack(Integer isTrack){
    	this.isTrack=isTrack;
    	return this;
    }
 
    
    
      
      
    public Integer getIsTrack(){
        return this.isTrack;  
    }
    
    public static Map<String,List<Integer>> getIsTrackCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("isTrack",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getIsTrackStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("is_track",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public EmployeeDepartment setIsOnline(Integer isOnline){
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
          
    public EmployeeDepartment setCreateDate(Integer createDate){
    	this.createDate=createDate;
    	return this;
    }
 
    
    public EmployeeDepartment autoSetCreateDate(){	
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
          
    public EmployeeDepartment setCreateTime(Integer createTime){
    	this.createTime=createTime;
    	return this;
    }
 
    
    
    public EmployeeDepartment autoSetCreateTime(){
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
    	EmployeeDepartment employeeDepartment = null;
		try {
			employeeDepartment = (EmployeeDepartment)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}   
        return employeeDepartment;  
    }
} 