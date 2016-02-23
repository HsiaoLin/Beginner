package com.beginner.base.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestJava7 {

	private static Logger logger = LoggerFactory.getLogger(TestJava7.class);

	/**
	 * 测试JavaSE-1.7新特性
	 */
	public static void main(String[] args) {
		logger.info("JavaSE-1.7新特性一：try-with-resources：");
		testTryWithResources();
		logger.info("");

		logger.info("JavaSE-1.7新特性二：switch语句条件表达式：");
		testSwitch("JavaSE-1.7新特性二：switch语句条件表达式支持String类型啦");
		testSwitch(Testswitch.MAN);
		testSwitch(Testswitch.WOMAN);
		logger.info("");

		logger.info("JavaSE-1.7新特性三：数值字面量的改进：");
		testValue();
		logger.info("");

		logger.info("JavaSE-1.7新特性四：异常处理优化：详情参看2个方法区别");
		testException();
		testException1();
		logger.info("");

		logger.info("JavaSE-1.7新特性五：优化变长参数调用：");
		logger.info("变长参数的基本用法：" + testParam(1, 2, 3));
		logger.info("变长参数的泛型用法：" + testParam1(1, 2, 3));
		logger.info("");
	}

	private static int testParam(int... num) {
		int j = 0;
		for (int i : num) {
			j += i;
		}
		return j;
	}
	/**
	 * 因为参数返回值是不确定的，造成类型不安全，编译器会警告，java7提供新的注解@SafeVarargs，
	 * 如果你确信方法不会造成错误可以和原来抑制编译器警告@SuppressWarnings("unchecked")一样使用
	 */
	@SafeVarargs
	private static <T> T testParam1(T... num) {
		return num.length > 0 ? num[0] : null;
	}

	private static void testException() {
		File file = new File("");
		FileReader r = null;
		try {
			r = new FileReader(file);
			r.read();
		} catch (IOException e) {
			logger.info("异常写法1");
		} finally {
			if (null != r) {
				try {
					r.close();
				} catch (IOException e) {
					logger.info("异常写法1");
				}
			}
		}
	}
	private static void testException1() {
		File file = new File("");
		try (FileReader r = new FileReader(file)) {
			r.read();
		} catch (IOException | RuntimeException e) {
			logger.info("异常写法2");
		}
	}

	/**
	 * 在编程语言中，字面量(literal)指的是在源代码中直接表示一个固定的值：
	 * 在java7以前支持10进制、8进制、16进制
	 * 现在开始支持二进制了哦，只需在数字前面加上"0b"或者"0B"
	 * <p>
	 * 使用下划线分割便于阅读，例如：900_000_000等价于900000000
	 * </p>
	 */
	private static void testValue() {
		//二进制：整型前面加"0b"或者"0B"
		int j = 0b111;
		logger.info("7的二进制字面量：0b111=" + j);
		//八进制：整型前面加"0"
		int m = 07;
		logger.info("7的八进制字面量：07=" + m);
		//十进制：默认支持
		int n = 7;
		logger.info("7的十进制字面量：7=" + n);
		//十六进制：整型前面加"0x"或者"0X"
		int i = 0x7;
		logger.info("7的十六进制字面量：0x7=" + i);
		logger.info("使用下划线分割便于阅读，例如：900_000_000=" + 900_000_000);
		logger.info("使用下划线分割便于阅读，例如：1_3.141_592_653=" + 1_3.141_592_653);
	}

	/**
	 * JDK7之前switch语句条件表达式只能是和int兼容的类型:char、byte、short、int
	 * 以及这些基本类型的封装类型Character、Byte、Short、Integer另外还有枚举类型.
	 * <p>
	 * 实现原理：在编译器上实现，在java虚拟机和字节码层次依然保持使用与int兼容的类型，例如在OpenJDK中反编译后的class文件可以看到OpenJDK使用
	 * 的是字符串的hashCode来进行比较的，而java虚拟机所看到的还是与int兼容的类型；
	 * 而且case字句中使用的也是equals方法进行比较是否相等，这是因为hash函数在映射的时候可能存在冲突，多个字符串的值可能一样；
	 * 为了保持代码与源代码逻辑的一致性所以还是使用equals方法去判断相等。
	 * </p>
	 * 建议：如果代码中有多处地方使用switch语句枚举字符串，就考虑用枚举类型进行替换。（下面演示枚举类型替换方案）
	 * @param s 传入的条件表达式
	 */
	public static void testSwitch(String s) {
		switch (s) {//字符串参数
		case "JavaSE-1.7新特性二：switch语句条件表达式支持String类型啦"://字符串常量
			if (s.equals("JavaSE-1.7新特性二：switch语句条件表达式支持String类型啦"))
				logger.info("JavaSE-1.7新特性二：switch语句条件表达式支持String拯救不开心！");
			break;
		default:
			break;
		}
	}
	public static void testSwitch(Testswitch s) {
		switch (s) {//字符串参数
		case MAN://字符串常量
			logger.info("但是不要高兴的太早哦￣へ￣，如果代码中有多处地方使用switch语句枚举字符串，");
			logger.info("就考虑用枚举类型进行替换,而不是无脑使用字符串类型：");
			logger.info("男");
			break;
		case WOMAN:
			logger.info("枚举的名字：" + Testswitch.WOMAN.name());
			logger.info("枚举的序号：" + Testswitch.WOMAN.ordinal());
			logger.info("枚举的描述：" + Testswitch.WOMAN.getDESC());
			logger.info("枚举的描述：" + Testswitch.WOMAN.DESC);
			break;
		default:
			logger.info("女");
			break;
		}
	}
	enum Testswitch {
		MAN("男"), WOMAN("女");

		final String DESC;

		private Testswitch(String DESC) {
			this.DESC = DESC;
		}
		public String getDESC() {
			return DESC;
		}
	}

	/**
	 * 资源管理新用法
	 */
	public static void testTryWithResources() {
		final String URL = "jdbc:mysql://localhost:3306/beginner?useUnicode=true&characterEncoding=utf-8";
		final String DRIVER = "com.mysql.jdbc.Driver";
		final String USERNAME = "root";
		final String PASSWORD = "shijie99";

		ResultSet rs = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD); Statement st = conn.createStatement()) {
			Class.forName(DRIVER);
			String sql = "SELECT * FROM PUB_USER T";
			rs = st.executeQuery(sql);

			rs.last();
			logger.info("查询得到的总行数{}", rs.getRow());

			ResultSetMetaData rsmd = rs.getMetaData();
			logger.info("查询得到的总列数{}", rsmd.getColumnCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
