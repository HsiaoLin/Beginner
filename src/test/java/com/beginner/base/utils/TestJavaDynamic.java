package com.beginner.base.utils;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestJavaDynamic {

	private static Logger logger = LoggerFactory.getLogger(TestJavaDynamic.class);

	/**
	 * java动态性
	 * @param args 参数列表
	 */
	public static void main(String[] args) {
		//脚本语言支持API
		testScriptAPI1();
		testScriptAPI2();
	}

	/**
	 * 脚本语言API：语言绑定
	 */
	private static void testScriptAPI2() {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = (ScriptEngine) manager.getEngineByName("JavaScript");
		if (null == engine)
			throw new RuntimeException("未找到JavaScript语言执行引擎！");
		try {
			logger.info("语言绑定的默认绑定对象使用：");
			engine.put("name", "testScriptAPI2-1");
			engine.eval("var msg = 'Hello ' + name;");
			engine.eval("println(msg)");
			logger.info("获取脚本语言的变量msg：" + engine.get("msg"));

			logger.info("语言绑定的自定义绑定对象使用：");
			Bindings bindings = new SimpleBindings();
			bindings.put("name", "testScriptAPI2-2");
			engine.eval("var msg = 'Hello ' + name;println(msg)", bindings);
			logger.info("通过eval方法传递的自定义绑定对象仅在当前eval调用中有效，并不会改变引擎默认的语言绑定对象:");
			logger.info("获取name：" + engine.get("name"));

		} catch (ScriptException e) {
			logger.error("脚本执行异常", e);
		}
	}
	/**
	 * 脚本语言API:获取脚本引擎的三种方式
	 */
	private static void testScriptAPI1() {
		//脚本引擎管理器,Java默认支持Javascript脚本语言：基于对象和事件驱动的客户端脚本语言
		//其他脚本语言则需要下载脚本引擎的库放在类路径下（遵循JSR 223）。
		ScriptEngineManager manager = new ScriptEngineManager();
		logger.info("可以通过三种方式找到JavaScript语言执行引擎：名称、扩展名、Mime类型：");
		//获取引擎的三种方式：
		//1、根据语言的名称
		ScriptEngine engine1 = (ScriptEngine) manager.getEngineByName("JavaScript");
		//2、根据语言文件的后缀名、扩展名
		ScriptEngine engine2 = (ScriptEngine) manager.getEngineByExtension("js");
		//3、根据语言Mime类型
		ScriptEngine engine3 = (ScriptEngine) manager.getEngineByMimeType("text/javascript");
		if (null == engine1 || null == engine2 || null == engine3)
			throw new RuntimeException("未找到JavaScript语言执行引擎！");
		try {
			engine1.eval("println('Hello World 1')");
			engine2.eval("println('Hello World 2')");
			engine3.eval("println('Hello World 3')");
		} catch (ScriptException e) {
			logger.error("脚本执行异常", e);
		}
	}
}
