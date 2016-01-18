package com.beginner.base.dao;

import java.util.List;

/**
 * @author Administrator
 *
 */
public interface DAO {

	/**
	 * 新增
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object save(String str, Object obj) throws Exception;

	/**
	 * 批量新增
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object saveBatch(String str, Object obj) throws Exception;

	/**
	 * 批量新增
	 * @param str
	 * @param objs
	 * @throws Exception
	 */
	public void batchSave(String str, List<?> objs) throws Exception;

	/**
	 * 更新
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object update(String str, Object obj) throws Exception;

	/**
	 * 批量更新
	 * @param str
	 * @param objs
	 * @throws Exception
	 */
	public void batchUpdate(String str, List<?> objs) throws Exception;

	/**
	 * 删除 
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object delete(String str, Object obj) throws Exception;

	/**
	 * 批量删除
	 * @param str
	 * @param objs
	 * @throws Exception
	 */
	public void batchDelete(String str, List<?> objs) throws Exception;

	/**
	 * 查找对象（1个）
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object findForObject(String str, Object obj) throws Exception;

	/**
	 * 查找对象（N个）
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object findForList(String str, Object obj) throws Exception;

	/**
	 * 查找对象封装成Map
	 * @param s
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object findForMap(String sql, Object obj, String key, String value) throws Exception;
}
