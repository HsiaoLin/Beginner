package com.beginner.base.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {

	private static Logger logger = LoggerFactory.getLogger(Test.class);

	public static void main(String[] args) throws InterruptedException {
		//http://www.cnblogs.com/wanqieddy/p/3853863.html
		ExecutorService executor = Executors.newFixedThreadPool(10);

		final List list = new ArrayList();

		final CountDownLatch count = new CountDownLatch(10);
		for (int i = 0; i < 10; i++) {
			executor.execute(new Runnable() {

				@Override
				public void run() {
					try {
						list.add(new Test().toString());
						logger.info(new Test().toString());
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						count.countDown();
					}
				}
			});
		}
		count.await(3, TimeUnit.SECONDS);

		logger.info("list.size()={}", list.size());
		logger.info("是否终止:" + executor.isTerminated());
		logger.info("是否关闭:" + executor.isShutdown());
		if (executor.isShutdown()) {
			executor.shutdownNow();
			executor = null;
		}
		logger.info("是否终止:" + executor.isTerminated());
		logger.info("是否关闭:" + executor.isShutdown());
	}

	@Override
	public String toString() {
		return "我是Test";
	}

}
