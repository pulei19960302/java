package com.qim.loan.util.wxpay;

import java.util.Map;

public class WxPayTest {
	
	
	public static void main(String[] args) throws Exception {
		Map<String, String> selectOrder = WXPayConstructionData.selectOrder(true, "asdasd");
		Map<String, String> httpRequest = WXPayRequestUtil.httpRequest(selectOrder, 500, 500, WXPayRequestUtil.UNIFIEDORDER_URL_SUFFIX);
	}
}
