package com.ran.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateCode {
	public static void main(String[] args) throws Exception {
		/* ============================================================================================= */
		String packageName = "back";  			//包名				========1
//		String objectName = "Log";	   			//类名				========2
		
		String[] arr = {"Statistics"};
		for (String objectName : arr){
			String tabletop = "back_";	   				//表前缀				========3
			tabletop = null == tabletop?"":tabletop.toLowerCase();		//表前缀转大写

			int zindex = 0;
			String fields = "";
			List<String[]> fieldList = new ArrayList<String[]>();   	//属性集合			========4
			for(int i=0; i< zindex; i++){
				fieldList.add(fields.split(",99,"));	//属性放到集合里面
			}
			
			Map<String,Object> root = new HashMap<String,Object>();		//创建数据模型
			root.put("fieldList", fieldList);
			root.put("packageName", packageName);						//包名
			root.put("objectName", objectName);							//类名
			root.put("objectNameLower", objectName.toLowerCase());		//类名(全小写)
			root.put("objectNameUpper", objectName.toLowerCase());		//类名(全大写)
			root.put("tabletop", tabletop);								//表前缀	
			root.put("nowDate", new Date());							//当前日期
			
//			DelAllFile.delFolder(PathUtil.getClasspath()+"admin/ftl"); //生成代码前,先清空之前生成的代码
			/* ============================================================================================= */
			
			String filePath = "admin/ftl/"+packageName+"/"+objectName+"/";						//存放路径
			DelAllFile.delFolder(PathUtil.getClasspath()+filePath);
			String ftlPath = "createCode";								//ftl路径
			
			/*生成controller*/
			Freemarker.printFile("controllerTemplate.ftl", root, "controller/"+packageName+"/"+objectName.toLowerCase()+"/"+objectName+"Controller.java", filePath, ftlPath);
			
			/*生成service*/
			Freemarker.printFile("serviceTemplate.ftl", root, "service/"+packageName+"/"+objectName.toLowerCase()+"/"+objectName+"Service.java", filePath, ftlPath);

			/*生成mybatis xml*/
			Freemarker.printFile("mapperMysqlTemplate.ftl", root, "mybatis_mysql/"+packageName+"/"+objectName+"Mapper.xml", filePath, ftlPath);
//			Freemarker.printFile("mapperOracleTemplate.ftl", root, "mybatis_oracle/"+packageName+"/"+objectName+"Mapper.xml", filePath, ftlPath);
			
			/*生成SQL脚本*/
			Freemarker.printFile("mysql_SQL_Template.ftl", root, "mysql数据库脚本/"+tabletop+objectName.toLowerCase()+".sql", filePath, ftlPath);
			//Freemarker.printFile("oracle_SQL_Template.ftl", root, "oracle数据库脚本/"+tabletop+objectName.toUpperCase()+".sql", filePath, ftlPath);
			
			/*生成jsp页面*/
			Freemarker.printFile("jsp_list_Template.ftl", root, "jsp/"+packageName+"/"+objectName.toLowerCase()+"/"+objectName.toLowerCase()+"_list.jsp", filePath, ftlPath);
			Freemarker.printFile("jsp_edit_Template.ftl", root, "jsp/"+packageName+"/"+objectName.toLowerCase()+"/"+objectName.toLowerCase()+"_edit.jsp", filePath, ftlPath);
			
			/*生成说明文档*/
			Freemarker.printFile("docTemplate.ftl", root, "说明.doc", filePath, ftlPath);
			
			//this.print("oracle_SQL_Template.ftl", root);  控制台打印
			
			/*生成的全部代码压缩成zip文件*/
			//FileZip.zip(PathUtil.getClasspath()+"admin/ftl/code", PathUtil.getClasspath()+"admin/ftl/code.zip");
			
			System.out.println("CreateCode...");
		}
		
	}
}
