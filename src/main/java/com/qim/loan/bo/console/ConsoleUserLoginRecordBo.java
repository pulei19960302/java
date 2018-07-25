package com.qim.loan.bo.console;  

import com.qim.loan.entity.console.ConsoleUserLoginRecord;

  
public class ConsoleUserLoginRecordBo{  

	private ConsoleUserLoginRecord consoleUserLoginRecord;
	
	public ConsoleUserLoginRecord getConsoleUserLoginRecord() {
		return consoleUserLoginRecord;
	}

	public void setConsoleUserLoginRecord(ConsoleUserLoginRecord consoleUserLoginRecord) {
		this.consoleUserLoginRecord = consoleUserLoginRecord;
	}

	public ConsoleUserLoginRecordBo(){
		consoleUserLoginRecord=new ConsoleUserLoginRecord();
	}

} 