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

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

	private static Logger logger = LoggerFactory.getLogger(PublicUtil.class);

	/**
	 * 获取项目路径
	 * @return String 	项目路径（D:\workspace\Beginner\）
	 * @since 			1.0.0
	 */
	public static String getPorjectPath() {
		return System.getProperty("user.dir") + "\\";
	}

	/**
	 * 获取请求的Host
	 */
	public static String remoteHost() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getRemoteHost();
	}
	/**
	 * 获取请求的IP地址
	 */
	public static String remoteAddr() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getRemoteAddr();
	}

	/**
	 * 获取本机IP(项目启动成功后才能获取)
	 */
	public static String ip() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getLocalAddr();
	}

	/**
	 * 获取本机IP(可在项目启动时获取)
	 */
	public static String getIp() {
		InetAddress addr = null;
		String ip;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			logger.error("获取服务器IP地址发生异常", e);
		}
		ip = addr.getHostAddress().toString();
		return ip;
	}

	/**
	 * 获取本项目所在Web服务器的端口号(项目启动成功后才能获取)
	 */
	public static int port() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getLocalPort();
	}

	/**
	 * 获取本项目所在Web服务器的端口号(可在项目启动时获取)
	 */
	public static String getPort() {
		String result = "";
		try {
			MBeanServer mBeanServer = null;
			List<MBeanServer> mBeanServers = MBeanServerFactory.findMBeanServer(null);
			if (mBeanServers.size() > 0) {
				for (MBeanServer _mBeanServer : mBeanServers) {
					mBeanServer = _mBeanServer;
					break;
				}
			}
			if (mBeanServer == null) {
				throw new IllegalStateException("没有发现JVM中关联的MBeanServer.");
			}
			Set<ObjectName> objectNames = null;
			objectNames = mBeanServer.queryNames(new ObjectName("Catalina:type=Connector,*"), null);
			if (objectNames == null || objectNames.size() <= 0) {
				throw new IllegalStateException("没有发现JVM中关联的MBeanServer : " + mBeanServer.getDefaultDomain() + " 中的对象名称.");
			}
			for (ObjectName objectName : objectNames) {
				String protocol = (String) mBeanServer.getAttribute(objectName, "protocol");
				if (protocol.equals("HTTP/1.1")) {
					int port = (Integer) mBeanServer.getAttribute(objectName, "port");
					result = port + StringUtils.EMPTY;
				}
			}
		} catch (Exception e) {
			logger.error("获取WEB服务器HTTP/1.1端口号发生异常", e);
		}
		return result;
	}

	/**
	 * 图片访问路径
	 * 
	 * @param pathType 		图片类型 visit-访问；save-保存
	 * @param pathCategory 	图片类别，如：话题图片-topic、话题回复图片-reply、商家图片
	 * @return String		图片访问路径
	 */
	public static String getPicturePath(String pathType, String pathCategory) {
		String strResult = "";
		StringBuffer strBuf = new StringBuffer();
		if ("visit".equals(pathType)) {
		} else if ("save".equals(pathType)) {
			String projectPath = PublicUtil.getPorjectPath().replaceAll("\\\\", "/");
			projectPath = splitString(projectPath, "bin/");
			strBuf.append(projectPath);
			strBuf.append("webapps/ROOT/");
		}
		strResult = strBuf.toString();
		return strResult;
	}

	private static String splitString(String str, String param) {
		String result = str;
		if (str.contains(param)) {
			int start = str.indexOf(param);
			result = str.substring(0, start);
		}
		return result;
	}

	/**
	 * 获取classpath
	 * @return String D:/workspace/Beginner/src/main/webapp/WEB-INF/classes/../../
	 */
	public static String getClasspath() {
		String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../").replaceAll("file:/", "")
				.replaceAll("%20", " ").trim();
		if (path.indexOf(":") != 1) {
			path = File.separator + path;
		}
		return path;
	}

	/**
	 * 获取classpath
	 * @return String D:/workspace/Beginner/src/main/webapp/WEB-INF/classes/
	 */
	public static String getClassResources() {
		String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:/", "")
				.replaceAll("%20", " ").trim();
		if (path.indexOf(":") != 1) {
			path = File.separator + path;
		}
		return path;
	}

	/**
	 * 获取项目名(项目启动成功后才能获取)
	 */
	public static String path() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getContextPath();
	}

	/**
	 * 获取项目名(项目启动时获取)
	 */
	public static String appName() {
		String appName = System.getProperty("user.dir");
		return appName.substring(appName.lastIndexOf("\\") + 1, appName.length());
	}

	/**
	 * 获取项目访问路径
	 * @return String http://ip地址:端口号/项目名/
	 */
	public static String basePath() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		return basePath;
	}
}