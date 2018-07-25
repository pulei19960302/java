package com.qim.loan.util.paramter;


import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.common.ListUtil;
import com.qim.loan.util.common.MapUtil;
import com.qim.loan.util.common.MessageUtil;
import com.qim.loan.util.common.StringUtil;
import com.qim.loan.util.setting.StatusSetting;


/**
     * 类名:BaseResponseVo
     * 描述:基础返回类	 
     * 创建者:冯子文
     * 创建时间: 2016年5月9日 上午10:19:53 
	 * 更新者:冯子文   
	 * 更新时间: 2016年5月9日 上午10:19:53
 */
public class BaseResponse {
	// 状态
	private Integer status;
	// 消息
	private String msg;
	//返回上传地址
	private String uploadPath;// 成功地址
	// 数据
	private Object result;	
	/**
	 * 
	     * 方法名:默认构造函数
	     * 功能描述:	 
	     * 创建者:冯子文
	     * 创建时间: 2016年5月9日 上午11:10:29
	 */
	public BaseResponse(){
		
	}	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setUploadFailure() {
		this.status =StatusSetting.getUploadFailure();
	}
	public void setExceptionFailure() {
		this.status = StatusSetting.getExceptionFailure();
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getResult() {
		return result;
	}
	
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setResult(Object result) {
		if(result instanceof Pager){//分页
			Pager pager=(Pager)result;
			List ls=pager.getRows();//列表
			if(ListUtil.isNotNull(ls)){//非空
				if(ls.get(0) instanceof Map){//Map
					this.result =ListUtil.toCamel((List<Map<String, Object>>)ls);
				}else{//实体
					this.result =ls;
				}
			}			
		}else{
			setMultiChange(result);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void setMultiChange(Object result){
		if(result instanceof List){//列表
			List<?> rows=(List<?>) result;
			if(ListUtil.isNotNull(rows)){//非空
				if(rows.get(0) instanceof Map){//Map
					this.result =ListUtil.toCamel((List<Map<String, Object>>)rows);
				}else{//实体
					this.result =rows;
				}
			}
		}else{
			setSingleChange(result);
		}
	}	
	
	@SuppressWarnings("unchecked")
	private void setSingleChange(Object result){
		if(result instanceof Map){//map
			Map<String, Object> map=(Map<String, Object>) result;
			if(MapUtil.isNotNull(map))
				map=MapUtil.toCamel(map);
			this.result =map;
		}else{
			this.result = result;
		}
	}
	public void setterStatus(int status,String msg){
		this.setStatus(status);
		this.setMsg(msg);
	}
	
	public void setterFailure(String msg){
		this.setStatus(StatusSetting.getFailureStatus());
		this.setMsg(msg);
	}
	
	public void setterSuccess(Object result){	
		this.setStatus(StatusSetting.getSuccessStatus());
		this.setResult(result);
	}
	
	public void setterSuccess(Object result,String msg){
		this.setStatus(StatusSetting.getSuccessStatus());
		this.setResult(result);
		this.setMsg(msg);
	}
	public void setterSuccessMsg(String msg){
		this.setStatus(StatusSetting.getSuccessStatus());
		this.setMsg(msg);
	}	

	public static BaseResponse countResponse(String title, int count){
		return count > 0 ? BaseResponse.setSuccessMsg(title + "成功.") :((count<-1)?BaseResponse.setFailure("传入的参数错误."):(count==-1)?BaseResponse.setFailure(title + "存在重复."):BaseResponse.setFailure(title + "失败."));
	}
	
	public static BaseResponse countResponse(String title, int count,String msg){
		return count > 0 ? BaseResponse.setSuccessMsg(title + "成功,"+msg) :((count<-1)?BaseResponse.setFailure("传入的参数错误."):(count==-1)?BaseResponse.setFailure(title + "存在重复."):BaseResponse.setFailure(title + "失败."));
	}
	
	public static BaseResponse ObjectResponse(Object object){
		return object==null? BaseResponse.setFailure("传入的参数错误."):BaseResponse.setSuccess(object);
	}		
	public static BaseResponse setSuccess(Object result){
		BaseResponse response=new BaseResponse();
		response.setterSuccess(result);
		return response;
	}

	public static BaseResponse setSuccessMsg(String msg){
		BaseResponse response=new BaseResponse();
		response.setterSuccessMsg(msg);
		return response;
	}
	
	public static BaseResponse setUploadFailureMsg(String msg){
		BaseResponse response=new BaseResponse();
		response.setUploadFailure();
		response.setterSuccessMsg(msg);
		return response;
	}
	
	public static BaseResponse setUploadSuccess(String uploadPath){
		BaseResponse response=new BaseResponse();
		response.setStatus(StatusSetting.getUploadSuccess());
		response.setUploadPath(uploadPath);
		return response;
	}	
	
	public BaseResponse setExceptionMsg(Exception e){
		BaseResponse response=new BaseResponse();
		response.setExceptionFailure();
		response.setMsg(getExceptionMsg(e,null,null));
		return response;
	}
	
	public BaseResponse setExceptionMsg(Exception e,Class<?> cls){
		BaseResponse response=new BaseResponse();
		response.setExceptionFailure();
		response.setMsg(getExceptionMsg(e,cls,null));
		return response;
	}
	
	public static BaseResponse setExceptionMsg(Exception e,Class<?> cls,String method){
		BaseResponse response=new BaseResponse();
		response.setExceptionFailure();
		response.setMsg(getExceptionMsg(e,cls,method));
		return response;
	}	
	
	public static BaseResponse setSuccess(Object result,String msg){
		BaseResponse response=new BaseResponse();
		response.setterSuccess(result,msg);
		return response;
	}
	public static BaseResponse setStatus(int status,String msg){
		BaseResponse response=new BaseResponse();
		response.setterStatus(status, msg);
		return response;
	}
	
	public static BaseResponse setFailure(String msg){
		BaseResponse response=new BaseResponse();
		response.setterFailure(msg);
		return response;
	}

	public BaseResponse setCountStatus(Integer count,String message){
		return count>0?setSuccessMsg(message+"成功."):setSuccessMsg(message+"失败.");
	}

	//获取当前类名及方法名
	private static String getExceptionMsg(Exception e,Class<?> cls,String method){
		StringBuilder sBuilder=new StringBuilder();
		sBuilder.append("time:["+DateUtil.formatNow("yyyy年MM月dd日 HH:mm:ss.SSS")+"]\r\n");
		if(cls!=null){
			String className=cls.getSimpleName();
			sBuilder.append("className:["+className+"]\r\n");			
		}
		if(StringUtil.isNotNull(method))
			sBuilder.append("methodName:["+method+"]\r\n");
		sBuilder.append("Error:["+e.getCause().getMessage()+"]\r\n");
		MessageUtil.error(LoggerFactory.getLogger(cls.getClass()), sBuilder.toString());
		return sBuilder.toString();
	} 
	
	//时间反序列化
	class DateDeserializer implements JsonDeserializer<java.util.Date> {
	    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
	    	return json == null ? null : new Date(json.getAsLong());
	    }
	}
	//时间序列化
	class DateSerializer implements JsonSerializer<Date> {
	    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
	    	return src == null ? null :new JsonPrimitive(src.getTime());
	    }
	}
	
	@Override
    public String toString(){
		//毫秒数
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(java.util.Date.class, new DateSerializer()).setDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        gb.registerTypeAdapter(java.util.Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);
        return gb.create().toJson(this);
    } 
	
	

	public static void main(String... args) {
//		Map<String,Object> map=new HashMap<String,Object>();
//		map.put("StartTime", new Date());
//		map.put("StartTimeSl","2013-07-27");
//		System.out.println(setSuccess(map));
	}

}

