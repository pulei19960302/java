package com.qim.loan.util.setting;

public class CacheBaseSetting {
	// cache库
	private Integer library;
	// cache时间(分钟)
	private Integer time;

	public CacheBaseSetting() {}

	public CacheBaseSetting(Integer library, Integer time) {
		super();
		this.setLibrary(library);
		this.setTime(time*60);
	}

	public Integer getLibrary() {
		return library;
	}

	public void setLibrary(Integer library) {
		this.library = library;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time*60;
	}
}
