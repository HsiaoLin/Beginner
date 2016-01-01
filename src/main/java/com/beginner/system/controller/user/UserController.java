/**
* <b>项目名：</b>不忘初心方得始终<br/>
* <b>包名：</b>com.beginner.system.controller<br/>
* <b>文件名：</b>UserController.java<br/>
* <b>版本信息：</b><br/>
* <b>日期：</b>2015年10月27日-下午7:57:07<br/>
* <b>Copyright (c)</b> 2015尹枭凌工作室-版权所有<br/>
*/
package com.beginner.system.controller.user;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.beginner.base.common.Const;
import com.beginner.base.controller.BaseController;
import com.beginner.base.plugin.page.Page;
import com.beginner.base.plugin.page.PageData;
import com.beginner.base.utils.AppUtil;
import com.beginner.base.utils.Jurisdiction;
import com.beginner.base.utils.ObjectExcelView;
import com.beginner.system.service.user.IUserService;

/**
* <b>类名称：</b>UserController<br/>
* <b>类描述：</b><br/>
* <b>创建人：</b>尹枭凌工作室-Hsiao Lin<br/>
* <b>创建时间：</b>2015-10-28<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b><br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
@Controller(value = "userController0")
@RequestMapping(value = "/system/user")
public class UserController extends BaseController {

	String menuUrl = "system/user/list"; //菜单地址(权限用)

	@Resource(name = "userService0")
	private IUserService userService;

	/**
	 * 新增
	 */
	@RequestMapping(value = "/save")
	public ModelAndView save() throws Exception {
		before(logger, "新增User");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
			return null;
		} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("USER_ID", null); //主键
		pd.put("USER_STATUS", "0"); //用户状态
		pd.put("LAST_LOGIN", ""); //最后登陆时间
		pd.put("PARENT_ID", "0"); //父用户ID
		pd.put("BUYING_AGENT_ID", "0"); //采购代理商ID
		pd.put("SUPPLIER_ID", "0"); //供应商ID
		userService.save(pd);
		mv.addObject("msg", "success");
		mv.setViewName("save_result");
		after(logger);
		return mv;
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public void delete(PrintWriter out) {
		before(logger, "删除User");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
			return;
		} //校验权限
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			userService.delete(pd);
			out.write("success");
			out.close();
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			after(logger);
		}
	}

	/**
	 * 修改
	 */
	@RequestMapping(value = "/edit")
	public ModelAndView edit() throws Exception {
		before(logger, "修改User");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
			return null;
		} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		userService.edit(pd);
		mv.addObject("msg", "success");
		mv.setViewName("save_result");
		after(logger);
		return mv;
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Page page) {
		before(logger, "列表User");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData> varList = userService.list(page); //列出User列表
			mv.setViewName("system/user/user_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.ROLE_RIGHTS, this.getRights()); //按钮权限
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			after(logger);
		}
		return mv;
	}

	/**
	 * 去新增页面
	 */
	@RequestMapping(value = "/goAdd")
	public ModelAndView goAdd() {
		before(logger, "去新增User页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("system/user/user_edit");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			after(logger);
		}
		return mv;
	}

	/**
	 * 去修改页面
	 */
	@RequestMapping(value = "/goEdit")
	public ModelAndView goEdit() {
		before(logger, "去修改User页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = userService.findById(pd); //根据ID读取
			mv.setViewName("system/user/user_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			after(logger);
		}
		return mv;
	}

	/**
	 * 批量删除
	 */
	@RequestMapping(value = "/deleteAll")
	@ResponseBody
	public Object deleteAll() {
		before(logger, "批量删除User");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "dell")) {
			return null;
		} //校验权限
		PageData pd = new PageData();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if (null != DATA_IDS && !"".equals(DATA_IDS)) {
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				userService.deleteAll(ArrayDATA_IDS);
				pd.put("msg", "ok");
			} else {
				pd.put("msg", "no");
			}
			pdList.add(pd);
			map.put("list", pdList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			after(logger);
		}
		return AppUtil.returnObject(pd, map);
	}

	/*
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value = "/excel")
	public ModelAndView exportExcel() {
		before(logger, "导出User到excel");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
			return null;
		}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("用户类型"); //1
			titles.add("中文名"); //2
			titles.add("登陆账号"); //3
			titles.add("登陆密码"); //4
			titles.add("手机"); //5
			titles.add("固定电话"); //6
			titles.add("用户邮箱"); //7
			titles.add("用户状态"); //8
			titles.add("最后登陆时间"); //9
			titles.add("父用户ID"); //10
			titles.add("采购代理商ID"); //11
			titles.add("供应商ID"); //12
			dataMap.put("titles", titles);
			List<PageData> varOList = userService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for (int i = 0; i < varOList.size(); i++) {
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("USER_TYPE")); //1
				vpd.put("var2", varOList.get(i).getString("CHINESE_NAME")); //2
				vpd.put("var3", varOList.get(i).getString("USER_NAME")); //3
				vpd.put("var4", varOList.get(i).getString("USER_PASSWORD")); //4
				vpd.put("var5", varOList.get(i).getString("MOBILE_PHONE")); //5
				vpd.put("var6", varOList.get(i).getString("USER_PHONE")); //6
				vpd.put("var7", varOList.get(i).getString("USER_MAIL")); //7
				vpd.put("var8", varOList.get(i).getString("USER_STATUS")); //8
				vpd.put("var9", varOList.get(i).getString("LAST_LOGIN")); //9
				vpd.put("var10", varOList.get(i).get("PARENT_ID").toString()); //10
				vpd.put("var11", varOList.get(i).get("BUYING_AGENT_ID").toString()); //11
				vpd.put("var12", varOList.get(i).get("SUPPLIER_ID").toString()); //12
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv, dataMap);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			after(logger);
		}
		return mv;
	}
}