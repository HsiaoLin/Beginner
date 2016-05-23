package com.beginner.core.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

public class AESTool {

	protected static final Logger logger = LoggerFactory.getLogger(AESTool.class);

	/**
	 * The initialization vector (IV).
	 */
	private static byte[] initVector = {0x32, 0x27, 0x36, 0x25, 0x34, 0x23, 0x32, 0x21, 0x38, 0x47, 0x56, 0x15, 0x23, 0x13, 0x42, 0x61};

	// 加密
	public static String encrypt(String sSrc, String sKey) throws Exception {
		if (null == sKey) {
			logger.error("Key为null");
			return null;
		}
		// 判断Key是否为16位
		if (sKey.length() != 16) {
			logger.error("Key长度不是16位");
			return null;
		}

		byte[] raw = sKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		// "算法/模式/补码方式"
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		IvParameterSpec iv = new IvParameterSpec(initVector);
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

		byte[] encrypted = cipher.doFinal(sSrc.getBytes("UTF-8"));

		return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
	}

	// 解密
	public static String decrypt(String sSrc, String sKey) throws Exception {
		try {
			// 判断Key是否正确
			if (sKey == null) {
				logger.error("Key为null");
				return null;
			}
			// 判断Key是否为16位
			if (sKey.length() != 16) {
				logger.error("Key长度不是16位");
				return null;
			}
			byte[] raw = sKey.getBytes("UTF-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(initVector);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, "UTF-8");
			return originalString;
		} catch (Exception e) {
			logger.error("解密异常", e);
			return null;
		}
	}

	public static void main(String[] args) throws Exception {
		/*
		 * 加密用的Key 可以用26个字母和数字组成，最好不要用保留字符，虽然不会错，至于怎么裁决，个人看情况而定
		 * 此处使用AES-128-CBC加密模式，key需要为16位。
		 */
		String cKey = UUIDUtil.get32UUID().substring(1, 17).toUpperCase();
		System.out.println(cKey);
		// 需要加密的字串
		String cSrc = "user＝15063142270,pwd＝110120吾问无为谓呜呜呜呜呜呜呜呜呜呜呜呜呜呜呜*&Y^*&^^&%^&%%$%*（）()_+__^$#@@@#%%$@!#~#$^^\\/";
		// 加密
		long lStart = System.currentTimeMillis();
		String enString = AESTool.encrypt(cSrc, cKey);
		System.out.println("加密后的字串是：" + enString);

		long lUseTime = System.currentTimeMillis() - lStart;
		System.out.println("加密耗时：" + lUseTime + "毫秒");
		// 解密
		lStart = System.currentTimeMillis();
		String DeString = AESTool.decrypt(enString, cKey);
		System.out.println("加密前的字串是：" + cSrc);
		System.out.println("解密后的字串是：" + DeString);
		lUseTime = System.currentTimeMillis() - lStart;
		System.out.println("解密耗时：" + lUseTime + "毫秒");
	}
}