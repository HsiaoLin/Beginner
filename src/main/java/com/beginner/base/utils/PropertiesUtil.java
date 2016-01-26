package com.beginner.base.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 读取properties文件信息
 */
public class PropertiesUtil {
	
	/**
	 * 获取key值对应的value
	 * 
	 * @param key key值
	 * @param pathName properties文件的路径和名称。比如：common\message.properties，写成：common/message
	 */
	public static String getProperties(String key, String pathName) {
		Locale locale = Locale.getDefault();
		ResourceBundle resource = ResourceBundle.getBundle(pathName, locale);
		return resource.getString(key);
	}

}
