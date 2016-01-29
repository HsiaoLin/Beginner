package com.beginner.base.utils;

import java.util.Map;

import com.beginner.base.plugin.page.PageData;
import com.fasterxml.jackson.databind.util.JSONPObject;

/**
* <b>类名称：</b>AppUtil<br/>
* <b>类描述：</b>App工具类<br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
public class AppUtil {

	protected static Logger logger = Logger.getLogger(AppUtil.class);

	/**
	 * 设置分页参数
	 */
	public static PageData setPageParam(PageData pd) {
		String page_now_str = pd.get("page_now").toString();
		int pageNowInt = Integer.parseInt(page_now_str) - 1;
		String page_size_str = pd.get("page_size").toString(); //每页显示记录数
		int pageSizeInt = Integer.parseInt(page_size_str);

		String page_now = pageNowInt + "";
		String page_start = (pageNowInt * pageSizeInt) + "";

		pd.put("page_now", page_now);
		pd.put("page_start", page_start);

		return pd;
	}

	/**
	 * 特殊字符处理如'<','>','"',''','&'
	 */
	public static String htmlEncode(String strSrc) {
		if (strSrc == null)
			return "";

		char[] arr_cSrc = strSrc.toCharArray();
		StringBuffer buf = new StringBuffer(arr_cSrc.length);
		char ch;

		for (int i = 0; i < arr_cSrc.length; i++) {
			ch = arr_cSrc[i];

			if (ch == '<')
				buf.append("&lt;");
			else if (ch == '>')
				buf.append("&gt;");
			else if (ch == '"')
				buf.append("&quot;");
			else if (ch == '\'')
				buf.append("&#039;");
			else if (ch == '&')
				buf.append("&amp;");
			else
				buf.append(ch);
		}

		return buf.toString();
	}

	/**
	 * 跨域操作时返回
	 */
	public static Object returnObject(PageData pd, Map<String, Object> map) {
		if (pd.containsKey("callback")) {
			String callback = pd.get("callback").toString();
			return new JSONPObject(callback, map);
		} else {
			return map;
		}
	}
}
