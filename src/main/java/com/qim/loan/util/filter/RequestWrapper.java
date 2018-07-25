package com.qim.loan.util.filter;

import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
/**
     * 类名:RequestWrapper
     * 描述:	 
     * 创建者:冯子文
     * 创建时间: 2016年5月17日 上午11:48:18 
	 * 更新者:冯子文   
	 * 更新时间: 2016年5月17日 上午11:48:18
 */
public class RequestWrapper extends HttpServletRequestWrapper {

	private Map<String, String[]> params;

	public RequestWrapper(HttpServletRequest request,Map<String, String[]> newParams) {
		super(request);
		this.params = newParams;
		newParameterMap(request);
	}

	@Override
	public String getParameter(String name) {
		String result = "";		
		Object v = params.get(name);
		if (v == null) {
			result = null;
		} else if (v instanceof String[]) {
			String[] strArr = (String[]) v;
			if (strArr.length > 0) {
				result =  strArr[0];
			} else {
				result = null;
			}
		} else if (v instanceof String) {
			result = (String) v;
		} else {
			result =  v.toString();
		}
		return result;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		return params;
	}

	@Override
	public Enumeration<String> getParameterNames() {
		return new Vector<String>(params.keySet()).elements();
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] result = null;
		Object v = params.get(name);
		if (v == null) {
			result =  null;
		} else if (v instanceof String[]) {
			result =  (String[]) v;
		} else if (v instanceof String) {
			result =  new String[] { (String) v };
		} else {
			result =  new String[] { v.toString() };
		}
		
		return result;
	}

	private void newParameterMap(HttpServletRequest req) {
		String queryString = req.getQueryString();
		if (queryString != null && queryString.trim().length() > 0) {
			String[] params = queryString.split("&");
			for (int i = 0; i < params.length; i++) {
				int splitIndex = params[i].indexOf("=");
				if (splitIndex == -1) {
					continue;
				}
				String key = params[i].substring(0, splitIndex);
				if (!this.params.containsKey(key)) {
					if (splitIndex < params[i].length()) {
						String value = params[i].substring(splitIndex + 1);
						this.params.put(key, new String[] { value });
					}
				}
			}
		}
	}

}
