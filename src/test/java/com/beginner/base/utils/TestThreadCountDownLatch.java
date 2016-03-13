package com.beginner.base.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* <b>类名称：</b>TestThreadCountDownLatch<br/>
* <b>类描述：</b>CountDownLatch对象用法：等待多线程处理完毕再继续执行。<br/>
* <b>创建人：</b>Hsiao Lin Studio-Hsiao Lin<br/>
* <b>创建时间：</b>2016-3-13 下午5:18:24<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b><br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
public class TestThreadCountDownLatch {

	private static Logger logger = LoggerFactory.getLogger(TestThreadCountDownLatch.class);

	public static void main(String[] args) throws InterruptedException {

		ExecutorService executor = Executors.newFixedThreadPool(10);

		final List<String> list = new ArrayList<String>();

		//初始化CountDownLatch对象，内部计数器=10=线程数
		final CountDownLatch count = new CountDownLatch(10);

		/* CountDownLatch 机制不是用来保护共享资源或者临界区。它是用来同步一个或者多个执行多个任务的线程。
		 * 它只能使用一次。一旦CountDownLatch的计数器到达0，任何对它的方法的调用都是无效的。
		 * 如果你想再次同步，你必须创建新的对象。*/

		for (int i = 0; i < 10; i++) {
			//启动线程
			executor.execute(new Runnable() {

				@Override
				public void run() {
					try {
						//每个线程向list中放一个字符串并且休眠2秒
						list.add("TestThreadCountDownLatch");
						logger.info("添加到list：TestThreadCountDownLatch");
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						//每次调用 countDown() 方法, CountDownLatch 对象内部计数器减一。
						count.countDown();
					}
				}
			});
		}

		/* 调用await()方法的任务将一直阻塞等待,直到这个CountDownLatch对象的计数值减到0为止
		 * await(long time, TimeUnit unit): 此方法会休眠直到被中断；
		 * CountDownLatch 内部计数器到达0或者特定的时间过去了。
		 * TimeUnit 类包含了:DAYS, HOURS, MICROSECONDS, MILLISECONDS, MINUTES, NANOSECONDS, 和 SECONDS.*/

		//这里最多等待3秒
		count.await(3, TimeUnit.SECONDS);

		logger.info("list.size()={}", list.size());

		log(executor);

		if (!executor.isShutdown()) {
			executor.shutdownNow();
		}

		log(executor);
	}

	private static void log(ExecutorService executor) {
		logger.info("是否终止:{}", executor.isTerminated());
		logger.info("是否关闭:{}", executor.isShutdown());
	}
}
