package com.qim.loan.util.common;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类名:PrimaryKeyUtil
 * 描述:	生成主键 
 * 创建者:冯子文
 * 创建时间: 2016年4月21日 下午2:56:50 
 * 更新者:冯子文   
 * 更新时间: 2016年4月21日 下午2:56:50
*/
public class PrimaryKeyUtil {
	
	private static Logger logger = LoggerFactory.getLogger(PrimaryKeyUtil.class);
	
	//32位ID
	public static String getPrimaryId32() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	//36位ID
	public static String getPrimaryId36() {
		return UUID.randomUUID().toString();
	}
	//短信码
	public static String numberCode(final int length){
		return getCodeBySource(length,"123456789");
	}
	//4位短信码
	public static String numberCode4(){
		return numberCode(4);
	}
	//5位短信吗
	public static String smsCode5(){
		return numberCode(4);
	}
	//6位短信码
	public static String numberCode6(){
		return numberCode(6);
	}
	//验证码
	public static String getCodeBySource(final int length,final String source) {
		String temp=source;
		if (StringUtil.isNull(source))
			temp = "3456789abcdefghjkmnpqrstuvwxyABCDEFGHJKMNPQRSTUVWXY";
		if(length<1)
			return "请输入正确的长度.";
		int len = temp.length();
		Random rand = new Random(System.currentTimeMillis());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) 
			sb.append(temp.charAt(rand.nextInt(len - 1)));
		return sb.toString();
	}
	//16为短码
	public static String getShortCode16(final String uuid){
		return getCompress(uuid,16);
	}
	
	public static String getToken(){
		return getShortCode16(getPrimaryId32())+getShortCode16(getPrimaryId32());
	}
	
	
	//8位短码
	public static String getShortCode8(final String uuid){
		return getCompress(uuid,8);
	}	
	//压缩UUID
	public static String getCompress(final String uuid,final int length){
		String chars="abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		if(uuid.length()%length!=0 || length<1)
			return "请输入UUID的整数倍长度(大于0).";
		int bit=uuid.length()/length;
		StringBuffer sb = new StringBuffer();  
	    for (int i = 0; i < length; i++) {  
	        String str = uuid.substring(i * bit, (i+1) * bit);  
	        int x = Integer.parseInt(str, 16)% 0x3E;  
	        sb.append(chars.charAt(x));  
	    }  
	    return sb.toString(); 
	}	
    // 生成随机颜色    
	public static Color getRandomColor() {    
        Random random = new Random();       
        int r =random.nextInt()%255;    
        int g =random.nextInt()%255;    
        int b =random.nextInt()%255;    
        return new Color(r, g, b);    
    }
	//获取层级
	public static String getHierarchyLevel(final String hierarchy){
		if(StringUtil.isNull(hierarchy)){
			return hierarchy+getHierarchy()+"/";
		}else{
			return "/"+getHierarchy()+"/";
		}
	}
	//产生层级结构
	public static String getHierarchy(){
		String source = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";	
		return getCodeBySource(3,source);
	}
	
	//字母码
	public static String getLetterCode(final int length){
		String source = "abcdefghjklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXY";	
		return getCodeBySource(length,source);
	}
	
	public static String getAlphaCode(final int length){
		String source = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";	
		return getCodeBySource(length,source);
	}
	
	//图形码
	public static String getGraphCode(final int length){
		String source = "3456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXY";	
		return getCodeBySource(length,source);
	}
	//4位图形码
	public static String getGraphCode4(){
		return getGraphCode(4);
	}
	//5位图形码
	public static String getGraphCode5(){
		return getGraphCode(5);
	}
	//6位图形码
	public static String getGraphCode6(){
		return getGraphCode(5);
	}	
	//数字码
	public static String getNumberCode(final int length){
		String source = "1234567890";	
		return getCodeBySource(length,source);
	}		
	//产生长数字数字(长度需要大于12)
    public static String getNumber(int len) { 
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String simple = null;
		try {
			simple =String.valueOf(System.currentTimeMillis()-sdf.parse("2017-01-01 00:00:00.000").getTime()).trim();
		} catch (ParseException e) {
			MessageUtil.error(logger, "产生数字", e);
			e.printStackTrace();
		}
		int length=simple.length();
		if(length>len){
			return simple;
		}else{
			return simple+getNumberCode(len-length);
		}
    }		
	// 15位主键号码
	public static Long getNumber15() {
		return Long.valueOf(getNumber(15));
	}
	// 18位主键号码
	public static Long getNumber18() {
		return Long.valueOf(getNumber(18));
	}	
	
	public static String getRegistePreCode(){
		
		return null;
	}
	
	public static long getCurrentTimeStamp(){
		return System.currentTimeMillis();
	}
	
	public static String getMixPreCode(){
		return getMixPreCode("");
	}
	
	//模式(混淆模式)
	public static String getMixPreCode(String fmt){//123321模式
		String current=String.valueOf(System.currentTimeMillis());
		if(StringUtil.isNull(fmt) || getFmt(fmt)>12)
			fmt="123231";
		//混淆模式(13/12 )
		StringBuilder sBuilder=new StringBuilder();
		int pos=0;
		for(int i=0;i<fmt.length();i++){
			sBuilder.append(current.substring(pos, pos+(Integer.valueOf(fmt.charAt(i))-48)));
			sBuilder.append(getAlphaCode(Integer.valueOf(fmt.charAt(i))-48));
			pos=pos+Integer.valueOf(fmt.charAt(i))-48;
		}
		return sBuilder.toString();
	}
	
	public static String getPrasePreCode(String preCode){
		return getPrasePreCode(preCode,"");
	}
		
	//模式解析(混淆模式)
	public static String getPrasePreCode(String preCode,String fmt){//123321模式
		if(StringUtil.isNull(preCode))
			return "";
		if(StringUtil.isNull(fmt) || getFmt(fmt)>12)
			fmt="123231";
		//混淆模式(13/12 )
		StringBuilder sBuilder=new StringBuilder();
		int pos=0;
		for(int i=0;i<fmt.length();i++){
			sBuilder.append(preCode.substring(pos, pos+(Integer.valueOf(fmt.charAt(i))-48)));
			pos=pos+(Integer.valueOf(fmt.charAt(i))-48)*2;
		}
		return sBuilder.toString();
	}
	
	public static Boolean getSpan(long time,int expireM){
		if(time<1)
			return false;
		long now=System.currentTimeMillis();
	    long diff =now - time;
	    long second = diff / 1000;
	    long minite=second/60;
	    if(minite>expireM)
	    	return false;
	    return true;
	}
	
	
	
	
	
	
	private static int getFmt(String fmt){
		if(StringUtil.isNull(fmt))
			return 0;
		int len=0;
		for(int i=0;i<fmt.length();i++)
			len+=Integer.valueOf(fmt.charAt(i))-48;
		return len;
	}
	
	
	

	public static void main(String... args) {
		for(int i=82009001;i<82010000;i++){
			System.out.println("INSERT INTO client_data(id,telphone_number,channel_id,channel_user_id,create_date) values('"+getPrimaryId32()+"','159"+i+"','1111122222','1111111111',20180529);");
		}
	}

}
