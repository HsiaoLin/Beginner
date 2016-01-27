package com.beginner.base.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 读取.properties文件信息
 */
public class PropertiesUtil {
	
	/**
	 * 获取.properties文件key对应的value
	 * @param key 		key的值
	 * @param pathName 	classpath:文件路径+文件名称（不加后缀.properties）如：classpath:common/message.properties写成：common/message
	 * @return String 	返回key值对应的value
	 */
	public static String getProperties(String key, String pathName) {
		Locale locale = Locale.getDefault();
		ResourceBundle resource = ResourceBundle.getBundle(pathName, locale);
		return resource.getString(key);
	}
}
