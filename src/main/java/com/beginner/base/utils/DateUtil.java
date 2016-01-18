package com.beginner.base.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

/**
* <b>类名称：</b>日期工具类<br/>
* <b>类描述：</b><br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年10月22日 下午5:17:31<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
public class DateUtil {

	private final static SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");

	private final static SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");

	private final static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");

	private final static SimpleDateFormat yyyy_MM_dd_HH_mm_ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private final static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");

	private static String[] PARSE_PATTERNS = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd",
			"yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM" };

	/**
	* getYear(方法描述：获取YYYY格式) <br />
	* (方法适用条件描述： – 可选)
	* @return
	* String
	* @exception
	* @since 1.0.0
	*/
	public static String getYear() {
		return yyyy.format(new Date());
	}

	/**
	* getDay(方法描述：获取YYYY-MM-DD格式) <br />
	* (方法适用条件描述： – 可选)
	* @return
	* String
	* @exception
	* @since 1.0.0
	*/
	public static String getDay() {
		return yyyy_MM_dd.format(new Date());
	}

	/**
	* getDays(方法描述：获取YYYYMMDD格式) <br />
	* (方法适用条件描述： – 可选)
	* @return
	* String
	* @exception
	* @since 1.0.0
	*/
	public static String getDays() {
		return yyyyMMdd.format(new Date());
	}

	/**
	* getTime(方法描述：获取YYYY-MM-DD HH:mm:ss格式) <br />
	* (方法适用条件描述： – 可选)
	* @return
	* String
	* @exception
	* @since 1.0.0
	*/
	public static String getTime() {
		return yyyy_MM_dd_HH_mm_ss.format(new Date());
	}

	/**
	* getTimeSecond(方法描述：获取YYYYMMDDHHmmss格式) <br />
	* (方法适用条件描述： – 可选)
	* @return
	* String
	* @exception
	* @since 1.0.0
	*/
	public static String getTimeSecond() {
		return yyyyMMddHHmmss.format(new Date());
	}

	/**
	* compareDate(方法描述：日期比较，如果s>=e 返回true 否则返回false) <br />
	* (方法适用条件描述： – 可选)
	* @param s
	* @param e
	* @return
	* boolean
	* @exception
	* @since 1.0.0
	*/
	public static boolean compareDate(String s, String e) {
		if (fomatDate(s) == null || fomatDate(e) == null) {
			return false;
		}
		return fomatDate(s).getTime() >= fomatDate(e).getTime();
	}

	/**
	* fomatDate(方法描述：格式化日期) <br />
	* (方法适用条件描述： – 可选)
	* @param date
	* @return
	* Date
	* @exception
	* @since 1.0.0
	*/
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	* fomatDateEx(方法描述：格式化日期) <br />
	* (方法适用条件描述： – 可选)
	* @param date
	* @param pattern
	* @return
	* Date
	* @exception
	* @since 1.0.0
	*/
	public static Date fomatDateEx(String date, String pattern) {
		if (date == null || "".equals(date)) {
			return null;
		}
		DateFormat fmt = new SimpleDateFormat(pattern);
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	* fomatDateString(方法描述：格式化日期) <br />
	* (方法适用条件描述： – 可选)
	* @param datetime
	* @return
	* String
	* @exception
	* @since 1.0.0
	*/
	public static String fomatDateString(String datetime) {
		StringBuffer dateStr = new StringBuffer();
		if (datetime != null && !"".equals(datetime)) {
			String year = datetime.substring(0, 4);
			String mon = datetime.substring(4, 6);
			String day = datetime.substring(6, 8);

			dateStr.append(year + "-" + mon + "-" + day);

			if (datetime.length() == 12) {
				String hour = datetime.substring(8, 10);
				dateStr.append(" " + hour);
				String min = datetime.substring(10, 12);
				dateStr.append(":" + min);
			}
		}
		return dateStr.toString();
	}

	/**
	* isValidDate(方法描述：校验日期是否合法) <br />
	* (方法适用条件描述： – 可选)
	* @param s
	* @return
	* boolean
	* @exception
	* @since 1.0.0
	*/
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	* getDiffYear(方法描述：时间相减得到年数) <br />
	* (方法适用条件描述： endTime - startTime)
	* @param startTime
	* @param endTime
	* @return
	* int
	* @exception
	* @since 1.0.0
	*/
	public static int getDiffYear(String startTime, String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
			return years;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	* getDaySub(方法描述：时间相减得到天数) <br />
	* (方法适用条件描述： endDateStr-beginDateStr)
	* @param beginDateStr
	* @param endDateStr
	* @return
	* long
	* @exception
	* @since 1.0.0
	*/
	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date beginDate = null;
		java.util.Date endDate = null;

		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	/**
	* getAfterDayDate(方法描述：得到n天之后的日期) <br />
	* (方法适用条件描述： – 可选)
	* @param days
	* @return
	* String
	* @exception
	* @since 1.0.0
	*/
	public static String getAfterDayDate(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdfd.format(date);

		return dateStr;
	}

	/**
	* getAfterDayWeek(方法描述：得到n天之后是周几) <br />
	* (方法适用条件描述： – 可选)
	* @param days
	* @return
	* String
	* @exception
	* @since 1.0.0
	*/
	public static String getAfterDayWeek(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String dateStr = sdf.format(date);

		return dateStr;
	}

	/**
	* getDateAfterMinute(方法描述：获取指定时间，指定分钟后的时间) <br />
	* (方法适用条件描述： – 可选)
	* @param date
	* @param minute
	* @return
	* Date
	* @exception
	* @since 1.0.0
	*/
	public static Date getDateAfterMinute(Date date, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}

	/**
	* getTimeFormatDateString(方法描述：获取yyyy-MM-dd HH:mm:ss格式的时间字符串) <br />
	* (方法适用条件描述： – 可选)
	* @param date
	* @return
	* String
	* @exception
	* @since 1.0.0
	*/
	public static String getTimeFormatDateString(Date date) {
		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdfd.format(date);
	}

	/**
	* getFormatDateString(方法描述：获取yyyy-MM-dd格式的时间字符串) <br />
	* (方法适用条件描述： – 可选)
	* @param date
	* @return
	* String
	* @exception
	* @since 1.0.0
	*/
	public static String getFormatDateString(Date date) {
		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
		return sdfd.format(date);
	}

	/**
	* parseDate(方法描述：日期型字符串转化为日期格式) <br />
	* (方法适用条件描述： – 可选)
	* @param str
	* 适用格式:
	* { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	*   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	*   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	* @return
	* Date
	* @exception
	* @since 1.0.0
	*/
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return DateUtils.parseDate(str.toString(), PARSE_PATTERNS);
		} catch (ParseException e) {
			return null;
		}
	}

	public static void main(String[] args) {
		Date date = new Date();
		System.out.println("当前时间是：" + date);
		System.out.println("当前时间是（获取YYYYMMDDHHmmss）：" + getTimeSecond());
		System.out.println("当前时间是（获取YYYYMMDD格式）：" + getDays());
		System.out.println("得到n天之后是周几：3天之后是" + getAfterDayWeek("3"));
	}
}
