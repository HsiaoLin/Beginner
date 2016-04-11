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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * <b>类名称：</b>Tools<br/>
 * <b>类描述：</b>常用工具类<br/>
 * <b>创建人：</b>Hsiao Lin Studio<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class Tools {

	/**
	 * 随机生成六位数验证码
	 * @return int 	返回的六位数验证码
	 * @since 		1.0.0
	 */
	public static int getRandomNum() {
		Random r = new Random();
		return r.nextInt(900000) + 100000;
	}

	/**
	 * 检测字符串是否为空(null,"","null","NULL","Null")
	 * @param str 		要验证的字符串
	 * @return boolean 	为空返回true否则返回false
	 * @since 			1.0.0
	 */
	public static boolean isEmpty(String str) {
		return StringUtils.isBlank(str) || "null".equalsIgnoreCase(str);
	}

	/**
	 * 检测集合是否为空
	 * @param collection 	要验证的集合
	 * @return boolean 		为空返回true否则返回false
	 * @since 				1.0.0
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return CollectionUtils.isEmpty(collection);
	}

	/**
	 * 检测字符串是否不为空(null,"","null")
	 * @param str 		要验证的字符串
	 * @return boolean 	为空返回true否则返回false
	 * @since 			1.0.0
	 */
	public static boolean isNotEmpty(String str) {
		return StringUtils.isNotBlank(str) && !"null".equalsIgnoreCase(str);
	}

	/**
	 * 检测集合是否非空
	 * @param collection 	要验证的集合
	 * @return boolean 		为空返回true否则返回false
	 * @since 				1.0.0
	 */
	public static boolean isNotEmpty(Collection<?> collection) {
		return CollectionUtils.isNotEmpty(collection);
	}

	/**
	 * 字符串转换为字符串数组
	 * @param str 			要分隔的字符串
	 * @param splitRegex 	分隔符
	 * @return String[] 	返回的字符串数组
	 * @since 				1.0.0
	 */
	public static String[] str2Array(String str, String splitRegex) {
		if (isEmpty(str)) {
			return null;
		}
		return str.split(splitRegex);
	}

	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * @param str 			要分隔的字符串
	 * @return String[] 	返回的字符串数组
	 * @since 				1.0.0
	 */
	public static String[] str2Array(String str) {
		return str2Array(str, ",\\s*");
	}

	/**
	 * 验证邮箱
	 * @param email 	邮箱
	 * @return boolean 	正确则返回true否则返回false
	 * @since 			1.0.0
	 */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证手机号码
	 * @param mobileNumber 	手机号码
	 * @return boolean		正确则返回true否则返回false
	 * @since 				1.0.0
	 */
	public static boolean checkMobileNumber(String mobileNumber) {
		boolean flag = false;
		try {
			Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
			Matcher matcher = regex.matcher(mobileNumber);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 向txt里写单行内容
	 * @param fileP 	文件路径
	 * @param content 	写入的内容
	 * @since 			1.0.0
	 */
	public static void writeFile(String fileP, String content) {
		String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../"; //项目路径
		filePath = (filePath.trim() + fileP.trim()).substring(6).trim();
		if (filePath.indexOf(":") != 1) {
			filePath = File.separator + filePath;
		}
		try {
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath), "utf-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(content);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取txt里的单行内容
	 * @param filepath 	文件路径
	 * @return String 	文件路径
	 * @since 1.0.0
	 */
	public static String readTxtFile(String path) {
		try {
			String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../"; //项目路径
			filePath = filePath.replaceAll("file:/", "");
			filePath = filePath.replaceAll("%20", " ");
			filePath = filePath.trim() + path.trim();
			if (filePath.indexOf(":") != 1) {
				filePath = File.separator + filePath;
			}
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding); // 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					return lineTxt;
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件,查看此路径是否正确:" + filePath);
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
		}
		return "";
	}
}
