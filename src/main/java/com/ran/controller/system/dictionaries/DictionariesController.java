package com.ran.controller.system.dictionaries;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ran.controller.base.BaseController;
import com.ran.entity.Page;
import com.ran.entity.system.Menu;
import com.ran.service.system.dictionaries.DictionariesService;
import com.ran.service.system.menu.MenuService;
import com.ran.util.AppUtil;
import com.ran.util.PageData;
/** 
 * 类名称：DictionariesController
 * @version
 */
@Controller
@RequestMapping(value="/dictionaries")
public class DictionariesController extends BaseController {
	
	@Resource(name="menuService")
	private MenuService menuService;
	@Resource(name="dictionariesService")
	private DictionariesService dictionariesService;
	

	
	/**
	 * 去添加父的列表页面
	 */
	@RequestMapping(value="/toAddPar")
	public ModelAndView addPar(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = getPageData();
		if ("edit".equals(pd.getString("opera"))){
			pd = dictionariesService.getById(pd);
			pd.put("opera", "edit");
		}
		mv.setViewName("system/dictionaries/dic_add");
		mv.addObject("pd",pd);
		return mv;
	}
	/**
	 * 去添加父的列表页面
	 */
	@RequestMapping(value="/toAddChild")
	public ModelAndView addChild(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = getPageData();
		if ("edit".equals(pd.getString("opera"))){
			pd = dictionariesService.getById(pd);
			pd.put("opera", "edit");
		}else{
			pd.put("parentId", pd.getString("code_id"));
		}
		mv.setViewName("system/dictionaries/dic_edit");
		mv.addObject("pd",pd);
		return mv;
	}
	/**
	 * 父的列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		List<PageData> varList = dictionariesService.listAll();
		mv.setViewName("system/dictionaries/dic_list");
		mv.addObject("varList", varList);
		return mv;
	}
	/**
	 * 查询子数据字典
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/sub")
	public void getSub(HttpServletResponse response)throws Exception{
		try {
			PageData pd = getPageData();
			List<PageData> subDics = dictionariesService.getByParentId(pd);
			JSONArray arr = JSONArray.fromObject(subDics);
			PrintWriter out;
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			String json = arr.toString();
			out.write(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}
	/**
	 * 添加业务字典类型
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/addPar")
	public void addPar(HttpServletResponse res,HttpServletRequest req)throws Exception{
		String ret = "fail";
		PrintWriter out = null;
		try {
			res.setCharacterEncoding("utf-8");
			req.setCharacterEncoding("utf-8");
			PageData pd = getPageData();
			out = res.getWriter();
			dictionariesService.addDic(pd);
			ret = "succ";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString(), e);
		}
		out.print(ret);
		out.flush();
		out.close();
	}
	/**
	 * 删除数据字典
	 */
	@RequestMapping(value="/del")
	@ResponseBody
	public void del(HttpServletResponse res,HttpServletRequest req){
		String ret = "fail";
		PrintWriter out = null;
		try {
			res.setCharacterEncoding("utf-8");
			req.setCharacterEncoding("utf-8");
			out = res.getWriter();
			PageData pd = getPageData();
			String isParent = pd.getString("isParent");
			if ("1".equals(isParent)){
				dictionariesService.delChildDic(pd);
			}
			dictionariesService.delDic(pd);
			ret = "succ";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString(), e);
		}
		out.print(ret);
		out.flush();
		out.close();
	}
	/**
	 * 修改数据字典
	 */
	@RequestMapping(value="/updateDic")
	@ResponseBody
	public void updateDic(HttpServletResponse res,HttpServletRequest req){
		String ret = "fail";
		PrintWriter out = null;
		try {
			res.setCharacterEncoding("utf-8");
			req.setCharacterEncoding("utf-8");
			out = res.getWriter();
			PageData pd = getPageData();
			dictionariesService.updateDic(pd);
			ret = "succ";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString(), e);
		}
		out.print(ret);
		out.flush();
		out.close();
	}
	

}
