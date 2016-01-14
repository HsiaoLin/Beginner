package com.beginner.base.plugin.listener;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;

import com.beginner.base.utils.Logger;

/**
 * <b>类名称：</b>Log4jListener<br/>
 * <b>类描述：</b>Servlet容器初始化时加载Log4j外部扩展的配置文件，需要时在web.xml中添加此监听并去掉org.springframework.web.util.Log4jConfigListener此监听。<br/>
 * <b>创建人：</b>尹枭凌工作室-Hsiao Lin<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2015年10月28日 上午11:20:35<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class Log4jListener implements ServletContextListener {

	Logger logger = Logger.getLogger(this.getClass());

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
			while (em.hasMoreElements()){
				String key = em.nextElement();
				log4j.setProperty(key, external.getProperty(key));
			}

			PropertyConfigurator.configure(log4j);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
