package com.beginner.base.utils;

import org.bson.Document;
import org.slf4j.LoggerFactory;

import com.beginner.core.utils.PropertyUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class TestMongodb {

	private static org.slf4j.Logger log = LoggerFactory.getLogger(TestMongodb.class);

	public static void main(String[] args) {
		String host = PropertyUtil.getStr("mongodb.host");
		int port = PropertyUtil.getInt("mongodb.port");
		try (MongoClient mongoClient = new MongoClient(host, port)) {
			log.info("connected to {}:{}", host, port);

			// 连接到数据库
			MongoIterable<String> names = mongoClient.listDatabaseNames();
			for (String name : names) {
				log.info("数据库名称：{}", name);
			}

			MongoDatabase mongoDb = mongoClient.getDatabase("local");
			MongoCollection<Document> c = mongoDb.getCollection("test");
			log.info("集合数量：{}", c.count());
		} catch (Exception e) {
			log.error("操作MongoDB异常：{}", e);
		}
	}
}
