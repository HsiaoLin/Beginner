package com.beginner.base.utils;

import org.bson.Document;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class TestMongodb {

	private static org.slf4j.Logger log = LoggerFactory.getLogger(TestMongodb.class);

	public static void main(String[] args) {
		// 连接到 mongodb 服务
		MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
		log.info("已经建立连接(127.0.0.1:27017)...");

		// 连接到数据库
		MongoIterable<String> names = mongoClient.listDatabaseNames();
		for (String name : names) {
			log.info("数据库名称：{}", name);
		}

		MongoDatabase mongoDb = mongoClient.getDatabase("local");
		MongoCollection<Document> c = mongoDb.getCollection("test");
		log.info("集合数量：{}", c.count());
		//		if (null == c) {
		//			mongoDb.createCollection("test");
		//		}
		mongoClient.close();
	}
}
