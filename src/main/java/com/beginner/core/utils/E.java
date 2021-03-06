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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
* <b>类名称：</b>E<br/>
* <b>类描述：</b>枚举工具类-新版本做返回信息国际化、自定义、可配置<br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
public enum E {

	//状态码KEY、VALUE、信息的KEY、数据的KEY
	SUCCESS("status", "0", "msg", "data"),

	FAILURE("status", "1", "msg", "data"),

	//状态码、中文信息、英文信息
	MSG_SUCCESS("0", "成功", "success"),

	MSG_FAILURE("1", "失败", "failure"),

	//状态码、中文信息、英文信息
	MSG_1001("1001", "新增失败", "The put fails."),

	MSG_1002("1002", "更新失败", "The update fails."),

	MSG_1003("1003", "删除失败", "The delete fails."),

	MSG_1004("1004", "查询失败", "The query fails."),

	MSG_1005("1005", "程序异常", "The program is Abnormal."),

	MSG_1006("1006", "参数非法", "Illegal request parameters."), ;

	public static void main(String[] args) {
		int[] intarray = { 1, 2, 3, 4, 5, 6, 7, 8 };
		JSONArray array = JSONArray.fromObject(intarray);
		JSONObject obj = new JSONObject();
		obj.element(E.SUCCESS.STATUS, E.MSG_SUCCESS.Code);
		obj.element(E.SUCCESS.MSG, E.MSG_SUCCESS.Chinese);
		obj.element(E.SUCCESS.DATA, array);

		System.out.println(obj.toString());
	}

	private String STATUS;

	private String STATUS_VALUE;

	private String MSG;

	private String DATA;

	private String Code;

	private String English;

	private String Chinese;

	public String getSTATUS() {
		return STATUS;
	}

	public String getSTATUS_VALUE() {
		return STATUS_VALUE;
	}

	public String getMSG() {
		return MSG;
	}

	public String getDATA() {
		return DATA;
	}

	public String getCode() {
		return Code;
	}

	public String getEnglish() {
		return English;
	}

	public String getChinese() {
		return Chinese;
	}

	private E(String sTATUS, String sTATUS_VALUE, String mSG, String dATA) {
		STATUS = sTATUS;
		STATUS_VALUE = sTATUS_VALUE;
		MSG = mSG;
		DATA = dATA;
	}

	private E(String code, String chinese, String english) {
		Code = code;
		Chinese = chinese;
		English = english;
	}
}
