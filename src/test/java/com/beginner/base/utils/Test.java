package com.beginner.base.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {

	private static Logger logger = LoggerFactory.getLogger(Test.class);

	public static void main(String[] args) throws InterruptedException {
		//http://www.cnblogs.com/wanqieddy/p/3853863.html
		//		ExecutorService executor = Executors.newFixedThreadPool(10);
		//
		//		final List<String> list = new ArrayList<String>();
		//
		//		final CountDownLatch count = new CountDownLatch(10);
		//		for (int i = 0; i < 10; i++) {
		//			executor.execute(new Runnable() {
		//
		//				@Override
		//				public void run() {
		//					try {
		//						list.add(new Test().toString());
		//						logger.info(new Test().toString());
		//						Thread.sleep(2000);
		//					} catch (InterruptedException e) {
		//						e.printStackTrace();
		//					} finally {
		//						count.countDown();
		//					}
		//				}
		//			});
		//		}
		//		count.await(3, TimeUnit.SECONDS);
		//
		//		logger.info("list.size()={}", list.size());
		//		logger.info("是否终止:" + executor.isTerminated());
		//		logger.info("是否关闭:" + executor.isShutdown());
		//		if (!executor.isShutdown()) {
		//			executor.shutdownNow();
		//			executor = null;
		//		}
		//		logger.info("是否终止:" + executor.isTerminated());
		//		logger.info("是否关闭:" + executor.isShutdown());

		final List<Integer> list1 = new ArrayList<Integer>();
		for (int i = 0; i < 1000000; i++) {
			list1.add(i);
		}

		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			executor.execute(new Tets1(i, list1));
		}
		logger.info("list1全部加1之后：{}", list1.get(990000));
	}

	@Override
	public String toString() {
		return "我是Test";
	}

}

class Tets1 implements Runnable {

	private int i = 0;

	private List<Integer> list = null;

	public Tets1(int i, List<Integer> list) {
		this.i = i;
		this.list = list;
	}
	@Override
	public void run() {
		for (int j = i * 100000; j < (i + 1) * 100000 - 1; j++) {
			list.add(j, list.get(j) + 1);
		}
	}

}
