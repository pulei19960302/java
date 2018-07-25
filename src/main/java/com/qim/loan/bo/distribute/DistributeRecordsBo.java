package com.qim.loan.bo.distribute;  

import com.qim.loan.entity.distribute.DistributeRecords;

  
public class DistributeRecordsBo{  

	private DistributeRecords distributeRecords;
	
	public DistributeRecords getDistributeRecords() {
		return distributeRecords;
	}

	public void setDistributeRecords(DistributeRecords distributeRecords) {
		this.distributeRecords = distributeRecords;
	}

	public DistributeRecordsBo(){
		distributeRecords=new DistributeRecords();
	}

} 