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

		final List<Integer> list1 = new ArrayList<Integer>(100);
		for (int i = 0; i < 100; i++) {
			list1.add(i);
		}

		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			Tets1 newTets1 = new Tets1();
			newTets1.setI(i);
			newTets1.setList(list1);
			executor.execute(newTets1);
		}

		if (!executor.isShutdown()) {
			executor.shutdownNow();
			executor = null;
		}

		for (int i = 0; i < list1.size(); i++) {
			logger.info("list1全部加1之后：{}", list1.get(i));
		}
	}

	@Override
	public String toString() {
		return "我是Test";
	}

}

class Tets1 implements Runnable {

	private int i = 0;

	private List<Integer> list = null;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public List<Integer> getList() {
		return list;
	}

	public void setList(List<Integer> list) {
		this.list = list;
	}

	@Override
	public void run() {
		for (int j = i * 10; j < (i + 1) * 10; j++) {
			int count = list.get(j) + 1;
			list.set(j, count);
		}
	}
}
