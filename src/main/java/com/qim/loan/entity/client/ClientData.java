package com.qim.loan.entity.client;  
  
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.common.PrimaryKeyUtil;

  
public class ClientData implements Serializable,Cloneable{  

	private static final long serialVersionUID = 1L;
	//ID      
    private String id;  
	//电话号码      
    private String telphoneNumber;  
	//渠道ID      
    private String channelId;  
	//渠道用户ID      
    private String channelUserId;  
	//创建日期      
    private Integer createDate;  
	//创建时间      
    private Integer createTime;  
	//总台用户ID      
    private String consoleUserId;  
	//分发日期      
    private Integer distributeDate;  
	//分发时间      
    private Integer distributeTime;  
	//部门一用户ID      
    private String employeeOneUserId;  
	//是否已打电话      
    private Integer isDial;  
	//是否有意向      
    private Integer isPurpose;  
	//意向说明      
    private String purposeDescript;  
	//意向日期      
    private Integer dialDate;  
	//意向时间      
    private Integer dialTime;  
	//真实姓名      
    private String realName;  
	//是否实名      
    private Integer isReal;  
	//服务密码      
    private String servicePassword;  
	//使用年限      
    private String phoneUseYear;  
	//身份证号码      
    private String identificationCardNumber;  
	//芝麻分      
    private Integer seseamScore;  
	//淘气值      
    private Integer naughtyScore;  
	//真实学历      
    private String realEducation;  
	//是否有房贷      
    private Integer isHouseLoan;  
	//是否购买公积金      
    private Integer isAccumulationFund;  
	//是否有信用卡      
    private Integer isCreadit;  
	//单张额度      
    private BigDecimal creadit;  
	//公司名称      
    private String companyName;  
	//公司地址      
    private String companyPlace;  
	//现居住地      
    private String livePlace;  
	//联系人姓名1      
    private String contractNameOne;  
	//联系人电话1      
    private String contractTelphoneOne;  
	//亲属关系1      
    private String contractRelationOne;  
	//联系人姓名2      
    private String contractNameTwo;  
	//联系人电话2      
    private String contractTelphoneTwo;  
	//亲属关系2      
    private String contractRelationTwo;  
	//联系人姓名3      
    private String contractNameThree;  
	//联系人电话3      
    private String contractTelphoneThree;  
	//亲属关系3      
    private String contractRelationThree;  
	//联系人姓名4      
    private String contractNameFour;  
	//联系人电话4      
    private String contractTelphoneFour;  
	//亲属关系4      
    private String contractRelationFour;  
	//联系人姓名5      
    private String contractNameFive;  
	//联系人电话5      
    private String contractTelphoneFive;  
	//亲属关系5      
    private String contractRelationFive;  
	//操作系统      
    private String osVersion;  
	//填写资料日期      
    private Integer fillDate;  
	//填写资料时间      
    private Integer fillTime;  
	//部门二用户ID      
    private String employeeTwoUserId;  
	//是否填写资料      
    private Integer isFill;  
	//是否已处理      
    private Integer isHanle;  
	//处理描述      
    private String handleDescript;  
	//下款日期      
    private Integer handleDate;  
	//下款时间      
    private Integer handleTime;  
    public  ClientData(){     
    }
     
    public ClientData setId(String id){
    	this.id=id.trim();
    	return this;
    }
 
    public ClientData autoSetId(){	
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
          
    public ClientData setTelphoneNumber(String telphoneNumber){
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
          
    public ClientData setChannelId(String channelId){
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
          
    public ClientData setChannelUserId(String channelUserId){
    	this.channelUserId=channelUserId.trim();
    	return this;
    }
 
    
    
      
      
    public String getChannelUserId(){
        return this.channelUserId;  
    }
    
    public static Map<String,List<String>> getChannelUserIdCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("channelUserId",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getChannelUserIdStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("channel_user_id",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setCreateDate(Integer createDate){
    	this.createDate=createDate;
    	return this;
    }
 
    
    public ClientData autoSetCreateDate(){	
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
          
    public ClientData setCreateTime(Integer createTime){
    	this.createTime=createTime;
    	return this;
    }
 
    
    
    public ClientData autoSetCreateTime(){
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
          
    public ClientData setConsoleUserId(String consoleUserId){
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
          
    public ClientData setDistributeDate(Integer distributeDate){
    	this.distributeDate=distributeDate;
    	return this;
    }
 
    
    public ClientData autoSetDistributeDate(){	
    	this.distributeDate=DateUtil.formatCurrentDate();
     	return this;
    }   
    
      
      
    public Integer getDistributeDate(){
        return this.distributeDate;  
    }
    
    public static Map<String,List<Integer>> getDistributeDateCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("distributeDate",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getDistributeDateStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("distribute_date",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setDistributeTime(Integer distributeTime){
    	this.distributeTime=distributeTime;
    	return this;
    }
 
    
    
    public ClientData autoSetDistributeTime(){
    	this.distributeTime=DateUtil.formatCurrentTime();	
    	return this;
    }
      
      
    public Integer getDistributeTime(){
        return this.distributeTime;  
    }
    
    public static Map<String,List<Integer>> getDistributeTimeCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("distributeTime",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getDistributeTimeStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("distribute_time",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setEmployeeOneUserId(String employeeOneUserId){
    	this.employeeOneUserId=employeeOneUserId.trim();
    	return this;
    }
 
    
    
      
      
    public String getEmployeeOneUserId(){
        return this.employeeOneUserId;  
    }
    
    public static Map<String,List<String>> getEmployeeOneUserIdCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("employeeOneUserId",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getEmployeeOneUserIdStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("employee_one_user_id",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setIsDial(Integer isDial){
    	this.isDial=isDial;
    	return this;
    }
 
    
    
      
      
    public Integer getIsDial(){
        return this.isDial;  
    }
    
    public static Map<String,List<Integer>> getIsDialCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("isDial",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getIsDialStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("is_dial",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setIsPurpose(Integer isPurpose){
    	this.isPurpose=isPurpose;
    	return this;
    }
 
    
    
      
      
    public Integer getIsPurpose(){
        return this.isPurpose;  
    }
    
    public static Map<String,List<Integer>> getIsPurposeCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("isPurpose",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getIsPurposeStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("is_purpose",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setPurposeDescript(String purposeDescript){
    	this.purposeDescript=purposeDescript.trim();
    	return this;
    }
 
    
    
      
      
    public String getPurposeDescript(){
        return this.purposeDescript;  
    }
    
    public static Map<String,List<String>> getPurposeDescriptCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("purposeDescript",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getPurposeDescriptStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("purpose_descript",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setDialDate(Integer dialDate){
    	this.dialDate=dialDate;
    	return this;
    }
 
    
    public ClientData autoSetDialDate(){	
    	this.dialDate=DateUtil.formatCurrentDate();
     	return this;
    }   
    
      
      
    public Integer getDialDate(){
        return this.dialDate;  
    }
    
    public static Map<String,List<Integer>> getDialDateCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("dialDate",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getDialDateStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("dial_date",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setDialTime(Integer dialTime){
    	this.dialTime=dialTime;
    	return this;
    }
 
    
    
    public ClientData autoSetDialTime(){
    	this.dialTime=DateUtil.formatCurrentTime();	
    	return this;
    }
      
      
    public Integer getDialTime(){
        return this.dialTime;  
    }
    
    public static Map<String,List<Integer>> getDialTimeCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("dialTime",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getDialTimeStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("dial_time",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setRealName(String realName){
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
          
    public ClientData setIsReal(Integer isReal){
    	this.isReal=isReal;
    	return this;
    }
 
    
    
      
      
    public Integer getIsReal(){
        return this.isReal;  
    }
    
    public static Map<String,List<Integer>> getIsRealCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("isReal",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getIsRealStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("is_real",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setServicePassword(String servicePassword){
    	this.servicePassword=servicePassword.trim();
    	return this;
    }
 
    
    
      
      
    public String getServicePassword(){
        return this.servicePassword;  
    }
    
    public static Map<String,List<String>> getServicePasswordCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("servicePassword",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getServicePasswordStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("service_password",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setPhoneUseYear(String phoneUseYear){
    	this.phoneUseYear=phoneUseYear.trim();
    	return this;
    }
 
    
    
      
      
    public String getPhoneUseYear(){
        return this.phoneUseYear;  
    }
    
    public static Map<String,List<String>> getPhoneUseYearCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("phoneUseYear",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getPhoneUseYearStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("phone_use_year",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setIdentificationCardNumber(String identificationCardNumber){
    	this.identificationCardNumber=identificationCardNumber.trim();
    	return this;
    }
 
    
    
      
      
    public String getIdentificationCardNumber(){
        return this.identificationCardNumber;  
    }
    
    public static Map<String,List<String>> getIdentificationCardNumberCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("identificationCardNumber",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getIdentificationCardNumberStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("identification_card_number",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setSeseamScore(Integer seseamScore){
    	this.seseamScore=seseamScore;
    	return this;
    }
 
    
    
      
      
    public Integer getSeseamScore(){
        return this.seseamScore;  
    }
    
    public static Map<String,List<Integer>> getSeseamScoreCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("seseamScore",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getSeseamScoreStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("seseam_score",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setNaughtyScore(Integer naughtyScore){
    	this.naughtyScore=naughtyScore;
    	return this;
    }
 
    
    
      
      
    public Integer getNaughtyScore(){
        return this.naughtyScore;  
    }
    
    public static Map<String,List<Integer>> getNaughtyScoreCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("naughtyScore",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getNaughtyScoreStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("naughty_score",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setRealEducation(String realEducation){
    	this.realEducation=realEducation.trim();
    	return this;
    }
 
    
    
      
      
    public String getRealEducation(){
        return this.realEducation;  
    }
    
    public static Map<String,List<String>> getRealEducationCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("realEducation",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getRealEducationStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("real_education",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setIsHouseLoan(Integer isHouseLoan){
    	this.isHouseLoan=isHouseLoan;
    	return this;
    }
 
    
    
      
      
    public Integer getIsHouseLoan(){
        return this.isHouseLoan;  
    }
    
    public static Map<String,List<Integer>> getIsHouseLoanCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("isHouseLoan",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getIsHouseLoanStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("is_house_loan",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setIsAccumulationFund(Integer isAccumulationFund){
    	this.isAccumulationFund=isAccumulationFund;
    	return this;
    }
 
    
    
      
      
    public Integer getIsAccumulationFund(){
        return this.isAccumulationFund;  
    }
    
    public static Map<String,List<Integer>> getIsAccumulationFundCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("isAccumulationFund",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getIsAccumulationFundStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("is_accumulation_fund",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setIsCreadit(Integer isCreadit){
    	this.isCreadit=isCreadit;
    	return this;
    }
 
    
    
      
      
    public Integer getIsCreadit(){
        return this.isCreadit;  
    }
    
    public static Map<String,List<Integer>> getIsCreaditCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("isCreadit",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getIsCreaditStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("is_creadit",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setCreadit(BigDecimal creadit){
    	this.creadit=creadit;
    	return this;
    }
 
    
    
      
      
    public BigDecimal getCreadit(){
        return this.creadit;  
    }
    
    public static Map<String,List<BigDecimal>> getCreaditCamel(BigDecimal ...args){
        if(args!=null && args.length>0){
	        Map<String,List<BigDecimal>> map=new HashMap<String, List<BigDecimal>>();
	    	map.put("creadit",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<BigDecimal>> getCreaditStrip(BigDecimal ...args){
        if(args!=null && args.length>0){
	        Map<String,List<BigDecimal>> map=new HashMap<String, List<BigDecimal>>();
	    	map.put("creadit",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setCompanyName(String companyName){
    	this.companyName=companyName.trim();
    	return this;
    }
 
    
    
      
      
    public String getCompanyName(){
        return this.companyName;  
    }
    
    public static Map<String,List<String>> getCompanyNameCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("companyName",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getCompanyNameStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("company_name",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setCompanyPlace(String companyPlace){
    	this.companyPlace=companyPlace.trim();
    	return this;
    }
 
    
    
      
      
    public String getCompanyPlace(){
        return this.companyPlace;  
    }
    
    public static Map<String,List<String>> getCompanyPlaceCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("companyPlace",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getCompanyPlaceStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("company_place",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setLivePlace(String livePlace){
    	this.livePlace=livePlace.trim();
    	return this;
    }
 
    
    
      
      
    public String getLivePlace(){
        return this.livePlace;  
    }
    
    public static Map<String,List<String>> getLivePlaceCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("livePlace",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getLivePlaceStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("live_place",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setContractNameOne(String contractNameOne){
    	this.contractNameOne=contractNameOne.trim();
    	return this;
    }
 
    
    
      
      
    public String getContractNameOne(){
        return this.contractNameOne;  
    }
    
    public static Map<String,List<String>> getContractNameOneCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contractNameOne",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getContractNameOneStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contract_name_one",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setContractTelphoneOne(String contractTelphoneOne){
    	this.contractTelphoneOne=contractTelphoneOne.trim();
    	return this;
    }
 
    
    
      
      
    public String getContractTelphoneOne(){
        return this.contractTelphoneOne;  
    }
    
    public static Map<String,List<String>> getContractTelphoneOneCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contractTelphoneOne",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getContractTelphoneOneStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contract_telphone_one",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setContractRelationOne(String contractRelationOne){
    	this.contractRelationOne=contractRelationOne.trim();
    	return this;
    }
 
    
    
      
      
    public String getContractRelationOne(){
        return this.contractRelationOne;  
    }
    
    public static Map<String,List<String>> getContractRelationOneCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contractRelationOne",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getContractRelationOneStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contract_relation_one",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setContractNameTwo(String contractNameTwo){
    	this.contractNameTwo=contractNameTwo.trim();
    	return this;
    }
 
    
    
      
      
    public String getContractNameTwo(){
        return this.contractNameTwo;  
    }
    
    public static Map<String,List<String>> getContractNameTwoCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contractNameTwo",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getContractNameTwoStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contract_name_two",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setContractTelphoneTwo(String contractTelphoneTwo){
    	this.contractTelphoneTwo=contractTelphoneTwo.trim();
    	return this;
    }
 
    
    
      
      
    public String getContractTelphoneTwo(){
        return this.contractTelphoneTwo;  
    }
    
    public static Map<String,List<String>> getContractTelphoneTwoCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contractTelphoneTwo",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getContractTelphoneTwoStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contract_telphone_two",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setContractRelationTwo(String contractRelationTwo){
    	this.contractRelationTwo=contractRelationTwo.trim();
    	return this;
    }
 
    
    
      
      
    public String getContractRelationTwo(){
        return this.contractRelationTwo;  
    }
    
    public static Map<String,List<String>> getContractRelationTwoCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contractRelationTwo",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getContractRelationTwoStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contract_relation_two",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setContractNameThree(String contractNameThree){
    	this.contractNameThree=contractNameThree.trim();
    	return this;
    }
 
    
    
      
      
    public String getContractNameThree(){
        return this.contractNameThree;  
    }
    
    public static Map<String,List<String>> getContractNameThreeCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contractNameThree",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getContractNameThreeStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contract_name_three",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setContractTelphoneThree(String contractTelphoneThree){
    	this.contractTelphoneThree=contractTelphoneThree.trim();
    	return this;
    }
 
    
    
      
      
    public String getContractTelphoneThree(){
        return this.contractTelphoneThree;  
    }
    
    public static Map<String,List<String>> getContractTelphoneThreeCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contractTelphoneThree",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getContractTelphoneThreeStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contract_telphone_three",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setContractRelationThree(String contractRelationThree){
    	this.contractRelationThree=contractRelationThree.trim();
    	return this;
    }
 
    
    
      
      
    public String getContractRelationThree(){
        return this.contractRelationThree;  
    }
    
    public static Map<String,List<String>> getContractRelationThreeCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contractRelationThree",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getContractRelationThreeStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contract_relation_three",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setContractNameFour(String contractNameFour){
    	this.contractNameFour=contractNameFour.trim();
    	return this;
    }
 
    
    
      
      
    public String getContractNameFour(){
        return this.contractNameFour;  
    }
    
    public static Map<String,List<String>> getContractNameFourCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contractNameFour",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getContractNameFourStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contract_name_four",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setContractTelphoneFour(String contractTelphoneFour){
    	this.contractTelphoneFour=contractTelphoneFour.trim();
    	return this;
    }
 
    
    
      
      
    public String getContractTelphoneFour(){
        return this.contractTelphoneFour;  
    }
    
    public static Map<String,List<String>> getContractTelphoneFourCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contractTelphoneFour",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getContractTelphoneFourStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contract_telphone_four",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setContractRelationFour(String contractRelationFour){
    	this.contractRelationFour=contractRelationFour.trim();
    	return this;
    }
 
    
    
      
      
    public String getContractRelationFour(){
        return this.contractRelationFour;  
    }
    
    public static Map<String,List<String>> getContractRelationFourCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contractRelationFour",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getContractRelationFourStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contract_relation_four",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setContractNameFive(String contractNameFive){
    	this.contractNameFive=contractNameFive.trim();
    	return this;
    }
 
    
    
      
      
    public String getContractNameFive(){
        return this.contractNameFive;  
    }
    
    public static Map<String,List<String>> getContractNameFiveCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contractNameFive",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getContractNameFiveStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contract_name_five",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setContractTelphoneFive(String contractTelphoneFive){
    	this.contractTelphoneFive=contractTelphoneFive.trim();
    	return this;
    }
 
    
    
      
      
    public String getContractTelphoneFive(){
        return this.contractTelphoneFive;  
    }
    
    public static Map<String,List<String>> getContractTelphoneFiveCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contractTelphoneFive",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getContractTelphoneFiveStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contract_telphone_five",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setContractRelationFive(String contractRelationFive){
    	this.contractRelationFive=contractRelationFive.trim();
    	return this;
    }
 
    
    
      
      
    public String getContractRelationFive(){
        return this.contractRelationFive;  
    }
    
    public static Map<String,List<String>> getContractRelationFiveCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contractRelationFive",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getContractRelationFiveStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("contract_relation_five",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setOsVersion(String osVersion){
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
          
    public ClientData setFillDate(Integer fillDate){
    	this.fillDate=fillDate;
    	return this;
    }
 
    
    public ClientData autoSetFillDate(){	
    	this.fillDate=DateUtil.formatCurrentDate();
     	return this;
    }   
    
      
      
    public Integer getFillDate(){
        return this.fillDate;  
    }
    
    public static Map<String,List<Integer>> getFillDateCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("fillDate",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getFillDateStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("fill_date",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setFillTime(Integer fillTime){
    	this.fillTime=fillTime;
    	return this;
    }
 
    
    
    public ClientData autoSetFillTime(){
    	this.fillTime=DateUtil.formatCurrentTime();	
    	return this;
    }
      
      
    public Integer getFillTime(){
        return this.fillTime;  
    }
    
    public static Map<String,List<Integer>> getFillTimeCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("fillTime",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getFillTimeStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("fill_time",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setEmployeeTwoUserId(String employeeTwoUserId){
    	this.employeeTwoUserId=employeeTwoUserId.trim();
    	return this;
    }
 
    
    
      
      
    public String getEmployeeTwoUserId(){
        return this.employeeTwoUserId;  
    }
    
    public static Map<String,List<String>> getEmployeeTwoUserIdCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("employeeTwoUserId",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getEmployeeTwoUserIdStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("employee_two_user_id",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setIsFill(Integer isFill){
    	this.isFill=isFill;
    	return this;
    }
 
    
    
      
      
    public Integer getIsFill(){
        return this.isFill;  
    }
    
    public static Map<String,List<Integer>> getIsFillCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("isFill",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getIsFillStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("is_fill",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setIsHanle(Integer isHanle){
    	this.isHanle=isHanle;
    	return this;
    }
 
    
    
      
      
    public Integer getIsHanle(){
        return this.isHanle;  
    }
    
    public static Map<String,List<Integer>> getIsHanleCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("isHanle",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getIsHanleStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("is_hanle",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setHandleDescript(String handleDescript){
    	this.handleDescript=handleDescript.trim();
    	return this;
    }
 
    
    
      
      
    public String getHandleDescript(){
        return this.handleDescript;  
    }
    
    public static Map<String,List<String>> getHandleDescriptCamel(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("handleDescript",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<String>> getHandleDescriptStrip(String ...args){
        if(args!=null && args.length>0){
	        Map<String,List<String>> map=new HashMap<String, List<String>>();
	    	map.put("handle_descript",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setHandleDate(Integer handleDate){
    	this.handleDate=handleDate;
    	return this;
    }
 
    
    public ClientData autoSetHandleDate(){	
    	this.handleDate=DateUtil.formatCurrentDate();
     	return this;
    }   
    
      
      
    public Integer getHandleDate(){
        return this.handleDate;  
    }
    
    public static Map<String,List<Integer>> getHandleDateCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("handleDate",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getHandleDateStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("handle_date",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    public ClientData setHandleTime(Integer handleTime){
    	this.handleTime=handleTime;
    	return this;
    }
 
    
    
    public ClientData autoSetHandleTime(){
    	this.handleTime=DateUtil.formatCurrentTime();	
    	return this;
    }
      
      
    public Integer getHandleTime(){
        return this.handleTime;  
    }
    
    public static Map<String,List<Integer>> getHandleTimeCamel(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("handleTime",Arrays.asList(args));
	    	return map;
    	}
    	return null;
    }

    public static Map<String,List<Integer>> getHandleTimeStrip(Integer ...args){
        if(args!=null && args.length>0){
	        Map<String,List<Integer>> map=new HashMap<String, List<Integer>>();
	    	map.put("handle_time",Arrays.asList(args));
	    	return map; 
    	}
    	return null;	    	 
    }    
          
    @Override  
    public Object clone() {  
    	ClientData clientData = null;
		try {
			clientData = (ClientData)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}   
        return clientData;  
    }
} 