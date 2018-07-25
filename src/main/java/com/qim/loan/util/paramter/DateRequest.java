package com.qim.loan.util.paramter;

import com.qim.loan.util.common.StringUtil;

public class DateRequest {

	private Integer createStartDate;
	private Integer createEndDate;
	private Integer startDate;
	private Integer endDate;
	private Integer createStartTime;
	private Integer createEndTime;
	private Integer startTime;
	private Integer endTime;
	private String orderBy; 

	public Integer getCreateStartDate() {
		return createStartDate;
	}

	public void setCreateStartDate(Integer createStartDate) {
		this.createStartDate = createStartDate;
	}

	public Integer getCreateEndDate() {
		return createEndDate;
	}

	public void setCreateEndDate(Integer createEndDate) {
		this.createEndDate = createEndDate;
	}

	public Integer getStartDate() {
		return startDate;
	}

	public void setStartDate(Integer startDate) {
		this.startDate = startDate;
	}

	public Integer getEndDate() {
		return endDate;
	}

	public void setEndDate(Integer endDate) {
		this.endDate = endDate;
	}

	public Integer getCreateStartTime() {
		return createStartTime;
	}

	public void setCreateStartTime(Integer createStartTime) {
		this.createStartTime = createStartTime;
	}

	public Integer getCreateEndTime() {
		return createEndTime;
	}

	public void setCreateEndTime(Integer createEndTime) {
		this.createEndTime = createEndTime;
	}

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Boolean getWhere(){
		if(createStartDate!=null || createEndDate!=null || startDate!=null || endDate!=null || createStartTime!=null || createEndTime!=null || startTime!=null || endTime!=null)
			return true;
		return false;
	}
	
	public Boolean getOrderBys(){
		if(StringUtil.isNotNull(orderBy))
			return true;
		return false;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
