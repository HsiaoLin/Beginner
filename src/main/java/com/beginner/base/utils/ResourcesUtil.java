package com.beginner.base.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
	 * java.util.ResourceBundle读取classpath下的properties配置文件（使用方法见TestResourcesUtil类）
	 * @param key 		key的值
	 * @param fileName 	文件路径+文件名称（不要加.properties文件扩展名）
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
	 * org.apache.commons.configuration.Configuration读取properties配置文件（使用方法见TestResourcesUtil类）
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

	/**
	 * 读取XML配置文件指定key的值（使用方法见TestResourcesUtil类）
	 * @param key 			XML文档的key
	 * @param fileName 		XML配置文件名称
	 * @return String 		指定key的值
	 * @throws Exception 	抛出的异常
	 */
	public static String getXmlProperty(String key, String fileName) throws Exception {

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

	/**
	 * 读取XML配置文件所有的KEY（使用方法见TestResourcesUtil类）
	 * @param fileName 		XML配置文件名称
	 * @return List<String> 返回数组类型的集合类
	 * @throws Exception 	抛出异常信息
	 */
	public static List<String> getXmlProperties(String fileName) throws Exception {

		if (StringUtils.isBlank(fileName))
			throw new IllegalArgumentException("The fileName cannot be null and cannot be empty.");
		
		XMLConfiguration config = null;
		Iterator<String> properties = null;
		List<String> list = new ArrayList<String>();
		try {
			config = new XMLConfiguration(fileName);
			properties = config.getKeys();
			while (properties.hasNext()) {
				String key = (String) properties.next();
				list.add(key);
			}
		} catch (Exception e) {
			throw new Exception();
		}
		return list;
	}
}
