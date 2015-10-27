package com.java.beginner.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.lang3.StringUtils;

public class PublicUtil {

	public static void main(String[] args) {
		System.out.println("本机的ip=" + PublicUtil.getIp());
		System.out.println("項目路徑=" + PublicUtil.getPorjectPath());
	}

	public static String getPorjectPath() {
		return System.getProperty("user.dir") + "\\";
	}

	/**
	* getIp(方法描述：获取本机ip) <br />
	* (方法适用条件描述： – 可选)
	* @return
	* String
	* @exception
	* @since  1.0.0
	*/
	public static String getIp() {
		String ip = StringUtils.EMPTY;
		try {
			InetAddress inet = InetAddress.getLocalHost();
			ip = inet.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ip;
	}

}