package com.qim.loan.bo.distribute;  

import com.qim.loan.entity.distribute.DistributeCurrencyRecord;

  
public class DistributeCurrencyRecordBo{  

	private DistributeCurrencyRecord distributeCurrencyRecord;
	
	public DistributeCurrencyRecord getDistributeCurrencyRecord() {
		return distributeCurrencyRecord;
	}

	public void setDistributeCurrencyRecord(DistributeCurrencyRecord distributeCurrencyRecord) {
		this.distributeCurrencyRecord = distributeCurrencyRecord;
	}

	public DistributeCurrencyRecordBo(){
		distributeCurrencyRecord=new DistributeCurrencyRecord();
	}

} 