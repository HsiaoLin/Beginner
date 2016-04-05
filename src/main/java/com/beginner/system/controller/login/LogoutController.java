/**
* <b>项目名：</b>勿忘初心方得始终<br/>
* <b>包名：</b>com.beginner.system.controller<br/>
* <b>文件名：</b>LoginController.java<br/>
* <b>版本信息：</b><br/>
* <b>日期：</b>2015年10月26日-下午3:18:18<br/>
* <b>Copyright (c)</b> 2015-2016 Hsiao Lin Studio-版权所有<br/>
*/
package com.beginner.system.controller.login;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.beginner.core.common.Const;
import com.beginner.core.controller.BaseController;
import com.beginner.core.plugin.PageData;
import com.beginner.core.utils.Tools;
import com.beginner.system.bean.user.User;

/**
* <b>类名称：</b>LogoutController<br/>
* <b>类描述：</b><br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
@Controller
public class LogoutController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/logout")
	public ModelAndView logout() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put(Const.SYSTEM_NAME, Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.setViewName("system/admin/signin");
		mv.addObject("pd", pd);
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Const.USER);
		logger.info("{}已登出", user.getName());
		currentUser.logout();
		return mv;
	}
}
