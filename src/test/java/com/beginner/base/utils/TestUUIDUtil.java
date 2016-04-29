package com.beginner.base.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beginner.core.utils.UUIDUtil;

public class TestUUIDUtil implements Runnable {

	private Logger logger = LoggerFactory.getLogger(TestUUIDUtil.class);

	@Override
	public void run() {
		logger.info("32位UUID：{}", UUIDUtil.get32UUID());
	}

	@Test
	public void testRun() {
		ExecutorService pool = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 1000; i++) {
			pool.execute(new TestUUIDUtil());
		}
		pool.shutdown();
	}
}
