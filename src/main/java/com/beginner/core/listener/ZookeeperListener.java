/*
 * Copyright 2015-9999 the original author or authors.
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
package com.beginner.core.listener;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.sf.json.JSONObject;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beginner.core.common.Const;
import com.beginner.core.utils.HttpUtil;
import com.beginner.core.utils.ProjectUtil;
import com.beginner.core.utils.PropertyUtil;

public class ZookeeperListener implements ServletContextListener {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String PATH = "Config";

	private static ZkClient zk;

	public void contextDestroyed(ServletContextEvent event) {
		zk.close();
		logger.info("Servlet容器销毁...");
	}

	/* 
	 * 获取本项目配置信息
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
		logger.info("Servlet容器初始化...");
		try {
			// 连接Zookeeper
			zk = new ZkClient(new ZkConnection(PropertyUtil.getStr("zookeeper.zkServers"), PropertyUtil.getInt("zookeeper.sessionTimeOut")));

			// 服务器IP地址
			String ip = ProjectUtil.getIp();
			// Web服务器端口号
			String port = ProjectUtil.getPort();
			// 项目名称
			String name = ProjectUtil.appName();

			// 获取zNode节点在目录树的路径
			String path = getPath(name, ip, port);
			// 请求参数
			String param = new String();
			// 请求URL
			String url = StringUtils.EMPTY;
			if (zk.exists(path))
				zk.readData(path);
			// 封装参数
			url = encapsulation(ip, port, url, param);

			// 调用配置中心API查询本系统配置信息
			String result = HttpUtil.post(url, param, 1000);

			if (StringUtils.isNotBlank(result)) {
				// 解析返回JSON，并给系统的配置项重新赋值
				setProperties(result);
				// 监听目录树对应zNode节点数据变更事件
				addListener(path);
			}
		} catch (Exception e) {
			if (null != zk)
				zk.close();
			logger.error("ZookeeperListener发生异常", e);
		}
	}

	/**
	 * 监听目录树对应zNode节点数据变更事件
	 */
	private void addListener(String path) {
		if (zk.exists(path)) {
			zk.subscribeDataChanges(path, new IZkDataListener() {

				@Override
				public void handleDataChange(String dataPath, Object data) throws Exception {
					logger.info("/zk的data变更了.");
					logger.info("变更后的值：" + data);
					logger.info("模拟调用URL地址");
					logger.info("返回数据初始化对象");
				}
				@Override
				public void handleDataDeleted(String dataPath) throws Exception {
					logger.info("/zk的data被删除.");
				}
			});
		}
	}

	/**
	 * 解析返回JSON，并给系统的配置项重新赋值
	 */
	private void setProperties(String result) throws ClassNotFoundException, IllegalAccessException {
		JSONObject json = JSONObject.fromObject(result);
		String className = json.getString("resourcesClass");
		if (json.containsKey("resourcesClass")) {
			Class<?> resourcesClass = Class.forName(className);
			Field[] fields = resourcesClass.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				field.setAccessible(true);
				String name = field.getName();
				Class<?> type = field.getType().getClass();
				if (json.containsKey(name)) {
					if (type.equals(String.class)) {
						field.set(resourcesClass, json.get(name));
					}
				}
			}
		}
	}

	/**
	 * 封装请求参数
	 */
	private String encapsulation(String ip, String port, String url, String param) {

		// 封装请求参数
		Map<String, Object> map = new HashMap<String, Object>();
		// IP地址
		map.put("ip", ip);
		// 端口号
		map.put("port", port);
		// 组ID
		map.put("groupId", PropertyUtil.getStr("app.groupId"));
		// 项目ID
		map.put("artifactId", PropertyUtil.getStr("app.artifactId"));
		// 版本号
		String version = PropertyUtil.getStr("app.version");
		// 获取指定的配置信息（请求中不包含version的话将获取最新配置信息）
		if (StringUtils.isNotBlank(version))
			map.put("version", version);

		param = JSONObject.fromObject(map).toString();

		// zNode节点信息中的URL为空则读取配置文件中的默认URL地址
		if (StringUtils.isBlank(url))
			url = PropertyUtil.getStr("default.url");

		return url;
	}

	/**
	 * 获取zNode节点在目录树的路径
	 */
	private String getPath(String name, String ip, String port) {
		return Const.SLASH + PATH + Const.SLASH + name + ip + port;
	}
}
