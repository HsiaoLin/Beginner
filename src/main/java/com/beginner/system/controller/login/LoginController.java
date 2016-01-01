/**
* <b>项目名：</b>不忘初心方得始终<br/>
* <b>包名：</b>com.beginner.system.controller<br/>
* <b>文件名：</b>LoginController.java<br/>
* <b>版本信息：</b><br/>
* <b>日期：</b>2015年10月26日-下午3:18:18<br/>
* <b>Copyright (c)</b> 2015尹枭凌工作室-版权所有<br/>
*/
package com.beginner.system.controller.login;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.beginner.base.common.Const;
import com.beginner.base.controller.BaseController;
import com.beginner.base.plugin.page.PageData;
import com.beginner.base.utils.AppUtil;
import com.beginner.base.utils.Tools;
import com.beginner.system.bean.user.User;

/**
* <b>类名称：</b>LoginController<br/>
* <b>类描述：</b><br/>
* <b>创建人：</b>尹枭凌工作室-Hsiao Lin<br/>
* <b>创建时间：</b>2015年10月26日 下午3:18:18<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b><br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController {

	/**
	* to_login(方法描述：访问登陆页面) <br />
	* (方法适用条件描述： – 可选)
	* @return
	* ModelAndView
	* @exception
	* @since  1.0.0
	*/
	@RequestMapping(value = "/to_login")
	public ModelAndView toLogin() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.setViewName("system/admin/login");
		mv.addObject("pd", pd);
		return mv;
	}

	/**
	* login(方法描述：请求登录，验证用户) <br />
	* (方法适用条件描述： – 可选)
	* @return
	* @throws Exception
	* Object
	* @exception
	* @since  1.0.0
	*/
	@RequestMapping(value = "/login_validation", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object loginValidation() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "";
		String KEYDATA[] = pd.getString("KEYDATA").split(",99,");

		if (null != KEYDATA && KEYDATA.length == 3) {
			//shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			String sessionCode = (String) session.getAttribute(Const.SECURITY_CODE); //获取session中的验证码

			String code = KEYDATA[2];
			if (null == code || "".equals(code)) {
				errInfo = "nullcode"; //验证码为空
			} else {
				String USERNAME = KEYDATA[0];
				String PASSWORD = KEYDATA[1];
				pd.put("USERNAME", USERNAME);
				//				if (Tools.notEmpty(sessionCode) && sessionCode.equalsIgnoreCase(code)) {
				//					String passwd = new SimpleHash("SHA-1", USERNAME, PASSWORD).toString(); //密码加密
				//					pd.put("PASSWORD", passwd);
				//					pd.put("user_type", 2);
				//
				//					pd = usererService.getUserByNameAndPwd(pd);
				//					if (pd != null) {
				//						//判断用户是否审核通过
				//						if ("audited".equals(pd.get("userStatus"))) {
				//							pd.put("LAST_LOGIN", DateUtil.getTime().toString());
				//							usererService.updateLastLogin(pd);
				User user = new User();
				user.setUserId(1);
				user.setUserName("尹枭凌");
				user.setName("尹枭凌");
				user.setUserPassword("123");
				Map pds = new HashMap();
				try {
					pds = BeanUtils.describe(user);
				} catch (Exception e1) {
					throw new Exception();
				}

				//							user.setUserType(String.valueOf(pd.get("userType")));
				//							user.setLastLogin(pd.getString("LAST_LOGIN"));
				//							user.setIp(pd.getString("IP"));
				//							user.setUserStatus(pd.getString("userStatus"));
				session.setAttribute(Const.USER, user);
				session.removeAttribute(Const.SECURITY_CODE);
				session.setAttribute(Const.USER_PAGEDATA, pds);
				//shiro加入身份验证
				Subject subject = SecurityUtils.getSubject();
				UsernamePasswordToken token = new UsernamePasswordToken(USERNAME, PASSWORD);
				try {
					subject.login(token);
				} catch (AuthenticationException e) {
					errInfo = "身份验证失败！";
				}
				//						} else {
				//							errInfo = "accountNotAduit"; //用户未通过审核
				//						}
				//					} else {
				//						errInfo = "usererror"; //用户名或密码有误
				//					}
				//				} else {
				//					errInfo = "codeerror"; //验证码输入有误
				//				}
				if (Tools.isEmpty(errInfo)) {
					errInfo = "success"; //验证成功
				}
			}
		} else {
			errInfo = "error"; //缺少参数
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}

	/**
	 * 进入tab标签
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index() {
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		this.getRequest().setAttribute("pd", pd);
		return "system/admin/index";
	}

	/**
	* tab(方法描述：tab页签) <br />
	* (方法适用条件描述： – 可选)
	* @return
	* String
	* @exception
	* @since  1.0.0
	*/
	@RequestMapping(value = "/tab")
	public String tab() {
		return "system/admin/tab";
	}

	/**
	* defaultPage(方法描述：进入首页后右侧显示默认页面) <br />
	* (方法适用条件描述： – 可选)
	* @return
	* String
	* @exception
	* @since  1.0.0
	*/
	@RequestMapping(value = "/login_default")
	public String defaultPage() {
		return "system/admin/default";
	}
}
