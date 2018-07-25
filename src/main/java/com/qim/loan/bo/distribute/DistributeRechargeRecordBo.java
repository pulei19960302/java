package com.qim.loan.bo.distribute;  

import com.qim.loan.entity.distribute.DistributeRechargeRecord;

  
public class DistributeRechargeRecordBo{  

	private DistributeRechargeRecord distributeRechargeRecord;
	
	public DistributeRechargeRecord getDistributeRechargeRecord() {
		return distributeRechargeRecord;
	}

	public void setDistributeRechargeRecord(DistributeRechargeRecord distributeRechargeRecord) {
		this.distributeRechargeRecord = distributeRechargeRecord;
	}

	public DistributeRechargeRecordBo(){
		distributeRechargeRecord=new DistributeRechargeRecord();
	}

} 