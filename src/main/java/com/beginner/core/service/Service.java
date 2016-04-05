/*
 * Copyright 2015-9999 the original author or authors.
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
package com.beginner.core.service;

import java.util.List;

import com.beginner.core.page.Page;
import com.beginner.core.plugin.PageData;

/**
* <b>类名称：</b>Service<br/>
* <b>类描述：</b><br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
public interface Service {

	/**
	* 新增
	*/
	public Object save(PageData pd) throws Exception;

	/**
	 * 批量新增
	 */
	public Object saveBatch(PageData pd) throws Exception;

	/**
	 * 删除
	*/
	public Object delete(PageData pd) throws Exception;

	/**
	 * 修改
	*/
	public Object edit(PageData pd) throws Exception;

	/**
	 * 列表
	*/
	public List<PageData> list(Page page) throws Exception;

	/**
	 * 列表(全部)
	*/
	public List<PageData> listAll(PageData pd) throws Exception;

	/**
	 * 通过id获取数据
	*/
	public PageData findById(PageData pd) throws Exception;

	/**
	 * 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS) throws Exception;
}