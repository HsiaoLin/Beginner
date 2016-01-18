package com.beginner.base.utils;

public class Logger {

	private org.apache.log4j.Logger log;

	/**
	* 创建一个新的实例 Logger,初始化Log4j的日志对象.
	* @param log4jLogger
	*/
	private Logger(org.apache.log4j.Logger log4jLogger) {
		log = log4jLogger;
	}

	/**
	* getLogger(方法描述：获取构造器，根据类初始化Logger对象) <br />
	* (方法适用条件描述： – 可选)
	* @param classObject Class对象
	* @return
	* Logger
	* @exception
	* @since 1.0.0
	*/
	public static Logger getLogger(@SuppressWarnings("rawtypes") Class classObject) {
		return new Logger(org.apache.log4j.Logger.getLogger(classObject));
	}

	/**
	* getLogger(方法描述：获取构造器，根据类名初始化Logger对象) <br />
	* (方法适用条件描述： – 可选)
	* @param loggerName 类名字符串
	* @return
	* Logger
	* @exception
	* @since 1.0.0
	*/
	public static Logger getLogger(String loggerName) {
		return new Logger(org.apache.log4j.Logger.getLogger(loggerName));
	}

	public void debug(Object object) {
		log.debug(object);
	}

	public void debug(Object object, Throwable e) {
		log.debug(object, e);
	}

	public void info(Object object) {
		log.info(object);
	}

	public void info(Object object, Throwable e) {
		log.info(object, e);
	}

	public void warn(Object object) {
		log.warn(object);
	}

	public void warn(Object object, Throwable e) {
		log.warn(object, e);
	}

	public void error(Object object) {
		log.error(object);
	}

	public void error(Object object, Throwable e) {
		log.error(object, e);
	}

	public void fatal(Object object) {
		log.fatal(object);
	}

	public String getName() {
		return log.getName();
	}

	public org.apache.log4j.Logger getLog4jLogger() {
		return log;
	}

	public boolean equals(Logger newLogger) {
		return log.equals(newLogger.getLog4jLogger());
	}
}