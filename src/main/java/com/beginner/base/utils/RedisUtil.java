package com.beginner.base.utils;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.beginner.base.common.Const;

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
public class RedisUtil {

	private static final String HOST = PropertyUtil.getProperty("redis.host", Const.BEGINNER);

	private static final Integer PORT = Integer.parseInt(PropertyUtil.getProperty("redis.port", Const.BEGINNER));

	private static final Integer TIMEOUT = Integer.parseInt(PropertyUtil.getProperty("redis.timeout", Const.BEGINNER));

	private static final String PASSWORD = PropertyUtil.getProperty("redis.password", Const.BEGINNER);

	private static final Integer DATABASE = Integer.parseInt(PropertyUtil.getProperty("redis.database", Const.BEGINNER));

	private static final String KEYPREFIX = PropertyUtil.getProperty("redis.keyPrefix", Const.BEGINNER);

	private static JedisPool pool;

	static {
		pool = new JedisPool(new JedisPoolConfig(), HOST, PORT, TIMEOUT, PASSWORD, DATABASE);
	}

	public static Jedis getJedis() {
		return pool.getResource();
	}

	public static void main(String[] args) {
		try (Jedis jedis = getJedis()) {
			jedis.set("测试key", "测试值");
			System.out.println(jedis.get("测试key"));

			//存储数据到列表中
			jedis.lpush("test-list", "Redis");
			jedis.lpush("test-list", "Mongodb");
			jedis.lpush("test-list", "Mysql");
			// 获取存储的数据并输出
			List<String> list = jedis.lrange("test-list", 0, 5);
			// 按照放入顺序倒序输出
			for (int i = 0; i < list.size(); i++) {
				System.out.println("test-list" + i + "：" + list.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
