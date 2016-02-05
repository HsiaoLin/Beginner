package com.beginner.base.utils;

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
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beginner.base.common.Const;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestEhcacheUtil {

	private static Logger log = LoggerFactory.getLogger(TestEhcacheUtil.class);

	private static final String EHCACHE_SETTING = "ehcache.configFile";

	@Test
	public void testGetCacheManager() {
		log.info("testGetCacheManager：" + EhcacheUtil.getCacheManager(ResourcesUtil.getProperty(EHCACHE_SETTING, Const.BEGINNER)));
		Assert.assertNotNull(EhcacheUtil.getCacheManager(ResourcesUtil.getProperty(EHCACHE_SETTING, Const.BEGINNER)));
	}

	@Test
	public void testGetCacheNames() {
		log.info("testGetCacheNames：" + EhcacheUtil.getCacheNames()[0]);
		Assert.assertEquals(EhcacheUtil.getCacheNames()[0], "beginner");
	}

	@Test
	public void testGetCache() {
		log.info("testGetCache：" + EhcacheUtil.getCache("beginner"));
		Assert.assertNotNull(EhcacheUtil.getCache("beginner"));
	}

	@Test
	public void testGetActiveConfigText() {
		log.info("testGetActiveConfigText：" + EhcacheUtil.getActiveConfigText("beginner"));
		Assert.assertNotNull(EhcacheUtil.getActiveConfigText("beginner"));
	}

	@Test
	public void testGetCacheConfig() {
		Cache cache = EhcacheUtil.getCache("beginner");
		CacheConfiguration config = EhcacheUtil.getCacheConfig(cache);

		log.info("testGetCacheConfig：" + config);
		Assert.assertNotNull(config);
	}

	@Test
	public void testPut() {
		Cache cache = EhcacheUtil.getCache("beginner");
		EhcacheUtil.put(cache, EhcacheUtil.newElement("test1", "测试put"));
		String value = (String) cache.get("test1").getObjectValue();

		log.info("testPut：" + value);
		Assert.assertEquals("测试put", value);
	}

	@Test
	public void testPutIfAbsent() {
		Cache cache = EhcacheUtil.getCache("beginner");

		EhcacheUtil.put(cache, EhcacheUtil.newElement("test1", "测试put"));
		Element e = EhcacheUtil.putIfAbsent(cache, EhcacheUtil.newElement("test1", "测试putIfAbsent"));
		String value = (String) cache.get("test1").getObjectValue();

		log.info("testPutIfAbsent：" + e.getObjectValue());
		log.info("testPutIfAbsent：" + value);
		Assert.assertEquals("测试put", value);
	}

	@Test
	public void testGet() {
		Cache cache = EhcacheUtil.getCache("beginner");

		EhcacheUtil.put(cache, EhcacheUtil.newElement("test1", "测试get"));
		String value = (String) EhcacheUtil.get(cache, "test1").getObjectValue();

		log.info("testGet：" + value);
		Assert.assertEquals("测试get", value);
	}

	@Test
	public void testGetObjectValue() {
		Cache cache = EhcacheUtil.getCache("beginner");

		EhcacheUtil.put(cache, EhcacheUtil.newElement("test1", "测试getObjectValue"));
		String value = (String) EhcacheUtil.getObjectValue(cache, "test1");

		log.info("testGetObjectValue：" + value);
		Assert.assertEquals("测试getObjectValue", value);
	}

	@Test
	public void testGetVersion() {
		Cache cache = EhcacheUtil.getCache("beginner");

		EhcacheUtil.put(cache, EhcacheUtil.newElement("test1", "测试getObjectValue"));
		long version = EhcacheUtil.getVersion(cache, "test1");

		log.info("testGetObjectValue：" + version);
		Assert.assertEquals(1L, version);
	}

	@Test
	public void testGetObjectKey() {
		Cache cache = EhcacheUtil.getCache("beginner");

		EhcacheUtil.put(cache, EhcacheUtil.newElement("test1", "测试getObjectValue"));
		String key = (String) EhcacheUtil.getObjectKey(cache, "test1");

		log.info("testGetObjectKey：" + key);
		Assert.assertEquals("test1", key);
	}

	@Test
	public void testReplace() {
		Cache cache = EhcacheUtil.getCache("beginner");
		if (null == cache) {
			cache = new Cache(new CacheConfiguration("beginner", 100));
			EhcacheUtil.getCacheManager(ResourcesUtil.getProperty(EHCACHE_SETTING, Const.BEGINNER)).addCache(cache);
		}
		EhcacheUtil.put(cache, EhcacheUtil.newElement("我是element", 1));
		EhcacheUtil.put(cache, EhcacheUtil.newElement(11, 11));
		EhcacheUtil.put(cache, EhcacheUtil.newElement(12, 12));

		Element element = EhcacheUtil.replace(cache, EhcacheUtil.newElement("我是element", 2));
		Element element11 = EhcacheUtil.replace(cache, 11, 111);
		Element element12 = EhcacheUtil.replace(cache, 12, 121, 1L);

		log.info("testRemoveElement：" + cache.get("我是element").getObjectValue() + "：" + element.getObjectValue());
		log.info("testRemoveElement：" + cache.get(11).getObjectValue() + "：" + element11.getObjectValue());
		log.info("testRemoveElement：" + cache.get(12).getObjectValue() + "：" + element12.getObjectValue());

		Assert.assertEquals(element.getObjectValue(), 1);
		Assert.assertEquals(element11.getObjectValue(), 11);
		Assert.assertEquals(element12.getObjectValue(), 12);
	}

	@Test
	public void testRemoveAll() {
		Cache cache = EhcacheUtil.getCache("beginner");

		EhcacheUtil.put(cache, EhcacheUtil.newElement("test1", "测试getObjectValue"));
		EhcacheUtil.removeAll(cache);
		Element e = EhcacheUtil.get(cache, "test1");

		log.info("testRemoveAll：" + e);
		Assert.assertNull(e);
	}

	@Test
	public void testRemove() {
		Cache cache = EhcacheUtil.getCache("beginner");

		Element e = EhcacheUtil.newElement("test", 1);
		EhcacheUtil.put(cache, EhcacheUtil.newElement("我是element", e));
		boolean flag = EhcacheUtil.remove(cache, "我是element");

		log.info("testRemove：" + flag);
		Assert.assertEquals(flag, true);
	}

	@Test
	public void testClearAll() {
		EhcacheUtil.clearAll();

		log.info("testRemoveCache：" + EhcacheUtil.getCache("beginner"));
		Assert.assertNotNull(EhcacheUtil.getCache("beginner"));
	}

	@Test
	public void testRemoveCache() {
		EhcacheUtil.removeCache("beginner");

		log.info("testRemoveCache：" + EhcacheUtil.getCache("beginner"));
		Assert.assertNull(EhcacheUtil.getCache("beginner"));
	}

	@Test
	public void testQueryElementsFromCache() {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("ehcache.xml");
		CacheManager cm = CacheManager.create(in);
		Cache cache = cm.getCache("beginner");
		cache.removeAll();
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
		//根据默认提供的可查询属性key进行查询
		Attribute<Integer> keyAttribute = cache.getSearchAttribute("key");

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
