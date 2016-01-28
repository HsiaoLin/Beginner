package com.beginner.base.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.util.JSONPObject;

import com.beginner.base.plugin.page.PageData;

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
	 * 
	 * @param pd
	 * @return
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
	 * 设置list中的distance
	 */
	public static List<PageData> setListDistance(List<PageData> list, PageData pd) {
		List<PageData> listReturn = new ArrayList<PageData>();
		String user_longitude = "";
		String user_latitude = "";

		try {
			user_longitude = pd.get("user_longitude").toString(); //"117.11811";
			user_latitude = pd.get("user_latitude").toString(); //"36.68484";
		} catch (Exception e) {
			logger.error("缺失参数--user_longitude和user_longitude");
			logger.error("lost param：user_longitude and user_longitude");
		}

		PageData pdTemp = new PageData();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			pdTemp = list.get(i);
			String longitude = pdTemp.get("longitude").toString();
			String latitude = pdTemp.get("latitude").toString();
			String distance = MapDistance.getDistance(user_longitude, user_latitude, longitude, latitude);
			pdTemp.put("distance", distance);
			pdTemp.put("size", distance.length());
			listReturn.add(pdTemp);
		}

		return listReturn;
	}

	public static Object returnObject(PageData pd, Map<String, Object> map) {
		if (pd.containsKey("callback")) {
			String callback = pd.get("callback").toString();
			return new JSONPObject(callback, map);
		} else {
			return map;
		}
	}
}
