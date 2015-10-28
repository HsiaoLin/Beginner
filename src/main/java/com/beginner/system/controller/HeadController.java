package com.beginner.system.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.beginner.base.controller.BaseController;
import com.beginner.common.Constant;
import com.beginner.plugin.page.PageData;
import com.beginner.system.bean.User;
import com.beginner.utils.AppUtil;
import com.beginner.utils.SmsUtil;
import com.beginner.utils.Tools;
import com.beginner.utils.Watermark;
import com.beginner.utils.mail.SimpleMailSender;

/** 
 * 类名称：HeadController
 * @version
 */
@Controller
@RequestMapping(value = "/head")
public class HeadController extends BaseController {

	//	@Resource(name = "userService")
	//	private UserService userService;
	//	@Resource(name = "appuserService")
	//	private AppuserService appuserService;

	/**
	 * 获取头部信息
	 */
	@RequestMapping(value = "/getUname")
	@ResponseBody
	public Object getList() {
		before(logger, "HeadController.getList()获取头部信息");
		PageData pd = new PageData();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			pd = this.getPageData();
			List<Map> pdList = new ArrayList<Map>();

			//shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();

			Map pds = new HashMap();
			pds = (Map) session.getAttribute(Constant.USER_PAGEDATA);

			if (MapUtils.isEmpty(pds)) {
				//获取当前登录者loginname
				String USERNAME = (String) currentUser.getPrincipal();
				pd.put("USERNAME", USERNAME);
				User user = new User();
				user.setUserId(1);
				user.setUserName("尹枭凌");
				user.setName("尹枭凌");
				user.setUserPassword("123");
				pds = BeanUtils.describe(user);
				//pds = userService.getUserByName(pd);
				session.setAttribute(Constant.USER_PAGEDATA, pds);
			}
			pdList.add(pds);
			map.put("list", pdList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			after(logger);
		}
		return AppUtil.returnObject(pd, map);
	}

	/**
	 * 保存皮肤
	 */
	@RequestMapping(value = "/setSKIN")
	public void setSKIN(PrintWriter out) {
		PageData pd = new PageData();
		try {
			pd = this.getPageData();

			//shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();

			String USERNAME = session.getAttribute(Constant.USERNAME).toString();//获取当前登录者loginname
			pd.put("USERNAME", USERNAME);
			//userService.setSKIN(pd);
			session.removeAttribute(Constant.USER_OBJ);
			session.removeAttribute(Constant.USER_PAGEDATA);
			out.write("success");
			out.close();
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

	}

	/**
	 * 去编辑邮箱页面
	 */
	@RequestMapping(value = "/editEmail")
	public ModelAndView editEmail() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/head/edit_email");
		mv.addObject("pd", pd);
		return mv;
	}

	/**
	 * 去发送短信页面
	 */
	@RequestMapping(value = "/goSendSms")
	public ModelAndView goSendSms() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/head/send_sms");
		mv.addObject("pd", pd);
		return mv;
	}

	/**
	 * 发送短信
	 */
	@RequestMapping(value = "/sendSms")
	@ResponseBody
	public Object sendSms() {
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String, Object> map = new HashMap<String, Object>();
		String msg = "ok"; //发送状态
		int count = 0; //统计发送成功条数
		int zcount = 0; //理论条数

		List<PageData> pdList = new ArrayList<PageData>();

		String PHONEs = pd.getString("PHONE"); //对方邮箱
		String CONTENT = pd.getString("CONTENT"); //内容
		String isAll = pd.getString("isAll"); //是否发送给全体成员 yes or no
		String TYPE = pd.getString("TYPE"); //类型 1：短信接口1   2：短信接口2
		String fmsg = pd.getString("fmsg"); //判断是系统用户还是会员 "appuser"为会员用户

		if ("yes".endsWith(isAll)) {
			try {
				List<PageData> userList = new ArrayList<PageData>();

				//userList = "appuser".equals(fmsg) ? appuserService.listAllUser(pd) : userService.listAllUser(pd);

				zcount = userList.size();
				try {
					for (int i = 0; i < userList.size(); i++) {
						if (Tools.checkMobileNumber(userList.get(i).getString("PHONE"))) { //手机号格式不对就跳过
							if ("1".equals(TYPE)) {
								SmsUtil.sendSms1(userList.get(i).getString("PHONE"), CONTENT); //调用发短信函数1
							} else {
								SmsUtil.sendSms2(userList.get(i).getString("PHONE"), CONTENT); //调用发短信函数2
							}
							count++;
						} else {
							continue;
						}
					}
					msg = "ok";
				} catch (Exception e) {
					msg = "error";
				}

			} catch (Exception e) {
				msg = "error";
			}
		} else {
			PHONEs = PHONEs.replaceAll("；", ";");
			PHONEs = PHONEs.replaceAll(" ", "");
			String[] arrTITLE = PHONEs.split(";");
			zcount = arrTITLE.length;
			try {
				for (int i = 0; i < arrTITLE.length; i++) {
					if (Tools.checkMobileNumber(arrTITLE[i])) { //手机号式不对就跳过
						if ("1".equals(TYPE)) {
							SmsUtil.sendSms1(arrTITLE[i], CONTENT); //调用发短信函数1
						} else {
							SmsUtil.sendSms2(arrTITLE[i], CONTENT); //调用发短信函数2
						}
						count++;
					} else {
						continue;
					}
				}
				msg = "ok";
			} catch (Exception e) {
				msg = "error";
			}
		}
		pd.put("msg", msg);
		pd.put("count", count); //成功数
		pd.put("ecount", zcount - count); //失败数
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}

	/**
	 * 去发送电子邮件页面
	 */
	@RequestMapping(value = "/goSendEmail")
	public ModelAndView goSendEmail() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/head/send_email");
		mv.addObject("pd", pd);
		return mv;
	}

	/**
	 * 发送电子邮件
	 */
	@RequestMapping(value = "/sendEmail")
	@ResponseBody
	public Object sendEmail() {
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String, Object> map = new HashMap<String, Object>();
		String msg = "ok"; //发送状态
		int count = 0; //统计发送成功条数
		int zcount = 0; //理论条数

		String strEMAIL = Tools.readTxtFile(Constant.EMAIL); //读取邮件配置

		List<PageData> pdList = new ArrayList<PageData>();

		String toEMAIL = pd.getString("EMAIL"); //对方邮箱
		String TITLE = pd.getString("TITLE"); //标题
		String CONTENT = pd.getString("CONTENT"); //内容
		String TYPE = pd.getString("TYPE"); //类型
		String isAll = pd.getString("isAll"); //是否发送给全体成员 yes or no

		String fmsg = pd.getString("fmsg"); //判断是系统用户还是会员 "appuser"为会员用户

		if (null != strEMAIL && !"".equals(strEMAIL)) {
			String strEM[] = strEMAIL.split(",fh,");
			if (strEM.length == 4) {
				if ("yes".endsWith(isAll)) {
					try {
						List<PageData> userList = new ArrayList<PageData>();

						userList = new ArrayList();//"appuser".equals(fmsg) ? appuserService.listAllUser(pd) : userService.listAllUser(pd);

						zcount = userList.size();
						try {
							for (int i = 0; i < userList.size(); i++) {
								if (Tools.checkEmail(userList.get(i).getString("EMAIL"))) { //邮箱格式不对就跳过
									SimpleMailSender.sendEmail(strEM[0], strEM[1], strEM[2], strEM[3], userList.get(i).getString("EMAIL"), TITLE,
											CONTENT, TYPE);//调用发送邮件函数
									count++;
								} else {
									continue;
								}
							}
							msg = "ok";
						} catch (Exception e) {
							msg = "error";
						}

					} catch (Exception e) {
						msg = "error";
					}
				} else {
					toEMAIL = toEMAIL.replaceAll("；", ";");
					toEMAIL = toEMAIL.replaceAll(" ", "");
					String[] arrTITLE = toEMAIL.split(";");
					zcount = arrTITLE.length;
					try {
						for (int i = 0; i < arrTITLE.length; i++) {
							if (Tools.checkEmail(arrTITLE[i])) { //邮箱格式不对就跳过
								SimpleMailSender.sendEmail(strEM[0], strEM[1], strEM[2], strEM[3], arrTITLE[i], TITLE, CONTENT, TYPE);//调用发送邮件函数
								count++;
							} else {
								continue;
							}
						}
						msg = "ok";
					} catch (Exception e) {
						msg = "error";
					}
				}
			} else {
				msg = "error";
			}
		} else {
			msg = "error";
		}
		pd.put("msg", msg);
		pd.put("count", count); //成功数
		pd.put("ecount", zcount - count); //失败数
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}

	/**
	 * 去系统设置页面
	 */
	@RequestMapping(value = "/goSystem")
	public ModelAndView goEditEmail() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("YSYNAME", Tools.readTxtFile(Constant.SYSNAME)); //读取系统名称
		pd.put("COUNTPAGE", Tools.readTxtFile(Constant.PAGE)); //读取每页条数
		String strEMAIL = Tools.readTxtFile(Constant.EMAIL); //读取邮件配置
		String strSMS1 = Tools.readTxtFile(Constant.SMS1); //读取短信1配置
		String strSMS2 = Tools.readTxtFile(Constant.SMS2); //读取短信2配置
		String strFWATERM = Tools.readTxtFile(Constant.FWATERM); //读取文字水印配置
		String strIWATERM = Tools.readTxtFile(Constant.IWATERM); //读取图片水印配置
		pd.put("Token", Tools.readTxtFile(Constant.WEIXIN)); //读取微信配置

		if (null != strEMAIL && !"".equals(strEMAIL)) {
			String strEM[] = strEMAIL.split(",fh,");
			if (strEM.length == 4) {
				pd.put("SMTP", strEM[0]);
				pd.put("PORT", strEM[1]);
				pd.put("EMAIL", strEM[2]);
				pd.put("PAW", strEM[3]);
			}
		}

		if (null != strSMS1 && !"".equals(strSMS1)) {
			String strS1[] = strSMS1.split(",fh,");
			if (strS1.length == 2) {
				pd.put("SMSU1", strS1[0]);
				pd.put("SMSPAW1", strS1[1]);
			}
		}

		if (null != strSMS2 && !"".equals(strSMS2)) {
			String strS2[] = strSMS2.split(",fh,");
			if (strS2.length == 2) {
				pd.put("SMSU2", strS2[0]);
				pd.put("SMSPAW2", strS2[1]);
			}
		}

		if (null != strFWATERM && !"".equals(strFWATERM)) {
			String strFW[] = strFWATERM.split(",fh,");
			if (strFW.length == 5) {
				pd.put("isCheck1", strFW[0]);
				pd.put("fcontent", strFW[1]);
				pd.put("fontSize", strFW[2]);
				pd.put("fontX", strFW[3]);
				pd.put("fontY", strFW[4]);
			}
		}

		if (null != strIWATERM && !"".equals(strIWATERM)) {
			String strIW[] = strIWATERM.split(",fh,");
			if (strIW.length == 4) {
				pd.put("isCheck2", strIW[0]);
				pd.put("imgUrl", strIW[1]);
				pd.put("imgX", strIW[2]);
				pd.put("imgY", strIW[3]);
			}
		}

		mv.setViewName("system/head/sys_edit");
		mv.addObject("pd", pd);

		return mv;
	}

	/**
	 * 保存系统设置1
	 */
	@RequestMapping(value = "/saveSys")
	public ModelAndView saveSys() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Tools.writeFile(Constant.SYSNAME, pd.getString("YSYNAME")); //写入系统名称
		Tools.writeFile(Constant.PAGE, pd.getString("COUNTPAGE")); //写入每页条数
		Tools.writeFile(Constant.EMAIL,
				pd.getString("SMTP") + ",fh," + pd.getString("PORT") + ",fh," + pd.getString("EMAIL") + ",fh," + pd.getString("PAW")); //写入邮件服务器配置
		Tools.writeFile(Constant.SMS1, pd.getString("SMSU1") + ",fh," + pd.getString("SMSPAW1")); //写入短信1配置
		Tools.writeFile(Constant.SMS2, pd.getString("SMSU2") + ",fh," + pd.getString("SMSPAW2")); //写入短信2配置
		mv.addObject("msg", "OK");
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 保存系统设置2
	 */
	@RequestMapping(value = "/saveSys2")
	public ModelAndView saveSys2() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Tools.writeFile(Constant.FWATERM, pd.getString("isCheck1") + ",fh," + pd.getString("fcontent") + ",fh," + pd.getString("fontSize") + ",fh,"
				+ pd.getString("fontX") + ",fh," + pd.getString("fontY")); //文字水印配置
		Tools.writeFile(Constant.IWATERM,
				pd.getString("isCheck2") + ",fh," + pd.getString("imgUrl") + ",fh," + pd.getString("imgX") + ",fh," + pd.getString("imgY")); //图片水印配置
		Watermark.fushValue();
		mv.addObject("msg", "OK");
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 保存系统设置3
	 */
	@RequestMapping(value = "/saveSys3")
	public ModelAndView saveSys3() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Tools.writeFile(Constant.WEIXIN, pd.getString("Token")); //写入微信配置
		mv.addObject("msg", "OK");
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 去代码生成器页面
	 */
	@RequestMapping(value = "/goProductCode")
	public ModelAndView goProductCode() throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/head/productCode");
		return mv;
	}

}
