package com.qim.loan.bo.distribute;  

import com.qim.loan.entity.distribute.DistributeUserLoginRecord;

  
public class DistributeUserLoginRecordBo{  

	private DistributeUserLoginRecord distributeUserLoginRecord;
	
	public DistributeUserLoginRecord getDistributeUserLoginRecord() {
		return distributeUserLoginRecord;
	}

	public void setDistributeUserLoginRecord(DistributeUserLoginRecord distributeUserLoginRecord) {
		this.distributeUserLoginRecord = distributeUserLoginRecord;
	}

	public DistributeUserLoginRecordBo(){
		distributeUserLoginRecord=new DistributeUserLoginRecord();
	}

} 