package com.beginner.base.utils;

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
