package com.qim.loan.util.paramter;

import java.util.List;


public class Pager {
	protected Integer currentPage; // 当前页
	protected Integer pageSize; // 每页记录数
	protected Long total; // 总记录数
	protected Long totalPage; // 总页数
	protected List<?> rows; // 当前页记录List形式
	private Boolean useFlag;
	private Boolean checkFlag;
	public Pager(){
		this.pageSize=10;
		this.currentPage=1;
	}
	public Pager(RequestPager requestPager){
		super();
		this.pageSize=requestPager.getPageSize();
		this.currentPage=requestPager.getCurrentPage();
	}	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getTotalPage() {
		if(this.totalPage!=null && this.totalPage>0)
			return totalPage;
		return (long) Math.ceil(this.total/this.pageSize);
	}
	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	public Boolean getUseFlag() {
		return useFlag;
	}
	public void setUseFlag(Boolean useFlag) {
		this.useFlag = useFlag;
	}
	public Boolean getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(Boolean checkFlag) {
		this.checkFlag = checkFlag;
	}
}
