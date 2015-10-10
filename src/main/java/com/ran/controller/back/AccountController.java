package com.ran.controller.back;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ran.controller.base.BaseController;
import com.ran.entity.Page;
import com.ran.service.system.user.UserService;
import com.ran.util.AppUtil;
import com.ran.util.Const;
import com.ran.util.Jurisdiction;
import com.ran.util.ObjectExcelView;
import com.ran.util.PageData;

/** 
 * 类名称：AccountController
 * 创建时间：2015-08-14
 */
@Controller
@RequestMapping(value="/back/account")
public class AccountController extends BaseController {
	
	String menuUrl = "/back/account/list.do"; //菜单地址(权限用)
	@Resource(name="userService")
	private UserService userService;
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response){
		PrintWriter out = null;
		String ret = "fail";
		try{
			request.setCharacterEncoding("utf-8");
			String userId = request.getParameter("userId");
			if (userId != null && !"".equals(userId)){
				response.setCharacterEncoding("utf-8");
				out = response.getWriter();
				String[] ids = userId.split(",");
				userService.deleteUser(ids);
				ret = "succ";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		out.print(ret);
		out.flush();
		out.close();
		
	}
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Account");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			String user = getRequest().getParameter("user");
			List<PageData>	varList;
			if (user != null && !"".equals(user)){
				pd.put("userId", user);
				pd = userService.findById(pd);
				varList = new ArrayList<PageData>();
				if (pd != null){
					varList.add(pd);
				}
			}else{
				varList = userService.listPdPageUser(page);	//列出Account列表
			}
			mv.setViewName("back/account/account_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		logBefore(logger, "去新增Account页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("back/account/account_add");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(){
		logBefore(logger, "去修改Account页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		HttpServletRequest request = null;
		try {
			request = getRequest();
			String userId = request.getParameter("userId");
			pd.put("userId", userId);
			pd = userService.findById(pd);	//根据ID读取
			mv.setViewName("back/account/account_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	/**
	 * 去查看页面
	 */
	@RequestMapping(value="/goView")
	public ModelAndView goView(){
		logBefore(logger, "去修改Account页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		HttpServletRequest request = null;
		try {
			request = getRequest();
			String userId = request.getParameter("userId");
			pd.put("userId", userId);
			pd = userService.findById(pd);	//根据ID读取
			mv.setViewName("back/account/account_view");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	/**
	 * 用户审核通过
	 */
	@RequestMapping(value="/commit")
	public void commit(HttpServletRequest request, HttpServletResponse response){
		String userId = request.getParameter("userId");
		PrintWriter out = null;
		String ret = "fail";
		try{
			if (userId != null && !"".equals(userId)){
				String[] ids = userId.split(",");
				response.setCharacterEncoding("utf-8");
				out = response.getWriter();
				userService.updateUserStatus(ids);
				ret = "succ";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		out.print(ret);
		out.flush();
		out.close();
	}
	/**
	 * 添加用户
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/add")
	public void add(HttpServletRequest request, HttpServletResponse response){
		PrintWriter out = null;
		String ret = "fail";
		try{
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			String userName = request.getParameter("userName");
			String userPassword = request.getParameter("userPassword");
			String userMail = request.getParameter("userMail");
			String userPhone = request.getParameter("userPhone");
			String userType = request.getParameter("userType");
			userPassword = new SimpleHash("SHA-1", userName, userPassword).toString();	//密码加密
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userName", userName);
			map.put("userPassword", userPassword);
			map.put("userMail", userMail);
			map.put("userPhone", userPhone);
			map.put("userType", userType);
			map.put("userStatus", "0");
			userService.addUser(map);
			ret = "succ";
		}catch(Exception e){
			e.printStackTrace();
		}
		out.print(ret);
		out.flush();
		out.close();
	}
	/**
	 * 修改用户
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/edit")
	public void edit(HttpServletRequest request, HttpServletResponse response){
		PrintWriter out = null;
		String ret = "fail";
		try{
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			String userName = request.getParameter("userName");
			String userId = request.getParameter("userId");
			String userPassword = request.getParameter("userPassword");
			String userMail = request.getParameter("userMail");
			String userPhone = request.getParameter("userPhone");
			String userType = request.getParameter("userType");
			userPassword = new SimpleHash("SHA-1", userName, userPassword).toString();	//密码加密
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userName", userName);
			map.put("userPassword", userPassword);
			map.put("userMail", userMail);
			map.put("userPhone", userPhone);
			map.put("userType", userType);
			map.put("userId", userId);
			userService.updateUser(map);
			ret = "succ";
		}catch(Exception e){
			e.printStackTrace();
		}
		out.print(ret);
		out.flush();
		out.close();
	}
	
	/* ===============================权限================================== */
	public Map<String, String> getHC(){
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
	}
	/* ===============================权限================================== */
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
