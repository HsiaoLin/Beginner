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

import java.util.UUID;

import org.joda.time.DateTime;

/**
 * <b>类名称：</b>UUIDUtil<br/>
 * <b>类描述：</b>UUID生成工具类<br/>
 * <b>创建人：</b>Hsiao Lin Studio<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class UUIDUtil {

	private static long num = 0;

	/**
	 * 获取带连接符号36位UUID(小写)
	 * @return 	36位UUID<br>例如：6a6cd388-f7ce-459c-a2e0-3747498c6786
	 */
	public static synchronized String get36UUID() {
		return UUID.randomUUID().toString().trim();
	}

	/**
	 * 获取带连接符号36位UUID(大写)
	 * @return 	36位UUID<br>例如：6A6CD388-F7CE-459C-A2E0-3747498C6786
	 */
	public static synchronized String get36UpperUUID() {
		return get36UUID().toUpperCase();
	}

	/**
	 * 获取不带连接符号32位UUID(小写)
	 * @return 	32位UUID<br>例如：5dd5e786d748438fb906ddf1c32be594
	 */
	public static synchronized String get32UUID() {
		return get36UUID().replaceAll("-", "");
	}

	/**
	 * 获取不带连接符号32位UUID(大写)
	 * @return 	32位UUID<br>例如：5DD5E786D748438FB906DDF1C32BE594
	 */
	public static synchronized String get32UpperUUID() {
		return get32UUID().toUpperCase();
	}

	/**
	 * 根据字符串生成固定UUID
	 * @return 	32位固定的UUID<br>例如：Beginner永远都是2ddabc33a6103ceb9311697534035d8d
	 */
	public static synchronized String getUUID(String name) {
		return UUID.nameUUIDFromBytes(name.getBytes()).toString().trim().replaceAll("-", "");
	}

	/**
	  * 根据当前日期生成长整型ID
	  * @return 例如：<br>
	  * 计算时间点：2016年02月19日13时35分12秒314毫秒<br>
	  * 计算的结果：2016021913351231401<br>
	  */
	public static synchronized long getLongId() {
		String id = DateTime.now().toString("yyyyMMddHHmmssS");
		if (num >= 99)
			num = 0l;
		++num;
		if (num < 10)
			id = id + 00 + num;
		else
			id += num;
		return Long.valueOf(id);
	}
}
