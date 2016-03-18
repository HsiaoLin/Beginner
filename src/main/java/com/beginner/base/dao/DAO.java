/*
 * Copyright 2015-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
