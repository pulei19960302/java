package com.qim.loan.util.wxpay;

import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class WXPayRequestUtil {

	public static final String WXPAY_BASE_URL = "api.mch.weixin.qq.com";
	// 下单接口
	public static final String UNIFIEDORDER_URL_SUFFIX = "/pay/unifiedorder";
	// 查询订单
	public static final String ORDERQUERY_URL_SUFFIX = "/pay/orderquery";
	//
	public static final String REVERSE_URL_SUFFIX = "/secapi/pay/reverse";
	// 关闭订单
	public static final String CLOSEORDER_URL_SUFFIX = "/pay/closeorder";
	// 退款 原路将钱退回买家
	public static final String REFUND_URL_SUFFIX = "/secapi/pay/refund";
	// 查询退款
	public static final String REFUNDQUERY_URL_SUFFIX = "/pay/refundquery";
	// 下载对账单
	public static final String DOWNLOADBILL_URL_SUFFIX = "/pay/downloadbill";

	public static final String WXPAYSDK_VERSION = "WXPaySDK/3.0.9";
	public static final String USER_AGENT = WXPAYSDK_VERSION + " (" + System.getProperty("os.arch") + " "
			+ System.getProperty("os.name") + " " + System.getProperty("os.version") + ") Java/"
			+ System.getProperty("java.version") + " HttpClient/"
			+ HttpClient.class.getPackage().getImplementationVersion();

	public static Map<String, String> httpRequest(Map<String, String> data, int connectTimeoutMs, int readTimeoutMs,
			String suffixUrl) throws Exception {
		BasicHttpClientConnectionManager connManager;
		connManager = new BasicHttpClientConnectionManager(RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory())
				.register("https", SSLConnectionSocketFactory.getSocketFactory()).build(), null, null, null);
		HttpClient httpClient = HttpClientBuilder.create().setConnectionManager(connManager).build();

		HttpPost httpPost = new HttpPost(WXPAY_BASE_URL + suffixUrl);

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readTimeoutMs)
				.setConnectTimeout(connectTimeoutMs).build();
		httpPost.setConfig(requestConfig);

		StringEntity postEntity = new StringEntity(WXPayUtil.generateSignedXml(data, WXPayConstructionData.APP_KEY),
				"UTF-8");
		httpPost.addHeader("Content-Type", "text/xml");
		httpPost.addHeader("User-Agent", USER_AGENT);
		httpPost.setEntity(postEntity);

		HttpResponse httpResponse = httpClient.execute(httpPost);
		HttpEntity httpEntity = httpResponse.getEntity();
		String string = EntityUtils.toString(httpEntity, "UTF-8");
		Map<String, String> result = WXPayUtil.xmlToMap(string);
		return result;
	}

	public static void main(String[] args) {
	}
}
