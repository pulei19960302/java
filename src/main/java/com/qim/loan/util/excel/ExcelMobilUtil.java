package com.qim.loan.util.excel;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qim.loan.entity.distribute.DistributeRecords;
import com.qim.loan.util.common.ListUtil;
import com.qim.loan.util.common.MessageUtil;
import com.qim.loan.util.common.MobilUtil;
import com.qim.loan.util.common.StringUtil;

public class ExcelMobilUtil {

	private static Logger logger = LoggerFactory.getLogger(ExcelMobilUtil.class);

	private static Workbook getWorkbook(String path) {
		try {
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(path)); // 建立输入流
			Workbook workbook = null;
			if (path.endsWith("xlsx") ? true : false)// 07及以后
				workbook = new XSSFWorkbook(input);
			else
				workbook = new HSSFWorkbook(input);// 03
			input.close();
			return workbook;
		} catch (Exception e) {
			MessageUtil.error(logger, "打开WorkBook错误", e);
			return null;
		}
	}

	public static List<String> getMobil(String path) {
		List<String> tempList = new LinkedList<String>();
		Workbook workbook = getWorkbook(path);
		int sheetNumber = workbook.getNumberOfSheets();
		for (int i = 0; i < sheetNumber; i++) {
			List<String> tmp = read(workbook.getSheetAt(i));
			if (ListUtil.isNotNull(tmp))
				tempList.addAll(tmp);
		}
		closeWorkbook(workbook);
		return tempList;
	}

	public static List<PhoneName> getMobilPhoneName(String path) {
		List<PhoneName> tempList = new LinkedList<PhoneName>();
		Workbook workbook = getWorkbook(path);
		int sheetNumber = workbook.getNumberOfSheets();
		for (int i = 0; i < sheetNumber; i++) {
			List<PhoneName> tmp = readExcelNameByPhone(workbook.getSheetAt(i));
			if (ListUtil.isNotNull(tmp))
				tempList.addAll(tmp);
		}
		closeWorkbook(workbook);
		return tempList;
	}
	
	public static List<String> getMobilPhoneAndName(String path) {
		List<String> tempList = new LinkedList<String>();
		Workbook workbook = getWorkbook(path);
		int sheetNumber = workbook.getNumberOfSheets();
		for (int i = 0; i < sheetNumber; i++) {
			List<String> tmp = readExcelNameAndPhone(workbook.getSheetAt(i));
			if (ListUtil.isNotNull(tmp))
				tempList.addAll(tmp);
		}
		closeWorkbook(workbook);
		return tempList;
	}
	
	

	private static List<String> read(Sheet sheet) {
		List<String> tempList = new LinkedList<String>();
		if (sheet != null)
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {// 行
				Row row = sheet.getRow(i);// 行
				if (row != null)
					for (int j = 0; j < row.getLastCellNum(); j++) {// 列
						Cell cell = row.getCell(j);
						DecimalFormat df = new DecimalFormat("#");
						if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							String temp = df.format(cell.getNumericCellValue());
							if (StringUtil.isNotNull(temp) && MobilUtil.isMobile(temp))
								tempList.add(temp);
						}
					}
			}
		if (tempList != null && tempList.size() > 0) {
			return tempList;
		} else {
			return null;
		}
	}

	private static List<String> readExcelNameAndPhone(Sheet sheet) {
		List<String> sheetList = new ArrayList<String>();
		Integer lastRowNum = sheet.getLastRowNum();
		for (int i = 0; i <= lastRowNum; i++) { // 行
			String tmp = "";
			Row row = sheet.getRow(i); // 第几行
			if(row!=null) {
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if(cell!=null) {
						switch (cell.getColumnIndex()) {
						case 0:
							if (cell.getStringCellValue().length() >= 2) {
								tmp = tmp + cell.getStringCellValue();
							}
							break;
						case 1:
							DecimalFormat df = new DecimalFormat("#");
							String temp = df.format(cell.getNumericCellValue());
							if (StringUtil.isNotNull(temp) && MobilUtil.isMobile(temp)) {
								tmp = tmp +"---"+temp;
							} else {
								continue;
							}
							sheetList.add(tmp);
							break;
						}						
					}
				}				
			}
		}
		return sheetList;
	}

	private static List<PhoneName> readExcelNameByPhone(Sheet sheet) {
		List<PhoneName> sheetList = new ArrayList<PhoneName>();
		PhoneName phoneName = null;
		Integer lastRowNum = sheet.getLastRowNum();
		for (int i = 0; i <= lastRowNum; i++) { // 行
			phoneName = new PhoneName();
			Row row = sheet.getRow(i); // 第几行
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				switch (cell.getColumnIndex()) {
				case 0:
					if (cell.getStringCellValue().length() >= 2) {
						phoneName.setName(cell.getStringCellValue());
					}
					break;
				case 1:
					DecimalFormat df = new DecimalFormat("#");
					String temp = df.format(cell.getNumericCellValue());
					if (StringUtil.isNotNull(temp) && MobilUtil.isMobile(temp)) {
						phoneName.setPhone(temp);
					} else {
						continue;
					}
					sheetList.add(phoneName);
					break;
				}

			}
		}
		return sheetList;
	}
	
	// 导出
	public static HSSFWorkbook export(List<DistributeRecords> list){
	   String[] excelHeader = {"姓名","电话","日期","时间"};
	   HSSFWorkbook wb = new HSSFWorkbook();
	   HSSFSheet sheet = wb.createSheet();
	   HSSFRow row = sheet.createRow((int) 0);
	   HSSFCellStyle style = wb.createCellStyle();
	   style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	   
	   for (int i = 0; i < excelHeader.length; i++) {
		 HSSFCell cell = row.createCell(i);
	     cell.setCellValue(excelHeader[i]);
	     cell.setCellStyle(style);
	     sheet.setColumnWidth(0, 256*35);  
	     sheet.setColumnWidth(1, 256*35);  
//	     sheet.setColumnWidth(2, 256*35);  
//	     sheet.setColumnWidth(3, 256*35);  
	     sheet.autoSizeColumn(i);
	   }
       for (int j = 0; j < list.size(); j++) {
	      row = sheet.createRow(j);
	      DistributeRecords distributeRecords = list.get(j);
	      row.createCell(0).setCellValue(distributeRecords.getRealName());
	      row.createCell(1).setCellValue(distributeRecords.getTelphoneNumber());
//	      row.createCell(2).setCellValue(distributeRecords.getCreateDate());
//	      row.createCell(3).setCellValue(distributeRecords.getCreateTime());
       }
       return wb;
    }
	   
	
	

	private static void closeWorkbook(Workbook workbook) {
		if (workbook != null)
			try {
				workbook.close();
			} catch (IOException e) {
				MessageUtil.error(logger, "关闭WorkBook错误", e);
			}
	}

	public static void main(String[] args) {

	}

	public static HSSFWorkbook exportMap(List<Map<String, Object>> list) {
		 String[] excelHeader = {"姓名","电话","日期","时间"};
		   HSSFWorkbook wb = new HSSFWorkbook();
		   HSSFSheet sheet = wb.createSheet();
		   HSSFRow row = sheet.createRow((int) 0);
		   HSSFCellStyle style = wb.createCellStyle();
		   style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			  row = sheet.createRow(0);
		      row.createCell(0).setCellValue(excelHeader[0]);
		      row.createCell(1).setCellValue(excelHeader[1]);
		      row.createCell(2).setCellValue(excelHeader[2]);
		      row.createCell(3).setCellValue(excelHeader[3]);
	       for (int j = 1; j < list.size()+1; j++) {
		      row = sheet.createRow(j);
		      Map<String,Object> obj = list.get(j-1);
		      row.createCell(0).setCellValue(obj.get("name").toString());
		      row.createCell(1).setCellValue(obj.get("number").toString());
		      row.createCell(2).setCellValue(obj.get("createDate").toString());
		      row.createCell(3).setCellValue(obj.get("createTime").toString());
	       }
	       return wb;
	    }

}
