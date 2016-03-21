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
package com.beginner.base.plugin.listener;

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

import com.beginner.base.utils.HttpUtil;
import com.beginner.base.utils.PropertyUtil;
import com.beginner.base.utils.PublicUtil;

public class ZookeeperListener implements ServletContextListener {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	private static ZkClient zk;

	public void contextDestroyed(ServletContextEvent event) {
		logger.info("Servlet容器销毁...");
	}

	/* 
	 * 获取本项目配置信息
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
		logger.info("Servlet容器初始化...");
		try {
			// 连接到Zookeeper并读取目录树对应配置URL的地址，封装请求参数
			Map<String, Object> param = new HashMap<String, Object>();
			String url = connectedZk(param);

			// 获取默认Version的配置信息，如果Version为空则返回最新的配置信息
			if (StringUtils.isBlank(url))
				url = PropertyUtil.getProperty("config.default", "beginner");

			// 调用配置中心API查询本系统配置信息
			String result = HttpUtil.post(url, JSONObject.fromObject(param).toString(), 1000);

			// 解析返回JSON，并给系统的配置项重新赋值
			setProperties(result);

			// 监听目录树对应zNode节点数据变更事件
			addListener();

		} catch (Exception e) {
			logger.error("ZookeeperListener发生异常", e);
		}
	}

	/**
	 * 监听目录树对应zNode节点数据变更事件
	 */
	private void addListener() {
		zk.subscribeDataChanges("/zk", new IZkDataListener() {

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
	 * 连接到Zookeeper并读取目录树对应配置URL的地址
	 */
	private String connectedZk(Map<String, Object> param) {
		String zkServers = PropertyUtil.getProperty("zookeeper.zkServers", "beginner");
		String sessionTimeOut = PropertyUtil.getProperty("zookeeper.zkServers", "beginner");

		zk = new ZkClient(new ZkConnection(zkServers, Integer.parseInt(sessionTimeOut)));
		logger.info("已经连接到Zookeeper({})...", zkServers);

		String ip = PublicUtil.getIp();
		String port = PublicUtil.getPort();
		String path = "/Config/" + PublicUtil.appName() + ip + port;
		String url = zk.readData(path);
		logger.info("zNode节点路径:{}", path);
		logger.info("zNode节点数据:{}", url);

		param.put("groupId", PropertyUtil.getProperty("groupId", "beginner"));
		param.put("artifactId", PropertyUtil.getProperty("artifactId", "beginner"));
		param.put("version", PropertyUtil.getProperty("version", "beginner"));
		param.put("ip", ip);
		param.put("port", port);

		return url;
	}
}
