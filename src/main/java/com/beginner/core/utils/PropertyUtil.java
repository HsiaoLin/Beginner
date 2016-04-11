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
package com.beginner.core.utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.lang3.StringUtils;

import com.beginner.core.common.Const;

/**
* <b>类名称：</b>ResourcesUtil<br/>
* <b>类描述：</b>配置文件读取工具类<br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
public class PropertyUtil {

	/**
	 * 获取beginner.properties文件key对应的String类型值
	 * @param key			key的值
	 * @return String		返回key值对应的value
	 */
	public static String getStr(String key) {
		return getProperty(key, Const.BEGINNER);
	}

	/**
	 * 获取beginner.properties文件key对应的int类型值
	 * @param key			key的值
	 * @return int			返回key值对应的value
	 */
	public static int getInt(String key) {
		return Integer.parseInt(getProperty(key, Const.BEGINNER));
	}

	/**
	 * 获取Properties属性集合
	 * @param fileName		例如:beginner.properties
	 * @return Properties 	属性集合
	 * @throws Exception	IO异常
	 */
	public static Properties getObj(String fileName) throws Exception {
		Properties properties = new Properties();
		properties.load(PropertyUtil.class.getClassLoader().getResourceAsStream(fileName));
		return properties;
	}

	/**
	 * 获取Properties属性集合
	 * @param fileName		例如:/data/resources/beginner.properties
	 * @return Properties 	属性集合
	 * @throws Exception	IO异常
	 */
	public static Properties getObjExternal(String fileName) throws Exception {
		Properties properties = new Properties();
		properties.load(new FileInputStream(fileName));
		return properties;
	}

	/**
	 * java.util.ResourceBundle读取classpath下的properties配置文件（使用方法见TestResourcesUtil类）
	 * @param key			key的值
	 * @param fileName		文件路径+文件名称（不要加.properties文件扩展名）
	 * @return String		返回key值对应的value
	 */
	public static String getProperty(String key, String fileName) {

		if (StringUtils.isBlank(key))
			throw new IllegalArgumentException("The key cannot be null and cannot be empty.");

		if (StringUtils.isBlank(fileName))
			throw new IllegalArgumentException("The fileName cannot be null and cannot be empty.");

		Locale locale = Locale.getDefault();

		ResourceBundle resource = ResourceBundle.getBundle(fileName, locale);
		String value = StringUtils.EMPTY;
		try {
			value = resource.getString(key);
		} catch (Exception e) {
			return value;
		}
		return value;
	}

	/**
	 * org.apache.commons.configuration.Configuration读取properties配置文件（使用方法见TestResourcesUtil类）
	 * <p>
	 * 可读取任意位置的properties配置文件：<br>
	 * 1、classpath下：key=type fileName=beginner.properties 				返回值-mysql<br>
	 * 2、项目盘符目录下：key=type fileName=/data/app/beginner.properties 	返回值-mysql<br>
	 * 3、项目盘符目录下：key=type fileName=D:/data/app/beginner.properties 	返回值-mysql<br>
	 * </p>
	 * @param key			key的值
	 * @param fileName		文件路径+文件名称+文件名称后缀
	 * @return String		返回key值对应的value
	 * @throws Exception	IO异常
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
	 * @param fileName		XML配置文件名称
	 * @return List<String>	返回数组类型的集合类
	 * @throws Exception	抛出异常信息
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
