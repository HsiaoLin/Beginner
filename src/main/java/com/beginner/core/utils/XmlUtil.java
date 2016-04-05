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

import com.thoughtworks.xstream.XStream;

/**
* <b>类名称：</b>XmlUtil<br/>
* <b>类描述：</b>XML工具类<br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
public class XmlUtil {

	/** 
	 * Xml转换成JavaBean
	 * 
	 * @param xml 			Xml格式的字符串
	 * @param rootElement 	Xml格式字符串的根元素
	 * @param c 			要转换的JavaBean的Class
	 * @return JavaBean
	 */
	@SuppressWarnings("unchecked")
	public static <T> T convertToJaveBean(String xml, String rootElement, Class<T> c) {
		T t = null;
		XStream xStream = getXStream();
		xStream.alias(rootElement, c);
		t = (T) xStream.fromXML(xml);
		return t;
	}

	public static XStream getXStream() {
		return singleXStream.xStream;
	}

	private static class singleXStream {

		public final static XStream xStream = new XStream();
	}
}
