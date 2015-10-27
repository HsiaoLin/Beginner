package com.java.beginner.common;

public class AlipayConfig {

	/**字符编码格式 目前支持 gbk 或 utf-8*/
	public static String input_charset = "utf-8";

	/**签名方式 不需修改*/
	public static String sign_type = "MD5";

	/**接口名称*/
	public static String alipay_service = "create_direct_pay_by_user";

	/**支付类型。1：商品购买*/
	public static String alipay_payment_type = "1";

	/**支付方式。directPay:余额支付,creditPay：信用支付*/
	public static String alipay_paymethod = "directPay";

	/**扫码支付方式。2：订单码-跳转模式*/
	public static String alipay_qr_pay_mode = "2";

}
