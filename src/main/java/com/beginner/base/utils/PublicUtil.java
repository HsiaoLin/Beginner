/*
 * Copyright 2015-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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