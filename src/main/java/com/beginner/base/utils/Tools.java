package com.beginner.base.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

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
	 * 把时间根据时、分、秒转换为时间段
	 * @param strDate 	字符串类型的日期参数
	 * @return String 	转换后的时间段
	 * @since 			1.0.0
	 */
	public static String getTimes(String strDate) {
		String resultTimes = "";

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date now;

		try {
			now = new Date();
			java.util.Date date = df.parse(strDate);
			long times = now.getTime() - date.getTime();
			long day = times / (24 * 60 * 60 * 1000);
			long hour = (times / (60 * 60 * 1000) - day * 24);
			long min = ((times / (60 * 1000)) - day * 24 * 60 - hour * 60);
			long sec = (times / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

			StringBuffer sb = new StringBuffer();
			if (hour > 0) {
				sb.append(hour + "小时前");
			} else if (min > 0) {
				sb.append(min + "分钟前");
			} else {
				sb.append(sec + "秒前");
			}
			resultTimes = sb.toString();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return resultTimes;
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * @param date 	Date类型的日期
	 * @return 		String类型的日期 yyyy-MM-dd HH:mm:ss
	 * @since 		1.0.0
	 */
	public static String date2Str(Date date) {
		return date2Str(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 按照参数format的格式把日期转字符串
	 * @param date 		Date类型日期
	 * @param format 	日期格式
	 * @return String 	格式化后的日期
	 * @since 			1.0.0
	 */
	public static String date2Str(Date date, String format) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} else {
			return "";
		}
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date 	字符串类型的日期
	 * @return 		Date类型的日期
	 * @since 		1.0.0
	 */
	public static Date str2Date(String date) {
		if (isNotEmpty(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		} else {
			return null;
		}
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
	 * 检测KEY是否正确
	 * @param paraname 	传入参数
	 * @param FKEY 		接收的 KEY
	 * @return boolean 	为空则返回true否则返回false
	 * @since 			1.0.0
	 */
	public static boolean checkKey(String paraname, String FKEY) {
		paraname = (null == paraname) ? "" : paraname;
		return MD5.md5(paraname + DateUtil.getDays() + ",beginner,").equals(FKEY);
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

	/**
	 * HTTP POST请求返回结果
	 * @param httpclient	HTTP request execution
	 * @param httppost		HTTP POST method
	 * @param json			JSON格式的参数
	 * @return String 		返回结果
	 * @throws Exception 	抛出异常
	 * @since 				1.0.0
	 */
	public static String getResponseBody(CloseableHttpClient httpclient, HttpPost httppost, JSONObject json)
			throws Exception {
		CloseableHttpResponse response = null;
		httppost.setHeader("Content-Type", "text/plain");
		httppost.setEntity(new StringEntity(json.toString(), "UTF-8"));
		response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();
		String ret = EntityUtils.toString(entity);
		EntityUtils.consume(entity);
		if (null != response)
			response.close();
		return ret;
	}
}
