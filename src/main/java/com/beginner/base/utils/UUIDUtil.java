package com.beginner.base.utils;

import java.util.UUID;

/**
* <b>类名称：</b>UuidUtil<br/>
* <b>类描述：</b>UUID生成工具类<br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年10月26日 下午3:31:53<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
public class UUIDUtil {

	public static String getUUID() {
		String uuid = UUID.randomUUID().toString().trim();
		return uuid;
	}

	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
}
