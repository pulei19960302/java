package com.qim.loan.util.paramter;

import java.io.Serializable;

public class RequestPager implements Serializable {

	private static final long serialVersionUID = -5799853186223716659L;
	private Integer currentPage = 1;
	private Integer pageSize = 10;

	public RequestPager(){}
	
	public RequestPager(Integer pageSize){
		super();
		this.setPageSize(pageSize);
	}
	public RequestPager(Integer currentPage,Integer pageSize){
		super();
		this.setCurrentPage(currentPage);
		this.setPageSize(pageSize);
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

	public static RequestPager setPager(Integer  pageSize){
		return new RequestPager(1,pageSize);
	}
	
	
	
	
	@Override
	public String toString() {
		return "RequestPager{" + "currentPage=" + currentPage + ", pageSize=" + pageSize + '}';
	}

}
