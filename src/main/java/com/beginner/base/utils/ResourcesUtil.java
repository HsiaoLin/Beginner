package com.beginner.base.utils;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ResourcesUtil {

	/**
	 * 读取配置文件
	 * @param strfile
	 * @return
	 */
	public static Configuration getConfig(String strfile) {
		Configuration config = null;
		try {
			PropertiesConfiguration factory = new PropertiesConfiguration(strfile);
			config = factory.getConfiguration();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return config;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//ConfigHelper ch = new ConfigHelper();
		//ch.test();
		try {
			Configuration config = getConfig("config.xml");
			String backColor = config.getString("colors.background");
			System.out.println("color: " + backColor);
			config = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
