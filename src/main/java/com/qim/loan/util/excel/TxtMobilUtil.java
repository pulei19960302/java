package com.qim.loan.util.excel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qim.loan.util.common.MessageUtil;
import com.qim.loan.util.common.MobilUtil;
import com.qim.loan.util.common.StringUtil;

public class TxtMobilUtil {

	private static Logger logger = LoggerFactory.getLogger(TxtMobilUtil.class);	
	
	public static List<String> getMobil(String path){
		List<String> list=new LinkedList<String>();
        try {
	        File file = new File(path);  
	        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));  
	        String line = null;  
	        while ((line = reader.readLine()) != null) {
	        	if(StringUtil.isNotNull(line) && MobilUtil.isMobile(line.trim()))
	        		list.add(line.trim());
	        }  
	        reader.close();
        }catch (Exception e) {
        	MessageUtil.error(logger, "读文件操作", e);
        	list=null;
        }         
        return list;  
	}
	
	
	public static void main(String[] args) {

	}

}
