package com.qim.loan.util.common;

public class TypeUtil {
	//JDBC
	public static String MysqlToJdbc(String type) {
		if (type.indexOf("(") > 0) 
			type = type.substring(0, type.indexOf("("));
		type = type.toLowerCase();
		String tempType = "";
		switch (type) {
		case "bigint":
		case "int":
			tempType = "BIGINT";
			break;
		case "varchar":
		case "text":
			tempType = "VARCHAR";
			break;
		case "decimal":
			tempType = "DECIMAL";
			break;
		case "date":	
		case "datetime":
			tempType = "TIMESTAMP";
			break;
		case "tiny":
			tempType = "BIT";
			break;
		case "tinyint":
			tempType = "TINYINT";
			break;	
		case "bit":
			tempType = "BOOLEAN";
			break;
		default:
			tempType = "VARCHAR";
		}
		return tempType;
	}
	
	//mysql
	public static String mysqlToEntity(String type) {
		if (type.indexOf("(") > 0) 
			type = type.substring(0, type.indexOf("("));
		type = type.toLowerCase();
		String tempType = "";
		switch (type) {
		case "decimal":
			tempType = "BigDecimal";
			break;
		case "float":
			tempType = "Float";
			break;
		case "double":
			tempType = "Double";
			break;
		case "int":	
			tempType = "Integer";
			break;
		case "id":
		case "integer":
			tempType = "Long";
			break;
		case "bigint":
			tempType = "BigInteger";
			break;		
		case "tinyint":
		case "boolean":
		case "smallint":
		case "mediumint":
			tempType = "Integer";
			break;
		case "bit":
			tempType = "Boolean";
			break;
		case "varchar":
		case "char":
		case "text":	
			tempType = "String";
			break;
		case "blob":
			//tempType = "byte\\[\\]";
			tempType = "String";
			break;
		case "date":
		case "year":
			tempType = "Date";
			break;
		case "time":
			tempType = "Time";
			break;
		case "datetime":
		case "timestamp":
			tempType = "Date";
			break;
		default:
			tempType = "String";			
		}
		return tempType;
	}
}
