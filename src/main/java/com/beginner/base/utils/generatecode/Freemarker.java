/*
 * Copyright 2015-2017 the original author or authors.
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
package com.beginner.base.utils.generatecode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import com.beginner.base.utils.PublicUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * <b>类名称：</b>Freemarker工具类<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>Hsiao Lin Studio<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class Freemarker {

	/**
	 * 打印到控制台(测试用)
	 */
	public static void print(String ftlName, Map<String, Object> root, String ftlPath) throws Exception {
		try {
			Template temp = getTemplate(ftlName, ftlPath); //通过Template可以将模板文件输出到相应的流
			temp.process(root, new PrintWriter(System.out));
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 输出到输出到文件
	 * @param ftlName	ftl文件名
	 * @param root		传入的map
	 * @param outFile	输出后的文件全部路径
	 * @param filePath	输出前的文件上部路径
	 */
	public static void printFile(String ftlName, Map<String, Object> root, String outFile, String filePath, String ftlPath) throws Exception {
		try {
			File file = new File(PublicUtil.getClasspath() + filePath + outFile);
			if (!file.getParentFile().exists()) { //判断有没有父路径，就是判断文件整个路径是否存在
				file.getParentFile().mkdirs(); //不存在就全部创建
			}
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			Template template = getTemplate(ftlName, ftlPath);
			template.process(root, out); //模版输出
			out.flush();
			out.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过文件名加载模版
	 * @param ftlName 文件名称
	 * @param ftlPath 文件路径
	 */
	public static Template getTemplate(String ftlName, String ftlPath) throws Exception {
		try {
			Configuration cfg = new Configuration(); //通过Freemaker的Configuration读取相应的ftl
			cfg.setEncoding(Locale.CHINA, "utf-8");
			cfg.setDirectoryForTemplateLoading(new File(PublicUtil.getClassResources() + "/ftl/" + ftlPath)); //设定去哪里读取相应的ftl模板文件
			Template temp = cfg.getTemplate(ftlName); //在模板文件目录中找到名称为name的文件
			return temp;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
