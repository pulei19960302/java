package com.qim.loan.util.excel;

public class PhoneName {
	//姓名
	private String name;
	//电话号码
	private String phone;

	public PhoneName(String name, String phone) {
		super();
		this.name = name;
		this.phone = phone;
	}

	public PhoneName() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
