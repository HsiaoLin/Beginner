package com.beginner.base.utils;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.StringUtils;

public class ResourcesUtil {

	/**
	 * 读取配置文件
	 * @param fileName
	 * @return String
	 */
	public static Configuration getConfig(String fileName) {
		Configuration config = null;
		try {
			config = new PropertiesConfiguration(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return config;
	}

	public static String getProperties(String key, String fileName) {
		String properties = StringUtils.EMPTY;
		try {
			properties = getConfig(fileName).getString(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//默认classpath路径下查找
		System.out.println(getProperties("type", "beginner.properties"));
	}
}
