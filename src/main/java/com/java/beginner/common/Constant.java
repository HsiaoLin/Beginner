package com.java.beginner.common;

/**
 * 项目名称：
 * 
 */
/**
* <b>类名称：</b>Constant<br/>
* <b>类描述：</b><br/>
* <b>创建人：</b>尹枭凌工作室-Hsiao Lin<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年10月26日 下午4:11:57<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
/**
* <b>类名称：</b>Constant<br/>
* <b>类描述：</b><br/>
* <b>创建人：</b>尹枭凌工作室-Hsiao Lin<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年10月26日 下午4:12:56<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
public class Constant {

	//======================日志=======================
	/**
	* START_CN:（变量描述：汉语“开始”）
	* @since 1.0.0
	*/
	public static final String START_CN = "开始";

	/**
	* END_CN:（变量描述：汉语“结束”）
	* @since 1.0.0
	*/
	public static final String END_CN = "结束";

	/**
	* START_EN:（变量描述：英语“START”）
	* @since 1.0.0
	*/
	public static final String START_EN = "START";

	/**
	* END_EN:（变量描述：英语“END”）
	* @since 1.0.0
	*/
	public static final String END_EN = "END";

	//======================日志=======================

	//====================== Shiro =======================
	/**
	* SESSION_SECURITY_CODE:（变量描述：安全编码）
	* @since 1.0.0
	*/
	public static final String SECURITY_CODE = "securityCode";

	/**
	* SESSION_ROLE_RIGHTS:（变量描述：权限）
	* @since 1.0.0
	*/
	public static final String ROLE_JURISDICTIONS = "roleJurisdictions";

	//====================== Shiro =======================

	//====================== Session =======================
	//====================== Session =======================

	//====================== Interceptor =======================
	/**
	* NO_INTERCEPTOR_PATH:（变量描述：不对匹配该值的访问路径拦截（正则））
	* @since 1.0.0
	*/
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(code)|(app)|(api)|(static)|(main)|(register)).*";

	//====================== Interceptor =======================

	//====================== User =======================
	/**
	* USER:（变量描述：）
	* @since 1.0.0
	*/
	public static final String USER = "USER";

	/**
	* USERNAME:（变量描述：用户名）
	* @since 1.0.0
	*/
	public static final String USERNAME = "USERNAME";

	//====================== User =======================

	//====================== Menu =======================

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

	//====================== Menu =======================

	//====================== System =======================
	/**
	* LOGIN:（变量描述：登录地址）
	* @since 1.0.0
	*/
	public static final String LOGIN = "/login/to_login";

	/**
	* SYSNAME:（变量描述：系统名称路径）
	* @since 1.0.0
	*/
	public static final String SYSNAME = "admin/config/SYSNAME.txt";

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

	/**
	* FILEPATHIMG:（变量描述：图片上传路径）
	* @since 1.0.0
	*/
	public static final String FILEPATHIMG = "uploadFiles/uploadImgs/";

	/**
	* FILEPATHFILE:（变量描述：文件上传路径）
	* @since 1.0.0
	*/
	public static final String FILEPATHFILE = "uploadFiles/file/";

	/**
	* FILEPATHTWODIMENSIONCODE:（变量描述：二维码存放路径）
	* @since 1.0.0
	*/
	public static final String FILEPATHTWODIMENSIONCODE = "uploadFiles/twoDimensionCode/";

	/**
	* APP_REGISTERED_PARAM_ARRAY:（变量描述：app注册接口_请求协议参数）
	* @since 1.0.0
	*/
	public static final String[] APP_REGISTERED_PARAM_ARRAY = new String[] { "countries", "uname", "passwd", "title", "full_name", "company_name",
			"countries_code", "area_code", "telephone", "mobile" };

	public static final String[] APP_REGISTERED_VALUE_ARRAY = new String[] { "国籍", "邮箱帐号", "密码", "称谓", "名称", "公司名称", "国家编号", "区号", "电话", "手机号" };

	// app根据用户名获取会员信息接口_请求协议中的参数
	public static final String[] APP_GETAPPUSER_PARAM_ARRAY = new String[] { "USERNAME" };

	public static final String[] APP_GETAPPUSER_VALUE_ARRAY = new String[] { "用户名" };

	//====================== System =======================

	//====================== Other =======================
	public static final String SESSION_QX = "QX";

	public static final String SESSION_userpds = "userpds";

	/**
	* SESSION_USERROL:（变量描述：用户对象）
	* @since 1.0.0
	*/
	public static final String SESSION_USERROL = "USERROL";

	/**
	* TRUE:（变量描述：“T”）
	* @since 1.0.0
	*/
	public static final String TRUE = "T";

	/**
	* FALSE:（变量描述：“F”）
	* @since 1.0.0
	*/
	public static final String FALSE = "F";
	//====================== Other =======================

}
