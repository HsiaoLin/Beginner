package com.beginner.base.utils;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * <b>类名称：</b>RedisUtil<br/>
 * <b>类描述：</b>Redis工具类<br/>
 * <p>Redis学习配置参考：http://www.runoob.com/redis/redis-conf.html</p>
 * <p>Redis安全相关参考：http://www.freebuf.com/articles/system/60654.html</p>
 * <b>创建人：</b>Hsiao Lin Studio<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class Redis {

	public static void main(String[] args) {

		//具体使用方法参见：com.beginner.system.controller.user.UserController.list
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		JedisPool jedisPool = (JedisPool) context.getBean("jedisPool");

		// try-with-resource方式自动关闭Jedis连接（JDK1.7及以上）
		try (Jedis jedis = jedisPool.getResource()) {
			// 一key一值
			jedis.set("测试key", "测试值");
			System.out.println("‘测试key’的值是：" + jedis.get("测试key"));

			// 存储数据到列表中
			jedis.lpush("test-list", "Redis");
			jedis.lpush("test-list", "Mongodb");
			jedis.lpush("test-list", "Mysql");
			// 获取存储的数据并输出
			List<String> list = jedis.lrange("test-list", 0, 3);
			// 取得的List按照数据放入顺序的倒序排列
			for (int i = 0; i < list.size(); i++) {
				System.out.println("test-list" + i + "：" + list.get(i));
			}

			// 清空当前DB
			jedis.flushDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
