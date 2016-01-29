package com.beginner.base.utils;

import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.lang3.StringUtils;

/**
* <b>类名称：</b>ResourcesUtil<br/>
* <b>类描述：</b>配置文件读取工具类<br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
public class ResourcesUtil {

	/**
	 * java.util.ResourceBundle读取classpath下的properties配置文件
	 * @param key 		key的值
	 * @param fileName 	文件路径+文件名称
	 * @return String 	返回key值对应的value
	 */
	public static String getProperty(String key, String fileName) {

		if (StringUtils.isBlank(key))
			throw new IllegalArgumentException("The key cannot be null and cannot be empty.");

		if (StringUtils.isBlank(fileName))
			throw new IllegalArgumentException("The fileName cannot be null and cannot be empty.");

		Locale locale = Locale.getDefault();

		ResourceBundle resource = ResourceBundle.getBundle(fileName, locale);

		return resource.getString(key);
	}

	/**
	 * org.apache.commons.configuration.Configuration读取properties配置文件
	 * <p>
	 * 可读取任意位置的properties配置文件：<br>
	 * 1、classpath下：key=type fileName=beginner.properties 				返回值-mysql<br>
	 * 2、项目盘符目录下：key=type fileName=/data/app/beginner.properties 	返回值-mysql<br>
	 * 3、项目盘符目录下：key=type fileName=D:/data/app/beginner.properties 	返回值-mysql<br>
	 * </p>
	 * @param key 		key的值
	 * @param fileName 	文件路径+文件名称+文件名称后缀
	 * @return String 	返回key值对应的value
	 * @throws Exception 
	 */
	public static String getProperties(String key, String fileName) throws Exception {

		if (StringUtils.isBlank(key))
			throw new IllegalArgumentException("The key cannot be null and cannot be empty.");

		if (StringUtils.isBlank(fileName))
			throw new IllegalArgumentException("The fileName cannot be null and cannot be empty.");

		String properties = StringUtils.EMPTY;
		Configuration config = null;
		try {
			config = new PropertiesConfiguration(fileName);
			properties = config.getString(key);
		} catch (Exception e) {
			throw new Exception();
		}
		return properties;
	}

	public static String getXmlProperties(String key, String fileName) throws Exception {

		if (StringUtils.isBlank(key))
			throw new IllegalArgumentException("The key cannot be null and cannot be empty.");

		if (StringUtils.isBlank(fileName))
			throw new IllegalArgumentException("The fileName cannot be null and cannot be empty.");

		String properties = StringUtils.EMPTY;
		XMLConfiguration config = null;
		try {
			config = new XMLConfiguration(fileName);
			properties = config.getString(key);
		} catch (Exception e) {
			throw new Exception();
		}
		return properties;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(getProperties("type", "beginner.properties"));
		System.out.println(getProperties("type", "/data/app/beginner.properties"));
		System.out.println(getProperties("type", "D:/data/app/beginner.properties"));
		System.out.println(getProperty("type", "beginner"));

		System.out.println(getXmlProperties("configuration.processing.test", "config.xml"));
		System.out.println(getXmlProperties("configuration.processing[@stage]", "config.xml"));
	}
}
