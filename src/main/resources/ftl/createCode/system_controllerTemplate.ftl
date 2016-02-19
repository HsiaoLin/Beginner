/**
* <b>项目名：</b>勿忘初心方得始终<br/>
* <b>包名：</b>com.beginner.${packageName}.controller<br/>
* <b>文件名：</b>${objectName}Controller.java<br/>
* <b>日期：</b>2015年05月21日 下午6:18:18<br/>
* <b>Copyright (c)</b> 2015-2016 Hsiao Lin Studio-版权所有<br/>
*/
package com.beginner.system.controller.${packageName};

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
import com.beginner.system.service.${objectNameLower}.${objectName}Service;

/**
* <b>类名称：</b>${objectName}Controller<br/>
* <b>类描述：</b><br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>创建时间：</b>2015年05月21日 下午6:18:18<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b><br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
@Controller
@RequestMapping(value="/${packageName}/${objectNameLower}")
public class ${objectName}Controller extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	String menuUrl = "${packageName}/${objectNameLower}/list"; //菜单地址(权限用)
	@Resource(name="${objectNameLower}Service")
	private ${objectName}Service ${objectNameLower}Service;

	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() {
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			pd.put("${objectNameUpper}_ID", null);	//主键
	<#list fieldList as var>
		<#if var[3] == "否">
			<#if var[1] == 'Date'>
			pd.put("${var[0]}", Tools.date2Str(new Date()));	//${var[2]}
			<#else>
			pd.put("${var[0]}", "${var[4]?replace("无","")}");	//${var[2]}
			</#if>
		</#if>
	</#list>
			${objectNameLower}Service.save(pd);
			logger.info("新增${objectName}成功");
		} catch (Exception e) {
			logger.error("新增${objectName}失败", e);
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			${objectNameLower}Service.delete(pd);
			out.write("success");
			out.close();
			logger.info("删除${objectName}成功");
		} catch(Exception e){
			logger.error("删除${objectName}失败", e);
		}
	}

	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() {
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			${objectNameLower}Service.edit(pd);
			logger.info("修改${objectName}成功");
		} catch (Exception e) {
			logger.error("修改${objectName}失败", e);
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = ${objectNameLower}Service.list(page);//列出${objectName}列表
			mv.setViewName("${packageName}/${objectNameLower}/${objectNameLower}_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			//权限
			mv.addObject(Const.ROLE_RIGHTS, this.getRights());
		} catch(Exception e){
			logger.error("${objectName}分页列表查询失败", e);
		}
		return mv;
	}

	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("${packageName}/${objectNameLower}/${objectNameLower}_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}	

	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			pd = ${objectNameLower}Service.findById(pd);//根据ID读取
		} catch (Exception e) {
			logger.error("根据ID查询${objectName}失败", e);
		}
		mv.setViewName("${packageName}/${objectNameLower}/${objectNameLower}_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}

	/**
	 * 批量删除
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() {
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				${objectNameLower}Service.deleteAll(ArrayDATA_IDS);
				pd.put("msg", "ok");
			}else{
				pd.put("msg", "no");
			}
			pdList.add(pd);
			map.put("list", pdList);

			logger.info("批量删除${objectName}成功");
		} catch (Exception e) {
			logger.error("批量删除${objectName}失败", e);
		}
		return AppUtil.returnObject(pd, map);
	}

	/**
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(){
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
	<#list fieldList as var>
			titles.add("${var[2]}");	//${var_index+1}
	</#list>
			dataMap.put("titles", titles);
			List<PageData> varOList = ${objectNameLower}Service.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
	<#list fieldList as var>
			<#if var[1] == 'Integer'>
				vpd.put("var${var_index+1}", varOList.get(i).get("${var[0]}").toString());	//${var_index+1}
			<#else>
				vpd.put("var${var_index+1}", varOList.get(i).getString("${var[0]}"));	//${var_index+1}
			</#if>
	</#list>
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);

			logger.info("导出${objectName}到excel成功");
		} catch(Exception e){
			logger.error("导出${objectName}到excel失败", e);
		}
		return mv;
	}
}