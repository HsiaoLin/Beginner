package com.beginner.base.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.lang3.StringUtils;

/**
* <b>类名称：</b>PublicUtil<br/>
* <b>类描述：</b>公共工具类<br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
public class PublicUtil {

	/**
	 * 获取项目路径
	 * @return String 	项目路径（D:\workspace\Beginner\）
	 * @since 			1.0.0
	 */
	public static String getPorjectPath() {
		return System.getProperty("user.dir") + "\\";
	}

	/**
	 * 获取本机IP地址
	 * @return String 	IP地址（192.168.6.240）
	 * @since 			1.0.0
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