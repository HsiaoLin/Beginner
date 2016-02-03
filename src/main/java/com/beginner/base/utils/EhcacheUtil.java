package com.beginner.base.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.search.Attribute;
import net.sf.ehcache.search.Query;

import com.beginner.base.common.Const;

/**
* <b>类名称：</b>EhcacheUtil<br/>
* <b>类描述：</b>ehcache缓存工具类<br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
public class EhcacheUtil {

	private static final String EHCACHE_SETTING = "ehcache.configFile";

	/**
	 * 缓存管理
	 */
	private static CacheManager cacheManager;

	static {
		cacheManager = getCacheManager(ResourcesUtil.getProperty(EHCACHE_SETTING, Const.BEGINNER));
	}

	/**
	 * 获取缓存管理类的实例，指定配置文件名称
	 * @param ehcache 指定配置文件的名称比如：ehcache-failsafe.xml、ehcache.xml等
	 * @return CacheManager 缓存管理类实例
	 */
	public static CacheManager getCacheManager(String ehcache) {
		return CacheManager.create(EhcacheUtil.class.getClassLoader().getResourceAsStream(ehcache));
	}

	/**
	 * 清除所有缓存的数据，但是缓存实例仍存在
	 */
	public static void clearAll() {
		cacheManager.clearAll();
	}

	/**
	 * 关闭缓存
	 */
	public static void shutdown() {
		cacheManager.shutdown();
	}

	/**
	 * 获取所有缓存实例的名称不包含defaultCache
	 * @return String[] 所有缓存实例名称的数组
	 */
	public static String[] getCacheNames(){
		return cacheManager.getCacheNames();
	}

	/**
	 * 根据指定名称获取缓存的实例对象
	 * @param cacheName 	缓存实例名称
	 * @return Cache 		缓存对象
	 */
	public static Cache getCache(String cacheName){
		return cacheManager.getCache(cacheName);
	}

	/**
	 * 根据指定名称获取缓存在内存中的配置信息，缓存配置动态修改也会体现出来
	 * @param cacheName 	缓存实例名称
	 * @return String 		XML结构的字符串配置信息
	 */
	public static String getActiveConfigText(String cacheName){
		return cacheManager.getActiveConfigurationText();
	}

	/**
	 * 从内存中删除一个缓存以及所有的数据，Cache被销毁
	 * @param cacheName 缓存实例名称
	 */
	public static void removeCache(String cacheName) {
		cacheManager.removeCache(cacheName);
	}

	/**
	 * 根据指定名称获取缓存配置信息的实例对象
	 * @param cache 				缓存实例
	 * @return CacheConfiguration 	缓存配置信息
	 */
	public static CacheConfiguration getCacheConfig(Cache cache) {
		return cache.getCacheConfiguration();
	}

	/**
	 * 把数据从内存刷到DiskStore，从DiskStore刷新到Disk中
	 * @param cache 缓存实例
	 */
	public static void flush(Cache cache) {
		cache.flush();
	}

	/**
	 * 把数据放入缓存
	 * @param cache 	缓存实例
	 * @param element 	要缓存的元素
	 */
	public static void put(Cache cache, Element element) {
		cache.put(element);
	}

	/**
	 * 如果不存在才把数据放入缓存
	 * @param cache 	缓存实例
	 * @param element 	要缓存的元素
	 * @return 			如果存在返回已存在的元素，不存在返回放入的元素
	 */
	public static Element putIfAbsent(Cache cache, Element element) {
		return cache.putIfAbsent(element);
	}

	/**
	 * 根据key在缓存中取得对应的元素
	 * @param cache 	缓存实例
	 * @param key 		元素的名
	 * @return 			要取得的元素
	 */
	public static Element get(Cache cache, Object key) {
		return cache.get(key);
	}

	/**
	 * 根据key在缓存中取得对应的元素的值
	 * @param cache 	缓存实例
	 * @param key 		元素的名
	 * @return Object	元素的值
	 */
	public static Object getObjectValue(Cache cache, Object key) {
		return get(cache, key).getObjectValue();
	}

	/**
	 * 根据key在缓存中取得对应的元素的版本
	 * @param cache 	缓存实例
	 * @param key 		元素的名
	 * @return long		元素的版本
	 */
	public static long getVersion(Cache cache, Object key) {
		return get(cache, key).getVersion();
	}

	/**
	 * 根据key在缓存中取得对应的元素的名
	 * @param cache 	缓存实例
	 * @param key 		元素的名
	 * @return long		元素的名
	 */
	public static Object getObjectKey(Cache cache, Object key) {
		return get(cache, key).getObjectKey();
	}

	/**
	 * 删除缓存中所有数据
	 * @param cache 缓存实例
	 */
	public static void removeAll(Cache cache) {
		cache.removeAll();
	}

	/**
	 * 删除缓存中指定key的数据
	 * @param cache 	缓存实例
	 * @return boolean 	成功则返回true否则返回false
	 */
	public static boolean remove(Cache cache, Object key) {
		return cache.remove(key);
	}

	/**
	 * 替换缓存中的元素
	 * @param cache 	缓存实例
	 * @param element 	要替换的元素
	 * @return 			返回替换前的元素
	 */
	public static Element replace(Cache cache, Element element) {
		return cache.replace(element);
	}

	/**
	 * 替换缓存中的元素
	 * @param cache 	缓存实例
	 * @param element 	要替换的元素的名
	 * @param element 	要替换的元素的值
	 * @return 			返回替换前的元素
	 */
	public static Element replace(Cache cache, Object key, Object value) {
		return cache.replace(newElement(key, value));
	}

	/**
	 * 替换缓存中的元素
	 * @param cache 	缓存实例
	 * @param element 	要替换的元素的名
	 * @param element 	要替换的元素的值
	 * @param element 	要替换的元素的版本
	 * @return 			返回替换前的元素
	 */
	public static Element replace(Cache cache, Object key, Object value, long version) {
		return cache.replace(newElement(key, value, version));
	}

	/**
	 * 根据key、value构造一个元素
	 * @param key 		元素的名
	 * @param value 	元素的值
	 * @return 			构造的元素
	 */
	public static Element newElement(Object key, Object value) {
		return newElement(key, value, 1L);
	}

	/**
	 * 根据key、value构造一个元素
	 * @param key 		元素的名
	 * @param value 	元素的值
	 * @param version 	元素的版本
	 * @return 			构造的元素
	 */
	public static Element newElement(Object key, Object value, long version) {
		return new Element(key, value, version);
	}

	/**
	 * 获取缓存查询对象
	 * @param cache 缓存实例
	 * @return 		缓存查询对象
	 */
	public static Query createQuery(Cache cache) {
		return cache.createQuery();
	}

	/**
	 * 获取缓存查询属性对象
	 * @param cache 缓存实例
	 * @return 		查询属性对象
	 */
	public static Attribute<Object> getSearchAttribute(Cache cache) {
		return cache.getSearchAttribute("key");
	}
}
