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

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Assert;
import org.junit.Test;

/**
 * <b>类名称：</b>DateUtil<br/>
 * <b>类描述：</b>日期工具类<br/>
 * <b>创建人：</b>Hsiao Lin Studio<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class DateUtil {

	public static String[] PARSE_PATTERNS = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd",
			"yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM" };

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
	/**
	 * DateTime变化（增减）
	*/
	@Test
	public void testChange() {
		DateTime dateTime = new DateTime(2014, 1, 2, 10, 11, 12);

		//得到1年2个月3天后的日期
		Assert.assertEquals(new DateTime(2015, 3, 5, 10, 11, 12), dateTime.plusYears(1).plusMonths(2).plusDays(3));
	}

	/**
	 * 指定值（更改DateTime的特定部分为特定值）
	*/
	@Test
	public void testSpecific() {
		DateTime dateTime = new DateTime(2014, 12, 2, 8, 12, 45, 666);

		//把小时指定为13点
		Assert.assertEquals(new DateTime(2014, 12, 2, 13, 12, 45, 666), dateTime.withHourOfDay(13));
	}

	/**
	 * 字符串和DateTime互转
	*/
	@Test
	public void testToDateToString() {
		String str = "2014-12-02 08:12:45";
		DateTime dateTime = new DateTime(2014, 12, 2, 8, 12, 45);

		//字符串转化为DateTime
		DateTime parsedDateTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(str);
		Assert.assertEquals(dateTime, parsedDateTime);

		//DateTime转化为字符串，有两种方法
		String str01 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").print(dateTime);
		Assert.assertEquals(str, str01);

		String str02 = dateTime.toString("yyyy-MM-dd HH:mm:ss");
		Assert.assertEquals(str, str02);

	}

	/**
	 * DateTime截断
	*/
	@Test
	public void testTruncateTo() {
		DateTime dateTime = new DateTime(2014, 12, 2, 8, 12, 45, 666);

		//精确到天
		DateTime tillDay = dateTime.dayOfMonth().roundFloorCopy();
		Assert.assertEquals(new DateTime(2014, 12, 2, 0, 0, 0), tillDay);

		//精确到秒
		DateTime tillSecond = dateTime.secondOfMinute().roundFloorCopy();
		Assert.assertEquals(new DateTime(2014, 12, 2, 8, 12, 45), tillSecond);

	}

	/**
	 * 得到某一天的23:59:59
	*/
	@Test
	public void testWith() {
		DateTime dateTime = new DateTime(2014, 12, 2, 8, 12, 45);

		//两种方法

		//指定时分秒为23:59:59
		Assert.assertEquals(new DateTime(2014, 12, 2, 23, 59, 59), dateTime.withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59));

		//取一天中的最后一秒（秒的最大值）
		Assert.assertEquals(new DateTime(2014, 12, 2, 23, 59, 59), dateTime.secondOfDay().withMaximumValue());

		//得到当天的00:00:00
		Assert.assertEquals(new DateTime(2014, 12, 2, 0, 0, 0), dateTime.secondOfDay().withMinimumValue());
	}
}
