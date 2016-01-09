/**
* <b>项目名：</b>勿忘初心方得始终<br/>
* <b>包名：</b>com.beginner.system.controller<br/>
* <b>文件名：</b>GisController.java<br/>
* <b>版本信息：</b><br/>
* <b>日期：</b>2015年10月27日-下午7:57:07<br/>
* <b>Copyright (c)</b> 2015尹枭凌工作室-版权所有<br/>
*/
package com.beginner.gis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.beginner.base.controller.BaseController;
import com.beginner.base.plugin.page.PageData;

/**
* <b>类名称：</b>GisController<br/>
* <b>类描述：</b><br/>
* <b>创建人：</b>尹枭凌工作室-Hsiao Lin<br/>
* <b>创建时间：</b>2015-10-28<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b><br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
@Controller
@RequestMapping(value = "/gis")
public class GisController extends BaseController {

	/**
	 * 去GIS地图页面
	 */
	@RequestMapping(value = "/goGis")
	public ModelAndView goAdd() {
		before(logger, "去Gis地图展示页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("gis/map");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			after(logger);
		}
		return mv;
	}
}
