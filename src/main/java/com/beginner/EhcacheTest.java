package com.beginner;

import java.io.InputStream;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.search.Attribute;
import net.sf.ehcache.search.Query;
import net.sf.ehcache.search.Result;
import net.sf.ehcache.search.Results;

import org.junit.Assert;
import org.junit.Test;

public class EhcacheTest {

	@Test
	public void testCacheManager() {
		InputStream in = this.getClass().getResourceAsStream("ehcache.xml");
		CacheManager cm = CacheManager.create(in);

		//列出所有的缓存名称，不包括配置文件中的<defaultCache>
		String[] names = cm.getCacheNames();

		System.out.println("所有缓存实例：");
		for (String s : names) {
			System.out.println(s);
		}
		System.out.println();

		//只有一个名称为beginner的cache
		Assert.assertEquals(1, names.length);
		Assert.assertEquals(names[0], "beginner");

		//根据指定名称查找缓存对象
		Cache cache = cm.getCache("beginner"); //根据缓存名称获取缓存
		Assert.assertNotNull(cache);

		//获取，更新Cache配置的接口
		CacheConfiguration configuration = cache.getCacheConfiguration();
		configuration.setTimeToIdleSeconds(3600);

		//缓存在内存中的配置信息，缓存配置动态修改也会体现出来,
		System.out.println(cm.getActiveConfigurationText());

		//清除所有缓存的数据，但是缓存本身仍然存在
		cm.clearAll();

		//从内存中删除一个缓存以及所有的数据，Cache被销毁
		//cm.removeCache("beginner");
	}

	@Test
	public void testAddElementToCache() {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("ehcache.xml");
		CacheManager cm = CacheManager.create(in);

		Cache cache = cm.getCache("beginner");

		Person p1 = new Person(1, "Jack", 21);
		Person p2 = new Person(2, "Mike", 73);

		cache.putIfAbsent(new Element(p1, p1, 1));
		cache.put(new Element(p2, p2, 1));
		cache.putIfAbsent(new Element(p2, p1, 1));//只有Key为p2的数据不存在才插入

		//得到的是p2,而不是p1
		Element e = cache.get(p2);
		Assert.assertEquals(p2, e.getObjectValue());

		//把数据从内存刷到DiskStore，从DiskStore刷新到Disk中
		cache.flush();
	}

	@Test
	public void testRemoveElementFromCache() {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("ehcache.xml");
		CacheManager cm = CacheManager.create(in);

		Cache cache = cm.getCache("beginner");

		Person p1 = new Person(1, "Jack", 21);
		Person p2 = new Person(2, "Mike", 73);

		Element e1 = new Element(p1, p1, 1);
		cache.putIfAbsent(e1);
		Element e2 = new Element(p2, p2, 1);
		cache.put(e2);

		cache.remove(p1);
		boolean isSucc = cache.removeElement(e1);
		//e1已经被删除，因此操作返回false
		Assert.assertFalse(isSucc);

		cache.put(e1);

		cache.removeAll();

		Assert.assertEquals(0, cache.getSize());
	}

	@Test
	public void testUpdateElementInCache() {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("ehcache.xml");
		CacheManager cm = CacheManager.create(in);

		Cache cache = cm.getCache("beginner");

		Person p1 = new Person(1, "Jack", 21);
		Person p2 = new Person(2, "Mike", 73);

		Element e1 = new Element(p1, p1, 1);
		cache.putIfAbsent(e1);
		Element e2 = new Element(p2, p2, 1);
		cache.put(e2);

		e2 = new Element(p2, p1, 1);
		cache.replace(e2);

		Assert.assertEquals(p1, e2.getObjectValue());
	}

	@Test
	public void testQueryElementsFromCache() {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("ehcache.xml");
		CacheManager cm = CacheManager.create(in);
		Cache cache = cm.getCache("beginner");

		//EhCache中的数据类型是Element，它包含Key,Value和一个版本信息
		Element e = new Element(1000, 10000, 1);
		cache.put(e);

		//添加第二个数据
		e = new Element(2000, 20000, 1);
		cache.put(e);

		//缓存中有两条数据
		Assert.assertEquals(2, cache.getSize());

		//通过get方法获得key对应的数据
		e = cache.get(1000);
		Assert.assertEquals(10000, e.getObjectValue());

		//创建查询
		Query q = cache.createQuery();

		//属性是范型类，得到key都应的查询属性对象
		Attribute<Integer> keyAttribute = cache.getSearchAttribute("key"); //根据默认提供的可查询属性key进行查询

		//构造查询条件,这是一个链式写法，一个Query对象可以写多个查询条件
		//创建查找key的值为2000的查询
		q = q.addCriteria(keyAttribute.eq(2000));

		//如果不includeKeys和q.includeValues();,则测试结果集中不包括Keys和Values信息
		q.includeKeys();
		q.includeValues();

		//执行查询
		Results results = q.execute();//执行查询
		Assert.assertNotNull(results);
		Assert.assertEquals(results.size(), 1);

		//列出所有结果
		List<Result> resultList = results.all();
		Result result = resultList.get(0);
		Assert.assertEquals(2000, result.getKey());

		Assert.assertEquals(20000, result.getValue());
	}

}
