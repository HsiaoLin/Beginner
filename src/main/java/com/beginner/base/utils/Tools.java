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
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

/**
* <b>类名称：</b>Tools<br/>
* <b>类描述：</b><br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年10月22日 下午5:54:40<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
public class Tools {

	/**
	* getRandomNum(方法描述：随机生成六位数验证码 ) <br />
	* (方法适用条件描述： – 可选)
	* @return
	* int
	* @exception
	* @since 1.0.0
	*/
	public static int getRandomNum() {
		Random r = new Random();
		return r.nextInt(900000) + 100000;
	}

	/**
	* notEmpty(方法描述：检测字符串是否不为空(null,"","null")) <br />
	* (方法适用条件描述： – 可选)
	* @param s
	* @return
	* boolean
	* @exception
	* @since 1.0.0
	*/
	public static boolean notEmpty(String s) {
		return s != null && !"".equals(s) && !"null".equals(s) && !"NULL".equals(s) && !"Null".equals(s);
	}

	/**
	* isEmpty(方法描述：检测字符串是否为空(null,"","null","NULL","Null")) <br />
	* (方法适用条件描述： – 可选)
	* @param s
	* @return
	* boolean
	* @exception
	* @since 1.0.0
	*/
	public static boolean isEmpty(String s) {
		return s == null || "".equals(s) || "null".equals(s) || "NULL".equals(s) || "Null".equals(s);
	}

	/**
	* str2StrArray(方法描述：字符串转换为字符串数组) <br />
	* (方法适用条件描述： – 可选)
	* @param str
	* @param splitRegex 分隔符
	* @return
	* String[]
	* @exception
	* @since 1.0.0
	*/
	public static String[] str2StrArray(String str, String splitRegex) {
		if (isEmpty(str)) {
			return null;
		}
		return str.split(splitRegex);
	}

	/**
	* str2StrArray(方法描述：用默认的分隔符(,)将字符串转换为字符串数组) <br />
	* (方法适用条件描述： – 可选)
	* @param str
	* @return
	* String[]
	* @exception
	* @since 1.0.0
	*/
	public static String[] str2StrArray(String str) {
		return str2StrArray(str, ",\\s*");
	}

	/**
	* date2Str(方法描述：按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串) <br />
	* (方法适用条件描述： – 可选)
	* @param date
	* @return
	* String yyyy-MM-dd HH:mm:ss
	* @exception
	* @since 1.0.0
	*/
	public static String date2Str(Date date) {
		return date2Str(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	* str2Date(方法描述：按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期) <br />
	* (方法适用条件描述： – 可选)
	* @param date
	* @return
	* Date
	* @exception
	* @since 1.0.0
	*/
	public static Date str2Date(String date) {
		if (notEmpty(date)) {
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
	* date2Str(方法描述：按照参数format的格式，日期转字符串) <br />
	* (方法适用条件描述： – 可选)
	* @param date
	* @param format
	* @return
	* String
	* @exception
	* @since 1.0.0
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
	* getTimes(方法描述：把时间根据时、分、秒转换为时间段) <br />
	* (方法适用条件描述： – 可选)
	* @param StrDate
	* @return
	* String
	* @exception
	* @since 1.0.0
	*/
	public static String getTimes(String StrDate) {
		String resultTimes = "";

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date now;

		try {
			now = new Date();
			java.util.Date date = df.parse(StrDate);
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
	* writeFile(方法描述：写txt里的单行内容) <br />
	* (方法适用条件描述： – 可选)
	* @param fileP 文件路径
	* @param content 写入的内容
	* void
	* @exception
	* @since 1.0.0
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
	* checkEmail(方法描述：验证邮箱) <br />
	* (方法适用条件描述： – 可选)
	* @param email
	* @return
	* boolean
	* @exception
	* @since 1.0.0
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
	* checkMobileNumber(方法描述：验证手机号码) <br />
	* (方法适用条件描述： – 可选)
	* @param mobileNumber
	* @return
	* boolean
	* @exception
	* @since 1.0.0
	*/
	public static boolean checkMobileNumber(String mobileNumber) {
		boolean flag = false;
		try {
			Pattern regex = Pattern
					.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
			Matcher matcher = regex.matcher(mobileNumber);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	* checkKey(方法描述：检测KEY是否正确) <br />
	* (方法适用条件描述： – 可选)
	* @param paraname 传入参数
	* @param FKEY 接收的 KEY
	* @return
	* boolean 为空则返回true否则返回false
	* @exception
	* @since 1.0.0
	*/
	public static boolean checkKey(String paraname, String FKEY) {
		paraname = (null == paraname) ? "" : paraname;
		return MD5.md5(paraname + DateUtil.getDays() + ",fh,").equals(FKEY);
	}

	/**
	* readTxtFile(方法描述：读取txt里的单行内容) <br />
	* (方法适用条件描述： – 可选)
	* @param filepath 文件路径
	* @return
	* String
	* @exception
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
	* getResponseBody(方法描述：http客户端-POST) <br />
	* (方法适用条件描述： – 可选)
	* @param httpclient
	* @param httppost
	* @param json
	* @return
	* @throws Exception
	* String
	* @exception
	* @since 1.0.0
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
