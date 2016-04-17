/*
 * Copyright 2015-9999 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.beginner.system.controller.user;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.beginner.core.common.Const;
import com.beginner.core.controller.BaseController;
import com.beginner.core.page.Page;
import com.beginner.core.plugin.PageData;
import com.beginner.core.utils.AppUtil;
import com.beginner.core.utils.ObjectExcelView;
import com.beginner.system.service.user.IUserService;

/**
 * <b>类名称：</b>UserController<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>Hsiao Lin Studio<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@Controller
@RequestMapping(value = "/system/user")
public class UserController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "userService")
	private IUserService userService;

	@Resource(name = "jedisPool")
	private JedisPool jedisPool;

	/**
	 * 新增
	 */
	@RequestMapping(value = "/save")
	public ModelAndView save() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			pd.put("USER_ID", null); //主键
			pd.put("USER_STATUS", "0"); //用户状态
			pd.put("LAST_LOGIN", ""); //最后登陆时间
			pd.put("PARENT_ID", "0"); //父用户ID
			pd.put("BUYING_AGENT_ID", "0"); //采购代理商ID
			pd.put("SUPPLIER_ID", "0"); //供应商ID
			userService.save(pd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("msg", "success");
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public void delete(PrintWriter out) {
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			userService.delete(pd);
			out.write("success");
			out.close();
			logger.info("删除User成功");
		} catch (Exception e) {
			logger.error("删除User失败", e);
		}
	}

	/**
	 * 修改
	 */
	@RequestMapping(value = "/edit")
	public ModelAndView edit() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			userService.edit(pd);
			logger.info("修改用户信息成功");
		} catch (Exception e) {
			logger.error("修改用户信息失败", e);
		}
		mv.addObject("msg", "success");
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Page page) {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			//testPool();

			pd = this.getPageData();
			page.setPd(pd);
			List<PageData> varList = userService.list(page); //列出User列表
			mv.addObject("varList", varList);
		} catch (Exception e) {
			logger.error("用户列表查询失败", e);
		}
		mv.setViewName("system/user/user_list");
		mv.addObject("pd", pd);
		mv.addObject(Const.ROLE_RIGHTS, this.getRights()); //按钮权限
		return mv;
	}

	/**
	 * 测试Redis连接池
	 */
	private void testPool() {
		logger.info("判断jedisPool是否为null：{}", null == jedisPool);
		// try-with-resource方式自动关闭Jedis连接（JDK1.7及以上）
		try (Jedis jedis = jedisPool.getResource()) {
			// 一key一值
			jedis.set("测试key", "测试值");
			System.out.println("‘测试key’的值是：" + jedis.get("测试key"));

			// 存储数据到列表中
			jedis.lpush("test-list", "Redis");
			jedis.lpush("test-list", "Mongodb");
			jedis.lpush("test-list", "Mysql");
			// 获取存储的数据并输出
			List<String> list = jedis.lrange("test-list", 0, 3);
			// 取得的List按照数据放入顺序的倒序排列
			for (int i = 0; i < list.size(); i++) {
				System.out.println("test-list" + i + "：" + list.get(i));
			}

			// 清空当前DB
			jedis.flushDB();
		} catch (Exception e) {
			logger.error("测试Redis连接池异常", e);
		}
	}

	/**
	 * 去新增页面
	 */
	@RequestMapping(value = "/goAdd")
	public ModelAndView goAdd() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/user/user_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}

	/**
	 * 去修改页面
	 */
	@RequestMapping(value = "/goEdit")
	public ModelAndView goEdit() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = userService.findById(pd); //根据ID读取
		} catch (Exception e) {
			logger.error("根据ID查询用户失败", e);
		}
		mv.setViewName("system/user/user_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}

	/**
	 * 批量删除
	 */
	@RequestMapping(value = "/deleteAll")
	@ResponseBody
	public Object deleteAll() {
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

			logger.info("批量删除User成功");
		} catch (Exception e) {
			logger.error("批量删除User失败", e);
		}
		return AppUtil.returnObject(pd, map);
	}

	/*
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value = "/excel")
	public ModelAndView exportExcel() {
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

			logger.info("导出User到excel成功");
		} catch (Exception e) {
			logger.error("导出User到excel失败", e);
		}
		return mv;
	}
}
