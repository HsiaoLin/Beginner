/**
* <b>项目名：</b>不忘初心方得始终<br/>
* <b>包名：</b>com.java.beginner.system.controller<br/>
* <b>文件名：</b>LoginController.java<br/>
* <b>版本信息：</b><br/>
* <b>日期：</b>2015年10月26日-下午3:18:18<br/>
* <b>Copyright (c)</b> 2015尹枭凌工作室-版权所有<br/>
*/
package com.java.beginner.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.beginner.base.BaseController;
import com.java.beginner.common.Constant;
import com.java.beginner.plugin.page.PageData;
import com.java.beginner.utils.Tools;

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

	@RequestMapping(value = "/to_login")
	public ModelAndView to_login() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Constant.SYSNAME)); //读取系统名称
		mv.setViewName("system/admin/login");
		mv.addObject("pd", pd);
		return mv;
	}
}
