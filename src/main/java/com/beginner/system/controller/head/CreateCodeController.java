/**
* <b>项目名：</b>勿忘初心方得始终<br/>
* <b>包名：</b>com.beginner.system.controller<br/>
* <b>文件名：</b>CreateCodeController.java<br/>
* <b>版本信息：</b><br/>
* <b>日期：</b>2015年10月26日-下午3:18:18<br/>
* <b>Copyright (c)</b> 2015-2016 Hsiao Lin Studio-版权所有<br/>
*/
package com.beginner.system.controller.head;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.beginner.base.controller.BaseController;
import com.beginner.base.plugin.page.PageData;
import com.beginner.base.utils.PublicUtil;
import com.beginner.base.utils.generatecode.DelAllFile;
import com.beginner.base.utils.generatecode.FileDownload;
import com.beginner.base.utils.generatecode.FileZip;
import com.beginner.base.utils.generatecode.Freemarker;

/**
* <b>类名称：</b>CreateCodeController<br/>
* <b>类描述：</b>生成代码<br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
@Controller
@RequestMapping(value = "/createCode")
public class CreateCodeController extends BaseController {

	@RequestMapping(value = "/proCode")
	public void proCode(HttpServletResponse response) throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();

		String businessOrsystem = pd.getString("businessOrsystem"); //包名
		String packageName = pd.getString("packageName"); //包名
		String objectName = pd.getString("objectName"); //类名
		String tabletop = pd.getString("tabletop"); //表前缀
		tabletop = null == tabletop ? "" : tabletop.toUpperCase(); //表前缀转大写
		String zindext = pd.getString("zindex"); //属性总数
		int zindex = 0;
		if (null != zindext && !"".equals(zindext)) {
			zindex = Integer.parseInt(zindext);
		}
		List<String[]> fieldList = new ArrayList<String[]>(); //属性集合
		for (int i = 0; i < zindex; i++) {
			fieldList.add(pd.getString("field" + i).split(",fh,")); //属性放到集合里面
		}

		Map<String, Object> root = new HashMap<String, Object>(); //创建数据模型
		root.put("fieldList", fieldList);
		root.put("packageName", packageName); //包名
		root.put("objectName", objectName); //类名
		root.put("objectNameLower", objectName.toLowerCase()); //类名(全小写)
		root.put("objectNameUpper", objectName.toUpperCase()); //类名(全大写)
		root.put("tabletop", tabletop); //表前缀	
		root.put("nowDate", new Date()); //当前日期

		DelAllFile.delFolder(PublicUtil.getClasspath() + "admin/ftl"); //生成代码前,先清空之前生成的代码

		String filePath = "admin/ftl/code/"; //存放路径
		String ftlPath = "createCode"; //ftl路径

		if ("business".equals(businessOrsystem)) {
			/*生成controller*/
			Freemarker.printFile("business_controllerTemplate.ftl", root, businessOrsystem + "/controller/" + objectName.toLowerCase() + "/"
					+ objectName + "Controller.java", filePath, ftlPath);

			/*生成service接口*/
			Freemarker.printFile("business_serviceInterface.ftl", root, businessOrsystem + "/service/" + objectName.toLowerCase() + "/I" + objectName
					+ "Service.java", filePath, ftlPath);
			/*生成service实现类*/
			Freemarker.printFile("business_serviceTemplate.ftl", root, businessOrsystem + "/service/" + objectName.toLowerCase() + "/" + objectName
					+ "Service.java", filePath, ftlPath);
		} else if ("system".equals(businessOrsystem)) {
			/*生成controller*/
			Freemarker.printFile("system_controllerTemplate.ftl", root, businessOrsystem + "/controller/" + objectName.toLowerCase() + "/"
					+ objectName + "Controller.java", filePath, ftlPath);

			/*生成service接口*/
			Freemarker.printFile("system_serviceInterface.ftl", root, businessOrsystem + "/service/" + objectName.toLowerCase() + "/I" + objectName
					+ "Service.java", filePath, ftlPath);
			/*生成service实现类*/
			Freemarker.printFile("system_serviceTemplate.ftl", root, businessOrsystem + "/service/" + objectName.toLowerCase() + "/" + objectName
					+ "Service.java", filePath, ftlPath);
		}

		/*生成mybatis xml*/
		Freemarker.printFile("mapperMysqlTemplate.ftl", root, "mybatis_mysql/" + businessOrsystem + "/" + packageName + "/" + objectName
				+ "Mapper.xml", filePath, ftlPath);
		//Freemarker.printFile("mapperOracleTemplate.ftl", root, "mybatis_oracle/" + packageName + "/" + objectName + "Mapper.xml", filePath, ftlPath);

		/*生成SQL脚本*/
		Freemarker.printFile("mysql_SQL_Template.ftl", root, "mysql数据库脚本/" + tabletop + objectName.toUpperCase() + ".sql", filePath, ftlPath);
		//Freemarker.printFile("oracle_SQL_Template.ftl", root, "oracle数据库脚本/" + tabletop + objectName.toUpperCase() + ".sql", filePath, ftlPath);

		/*生成jsp页面*/
		Freemarker.printFile("jsp_list_Template.ftl", root,
				"jsp/" + businessOrsystem + "/" + objectName.toLowerCase() + "/" + objectName.toLowerCase() + "_list.jsp", filePath, ftlPath);
		Freemarker.printFile("jsp_edit_Template.ftl", root,
				"jsp/" + businessOrsystem + "/" + objectName.toLowerCase() + "/" + objectName.toLowerCase() + "_edit.jsp", filePath, ftlPath);

		/*生成说明文档*/
		//Freemarker.printFile("docTemplate.ftl", root, "说明.doc", filePath, ftlPath);

		/*生成的全部代码压缩成zip文件*/
		FileZip.zip(PublicUtil.getClasspath() + "admin/ftl/code");

		/*下载代码*/
		FileDownload.fileDownload(response, PublicUtil.getClasspath() + "admin/ftl/code.zip", "code.zip");
	}
}
