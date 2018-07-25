package com.qim.loan.entity.employee;  
  
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.common.PrimaryKeyUtil;

  
public class EmployeeUser implements Serializable,Cloneable{  

	private static final long serialVersionUID = 1L;
	//ID      
    private String id;  
	//员工部门ID      
    private String employeeDepartmentId;  
	//员工编号      
    private String employeeNumber;  
	//员工电话      
    private String employeeTelphone;  
	//真实姓名      
    private String employeeRealName;  
	//性别      
    private Integer gender;  
	//用户名      
    private String employeeUserName;  
	//密码      
    private String employeeUserPassword;  
	//总台用户ID      
    private String consoleUserId;  
	//上线状态      
    private Integer isOnline;  
	//创建日期      
    private Integer createDate;  
	//创建时间      
    private Integer createTime;  
    public  EmployeeUser(){     
    }
     
    public EmployeeUser setId(String id){
    	this.id=id.trim();
    	return this;
    }
 
    public EmployeeUser autoSetId(){	
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
          
    public EmployeeUser setEmployeeDepartmentId(String employeeDepartmentId){
    	this.employeeDepartmentId=employeeDepartmentId.trim();
    	return this;
    }
 
    
    
      
      
    public String getEmployeeDepartmentId(){
        return this.employeeDepartmentId;  
    }
    
    public static Map<String,List<String>> getEmployeeDepartmentIdCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("employeeDepartmentId",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getEmployeeDepartmentIdStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("employee_department_id",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public EmployeeUser setEmployeeNumber(String employeeNumber){
    	this.employeeNumber=employeeNumber.trim();
    	return this;
    }
 
    
    
      
      
    public String getEmployeeNumber(){
        return this.employeeNumber;  
    }
    
    public static Map<String,List<String>> getEmployeeNumberCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("employeeNumber",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getEmployeeNumberStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("employee_number",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public EmployeeUser setEmployeeTelphone(String employeeTelphone){
    	this.employeeTelphone=employeeTelphone.trim();
    	return this;
    }
 
    
    
      
      
    public String getEmployeeTelphone(){
        return this.employeeTelphone;  
    }
    
    public static Map<String,List<String>> getEmployeeTelphoneCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("employeeTelphone",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getEmployeeTelphoneStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("employee_telphone",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public EmployeeUser setEmployeeRealName(String employeeRealName){
    	this.employeeRealName=employeeRealName.trim();
    	return this;
    }
 
    
    
      
      
    public String getEmployeeRealName(){
        return this.employeeRealName;  
    }
    
    public static Map<String,List<String>> getEmployeeRealNameCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("employeeRealName",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getEmployeeRealNameStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("employee_real_name",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public EmployeeUser setGender(Integer gender){
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
          
    public EmployeeUser setEmployeeUserName(String employeeUserName){
    	this.employeeUserName=employeeUserName.trim();
    	return this;
    }
 
    
    
      
      
    public String getEmployeeUserName(){
        return this.employeeUserName;  
    }
    
    public static Map<String,List<String>> getEmployeeUserNameCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("employeeUserName",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getEmployeeUserNameStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("employee_user_name",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public EmployeeUser setEmployeeUserPassword(String employeeUserPassword){
    	this.employeeUserPassword=employeeUserPassword.trim();
    	return this;
    }
 
    
    
      
      
    public String getEmployeeUserPassword(){
        return this.employeeUserPassword;  
    }
    
    public static Map<String,List<String>> getEmployeeUserPasswordCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("employeeUserPassword",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getEmployeeUserPasswordStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("employee_user_password",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public EmployeeUser setConsoleUserId(String consoleUserId){
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
          
    public EmployeeUser setIsOnline(Integer isOnline){
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
          
    public EmployeeUser setCreateDate(Integer createDate){
    	this.createDate=createDate;
    	return this;
    }
 
    
    public EmployeeUser autoSetCreateDate(){	
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
          
    public EmployeeUser setCreateTime(Integer createTime){
    	this.createTime=createTime;
    	return this;
    }
 
    
    
    public EmployeeUser autoSetCreateTime(){
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
    	EmployeeUser employeeUser = null;
		try {
			employeeUser = (EmployeeUser)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}   
        return employeeUser;  
    }
} 