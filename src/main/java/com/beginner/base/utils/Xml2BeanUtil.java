package com.beginner.base.utils;

import com.thoughtworks.xstream.XStream;

public class Xml2BeanUtil {

	/** 
	 * xml转换成JavaBean 
	 * 
	 * @param xml xml格式的字符串
	 * @param rootElement xml格式字符串的根元素
	 * @param c 要转换的JavaBean的class
	 * @return 
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
