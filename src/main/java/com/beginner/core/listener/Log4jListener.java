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

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>类名称：</b>Log4jListener<br/>
 * <b>类描述：</b>Servlet容器初始化时加载Log4j外部扩展的配置文件，需要时在web.xml中添加此监听并去掉org.springframework.web.util.Log4jConfigListener此监听。<br/>
 * <b>创建人：</b>Hsiao Lin Studio<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class Log4jListener implements ServletContextListener {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public void contextDestroyed(ServletContextEvent event) {
		logger.info("ServletContext Destroyed...");
	}

	@SuppressWarnings("unchecked")
	public void contextInitialized(ServletContextEvent event) {
		logger.info("ServletContext Initialized...");
		try {
			Properties log4j = new Properties();
			log4j.load(this.getClass().getClassLoader().getResourceAsStream("log4j.properties"));

			Properties external = new Properties();
			external.load(new FileInputStream("/data/resources/log4j-external.properties"));

			Enumeration<String> em = (Enumeration<String>) external.propertyNames();
			while (em.hasMoreElements()) {
				String key = em.nextElement();
				log4j.setProperty(key, external.getProperty(key));
			}

			PropertyConfigurator.configure(log4j);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
