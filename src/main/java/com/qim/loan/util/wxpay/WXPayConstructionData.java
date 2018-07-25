package com.qim.loan.util.wxpay;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.qim.loan.util.common.StringUtil;

/**
 * 
 * 类名:用来构造发请求的参数 描述: 创建者:LGZ 创建时间: 2018年7月19日 下午3:25:19 更新者:LGZ 更新时间: 2018年7月19日
 * 下午3:25:19
 */
public class WXPayConstructionData {

	public static final String APPID = "";
	// 商户号
	public static final String MCH_ID = "";

	public static final String APP_KEY = "";
	//

	// sign签名秘钥需要拼接 nonce_str随机字符串 body商品描述 out_trade_no商户订单号 total_fee总金额
	// spbill_create_ip传用户IP notify_url通知地址 trade_type交易类型 scene_info场景信息
	public static final String NONCE_STR = "/secapi/pay/reverse";
	// 关闭订单
	public static final String SIGN = "/pay/closeorder";
	// 退款 原路将钱退回买家
	public static final String REFUND_URL_SUFFIX = "/secapi/pay/refund";

	/**
	 * 
	 * 方法名: 功能描述: productDescription-商品描述 outTradeNo-商家订单号(内部)
	 * totalMoney-总金额(/分) userIP-用户IP地址 callBackUrl-回调地址 tradeType-交易类型(h5传
	 * MWEB) sceneInfo-场景(1-IOS 2-Android 3-wap网站) 创建者:LGZ 创建时间: 2018年7月20日
	 * 上午10:05:31 更新者:LGZ 更新时间: 2018年7月20日 上午10:05:31
	 * 
	 * @return
	 */
	public static Map<String, String> placeAnOrder(String productDescription, String outTradeNo, int totalMoney,
			String userIP, String callBackUrl, String tradeType, int sceneInfo) {

		Map<String, String> paramsMap = new HashMap<>();
		// APPID
		paramsMap.put("appid", APPID);
		// 商户号
		paramsMap.put("mch_id", MCH_ID);
		// 随机字符串，不长于32位。推荐随机数生成算法
		paramsMap.put("nonce_str", UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32));
		// 商品描述
		paramsMap.put("body", productDescription);
		// 商户的订单号
		paramsMap.put("out_trade_no", outTradeNo);
		// total_fee 总金额
		paramsMap.put("total_fee", "" + totalMoney);
		// 终端IP
		paramsMap.put("spbill_create_ip", userIP);
		// 异步通知的回调地址
		paramsMap.put("notify_url", callBackUrl);
		// 交易类型
		paramsMap.put("trade_type", tradeType);
		// 应用场景
		switch (sceneInfo) {
		case 1:
			paramsMap.put("scene_info",
					"{\"h5_info\": {\"type\":\"IOS\",\"app_name\": \"瞬闪银\",\"bundle_id\": \"com.tencent.tmgp.sgame\"}}");
			break;
		case 2:
			paramsMap.put("scene_info",
					"{\"h5_info\": {\"type\":\"Android\",\"app_name\": \"瞬闪银\",\"package_name\": \"com.tencent.tmgp.sgame\"}}");
			break;
		case 3:
			paramsMap.put("scene_info",
					"{\"h5_info\": {\"type\":\"Wap\",\"wap_url\": \"瞬闪银\",\"wap_name\": \"com.tencent.tmgp.sgame\"}}");
			break;
		}
		return paramsMap;

	}

	/**
	 * 
	 * 方法名:通过商家订单号查询
	 * 功能描述: isUserOutTradeNo-是否使用商家订单号 true 使用自家订单号 false
	 * 使用微信的订单号 orderNum--订单号 创建者:LGZ 创建时间: 2018年7月20日 上午10:18:31 更新者:LGZ 更新时间:
	 * 2018年7月20日 上午10:18:31
	 */
	public static Map<String, String> selectOrder(Boolean isUserOutTradeNo, String orderNum) {

		Map<String, String> paramsMap = new HashMap<>();
		// APPID
		paramsMap.put("appid", APPID);
		// 商户号
		paramsMap.put("mch_id", MCH_ID);
		// 随机字符串，不长于32位。推荐随机数生成算法
		paramsMap.put("nonce_str", UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32));
		// 商户的订单号
		if (isUserOutTradeNo) {
			paramsMap.put("out_trade_no", orderNum);
		} else {
			paramsMap.put("transaction_id", orderNum);
		}
		return paramsMap;

	}

	/**
	 * 
	     * 方法名: 关闭订单   
	     * 功能描述:	 outTradeNo 商家订单号
	     * 创建者:LGZ
	     * 创建时间: 2018年7月20日 上午10:29:28 
		 * 更新者:LGZ  
		 * 更新时间: 2018年7月20日 上午10:29:28
	 */
	public static Map<String, String> closeOrder(String outTradeNo) {

		Map<String, String> paramsMap = new HashMap<>();
		// APPID
		paramsMap.put("appid", APPID);
		// 商户号
		paramsMap.put("mch_id", MCH_ID);
		// 随机字符串，不长于32位。推荐随机数生成算法
		paramsMap.put("nonce_str", UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32));
		// 商户的订单号
		paramsMap.put("out_trade_no", outTradeNo);
		return paramsMap;

	}

	/**
	 * 
	     * 方法名:申请退款
	     * 功能描述:isUserOutTradeNo-是否使用商家订单号 true 使用自家订单号 false   orderNum订单号  	 outRefundNo商家的退款单号   totalMoney总金额  refundMoney退款金额     notifyUrl退款通知接口(可不传)
	     * 创建者:LGZ
	     * 创建时间: 2018年7月20日 上午10:31:20 
		 * 更新者:LGZ  
		 * 更新时间: 2018年7月20日 上午10:31:20
	 */
	public static Map<String, String> applyForRefund(Boolean isUserOutTradeNo, String orderNum,String outRefundNo,int totalMoney,int refundMoney,String notifyUrl) {

		Map<String, String> paramsMap = new HashMap<>();
		// APPID
		paramsMap.put("appid", APPID);
		// 商户号
		paramsMap.put("mch_id", MCH_ID);
		// 随机字符串，不长于32位。推荐随机数生成算法
		paramsMap.put("nonce_str", UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32));
		//商家退款的单号
		paramsMap.put("out_refund_no", outRefundNo);
		//total_fee 订单金额 /分
		paramsMap.put("total_fee", ""+totalMoney);
		//refund_fee
		paramsMap.put("refund_fee", ""+refundMoney);
		// 商户的订单号
		if (isUserOutTradeNo) {
			paramsMap.put("out_trade_no", orderNum);
		} else {
			paramsMap.put("transaction_id", orderNum);
		}
		//异步同步退款结果
		if(StringUtil.isNotNull(notifyUrl)){
			paramsMap.put("notify_url", notifyUrl);
		}
		return paramsMap;
	}
	
	/**
	 * 
	     * 方法名:退款查询
	     * 功能描述:orderNumType订单号类型 (1微信订单号 2商户订单号 3商户退款订单号 4微信退款单号)	 
	     * 创建者:LGZ
	     * 创建时间: 2018年7月20日 上午10:36:15 
		 * 更新者:LGZ  
		 * 更新时间: 2018年7月20日 上午10:36:15
	 */
	public static Map<String, String> refundQuery(int orderNumType, String orderNum) {
		Map<String, String> paramsMap = new HashMap<>();
		// APPID
		paramsMap.put("appid", APPID);
		// 商户号
		paramsMap.put("mch_id", MCH_ID);
		// 随机字符串，不长于32位。推荐随机数生成算法
		paramsMap.put("nonce_str", UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32));
		switch (orderNumType) {
		case 1:
			paramsMap.put("transaction_id","");
			break;
		case 2:
			paramsMap.put("out_trade_no","");
			break;
		case 3:
			paramsMap.put("out_refund_no","");
			break;
		case 4:
			paramsMap.put("refund_id","");
			break;
		}
		return paramsMap;
	}
	public static void main(String[] args) {
		Map<String, String> placeAnOrder = placeAnOrder("这是一个商品", "SDSDDEW", 5000, "192.168.1.1", "http://sadasdasda",
				"MWEB", 2);
	}
}
