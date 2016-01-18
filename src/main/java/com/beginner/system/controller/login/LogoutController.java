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
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.beginner.base.common.Const;
import com.beginner.base.controller.BaseController;
import com.beginner.base.plugin.page.PageData;
import com.beginner.base.utils.Tools;

/**
* <b>类名称：</b>LogoutController<br/>
* <b>类描述：</b><br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2016-1-1 上午1:05:14<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
@Controller
public class LogoutController extends BaseController {

	@RequestMapping(value = "/logout")
	public ModelAndView logout() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		mv.setViewName("system/admin/login");
		mv.addObject("pd", pd);
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return mv;
	}
}
