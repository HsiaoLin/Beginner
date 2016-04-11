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
package com.beginner.core.common;

import org.apache.commons.lang3.StringUtils;

import com.beginner.core.utils.PropertyUtil;

/**
 * <b>类名称：</b>Const<br/>
 * <b>类描述：</b>常量类工具类<br/>
 * <b>创建人：</b>Hsiao Lin Studio<br/>
 * <b>创建时间：</b>2015年05月21日 下午6:18:18<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class Const extends StringUtils {

	//======================Beginner Start =======================
	/**
	 * BEGINNER:（变量描述：beginner）
	 * @since 1.0.0
	 */
	public static final String BEGINNER = "beginner";

	/**
	 * BEGINNER_PROPERTIES:（变量描述：beginner.properties）
	 * @since 1.0.0
	 */
	public static final String BEGINNER_PROPERTIES = "beginner.properties";

	/**
	 * SLASH：（变量描述：斜杠）
	 * @since 1.0.0
	 */
	public static final String SLASH = "/";

	/**
	 * BACK_SLASH：（变量描述：反斜杠）
	 * @since 1.0.0
	 */
	public static final String BACK_SLASH = "\\";

	//======================Beginner End =======================

	//====================== Shiro Start =======================
	/**
	 * SECURITY_CODE:（变量描述：安全编码）
	 * @since 1.0.0
	 */
	public static final String SECURITY_CODE = "securityCode";

	/**
	 * ROLE_JURISDICTIONS:（变量描述：权限）
	 * @since 1.0.0
	 */
	public static final String ROLE_JURISDICTIONS = "roleJurisdictions";

	/**
	 * ROLE_JURISDICTIONS:（变量描述：权限）
	 * @since 1.0.0
	 */
	public static final String ROLE_RIGHTS = "roleRights";

	//====================== Shiro End =======================

	//====================== Session Start =======================
	/**
	 * USER:（变量描述：User对象）
	 * @since 1.0.0
	 */
	public static final String USER = "user";

	/**
	 * USERPDS:（变量描述：PageData类型的User对象）
	 * @since 1.0.0
	 */
	public static final String USER_PAGEDATA = "userPageData";

	/**
	 * USERNAME:（变量描述：用户名）
	 * @since 1.0.0
	 */
	public static final String USERNAME = "userName";

	//====================== Session End =======================

	//====================== Interceptor Start =======================
	/**
	 * NO_INTERCEPTOR_PATH:（变量描述：不对匹配该值的访问路径拦截（正则））
	 * @since 1.0.0
	 */
	public static final String NO_INTERCEPTOR_PATH = ".*/(|(login)|(logout)|(code)|(app)|(api)|(static)|(main)|(register)).*";

	//====================== Interceptor End =======================

	//====================== Menu Start =======================

	/**
	 * MENU_USER:（变量描述：当前登陆用户的菜单列表）
	 * @since 1.0.0
	 */
	public static final String MENU_USER = "userMenu";

	/**
	 * MENU_ALL:（变量描述：全部的菜单列表）
	 * @since 1.0.0
	 */
	public static final String MENU_ALL = "allMenu";

	//====================== Menu End =======================

	//====================== System Start =======================
	/**
	 * LOGIN:（变量描述：登录地址）
	 * @since 1.0.0
	 */
	public static final String LOGIN = "/to_login";

	/**
	 * SYSNAME:（变量描述：系统名称路径）
	 * @since 1.0.0
	 */
	public static final String SYSNAME = "admin/config/SYSNAME.txt";

	/**
	* SYSTEM_NAME:（变量描述：SYSNAME）
	* @since 1.0.0
	*/
	public static final String SYSTEM_NAME = "SYSNAME";

	/**
	 * PAGE:（变量描述：分页条数配置路径）
	 * @since 1.0.0
	 */
	public static final String PAGE = "admin/config/PAGE.txt";

	/**
	 * EMAIL:（变量描述：邮箱服务器配置路径）
	 * @since 1.0.0
	 */
	public static final String EMAIL = "admin/config/EMAIL.txt";

	/**
	 * SMS1:（变量描述：短信账户配置路径1）
	 * @since 1.0.0
	 */
	public static final String SMS1 = "admin/config/SMS1.txt";

	/**
	 * SMS2:（变量描述：短信账户配置路径2）
	 * @since 1.0.0
	 */
	public static final String SMS2 = "admin/config/SMS2.txt";

	/**
	 * FWATERM:（变量描述：文字水印配置路径）
	 * @since 1.0.0
	 */
	public static final String FWATERM = "admin/config/FWATERM.txt";

	/**
	 * IWATERM:（变量描述：图片水印配置路径）
	 * @since 1.0.0
	 */
	public static final String IWATERM = "admin/config/IWATERM.txt";

	/**
	 * WEIXIN:（变量描述：微信配置路径）
	 * @since 1.0.0
	 */
	public static final String WEIXIN = "admin/config/WEIXIN.txt";

	//====================== System End =======================

	//====================== Other Start =======================

	/**
	* KEYPREFIX:（变量描述：Redis key前缀）
	* @since 1.0.0
	*/
	public static final String KEYPREFIX = PropertyUtil.getStr("redis.keyPrefix");

	//====================== Other End =======================

}
