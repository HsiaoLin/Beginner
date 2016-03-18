package com.beginner.base.utils;

import java.net.UnknownHostException;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class TestConfig implements ApplicationListener<ContextRefreshedEvent> {

	private static Logger logger = LoggerFactory.getLogger(TestConfig.class);

	/*
	 * 配置中心-zookeeper方案初版：
	 * 
	 * TODO：1、解决多项目部署
	 * TODO：2、解决分布式部署
	 * TODO：3、解决本地化配置
	 * TODO：4、提供存储、通知、查询基础功能
	 * TODO：5、解决平滑升级-要有完善的版本管理机制
	 * TODO：6、解决侵入式设计-jar包、监听等等方案
	 * TODO：7、整体规划、流程规划、平滑升级规划
	 * TODO：8、解决高可用
	 * TODO：9、解决低延迟
	 * TODO：10、解决时效性
	 * dubbo如何使用zookeeper
	 */
	private static ZkClient zk;

	public static void main(String[] args) throws UnknownHostException {
		//InetAddress addr = InetAddress.getLocalHost();
		//logger.info("获取项目路径：{}", PathUtil.appName());
		//logger.info("获取项目路径：{}", addr.getHostAddress());
		//ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext(new String[] { "classpath:spring/applicationContext.xml" });
		//ApplicationContext app = new XmlWebApplicationContext("spring/applicationContext.xml");
		//TestConfig c = (TestConfig) app.getBean("aa");
		//testDataListener();
	}
	/**
	 * 客户端监听数据变更
	 */
	private static void testDataListener() {
		zk.writeData("/zk", "yinxiaoling");

		zk.subscribeDataChanges("/zk", new IZkDataListener() {

			@Override
			public void handleDataChange(String dataPath, Object data) throws Exception {
				logger.info("/zk的data变更了.");
				logger.info("变更后的值：" + data);
				logger.info("模拟调用URL地址");
				logger.info("返回数据初始化对象");//TODO：重点如何去兼容
			}

			@Override
			public void handleDataDeleted(String dataPath) throws Exception {
				logger.info("/zk的data被删除.");
			}

		});

		logger.info(zk.readData("/zk") + "");

		zk.writeData("/zk", "111");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		zk.writeData("/zk", "111");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {
			//			logger.info("获取IP：{}", PathUtil.ip());
			//			logger.info("获取PT：{}", PathUtil.port());
			//			zk = new ZkClient(new ZkConnection("127.0.0.1:2181", 100000));
			//			testDataListener();
		}
	}
}
