package com.beginner.base.dao;

import java.util.List;

public interface DAO {

	/**
	 * 新增
	 */
	public Object save(String str, Object obj) throws Exception;

	/**
	 * 批量新增
	 */
	public Object saveBatch(String str, Object obj) throws Exception;

	/**
	 * 批量新增
	 */
	public void batchSave(String str, List<?> objs) throws Exception;

	/**
	 * 更新
	 */
	public Object update(String str, Object obj) throws Exception;

	/**
	 * 批量更新
	 */
	public void batchUpdate(String str, List<?> objs) throws Exception;

	/**
	 * 删除 
	 */
	public Object delete(String str, Object obj) throws Exception;

	/**
	 * 批量删除
	 */
	public void batchDelete(String str, List<?> objs) throws Exception;

	/**
	 * 查找对象（1个）
	 */
	public Object findForObject(String str, Object obj) throws Exception;

	/**
	 * 查找对象（N个）
	 */
	public Object findForList(String str, Object obj) throws Exception;

	/**
	 * 查找对象封装成Map
	 */
	public Object findForMap(String sql, Object obj, String key, String value) throws Exception;
}
