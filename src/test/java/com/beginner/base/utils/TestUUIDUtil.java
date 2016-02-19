package com.beginner.base.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestUUIDUtil implements Runnable {

	private Logger logger = LoggerFactory.getLogger(TestUUIDUtil.class);

	@Override
	public void run() {
		logger.info("32位UUID：{}", UUIDUtil.get32UUID());
	}

	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 1000; i++) {
			pool.execute(new TestUUIDUtil());
		}
		pool.shutdown();

		try (Connection conn = DriverManager.getConnection("", "", "");
			PreparedStatement ps = (PreparedStatement) conn.createStatement()) {
			
			String sql = "SELECT * FROM liu_da_ran T WHERE T.ID='liuranran'";
			ps.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
