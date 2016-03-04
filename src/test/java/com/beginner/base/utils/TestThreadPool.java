package com.beginner.base.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestThreadPool {

	private static Logger logger = LoggerFactory.getLogger(TestThreadPool.class);

	public static void main(String[] args) {
		//http://cuisuqiang.iteye.com/blog/2019372
		testCachedThreadPool();
		testFixedThreadPool();
		testScheduledThreadPool();
		testScheduledThreadPoolAtFixedRate();
		testSingleThreadExecutor();
	}

	//创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
	private static void testCachedThreadPool() {
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			final int index = i;
			try {
				//线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
				Thread.sleep(index * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			executor.execute(new Runnable() {

				@Override
				public void run() {
					logger.info("index的值：" + index);
				}
			});
		}
	}
	//创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
	private static void testFixedThreadPool() {
		ExecutorService executor = Executors.newFixedThreadPool(3);
		//因为线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字。
		//定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()
		for (int i = 0; i < 12; i++) {
			final int index = i;
			executor.execute(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(2000);
						logger.info("index的值：" + index);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	//创建一个定长线程池，支持定时及周期性任务执行。
	private static void testScheduledThreadPool() {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
		executor.schedule(new Runnable() {

			@Override
			public void run() {
				logger.info("执行啦！");
			}
		}, 3, TimeUnit.SECONDS);//表示延迟3秒执行。
	}
	//创建一个定长线程池，支持定时及周期性任务执行。
	private static void testScheduledThreadPoolAtFixedRate() {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
		executor.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				logger.info("ScheduledThreadPoolAtFixedRate");
			}
		}, 1, 3, TimeUnit.SECONDS);//表示延迟1秒后每3秒执行一次。
	}
	//创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
	private static void testSingleThreadExecutor() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 10; i++) {
			final int index = i;
			executor.execute(new Runnable() {

				@Override
				public void run() {
					try {
						logger.info("SingleThreadExecutor测试...{}", index);
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
}
